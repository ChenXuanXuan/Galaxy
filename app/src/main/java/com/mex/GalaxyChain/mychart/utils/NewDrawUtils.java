package com.mex.GalaxyChain.mychart.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.Log;

import com.mex.GalaxyChain.mychart.bean.CMinute;
import com.mex.GalaxyChain.mychart.bean.StickData;
import com.mex.GalaxyChain.mychart.view.KLineView;

import java.util.ArrayList;

/**
 * Created by Lsj on 2016/8/5.
 */
public class NewDrawUtils {
    //分时图时间、价格、涨幅文字大小
    public static final float FENSHI_TEXT_SIZE = 26f;
    //时间文字大小
    public static final float FENSHI_TIME_SIZE = 30f;
    //当文字在最上方的时候，需要往下偏移，如高的涨幅
    public static final float FENSHI_TEXT_OFFSET = 25f;


    /**
     * 传入价格和
     *
     * @param prices   价格
     * @param xUnit    x轴每两点距离
     * @param height   控件高度
     * @param max      价格最大值
     * @param min      价格最小值
     * @param fromZero 是否从0开始，意思是，假如y轴为0是否画出该点（比如在SMA5、SMA10中，并不是由0开始）
     * @return
     */
    public static float[] getLines(float[] prices, float xUnit, float height, float max, float min, boolean fromZero, float y, float xOffset) {
        float[] result = new float[prices.length * 4];
        float yUnit = (max - min) / height;
        for (int i = 0; i < prices.length - 1; i++) {
            //排除起点为0的点
            if (!fromZero && prices[i] == 0) continue;
            result[i * 4 + 0] = xOffset + i * xUnit;
            result[i * 4 + 1] = y + height - (prices[i] - min) / yUnit;
            result[i * 4 + 2] = xOffset + (i + 1) * xUnit;
            result[i * 4 + 3] = y + height - (prices[i + 1] - min) / yUnit;
        }
        return result;
    }

    public static float[] getNewLines(float[] prices, float xUnit, float height, float max, float min, boolean fromZero, float y, float xOffset) {
        float[] result = new float[prices.length * 4];
        float yUnit = (max - min) / height;
        LogUtils.e("*****************************xOffset:" + xOffset);
        for (int i = 0; i < prices.length - 1; i++) {
            //排除起点为0的点
            if (!fromZero && prices[i] == 0) continue;
            result[i * 4 + 0] = xOffset + i * xUnit;
            result[i * 4 + 1] = y + height - (prices[i] - min) / yUnit;
            result[i * 4 + 2] = xOffset + (i + 1) * xUnit;
            result[i * 4 + 3] = y + height - (prices[i + 1] - min) / yUnit;
        }
        return result;
    }

    /**
     * 画线，并且线整体在X轴有偏移
     * 使用：画K线均线时，X轴需要加一点
     */
    public static void drawLineWithXOffset(Canvas canvas, float[] prices, float xUnit, float height, int color, float max, float min, float xOffset) {
        drawLines(canvas, prices, xUnit, height, color, max, min, false, 0, xOffset);
    }

    /**
     * 画线
     *
     * @param canvas
     * @param prices   价格
     * @param xUnit    x轴每两点距离
     * @param height   控件高度
     * @param color    颜色
     * @param max      价格最大值
     * @param min      价格最小值
     * @param fromZero 是否从0开始，意思是，假如y轴为0是否画出该点（比如在SMA5、SMA10中，并不是由0开始）
     */
    public static void drawLines(Canvas canvas, float[] prices, float xUnit, float height, int color, float max, float min, boolean fromZero) {
        drawLines(canvas, prices, xUnit, height, color, max, min, fromZero, 0, 0);
    }

    public static void drawNewLines(Canvas canvas, float[] prices, float xUnit, float height, int color, float max, float min, boolean fromZero, float count) {
        drawLines(canvas, prices, xUnit, height, color, max, min, fromZero, 0, count);
    }

    public static void drawPriceShader(Canvas canvas, float[] prices, float xUnit, float height, float max, float min) {
        float[] points = getLines(prices, xUnit, height, max, min, false, 0, 0);
        if (points == null || points.length == 0) return;
        Shader mShasder = new LinearGradient(0, 0, 0, height, new int[]{Color.parseColor("#aa3483e9"), Color.parseColor("#303483e9")}, null, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(mShasder);
        paint.setStrokeWidth(1.0f);
        for (int i = 1; i < points.length / 4; i++) {
            float x1 = points[i * 4];
            float y1 = points[i * 4 + 1];
            float x2 = points[i * 4 + 2];
            float y2 = points[i * 4 + 3];
            Path path = new Path();
            path.moveTo(x1, height);
            path.lineTo(x1, y1);
            path.lineTo(x2, y2);
            path.lineTo(x2, height);
            path.close();
            canvas.drawPath(path, paint);
        }

    }

    /**
     * 画线
     *
     * @param canvas
     * @param prices   价格
     * @param xUnit    x轴每两点距离
     * @param height   控件高度
     * @param color    颜色
     * @param max      价格最大值
     * @param min      价格最小值
     * @param fromZero 是否从0开始，意思是，假如y轴为0是否画出该点（比如在SMA5、SMA10中，并不是由0开始）
     * @param y        左上角Y轴,指标并不是从左上角开始画
     */
    public static void drawLines(Canvas canvas, float[] prices, float xUnit, float height, int color, float max, float min, boolean fromZero, float y, float xOffset) {
        if (canvas == null) {
            Log.w("DrawUtils", "canvas为空");
            return;
        }
        float tmax = 0f;
        float tmin = 0f;
        for (float f : prices) {
            tmax = tmax > f ? tmax : f;
            tmin = tmin < f ? tmin : f;
        }
        //如果数组中值全为0，则不画该线
        if (tmax == 0 && tmin == 0)
            return;
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(color);
        p.setStrokeWidth(2.0f);
        if (xOffset >= 0) {
            canvas.drawLines(getNewLines(prices, xUnit, height, max, min, fromZero, y, xOffset), p);
        } else {
            canvas.drawLines(getLines(prices, xUnit, height, max, min, fromZero, y, xOffset), p);
        }
        p.reset();
    }

    /**
     * 画蜡烛形图
     *
     * @param canvas
     * @param maxY   最高 传入计算完成的值（对应x,y轴）max/yUnit
     * @param minY   最低 传入计算完成的值（对应x,y轴）
     * @param openY  开盘 传入计算完成的值（对应x,y轴）
     * @param closeY 收盘 传入计算完成的值（对应x,y轴）
     * @param x      x轴 烛形图左上x坐标
     * @param y      y轴坐标 烛形图左上y坐标
     * @param width  屏幕宽度
     */
    public static void drawCandle(Canvas canvas, float maxY, float minY, float openY, float closeY, float x, float y, float drawCount, float width) {
        if (canvas == null) {
            Log.w("DrawUtils", "canvas为空");
            return;
        }
        //当在坐标系之外，不画
        if (x < 0 || y < 0) return;

        float xUnit = width / drawCount;
//        float diff = xUnit - xUnit / KLineView.WIDTH_SCALE;
        float diff = xUnit - xUnit / KLineView.WIDTH_SCALE;
        //是否上涨,由于计算成了Y轴坐标，所以上面的小，下面的大
        boolean isRise = closeY <= openY;

        Paint p = new Paint();
        p.setStrokeWidth(2.0f);
        p.setAntiAlias(true);
        NewColorUtil.getTextColorAsh(isRise ? 1 : -1, 0);
        p.setColor(isRise ? NewColorUtil.COLOR_RED : NewColorUtil.COLOR_GREEN);
        p.setStyle(isRise ? Paint.Style.STROKE : Paint.Style.FILL);
//        canvas.drawLine(x + xUnit / 2, y, x + xUnit / 2, y + (minY - maxY) + 1, p);

        canvas.drawLine(x + xUnit / 2, y, x + xUnit / 2, y + (openY - maxY) - (openY - closeY) + 1, p);
        canvas.drawLine(x + xUnit / 2, y + (openY - maxY), x + xUnit / 2, y + (minY - maxY) + 1, p);
        canvas.drawRect(x + diff / 2, y + ((!isRise ? closeY : openY) - maxY), x + xUnit - diff / 2, y + ((!isRise ? openY : closeY) - maxY) + 1, p);
        p.reset();
    }


    /**
     * K线：Y轴价格
     *
     * @param canvas
     * @param max
     * @param min
     * @param height
     */
    public static void drawKLineYPrice(Canvas canvas, double max, double min, float height) {
        double diff = max - min;
        String p1 = NumberUtil.getMaxPriceString(max);
        String p2 = NumberUtil.getMaxPriceString(min + diff * 3 / 4);
        String p3 = NumberUtil.beautifulDouble(min + diff * 2 / 4);
        String p4 = NumberUtil.getMinPriceString(min + diff * 1 / 4);
        String p5 = NumberUtil.getMinPriceString(min);
        Paint p = new Paint();
        p.setColor(NewColorUtil.COLOR_PRICE_LINE);
        p.setTextSize(FENSHI_TEXT_SIZE);
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(p1, 0, FENSHI_TEXT_OFFSET, p);
        canvas.drawText(p2, 0, height * 1 / 4, p);
        canvas.drawText(p3, 0, height * 2 / 4, p);
        canvas.drawText(p4, 0, height * 3 / 4, p);
        canvas.drawText(p5, 0, height, p);
    }

    /**
     * 分时线：Y轴涨跌幅
     *
     * @param canvas
     * @param max    最大值
     * @param min    最小值
     * @param yj     昨结
     */
    public static void drawYPercentAndPrice(Canvas canvas, float max, float min, float yj, float width, float height) {
        //计算出最大涨跌幅
//        double upPercent = (max - yj) / yj;
//        double downPercent = (min - yj) / yj;
//        double maxPercent = Math.abs(upPercent) > Math.abs(downPercent) ? upPercent : downPercent;
//        double halfPercent = maxPercent / 2d;
        float upPercent = Math.abs((max - yj) / yj);
        float downPercent = Math.abs((min - yj) / yj);
        float rate = Math.max(upPercent, downPercent);
        double maxPercent;
        double halfPercent;
        if (rate >= 0.004) {
            maxPercent = rate;
        } else {
            maxPercent = 0.004;
        }
        halfPercent = maxPercent / 2d;

        //计算出最大价格
//        double diff = Math.abs(max - yd) > Math.abs(min - yd) ? Math.abs(max - yd) : Math.abs(min - yd);
//        String p1 = NumberUtil.getMoneyString(yd + diff);
//        String p2 = NumberUtil.getMoneyString(yd + diff / 2);
//        String p3 = NumberUtil.getMoneyString(yd - diff / 2);
//        String p4 = NumberUtil.getMoneyString(yd - diff);

        double diffMax = yj * (1 + maxPercent);
        double diffMin = yj * (1 - maxPercent);
//        String p1 = numberDouUPFormat(diffMax) > diffMax ? (numberDouUPFormat(diffMax) + "") : (diffMax + 0.01) + "";
//        String p2 = numberDouUPFormat((diffMax + yj) / 2) > (diffMax + yj) / 2 ? (numberDouUPFormat((diffMax + yj) / 2) + "") : ((diffMax + yj) / 2 + 0.01) + "";
//        String p3 = numberDouUPFormat((diffMin + yj) / 2) > (diffMin + yj) / 2 ? (numberDouUPFormat((diffMin + yj) / 2) + "") : ((diffMin + yj) / 2 + 0.01) + "";
//        String p4 = numberDouUPFormat(diffMin) > diffMin ? (numberDouUPFormat(diffMin) + "") : (diffMin + 0.01) + "";
        String p1 = NumberUtil.getMaxPriceString(diffMax);
        String p2 = NumberUtil.getMaxPriceString((diffMax + yj) / 2);
        String p3 = NumberUtil.getMinPriceString((diffMin + yj) / 2);
        String p4 = NumberUtil.getMinPriceString(diffMin);

//        double finalMaxPercent;
//        double halfPercent;
//        finalMaxPercent = numberDouFormat4(maxPercent) > maxPercent ? numberDouFormat4(maxPercent) : (maxPercent + 0.0001);
//        halfPercent = finalMaxPercent / 2d;

        Paint p = new Paint();
        p.setColor(NewColorUtil.getTextColorAsh(1, 0));
        p.setTextSize(FENSHI_TEXT_SIZE);
        p.setTextAlign(Paint.Align.RIGHT);
        //最大涨幅(价格)
        canvas.drawText(NumberUtil.getPercentString(Math.abs(maxPercent)), width, FENSHI_TEXT_OFFSET, p);
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(p1, 0, FENSHI_TEXT_OFFSET, p);
        //一半涨幅(价格)
        p.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(NumberUtil.getPercentString(Math.abs(halfPercent)), width, height * 1 / 4, p);
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(p2, 0, height * 1 / 4, p);
        //中间0%
        p.setColor(NewColorUtil.getTextColorAsh(0, 0));
        p.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("0.00%", width, height / 2, p);
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(NumberUtil.beautifulDouble(yj), 0, height * 2 / 4, p);
        //跌幅一半
        p.setColor(NewColorUtil.getTextColorAsh(0, 1));
        p.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("-" + NumberUtil.getPercentString(Math.abs(halfPercent)), width, height * 3 / 4, p);
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(p3, 0, height * 3 / 4, p);
        //最下面跌幅
        p.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("-" + NumberUtil.getPercentString(Math.abs(maxPercent)), width, height - 4, p);
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(p4, 0, height - 4, p);
        p.reset();
    }

    /**
     * 分时图：X轴时间
     *
     * @param canvas
     * @param duration 服务器返回的时间
     * @param height   高度
     * @param width    宽
     */
    public static void drawXTime(Canvas canvas, String duration, long time, float width, float height) {
        String leftTime = DateUtil.getShortDateJustMonthAndDay(time);
        ArrayList<String> times = NewLineUtil.getTimes(duration);
        float[] textX = null;
        if (NewLineUtil.isIrregular(duration)) {
            textX = NewLineUtil.getXByDuration(duration, width);
        }
        if (times.size() == 0)
            return;
        float x1 = 0;
        float x2 = 0;
        float x3 = 0;
        float x4 = 0;
        float x5 = 0;
        float x6 = 0;
        if (times.size() == 2) {
            x2 = width;
        } else if (times.size() == 4) {
            if (textX != null) {
                x2 = textX[1] - 5;
            } else {
                x2 = width / 2f - 5;
            }
            x3 = x2;
            x4 = width;
        } else if (times.size() == 6) {
            if (textX != null) {
                x2 = textX[1] - 3;
                x4 = textX[3] - 3;
            } else {
                x2 = width / 3f - 5;
                x4 = width * 2f / 3f - 5;
            }
            x3 = x2;
            x5 = x4;
            x6 = width;
        }
        //上面偏移一点
        height = height + 25;
        Paint p = new Paint();
        p.setTextSize(FENSHI_TIME_SIZE);
        p.setColor(Color.BLACK);
        p.setTextAlign(Paint.Align.LEFT);
        //左侧日期
        canvas.drawText(leftTime, x1, height, p);
        if (times.size() == 2) {
            p.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(times.get(1), x2, height, p);
        } else if (times.size() == 4) {
            canvas.drawText("|" + times.get(2), x3, height, p);
            p.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(times.get(1), x2, height, p);
            canvas.drawText(times.get(3), x4, height, p);
        } else if (times.size() == 6) {
            canvas.drawText("|" + times.get(2), x3, height, p);
            canvas.drawText("|" + times.get(4), x5, height, p);
            p.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(times.get(1), x2, height, p);
            canvas.drawText(times.get(3), x4, height, p);
            canvas.drawText(times.get(5), x6, height, p);
        }
        p.reset();
    }

    /**
     * K线:下面的时间
     *
     * @param canvas
     * @param s1
     * @param s2
     * @param width
     * @param height
     */
    public static void drawKLineXTime(Canvas canvas, String s1, String s2, float width, float height) {
        //上面偏移一点
        height = height + 25;
        Paint p = new Paint();
        p.setTextSize(FENSHI_TIME_SIZE);
        p.setColor(Color.BLACK);
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(s1, 0, height, p);
        p.setTextAlign(Paint.Align.RIGHT);
        if (s2 != null)
            canvas.drawText(s2, width, height, p);
    }

    /**
     * 指标1：VOL
     * 画VOL线
     *
     * @param canvas  分时
     * @param xUint   x轴单位距离
     * @param y       左上y坐标
     * @param height  绘画区域高度
     * @param max     最大量
     * @param yd      昨收
     * @param minutes 量集合
     */
    public static void drawVOLRects(Canvas canvas, float xUint, float y, float height, long max, float yd, ArrayList<CMinute> minutes) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        float yUnit = height / max;
        p.setTextSize(FENSHI_TEXT_SIZE);
        p.setColor(Color.BLACK);
        for (int i = 0; i < minutes.size(); i++) {
            if (minutes.get(i).getCount() != 0) {
                if (i == 0) {
                    if (minutes.get(i).getPrice() > yd) {
                        p.setColor(NewColorUtil.COLOR_VOL_ROSE);
                    } else {
                        p.setColor(NewColorUtil.COLOR_VOL_DOWN);
                    }
                } else {
                    if (minutes.get(i).getPrice() > minutes.get(i - 1).getPrice()) {
                        p.setColor(NewColorUtil.COLOR_VOL_ROSE);
                    } else {
                        p.setColor(NewColorUtil.COLOR_VOL_DOWN);
                    }
                }
                p.setStrokeWidth(2f);
                canvas.drawLine(xUint * i, y + (height - minutes.get(i).getCount() * yUnit), xUint * i, y + height, p);
            }
        }
        //成交最大值和中间值
        p.setColor(NewColorUtil.COLOR_PRICE_LINE);
        canvas.drawText(max / 2 + "", 0, y + (height + FENSHI_TEXT_SIZE) / 2, p);
        canvas.drawText(max + "", 0, y + FENSHI_TEXT_SIZE, p);
        canvas.drawText(0 + "", 0, y + height, p);
    }

    public static void drawNewVOLRects(Canvas canvas, float xUint, float y, float height, long max, float yd, ArrayList<CMinute> minutes, float yesOffest, int yesData) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        float yUnit = height / max;
        p.setTextSize(FENSHI_TEXT_SIZE);
        p.setColor(Color.BLACK);
        for (int i = 0; i < minutes.size() - yesData; i++) {
            if (minutes.get(i).getCount() != 0) {
                if (i == 0) {
                    if (minutes.get(i).getPrice() > yd) {
                        p.setColor(NewColorUtil.COLOR_VOL_ROSE);
                    } else {
                        p.setColor(NewColorUtil.COLOR_VOL_DOWN);
                    }
                } else {
                    if (minutes.get(i).getPrice() > minutes.get(i - 1).getPrice()) {
                        p.setColor(NewColorUtil.COLOR_VOL_ROSE);
                    } else {
                        p.setColor(NewColorUtil.COLOR_VOL_DOWN);
                    }
                }
                p.setStrokeWidth(1f);
                if (i == 0) {
                    canvas.drawLine(yesOffest + xUint * i, y + (height - minutes.get(i + yesData).getCount() * yUnit), xUint * i, y + height, p);
                } else {
                    canvas.drawLine(xUint * i, y + (height - minutes.get(i + yesData).getCount() * yUnit), xUint * i, y + height, p);
                }
            }
        }
        //成交最大值和中间值
        p.setColor(NewColorUtil.COLOR_PRICE_LINE);
        canvas.drawText(max / 2 + "", 0, y + (height + FENSHI_TEXT_SIZE) / 2, p);
        canvas.drawText(max + "", 0, y + FENSHI_TEXT_SIZE, p);
        canvas.drawText(0 + "", 0, y + height, p);
    }

    /**
     * 指标1：VOL
     * 画VOL线,K线使用
     *
     * @param canvas
     * @param y      左上y坐标
     * @param height 绘画区域高度
     * @param max    最大量
     * @param datas  量集合
     */
    public static void drawVOLRects(Canvas canvas, float xUnit, float y, float height, long max, ArrayList<StickData> datas) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        float yUnit = height / max;
        p.setTextSize(FENSHI_TEXT_SIZE);
        p.setColor(Color.BLACK);
        p.setStrokeWidth(2);
        float diff = xUnit - xUnit / KLineView.WIDTH_SCALE;
        /**
         *K线成交量最大值最小值
         */
        if (max >= 10000) {
            canvas.drawText(NumberUtil.beautifulDouble(max / 20000.00, 2) + "万", 0, y + (height + FENSHI_TEXT_SIZE) / 2, p);
            canvas.drawText(NumberUtil.beautifulDouble(max / 10000.00, 2) + "万", 0, y + FENSHI_TEXT_SIZE, p);
            canvas.drawText(0 + "", 0, y + height, p);
        } else {
            canvas.drawText(max / 2 + "", 0, y + (height + FENSHI_TEXT_SIZE) / 2, p);
            canvas.drawText(max + "", 0, y + FENSHI_TEXT_SIZE, p);
            canvas.drawText(0 + "", 0, y + height, p);
        }

        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getCount() != 0) {
                StickData data = datas.get(i);
                p.setColor(data.getClose() > data.getOpen() ? NewColorUtil.COLOR_RED : NewColorUtil.COLOR_GREEN);
                p.setStyle(data.getClose() > data.getOpen() ? Paint.Style.STROKE : Paint.Style.FILL);
//                int color = NewColorUtil.getTextColorAsh(data.getClose() > data.getOpen() ? 1 : -1, 0);
//                p.setColor(color);
                canvas.drawRect(xUnit * i, y + (height - data.getCount() * yUnit), xUnit * (i + 1) - diff, y + height, p);
            }
        }
    }

    /**
     * 指标MACD中的MACD值
     *
     * @param canvas
     * @param macds
     * @param max
     * @param min
     * @param height 指标高度
     * @param y
     * @param xUint
     */
    public static void drawMACDRects(Canvas canvas, float[] macds, float max, float min, float height, float y, float xUint) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStrokeWidth(2);
        float yUnit = height / (max - min) / 2;
        float halfY = y + height / 2f;
        float diff = xUint - xUint / KLineView.WIDTH_SCALE;
        int i = 0;
        for (float f : macds) {
            LogUtils.e("***************************macds:" + f);
            p.setColor(f > 0 ? NewColorUtil.COLOR_RED : NewColorUtil.COLOR_GREEN);
//            p.setStyle(f > 0 ? Paint.Style.STROKE : Paint.Style.FILL);
            if (f != 0) {
//                canvas.drawRect(xUint * i + diff / 2, halfY, xUint * (i + 1) - diff / 2, halfY - f * yUnit - 1, p);
                canvas.drawLine(xUint * i + diff / 2, halfY, xUint * i + diff / 2, halfY - f * yUnit, p);
            }
            i++;
        }
    }


    public static void drawIndexMiddleText(Canvas canvas, String text, float y) {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.BLACK);
        p.setTextSize(FENSHI_TEXT_SIZE);
        canvas.drawText(text, 0, y, p);
    }

    public static float getY(float mainH, float input, float yMin, float yUnit) {
        return mainH - (input - yMin) / yUnit;
    }
}
