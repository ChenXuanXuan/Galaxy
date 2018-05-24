package com.mex.GalaxyChain.ui.mine.activity;

import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.LoginOutBean;
import com.mex.GalaxyChain.bean.eventbean.CloseBean;
import com.mex.GalaxyChain.bean.requestbean.PostLoginOutBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.DeviceUtil;
import com.mex.GalaxyChain.utils.LogUtils;
import com.mex.GalaxyChain.utils.MD5Util;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 上午11:09
 */
@EActivity(R.layout.frag_mine_setting)
public class SettingActivity extends BaseActivity {
	@ViewById
	ImageView back;

	@ViewById
	TextView mTitle;

	@ViewById
	TextView loginOut;

	Map<String, Object> params = new ArrayMap<>();

	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("设置");
		back.setVisibility(View.VISIBLE);
	}

	@Click({R.id.back, R.id.checkVersion, R.id.passWord, R.id.loginOut})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;

			case R.id.checkVersion://检查更新
				getReg();
                AppUtil.getVersionHome(this);
				break;

			case R.id.passWord://支付密码(1.0暂时不做)
				//PassWordActivity_.intent(this).start();
				break;

			case R.id.loginOut:
			    PostLoginOut();
			    break;
		}
	}


    private void PostLoginOut() {
	    if(UserGolbal.getInstance().locationSuccess()){
            //定位成功 经纬度直接用
            double mLongitude = UserGolbal.getInstance().getLongitude(); //空
            double mLatitude = UserGolbal.getInstance().getLatitude();//空
            String token = UserGolbal.getInstance().getUserToken();
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
                              UserGolbal.getInstance().setLoginOut("");
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




    private void getReg() {

		Date dt = new Date();
		String timeStamp = dt.getTime() + "";
		String country = "86";
		String mobilePhone = "15101650501";
		String otype = "1";
		String time = timeStamp;

		params.put("country", country);
		params.put("mobile", mobilePhone);
		params.put("otype", otype);
		params.put("time", time);
		String sign = creatSign(params);

		/*UserRepo.getInstance().getCode(country, mobilePhone, otype, time, sign)
                .subscribe(new HttpSubscriber<ReponseData<BaseBean>>() {
			@Override
			protected void onSuccess(ReponseData<BaseBean> repoData) {
				mDismissDialog();
				ToastUtils.showCorrectImage(repoData.getCode()+"");


			}


			@Override
			protected void onFailure(ApiException e) {
			}
		});*/
	}


	public String creatSign(Map<String, Object> params) {
		// 先将参数以其参数名的字典序升序进行排序
		Map<String, Object> sortedParams = new TreeMap<String, Object>(params);
		Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();

		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder basestring = new StringBuilder();
		for (Map.Entry<String, Object> param : entrys) {
			if (param.getKey().equals("sign")) {//去掉签名字段
				continue;
			}
			basestring.append(param.getKey());
			if (param.getValue() != null) {
				basestring.append(param.getValue().toString());
			}
		}
		basestring.append("jiaoyisuo@2017");
		LogUtils.d("basestring = " + basestring.toString());
		// 使用MD5对待签名串求签
		String curSign = MD5Util.MD5(basestring.toString());
		LogUtils.d("test basesign={}==============sign={}, serverSign={}" + basestring.toString() + curSign);
		return curSign;
	}



}
