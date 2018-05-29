package com.mex.GalaxyChain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.mex.GalaxyChain.ui.activity.AmountH5Activity_;
import com.mex.GalaxyChain.ui.activity.ChiCangUnLoginActivity_;
import com.mex.GalaxyChain.ui.activity.H5LoadingActivity_;
import com.mex.GalaxyChain.ui.activity.JieSuanActivity_;
import com.mex.GalaxyChain.ui.asset.activity.MarketMainAct;
import com.mex.GalaxyChain.ui.market.FindPwStepOneActivity_;
import com.mex.GalaxyChain.ui.market.FindPwStepThreeActivity_;
import com.mex.GalaxyChain.ui.market.FindPwStepTwoActivity_;
import com.mex.GalaxyChain.ui.market.PhoneNumLoginActivity_;
import com.mex.GalaxyChain.ui.mine.activity.AccountMoneyFlowActivity_;
import com.mex.GalaxyChain.ui.mine.activity.CertificationActivity_;
import com.mex.GalaxyChain.ui.mine.activity.CoinsCoinsRechargeH5Activity_;
import com.mex.GalaxyChain.ui.mine.activity.FaBiRechargeH5Activity_;
import com.mex.GalaxyChain.ui.mine.activity.PhoneNumRegistActivity1_;
import com.mex.GalaxyChain.ui.mine.activity.PhoneNumRegistActivity2_;
import com.mex.GalaxyChain.ui.mine.activity.WithDrawActivity_;
import com.mex.GalaxyChain.ui.mine.activity.WithDrawSuccessActivity_;


public class UIHelper {







    public static void jumptoFindPwStepTwoActivity(Context context, String et_phone_numberString) {

       Intent intent = new Intent(context, FindPwStepTwoActivity_.class);
        intent.putExtra("phoneNumber",et_phone_numberString);
        context.startActivity(intent);

    }






    public static void toFindPwStepThreeActivity(Context context, String phoneNumber, String et_verify_passwordString) {
        Intent intent = new Intent(context, FindPwStepThreeActivity_.class);
        intent.putExtra("mobile",phoneNumber);
        intent.putExtra("verfiycode",et_verify_passwordString);
        context.startActivity(intent);
    }







    public  static void toMarkMainAct_kLine(Activity activity){
           Intent intent = new Intent(activity, MarketMainAct.class);
           activity.startActivity(intent);
      };



    public static void toKLineActivity(Context context ) {
        Intent intent = new Intent(context, MarketMainAct.class);
        context.startActivity(intent);
    }


    public static void toJieSuanActivity(FragmentActivity activity) {
        Intent intent = new Intent(activity, JieSuanActivity_.class);
        activity.startActivity(intent);
    }



    public static void jumptoMainActivity(Activity activity,String tag) {
        Intent intent = new Intent(activity, MainActivity_.class);
        intent.putExtra("tag",tag);
        activity.startActivity(intent);


    }


    //手机号登陆界面
    public static void jumptoPhoneNumLoginActivity(Activity activity,String tag) {
        Intent intent = new Intent(activity,PhoneNumLoginActivity_.class);
        intent.putExtra("tag",tag);
        activity.startActivity(intent);
    }



    public static void toFindPassWordActivity(Context context) {
        Intent intent = new Intent(context, FindPwStepOneActivity_.class);
        context.startActivity(intent);
    }



    public static void toRegistActivity(Context context) {
        Intent intent = new Intent(context, PhoneNumRegistActivity1_.class);
        context.startActivity(intent);

    }


    public static void toRegistActivity2(Activity activity,String phoneNum) {

        Intent intent = new Intent(activity, PhoneNumRegistActivity2_.class);
         intent.putExtra("phoneNumber",phoneNum);
        activity.startActivity(intent);
        activity.finish();

    }

    public static void jumptoChiCangUnLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, ChiCangUnLoginActivity_.class);
        activity.startActivity(intent);
    }

    public static void toAmountH5Activity(Activity activity, String url) {

        Intent intent = new Intent(activity, AmountH5Activity_.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    public static void ToCoinsCoinsRechargeH5Activity(Activity activity, String url) {
        Intent intent = new Intent(activity, CoinsCoinsRechargeH5Activity_.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);

    }

    public static void ToH5LoadingActivity(Activity activity,int tag) {
        Intent intent = new Intent(activity, H5LoadingActivity_.class);
        intent.putExtra("tag", tag);
        activity.startActivity(intent);

    }





    public static void ToFaBiRechargeH5Activity(Activity activity, String url) {
        Intent intent = new Intent(activity, FaBiRechargeH5Activity_.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    public static void ToWithDrawActivity(Activity activity) {
        Intent intent = new Intent(activity, WithDrawActivity_.class);

        activity.startActivity(intent);
    }

    public static void ToWithDrawSuccessActivity(Activity activity) {
        Intent intent = new Intent(activity, WithDrawSuccessActivity_.class);
        activity.startActivity(intent);
    }

    public static void ToAccountMoneyFlowActivity(Activity activity,int type) {
        Intent intent = new Intent(activity, AccountMoneyFlowActivity_.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }


     //实名认证   公安系统认证+ C1
    public static void ToCertificationC1Activity(Activity activity) {
        // CertificationActivity_.intent(AssetCenterAct.this).start();
        Intent intent = new Intent(activity, CertificationActivity_.class);
        activity.startActivity(intent);
    }
}
