package com.mex.GalaxyChain.ui.mine.activity;

import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.LogUtils;
import com.mex.GalaxyChain.utils.MD5Util;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


@EActivity(R.layout.activity_about)
public class AbountActivity extends BaseActivity {

    @ViewById
    ImageView back;

    @ViewById
    TextView mTitle;


    @ViewById(R.id.tv_versionName)
    TextView tv_versionName;


    @ViewById(R.id.rl_checkVersion)
    RelativeLayout rl_checkVersion;



    @Click({R.id.back,R.id.rl_checkVersion})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.rl_checkVersion:
                 getReg();
                 AppUtil.getVersionHome(this); //检查新版本
                break;


        }
    }

    @AfterViews
    void init() {
        initView();
    }

    private void initView() {
        mTitle.setText("关于");
        back.setVisibility(View.VISIBLE);
        tv_versionName.setText(AppUtil.getAppVersionName(MyApplication.getInstance()));
    }



    Map<String, Object> params = new ArrayMap<>();
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
