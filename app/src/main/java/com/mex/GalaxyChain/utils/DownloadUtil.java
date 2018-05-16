package com.mex.GalaxyChain.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.mex.GalaxyChain.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 上午11:21
 */
public class DownloadUtil {

	private static final String TAG = "DownloadUtil";

	private final int FILE_DOWNLOAD_START = 0;

	private final int FILE_DOWNLOAD = 1;

	private final int FILE_DOWNLOAD_FINISH = 2;

	private final int FILE_DOWNLOAD_ERROR = 3;

	private Context mContext;

	private HttpURLConnection conn;

	/**
	 * 下载地址
	 */
	private URL downUrl;
	/**
	 * 下载文件保存地址
	 */
	private File saveFile;
	/**
	 * 下载文件临时地址
	 */
	private File infoFile;
	/**
	 * 已下载完成大小
	 */
	private long totalFinish;
	/**
	 * 文件大小
	 */
	private long fileSize;
	/**
	 * 下载文件保存目录
	 */
	private File saveDir;
	/**
	 * 是否在通知栏显示加载进度
	 */
	private boolean isNotification = true;

	private Notification downLoadNotification;
	private NotificationManager downLoadNotificationManager;
	private PendingIntent downLoadPendingIntent;
	private RemoteViews contentView;
	/**
	 * 文件 名称
	 */
	private String title;
	/**
	 * APP Logo
	 */
	private int icon;
	/**
	 * 下载进度刷新大小
	 */
	private int flag = 1024 * 100;

	private OnDownloadListener mListener;

	private Handler mHandler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
				case FILE_DOWNLOAD_START:
					startNotification();
					break;
				case FILE_DOWNLOAD:
					progressNotification();
					break;
				case FILE_DOWNLOAD_FINISH:
					finishNotification();
					break;
				case FILE_DOWNLOAD_ERROR:
					errorNotification();
					break;
			}
			return false;
		}
	});

	/**
	 * 调用setTitle和setIcon设置应用名和应用Logo
	 *
	 * @param context
	 */
	public DownloadUtil(Context context) {
		this(context, null, R.drawable.ic_launcher);
	}

	/**
	 * @param context
	 * @param title   应用名
	 * @param icon    应用Logo
	 */
	public DownloadUtil(Context context, String title, int icon) {
		this.mContext = context;
		this.title = title;
		this.icon = icon;
	}

	/**
	 * 设置应用名
	 *
	 * @param title
	 * @author LSJ
	 * @create 2015-12-16 上午11:56:21
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 设置应用Logo
	 *
	 * @param icon
	 * @author LSJ
	 * @create 2015-12-16 上午11:56:21
	 */
	public void setIcon(int icon) {
		this.icon = icon;
	}

	/**
	 * 下载安装文件
	 */
	public void download(String url) {
		totalFinish = 0;
		boolean isDown = initDownFile(url);
		if (isDown) {
			new Thread() {
				public void run() {
					fileSize = getFileSize(downUrl);
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							if (fileSize > 0) {
								startDownLoad();
							} else {
								Toast.makeText(mContext, "文件获取失败!", Toast.LENGTH_LONG).show();
							}
						}
					});
				}
			}.start();
		}
	}

	private boolean initDownFile(String url) {
		if (saveDir == null) {
			saveDir = AppSDCardManager.getDiskCacheFile(mContext);
		}
		if (saveFile == null || !saveFile.isFile()) {
			saveFile = new File(saveDir, url.substring(url.lastIndexOf("/") + 1) + ".png");
		}
		if (saveFile.exists()) {
			saveFile.delete();
		}
		infoFile = new File(saveDir, saveFile.getName() + ".info");
		try {
			downUrl = new URL(url);
		} catch (MalformedURLException e) {
			Toast.makeText(mContext, "文件下载地址无效!", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}

	/**
	 * 获取下载文件大小
	 *
	 * @return
	 */
	private long getFileSize(URL url) {
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Accept-Encoding", "identity");
			conn.setConnectTimeout(8000);
			return conn.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 开始下载文件
	 */
	private void startDownLoad() {
		sendStartMessage();
		new DownloadThread().start();
	}

	/**
	 * 下载线程类
	 */
	private class DownloadThread extends Thread {
		@Override
		public void run() {
			try {
				InputStream in = conn.getInputStream();
				FileOutputStream fileRaf = new FileOutputStream(saveFile);
				byte[] buffer = new byte[1024 * 10];
				int len;
				int temp = 0;
				while ((len = in.read(buffer)) != -1) {
					fileRaf.write(buffer, 0, len);
					totalFinish += len;
					temp += len;
					if (temp > flag) {
						temp = 0;
						sendProgressMessage();
					}
					if (totalFinish >= fileSize) {
						sendFinishMessage();
					}
				}
				in.close();
				fileRaf.close();
				if (totalFinish == fileSize)
					infoFile.delete();
			} catch (IOException e) {
				e.printStackTrace();
				sendErrorMessage();
			} catch (Exception e) {
				e.printStackTrace();
				sendErrorMessage();
			}
		}
	}

	/**
	 * 开始下载 通知栏通知
	 *
	 * @author LSJ
	 * @time 2015-12-6下午02:18:48
	 */
	private void startNotification() {
		downLoadNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
		downLoadNotification = new Notification();
		downLoadNotification.icon = R.drawable.ic_launcher;
		downLoadNotification.tickerText = "正在下载";
		downLoadNotification.flags |= Notification.FLAG_ONGOING_EVENT; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中
		downLoadNotification.flags |= Notification.FLAG_AUTO_CANCEL;

		contentView = new RemoteViews(mContext.getPackageName(), R.layout.layout_notification_file_download);
		contentView.setTextViewText(R.id.tv_notification_title, "Download: " + title);
		contentView.setTextViewText(R.id.tv_notification_percent, "0% ");
		contentView.setImageViewResource(R.id.iv_notification_icon, icon);
		contentView.setProgressBar(R.id.pb_notification_progress, 100, 0, false);
		downLoadNotification.contentView = contentView;
		downLoadPendingIntent = PendingIntent.getActivity(mContext.getApplicationContext(), 0, new Intent(), 0);
		downLoadNotification.contentIntent = downLoadPendingIntent;
		downLoadNotificationManager.notify(1, downLoadNotification);
		Log.i(TAG, "开始下载文件:" + downUrl);
	}

	/**
	 * 通知栏下载进度刷新
	 *
	 * @author LSJ
	 * @create 2015-12-16 上午11:56:21
	 */
	private void progressNotification() {
		contentView.setTextViewText(R.id.tv_notification_percent, ((int) (totalFinish * 100 / fileSize)) + "% ");
		contentView.setProgressBar(R.id.pb_notification_progress, 100, (int) (totalFinish * 100 / fileSize), false);
		downLoadNotificationManager.notify(1, downLoadNotification);
	}

	/**
	 * 下载完成
	 *
	 * @author LSJ
	 * @create 2015-12-16 上午11:56:21
	 */
	private void finishNotification() {
		contentView.setTextViewText(R.id.tv_notification_title, title + " 下载完成!");
		contentView.setTextViewText(R.id.tv_notification_percent, "100% ");
		contentView.setProgressBar(R.id.pb_notification_progress, 100, 100, false);
		downLoadNotificationManager.notify(1, downLoadNotification);
		Log.i(TAG, "下载文件完成:" + saveFile.getAbsolutePath());
	}

	/**
	 * 下载错误
	 *
	 * @author LSJ
	 * @create 2015-12-16 上午11:56:21
	 */
	private void errorNotification() {
		Toast.makeText(mContext, "下载失败!", Toast.LENGTH_LONG).show();
		downLoadNotificationManager.cancel(1);
		Log.i(TAG, "下载文件失败");
	}

	/**
	 * 发送开始下载通知
	 *
	 * @author LSJ
	 * @create 2015-12-16 上午11:56:21
	 */
	private void sendStartMessage() {
		if (mListener != null)
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					mListener.onStart();
				}
			});
		if (isNotification) {
			mHandler.sendEmptyMessage(FILE_DOWNLOAD_START);
		}
	}

	/**
	 * 发送下载进度通知
	 *
	 * @author LSJ
	 * @create 2015-12-16 上午11:56:21
	 */
	private void sendProgressMessage() {
		if (mListener != null)
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					mListener.onProgress((int) (totalFinish * 100 / fileSize));
				}
			});
		if (isNotification) {
			mHandler.sendEmptyMessage(FILE_DOWNLOAD);
		}
	}

	/**
	 * 发送下载错误通知
	 *
	 * @author LSJ
	 * @create 2015-12-16 上午11:56:21
	 */
	private void sendErrorMessage() {
		if (mListener != null)
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					mListener.onFailure();
				}
			});
		if (isNotification) {
			mHandler.sendEmptyMessage(FILE_DOWNLOAD_ERROR);
		}
	}

	/**
	 * 发送下载完成通知
	 */
	private void sendFinishMessage() {
		if (mListener != null)
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					mListener.onFinish(saveFile);
				}
			});
		if (isNotification) {
			mHandler.sendEmptyMessage(FILE_DOWNLOAD_FINISH);
		}
	}

	public void setNotification(boolean isNotification) {
		this.isNotification = isNotification;
	}

	/**
	 * 设置文件保存目录
	 *
	 * @param saveDir
	 * @author LSJ
	 * @create 2015-12-16 上午11:56:21
	 */
	public void setSaveDir(File saveDir) {
		this.saveDir = saveDir;
	}

	/**
	 * 设置文件保存路径
	 *
	 * @param saveFile
	 * @author LSJ
	 * @create 2015-12-16 上午11:56:21
	 */
	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}

	public void setOnDownloadListener(OnDownloadListener onDownloadListener) {
		this.mListener = onDownloadListener;
	}

	public interface OnDownloadListener {

		public void onStart();

		/**
		 * 下载进度，0-100
		 *
		 * @param progress
		 * @author LSJ
		 * @create 2015-12-16 上午11:56:21
		 */
		public void onProgress(int progress);

		public void onFinish(File saveFile);

		public void onFailure();
	}
}
