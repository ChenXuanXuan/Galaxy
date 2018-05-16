package com.mex.GalaxyChain.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.view.View;
import android.view.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * name：
 * describe:应用在SD卡下文件管理
 * author: LSJ
 * time 24/4/18 上午11:22
 */
public class AppSDCardManager {
	private final String rootPath = "taojin";
	private final String imageCache = "imagecache";
	private String rootFolder = null;
	private String imageCacheFolder = null;
	private static AppSDCardManager sdCardManager = null;

	public AppSDCardManager(Context mContext) {
		// 判断SD卡是否存在
		File sdCard = null;
		if (isSDCardExist()) {
			sdCard = Environment.getExternalStorageDirectory();
		} else {
			sdCard = mContext.getCacheDir();
		}

		// 根目录
		rootFolder = sdCard + File.separator + rootPath;
		File folder = new File(rootFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}
		// 图片缓存目录
		imageCacheFolder = rootFolder + File.separator + imageCache;
		folder = new File(imageCacheFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	public static AppSDCardManager getInstance(Context context) {
		if (sdCardManager == null) {
			sdCardManager = new AppSDCardManager(context);
		}
		return sdCardManager;
	}

	public static String getDiskCacheDir(Context context) {
		File file = getDiskCacheFile(context);
		if (file != null) {
			return file.getPath();
		} else {
			return null;
		}
	}

	public static File getDiskCacheFile(Context context) {
		if (isSDCardExist() || !Environment.isExternalStorageRemovable()) {
			return context.getExternalCacheDir();
		} else {
			return context.getCacheDir();
		}
	}

	/**
	 * 判断SD卡是否存在
	 *
	 * @return ，true:SD卡存在；false：SD卡不存在
	 */
	public static boolean isSDCardExist() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 初始化root Folder
	 */
	private void initRootFolder() {
		File folder = new File(rootFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	/**
	 * 获取图像缓存文件路径
	 *
	 * @return
	 */
	public String getImageCachePath() {
		initRootFolder();
		// 缓存目录
		imageCacheFolder = rootFolder + File.separator + imageCache;
		File folder = new File(imageCacheFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}
		return imageCacheFolder;
	}

	/**
	 * 获取图像缓存文件路径
	 *
	 * @return
	 */
	public File getImageCacheFile() {
		return new File(getImageCachePath());
	}

	public String getScreenShot(Activity activity) {
		if (activity == null)
			return null;
		return getWindowShot(activity.getWindow());
	}

	public String getWindowShot(Window window) {
		if (window == null)
			return null;
		return getViewShot(window.getDecorView());
	}

	/**
	 * 截取某个View显示时对应的图片，存到sd卡，并返回路径
	 *
	 * @param view
	 * @return
	 */
	public String getViewShot(View view) {
		if (view == null)
			return null;
		view.buildDrawingCache();
		view.setDrawingCacheEnabled(true);
		Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0, 0, view.getWidth(), view.getHeight(), new Matrix(),
				true);
		view.destroyDrawingCache();

		// 考虑无效图片存储问题，每次截屏后替换本地图片，避免截屏图片过多占用内存
		// 需要分类型存储，可修改传参用文件名区别
		String path = getImageCachePath() + "share_screen_shot" + ".png";
		File file = new File(path);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			// 用png格式、100的质量进行压缩
			bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bmp.recycle();
		}
		return file.getPath();
	}

}

