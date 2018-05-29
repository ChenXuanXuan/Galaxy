package com.mex.GalaxyChain.ui.market;

import android.Manifest;
import android.os.Build;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.PostLoginBean;
import com.mex.GalaxyChain.bean.RegistLoginBean;
import com.mex.GalaxyChain.bean.UserMeBean;
import com.mex.GalaxyChain.bean.requestbean.RequestPostLoginBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.bean.galaxychainbean.LoginBean;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.CreatSignUtils;
import com.mex.GalaxyChain.utils.DeviceUtil;
import com.mex.GalaxyChain.utils.EditUtils;
import com.mex.GalaxyChain.utils.LogUtils;
import com.mex.GalaxyChain.utils.RequestPermissionUtils;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

//手机号登录界面
@EActivity(R.layout.activity_login_phonenum)
public class PhoneNumLoginActivity extends BaseActivity {

    @Extra
    String tag;
    @ViewById
    EditText et_phone_number;
    @ViewById
    ImageView ll_clear_phone;
    @ViewById
    EditText et_phone_password; // 设置密码



    @ViewById
    CheckBox cb_show_password_phone;


    @Click({R.id.tv_login, R.id.tv_forget_pwd, R.id.tv_regist, R.id.ll_clear_phone})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                doLogin();
                //UIHelper.jumptoMainActivity(this); //登陆到主界面
                break;
            case R.id.tv_forget_pwd: //to 找回密码界面
                UIHelper.toFindPassWordActivity(this);
                break;
            case R.id.tv_regist:  //to到手机号 注册界面
                UIHelper.toRegistActivity(this); // 手机号码注册1
                // UIHelper.toRegistActivity2(this);   //手机好吗注册2
                break;
            case R.id.ll_clear_phone:
                et_phone_number.setText("");
                EditUtils.cursorFollow(et_phone_number);
                break;

        }
    }


    @AfterTextChange
    protected void et_phone_number() {
        String et_phone_numberString = et_phone_number.getText().toString().trim();
        if (et_phone_numberString.length() > 0) {
            ll_clear_phone.setVisibility(View.VISIBLE);
        } else {
            ll_clear_phone.setVisibility(View.GONE);
        }
    }


    @FocusChange(R.id.et_phone_number)
    protected void onEditTextFocusListener(EditText e, boolean hasFocus) {
        switch (e.getId()) {
            case R.id.et_phone_number:
                if (hasFocus) {
                    if (!isEmpty(e.getText().toString().trim())) {
                        ll_clear_phone.setVisibility(View.VISIBLE);
                    } else {
                        ll_clear_phone.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }


    @AfterViews
    protected void init() {
        //  EventBus.getDefault().register(this);
        initListener();
    }

    private void initListener() {
        cb_show_password_phone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { //选中明文
                    et_phone_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    et_phone_password.setEnabled(true);
                    et_phone_password.requestFocus();
                    EditUtils.cursorFollow(et_phone_password);
                } else {//未选中 暗纹
                    et_phone_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    et_phone_password.setEnabled(true);
                    et_phone_password.requestFocus();
                    EditUtils.cursorFollow(et_phone_password);
                }
            }
        });
    }


    private void doLogin() {
        String mobilePhone = et_phone_number.getText().toString().trim();
        if (isEmpty(mobilePhone)) {
            ToastUtils.showErrorImage("手机号码不能为空");
            return;
        }


        String et_phone_passwordString = et_phone_password.getText().toString().trim();
        if (isEmpty(et_phone_passwordString)) {
            ToastUtils.showErrorImage("密码不能为空");
            return;
        }


        HashMap<String, Object> params = new HashMap<>();
        final String country = "86";
        params.put("country", country);
        params.put("mobile", mobilePhone);
        UserGolbal.getInstance().setPhoneNum(mobilePhone);
        params.put("password", et_phone_passwordString);
        Date dt = new Date();
        String timeStamp = dt.getTime() + "";
        params.put("time", timeStamp);
        String sign = CreatSignUtils.creatSign(params);
         //  mShowDialog();

        //http://aaa.mex.group:81/exchange-api/api/login_mobile
        UserRepo.getInstance().
                getLoginCode(country, mobilePhone, et_phone_passwordString, timeStamp, sign)
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showErrorImage("登陆失败请重试");
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getCode() == 0) {
                              //登陆成功 再次登陆
                             loacationAndPostLogin(loginBean);
                              loadGetUserMe(loginBean);//mex  请求userme   获取userid status
                        } else if (loginBean.getCode() == 110020) {
                            ToastUtils.showErrorImage("用户名不存在");
                            return;
                        } else if (loginBean.getCode() == 100001) {
                            ToastUtils.showErrorImage("系统异常");
                            return;
                        } else {
                            ToastUtils.showErrorImage("登陆失败请重试");
                            return;
                        }
                    }
                });


    }

    private void loadGetUserMe(LoginBean loginBean) {
        HashMap<String, Object> params = new HashMap<>();
        String token=loginBean.getData().getToken();
        params.put("token",token);
        Date dt = new Date();
        String timeStamp = dt.getTime() + "";
        params.put("time", timeStamp);
        String sign = CreatSignUtils.creatSign(params);
        UserRepo.getInstance().getUserMe(token,timeStamp,sign)
                .subscribe(new Subscriber<UserMeBean>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(UserMeBean userMeBean) {
                       if(userMeBean.getCode().equals("0")){  //0 获取userme接口成功
                           UserMeBean.DataBean.AuthBean auth=userMeBean.getData().getAuth();
                            if(auth.getLevel()==Constants.RENZHENG_C1){ //=1 C1 认证
                                UserGolbal.getInstance().setStatus_auth_c1(auth.getStatus());//status=1 C1实名认证通过

                            }
                            UserGolbal.getInstance().uid=userMeBean.getData().getUser().getUid();
                       }
                    }
                });
    }


    private void loacationAndPostLogin(final LoginBean loginBean) {
        RequestPermissionUtils.requestPermission(new Runnable() {
                                                     @Override
                                                     public void run() {
                                                         initLocation(loginBean);
                                                     }
                                                 }, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE);
    }

    private AMapLocationClient locationClient = null;

    private void initLocation(final LoginBean loginBean) {
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
                        PostLoginByWHJK(loginBean, mLongitude, mLatitude);
                    } else {
                        Log.e("AmapError", "location Error, ErrCode:"
                                + location.getErrorCode() + ", errInfo:"
                                + location.getErrorInfo());
                    }

                }
            }
        });
    }

    private void PostLoginByWHJK(final LoginBean loginBean, double mLongitude, double mLatitude) {
        mShowDialog();
        RequestPostLoginBean requestPostLoginBean = new RequestPostLoginBean();
        requestPostLoginBean.setToken(loginBean.getData().getToken());
        requestPostLoginBean.setExpire(loginBean.getData().getExpire());
        requestPostLoginBean.setDeviceType(Constants.ANDROID);
        requestPostLoginBean.setDevcieModel(Build.MODEL);
        requestPostLoginBean.setChannelId(Constants.channelId);
        requestPostLoginBean.setVersion(AppUtil.getAppVersionName(MyApplication.getInstance()));
        MyApplication instance = MyApplication.getInstance();
        String device_identifier = DeviceUtil.getUdid(instance);
        requestPostLoginBean.setDeviceId(HttpInterceptor.silentURLEncode(device_identifier));
        if (mLatitude != 0.0) requestPostLoginBean.setLongitude(mLongitude);
        if (mLatitude != 0.0) requestPostLoginBean.setLatitude(mLatitude);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(requestPostLoginBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                jsonStr);

        Observable<PostLoginBean> postLoginBeanObservable = UserRepo.getInstance().RequestPostLogin(requestBody);
         postLoginBeanObservable.subscribe(new Observer<PostLoginBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading();
                ToastUtils.showErrorImage(e.getMessage() + "登陆失败");
            }

            @Override
            public void onNext(PostLoginBean postLoginBean) {
                if (postLoginBean.getCode() == 200) {
                    dismissLoading();
                    RegistLoginBean registLoginBean = new RegistLoginBean();
                    registLoginBean.setUsertoken(loginBean.getData().getToken());
                    UserGolbal.getInstance().setUserToken(loginBean.getData().getToken());
                    EventBus.getDefault().post(registLoginBean);

                      if(!isEmpty(tag)){
                                if(tag.equals(Constants.FROM_PAYORDER_K_MOKEMORE)){ //K线详情 看涨买多 --->登陆界面---> K线详情 看涨买多
                                    UIHelper.toMarkMainAct_kLine(PhoneNumLoginActivity.this);
                                    finish();
                                }else if(tag.equals(Constants.FROM_CHICANG_UNLOGIN)){ //持仓未登录界面--->登陆界面--->持仓已登陆界面(MainActivity 1)

                                    UIHelper.jumptoMainActivity(PhoneNumLoginActivity.this,tag);

                                    finish();
                                }

                      }else{
                          //(不带标签tag)一般其他的 常规情况下 登陆进入主界面
                           UIHelper.jumptoMainActivity(PhoneNumLoginActivity.this,""); //主界面
                           finish();

                      }












                } else {
                    ToastUtils.showErrorImage(postLoginBean.getCode() + "登陆失败");
                }


            }
        });
    }

}
