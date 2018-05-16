package com.mex.GalaxyChain.utils;

import android.content.Context;

import com.mex.GalaxyChain.R;

import java.io.File;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 上午11:20
 */
public class VersionUtil {

	public static final String TAG = "versionUtil";

	private Context mContext;

	private boolean isDowning = false;

	private DownloadUtil mUtil;

	private DownloadUtil.OnDownloadListener mListener;

	private static VersionUtil mVersionUtil;

	public static VersionUtil getInstance(Context context) {
		return getInstance(context, null, R.drawable.ic_launcher);
	}

	public static VersionUtil getInstance(Context context, String title, int icon) {
		if (mVersionUtil == null)
			mVersionUtil = new VersionUtil(context, title, icon);
		return mVersionUtil;
	}

	/**
	 * @param context
	 * @param title   应用名+"v"+版本名称
	 * @param icon    APP icon
	 */
	private VersionUtil(final Context context, String title, int icon) {
		this.mContext = context;
		mUtil = new DownloadUtil(context, title, icon);
		mUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {
			@Override
			public void onStart() {
				isDowning = true;
				if (mListener != null)
					mListener.onStart();
			}

			@Override
			public void onProgress(int progress) {
				if (mListener != null)
					mListener.onProgress(progress);
			}

			@Override
			public void onFinish(File saveFile) {
				isDowning = false;
				if (mListener != null)
					mListener.onFinish(saveFile);
				FileUtil.openFile(context, saveFile);
			}

			@Override
			public void onFailure() {
				isDowning = false;
				if (mListener != null)
					mListener.onFailure();
			}
		});
	}

	/**
	 * 下载APK
	 *
	 * @param url
	 * @param onDownloadListener
	 * @author LSJ
	 * @time 2014-10-9上午09:47:04
	 */
	public void download(String url, DownloadUtil.OnDownloadListener onDownloadListener) {
		if (!isDowning) {
			mUtil.download(url);
			setOnDownloadListener(onDownloadListener);
		} else {
			ToastUtils.showCorrectImage("正在下载中...");
		}
	}

	public void download(String url) {
		if (!isDowning) {
			mUtil.download(url);
		} else {
			ToastUtils.showCorrectImage("正在下载中...");
		}
	}

	public void setTitle(String title) {
		if (mUtil != null)
			mUtil.setTitle(title);
	}

	public void setIcon(int icon) {
		if (mUtil != null)
			mUtil.setIcon(icon);
	}

	public void setOnDownloadListener(DownloadUtil.OnDownloadListener onDownloadListener) {
		this.mListener = onDownloadListener;
	}
}

