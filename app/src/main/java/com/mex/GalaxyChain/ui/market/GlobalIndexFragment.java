package com.mex.GalaxyChain.ui.market;


import android.Manifest;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.adapter.AllVarietyAdapter2;
import com.mex.GalaxyChain.bean.MultiplBean;
import com.mex.GalaxyChain.bean.SymbolBean;
import com.mex.GalaxyChain.bean.requestbean.GoodsPriceBean;
import com.mex.GalaxyChain.bean.requestbean.RequestDescriptionBean;
import com.mex.GalaxyChain.bean.requestbean.RequestGoodsPrice;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.ui.asset.entity.NumEntity;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.DeviceUtil;
import com.mex.GalaxyChain.utils.LogUtils;
import com.mex.GalaxyChain.utils.RequestPermissionUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;


//全球指数
@EFragment(R.layout.fragment_allvariety)
public class GlobalIndexFragment extends BaseFragment {




    private AllVarietyAdapter2 mAllVarietyAdapter2;
    @ViewById
    TextView noData;
    // @ViewById
    //PulltoRefreshBaseVeiw  refreshLayout;


    @ViewById
    ListView listView;




    @AfterViews
    protected void initViewData() {
        //   EventBus.getDefault().register(this);

        mAllVarietyAdapter2 = new AllVarietyAdapter2(getActivity());
        listView.setAdapter(mAllVarietyAdapter2);
        initLoacationAndRequestData( );



    }





    private void initLoacationAndRequestData() {

        RequestPermissionUtils.requestPermission(new Runnable() {
                                                     @Override
                                                     public void run() {
                                                         initLocation();
                                                     }
                                                 }, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE);


    }

    private AMapLocationClient locationClient = null;
    private void initLocation() {

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
                        loadNetData( mLongitude, mLatitude);

                    } else {
                        Log.e("AmapError", "location Error, ErrCode:"
                                + location.getErrorCode() + ", errInfo:"
                                + location.getErrorInfo());
                    }

                }
            }
        });


    }



    private void loadNetData( double mLongitude,double mLatitude) {
        if (isAdded())
        showLoading(getString(R.string.loading));
        MyApplication instance = MyApplication.getInstance();
        String device_identifier = DeviceUtil.getUdid(instance);
        int all_variety = Constants.GLOBAL_INDEX; //全球指数
        //  int all_variety = Constants.GLOBAL_INDEX;
        // int all_variety = Constants.CRYPTO_CURRENCY;
        int deviceType = Constants.ANDROID; //设备类型
        String phoneModel = Build.MODEL;  //手机型号(华为)
        int channelID=Constants.channelId;////白标ID
        String APKVersion= AppUtil.getAppVersionName(MyApplication.getInstance());// APP版本号
        String deviceID= HttpInterceptor.silentURLEncode(device_identifier);//设备ID

        netDataForDescription(all_variety,deviceType,phoneModel,channelID,APKVersion,deviceID,mLongitude,mLatitude);

        netDataForGoodsPrice(all_variety,deviceType,phoneModel,channelID,APKVersion,deviceID,mLongitude,mLatitude);

          startTimer(all_variety,deviceType,phoneModel,channelID,APKVersion,deviceID,mLongitude,mLatitude);
    }



    private void netDataForDescription(final int all_variety, int deviceType, String phoneModel, int channelID, String APKVersion, String deviceID, final double mLongitude, final double mLatitude) {
        RequestDescriptionBean requestDescriptionBean = new RequestDescriptionBean();//商品描述
        requestDescriptionBean.setCommoditytype(all_variety); //商品类型
        requestDescriptionBean.setDeviceType(deviceType); //设备类型
        requestDescriptionBean.setDevcieModel(phoneModel); //手机型号(华为)
        requestDescriptionBean.setChannelId(channelID); ////白标ID
        requestDescriptionBean.setVersion(APKVersion); // APP版本号
        requestDescriptionBean.setDeviceId(deviceID); //设备ID
        if (mLatitude != 0.0) requestDescriptionBean.setLatitude(mLatitude); //维度
        if (mLongitude != 0.0) requestDescriptionBean.setLongitude(mLongitude); //精度

        Gson gson = new Gson();
        String jsonStr = gson.toJson(requestDescriptionBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                jsonStr);


        UserRepo.getInstance().getSymbols(requestBody)
                .subscribe(new Subscriber<SymbolBean>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        dismissLoading();
                        e.printStackTrace();}

                    @Override
                    public void onNext(SymbolBean symbolBean) {
                        dismissLoading();
                        //ToastUtils.showCorrectImage("请求成功0");
                        //LogUtils.d(symbolBean.toString());
                        SymbolBean.DataBean   dataBean=symbolBean.getData();
                        if(dataBean==null) return;
                        List<SymbolBean.DataBean.SymbolInfosBean> symbolInfosBeanList= dataBean.getSymbolInfos();
                        if(symbolInfosBeanList==null||symbolInfosBeanList.size()==0){
                            noData.setVisibility(View.VISIBLE);
                            listView.setEmptyView(noData);
                            return;
                        }
                        mAllVarietyAdapter2.setItems(symbolInfosBeanList);
                        List<SymbolBean.DataBean.HandNumSBean> handNumSBeanList = symbolBean.getData().getHandNumS();// 几手
                        List<SymbolBean.DataBean.StopLossTimesBean> stopLossTimesBeanList  =symbolBean.getData().getStopLossTimes(); //几倍
                        setOnItemClickForListView(symbolInfosBeanList,mLongitude,mLatitude,all_variety,handNumSBeanList,stopLossTimesBeanList);
                    }
                });



    }



    List<GoodsPriceBean.DataBean>  goodsPriceList = new ArrayList<>();
    private void netDataForGoodsPrice(int all_variety,int deviceType,String phoneModel,int channelID,String APKVersion, String deviceID,double mLongitude,double mLatitude  ) {
        RequestGoodsPrice requestGoodsPrice = new RequestGoodsPrice();  //商品价格
        requestGoodsPrice.setCommoditytype(all_variety);
        requestGoodsPrice.setDeviceType(deviceType);
        requestGoodsPrice.setDevcieModel(phoneModel);
        requestGoodsPrice.setChannelId(channelID);
        requestGoodsPrice.setVersion(APKVersion);
        requestGoodsPrice.setDeviceId(deviceID);
        if (mLongitude != 0.0) requestGoodsPrice.setLongitude(mLongitude);
        if (mLatitude != 0.0) requestGoodsPrice.setLatitude(mLatitude);

        // EventBus.getDefault().post(requestGoodsPrice);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(requestGoodsPrice);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                jsonStr);

        UserRepo.getInstance().getGoodsPrice(requestBody)
                .subscribe(new Subscriber<GoodsPriceBean>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        // dismissLoading();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GoodsPriceBean goodsPriceBean) {
                        if(goodsPriceBean.getCode()==200){
                            //ToastUtils.showCorrectImage("请求成功1");
                            //LogUtils.d("TAG",goodsPriceBean.toString());
                            List<GoodsPriceBean.DataBean>  dataBeanList=goodsPriceBean.getData();
                            if(dataBeanList==null||dataBeanList.size()==0)return;
                            goodsPriceList.clear();
                            goodsPriceList.addAll(dataBeanList);
                            mAllVarietyAdapter2.transmitGoodsPriceList(goodsPriceList);
                        }
                    }
                });

        }





    private  MyCountDownTimer mCountDownTimer;
    private void startTimer(int all_variety,int deviceType,String phoneModel,int channelID,String APKVersion, String deviceID,double mLongitude,double mLatitude ) {
        if (mCountDownTimer == null) {
            mCountDownTimer = new  MyCountDownTimer(3000, 1000,
                    all_variety,deviceType,phoneModel,channelID,APKVersion,deviceID,mLongitude,mLatitude);
        }
        mCountDownTimer.start();

    }





    public class MyCountDownTimer  extends CountDownTimer {

        private final int all_variety;
        private final int deviceType;
        private final String phoneModel;
        private final int channelID;
        private final String APKVersion;
        private final String deviceID;
        private final double mLongitude;
        private final double mLatitude;

        /**
         * @param millisInFuture    计时总时间，计时结束调用onfinish方法
         * @param countDownInterval 多长时间回调一次ontick方法
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval,int all_variety,int deviceType,String phoneModel,int channelID,String APKVersion, String deviceID,double mLongitude,double mLatitude) {
            super(millisInFuture, countDownInterval);
            this.all_variety=all_variety;
            this.deviceType=deviceType;
            this.phoneModel=phoneModel;
            this.channelID=channelID;
            this.APKVersion=APKVersion;
            this.deviceID=deviceID;
            this.mLongitude=mLongitude;
            this.mLatitude=mLatitude;
        }

        @Override
        public void onTick(long millisUntilFinished) {}

        @Override
        public void onFinish() {
            netDataForGoodsPrice(all_variety,deviceType,phoneModel,channelID,APKVersion,deviceID,mLongitude,mLatitude);
            mCountDownTimer.start();

        }
    }




    private void setOnItemClickForListView(final List<SymbolBean.DataBean.SymbolInfosBean>   mSymbolInfosBeanList
            , final double mLongitude, final double mLatitude, final int all_variety, final List<SymbolBean.DataBean.HandNumSBean> handNumSBeanList,
                                           final List<SymbolBean.DataBean.StopLossTimesBean> stopLossTimesBeanList) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mSymbolInfosBeanList!=null&&mSymbolInfosBeanList.size()>0){
                    SymbolBean.DataBean.SymbolInfosBean symbolInfosBean=   mSymbolInfosBeanList.get(position);
                    UserGolbal.getInstance().setSymbol(symbolInfosBean.getSymbol());
                    UserGolbal.getInstance().setSymbolname(symbolInfosBean.getSymbolname());
                    UserGolbal.getInstance().setTag(all_variety);//全球指数
                    UserGolbal.getInstance().setClosetime(symbolInfosBean.getClosetime());// 平仓时间
                    UserGolbal.getInstance().setPerprofitnumber(symbolInfosBean.getPerprofitnumber());  //perprofitnumber 收益点数
                    UserGolbal.getInstance().setPerprofit(symbolInfosBean.getPerprofit()); //perprofit 收益
                    UserGolbal.getInstance().setCurrencytype(symbolInfosBean.getCurrencytype());  //currencytype;//币种类型

                    if (handNumSBeanList != null && handNumSBeanList.size() > 0) {
                        List<NumEntity> numEntityList = new ArrayList<>();
                        for (SymbolBean.DataBean.HandNumSBean handNumSBean : handNumSBeanList) {
                            NumEntity handNumbersBean = new NumEntity();
                            handNumbersBean.setId(handNumSBean.getId());
                            handNumbersBean.setHandSum(handNumSBean.getName());
                            numEntityList.add(handNumbersBean);
                        }
                        UserGolbal.getInstance().setNumEntityList(numEntityList);  // 几手
                    }

                    if(stopLossTimesBeanList!=null&&stopLossTimesBeanList.size()>0){
                        List<MultiplBean> multiplBeanList = new ArrayList<>();
                        for (SymbolBean.DataBean.StopLossTimesBean stopLossTimesBean : stopLossTimesBeanList) {
                            MultiplBean multiplBean = new MultiplBean();
                            multiplBean.setId(stopLossTimesBean.getId());
                            multiplBean.setName(stopLossTimesBean.getName());
                            multiplBeanList.add(multiplBean);
                        }
                        UserGolbal.getInstance().setMultiplBeanList(multiplBeanList);//几倍

                    }





                    UIHelper.toKLineActivity(getActivity()); //跳转到K线详情页面
                }


            }
        });
    }





   /* @Override
    public void onStop() {
        super.onStop();
        if(mCountDownTimer!=null){

            mCountDownTimer.onFinish();
            LogUtils.d("TAG","mCountDownTimer is finish21");
        }

    }*/

    /*@Override
    public void onPause() {
        super.onPause();
        if(mCountDownTimer!=null){
            mCountDownTimer.onFinish();
              LogUtils.d("TAG","mCountDownTimer is finish22");
        }

    }*/

   @Override
    public void onDestroy() {
        super.onDestroy();
        if(mCountDownTimer!=null){
            mCountDownTimer.onFinish();
            LogUtils.d("TAG","mCountDownTimer is finish23");
        }

    }



}
