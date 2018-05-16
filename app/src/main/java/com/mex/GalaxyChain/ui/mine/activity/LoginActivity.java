package com.mex.GalaxyChain.ui.mine.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.ui.asset.view.XEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 下午2:18
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

	@Extra
	String phone;

	@Extra
	String password;

	@ViewById
	ImageView back;

	@ViewById
	TextView mTitle;

	@ViewById
    XEditText etPhone;

	@ViewById
    XEditText etPsw;

	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("登录");
		back.setVisibility(View.VISIBLE);
		if (!TextUtils.isEmpty(phone))
			etPhone.setText(phone);
		if (!TextUtils.isEmpty(password))
			etPsw.setText(password);
	}

	@Click({R.id.back, R.id.confirm, R.id.forgetPsw})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;

			case R.id.confirm:
				doConfirm();
				break;

			case R.id.forgetPsw:
				break;
		}
	}

	private void doConfirm() {
		//登录网络请求
		MyApplication.getInstance().loginSuccess();
		finish();
	}
}
