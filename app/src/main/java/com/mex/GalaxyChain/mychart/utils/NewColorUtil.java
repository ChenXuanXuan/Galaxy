package com.mex.GalaxyChain.mychart.utils;

import android.graphics.Color;

import com.mex.GalaxyChain.mychart.bean.CMinute;
import com.mex.GalaxyChain.mychart.bean.StickData;
import com.mex.GalaxyChain.mychart.chart.kchart.chart.KLineEntity;


/**
 * Created by LSJ on 2016/8/25.
 */
public class NewColorUtil {
    //资金超大单线颜色
    public static final int COLOR_ZJ_SUPER = Color.parseColor("#FF9966");
    //资金大单线颜色
    public static final int COLOR_ZJ_BIG = Color.parseColor("#FF0000");
    //资金中单线颜色
    public static final int COLOR_ZJ_MIDDLE = Color.parseColor("#999900");
    //资金超小线颜色
    public static final int COLOR_ZJ_SMALL = Color.parseColor("#0099FF");
    //跌颜色
//    public static final int COLOR_GREEN = Color.parseColor("#1aaa0f");
    public static final int COLOR_GREEN = Color.parseColor("#0e730b");
    //涨颜色
//    public static final int COLOR_RED = Color.parseColor("#ff4c52");
    public static final int COLOR_RED = Color.parseColor("#e14f5c");
    //平灰
    public static final int COLOR_PING_ASH = Color.parseColor("#333333");
    //平白
    public static final int COLOR_PING_WHITE = Color.parseColor("#efefef");
    //5均线颜色
    public static final int COLOR_SMA5 = Color.parseColor("#ffbb40");
    //10均线颜色
    public static final int COLOR_SMA10 = Color.parseColor("#46d1ff");
    //20均线颜色
    public static final int COLOR_SMA20 = Color.parseColor("#fb45ff");
    //MACD指标DIF颜色
    public static final int COLOR_DIF = Color.parseColor("#38b0d3");
    //MACD指标DEA颜色
    public static final int COLOR_DEA = Color.parseColor("#d8a161");
    //MACD指标MACD字体颜色
    public static final int COLOR_MACD = Color.parseColor("#e27bda");
    //KDJ指标K颜色
    public static final int COLOR_KDJ_K = COLOR_DIF;
    //KDJ指标D颜色
    public static final int COLOR_KDJ_D = COLOR_DEA;
    //KDJ指标J颜色
    public static final int COLOR_KDJ_J = Color.parseColor("#a930bd");

    //分时线价格颜色
    public static final int COLOR_PRICE_LINE = Color.parseColor("#464647");
    //分时线均线颜色
    public static final int COLOR_SMA_LINE = Color.parseColor("#e38920");
    public static final int COLOR_CROSS_LINE = Color.parseColor("#555555");
    //虚线颜色
    public static final int COLOR_EFFECT_LINE = Color.parseColor("#eae6e6");
    //分时虚线颜色
    public static final int COLOR_EFFECT_FENSHI_LINE = Color.parseColor("#e3dfdf");
    //分时成交量涨
    public static final int COLOR_VOL_ROSE = Color.parseColor("#d77987");
    //分时成交量跌
    public static final int COLOR_VOL_DOWN = Color.parseColor("#8dc389");
    //价格框颜色
    public static final int COLOR_PRPICE_STROKE = Color.parseColor("#666666");

    public static String getColorRGB(double curr, double change) {
        if (curr > change) return "#e36d50";
        if (curr < change) return "#3b7f19";
        return "#333333";
    }

    /**
     * 获取价格显示的颜色，curr>change是红，等于是黑，小于是绿
     * 平是灰色
     *
     * @param curr   当前价
     * @param change 变化颜色的价格
     * @return
     */
    public static int getTextColorAsh(double curr, double change) {
        if (curr == change)
            return COLOR_PING_ASH;
        if (curr < change)
            return COLOR_GREEN;
        return COLOR_RED;
//        return COLOR_PING_WHITE;
    }

    /**
     * 获取价格显示的颜色，curr>change是红，等于是黑，小于是绿
     * 平是白色
     *
     * @param curr   当前价
     * @param change 变化颜色的价格
     * @return
     */
    public static int getTextColorWhite(double curr, double change) {
        if (curr == change)
            return COLOR_PING_WHITE;
        if (curr < change)
            return COLOR_GREEN;
        return COLOR_RED;
    }

    /**
     * 价格信息
     * 十字线滑动时，显示部分信息
     *
     * @param entity
     * @return
     */

    public static String getTime(CMinute entity, double yj) {
        StringBuffer sb = new StringBuffer();
        sb.append(entity.getTimeStr());
        return sb.toString();
    }

    public static String getPrice(CMinute entity, double yj) {
        double percent = (entity.getPrice() - yj) / yj * 100;
        StringBuffer sb = new StringBuffer();
        if (entity.getPrice() >= yj) {
            sb.append("价格:" + "<b><font color='#ef2b2b' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getPrice()) + "(+" + NumberUtil.beautifulDouble(percent, 2) + "%)" + "</font>");
        } else {
            sb.append("价格:" + "<b><font color='#3b7f19' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getPrice()) + "(" + NumberUtil.beautifulDouble(percent, 2) + "%)" + "</font>");
        }
        return sb.toString();
    }

    public static String getRose(CMinute entity, double yj) {
        double percent = (entity.getPrice() - yj) / yj * 100;
        StringBuffer sb = new StringBuffer();
        if (percent >= 0) {
            sb.append("涨幅:" + "<b><font color='#ef2b2b' textStyle='bold'>" + "+" + NumberUtil.beautifulDouble(percent * 100, 2) + "%" + "</font>");
        } else {
            sb.append("涨幅:" + "<b><font color='#3b7f19' textStyle='bold'>" + NumberUtil.beautifulDouble(percent * 100, 2) + "%" + "</font>");
        }
        return sb.toString();
    }

    public static String getAveragePrice(CMinute entity, double yj) {
        StringBuffer sb = new StringBuffer();
        if (entity.getAverage() >= yj) {
            sb.append("均价:" + "<b><font color='#ef2b2b' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getAverage()) + "</font>");
        } else {
            sb.append("均价:" + "<b><font color='#3b7f19' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getAverage()) + "</font>");
        }
        return sb.toString();
    }

    public static String getVol(CMinute entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("成交量:" + "<b><font color='#333333' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getCount(), 0) + "</font>");
        return sb.toString();
    }


    public static String getKai(StickData entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getOpen() >= entity.getLast()) {
            sb.append("开：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getOpen()) + "</font>");
        } else {
            sb.append("开：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getOpen()) + "</font>");
        }
        return sb.toString();
    }

    public static String getGao(StickData entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getHigh() >= entity.getLast()) {
            sb.append("高：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getHigh()) + "</font>");
        } else {
            sb.append("高：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getHigh()) + "</font>");
        }
        return sb.toString();
    }

    public static String getFu(StickData entity) {
        StringBuffer sb = new StringBuffer();
        LogUtils.e("*****************************幅：" + entity.getRate());
        if (entity.getRate() >= 0) {
            sb.append("幅：" + "<b><font color='#f30300' textStyle='bold'>" + "+" + NumberUtil.beautifulDouble(entity.getRate() * 100, 2) + "%" + "</font>");
        } else {
            sb.append("幅：" + "<b><font color='#3b7f19' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getRate() * 100, 2) + "%" + "</font>");
        }
        return sb.toString();
    }

    public static String getChengjiao(StickData entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getCount() >= 10000) {
            sb.append("成交：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(((float) entity.getCount() / 10000), 2) + "万" + "</font>");
        } else {
            sb.append("成交：" + "<b><font color='#049401' textStyle='bold'>" + entity.getCount() + "</font>");
        }
        return sb.toString();
    }

    public static String getShou(StickData entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getClose() >= entity.getLast()) {
            sb.append("收：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getClose()) + "</font>");
        } else {
            sb.append("收：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getClose()) + "</font>");
        }
        return sb.toString();
    }

    public static String getDi(StickData entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getLow() >= entity.getLast()) {
            sb.append("低：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getLow()) + "</font>");
        } else {
            sb.append("低：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getLow()) + "</font>");
        }
        return sb.toString();
    }

    public static String getE(StickData entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getRateF() > 0) {
            sb.append("额：" + "<b><font color='#f30300' textStyle='bold'>" + "+" + NumberUtil.beautifulDouble(entity.getRateF()) + "</font>");
        } else {
            sb.append("额：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getRateF()) + "</font>");
        }
        return sb.toString();
    }

    public static String getJunjia(StickData entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getAverage() >= entity.getClose()) {
            sb.append("均价：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getAverage()) + "</font>");
        } else {
            sb.append("均价：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getAverage()) + "</font>");
        }
        return sb.toString();
    }

    public static String getSMA5(StickData entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getSma5() > 0) {
            sb.append("MA5：" + "<font color='#e3a92e'>" + NumberUtil.beautifulDouble(entity.getSma5()) + "</font>");
        } else {
            sb.append("MA5：" + "<font color='#e3a92e'>" + "--" + "</font>");
        }
        return sb.toString();
    }

    public static String getSMA10(StickData entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getSma10() > 0) {
            sb.append("MA10：" + "<font color='#20a7e7'>" + NumberUtil.beautifulDouble(entity.getSma10()) + "</font>");
        } else {
            sb.append("MA10：" + "<font color='#20a7e7'>" + "--" + "</font>");
        }
        return sb.toString();
    }

    public static String getSMA20(StickData entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getSma20() > 0) {
            sb.append("MA20：" + "<font color='#a626bb'>" + NumberUtil.beautifulDouble(entity.getSma20()) + "</font>");
        } else {
            sb.append("MA20：" + "<font color='#a626bb'>" + "--" + "</font>");
        }
        return sb.toString();
    }

    public static String getNewKai(KLineEntity entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getOpenPrice() >= entity.getLowPrice()) {
            sb.append("开：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getOpenPrice()) + "</font>");
        } else {
            sb.append("开：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getOpenPrice()) + "</font>");
        }
        return sb.toString();
    }

    public static String getNewGao(KLineEntity entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getHighPrice() >= entity.getLowPrice()) {
            sb.append("高：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getHighPrice()) + "</font>");
        } else {
            sb.append("高：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getHighPrice()) + "</font>");
        }
        return sb.toString();
    }

    public static String getNewFu(KLineEntity entity) {
        StringBuffer sb = new StringBuffer();
//        if (entity.getRose() > 0) {
//            sb.append("幅：" + "<b><font color='#f30300' textStyle='bold'>" + "+" + NumberUtil.beautifulDouble(entity.getRose() * 100, 2) + "%" + "</font>");
//        } else {
//            sb.append("幅：" + "<b><font color='#3b7f19' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getRose() * 100, 2) + "%" + "</font>");
//        }
        return sb.toString();
    }

    public static String getNewChengjiao(KLineEntity entity) {
        StringBuffer sb = new StringBuffer();
//        if (entity.getVol() >= 10000) {
//            sb.append("成交：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(((float) entity.getVol() / 10000), 2) + "万" + "</font>");
//        } else {
//            sb.append("成交：" + "<b><font color='#049401' textStyle='bold'>" + entity.getVol() + "</font>");
//        }
        return sb.toString();
    }

    public static String getNewShou(KLineEntity entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getClosePrice() >= entity.getLowPrice()) {
            sb.append("收：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getClosePrice()) + "</font>");
        } else {
            sb.append("收：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getClosePrice()) + "</font>");
        }
        return sb.toString();
    }

    public static String getNewDi(KLineEntity entity) {
        StringBuffer sb = new StringBuffer();
        if (entity.getLowPrice() >= entity.getLowPrice()) {
            sb.append("低：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getLowPrice()) + "</font>");
        } else {
            sb.append("低：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getLowPrice()) + "</font>");
        }
        return sb.toString();
    }

    public static String getNewE(KLineEntity entity) {
        StringBuffer sb = new StringBuffer();
//        if (entity.getRoseF() > 0) {
//            sb.append("额：" + "<b><font color='#f30300' textStyle='bold'>" + "+" + NumberUtil.beautifulDouble(entity.getRoseF()) + "</font>");
//        } else {
//            sb.append("额：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getRoseF()) + "</font>");
//        }
        return sb.toString();
    }

    public static String getNewJunjia(KLineEntity entity) {
        StringBuffer sb = new StringBuffer();
//        if (entity.getAverage() >= entity.getClosePrice()) {
//            sb.append("均价：" + "<b><font color='#f30300' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getAverage()) + "</font>");
//        } else {
//            sb.append("均价：" + "<b><font color='#049401' textStyle='bold'>" + NumberUtil.beautifulDouble(entity.getAverage()) + "</font>");
//        }
        return sb.toString();
    }
}
