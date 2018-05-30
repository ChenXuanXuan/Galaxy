package com.mex.GalaxyChain.ui.mine.activity;

import android.Manifest;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.PostLoginBean;
import com.mex.GalaxyChain.bean.VerifycodeBean;
import com.mex.GalaxyChain.bean.requestbean.RequestPostLoginBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.bean.galaxychainbean.RegistBean;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.CommonUtil;
import com.mex.GalaxyChain.utils.CreatSignUtils;
import com.mex.GalaxyChain.utils.DeviceUtil;
import com.mex.GalaxyChain.utils.EditUtils;
import com.mex.GalaxyChain.utils.LogUtils;
import com.mex.GalaxyChain.utils.RequestPermissionUtils;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

@EActivity(R.layout.activity_phone_register2)
public class PhoneNumRegistActivity2  extends BaseActivity{

    @Extra
    String phoneNumber;

     // @ViewById
    // EditText et_phone_number,et_verify_password

   // @ViewById
   // ImageView ll_clear_phone,

    @ViewById
    TextView bt_retrieve_verify_code;//接受验证码


    @ViewById
    EditText et_verify_password,et_phone_password; //验证码  设置密码


    @ViewById
    CheckBox cb_show_password_phone,ch_bx;

    @ViewById
    ImageView back;

    private MyCountDownTimer mMyCountDownTimer;


    @Click({R.id.bt_retrieve_verify_code,R.id.bt_register_now,R.id.back})
    void onClick(View view){
        switch(view.getId()){
            case R.id.bt_retrieve_verify_code://接受验证码
                onReceiveVerifyCode();
                break;
            case R.id.bt_register_now: //立即注册
                bt_register_now();
                break;

            case R.id.back:
                finish();
                break;

        }
    }



    private void onReceiveVerifyCode() {
        Map<String, Object> params = new HashMap<>();

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
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showErrorImage("验证码获取失败请重试");
                    }

                    @Override
                    public void onNext(VerifycodeBean verifycodeBean) {
                       if(verifycodeBean.getCode().equals("0")){
                           ToastUtils.showTextInMiddle("稍后验证码将以短信的形式发送到您的手机");
                           startTimer();
                       }


                    }
                });






    }

    private void startTimer() {
          if(mMyCountDownTimer==null){
              mMyCountDownTimer = new MyCountDownTimer(60000, 1000);
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






    @AfterViews
    protected void init(){
        initListener();
        LogUtils.d( "TAG_fragmentActivity", UserGolbal.getInstance().getLongitude()+UserGolbal.getInstance().getLatitude()+"");

    }

    private void initListener() {
        cb_show_password_phone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){ //选中明文
                     et_phone_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                     et_phone_password.setEnabled(true);
                     et_phone_password.requestFocus();
                     EditUtils.cursorFollow(et_phone_password);
                 }else{//未选中 暗纹
                     et_phone_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                     et_phone_password.setEnabled(true);
                     et_phone_password.requestFocus();
                     EditUtils.cursorFollow(et_phone_password);
                 }
            }
        });
    }


    private void bt_register_now() {
        if(isEmpty(phoneNumber)){
            ToastUtils.showErrorImage("手机号码不能为空");
            return;
        }

        String et_verify_passwordString = et_verify_password.getText().toString().trim();
        if (isEmpty(et_verify_passwordString)) {
            ToastUtils.showErrorImage("验证码不能为空");
            return;
        }

        String et_phone_passwordString = et_phone_password.getText().toString().trim();
        if (isEmpty(et_phone_passwordString)) {
            ToastUtils.showErrorImage("密码不能为空");
            return;
        }

        if(!ch_bx.isChecked()){
            CommonUtil.showSimpleInfo(getString(R.string.toast_rule_uncheck));
            return;
        }

        registerPhoneNow(phoneNumber, et_verify_passwordString, et_phone_passwordString);

    }


    private void registerPhoneNow(String phoneNumber, String et_verify_passwordString, final String et_phone_passwordString) {
        HashMap<String, Object> params = new HashMap<>();
        Date dt = new Date();
        String timeStamp = dt.getTime() + "";
        final String  country = "86";
        final String mobilePhone =phoneNumber;
      //  UserGolbal.getInstance().setPhoneNum(phoneNumber);

      //  String invitedCode="0123";
        params.put("country", country);
        params.put("mobile", mobilePhone);
        params.put("verifycode", et_verify_passwordString);
        params.put("password", et_phone_passwordString);
        params.put("time", timeStamp);
       // params.put("invitedCode",invitedCode);
        String sign = CreatSignUtils.creatSign(params);

      UserRepo.getInstance().getRegistCode(country,mobilePhone,et_verify_passwordString,et_phone_passwordString,timeStamp,sign)
          .subscribe(new Observer<RegistBean>() {
                         @Override
                         public void onCompleted() {}

                         @Override
                         public void onError(Throwable e) {
                             ToastUtils.showErrorImage(e.getMessage()+"注册失败请重试");
                             }

                         @Override
                         public void onNext(RegistBean registBean) {
                             if(registBean.getCode().equals("0")){
                                // ToastUtils.showCorrectImage("注册成功，正在登录");
                                  RegistBean.DataBean dataBean= registBean.getData();
                                  if(dataBean==null) return;
                                 ToastUtils.showErrorImage("注册成功");
                                 finish();//注册成功直接返回到登陆界面进行登陆
                                  //loacationAndPostLogin(dataBean); //注册成功  直接王浩登陆    跳到主界面
                             }else if(registBean.getCode().equals("110001")){
                                 ToastUtils.showErrorImage("短信验证码错误或过期");return;
                             }else if(registBean.getCode().equals("110023")){
                                 ToastUtils.showErrorImage("手机号已注册");return;
                                 //RegistBean.DataBean dataBean= registBean.getData();
                                 //loacationAndPostLogin(dataBean);
                             } else if(registBean.getCode().equals("100005")){
                                 ToastUtils.showErrorImage("参数签名错误"); return;
                             }else if(registBean.getCode().equals("110013")){
                                 ToastUtils.showErrorImage("邀请码无效");return;
                             }else if(registBean.getCode().equals("100001")){
                                 ToastUtils.showErrorImage("系统异常");return;
                             }

                             }
                     });



    }

    private void loacationAndPostLogin(final  RegistBean.DataBean dataBean) {
        RequestPermissionUtils.requestPermission(new Runnable() {
                                                     @Override
                                                     public void run() {
                                                         initLocation(dataBean);
                                                     }
                                                 }, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE);






    }
    private AMapLocationClient locationClient = null;
    private void initLocation(final RegistBean.DataBean dataBean) {
        //初始化client
        locationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //locationOption = getDefaultOption();
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        aMapLocationClientOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        aMapLocationClientOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        aMapLocationClientOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        aMapLocationClientOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        aMapLocationClientOption.setInterval(200000);
        //给定位客户端对象设置定位参数
        locationClient.setLocationOption(aMapLocationClientOption);
        // 启动定位
        locationClient.startLocation();
        // 设置定位监听
        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation location) {
                if (location != null) {
                    if (location.getErrorCode() == 0) {
                        LogUtils.d("经度:" + location.getLongitude() + " 纬度:" + location.getLatitude());
                        double mLongitude = location.getLongitude();
                        double mLatitude = location.getLatitude();
                        PostLoginByWHJK(dataBean,mLongitude, mLatitude);
                    } else {
                        Log.e("AmapError", "location Error, ErrCode:"
                                + location.getErrorCode() + ", errInfo:"
                                + location.getErrorInfo());
                    }

                }
            }
        });
    }



    private void PostLoginByWHJK(RegistBean.DataBean dataBean,double mLongitude, double mLatitude) {
       mShowDialog();
        RequestPostLoginBean requestPostLoginBean  = new  RequestPostLoginBean();
        requestPostLoginBean.setToken(dataBean.getToken()); //token
        requestPostLoginBean.setExpire(dataBean.getExpire()); //expire
        int deviceType = Constants.ANDROID; //设备类型
        requestPostLoginBean.setDeviceType(deviceType);
        String phoneModel = Build.MODEL;  //手机型号(华为)
        requestPostLoginBean.setDevcieModel(phoneModel);
        int channelID=Constants.channelId;////白标ID
        requestPostLoginBean.setChannelId(channelID);
        String APKVersion= AppUtil.getAppVersionName(MyApplication.getInstance());// APP版本号
        requestPostLoginBean.setVersion(APKVersion);
        MyApplication instance = MyApplication.getInstance();
        String device_identifier = DeviceUtil.getUdid(instance);
        String deviceID= HttpInterceptor.silentURLEncode(device_identifier);//设备ID
        requestPostLoginBean.setDeviceId(deviceID);
        if (mLatitude != 0.0) requestPostLoginBean.setLongitude(mLongitude);
        if (mLatitude != 0.0) requestPostLoginBean.setLatitude(mLatitude);

         Gson gson = new Gson();
        String jsonStr = gson.toJson(requestPostLoginBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                jsonStr);

        Observable<PostLoginBean> postLoginBeanObservable= UserRepo.getInstance().RequestPostLogin(requestBody);
        postLoginBeanObservable.subscribe(new Observer<PostLoginBean>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {

                dismissLoading();
                ToastUtils.showErrorImage(e.getMessage()+"登陆失败");
             }

            @Override
            public void onNext(PostLoginBean postLoginBean) {
                   if(postLoginBean.getCode()==200){
                       dismissLoading();
                        //ToastUtils.showCorrectImage("注册成功,正在登陆....");
                       UIHelper.jumptoMainActivity(PhoneNumRegistActivity2.this,""); //注册成功 直接到主界面
                        finish();
                   }else{
                       ToastUtils.showErrorImage(postLoginBean.getMsg()); return;
                   }


            }
        });




    }





}
