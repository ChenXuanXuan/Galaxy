package com.mex.GalaxyChain.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.common.view.CenterToast;

/**
 * name：
 * describe:
 * author: LSJ
 * time 19/3/18 上午11:58
 */
public class ToastUtils {
    public static void showInMiddle( String message) {

        Toast toast = Toast.makeText(MyApplication.appContext, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showInMiddle( int resId) {
        Toast toast = Toast.makeText(MyApplication.appContext, resId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showSingleToastInMiddle( int resId, int imageType) {
        showSingleToastInMiddle(MyApplication.appContext, MyApplication.appContext.getResources().getText(resId).toString(), imageType);
    }

    public static void showSingleToastInMiddle(Context appContext, String content, int imageType) {
        CenterToast.showToast(MyApplication.appContext, content, Toast.LENGTH_SHORT, imageType);
    }

    public static void showSingleToastLongInMiddle(Context context, String content, int imageType) {
        CenterToast.showToast(context, content, Toast.LENGTH_LONG, imageType);
    }

    public static void showTextInMiddle( String content) {
        showSingleToastInMiddle(MyApplication.appContext, content, CenterToast.TYPE_HIDE);
    }

    public static void showTextInMiddle( int resId) {
        showSingleToastInMiddle(MyApplication.appContext, MyApplication.appContext.getResources().getText(resId).toString(), CenterToast.TYPE_HIDE);
    }


    public static void showErrorImage( String content) {
        showSingleToastInMiddle(MyApplication.appContext, content, CenterToast.TYPE_ERROR);
    }

    public static void showLongErrorImage( String content) {
        showSingleToastLongInMiddle(MyApplication.appContext, content, CenterToast.TYPE_ERROR);
    }

    public static void showErrorImage(int resId) {
        showSingleToastInMiddle(MyApplication.appContext, MyApplication.appContext.getResources().getText(resId).toString(), CenterToast.TYPE_ERROR);
    }

    public static void showCorrectImage(String content) {
        showSingleToastInMiddle(MyApplication.appContext, content, CenterToast.TYPE_CORRECT);
    }

    public static void showCorrectImage( int resId) {
        showSingleToastInMiddle(MyApplication.appContext, MyApplication.appContext.getResources().getText(resId).toString(), CenterToast.TYPE_CORRECT);
    }
}
