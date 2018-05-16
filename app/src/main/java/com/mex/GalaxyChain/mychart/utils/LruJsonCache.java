package com.mex.GalaxyChain.mychart.utils;

import android.support.v4.util.LruCache;
import android.text.TextUtils;

/**
 * @author LSJ
 * @Description
 * @date 2017/9/7 下午5:37
 */
public class LruJsonCache {
    private static LruCache<String, String> mMemoryCache;

    public LruJsonCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory() / 10;
        mMemoryCache = new LruCache<String, String >(maxMemory) {
        @Override
        protected int sizeOf (String key, String value){
            return value.length();
        }
        };
    }

    /**
     * @return void
     * @Title: addJsonToMemoryCache
     * @Description: TODO 添加json内存
     */
    public static void addJsonToMemoryCache(String key, String jsonString) {
        if (mMemoryCache == null) {
            return;
        }
        if (TextUtils.isEmpty(key)) {
            return;
        }

        if (getJsonFromMemCache(key) == null && jsonString != null) {
            mMemoryCache.put(key, jsonString);
        }
    }

    /**
     * 从内存缓存中获取一个Json
     *
     * @param key
     * @return
     */
    public static String getJsonFromMemCache(String key) {
        if (mMemoryCache == null) {
            return null;
        }
        return mMemoryCache.get(key);
    }
}