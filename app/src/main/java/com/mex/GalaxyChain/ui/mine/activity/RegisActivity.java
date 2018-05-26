package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.ui.asset.view.XEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.frag_mine_regis)
public class RegisActivity extends BaseActivity {

	@ViewById
	TextView mTitle;

	@ViewById
    XEditText phone;


	@ViewById(R.id.ruleDesc)
	CheckedTextView ruleDesc;

	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("注册登录");
	}

	@Click({R.id.ruleDesc, R.id.ruleDescLink, R.id.confirm})
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
		}
	}

	private void linkRule() {
		WebViewActivity_.intent(this).title(getString(R.string.title_service_rule)).url("url").start();
	}

	private void doConfirm() {
		RegisterActivity_.intent(this).phone(phone.getText().toString().trim()).start();
	}
}
