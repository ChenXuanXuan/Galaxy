package com.mex.GalaxyChain.ui.activity;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.net.NetFuncConstants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_loading_h5)
public class H5LoadingActivity extends BaseActivity {
    @Extra
    int tag;

    @ViewById(R.id.rootView)
    LinearLayout rootView;




    @AfterViews
    void init() {

        AlphaAnimation aa = new AlphaAnimation(1.0f, 0.1f);
        aa.setDuration(1500);
        rootView.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rootView.setAlpha(0.1f);
                 enterH5();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }




    private void enterH5() {
        if(tag== Constants.PAYOUTMONEYTYPE_BIBI){//tag=1  币币充值H5
            String bibi_url= NetFuncConstants.H5_URL_BIBICHONGZHI;//H5 币币充值url
            UIHelper.ToCoinsCoinsRechargeH5Activity(this,bibi_url);
            finish();
        }else if(tag== Constants.PAYOUTMONEYTYPE_FABI){//tag=2  法币充值H5
            String fabi_url=NetFuncConstants.H5_URL_FABICHONGZHI;//H5 法币充值url
            UIHelper.ToFaBiRechargeH5Activity(this,fabi_url);
            finish();
        }else if(tag== Constants.ASSETACCOUNT_ZHICHAN){//tag=3 资产账户H5
              String url=NetFuncConstants.H5_URL_ASSETACCOUNT;
              UIHelper.toAmountH5Activity(this,url);
             finish();
        }




    }


}
