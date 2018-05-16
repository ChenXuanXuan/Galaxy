package com.mex.GalaxyChain.ui.mine.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.ui.asset.view.XEditText;
import com.mex.GalaxyChain.utils.IEditTextChangeListener;
import com.mex.GalaxyChain.utils.ToastUtils;
import com.mex.GalaxyChain.utils.WorksSizeCheckUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 上午11:35
 */
@EActivity(R.layout.frag_mine_psw)
public class PassWordActivity extends BaseActivity {
	@ViewById
	ImageView back;

	@ViewById
	TextView mTitle;

	@ViewById
    XEditText etPsw;

	@ViewById
    XEditText etPswAgain;

	@ViewById
	TextView confirm;

	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("支付密码");
		back.setVisibility(View.VISIBLE);
		initEditListener();
	}

	private void initEditListener() {
		WorksSizeCheckUtil.textChangeListener textChangeListener = new WorksSizeCheckUtil.textChangeListener(confirm);
		textChangeListener.addAllEditText(etPsw, etPswAgain);
		WorksSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
			@Override
			public void textChange(boolean isHasContent) {
				if (isHasContent) {
					confirm.setEnabled(true);
//						confirm.setBackgroundResource(R.drawable.code_bg);
				} else {
					confirm.setEnabled(false);
//						confirm.setBackgroundResource(R.drawable.code_normar_bg);
				}
			}
		});
	}

	@Click({R.id.back, R.id.confirm})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;

			case R.id.confirm:
				doConfirm();
				break;
		}
	}

	private void doConfirm() {
		if (TextUtils.isEmpty(etPsw.getText().toString().trim())) {
			ToastUtils.showErrorImage("请输入支付密码");
			return;
		}

		if (TextUtils.isEmpty(etPswAgain.getText().toString().trim())) {
			ToastUtils.showErrorImage("请再次输入支付密码");
			return;
		}

		if (!etPsw.getText().toString().trim().equals(etPswAgain.getText().toString().trim())) {
			ToastUtils.showErrorImage("输入密码不一致");
			return;
		}

		//进行网络请求
	}
}
