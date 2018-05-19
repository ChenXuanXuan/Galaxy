package com.mex.GalaxyChain.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.ui.mine.activity.LoginActivity;
import com.mex.GalaxyChain.utils.webUtils.WebViewJavaScriptFunction;
import com.mex.GalaxyChain.view.X5WebView;
import com.tencent.smtt.sdk.ValueCallback;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * name：
 * describe:
 * author: LSJ
 * time 2018/5/19 16:22
 */

@EActivity(R.layout.layout_act_browser)
public class BrowserActivity extends BaseActivity {
    @ViewById
    X5WebView mWebView;
    private String mUrl;
    private ValueCallback<Uri> uploadFile;

    public static void launch(Context ctx, String url) {
        Intent intent = new Intent(ctx, BrowserActivity_.class);
        intent.putExtra("url", url);
        ctx.startActivity(intent);
    }

    @AfterViews
    void init() {
        mUrl = getIntent().getStringExtra("url");
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mWebView.loadUrl(mUrl);
        initJs();
    }

    private void initJs() {
        mWebView.addJavascriptInterface(new WebViewJavaScriptFunction() {
            @Override
            public void onJsFunctionCalled(String tag) {
            }

            /**js调用本地的方法，这里模仿调用登录方法*/
            @JavascriptInterface
            public void htmlCallLogon(int type) {
            }

        }, "android");
        mWebView.addJavascriptInterface(this, "tjAdver");

        /**这是本地调用js方法，传递参数，如果不需要就把参数去掉*/
//        String uid = "";
//        String token = ConfigManager.getUserToken();
//        mWebView.loadUrl("javascript:take('" + uid + "','" + token  + "')");
//                            有返回值时调用
//                            mWebView.evaluateJavascript("take(" + uid + ")", new ValueCallback<String>() {
//                                @Override
//                                public void onReceiveValue(String value) {
//                                    ToastUtils.popoToast("uid");
//                                }
//                            });
    }

    @Click({R.id.tv_top_bar_left})
    void click(View v) {
        switch (v.getId()) {
            case R.id.tv_top_bar_left:
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                if (Integer.parseInt(Build.VERSION.SDK) >= 16)
                    return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (null != uploadFile) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }

        }

    }

    @Override
    protected void onDestroy() {
        if (mWebView != null)
            mWebView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent == null || mWebView == null || intent.getData() == null)
            return;
        mWebView.loadUrl(intent.getData().toString());
    }

}

