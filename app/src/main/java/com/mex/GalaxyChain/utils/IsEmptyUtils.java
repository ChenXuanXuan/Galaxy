package com.mex.GalaxyChain.utils;


import android.text.TextUtils;

/**
 * Author: 墨色玄清丶
 * *
 * Date: 2016-06-13 18:47
 * *
 * QQ: 363246266
 * *
 * Version: V1.0
 */
public class IsEmptyUtils {
    private static final String TAG = "IsEmptyUtils";

    public static boolean isEmpty(Object o) {
        if (null == o) {
            return true;
        }
        if (o instanceof String && TextUtils.isEmpty((String) o)) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty(Object... o) {
        for (Object object : o) {
            if (isEmpty(object))
                return true;
        }
        return false;
    }

}
