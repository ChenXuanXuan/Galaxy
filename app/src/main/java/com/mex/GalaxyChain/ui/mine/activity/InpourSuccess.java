package com.mex.GalaxyChain.ui.mine.activity;

import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 上午12:53
 */
@EActivity(R.layout.inpour_success)
public class InpourSuccess extends BaseActivity {
	@ViewById
	TextView mTitle;

	@AfterViews
	void  init(){
		initView();
	}

	private void initView() {
		mTitle.setText("充值成功");
	}
}
