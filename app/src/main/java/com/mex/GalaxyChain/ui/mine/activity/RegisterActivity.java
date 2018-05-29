package com.mex.GalaxyChain.ui.mine.activity;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.ui.asset.view.XEditText;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.mine_register)
public class RegisterActivity extends BaseActivity {

    @ViewById
    LinearLayout topBar;

	@ViewById
	TextView mTitle;

	@Extra
	String phone;

	@ViewById
    XEditText code;

	@ViewById
	TextView requestCode;

	@ViewById
    XEditText psw;

	@ViewById(R.id.ruleDesc)
	CheckedTextView ruleDesc;

	private int checkTime = 0;
	private MyCount mc = new MyCount(60000, 1000);

	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
        topBar.setBackgroundColor(Color.TRANSPARENT);
		mTitle.setText("注册登录");
	}

	@Click({R.id.ruleDesc, R.id.ruleDescLink, R.id.confirm, R.id.requestCode})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.ruleDesc:
				ruleDesc.setChecked(!ruleDesc.isChecked());
				break;

			case R.id.ruleDescLink:
				linkRule();
				break;

			case R.id.confirm:
				doConfirm();
				break;

			case R.id.requestCode:
				doCode();
				break;
		}
	}

	private void linkRule() {
		//WebViewActivity_.intent(this).title(getString(R.string.title_service_rule)).url("url").start();
	}

	private void doConfirm() {
		LoginActivity_.intent(this)
				.phone(phone)
				.password(psw.getText().toString().trim())
				.start();
	}

	private void doCode() {
		AppUtil.closeKeyboard(this);
		if (checkTime == 0) {
			// TODO: 15/4/18  进行网络请求
			//ToastUtils.showToast("获取验证码成功,请注意查收");
			mc.start();
			code.setBackgroundResource(R.drawable.code_normal_bg);
		} else {
			ToastUtils.showErrorImage("60s内不能重复请求");
		}
	}

	class MyCount extends CountDownTimer {
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			checkTime++;
			requestCode.setText("请等待" + millisUntilFinished / 1000 + "秒");

		}

		@Override
		public void onFinish() {
			checkTime = 0;
			requestCode.setText("重新获取验证码");
			requestCode.setBackgroundResource(R.drawable.code_bg);
		}
	}

}
