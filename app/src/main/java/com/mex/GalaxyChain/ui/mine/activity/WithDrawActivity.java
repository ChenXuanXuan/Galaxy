package com.mex.GalaxyChain.ui.mine.activity;

import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.PayOutMeneyBean;
import com.mex.GalaxyChain.bean.requestbean.PayOutMoneyBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.DeviceUtil;
import com.mex.GalaxyChain.utils.IsEmptyUtils;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

@EActivity(R.layout.activity_withdraw)
public class WithDrawActivity  extends BaseActivity {


    @ViewById(R.id.mTitle)
    TextView mTitle;

    @ViewById(R.id.back)
    ImageView back;
    @ViewById(R.id.et_inPut_yyAcount)
    EditText et_inPut_yyAcount;

    @ViewById(R.id.tv_withDraw_next)
    TextView tv_withDraw_next;


    @ViewById(R.id.tv_canDraw_YY)
    TextView tv_canDraw_YY;   //当前可提余额


    @Click({R.id.back,R.id.tv_withDraw_next})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

            case R.id.tv_withDraw_next: //验证支付密码
                 checkPayPasssWord();
                  break;
        }


    }



    @AfterViews
    void init() {
        mTitle.setText("提现");
        back.setVisibility(View.VISIBLE);
        tv_canDraw_YY.setText("当前可提"+ UserGolbal.getInstance().canusedamount+"YY");

    }






    private void checkPayPasssWord() {
        String yyAcount = et_inPut_yyAcount.getText().toString();
        if (IsEmptyUtils.isEmpty(yyAcount)) {
            ToastUtils.showTextInMiddle("提现不能为空");
            return;
        }


        if (!IsEmptyUtils.isEmpty(yyAcount) && Double.valueOf(yyAcount) < Constants.MIN_YY_ACONUT) { //提现<10.00YYB
            ToastUtils.showTextInMiddle("最小提现数量10.00");
            return;
        }

        if (!IsEmptyUtils.isEmpty(yyAcount) && Double.valueOf(yyAcount) > UserGolbal.getInstance().canusedamount) { //提现<10.00YYB
            ToastUtils.showTextInMiddle("最大提现应小于当前可提余额");
            return;
        }

        UserGolbal.getInstance().drawedYYAcount=yyAcount;//已提现的YY数量

      /*  PayDialog.show(this, new PayDialog.PaySuccessListener() {
            @Override
            public void paySuccessedListener(String pwd) {
                //拿到密码 进行网络请求 验证支付密码是否正确

               // ToastUtils.showCorrectImage("网络请求");
                // InpourSuccess_.intent(InpourChannelAct.this).start();
                UIHelper.ToWithDrawSuccessActivity(WithDrawActivity.this);

            }
        });*/

        //做网络请求
        if (UserGolbal.getInstance().locationSuccess()) {
            mShowDialog();
            double mLongitude = UserGolbal.getInstance().getLongitude();
            double mLatitude = UserGolbal.getInstance().getLatitude();
            PayOutMoneyBean payOutMoneyBean = new PayOutMoneyBean();
            payOutMoneyBean.longitude = mLongitude;
            payOutMoneyBean.latitude = mLatitude;
           // payOutMoneyBean.usertoken = UserGolbal.getInstance().getUserToken();
            payOutMoneyBean.usertoken = ConfigManager.getUserToken();
            payOutMoneyBean.payoutmoney = Double.valueOf(yyAcount);
            payOutMoneyBean.payouttype = Constants.PAYOUTMONEYTYPE_FABI;
            payOutMoneyBean.deviceType = Constants.ANDROID;
            payOutMoneyBean.devcieModel = Build.MODEL;
            payOutMoneyBean.channelId = Constants.channelId;
            payOutMoneyBean.version = AppUtil.getAppVersionName(MyApplication.getInstance());
            MyApplication instance = MyApplication.getInstance();
            String device_identifier = DeviceUtil.getUdid(instance);
            String deviceID = HttpInterceptor.silentURLEncode(device_identifier);
            payOutMoneyBean.deviceId = deviceID;

            Gson gson = new Gson();
            String jsonStr = gson.toJson(payOutMoneyBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
            UserRepo.getInstance().postPayOutMeney(requestBody)
                    .subscribe(new Subscriber<PayOutMeneyBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            dismissLoading();
                        }

                        @Override
                        public void onNext(PayOutMeneyBean payOutMeneyBean) {
                            dismissLoading();

                            if(payOutMeneyBean.getCode()==200){//提现成功跳转到提现审核页面
                                UIHelper.ToWithDrawSuccessActivity(WithDrawActivity.this);

                            }else{
                                ToastUtils.showTextInMiddle("提现失败");//提现失败
                                return;
                            }
                        }
                    });


        } else {
            //重新去请求经纬度 在进行赋值
            UserGolbal.getInstance().requestLocation();
        }


    }






}
