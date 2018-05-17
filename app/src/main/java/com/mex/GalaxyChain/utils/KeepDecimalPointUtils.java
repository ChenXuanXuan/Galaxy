package com.mex.GalaxyChain.utils;

import com.mex.GalaxyChain.MyApplication;

import java.text.NumberFormat;

public class KeepDecimalPointUtils {



    /*  digit  保留的小数位数
    *
    *
    * */
    public static  NumberFormat  keepPoint(int digit){
        NumberFormat mNumberFormat = MyApplication.getInstance().mNumberFormat;
        mNumberFormat.setMaximumFractionDigits(digit);
        return   mNumberFormat;
    }

}
