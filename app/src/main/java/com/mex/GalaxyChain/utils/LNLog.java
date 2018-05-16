package com.mex.GalaxyChain.utils;

import android.util.Log;

import com.mex.GalaxyChain.BuildConfig;


/**
 * Created by lenote on 2015/10/18.
 */
public class LNLog {
    private static final String TAG = "le_note";

    public static void d(String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.d(TAG + tag, msg);
        }
    }
    public static void e(String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.e(TAG+tag,msg);
        }
    }
    public static void i(String tag, String msg){
        if(BuildConfig.DEBUG){
            Log.i(TAG+tag,msg);
        }
    }
}
