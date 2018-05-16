package com.mex.GalaxyChain.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.Constants;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_chicang_unlogin)
public class ChiCangUnLoginActivity  extends BaseActivity {



    @ViewById
    TextView tv_chicang_login;


    @Click({R.id.tv_chicang_login})
    void onClick(View view) {
        UIHelper.jumptoPhoneNumLoginActivity(this, Constants.FROM_CHICANG_UNLOGIN);
        finish();
    }

}
