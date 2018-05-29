package com.mex.GalaxyChain.utils;

import android.os.Build;

import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.bean.MoneyFlowBean;
import com.mex.GalaxyChain.bean.PayOutListBean;
import com.mex.GalaxyChain.bean.PostPayInBean;
import com.mex.GalaxyChain.bean.requestbean.PayInMoneyBean;
import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

public class LoadNetDataForMoneyFlowUtil {


    private static LoadNetDataForMoneyFlowUtil mMLoadNetDataForMoneyFlowUtil;

    public static LoadNetDataForMoneyFlowUtil getMoneyFlowInstance(){
        if(IsEmptyUtils.isEmpty(mMLoadNetDataForMoneyFlowUtil)){
          synchronized (LoadNetDataForMoneyFlowUtil.class){
              if(IsEmptyUtils.isEmpty(mMLoadNetDataForMoneyFlowUtil)){
                  mMLoadNetDataForMoneyFlowUtil = new LoadNetDataForMoneyFlowUtil();

              }
          }
        }

       return  mMLoadNetDataForMoneyFlowUtil;
  }

  public synchronized void loadNetData(int page, final int biztype){
         if(UserGolbal.getInstance().locationSuccess()){
             HashMap<String, Object> paramMap = new HashMap<>();
             //定位成功 经纬度直接用
             double mLongitude = UserGolbal.getInstance().getLongitude(); //空
             double mLatitude = UserGolbal.getInstance().getLatitude();//空
            // String token = UserGolbal.getInstance().getUserToken();
             String token = ConfigManager.getUserToken();
             paramMap.put("latitude", mLatitude);
             paramMap.put("longitude", mLongitude);
             if(biztype==Constants.TIXIAN){//提现记录(没有biztype 参数)
                 paramMap.put("token", token);
             }else{ //全部 充值 开仓 结算
                 paramMap.put("usertoken", token);
                 paramMap.put("biztype", biztype);
             }


             paramMap.put("page", page);
             paramMap.put("pagesize", Constants.PAGESIZE);
             final int deviceType = Constants.ANDROID;//设备类型(1=IOS，2=安卓,3=UWP,4=PC)
             paramMap.put("deviceType", deviceType);
             String devcieModel = Build.MODEL;  //手机型号(如:华为)
             paramMap.put("devcieModel", devcieModel);
             int channelId = Constants.channelId;////白标ID
             paramMap.put("channelId", channelId);
             paramMap.put("version", AppUtil.getAppVersionName(MyApplication.getInstance()));
             MyApplication instance = MyApplication.getInstance();
             String device_identifier = DeviceUtil.getUdid(instance);
             String deviceID= HttpInterceptor.silentURLEncode(device_identifier);
             paramMap.put("deviceId", deviceID);

                 if(biztype==Constants.TIXIAN){ //提现记录的网络回调
                     analysistixianNetData(paramMap);
                 }else{ //全部 充值 开仓 结算的网络回调
                     analysisOtherNetData(paramMap,biztype);
                 }

                   

             }else{
             //重新去请求经纬度 在进行赋值
             UserGolbal.getInstance().requestLocation();

         }

   }

    private void analysistixianNetData(HashMap<String, Object> paramMap) {
        UserRepo.getInstance().getPayOutList(paramMap)
                .subscribe(new Subscriber<PayOutListBean>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                            loadMeneyFlowCallBackListener.onFailtueCallBack();
                        }
                    }

                    @Override
                    public void onNext(PayOutListBean payOutListBean) {
                        if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                            LogUtils.d("TAG->提现",payOutListBean.getData().getList().size()+"");
                            loadMeneyFlowCallBackListener.onSuccessCallBack(null,payOutListBean);
                        }
                    }
                });
    }

    private void analysisOtherNetData(HashMap<String, Object> paramMap,final int biztype) {
        UserRepo.getInstance().getMoneyFlow(paramMap)
                .subscribe(new Subscriber<MoneyFlowBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //  ToastUtils.showTextInMiddle("无网络");
                        if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                            loadMeneyFlowCallBackListener.onFailtueCallBack();
                        }

                    }

                    @Override
                    public void onNext(MoneyFlowBean moneyFlowBean) {
                        switch (biztype) {
                            case Constants.ALL: {
                                if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                                    LogUtils.d("TAG->"+Constants.ALL,moneyFlowBean.getData().getList().size()+"");
                                    loadMeneyFlowCallBackListener.onSuccessCallBack(moneyFlowBean,null);
                                }
                                break;
                            }

                            case Constants.CONGZHI: {
                                if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                                    LogUtils.d("TAG->"+Constants.CONGZHI,moneyFlowBean.getData().getList().size()+"");
                                    loadMeneyFlowCallBackListener.onSuccessCallBack(moneyFlowBean,null);
                                }
                                break;
                            }


                                /* case Constants.TIXIAN: {
                                     if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                                         LogUtils.d("TAG->"+Constants.TIXIAN,moneyFlowBean.getData().getList().size()+"");
                                         loadMeneyFlowCallBackListener.onSuccessCallBack(moneyFlowBean);
                                     }
                                     break;
                                 }*/


                            case Constants.KAICHANG: {
                                if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                                    LogUtils.d("TAG->"+Constants.KAICHANG,moneyFlowBean.getData().getList().size()+"");
                                    loadMeneyFlowCallBackListener.onSuccessCallBack(moneyFlowBean,null);
                                }
                                break;
                            }

                            case Constants.PINCHANG: {
                                if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                                    LogUtils.d("TAG->"+Constants.PINCHANG,moneyFlowBean.getData().getList().size()+"");
                                    loadMeneyFlowCallBackListener.onSuccessCallBack(moneyFlowBean,null);
                                }
                                break;
                            }





                        }
                    }});
    }

    
    
    
    
    
    
    
    
    

    private LoadMeneyFlowsSuccessCallBackListener loadMeneyFlowCallBackListener;
    public void setLoadMeneyFlowCallBackListener(LoadMeneyFlowsSuccessCallBackListener loadMeneyFlowCallBackListener){
        this.loadMeneyFlowCallBackListener=loadMeneyFlowCallBackListener;
    };

   public interface LoadMeneyFlowsSuccessCallBackListener {
        void  onSuccessCallBack(MoneyFlowBean moneyFlowBean,PayOutListBean payOutListBean);
       void  onFailtueCallBack();

        }






    //==============================================================
     //H5请求
    public void PostPayInMoneyRequest(String payinmoney, int payintype, String sourcecurrency) {
        if (UserGolbal.getInstance().locationSuccess()) {
            double mLongitude = UserGolbal.getInstance().getLongitude(); //空
            double mLatitude = UserGolbal.getInstance().getLatitude();//空
            PayInMoneyBean payInMoneyBean = new PayInMoneyBean();
           //  payInMoneyBean.usertoken = UserGolbal.getInstance().getUserToken();
            payInMoneyBean.usertoken = ConfigManager.getUserToken();
            payInMoneyBean.payinmoney = Double.valueOf(payinmoney);
            payInMoneyBean.payintype = payintype;
            payInMoneyBean.sourcecurrency = sourcecurrency;
            payInMoneyBean.deviceType = Constants.ANDROID;
            payInMoneyBean.devcieModel = Build.MODEL;
            payInMoneyBean.channelId = Constants.channelId;
            payInMoneyBean.version = AppUtil.getAppVersionName(MyApplication.getInstance());
            MyApplication instance = MyApplication.getInstance();
            String device_identifier = DeviceUtil.getUdid(instance);
            String deviceID = HttpInterceptor.silentURLEncode(device_identifier);
            payInMoneyBean.deviceId = deviceID;
            payInMoneyBean.longitude = mLongitude;
            payInMoneyBean.latitude = mLatitude;
            Gson gson = new Gson();
            String jsonStr = gson.toJson(payInMoneyBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
            UserRepo.getInstance().getPostPayInMoney(requestBody)
                    .subscribe(new Subscriber<PostPayInBean>() {
                        @Override
                        public void onCompleted() {}

                        @Override
                        public void onError(Throwable e) {
                            if (!IsEmptyUtils.isEmpty(h5PayMoneyCallBackListener)) {
                                h5PayMoneyCallBackListener.onFailtueCallBack();
                            }
                        }

                        @Override
                        public void onNext(PostPayInBean postPayInBean) {
                            if (!IsEmptyUtils.isEmpty(h5PayMoneyCallBackListener)) {
                                h5PayMoneyCallBackListener.onSuccessCallBack(postPayInBean);
                            }

                        }
                    });

        }else{
            UserGolbal.getInstance().requestLocation();
        }


     }





    private H5PayMoneyCallBackListener h5PayMoneyCallBackListener;
    public void setH5PayMoneyCallBackListener(H5PayMoneyCallBackListener h5PayMoneyCallBackListener){
        this.h5PayMoneyCallBackListener=h5PayMoneyCallBackListener;
    };

    public interface H5PayMoneyCallBackListener {
        void  onSuccessCallBack(PostPayInBean postPayInBean);
        void  onFailtueCallBack();

    }



}
