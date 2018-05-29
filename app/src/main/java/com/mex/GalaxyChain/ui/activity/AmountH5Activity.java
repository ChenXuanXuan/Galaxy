
package com.mex.GalaxyChain.ui.activity;

import android.graphics.PixelFormat;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.PostPayInBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.utils.IsEmptyUtils;
import com.mex.GalaxyChain.utils.LoadNetDataForMoneyFlowUtil;
import com.mex.GalaxyChain.utils.LogUtils;
import com.mex.GalaxyChain.utils.ToastUtils;
import com.mex.GalaxyChain.utils.webUtils.WebViewJavaScriptFunction;
import com.mex.GalaxyChain.view.X5WebView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;



/*
*
* 资产账户H5: 币币账户  法币账户  银行卡  支付宝
*
* */
@EActivity(R.layout.fragment_mine_amount_h5)
public class AmountH5Activity extends BaseActivity {

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
        mTitle.setText("资产账户");
        back.setVisibility(View.VISIBLE);
        mUrl = getIntent().getStringExtra("url");
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mWebView.loadUrl(mUrl);
       //JS端 传递数据  给   安卓端  // 币币账户:币币购买  和   法币账户:法币购买YY 币
        mWebView.addJavascriptInterface(new WebViewJavaScriptFunction() {
            @JavascriptInterface
            public void onJsFunctionCalled(String tag) {}

            // void getDataFromJS(double payinmoney,int payintype,String  sourcecurrency);
            @JavascriptInterface
            public void getDataFromJS(String payinmoney, int payintype, String sourcecurrency) {
                if (IsEmptyUtils.isEmpty(payinmoney)) {
                    ToastUtils.showTextInMiddle("输入充值YY币为空");
                    return;
                }

                if (IsEmptyUtils.isEmpty(payintype)) {
                    ToastUtils.showTextInMiddle("请选择充值币种类型");
                    return;
                }

                if (IsEmptyUtils.isEmpty(sourcecurrency)) {
                    ToastUtils.showTextInMiddle("请选择币种类型");
                    return;
                }
                LogUtils.d("TAG-->获取H5传递过来的数据", payinmoney + " " + payintype + " " + sourcecurrency);
                // PostPayInMoneyRequest(payinmoney, payintype, sourcecurrency);
                LoadNetDataForMoneyFlowUtil.getMoneyFlowInstance().PostPayInMoneyRequest(payinmoney, payintype, sourcecurrency);
                LoadNetDataForMoneyFlowUtil.getMoneyFlowInstance().setH5PayMoneyCallBackListener(new LoadNetDataForMoneyFlowUtil.H5PayMoneyCallBackListener() {
                    @Override
                    public void onSuccessCallBack(PostPayInBean postPayInBean) {
                        if (postPayInBean.getCode() == 200) {
                            //  ToastUtils.showTextInMiddle("下单成功");
                            int mOrderid = postPayInBean.getData().getOrderid();
                            //安卓调JS  给JS 传递参数   mOrderid&Constants.CHANNEL
                            String str = mOrderid + "&" + Constants.CHANNEL;
                            mWebView.loadUrl("javascript:javaCallJs('" + str + "')");
                            LogUtils.d("TAG->安卓原生给H5传递参数", str);
                        } else {
                            ToastUtils.showTextInMiddle("下单失败");
                            return;
                        }
                    }

                    @Override
                    public void onFailtueCallBack() {}
                });
            }


        }, "android");


        }

}















