package com.mex.GalaxyChain.utils;

import android.os.Build;

import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.bean.MoneyFlowBean;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;

import java.util.HashMap;

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
             String token = UserGolbal.getInstance().getUserToken();
             paramMap.put("latitude", mLatitude);
             paramMap.put("longitude", mLongitude);
             paramMap.put("usertoken", token);
             paramMap.put("biztype", biztype);
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
             UserRepo.getInstance().getMoneyFlow(paramMap)
                    .subscribe(new Subscriber<MoneyFlowBean>() {
                         @Override
                         public void onCompleted() {

                         }

                         @Override
                         public void onError(Throwable e) {
                             ToastUtils.showTextInMiddle("无网络");
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
                                         loadMeneyFlowCallBackListener.onSuccessCallBack(moneyFlowBean);
                                     }
                                     break;
                                 }

                                 case Constants.CONGZHI: {
                                     if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                                         LogUtils.d("TAG->"+Constants.CONGZHI,moneyFlowBean.getData().getList().size()+"");
                                         loadMeneyFlowCallBackListener.onSuccessCallBack(moneyFlowBean);
                                     }
                                     break;
                                 }


                                 case Constants.TIXIAN: {
                                     if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                                         LogUtils.d("TAG->"+Constants.TIXIAN,moneyFlowBean.getData().getList().size()+"");
                                         loadMeneyFlowCallBackListener.onSuccessCallBack(moneyFlowBean);
                                     }
                                     break;
                                 }


                                 case Constants.KAICHANG: {
                                     if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                                         LogUtils.d("TAG->"+Constants.KAICHANG,moneyFlowBean.getData().getList().size()+"");
                                         loadMeneyFlowCallBackListener.onSuccessCallBack(moneyFlowBean);
                                     }
                                     break;
                                 }

                                 case Constants.PINCHANG: {
                                     if (!IsEmptyUtils.isEmpty(loadMeneyFlowCallBackListener)) {
                                         LogUtils.d("TAG->"+Constants.PINCHANG,moneyFlowBean.getData().getList().size()+"");
                                         loadMeneyFlowCallBackListener.onSuccessCallBack(moneyFlowBean);
                                     }
                                     break;
                                 }





                             }
                         }});

             }else{
             //重新去请求经纬度 在进行赋值
             UserGolbal.getInstance().requestLocation();

         }

   }




    private LoadMeneyFlowsSuccessCallBackListener loadMeneyFlowCallBackListener;
    public void setLoadMeneyFlowCallBackListener(LoadMeneyFlowsSuccessCallBackListener loadMeneyFlowCallBackListener){
        this.loadMeneyFlowCallBackListener=loadMeneyFlowCallBackListener;
    };

   public interface LoadMeneyFlowsSuccessCallBackListener {
        void  onSuccessCallBack(MoneyFlowBean moneyFlowBean);
       void  onFailtueCallBack();

        }

}
