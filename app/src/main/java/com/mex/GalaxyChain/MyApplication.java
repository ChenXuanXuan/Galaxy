package com.mex.GalaxyChain;

import android.app.Application;
import android.content.Context;
import android.graphics.Rect;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.utils.IConstant;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;


/**
 * Created by LSJ on 2015/9/5.
 */
public class MyApplication extends Application{
        //MultiDexApplication {
	public static Context appContext;
	/**
	 * 全局debug标记
	 */
	public static boolean isDebug = true;
	static MyApplication sInstance;
	Rect displayRect = new Rect();
	DisplayMetrics displayMetrics = new DisplayMetrics();
    public   NumberFormat mNumberFormat;



	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		appContext = getApplicationContext();
		ConfigManager.init(this);
	//	initImageLoader(getApplicationContext());
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getRectSize(displayRect);
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		isDebug = BuildConfig.DEBUG;
		initBugly();
        //初始化number 保留小数点位数格式
        mNumberFormat = NumberFormat.getInstance();
		//初始化X5内核
		QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
			@Override
			public void onCoreInitFinished() {
				//x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。

			}

			@Override
			public void onViewInitFinished(boolean b) {
				//x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
				Log.e("@@", "加载内核是否成功:" + b);
			}
		});
	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
	}

	private void initBugly() {
		Context context = getApplicationContext();
		// 获取当前包名
		String packageName = context.getPackageName();
		// 获取当前进程名
		String processName = getProcessName(android.os.Process.myPid());
		// 设置是否为上报进程
		CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
		strategy.setUploadProcess(processName == null || processName.equals(packageName));
		// 初始化Bugly
		CrashReport.initCrashReport(context, IConstant.APPID, isDebug, strategy);
	}

	/**
	 * 获取进程号对应的进程名
	 *
	 * @param pid 进程号
	 * @return 进程名
	 */
	private static String getProcessName(int pid) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
			String processName = reader.readLine();
			if (!TextUtils.isEmpty(processName)) {
				processName = processName.trim();
			}
			return processName;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		return null;
	}

	public static MyApplication getInstance() {
		return sInstance;
	}


	public int getWidth() {
		return displayRect.width();
	}


	public void loginSuccess() {
		ConfigManager.setIsLogin(true);
	}
}
