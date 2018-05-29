package com.mex.GalaxyChain.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.dialog.VersionDialog;
import com.mex.GalaxyChain.net.bean.BaseBean;

import java.io.File;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by lenote on 2015/10/18.
 */
public class AppUtil {
	private static DisplayMetrics mDisplayMetrics;
	private static VersionDialog dialog;

	public static boolean checkPermission(Context context, String permission) {
		PackageManager pm = context.getPackageManager();
		int hasPerm = pm.checkPermission(permission, context.getPackageName());
		return hasPerm == PackageManager.PERMISSION_GRANTED;
	}

	public static String getAppVersionName(Context context) {
		String versionName = "0.0.0";

		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			versionName = packageInfo.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}

	public static void change(int pos, List<BaseFragment> mFragments, FragmentManager manager, int repId) {
		for (int i = 0; i < mFragments.size(); i++) {
			if (i == pos) {
				showFragment(i, manager, mFragments, repId);
			} else {
				hideFragment(i, manager, mFragments);
			}
		}
	}

	public static String sHA1(Context context) {
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
context.getPackageName(), PackageManager.GET_SIGNATURES);
			byte[] cert = info.signatures[0].toByteArray();
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] publicKey = md.digest(cert);
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < publicKey.length; i++) {
				String appendString = Integer.toHexString(0xFF & publicKey[i])
						.toUpperCase(Locale.US);
				if (appendString.length() == 1)
					hexString.append("0");
				hexString.append(appendString);
				hexString.append(":");
			}
			String result = hexString.toString();
			return result.substring(0, result.length()-1);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void showFragment(int index, FragmentManager manager, List<BaseFragment> mFragments, int repId) {
		FragmentTransaction startFragmentTransaction = manager.beginTransaction();
		Fragment startFragment = mFragments.get(index);
		boolean isOnresu = false;
		if (!startFragment.isAdded()) {
			isOnresu = false;
			startFragmentTransaction.add(repId, startFragment);
		} else {
			isOnresu = true;
//            startFragment.onResume();
			startFragmentTransaction.show(startFragment);
		}
		startFragmentTransaction.commitAllowingStateLoss();
		if (isOnresu)
			startFragment.onResume();
	}

	private static void hideFragment(int index, FragmentManager manager, List<BaseFragment> mFragments) {
		// 暂停当前显示的Fragment并隐藏
		FragmentTransaction currFragmentTransaction = manager.beginTransaction();
		Fragment currFragment = mFragments.get(index);
		currFragment.onPause();
		if (currFragment.isAdded()) {
			currFragmentTransaction.hide(currFragment);
			currFragmentTransaction.commitAllowingStateLoss();
		}
	}

	/**
	 * 复制到剪切板
	 */
	public static void copyToText(String text) {
		ClipboardManager cm = (ClipboardManager) MyApplication.appContext.getSystemService(Context.CLIPBOARD_SERVICE);
		cm.setText(text);
		ToastUtils.showCorrectImage(MyApplication.appContext.getResources().getString(R.string.tab_asset_savesuccess));
	}

	/**
	 * 获取手机分辨率
	 * author Lsj
	 *
	 * @return DisplayMetrics
	 */
	public static DisplayMetrics getScreenPixel(Context context) {
		if (mDisplayMetrics == null) {
			mDisplayMetrics = new DisplayMetrics();
			((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
					mDisplayMetrics);
		}
		Log.e("DisplayMetrics", "分辨率：" + mDisplayMetrics.widthPixels + "*" + mDisplayMetrics.heightPixels + ",精度："
				+ mDisplayMetrics.density + ",densityDpi=" + mDisplayMetrics.densityDpi);
		return mDisplayMetrics;
	}

	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int dp2px(Context context, int dp) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return (int) ((dp * displayMetrics.density) + 0.5);
	}

	/**
	 * @param activity
	 * @param rootView 父布局控件
	 * @param comment  Edittext布局
	 */
	public static void initEditText(final Activity activity, final View rootView, final View comment) {
		rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				//虚拟按键高度
				int bottomStatusHeight = getBottomStatusHeight(activity);
				Rect rect = new Rect();
				//取得 rootView 可视区域
				rootView.getWindowVisibleDisplayFrame(rect);
				//取得 rootView 不可视区域高度 (被其他View遮挡的区域高度)
				int rootInvisibleHeight = 0;
				if (checkNavigationBar(activity)) {
					rootInvisibleHeight = rootView.getRootView().getHeight() - rect.bottom - bottomStatusHeight;
				} else {
					rootInvisibleHeight = rootView.getRootView().getHeight() - rect.bottom;
				}
				//要是不可视区域高度大于100，则输入键盘就显示
				if (rootInvisibleHeight > 100) {
					int[] location = new int[2];
					//取得 scrollToInput 的坐标
					comment.getLocationInWindow(location);
					//计算滚动高度(rootView)，这样 (scrollToInput)在可视区域
					int srollHeight = (location[1] + comment.getHeight()) - rect.bottom;
					rootView.scrollTo(0, srollHeight);
				} else {
					//隐藏软键盘
					rootView.scrollTo(0, 0);
				}
			}
		});
	}

	/**
	 * 获取 虚拟按键的高度
	 *
	 * @param context
	 * @return
	 */
	public static int getBottomStatusHeight(Context context) {
		int totalHeight = getDpi(context);

		int contentHeight = getScreenHeight(context);

		return totalHeight - contentHeight;
	}

	/**
	 * @param context 获取是否存在NavigationBar
	 * @return
	 */
	public static boolean checkNavigationBar(Context context) {
		boolean hasNavigationBar = false;
		Resources rs = context.getResources();
		int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
		if (id > 0) {
			hasNavigationBar = rs.getBoolean(id);
		}
		try {
			Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
			Method m = systemPropertiesClass.getMethod("get", String.class);
			String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
			if ("1".equals(navBarOverride)) {
				hasNavigationBar = false;
			} else if ("0".equals(navBarOverride)) {
				hasNavigationBar = true;
			}
		} catch (Exception e) {

		}
		return hasNavigationBar;

	}

	/**
	 * @param context 获取屏幕原始尺寸高度，包括虚拟功能键高度
	 * @return
	 */
	public static int getDpi(Context context) {
		int dpi = 0;
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		@SuppressWarnings("rawtypes")
		Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
			Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, displayMetrics);
			dpi = displayMetrics.heightPixels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dpi;
	}

	/**
	 * 获得屏幕高度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	/**
	 * 获取当前应用版本名
	 *
	 * @param context
	 * @return 当前应用版本名
	 */
	public static String getVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}

	// Android获取一个用于打开Excel文件的intent
	public static Intent getExcelFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-excel");
		return intent;
	}

	// Android获取一个用于打开Word文件的intent
	public static Intent getWordFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/msword");
		return intent;
	}

	// Android获取一个用于打开CHM文件的intent
	public static Intent getChmFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/x-chm");
		return intent;
	}

	// Android获取一个用于打开文本文件的intent
	public static Intent getTextFileIntent(String param, boolean paramBoolean) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (paramBoolean) {
			Uri uri1 = Uri.parse(param);
			intent.setDataAndType(uri1, "text/plain");
		} else {
			Uri uri2 = Uri.fromFile(new File(param));
			intent.setDataAndType(uri2, "text/plain");
		}
		return intent;
	}

	// Android获取一个用于打开PDF文件的intent
	public static Intent getPdfFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/pdf");
		return intent;
	}

	// Android获取一个用于打开PPT文件的intent
	public static Intent getPptFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
		return intent;
	}

	// Android获取一个用于打开APK文件的intent
	public static Intent getApkFileIntent(String param) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
//		Uri uri = Uri.fromFile(new File(param));
		Uri uri;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			// "ruancoder.fileprovider"即是在清单文件中配置的authorities
			uri = FileProvider.getUriForFile(MyApplication.appContext, "ruancoder.fileprovider", new File(param));
			//临时授权
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		} else {
			uri = Uri.fromFile(new File(param));
		}
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		return intent;
	}

	/**
	 * 获得屏幕宽度
	 *
	 * @param context
	 * @return 像素
	 */
	public static int getScreenWeight(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	public static int Dp2Px(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	/**
	 * 邮箱验证
	 */
	public static boolean isEmail(String strEmail) {
		String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
		if (TextUtils.isEmpty(strPattern)) {
			return false;
		} else {
			return strEmail.matches(strPattern);
		}
	}

	/**
	 * 强制打开软键盘
	 */
	public static void showSoftInputFromWindow(Activity activity, EditText editText) {
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
		activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	}

	/**
	 * 关闭软键盘
	 */
	public static void closeKeyboard(Context context) {
		InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		View view = ((Activity) context).getCurrentFocus();
		if (view != null) {
			IBinder binder = view.getApplicationWindowToken();
			if (binder != null && im != null && view != null) {
				im.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
	}

	/**
	 * 检查新版本
	 */
	public static void getVersionHome(final Activity activity) {
//		UserRepo.getIntance().getUpdate()
//				.subscribe(new HttpSubscriber<ReponseData<BaseBean>>() {
//					@Override
//					protected void onSuccess(ReponseData<BaseBean> repoData) {
//						BaseBean result = repoData.getResult();
//						if (result != null) {
//							update(activity, result, false);
//						}
//					}
//
//					@Override
//					protected void onFailure(ApiException e) {
//					}
//
//				});
		BaseBean baseBean = new BaseBean();
		baseBean.setVersion("1.0.1");
		baseBean.setDesc("修复部分bug");
		baseBean.setUrl("");

        update(activity, baseBean, false);
	}

	private static void update(Activity mContext, BaseBean result, boolean isMustUpdate) {
		if (dialog == null && !IConstant.ISDIALOG) {
			dialog = new VersionDialog(mContext, R.style.dialog, result, isMustUpdate);
			dialog.show();
			IConstant.ISDIALOG = true;
			dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
				@Override
				public void onDismiss(DialogInterface dialogInterface) {
					IConstant.ISDIALOG = false;
				}
			});
		}
	}

	public static void hideKeyboard(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			if (activity.getCurrentFocus().getWindowToken() != null) {
				imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
	}

	/**
	 * 打开软键盘
	 */
	public static void showKeyboard(Context context) {
		InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		View view = ((Activity) context).getCurrentFocus();
		if (view != null) {
			IBinder binder = view.getApplicationWindowToken();
			if (binder != null && im != null && view != null) {
				im.showSoftInputFromInputMethod(binder, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
	}

	/**
	 * 软键盘是否弹出
	 */

	public static boolean isKeyboard(Activity activity) {
		WindowManager.LayoutParams params = activity.getWindow().getAttributes();
		if (params.softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
			return true;
		} else {
			return false;
		}
	}

	public static Dialog createLoadingDialog(Context context, String msg, boolean isLoading) {
		AnimationDrawable mLoadAnim = null;
		Dialog loadingDialog;
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_layout, null);
		ImageView loading = (ImageView) v.findViewById(R.id.gif);
		TextView tvMsg = (TextView) v.findViewById(R.id.tipTextView);
//		tvMsg.setText(TextUtils.isEmpty(msg) ? "" : msg);
		loadingDialog = new Dialog(context, R.style.loading_dialog);
		loadingDialog.setCancelable(true);
		Window window = loadingDialog.getWindow();
		window.setGravity(Gravity.CENTER);
		loading.setBackgroundResource(R.drawable.loading_gif);
		mLoadAnim = (AnimationDrawable) loading.getBackground();

		if (isLoading) {
			mLoadAnim.start();
			loadingDialog.setContentView(v);
			return loadingDialog;
		} else if (!isLoading && mLoadAnim != null) {
			mLoadAnim.stop();
			return null;
		}
		return loadingDialog;
	}

	public static Dialog createLoadingDialog(Context context, boolean isLoading) {
		Dialog loadingDialog;
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.layout_dialog_loading, null);
		loadingDialog = new Dialog(context, R.style.loading_dialog);
		loadingDialog.setCancelable(true);
		loadingDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND
				| WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		Window window = loadingDialog.getWindow();
		window.setGravity(Gravity.CENTER);

		if (isLoading) {
			loadingDialog.setContentView(v);
			return loadingDialog;
		} else if (!isLoading) {
			return null;
		}

		return loadingDialog;
	}

	public static void measureWidthAndHeight(View mPopupView) {
		//设置测量模式为UNSPECIFIED可以确保测量不受父View的影响
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		mPopupView.measure(w, h);
		//得到测量宽度
		int mWidth = mPopupView.getMeasuredWidth();
		//得到测量高度
		int mHeight = mPopupView.getMeasuredHeight();
	}

	/**
	 * 获取屏幕高度像素
	 *
	 * @return
	 */
	private int getScreenHeight(Activity activity) {
		// 获取屏幕实际像素
		DisplayMetrics displayMetrics = new DisplayMetrics();
		Display display = activity.getWindowManager()
				.getDefaultDisplay();
		display.getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}

	public static SimpleDateFormat sf = null;
	public static String getDateToString(long time) {
		Date d = new Date(time);
		sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sf.format(d);
	}

	public static String getDateToStringDetail(long time) {
		Date d = new Date(time);
		sf = new SimpleDateFormat("MM-dd HH:mm");
		return sf.format(d);
	}




    /*将字符串转为时间戳*/
    public static long getStringToDate(String dateString,String pattern){
        sf = new SimpleDateFormat(pattern); //"MM-dd HH:mm"
        Date date = new Date();
        try {
            date = sf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date.getTime();
    }













}
