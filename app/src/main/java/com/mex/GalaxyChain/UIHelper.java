package com.mex.GalaxyChain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.mex.GalaxyChain.ui.activity.ChiCangUnLoginActivity_;
import com.mex.GalaxyChain.ui.activity.JieSuanActivity_;
import com.mex.GalaxyChain.ui.asset.activity.MarketMainAct;
import com.mex.GalaxyChain.ui.market.FindPwStepOneActivity_;
import com.mex.GalaxyChain.ui.market.FindPwStepThreeActivity_;
import com.mex.GalaxyChain.ui.market.FindPwStepTwoActivity_;
import com.mex.GalaxyChain.ui.market.PhoneNumLoginActivity_;
import com.mex.GalaxyChain.ui.mine.activity.PhoneNumRegistActivity1_;
import com.mex.GalaxyChain.ui.mine.activity.PhoneNumRegistActivity2_;


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
}
