package com.mex.GalaxyChain.utils;

import java.text.DecimalFormat;

public class DigitalUtil {

    public static String formatPrice(int i,double data){

        DecimalFormat formater= new DecimalFormat();
        formater.setMaximumFractionDigits(i);
        formater.setGroupingSize(3);
        return  formater.format(data);
    }




}
