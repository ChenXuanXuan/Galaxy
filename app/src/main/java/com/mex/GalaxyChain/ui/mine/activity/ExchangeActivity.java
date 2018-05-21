package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.UserGolbal;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 上午10:40
 */
@EActivity(R.layout.fragment_mine_exchange)
public class ExchangeActivity extends BaseActivity {
	@ViewById
	ImageView back;

	@ViewById
	TextView mTitle;

    @ViewById(R.id.tv_totalAmount)
    TextView tv_totalAmount;

    @ViewById(R.id.tv_canUsedAmount)
    TextView tv_canUsedAmount;

    @ViewById(R.id.tv_frozenmargin)
    TextView tv_frozenmargin;


    @ViewById(R.id.tv_totalprofit)
    TextView tv_totalprofit;



	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("交易账户");
		back.setVisibility(View.VISIBLE);
        tv_totalAmount.setText(UserGolbal.getInstance().amount+"");
        tv_canUsedAmount.setText(UserGolbal.getInstance().canusedamount+"");
        tv_frozenmargin.setText(UserGolbal.getInstance().frozenmargin+"");
        tv_totalprofit.setText(UserGolbal.getInstance().totalprofit+"");


	}

	@Click({R.id.back, R.id.inpour, R.id.withDraw})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;

			case R.id.inpour://充值
				InpourActivity_.intent(this).start();
				break;

			case R.id.withDraw://提现
				InpourActivity_.intent(this).start();
				break;
		}
	}
}
