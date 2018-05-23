package com.mex.GalaxyChain.common;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.mex.GalaxyChain.ui.common.AppManager;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.IConstant;
import com.mex.GalaxyChain.utils.IsEmptyUtils;
import com.mex.GalaxyChain.utils.LNLog;

/**
 * Created by lenote on 2015/9/6.
 */
public class BaseActivity extends FragmentActivity{
	protected ProgressDialog mLoading;
	private Dialog dialog;



    public BaseActivity getActivity() {
        return BaseActivity.this;
    }

	protected void showLoading(String msg) {
//        showLoading(msg,true);
		mShowDialog(msg);
	}

	public void mShowDialog(String msg) {
		if (dialog == null) {
			dialog = AppUtil.createLoadingDialog(this, true);
			dialog.setCanceledOnTouchOutside(false);
		}
		if (dialog != null && !this.isFinishing()) {
			dialog.show();
			dialog.setCanceledOnTouchOutside(false);
		}
	}

	public void mShowDialog() {
		if (dialog == null) {
			dialog = AppUtil.createLoadingDialog(this, true);
			dialog.setCanceledOnTouchOutside(false);
		}
		if (dialog != null && !this.isFinishing()) {
			dialog.show();
			dialog.setCanceledOnTouchOutside(false);
		}
	}

	protected void dismissLoading() {
		mDismissDialog();
//		if (mLoading != null) {
//			mLoading.dismiss();
//			mLoading = null;
//		}
	}

	public void mDismissDialog() {
		if (dialog != null && dialog.isShowing() && !this.isFinishing()) {
			dialog.dismiss();
			AppUtil.createLoadingDialog(this, "", false);
			dialog.setCanceledOnTouchOutside(false);
		}
	}

	protected void showToast(@StringRes int strRes) {
		Toast.makeText(this, strRes, Toast.LENGTH_SHORT).show();
	}

	protected void showToast(String info) {
		Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
	}

	protected void actionBarPressed() {
		new Thread() {
			@Override
			public void run() {
				try {
					Instrumentation inst = new Instrumentation();
					inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
				} catch (Exception e) {
					LNLog.e("Exception when onBack", e.toString());
				}
			}
		}.start();
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View v = getCurrentFocus();
			if (isShouldHideInput(v, ev)) {

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm != null) {
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}
			return super.dispatchTouchEvent(ev);
		}
		// 必不可少，否则所有的组件都不会有TouchEvent了
		if (getWindow().superDispatchTouchEvent(ev)) {
			return true;
		}
		return onTouchEvent(ev);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return handleHomeItem(item) || super.onOptionsItemSelected(item);
	}

	protected boolean handleHomeItem(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			onBackPressed();
			return true;
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		if (!handleBackPresse()) {
			super.onBackPressed();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActionBar();
		    AppManager.addActivity(this);
	}

	protected void initActionBar() {
		ActionBar actionBar = getActionBar();
		if (actionBar == null) {
			return;
		}
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		IConstant.ISDIALOG = false;
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	protected boolean handleBackPresse() {
		return false;
	}


	public boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] leftTop = {0, 0};
			//获取输入框当前的location位置
			v.getLocationInWindow(leftTop);
			int left = leftTop[0];
			int top = leftTop[1];
			int bottom = top + v.getHeight();
			int right = left + v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) {
				// 点击的是输入框区域，保留点击EditText的事件
				return false;
			} else {
				return true;
			}
		}
		return false;
	}



	/**
	 * 权限拒绝
	 */
	public void denied(int requestCode) {}

	/**
	 * 权限同意
	 *
	 * @param requestCode
	 */
	public void grant(int requestCode) {
	}


    protected boolean isEmpty(Object... o) {
        return IsEmptyUtils.isEmpty(o);
    }



}
