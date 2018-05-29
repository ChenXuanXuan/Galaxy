package com.mex.GalaxyChain.ui.mine.activity;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.LoginOutBean;
import com.mex.GalaxyChain.bean.eventbean.CloseBean;
import com.mex.GalaxyChain.bean.requestbean.PostLoginOutBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.UrlTools;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.ui.activity.BrowserActivity_;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.DeviceUtil;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;


@EActivity(R.layout.frag_mine_setting)
public class SettingActivity extends BaseActivity {
	@ViewById
	ImageView back;

	@ViewById
	TextView mTitle;

	@ViewById
	TextView loginOut;

    @ViewById(R.id.rl_connectionWe)
    RelativeLayout rl_connectionWe;//联系我们

    @ViewById(R.id.rl_about)
    RelativeLayout rl_about;//关于





	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("设置");
		back.setVisibility(View.VISIBLE);
	}

	@Click({R.id.back, R.id.passWord, R.id.loginOut,R.id.rl_connectionWe,R.id.rl_about})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;
				case R.id.passWord://支付密码(1.0暂时不做)
				//PassWordActivity_.intent(this).start();
				break;

			case R.id.loginOut:
			    PostLoginOut();
			    break;


            case  R.id.rl_connectionWe://联系我们
                BrowserActivity_.launch(getActivity(), UrlTools.H5_URL_questionAndConnectionWe);
                break;


            case   R.id.rl_about:// 关于
                AbountActivity_.intent(this).start();
                break;
		}
	}


    private void PostLoginOut() {
	    if(UserGolbal.getInstance().locationSuccess()){
            //定位成功 经纬度直接用
            double mLongitude = UserGolbal.getInstance().getLongitude(); //空
            double mLatitude = UserGolbal.getInstance().getLatitude();//空
           // String token = UserGolbal.getInstance().getUserToken();
            String token = ConfigManager.getUserToken();
            PostLoginOutBean  postLoginOutBean =new PostLoginOutBean();
            postLoginOutBean.setToken(token);
            postLoginOutBean.setDeviceType(Constants.ANDROID);
            postLoginOutBean.setDevcieModel(Build.MODEL);
            postLoginOutBean.setChannelId(Constants.channelId);
            postLoginOutBean.setVersion(AppUtil.getAppVersionName(MyApplication.getInstance()));
            MyApplication instance = MyApplication.getInstance();
            String device_identifier = DeviceUtil.getUdid(instance);
            String deviceID= HttpInterceptor.silentURLEncode(device_identifier);
            postLoginOutBean.setDeviceId(deviceID);
            postLoginOutBean.setLongitude(mLongitude);
            postLoginOutBean.setLatitude(mLatitude);
            Gson gson = new Gson();
            String jsonStr = gson.toJson(postLoginOutBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
            UserRepo.getInstance().getPostLoginOut(requestBody)
                    .subscribe(new Subscriber<LoginOutBean>() {
                        @Override
                        public void onCompleted() {}

                        @Override
                        public void onError(Throwable e) {
                           ToastUtils.showErrorImage("退出登陆失败");
                        }

                        @Override
                        public void onNext(LoginOutBean loginOutBean) {
                             ToastUtils.showCorrectImage("退出登录成功");
                            //  UserGolbal.getInstance().setLoginOut();
                               ConfigManager.logOut();
                              CloseBean closeBean =new CloseBean();
                              EventBus.getDefault().post(closeBean);
                              UIHelper.jumptoPhoneNumLoginActivity(SettingActivity.this,"");
                               finish();

                        }
                    });

        }else{

            //重新去请求经纬度 在进行赋值
            UserGolbal.getInstance().requestLocation();
        }



	}











}
