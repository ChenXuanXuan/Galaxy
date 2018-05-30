package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
交易账户: 总资金  可用余额  占用保证金    总浮动盈亏  充值(H5 需实名认证)  提现(原生 需实名认证)
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

		//tv_totalAmount.setText(UserGolbal.getInstance().amount+"");
       // tv_canUsedAmount.setText(UserGolbal.getInstance().canusedamount+"");
       // tv_frozenmargin.setText(UserGolbal.getInstance().frozenmargin+"");
       // tv_totalprofit.setText(UserGolbal.getInstance().totalprofit+"");


	}

	@Click({R.id.back, R.id.inpour, R.id.withDraw})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;

			case R.id.inpour://充值(H5  需 认证 )
              //  if(UserGolbal.getInstance().isRealnameAuthC1Success()){
                if(ConfigManager.isRealnameAuthC1Success()){
                    InpourActivity_.intent(this).start();
                }else{
                    ToastUtils.showTextInMiddle("请先认证");
                     UIHelper.ToCertificationC1Activity(this);
                     }

                break;

			case R.id.withDraw://认证 提现(relative)
				//if(UserGolbal.getInstance().isRealnameAuthC1Success()){
                if(ConfigManager.isRealnameAuthC1Success()){
                    UIHelper.ToWithDrawActivity(getActivity());
                }else{
                    ToastUtils.showTextInMiddle("请先认证");
                    UIHelper.ToCertificationC1Activity(this);
                    }
				break;
		}
	}
}
