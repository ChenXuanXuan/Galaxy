package com.mex.GalaxyChain.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;


import com.mex.GalaxyChain.R;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenote on 2015/9/8.
 */
public class Utils {
    public static String toMD5(String source) {
        if (null == source || "".equals(source)) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(source.getBytes());
            return toHex(digest.digest(),true);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String toHex(byte[] buf, boolean isLow) {
        if (buf == null) return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            if(!isLow) {
                appendHex(result, buf[i]);
            }else{
                appendHexLow(result, buf[i]);
            }
        }
        return result.toString();
    }

    private final static String HEX = "0123456789ABCDEF";
    private final static String LOW_HEX = "0123456789abcdef";

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
    private static void appendHexLow(StringBuffer sb, byte b) {
        sb.append(LOW_HEX.charAt((b >> 4) & 0x0f)).append(LOW_HEX.charAt(b & 0x0f));
    }
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0 || "null".equals(string.toLowerCase().trim());
    }

    public static Map<String, String> convertParams(Object... params) {
        if (params == null || params.length == 0) {
            return null;
        }
        if (params.length % 2 != 0)
            throw new IllegalArgumentException(
                    "Must specify an even number of parameter names/values");

        final Map<String, String> result = new HashMap<String, String>();


        result.put(String.valueOf(params[0]),
                String.valueOf(params[1]));

        for (int i = 2; i < params.length; i += 2) {
            result.put(String.valueOf(params[i]),
                    String.valueOf(params[i + 1]));
        }
        return result;
    }

    public static File getCacheDirectory(Context context, String fileName) {
        File cacheDir = getCacheDirectory(context);
        File individualCacheDir = new File(cacheDir, fileName);
        if (!individualCacheDir.exists()) {
            if (!individualCacheDir.mkdir()) {
                individualCacheDir = cacheDir;
            }
        }
        return individualCacheDir;
    }
    public static File getCacheDirectory(Context context) {
        File appCacheDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            appCacheDir = getExternalCacheDir(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        return appCacheDir;
    }
    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appCacheDir = new File(new File(dataDir,  context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
            }
        }
        return appCacheDir;
    }
    public static void showCallComfirm(final Activity activity, final String mobile) {

        new AlertDialog.Builder(activity).setTitle("确认要拨打电话+"+mobile+"吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                call(activity,activity.getPackageManager(),mobile);
            }
        }).setNegativeButton("取消",null).show();
    }

    public static void call(Context context, PackageManager pm, String mobile) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(pm) != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, R.string.no_tel_toast, Toast.LENGTH_LONG).show();
        }
    }

    public static String toMD5low(String source) {
        if (null == source || "".equals(source)) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(source.getBytes());
            return toHex(digest.digest(),true);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }
}
