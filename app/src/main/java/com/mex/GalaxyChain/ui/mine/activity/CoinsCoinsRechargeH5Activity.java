package com.mex.GalaxyChain.ui.mine.activity;

import android.graphics.PixelFormat;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.PostPayInBean;
import com.mex.GalaxyChain.bean.requestbean.PayInMoneyBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.DeviceUtil;
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

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;


/*  币币充值  */
@EActivity(R.layout.activity_coinscoinsrecharge_h5)
public class CoinsCoinsRechargeH5Activity extends BaseActivity {


    @ViewById(R.id.back)
    ImageView back;
    @ViewById(R.id.mTitle)
    TextView mTitle;
    @ViewById
    X5WebView mWebView;
    private String mUrl;


    @Click(R.id.back)
    void onClick(View view) {
        finish();
    }


    @AfterViews
    void init() {
        mTitle.setText("币币充值");
        back.setVisibility(View.VISIBLE);
        mUrl = getIntent().getStringExtra("url");
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

         mWebView.loadUrl(mUrl);
        //===========================================
        /*JS端  调用java安卓端:  JS端 传递数据  给   安卓端
         * 例如:在H5 输入一个数据    传递给安卓原生端  安卓原生端 能够获取此数据 进行显示   */

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


    private void PostPayInMoneyRequest(String payinmoney, int payintype, String sourcecurrency) {
        if (UserGolbal.getInstance().locationSuccess()) {
            double mLongitude = UserGolbal.getInstance().getLongitude(); //空
            double mLatitude = UserGolbal.getInstance().getLatitude();//空
            PayInMoneyBean payInMoneyBean = new PayInMoneyBean();
            payInMoneyBean.usertoken = ConfigManager.getUserToken();
            // payInMoneyBean.usertoken = UserGolbal.getInstance().getUserToken();
            payInMoneyBean.payinmoney = Double.valueOf(payinmoney);
            payInMoneyBean.payintype = payintype;
            payInMoneyBean.sourcecurrency = sourcecurrency;
            payInMoneyBean.deviceType = Constants.ANDROID;
            payInMoneyBean.devcieModel = Build.MODEL;
            payInMoneyBean.channelId = Constants.channelId;
            payInMoneyBean.version = AppUtil.getAppVersionName(MyApplication.getInstance());
            MyApplication instance = MyApplication.getInstance();
            String device_identifier = DeviceUtil.getUdid(instance);
            String deviceID = HttpInterceptor.silentURLEncode(device_identifier);
            payInMoneyBean.deviceId = deviceID;
            payInMoneyBean.longitude = mLongitude;
            payInMoneyBean.latitude = mLatitude;
            Gson gson = new Gson();
            String jsonStr = gson.toJson(payInMoneyBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
            UserRepo.getInstance().getPostPayInMoney(requestBody)
                    .subscribe(new Subscriber<PostPayInBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onNext(PostPayInBean postPayInBean) {
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
                    });

        } else {
            UserGolbal.getInstance().requestLocation();
        }


    }




}






