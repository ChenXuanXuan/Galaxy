package com.mex.GalaxyChain.utils;

import com.google.gson.Gson;

/**
 * Created by lsj-pc on 2018/5/10.
 */

public class GsonUtils {
    public static Object getObject(Class<?> obj, String json) {
        try {
            Gson gson = new Gson();
            Object object = gson.fromJson(json, obj);
            return object;
        } catch (Exception e) {
            LogUtils.e(e.toString());
            return null;
        }
    }
}
