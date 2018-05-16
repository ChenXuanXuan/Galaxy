package com.mex.GalaxyChain.mychart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Html;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ProgressBar;

import com.lsj.kchart.kchartlib.chart.KChartView;
import com.mex.GalaxyChain.mychart.bean.CrossBean;
import com.mex.GalaxyChain.mychart.bean.StickData;
import com.mex.GalaxyChain.mychart.obser.IObserver;
import com.mex.GalaxyChain.mychart.obser.ISubject;
import com.mex.GalaxyChain.mychart.utils.GridUtils;
import com.mex.GalaxyChain.mychart.utils.IndexParseUtil;
import com.mex.GalaxyChain.mychart.utils.NewColorUtil;
import com.mex.GalaxyChain.mychart.utils.NewDrawUtils;
import com.mex.GalaxyChain.mychart.utils.NewLineUtil;
import com.mex.GalaxyChain.mychart.utils.NumberUtil;
import com.mex.GalaxyChain.mychart.utils.NumberUtils;

import java.util.ArrayList;


/**
 * Created by LSJ on 2017/5/25.
 * K线View
 */
public class KLineView extends ChartView implements ChartConstant, ISubject {
    //烛形图加空白的宽度和烛形图宽度之比
    public static final float WIDTH_SCALE = 1.2f;
    //烛形图和右侧空白的宽度
    public float DEFUALT_WIDTH = 19;
    //K线所有数据
    private ArrayList<StickData> data;
    //K线展示的数据
    private ArrayList<StickData> showList;
    //一屏烛形图数量
    private int drawCount;
    //没两个烛形图x轴的距离
    private float candleXDistance;

    //当前画图偏移量（往右滑动之后）
    private int offset;
    //y轴最大值
    protected double yMax;
    //y轴最小值
    protected double yMin;

    protected float yUnit;
    protected float xUnit;
    private ArrayList observers = new ArrayList();


    ProgressBar mProgressBar;
    private boolean isRefreshing=false;
    private boolean isLoadMoreEnd=false;
    private boolean mLastScrollEnable;
    private boolean mLastScaleEnable;

    private KChartRefreshListener mRefreshListener;


    public KLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 重置加载更多
     */
    public void resetLoadMoreEnd() {
        isLoadMoreEnd=false;
    }

    public interface KChartRefreshListener{
        /**
         * 加载更多
         * @param chart
         */
        void onLoadMoreBegin(KChartView chart);
    }




    @Override
    protected boolean onViewScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (data != null && drawCount < data.size() && Math.abs(distanceX) > DEFUALT_WIDTH) {
            int temp = offset + (int) (0 - distanceX / DEFUALT_WIDTH);
            if (temp < 0 || temp + drawCount > data.size()) {
            } else {
                offset = temp;
                postInvalidate();
            }
            return true;
        }
        return false;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    private ScaleGestureDetector mScaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            if (data == null) return super.onScale(detector);
            //放大是由1变大，缩小是由1变小
            float scale = detector.getScaleFactor();
            //这个变化太快，把scale变慢一点
            scale = 1 + ((scale - 1) * 0.4f);
            drawCount = (int) (mWidth / DEFUALT_WIDTH);
            if (scale < 1 && drawCount >= data.size()) {
                //缩小时，当缩到一屏显示完时，则不再缩小
            } else if (scale > 1 && drawCount < 50) {
                //放大时，当一屏少于20个时，则不再放大
            } else {
                DEFUALT_WIDTH = DEFUALT_WIDTH * scale;
                invalidate();
            }
            return super.onScale(detector);
        }
    });


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector != null)
            gestureDetector.onTouchEvent(event);
        if (mScaleGestureDetector != null)
            mScaleGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void init() {
        if (data == null) return;
        drawCount = (int) (mWidth / DEFUALT_WIDTH);
        candleXDistance = drawCount * WIDTH_SCALE;
        if (data != null && data.size() > 0) {
            if (drawCount < data.size()) {
                getShowList(offset);
            } else {
                showList = new ArrayList<>();
                showList.addAll(data);
            }
        }
        if (showList == null) return;
        float[] low = new float[showList.size()];
        float[] high = new float[showList.size()];
        int i = 0;
        for (StickData d : showList) {
            low[i] = (float) d.getLow();
            high[i] = (float) d.getHigh();
            i++;
        }
        float[] maxAndMin = NewLineUtil.getMaxAndMin(low, high);
        yMax = maxAndMin[0];
        yMin = maxAndMin[1];
        yUnit = (float) (yMax - yMin) / mainH;
        xUnit = mWidth / drawCount;
    }

    @Override
    protected void drawGrid(Canvas canvas) {
        GridUtils.drawGrid(canvas, mWidth, mainH);
        GridUtils.drawIndexGrid(canvas, indexStartY, mWidth, indexH);
    }

    @Override
    protected void drawCandles(Canvas canvas) {
        if (data == null || data.size() == 0) return;
        float x = 0;
        if (showList == null || showList.size() == 0) return;
        //计算出页面能显示多少个
        for (int i = 0; i < showList.size(); i++) {
            if (drawCount < data.size()) {
                x = mWidth - (mWidth / drawCount * (showList.size() - i));
            } else {
                x = (mWidth / drawCount * i);
            }
            NewDrawUtils.drawCandle(canvas,
                    parseNumber(showList.get(i).getHigh()),
                    parseNumber(showList.get(i).getLow()),
                    parseNumber(showList.get(i).getOpen()),
                    parseNumber(showList.get(i).getClose()),
                    x,
                    parseNumber(showList.get(i).getHigh()),
                    candleXDistance,
                    mWidth);
        }
    }

    /**
     * SMA5 SMA10 SMA20
     *
     * @param canvas
     */
    @Override
    protected void drawLines(Canvas canvas) {
        if (data == null || data.size() == 0) return;
        float[] sma5 = new float[showList.size()];
        float[] sma10 = new float[showList.size()];
        float[] sma20 = new float[showList.size()];
        int size = showList.size();
        for (int i = 0; i < showList.size(); i++) {
            if (size > IndexParseUtil.START_SMA5)
                sma5[i] = (float) showList.get(i).getSma5();
            if (size > IndexParseUtil.START_SMA10)
                sma10[i] = (float) showList.get(i).getSma10();
            if (size > IndexParseUtil.START_SMA20)
                sma20[i] = (float) showList.get(i).getSma20();
        }
        NewDrawUtils.drawLineWithXOffset(canvas, sma5, DEFUALT_WIDTH, mainH, NewColorUtil.COLOR_SMA5, (float) yMax, (float) yMin, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLineWithXOffset(canvas, sma10, DEFUALT_WIDTH, mainH, NewColorUtil.COLOR_SMA10, (float) yMax, (float) yMin, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLineWithXOffset(canvas, sma20, DEFUALT_WIDTH, mainH, NewColorUtil.COLOR_SMA20, (float) yMax, (float) yMin, DEFUALT_WIDTH / 2);
    }

    @Override
    protected void drawText(Canvas canvas) {
        if (data == null || data.size() == 0) return;
        //4，画X轴时间
//        if (showList.size() <= drawCount) {
//            NewDrawUtils.drawKLineXTime(canvas, showList.get(0).getTime(), showList.get(showList.size() - 1).getTime(), mWidth, mainH);
//        } else {
//            NewDrawUtils.drawKLineXTime(canvas, showList.get(0).getTime(), null, mWidth, mainH);
//        }
        //5，画Y轴价格
        NewDrawUtils.drawKLineYPrice(canvas, yMax, yMin, mainH);
    }

    @Override
    protected void drawVOL(Canvas canvas) {
        if (data == null || data.size() == 0) return;
        long max = 0;
        for (StickData data : showList) {
            max = data.getCount() > max ? data.getCount() : max;
        }
        //如果量全为0，则不画
        if (max == 0) return;
        //2,画量线，多条竖直线
        NewDrawUtils.drawVOLRects(canvas, xUnit, indexStartY, indexH, max, showList);
        //3,画量的sma5,sma10
        drawCountSma(canvas, (float) max);
    }

    @Override
    protected void drawZJ(Canvas canvas) {
        if (data == null || data.size() == 0) return;
        float[] sup = new float[showList.size()];
        float[] big = new float[showList.size()];
        float[] mid = new float[showList.size()];
        float[] sma = new float[showList.size()];
        int size = showList.size();
        for (int i = 0; i < showList.size(); i++) {
            sup[i] = (float) showList.get(i).getSp();
            big[i] = (float) showList.get(i).getBg();
            mid[i] = (float) showList.get(i).getMd();
            sma[i] = (float) showList.get(i).getSm();
        }
        float maxAndMin[] = NewLineUtil.getMaxAndMin(sup, big, mid, sma);
        NewDrawUtils.drawLines(canvas, sup, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_ZJ_SUPER, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLines(canvas, big, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_ZJ_BIG, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLines(canvas, mid, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_ZJ_MIDDLE, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLines(canvas, sma, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_ZJ_SMALL, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawIndexMiddleText(canvas, (maxAndMin[0] - maxAndMin[1]) / 2 + "", indexStartY + indexH / 2);
    }

    @Override
    protected void drawMACD(Canvas canvas) {
        if (data == null || data.size() == 0) return;
        float[] dif = new float[showList.size()];
        float[] dea = new float[showList.size()];
        float[] macd = new float[showList.size()];
        for (int i = 0; i < showList.size(); i++) {
            if (data.indexOf(showList.get(i)) > IndexParseUtil.START_DIF) {
                dif[i] = (float) showList.get(i).getDif();
            }
            if (data.indexOf(showList.get(i)) > IndexParseUtil.START_DEA) {
                dea[i] = (float) showList.get(i).getDea();
            }
            macd[i] = (float) showList.get(i).getMacd();
        }
        float maxAndMin[] = NewLineUtil.getMaxAndMin(dif, dea, macd);
        NewDrawUtils.drawMACDRects(canvas, macd, maxAndMin[0], maxAndMin[1], indexH, indexStartY, DEFUALT_WIDTH);
        NewDrawUtils.drawLines(canvas, dif, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_DIF, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLines(canvas, dea, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_DEA, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawIndexMiddleText(canvas, "0", indexStartY + indexH / 2);
    }

    @Override
    protected void drawKDJ(Canvas canvas) {
        if (data == null || data.size() == 0) return;
        float[] kl = new float[showList.size()];
        float[] dl = new float[showList.size()];
        float[] jl = new float[showList.size()];
        int size = showList.size();
        for (int i = 0; i < showList.size(); i++) {
//            if (size > IndexParseUtil.START_K)
            kl[i] = (float) showList.get(i).getK();
//            if (size > IndexParseUtil.START_DJ) {
            dl[i] = (float) showList.get(i).getD();
            jl[i] = (float) showList.get(i).getJ();
//            }
        }
        float maxAndMin[] = NewLineUtil.getMaxAndMin(kl, dl, jl);
        NewDrawUtils.drawLines(canvas, kl, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_KDJ_K, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLines(canvas, dl, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_KDJ_D, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLines(canvas, jl, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_KDJ_J, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawIndexMiddleText(canvas, (maxAndMin[0] - maxAndMin[1]) / 2 + "", indexStartY + indexH / 2);
    }

    /**
     * 画BOLL
     */
    @Override
    protected void drawBOLL(Canvas canvas) {

        if (data == null || data.size() == 0) return;
        float[] up = new float[showList.size()];
        float[] mb = new float[showList.size()];
        float[] dn = new float[showList.size()];
        float ma5 = 0;
        float ma10 = 0;
        float ma20 = 0;


        for (int i = 0; i < showList.size(); i++) {
            StickData point = showList.get(i);
            final double closePrice = point.getClose();

            ma5 += closePrice;
            ma10 += closePrice;
            ma20 += closePrice;
            if (i >= 5) {
                ma5 -= showList.get(i - 5).getClose();
                point.setSma5(ma5 / 5f);
            } else {
                point.setSma5(ma5 / (i + 1f));
            }
            if (i >= 10) {
                ma10 -= showList.get(i - 10).getClose();
                point.setSma10(ma10 / 10f);
            } else {
                point.setSma10(ma10 / (i + 1f));
            }
            if (i >= 20) {
                ma20 -= showList.get(i - 20).getClose();
                point.setSma20(ma20 / 20f);
            } else {
                point.setSma20(ma20 / (i + 1f));
            }

            if (i == 0) {
                mb[i] = (float) point.getClose();
                up[i] = (float) point.getSma5();
                dn[i] = (float) point.getSma20();
            } else {
                int n = 20;
                if (i < 20) {
                    n = i + 1;
                }
                float md = 0;
                for (int j = i - n + 1; j <= i; j++) {
                    double c = showList.get(j).getClose();
                    double m = point.getSma20();
                    double value = c - m;
                    md += value * value;
                }
                md = md / (n - 1);
                md = (float) Math.sqrt(md);
                mb[i] = 0.0f;
                if (i >= 5) {
                    mb[i] = (float) point.getSma5();
                } else if (i >= 10) {
                    mb[i] = (float) point.getSma10();
                } else {
                    mb[i] = (float) point.getSma20();
                }
                up[i] = (float) mb[i] + 2f * md;
                dn[i] = (float) mb[i] - 2f * md;
            }
        }

        float maxAndMin[] = NewLineUtil.getMaxAndMin(up, mb, dn);

        //画笔，价格，两点间距离，控件高度，颜色，价格最大值，价格最小值，是否从零开始，左上角Y轴,指标并不是从左上角开始画
        NewDrawUtils.drawLines(canvas, mb, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_KDJ_K, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLines(canvas, up, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_KDJ_D, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLines(canvas, dn, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_KDJ_J, maxAndMin[0], maxAndMin[1], false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawIndexMiddleText(canvas, NumberUtils.numberFormat((maxAndMin[0] + maxAndMin[1]) / 2 + ""), indexStartY + indexH / 2);
    }

    /**
     * 把传入的参数计算成坐标，直接展示到界面上
     *
     * @param input
     * @return 返回里面的StickData的最高价最低价，都是可以直接显示在坐标上的
     */
    private float parseNumber(double input) {
        return mainH - (float) ((input - yMin) / yUnit);
    }

    /**
     * 画量的均线
     *
     * @param canvas
     * @param max
     */
    private void drawCountSma(Canvas canvas, float max) {
        if (data == null || data.size() == 0) return;
        float[] sma5 = new float[showList.size()];
        float[] sma10 = new float[showList.size()];
        float[] sma20 = new float[showList.size()];
        int size = showList.size();
        for (int i = 0; i < showList.size(); i++) {
            if (size > IndexParseUtil.START_SMA5)
                sma5[i] = (float) showList.get(i).getCountSma5();
            if (size > IndexParseUtil.START_SMA10)
                sma10[i] = (float) showList.get(i).getCountSma10();
            if (size > IndexParseUtil.START_SMA20)
                sma20[i] = (float) showList.get(i).getCountSma20();

        }
        NewDrawUtils.drawLines(canvas, sma5, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_SMA5, max, 0f, false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLines(canvas, sma10, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_SMA10, max, 0f, false, indexStartY + 2, DEFUALT_WIDTH / 2);
        NewDrawUtils.drawLines(canvas, sma20, DEFUALT_WIDTH, indexH - 2, NewColorUtil.COLOR_SMA20, max, 0f, false, indexStartY + 2, DEFUALT_WIDTH / 2);
    }

    public void setDataAndInvalidate(ArrayList<StickData> data) {
        this.data = data;
        parseData();
        postInvalidate();
    }

    /**
     * 获取页面一页可展示的数据
     */
    private void getShowList(int offset) {
        if (offset != 0 && data.size() - drawCount - offset < 0) {
            offset = data.size() - drawCount;
        }
        showList = new ArrayList<>();
        showList.addAll(data.subList(data.size() - drawCount - offset, data.size() - offset));
    }

    /**
     * 设置K线类型
     */
    public void setType(int type) {
        lineType = type;
    }

    /**
     * 计算各指标
     */
    private void parseData() {
        offset = 0;
        //根据当前显示的指标类型，优先计算指标
        IndexParseUtil.initSma(this.data);
        switch (indexType) {
            case INDEX_MACD:
                IndexParseUtil.initMACD(data);
                break;

            case INDEX_KDJ:
                IndexParseUtil.initKDJ(data);
                break;

            case INDEX_BOLL:
                IndexParseUtil.initBOLL(data);
                break;
        }
        //把暂时不显示的计算，放在线程中去完成，避免阻塞主线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (indexType) {
                    case INDEX_VOL:
                        IndexParseUtil.initMACD(data);
                        IndexParseUtil.initKDJ(data);
                        IndexParseUtil.initBOLL(data);
                        break;
                    case INDEX_MACD:
                        IndexParseUtil.initKDJ(data);
                        IndexParseUtil.initBOLL(data);
                        break;
                    case INDEX_KDJ:
                        IndexParseUtil.initMACD(data);
                        IndexParseUtil.initBOLL(data);
                        break;

                    case INDEX_BOLL:
                        IndexParseUtil.initMACD(data);
                        IndexParseUtil.initKDJ(data);
                        break;
                }
            }
        }).start();
    }

    @Override
    public void onCrossMove(float x, float y) {
        super.onCrossMove(x, y);
        if (crossView == null || showList == null) return;
        int position = (int) Math.rint(new Double(x) / new Double(DEFUALT_WIDTH));
        if (position < showList.size() && position >= 0) {
            StickData data = showList.get(position);
            float xIn = (mWidth / drawCount * position) + (mWidth / candleXDistance / 2);
            CrossBean bean = new CrossBean(xIn, getY(data.getClose()));
            bean.price = data.getClose() + "";
            //注意，这个值用来区分，是否画出均线的小圆圈
            bean.y2 = -1;
            bean.timeYMD = data.getTime();
            setIndexTexts(data, bean);
            crossView.drawLine(bean);
            if (crossView.getVisibility() == GONE) {
                crossView.setVisibility(VISIBLE);
                //TODO 这里显示该点数据到屏幕
                msg.setVisibility(VISIBLE);
                for (Object i : observers) {
                    IObserver observer = (IObserver) i;
                    observer.Response(0);
                }
            }
            kai.setText(Html.fromHtml(NewColorUtil.getKai(data)));
            gao.setText(Html.fromHtml(NewColorUtil.getGao(data)));
            fu.setText(Html.fromHtml(NewColorUtil.getFu(data)));
            chengjiao.setText(Html.fromHtml(NewColorUtil.getChengjiao(data)));
            shou.setText(Html.fromHtml(NewColorUtil.getShou(data)));
            di.setText(Html.fromHtml(NewColorUtil.getDi(data)));
            e.setText(Html.fromHtml(NewColorUtil.getE(data)));
            junjia.setText(Html.fromHtml(NewColorUtil.getJunjia(data)));
            sma5.setText(Html.fromHtml(NewColorUtil.getSMA5(data)));
            sma10.setText(Html.fromHtml(NewColorUtil.getSMA10(data)));
            sma20.setText(Html.fromHtml(NewColorUtil.getSMA20(data)));
        }
    }

    @Override
    public void onDismiss() {
        msg.setVisibility(GONE);
        for (Object i : observers) {
            IObserver observer = (IObserver) i;
            observer.Response(1);
        }
    }

    //获取价格对应的Y轴
    private float getY(double price) {
        return mainH - (new Float(price) - (float) yMin) / yUnit;
    }


    /**
     * 设置指标左上角的文字
     *
     * @param data
     * @param bean
     */
    private void setIndexTexts(StickData data, CrossBean bean) {
        switch (indexType) {
            case INDEX_VOL:
                bean.indexText = new String[4];
                if (data.getCount() >= 10000) {
                    bean.indexText[0] = "VOL:" + NumberUtil.beautifulDouble(data.getCount() / 10000.00, 2) + "万";
                } else {
                    bean.indexText[0] = "VOL:" + data.getCount();
                }

                if (data.getCountSma5() >= 10000) {
                    bean.indexText[1] = "MA5:" + NumberUtil.beautifulDouble(data.getCountSma5() / 10000.00, 2) + "万";
                } else {
                    if (data.getCountSma5() == 0) {
                        bean.indexText[1] = "MA5:-";
                    } else {
                        bean.indexText[1] = "MA5:" + NumberUtil.beautifulDouble(data.getCountSma5(), 0);
                    }
                }

                if (data.getCountSma10() >= 10000) {
                    bean.indexText[2] = "MA10:" + NumberUtil.beautifulDouble(data.getCountSma10() / 10000.00, 2) + "万";
                } else {
                    if (data.getCountSma10() == 0.0) {
                        bean.indexText[2] = "MA10:-";
                    } else {
                        bean.indexText[2] = "MA10:" + NumberUtil.beautifulDouble(data.getCountSma10(), 0);
                    }
                }

                if (data.getCountSma20() >= 10000) {
                    bean.indexText[3] = "MA20:" + NumberUtil.beautifulDouble(data.getCountSma20() / 10000.00, 2) + "万";
                } else {
                    if (data.getCountSma20() == 0) {
                        bean.indexText[3] = "MA20:-";
                    } else {
                        bean.indexText[3] = "MA20:" + NumberUtil.beautifulDouble(data.getCountSma20(), 0);
                    }
                }
                bean.indexColor = new int[]{
                        data.isRise() ? NewColorUtil.COLOR_RED : NewColorUtil.COLOR_GREEN,
                        NewColorUtil.COLOR_SMA5,
                        NewColorUtil.COLOR_SMA10,
                        NewColorUtil.COLOR_SMA20
                };
                break;
            case INDEX_MACD:
                bean.indexText = new String[4];
                bean.indexText[0] = "MACD(12,26,9)";
                bean.indexText[1] = "DIF：" + NumberUtil.beautifulDouble(data.getDif());
                bean.indexText[2] = "DEA：" + NumberUtil.beautifulDouble(data.getDea());
                bean.indexText[3] = "MACD：" + NumberUtil.beautifulDouble(data.getMacd());
                bean.indexColor = new int[]{
                        Color.BLACK,
                        NewColorUtil.COLOR_DIF,
                        NewColorUtil.COLOR_DEA,
                        NewColorUtil.COLOR_MACD
                };
                break;
            case INDEX_KDJ:
                bean.indexText = new String[4];
                bean.indexText[0] = "KDJ(9,3,3)";
                bean.indexText[1] = "K：" + NumberUtil.beautifulDouble(data.getK());
                bean.indexText[2] = "D：" + NumberUtil.beautifulDouble(data.getD());
                bean.indexText[3] = "J：" + NumberUtil.beautifulDouble(data.getJ());
                bean.indexColor = new int[]{
                        Color.BLACK,
                        NewColorUtil.COLOR_KDJ_K,
                        NewColorUtil.COLOR_KDJ_D,
                        NewColorUtil.COLOR_KDJ_J
                };
                break;

            case INDEX_BOLL:
                bean.indexText = new String[4];
                bean.indexText[0] = "BOLL(9,3,3)";
                bean.indexText[1] = "up：" + NumberUtil.beautifulDouble(data.getUp());
                bean.indexText[2] = "mb：" + NumberUtil.beautifulDouble(data.getMb());
                bean.indexText[3] = "dn：" + NumberUtil.beautifulDouble(data.getDn());
                bean.indexColor = new int[]{
                        Color.BLACK,
                        NewColorUtil.COLOR_KDJ_K,
                        NewColorUtil.COLOR_KDJ_D,
                        NewColorUtil.COLOR_KDJ_J
                };
                break;
        }

    }

    @Override
    public void Aimat(IObserver iObserver) {
        this.observers.add(iObserver);
    }
}
