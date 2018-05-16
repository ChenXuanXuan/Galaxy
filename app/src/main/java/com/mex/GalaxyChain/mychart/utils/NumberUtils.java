package com.mex.GalaxyChain.mychart.utils;

import java.text.DecimalFormat;

/**
 * Created by LSJ on 2016/2/25.
 */

public class NumberUtils {
    public static String numberFormatD(double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number);
    }

    public static String numberFormatF(float number) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number);
    }

    public static String numberFormat(String number) {
        try {
            double num = Double.parseDouble(number);
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(num);
        } catch (Exception e) {
            return number;
        }
    }

    public static String numberFormatInt(float number) {
        try {
            DecimalFormat df = new DecimalFormat("0");
            return df.format(number);
        } catch (Exception e) {
            return number + "";
        }
    }

    public static String numberFormat(Double number) {
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(number);
        } catch (Exception e) {
            return number + "";
        }
    }

    public static String removeSpace(String s) {
        try {
            return s.replace(" ", "");
        } catch (Exception e) {
            return s;
        }
    }

}
