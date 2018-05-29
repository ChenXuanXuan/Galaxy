package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**

 *  账户中心: 手机号  实名认证原生  交易账户  资产账户 H5
 *
 */

@EActivity(R.layout.layout_asset_center)
public class AssetCenterAct extends BaseActivity {
	@ViewById
	ImageView back;

	@ViewById
	TextView mTitle;

    @ViewById(R.id.tv_isRenZheng)
    TextView tv_isRenZheng;//tv_isRenZheng

    @ViewById(R.id.phoneNum)
    TextView phoneNum;

    @ViewById(R.id.rl_amountCenter_Certification)
    RelativeLayout rl_amountCenter_Certification;

	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("账户中心");
		back.setVisibility(View.VISIBLE);
        phoneNum.setText(UserGolbal.getInstance().getPhoneNum());
        if(UserGolbal.getInstance().isRealnameAuthC1Success()){ //认证审核通过C1
             tv_isRenZheng.setText("已认证");
              rl_amountCenter_Certification.setClickable(false);
        }else{
            tv_isRenZheng.setText("未认证");
             rl_amountCenter_Certification.setClickable(true);
        }
	}

	@Click({R.id.back, R.id.exchange, R.id.amount,R.id.rl_amountCenter_Certification})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;

			case R.id.exchange://交易账户
				  ExchangeActivity_.intent(AssetCenterAct.this).start();
				  break;

            case R.id.amount://资产账户(H5):需要认证   url需要加ususerid token
                if(UserGolbal.getInstance().isRealnameAuthC1Success()){
                    int tag= Constants.ASSETACCOUNT_ZHICHAN;
                    UIHelper.ToH5LoadingActivity(AssetCenterAct.this,tag);

                   // String url=NetFuncConstants.H5_URL_ASSETACCOUNT;
                   // UIHelper.toAmountH5Activity(AssetCenterAct.this,url);
                  }else{
                    ToastUtils.showTextInMiddle("请先认证");
                    UIHelper.ToCertificationC1Activity(this);

                 }

                break;



            case R.id.rl_amountCenter_Certification://实名认证
                //公安系统认证+ C1
                 UIHelper.ToCertificationC1Activity(this);
               //  CertificationActivity_.intent(AssetCenterAct.this).start();
               // CertificationActivity2_.intent(AssetCenterAct.this).start();//高杰实名认证
                break;
		}
	}
}
