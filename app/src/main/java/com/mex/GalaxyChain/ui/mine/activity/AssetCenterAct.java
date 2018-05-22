package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
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
 * time 24/4/18 上午10:32
 *  账户中心: 手机号  实名认证原生  交易账户  资产账户 H5
 *
 */

@EActivity(R.layout.layout_asset_center)
public class AssetCenterAct extends BaseActivity {
	@ViewById
	ImageView back;

	@ViewById
	TextView mTitle;

    @ViewById
    TextView tv_isRenZheng;//tv_isRenZheng


	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("账户中心");
		back.setVisibility(View.VISIBLE);
        int realnamestatus=UserGolbal.getInstance().getRealnamestatus();
        if(realnamestatus==1){ //已认证
            tv_isRenZheng.setText("已认证");
        }else{
            tv_isRenZheng.setText("未认证");
        }
	}

	@Click({R.id.back, R.id.exchange, R.id.amount,R.id.rl_amountCenter_Certification})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;

			case R.id.exchange://交易账户(H5)
				  ExchangeActivity_.intent(AssetCenterAct.this).start();
				  break;

			case R.id.amount://资产账户(H5)
				//AmountActivity_.intent(AssetCenterAct.this).start();
                String url = "http://yanshengpin.mex.group:82/";
                  UIHelper.toAmountH5Activity(AssetCenterAct.this,url);
				break;



            case R.id.rl_amountCenter_Certification://实名认证
                CertificationActivity_.intent(AssetCenterAct.this).start();
                break;
		}
	}
}
