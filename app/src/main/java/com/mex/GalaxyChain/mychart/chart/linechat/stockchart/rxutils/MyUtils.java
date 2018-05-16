package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.rxutils;


public class MyUtils {
    /**
     * Prevent class instantiation.
     */
    private MyUtils() {
    }

    public static String getVolUnit(float num) {

        int e = (int) Math.floor(Math.log10(num));

        if (e >= 8) {
            return "亿手";
        } else if (e >= 4) {
            return "万手";
        } else {
            return "手";
        }


    }
}
