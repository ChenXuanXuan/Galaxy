package com.mex.GalaxyChain.mychart.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * @author LSJ
 * @Description
 * @date 2017/9/7 下午5:43
 */
public class ConfigCacheUtils {
    private static final String TAG = ConfigCacheUtils.class.getName();
    //App缓存文件根目录
    private static final String ROOT = "/Xiaoting/cache/";

    public static final int CONFIG_CACHE_SHORT_TIMEOUT = 1000 * 60 * 5; // 5分钟
    public static final int CONFIG_CACHE_MEDIUM_TIMEOUT = 1000 * 60 * 60 * 1; // 1小时
    public static final int CONFIG_CACHE_ML_TIMEOUT = 1000 * 60 * 60 * 24 * 1; // 1天
    public static final int CONFIG_CACHE_MAX_TIMEOUT = 1000 * 60 * 60 * 24 * 7; // 7天

    /**
     * CONFIG_CACHE_MODEL_LONG : 长时间(7天)缓存模式
     * CONFIG_CACHE_MODEL_ML : 中长时间(1天)缓存模式
     * CONFIG_CACHE_MODEL_MEDIUM: 中等时间(1小时)缓存模式
     * CONFIG_CACHE_MODEL_SHORT : 短时间(5分钟)缓存模式
     */
    public enum ConfigCacheModel {
        CONFIG_CACHE_MODEL_SHORT, CONFIG_CACHE_MODEL_MEDIUM, CONFIG_CACHE_MODEL_ML, CONFIG_CACHE_MODEL_LONG;
    }

    /**
     * 获得缓存文件夹路径
     *
     * @return 缓存文件夹路径
     */
    public static String getCachePath(String path) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //获取SD卡的目录
            File sdDirFile = Environment.getExternalStorageDirectory();
            String cachePath = null;
            try {
                cachePath = sdDirFile.getCanonicalPath() + path;
                FileUtils.CreateDir(cachePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return cachePath;
        }
        return null;
    }

    /**
     * 获取缓存
     *
     * @param subPath  缓存文件子目录路径，比如缓存文件在"/Xiaoting/cache/news/"下，此处sebPath="news/"
     * @param fileName 缓存文件名，如果有后缀，需要加上后缀
     * @param model    缓存模式
     * @param context  上下文环境
     * @return 缓存数据
     */
    public static String getCache(String subPath, String fileName, ConfigCacheModel model, Context context) {
        if (fileName == null)
            return null;

        String result = null;//存储缓存数据的字符串
        boolean deleteCache = false;//标志缓存文件是否过期需要删除

        //获取缓存文件
        String path = getCachePath(ROOT + subPath) + fileName;
        File file = new File(path);

        if (file.exists() && file.isFile()) {
            //缓存文件存储的时间
            long expiredTime = System.currentTimeMillis() - file.lastModified();
//          Log.i(TAG, file.getAbsolutePath() + " expiredTime:" + expiredTime / 60000 + "min");

            //当网络是无效的,只能读缓存；网络有效时，要判断缓存是否过期
            if (NetworkUtils.isAvailable(context) != false) {
                Log.i(TAG, "Network is abailable!");
                if (expiredTime < 0)//系统时间不正确
                    return null;
                if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_SHORT) {
                    if (expiredTime > CONFIG_CACHE_SHORT_TIMEOUT)
                        deleteCache = true;
                } else if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_MEDIUM) {
                    if (expiredTime > CONFIG_CACHE_MEDIUM_TIMEOUT)
                        deleteCache = true;
                } else if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_ML) {
                    if (expiredTime > CONFIG_CACHE_ML_TIMEOUT)
                        deleteCache = true;
                } else if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_LONG) {
                    if (expiredTime > CONFIG_CACHE_MAX_TIMEOUT)
                        deleteCache = true;
                } else {
                    if (expiredTime > CONFIG_CACHE_MAX_TIMEOUT)
                        deleteCache = true;
                }

                if (deleteCache == true) {//如果文件已过期，删除缓存
                    clearCache(file);
                    return null;
                }
            }
            try {//读取缓存
                result = FileUtils.readTextFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 写缓存文件
     *
     * @param subPath  缓存文件子目录路径，比如缓存文件在"/Xiaoting/cache/news/"下，此处sebPath="news/"
     * @param data     要写入缓存的字符串
     * @param fileName 缓存文件名，如果有后缀，需要加上后缀
     */
    public static void setCache(String subPath, String data, String fileName) {
        String path = getCachePath(ROOT + subPath) + fileName;
        File file = new File(path);
        try {
            if (!file.exists())//如果文件不存在，就先创建它
                file.createNewFile();
            // 写缓存数据（覆盖原文件地）
            FileUtils.writeTextFile(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除历史缓存文件
     *
     * @param cacheFile 要删除的文件获目录
     */
    public static void clearCache(File cacheFile) {
        if (cacheFile == null) {//如果cacheFile为空，就删除缓存文件根目录下的所有文件
            File cacheDir = new File(getCachePath(ROOT));
            if (cacheDir.exists())
                clearCache(cacheDir);
        } else if (cacheFile.isFile()) {//如果cacheFile是文件，就删除它
            cacheFile.delete();
        } else if (cacheFile.isDirectory()) {//如果cacheFile是目录，就删除目录下的所有文件
            File[] childFiles = cacheFile.listFiles();
            for (int i = 0; i < childFiles.length; i++)
                clearCache(childFiles[i]);
        }
    }
}
