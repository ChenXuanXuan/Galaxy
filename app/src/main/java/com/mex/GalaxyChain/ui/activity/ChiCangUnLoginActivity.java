package com.mex.GalaxyChain.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.common.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_chicang_unlogin)
public class ChiCangUnLoginActivity  extends BaseActivity {

    @ViewById
    ImageView back;

    @ViewById
    TextView mTitle;

    @ViewById
    TextView tv_chicang_login;

    @Extra
    String tag;


    @Click({R.id.tv_chicang_login,R.id.back})
    void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_chicang_login:
                UIHelper.jumptoPhoneNumLoginActivity(this, tag);
                finish();
                break;

            case R.id.back:
                finish();
                break;
        }

    }



    @AfterViews
    void init(){
        back.setVisibility(View.VISIBLE);
        mTitle.setVisibility(View.GONE);
    }

}
