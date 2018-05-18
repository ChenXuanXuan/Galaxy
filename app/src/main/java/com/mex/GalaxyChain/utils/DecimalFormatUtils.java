package com.mex.GalaxyChain.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by LSJ on 18/3/10.
 */

public class DecimalFormatUtils  {
    private static StringBuilder getFormat(int decimal) {
        StringBuilder format = new StringBuilder("0");

        if (decimal > 0)
            format.append(".");
        for (int i = 0; i < decimal; i++) {
            format.append("0");
        }
        return format;
    }

    public static String getEstimatedPrice(double price) {
        if (price < 0.01) return "0.01";
        else return getPrice(price);
    }

    public static String getPrice(double price) {
//		DecimalFormat decimalFormat = new DecimalFormat();
//		decimalFormat.setMaximumFractionDigits(2);
//		decimalFormat.setMinimumFractionDigits(2);
//		decimalFormat.setGroupingSize(3);
//		String stringValue = decimalFormat.format(price);
//		return stringValue;

        Locale locale = Locale.getDefault();
//		Locale.setDefault(locale);
        Locale.setDefault(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingSize(3);
        return decimalFormat.format(price);
    }

    public static String getDecimalRate(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00%");
        Locale.setDefault(Locale.US);
        return decimalFormat.format(value);
    }

    public static String toString(float value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        Locale.setDefault(Locale.US);
        return decimalFormat.format(value);
    }

    public static String toString(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        Locale.setDefault(Locale.US);
        return decimalFormat.format(value);
    }

    /**
     * 去掉小数点后多余的0
     */
    public static String formatoint(double value, int decimal) {
        return format(getDecimal(value, decimal));
    }

    public static String format(String s) {
        if (s.indexOf(".") > 0) {
            //正则表达
            s = s.replaceAll("0+?$", "");//去掉后面无用的零
            s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return s;
    }

    /**
     * FLOOR
     *
     * @param value
     * @param decimal
     * @return
     */
    public static String getDecimal(double value, int decimal) {
        Locale.setDefault(Locale.US);
        StringBuilder format = getFormat(decimal);
        DecimalFormat decimalFormat = new DecimalFormat(format.toString());
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String stringValue = decimalFormat.format(value);
        return stringValue;
    }

    public static String getPriceDecimal(double value, int decimal) {
        Locale locale = Locale.getDefault();
        Locale.setDefault(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        decimalFormat.setMaximumFractionDigits(decimal);
        decimalFormat.setMinimumFractionDigits(decimal);
        decimalFormat.setGroupingSize(3);
        Locale.setDefault(locale);
        return decimalFormat.format(value);
    }


    /**
     * 去掉,转换成double
     */
    public static double valueOf(String num) {
        num = num.replace(",", "");
        double value = 0;
        try {
            value = Double.parseDouble(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
