package com.mex.GalaxyChain.ui.market;

import android.os.CountDownTimer;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.VerifycodeBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.CreatSignUtils;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Date;
import java.util.Map;

import rx.Subscriber;

@EActivity(R.layout.activity_find_pw_two)
public class FindPwStepTwoActivity extends BaseActivity {

@Extra
String phoneNumber;

    @ViewById
    TextView bt_retrieve_verify_code;//接受验证码

    @ViewById
    EditText et_verify_password; //验证码

    private  MyCountDownTimer mMyCountDownTimer;


     @Click({R.id.bt_retrieve_verify_code,R.id.tv_nextStep})
    void onClick(View view ){
        switch(view.getId()){
            case R.id.bt_retrieve_verify_code://接受验证码
                onReceiveVerifyCode();
                break;


            case R.id.tv_nextStep: //下一页
                toFindPwStepThreeActivity();

                break;
        }
    }

    private void toFindPwStepThreeActivity() {
        String et_verify_passwordString = et_verify_password.getText().toString().trim();
        if (isEmpty(et_verify_passwordString)) {
            ToastUtils.showErrorImage("验证码不能为空");
            return;
        }


        UIHelper.toFindPwStepThreeActivity(this,phoneNumber,et_verify_passwordString );
        finish();
    }


    private void onReceiveVerifyCode() {
        Map<String, Object> params = new ArrayMap<>();

        if(isEmpty(phoneNumber)){
            ToastUtils.showErrorImage("手机号码不能为空");
            return;
        }

        Date dt = new Date();
        String timeStamp = dt.getTime() + "";
        String country = "86";
        //String mobilePhone = "15028280990";
        String mobilePhone =phoneNumber;
        String otype = "1";

        params.put("country", country);
        params.put("mobile", mobilePhone);
        params.put("otype", otype);
        params.put("time", timeStamp);
        String sign = CreatSignUtils.creatSign(params);




       UserRepo.getInstance().getCode(country, mobilePhone, otype, timeStamp, sign)
                .subscribe(new Subscriber<VerifycodeBean>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) { ToastUtils.showErrorImage("验证码获取失败请重试"); return;}

                    @Override
                    public void onNext(VerifycodeBean verifycodeBean) {
                        ToastUtils.showTextInMiddle("稍后验证码将以短信的形式发送到您的手机");
                        startTimer();
                    }
                });

    }

    private void startTimer() {
        if(mMyCountDownTimer==null){
            mMyCountDownTimer = new  MyCountDownTimer(60000, 1000);
        }
        mMyCountDownTimer.start();
    }


    private class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            bt_retrieve_verify_code.setClickable(false);
            bt_retrieve_verify_code.setText("重新获取 " + millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            bt_retrieve_verify_code.setClickable(true);
            bt_retrieve_verify_code.setText("获取验证码");

        }
    }



}
