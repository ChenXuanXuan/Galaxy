package com.mex.GalaxyChain.mychart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Html;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.mex.GalaxyChain.mychart.bean.CMinute;
import com.mex.GalaxyChain.mychart.bean.CrossBean;
import com.mex.GalaxyChain.mychart.bean.FenshiDataResponse;
import com.mex.GalaxyChain.mychart.obser.IObserver;
import com.mex.GalaxyChain.mychart.obser.ISubject;
import com.mex.GalaxyChain.mychart.utils.GridUtils;
import com.mex.GalaxyChain.mychart.utils.LogUtils;
import com.mex.GalaxyChain.mychart.utils.NewColorUtil;
import com.mex.GalaxyChain.mychart.utils.NewDrawUtils;
import com.mex.GalaxyChain.mychart.utils.NewLineUtil;

import java.util.ArrayList;

/**
 * Created by LSJ on 2017/5/25.
 */
public class FenshiView extends ChartView implements ISubject {
    //分时数据
    private FenshiDataResponse data;
    //补全后的所有点
    private ArrayList<CMinute> minutes = new ArrayList<>();
    //所有价格
    private float[] price;
    //所有均线数据
    private float[] average;

    private float[] newPrice;
    //所有均线数据
    private float[] newAverage;
    private int yesData;
    //分时线昨收
    private double yd;
    //分时线昨结
    private float yj;
    //是否白盘夜盘一起显示
    private boolean hasNight;
    private float yMax;
    private float yMin;
    private float xUnit;
    private float yesXUnit;
    private float todXUnit;
    private ArrayList observers = new ArrayList();
    private int type = 1;//1:分时 2:两日

    public FenshiView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean onViewScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector != null)
            gestureDetector.onTouchEvent(event);
        return true;
    }


    @Override
    protected void init() {
        if (data == null) return;
        yd = data.getParam().getLast();
        yj = data.getLastSettle();
        yMax = data.getHigh();
        yMin = data.getLow();
        hasNight = NewLineUtil.hasNight(data.getParam().getDuration());
        type = data.getParam().getCount();
        xUnit = (mWidth) / data.getParam().getLength();
        yesXUnit = (mWidth) / 2 / data.getParam().getYesDataSize();
        todXUnit = (mWidth) / 2 / 720;
        yesData = data.getParam().getYesDataSize();
        LogUtils.e("*****************************yesData:" + yesData);
    }

    @Override
    protected void drawGrid(Canvas canvas) {
        //1,画网格
        if (data != null && data.getParam() != null && NewLineUtil.isIrregular(data.getParam().getDuration())) {
            //如果是不规则网格画不规则网格
            GridUtils.drawIrregularGrid(canvas, mWidth, mainH, data.getParam().getDuration(),data.getParam().getLength());
            GridUtils.drawIrregularIndexGrid(canvas, indexStartY, mWidth, indexH, data.getParam().getDuration(),data.getParam().getLength());
        } else {
            if (hasNight) {
                GridUtils.drawNightGrid(canvas, mWidth, mainH);
                GridUtils.drawNightIndexGrid(canvas, indexStartY, mWidth, indexH);
            } else {
                GridUtils.drawGrid(canvas, mWidth, mainH);
                GridUtils.drawIndexGrid(canvas, indexStartY, mWidth, indexH);
            }
        }

    }

    /**
     * 重新画分时图
     *
     * @param data
     */
    public void setDataAndInvalidate(FenshiDataResponse data) {
        this.data = data;
        minutes = data.getData();
        postInvalidate();
    }

    @Override
    protected void drawText(Canvas canvas) {
        if (data == null) return;
        NewDrawUtils.drawYPercentAndPrice(canvas, yMax, yMin, yj, mWidth, mainH);
        //分时线下面的区间时间如："20:00-2:30|9:00-15:30"
//        NewDrawUtils.drawXTime(canvas, data.getParam().getDuration(), data.getParam().getUntil(), mWidth, mainH);
    }

    @Override
    protected void drawLines(Canvas canvas) {
        if (data == null) return;
        drawAverageLine(canvas);
        drawPriceLine(canvas);
    }

    @Override
    protected void drawVOL(Canvas canvas) {
        if (data == null) return;
        long max = 0;
        for (CMinute minute : minutes) {
            max = minute.getCount() > max ? minute.getCount() : max;
        }
        //如果量全为0，则不画
        if (max != 0) {
            //2,画量线，多条竖直线
            if (type == 1) {
                NewDrawUtils.drawVOLRects(canvas, xUnit, indexStartY, indexH, max, (float) data.getParam().getLast(), minutes);
            } else {
                NewDrawUtils.drawVOLRects(canvas, (yesXUnit + todXUnit) / 2, indexStartY, indexH, max, (float) data.getParam().getLast(), minutes);
//                NewDrawUtils.drawNewVOLRects(canvas, xUnit, indexStartY, indexH, max, (float) data.getParam().getLast(), minutes, mWidth / 2, yesData);
            }
        }
    }

    @Override
    protected void drawZJ(Canvas canvas) {
    }

    /**
     * 价格线
     *
     * @param canvas
     */

    private void drawPriceLine(Canvas canvas) {
        //乘以1.001是为了让上下分别空一点出来
        if (type == 1) {
            double[] maxAndMin = NewLineUtil.getMaxAndMinByYd(yMax, yMin, yj);
            NewDrawUtils.drawLines(canvas, price, xUnit, mainH, NewColorUtil.COLOR_PRICE_LINE, (float) maxAndMin[0], (float) maxAndMin[1], false);
        } else {
            double[] maxAndMin = NewLineUtil.getMaxAndMinByYd(yMax, yMin, yj);
            NewDrawUtils.drawLines(canvas, price, yesXUnit, mainH, NewColorUtil.COLOR_PRICE_LINE, (float) maxAndMin[0], (float) maxAndMin[1], false);
            NewDrawUtils.drawNewLines(canvas, newPrice, todXUnit, mainH, NewColorUtil.COLOR_PRICE_LINE, (float) maxAndMin[0], (float) maxAndMin[1], false, yesData * yesXUnit + 1);
        }
    }

    private void drawAverageLine(Canvas canvas) {
        if (type == 1) {
            price = new float[minutes.size()];
            average = new float[minutes.size()];
            for (int i = 0; i < minutes.size(); i++) {
                price[i] = (float) minutes.get(i).getPrice();
                average[i] = (float) minutes.get(i).getAverage();
            }
            float[] maxAndMin1 = NewLineUtil.getMaxAndMin(average);
            //如果均线值全为0.01则不画改线，否则会影响价格线展示
            if (maxAndMin1[0] == 0.01 && maxAndMin1[1] == 0.01)
                return;
            //乘以1.001是为了让上下分别空一点出来
            double[] maxAndMin = NewLineUtil.getMaxAndMinByYd(yMax, yMin, yj);
            NewDrawUtils.drawLines(canvas, average, xUnit, mainH, NewColorUtil.COLOR_SMA_LINE, (float) maxAndMin[0], (float) maxAndMin[1], false);
        } else {
            price = new float[yesData];
            average = new float[yesData];
            newPrice = new float[minutes.size() - yesData];
            newAverage = new float[minutes.size() - yesData];

            for (int i = 0; i < yesData; i++) {
                price[i] = (float) minutes.get(i).getPrice();
                average[i] = (float) minutes.get(i).getAverage();
            }

            for (int i = 0; i < minutes.size() - yesData; i++) {
                newPrice[i] = (float) minutes.get(i + yesData).getPrice();
                newAverage[i] = (float) minutes.get(i + yesData).getAverage();
            }
            float[] maxAndMin1 = NewLineUtil.getMaxAndMin(average);
            //如果均线值全为0.01则不画改线，否则会影响价格线展示
            if (maxAndMin1[0] == 0.01 && maxAndMin1[1] == 0.01)
                return;
            //乘以1.001是为了让上下分别空一点出来
            double[] maxAndMin = NewLineUtil.getMaxAndMinByYd(yMax, yMin, yj);
            NewDrawUtils.drawLines(canvas, average, yesXUnit, mainH, NewColorUtil.COLOR_SMA_LINE, (float) maxAndMin[0], (float) maxAndMin[1], false);
            NewDrawUtils.drawNewLines(canvas, newAverage, todXUnit, mainH, NewColorUtil.COLOR_SMA_LINE, (float) maxAndMin[0], (float) maxAndMin[1], false, yesData * yesXUnit + 1);
        }
    }

    /**
     * 当十字线移动到某点时，回调到此处，用此处的数据判断是否显示十字线
     *
     * @param x x轴坐标
     * @param y y轴坐标
     */
    @Override
    public void onCrossMove(float x, float y) {
        LogUtils.e("************************************crossmWidth:" + mWidth);
        LogUtils.e("************************************crossX:" + (x - 43));
        x = (x - 43);
        super.onCrossMove(x, y);
        if (type == 1) {
            if (crossView == null || minutes == null) return;
            int position = (int) Math.rint(new Double(x) / new Double(xUnit));
            if (position < minutes.size() && position >= 0) {
                CMinute cMinute = minutes.get(position);
                float cy = (float) getY(cMinute.getPrice());
                CrossBean bean = new CrossBean(position * xUnit, cy);
                bean.y2 = (float) getY(cMinute.getAverage());
                bean.price = cMinute.getPrice() + "";
                bean.time = cMinute.getTime();
                //分时指标
                //setIndexTextAndColor(position, cMinute, bean);
                crossView.drawLine(bean);
                if (crossView.getVisibility() == GONE) {
                    //TODO 这里显示该点数据到屏幕
                    crossView.setVisibility(VISIBLE);
                    llFenshi.setVisibility(VISIBLE);
                    for (Object i : observers) {
                        IObserver observer = (IObserver) i;
                        observer.Response(0);
                    }
                }
                time.setText(Html.fromHtml(NewColorUtil.getTime(cMinute, yj)));
                fenshiPrice.setText(Html.fromHtml(NewColorUtil.getPrice(cMinute, yj)));
                rose.setText(Html.fromHtml(NewColorUtil.getVol(cMinute)));
                averagePrice.setText(Html.fromHtml(NewColorUtil.getAveragePrice(cMinute, yj)));
            }
        } else {
            if (crossView == null || minutes == null) return;
            if (x <= mWidth / 2) {
                int position = (int) Math.rint(new Double(x) / new Double(yesXUnit));
                if (position < yesData && position >= 0) {
                    CMinute cMinute = minutes.get(position);
                    float cy = (float) getY(cMinute.getPrice());
                    CrossBean bean = new CrossBean(position * yesXUnit, cy);
                    bean.y2 = (float) getY(cMinute.getAverage());
                    bean.price = cMinute.getPrice() + "";
                    bean.time = cMinute.getTime();
                    //分时指标
                    //setIndexTextAndColor(position, cMinute, bean);
                    crossView.drawLine(bean);
                    if (crossView.getVisibility() == GONE) {
                        //TODO 这里显示该点数据到屏幕
                        crossView.setVisibility(VISIBLE);
                        llFenshi.setVisibility(VISIBLE);
                        for (Object i : observers) {
                            IObserver observer = (IObserver) i;
                            observer.Response(0);
                        }
                    }
                    time.setText(Html.fromHtml(NewColorUtil.getTime(cMinute, yj)));
                    fenshiPrice.setText(Html.fromHtml(NewColorUtil.getPrice(cMinute, yj)));
                    rose.setText(Html.fromHtml(NewColorUtil.getVol(cMinute)));
                    averagePrice.setText(Html.fromHtml(NewColorUtil.getAveragePrice(cMinute, yj)));
                }
            } else {
                int position1 = (int) Math.rint(new Double(mWidth / 2) / new Double(yesXUnit));
                int position2 = (int) Math.rint(new Double(x - mWidth / 2) / new Double(todXUnit));
                int position = position1 + position2;
                if (position < minutes.size() && position >= yesData) {
                    CMinute cMinute = minutes.get(position);
                    float cy = (float) getY(cMinute.getPrice());
                    CrossBean bean = new CrossBean(position1 * yesXUnit + position2 * todXUnit, cy);
                    bean.y2 = (float) getY(cMinute.getAverage());
                    bean.price = cMinute.getPrice() + "";
                    bean.time = cMinute.getTime();
                    //分时指标
                    //setIndexTextAndColor(position, cMinute, bean);
                    crossView.drawLine(bean);
                    if (crossView.getVisibility() == GONE) {
                        //TODO 这里显示该点数据到屏幕
                        crossView.setVisibility(VISIBLE);
                        llFenshi.setVisibility(VISIBLE);
                        for (Object i : observers) {
                            IObserver observer = (IObserver) i;
                            observer.Response(0);
                        }
                    }
                    time.setText(Html.fromHtml(NewColorUtil.getTime(cMinute, yj)));
                    fenshiPrice.setText(Html.fromHtml(NewColorUtil.getPrice(cMinute, yj)));
                    rose.setText(Html.fromHtml(NewColorUtil.getVol(cMinute)));
                    averagePrice.setText(Html.fromHtml(NewColorUtil.getAveragePrice(cMinute, yj)));
                }
            }
        }
    }

    @Override
    public void onDismiss() {
        //指标字体隐藏
        llFenshi.setVisibility(GONE);
        for (Object i : observers) {
            IObserver observer = (IObserver) i;
            observer.Response(1);
        }
    }

    /**
     * 计算指标左上角应该显示的文字
     */
    private void setIndexTextAndColor(int position, CMinute cMinute, CrossBean bean) {
        switch (indexType) {
            case INDEX_VOL:
                bean.indexText = new String[]{"VOL:" + cMinute.getCount() + "手"};
                bean.indexColor = new int[]{cMinute.getPrice() > yj ? NewColorUtil.COLOR_RED : NewColorUtil.COLOR_GREEN};
                break;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (crossView != null)
            crossView.setVisibility(GONE);
    }

    //获取价格对应的Y轴
    private double getY(double price) {
        double[] maxAndMin = NewLineUtil.getMaxAndMinByYd(yMax, yMin, yj);
        if (price == maxAndMin[0]) return 0;
        if (price == maxAndMin[1]) return mainH;
        return mainH - (new Float(price) - maxAndMin[1]) / ((maxAndMin[0] - maxAndMin[1]) / mainH);
    }


    @Override
    public void Aimat(IObserver iObserver) {
        LogUtils.e("*******************************obserAimat:");
        this.observers.add(iObserver);
    }
}