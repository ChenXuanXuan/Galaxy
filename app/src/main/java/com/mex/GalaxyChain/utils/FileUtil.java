package com.mex.GalaxyChain.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 上午11:22
 */
public class FileUtil {

	public static final String TAG = "FileUtil";

	public static final int WORD = 1;

	public static final int TXT = 2;

	public static final int EXCEL = 3;

	public static final int PPT = 4;

	public static final int PDF = 5;

	public static final int APK = 6;
	/**
	 * SD卡最小空闲大小,若低于此值则认为SD卡不可用，单位MB
	 */
	private static final int SDCARD_MIN_SIZE = 50;
	/**
	 * 文件复制缓存大小
	 */
	public static final int BUFFER_SIZE = 1024;

	/**
	 * 判断SD卡是否可用
	 *
	 * @return true 挂载SD卡并且剩余空间大于SDCARD_MIN_SIZE，否则false
	 */
	public static boolean isSDCard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
				&& SDCARD_MIN_SIZE <= getSDCardSize()) {
			return true;
		} else {
			return false;
		}
	}

	public static long getSDCardSize() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File file = Environment.getExternalStorageDirectory();
			StatFs statFs = new StatFs(file.getPath());
			// 获取单个数据块的大小(Byte)
			long blockSize = statFs.getBlockSize();
			// 空闲的数据块的数量
			long freeBlocks = statFs.getAvailableBlocks();
			// 返回SD卡空闲大小
			// long SDCardSize = freeBlocks * blockSize; //单位Byte
			// long SDCardSize = (freeBlocks * blockSize)/1024; //单位KB
			long SDCardSize = (freeBlocks * blockSize) / 1024 / 1024;
			return SDCardSize; // 单位MB
		}
		return 0;
	}

	/**
	 * 打开文件
	 *
	 * @param context
	 * @param file
	 * @time 2014-10-9上午09:24:32
	 */
	public static void openFile(Context context, File file) {
		String path = file.getAbsolutePath();
		int type = FileUtil.getFileType(file);
		if (type == FileUtil.EXCEL) {
			context.startActivity(AppUtil.getExcelFileIntent(path));
		} else if (type == FileUtil.WORD) {
			context.startActivity(AppUtil.getWordFileIntent(path));
		} else if (type == FileUtil.PPT) {
			context.startActivity(AppUtil.getPptFileIntent(path));
		} else if (type == FileUtil.PDF) {
			context.startActivity(AppUtil.getPdfFileIntent(path));
		} else if (type == FileUtil.TXT) {
			context.startActivity(AppUtil.getTextFileIntent(path, false));
		} else if (type == FileUtil.APK) {
			context.startActivity(AppUtil.getApkFileIntent(path));
		} else {
			context.startActivity(AppUtil.getApkFileIntent(path));
		}
	}

	public static File getSDCard() {
		if (isSDCard()) {
			return Environment.getExternalStorageDirectory();
		}
		return null;
	}

	public static File getSDCardCache(Context context) {
		if (isSDCard()) {
			return context.getExternalCacheDir();
		}
		return null;
	}

	public static File getCacheDir(Context context) {
		return context.getCacheDir();
	}

	public static File getFileDir(Context context, String dirName) {
		return getFileDir(context, dirName, true, false);
	}

	public static File getFileDir(Context context, String dirName, boolean isDataDir) {
		return getFileDir(context, dirName, true, isDataDir);
	}

	public static File getFileDir(Context context, String dirName, boolean isSDCard, boolean isDataDir) {
		File rootDir = null;
		if (isSDCard) {
			if (isDataDir)
				rootDir = getSDCardCache(context);
			else
				rootDir = getSDCard();
			if (rootDir == null) {
				rootDir = getCacheDir(context);
			}
		} else {
			rootDir = getCacheDir(context);
		}
		if (dirName == null)
			return rootDir;
		File fileDir = new File(rootDir, dirName);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		return fileDir;
	}

	public static File getFileDir(File parentsDir, String dirName) {
		File fileDir = new File(parentsDir, dirName);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		return fileDir;
	}




	/**
	 * 文件复制
	 *
	 * @param is 复制源文件流
	 * @param os 复制目标文件流
	 */
	public static void copyFile(InputStream is, OutputStream os) {
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			while (true) {
				int count = is.read(buffer, 0, BUFFER_SIZE);
				if (count == -1)
					break;
				os.write(buffer, 0, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除文件
	 *
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file == null || !file.exists()) {
			return;
		}
		if (file.isFile()) {
			file.delete();
			return;
		}
		if (file.isDirectory()) {
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				file.delete();
				return;
			}
			for (int i = 0; i < childFiles.length; i++) {
				FileUtil.deleteFile(childFiles[i]);
			}
			file.delete();
		}
	}

	/**
	 * 获取文件类型,支持word,txt,excel,ppt,pdf,apk等类型
	 *
	 * @param file
	 * @return
	 * @author LSJ
	 * @create 2017-9-15 下午2:40:21
	 */
	public static int getFileType(File file) {
		if (file.isDirectory()) {
			return -1;
		}
		String fileName = file.getName();
		int index = fileName.lastIndexOf(".");
		if (index == -1) {
			return -1;
		}
		String ex = fileName.substring(index);
		if (ex.equalsIgnoreCase(".xls") || ex.equalsIgnoreCase(".xlsx")) {
			return EXCEL;
		} else if (ex.equalsIgnoreCase(".doc") || ex.equalsIgnoreCase(".docx")) {
			return WORD;
		} else if (ex.equalsIgnoreCase(".ppt") || ex.equalsIgnoreCase(".pptx")) {
			return PPT;
		} else if (ex.equalsIgnoreCase(".pdf")) {
			return PDF;
		} else if (ex.equalsIgnoreCase(".txt")) {
			return TXT;
		} else if (ex.contains(".apk") || ex.contains(".APK")) {
			return APK;
		}
		return -1;
	}

}

