package com.mex.GalaxyChain.ui.exchange;


import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.QuitEvent;
import com.mex.GalaxyChain.common.BaseFragment;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by LSJ on 18/3/4.
 * 持仓界面
 */
@EFragment(R.layout.fragment_exchange)
public class HolderFragment extends BaseFragment {

    @ViewById(R.id.tv_isLogined_jiesuan)
    TextView  tv_isLogined_jiesuan;  //已登陆  去 结算


    @Click({ R.id.tv_isLogined_jiesuan })
    void onClick(View view) {
        switch (view.getId()) {
          //  case R.id.tv_Login:
                // 持仓未登录状态下  手机号登陆界面
             //   UIHelper.jumptoPhoneNumLoginActivity(getActivity());
            //    break;
            case R.id.tv_isLogined_jiesuan:
                gotoJieSuan();
                break;

        }
    }


    boolean isLogined=true;
    private void gotoJieSuan() {

        if(isLogined){ //已登陆 去结算界面
            UIHelper.toJieSuanActivity(getActivity());

         }else{ // 没有登陆  去登陆界面


        }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(QuitEvent event) {

    }

}
