package com.mex.GalaxyChain.utils;

import android.text.Html;
import android.text.Spanned;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by lenote on 2015/10/18.
 */
public class StringUtils {

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0 || "null".equals(string.toLowerCase().trim());
    }
    public static String emptyToNull(String string) {
        return isNullOrEmpty(string) ? null : string;
    }

    public static boolean isNetAddress(String imgUrl) {
        if(!isNullOrEmpty(imgUrl)&&(imgUrl.startsWith("http://")||imgUrl.startsWith("https://"))){
            return true;
        }
        return false;
    }

    public static Spanned getLinkText(String info) {
        return  Html.fromHtml("<u>" + info + "</u>");
    }
    /**
     * 最多保留几位小数，就用几个#，最少位就用0来确定
     * @param d
     * @return
     */
    public static String floatTrans(float d) {
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(d);
    }
    public static String float2BitTrans(float d) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(d);
    }

    public static String getString(List<String> list, String split) {
        if(list!=null&&list.size()>0&&split!=null){
            String joins="";
            for(String info:list){
                joins+=info+split;
            }
            return joins.substring(0,joins.length()-split.length());
        }
        return "";
    }
}
