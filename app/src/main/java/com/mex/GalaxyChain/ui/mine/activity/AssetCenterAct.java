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

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 上午10:32
 */
@EActivity(R.layout.layout_asset_center)
public class AssetCenterAct extends BaseActivity {
	@ViewById
	ImageView back;

	@ViewById
	TextView mTitle;

	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("资产中心");
		back.setVisibility(View.VISIBLE);
	}

	@Click({R.id.back, R.id.exchange, R.id.amount})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;

			case R.id.exchange://交易账户
				ExchangeActivity_.intent(AssetCenterAct.this).start();
				break;

			case R.id.amount://资产账户
				AmountActivity_.intent(AssetCenterAct.this).start();
				break;
		}
	}
}
