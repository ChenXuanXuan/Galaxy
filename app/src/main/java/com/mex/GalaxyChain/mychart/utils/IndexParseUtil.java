package com.mex.GalaxyChain.mychart.utils;


import com.mex.GalaxyChain.mychart.bean.StickData;

import java.util.List;


/**
 * Created by Arvin on 2016/9/14.
 * 计算各种指标
 */
public class IndexParseUtil {

    //均线跨度(SMA5,SMA10,SMA20),注意修改该值时，需要同时增加StickData里面的sma字段、修改本类initSma方法，否则不会生效
    public static final int START_SMA5 = 5;
    public static final int START_SMA10 = 10;
    public static final int START_SMA20 = 20;
    //26:计算MACD时，26段close均价DIF=(EMA(CLOSE,12) - EMA(CLOSE,26))
    public static final int START_DIF = 26;
    //35：计算MACD时，35段开始取前九日DIF值 DEA:=EMA(DIF,9)
    public static final int START_DEA = 35;
    //12:计算K值
    public static final int START_K = 12;
    //15:计算DJ
    public static final int START_DJ = 15;

    //12:计算K值
    public static final int START_UP = 200;
    //15:计算DJ
    public static final int START_MB = 205;


    //9:计算RSV
    public static final int START_REV = 9;

    public static final int[] SMA = {START_SMA5,START_SMA10, START_SMA20};

    /**
     * 计算MACD
     * @param list
     */
    public static void initMACD(List<StickData> list) {
        double ema12 = 0;
        double ema26 = 0;
        double dif = 0;
        double dea = 0;
        double macd = 0;

        for (int i = 0; i < list.size(); i++) {
            StickData point = list.get(i);
            final double closePrice = point.getClose();
            if (i == 0) {
                ema12 = closePrice;
                ema26 = closePrice;
            } else {
//                EMA（12） = 前一日EMA（12） X 11/13 + 今日收盘价 X 2/13
//                EMA（26） = 前一日EMA（26） X 25/27 + 今日收盘价 X 2/27
                ema12 = ema12 * 11f / 13f + closePrice * 2f / 13f;
                ema26 = ema26 * 25f / 27f + closePrice * 2f / 27f;
            }
//            DIF = EMA（12） - EMA（26） 。
//            今日DEA = （前一日DEA X 8/10 + 今日DIF X 2/10）
//            用（DIF-DEA）*2即为MACD柱状图。
            dif = ema12 - ema26;
            dea = dea * 8f / 10f + dif * 2f / 10f;
            macd = (dif - dea) * 2f;
            point.setDif(dif);
            point.setDea(dea);
            point.setMacd(macd);
        }

//        if(list == null) return;
//        //1计算出所有的DIF
//        for(int i = 0; i < list.size(); i++) {
//            if(i + START_DIF <= list.size()) {
//                list.get(i + START_DIF - 1).setDif(getCloseSma(list.subList(i + START_DIF - 12, i + START_DIF)) - getCloseSma(list.subList(i + START_DIF - 26, i + START_DIF)));
//            }
//        }
//        //2计算出所有的DEA
//        for(int i = 0; i < list.size(); i++) {
//            if(i + START_DEA <= list.size()) {
//                list.get(i + START_DEA - 1).setDea(getDifSma(list.subList(i + START_DEA - 9, i + START_DEA)));
//                //3计算MACD
//                list.get(i + START_DEA - 1).setMacd(2d * (list.get(i + START_DEA - 1).getDif() - list.get(i + START_DEA - 1).getDea()));
//            }
//        }

    }

    /**
     * 计算KDJ
     * @param list
     */
    public static void initKDJ(List<StickData> list) {
        if(list == null) return;
//        //1计算出所有的REV
//        for(int i = 0; i < list.size(); i++) {
//            if(i + START_REV <= list.size()) {
//                //第9日开始计算RSV
//                StickData data = list.get(i + START_REV - 1);
//                double[] maxAndMin = getMaxAndMin(list.subList(i, i + START_REV));
//                list.get(i + START_REV - 1).setRsv((data.getClose() - maxAndMin[1]) / (maxAndMin[0] - maxAndMin[1]) * 100);
//            }
//        }
//        //2计算出所有K
//        for(int i = 0; i < list.size(); i++) {
//            if(i + START_K <= list.size()) {
//                list.get(i + START_K - 1).setK(getRSVSma(list.subList(i + START_K - 3, i + START_K)));
//            }
//        }
//        //3计算出所有的DJ
//        for(int i = 0; i < list.size(); i++) {
//            if(i + START_DJ <= list.size()) {
//                StickData data = list.get(i + START_DJ - 1);
//                list.get(i + START_DJ - 1).setD(getKSma(list.subList(i + START_DJ - 3, i + START_DJ)));
//                list.get(i + START_DJ - 1).setJ(3 * data.getK() - 2 * data.getD());
//            }
//        }

        double k = 0;
        double d = 0;

        for (int i = 0; i < list.size(); i++) {
            StickData point = list.get(i);
            final double closePrice = point.getClose();
            int startIndex = i - 8;
            if (startIndex < 0) {
                startIndex = 0;
            }
            double max9 = Float.MIN_VALUE;
            double min9 = Float.MAX_VALUE;
            for (int index = startIndex; index <= i; index++) {
                max9 = Math.max(max9, list.get(index).getHigh());
                min9 = Math.min(min9, list.get(index).getLow());

            }
            double rsv = 100f * (closePrice - min9) / (max9 - min9);
            if (i == 0) {
                k = rsv;
                d = rsv;
            } else {
                k = (rsv + 2f * k) / 3f;
                d = (k + 2f * d) / 3f;
            }
            point.setK(k);
            point.setD(d);
            point.setJ(3f * k - 2 * d);
        }

    }


    /**
     * 计算BOLL
     * @param list
     */
    public static void initBOLL(List<StickData> list) {
        for (int i = 0; i < list.size(); i++) {
            StickData point = list.get(i);
            final double closePrice = point.getClose();
            if (i == 0) {
                point.mb = closePrice;
                point.up = Float.NaN;
                point.dn = Float.NaN;
            } else {
                int n = 20;
                if (i < 20) {
                    n = i + 1;
                }
                float md = 0;
                for (int j = i - n + 1; j <= i; j++) {
                    double c = list.get(j).getClose();
                    double m = point.getSma20();
                    double value = c - m;
                    md += value * value;
                }
                md = md / (n - 1);
                md = (float) Math.sqrt(md);
//                point.mb = point.getSma20();
//                point.up = point.mb + 2f * md;
//                point.dn = point.mb - 2f * md;
                point.setMb(point.getSma20());
                point.setUp(point.mb + 2f * md);
                point.setDn(point.mb - 2f * md);
            }
        }

    }

    /**
     * 把list里面所有数据对应的均线计算出来并且赋值到里面
     *
     * @param list k线数据
     */
    public static void initSma(List<StickData> list) {
        if (list == null) return;
        for (int i = 0; i < list.size(); i++) {
            for (int j : SMA) {
                if (i + j <= list.size()) {
                    //第5日开始计算5日均线
                    if (j == START_SMA5) {
                        //量的SMA5
                        list.get(i + j - 1).setCountSma5(getCountSma(list.subList(i, i + j)));
                        //K线的SMA5
                        list.get(i + j - 1).setSma5(getCloseSma(list.subList(i, i + j)));
                    } else
                        //第10日开始计算10日均线
                        if (j == START_SMA10) {
                            //量的SMA10
                            list.get(i + j - 1).setCountSma10(getCountSma(list.subList(i, i + j)));
                            //K线的SMA10
                            list.get(i + j - 1).setSma10(getCloseSma(list.subList(i, i + j)));
                        }else
                            //第20日开始计算20日均线
                            if (j == START_SMA20) {
                                //量的SMA20
                                list.get(i + j - 1).setCountSma20(getCountSma(list.subList(i, i + j)));
                                //K线的SMA20
                                list.get(i + j - 1).setSma20(getCloseSma(list.subList(i, i + j)));
                            }
                }
            }
        }
    }

    /**
     * 计算KDJ时，取9日最高最低值
     * @param datas
     * @return
     */
    private static double[] getMaxAndMin(List<StickData> datas) {
        if(datas == null || datas.size() == 0)
            return new double[]{0, 0};
        double max = datas.get(0).getHigh();
        double min = datas.get(0).getLow();
        for(StickData data : datas) {
            max = max > data.getHigh() ? max : data.getHigh();
            min = min < data.getLow() ? min : data.getLow();
        }
        return new double[]{max, min};
    }


    /**
     * K线量计算移动平均值
     * @param datas
     * @return
     */
    private static double getCountSma(List<StickData> datas) {
        if (datas == null) return -1;
        double sum = 0;
        for (StickData data : datas) {
            sum += data.getCount();
        }
        return NumberUtil.doubleDecimal(sum / datas.size());
    }

    /**
     * K线收盘价计算移动平均价
     * @param datas
     * @return
     */
    private static double getCloseSma(List<StickData> datas) {
        if (datas == null) return -1;
        double sum = 0;
        for (StickData data : datas) {
            sum += data.getClose();
        }
        return NumberUtil.doubleDecimal(sum / datas.size());
    }

    /**
     * K线dif的移动平均值
     * @param datas
     * @return
     */
    private static double getDifSma(List<StickData> datas) {
        if (datas == null) return -1;
        double sum = 0;
        for (StickData data : datas) {
            sum += data.getDif();
        }
        return NumberUtil.doubleDecimal(sum / datas.size());
    }

    /**
     * 三日rsv移动平均值，即K值
     * @param datas
     * @return
     */
    private static double getRSVSma(List<StickData> datas) {
        if (datas == null) return -1;
        double sum = 0;
        for (StickData data : datas) {
            sum += data.getRsv();
        }
        return NumberUtil.doubleDecimal(sum / datas.size());
    }

    /**
     * 三日K移动平均值，即D值
     * @param datas
     * @return
     */
    private static double getKSma(List<StickData> datas) {
        if (datas == null) return -1;
        double sum = 0;
        for (StickData data : datas) {
            sum += data.getK();
        }
        return NumberUtil.doubleDecimal(sum / datas.size());
    }

}
