package com.mex.GalaxyChain.ui.mine.activity;

import android.graphics.PixelFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.view.X5WebView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_fabirecharge_h5)
public class FaBiRechargeH5Activity extends BaseActivity {



    @ViewById(R.id.back)
    ImageView back;
    @ViewById(R.id.mTitle)
    TextView mTitle;
    @ViewById
    X5WebView mWebView;
    private String mUrl;


    @Click(R.id.back)
    void  onClick(View view){
        finish();
    }


    @AfterViews
    void init(){
        mTitle.setText("法币充值");
        back.setVisibility(View.VISIBLE);
        mUrl = getIntent().getStringExtra("url");
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mWebView.loadUrl(mUrl);

    }

}
