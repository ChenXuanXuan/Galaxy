package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.frag_mine_about_us)
public class AboutUsActivity extends BaseActivity {
	@ViewById
	ImageView back;

	@ViewById
	TextView mTitle;

	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("关于我们");
		back.setVisibility(View.VISIBLE);
	}

	@Click({R.id.back})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;
		}
	}
}
