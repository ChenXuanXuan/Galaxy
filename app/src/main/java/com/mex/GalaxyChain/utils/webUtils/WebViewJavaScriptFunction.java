package com.mex.GalaxyChain.utils.webUtils;

public interface WebViewJavaScriptFunction {

    void onJsFunctionCalled(String tag);

    //payinmoney 入金金额  payintype 入金类型 1=币币 2=法币  sourcecurrency  目标币种
    void getDataFromJS(String payinmoney, int payintype, String sourcecurrency);




}
