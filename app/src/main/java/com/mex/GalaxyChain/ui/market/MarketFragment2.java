package com.mex.GalaxyChain.ui.market;

import android.Manifest;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.requestbean.RequestDescriptionBean;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.ui.exchange.MarketPagerAdapter;
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


@EFragment(R.layout.fragment_market2)
public class MarketFragment2 extends BaseFragment {

    @ViewById(R.id.tabLayout)
    android.support.design.widget.TabLayout tabLayout;
    @ViewById(R.id.viewPager)
    ViewPager viewPager;
    private AMapLocationClient locationClient = null;
    //private AMapLocationClientOption locationOption = null;
    private String[] marketsTtitleArr;

    private List<MarketListChildFragment> fragmentList = new ArrayList<>();
   // private List<MarketSession> pairList = new ArrayList<>();


    //定位监听
    /*private AMapLocationListener locationListener=new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (location!=null) {
              if(location.getErrorCode() == 0){
                    LogUtils.d("经度:"+location.getLongitude()+" 纬度:"+location.getLatitude());
                 double mLongitude = location.getLongitude();
                 double  mLatitude = location.getLatitude();
                     loadData(mLongitude,mLatitude);

             }else{
                 Log.e("AmapError","location Error, ErrCode:"
                         + location.getErrorCode() + ", errInfo:"
                         + location.getErrorInfo());
             }

            }
        }
    };*/

    String[] titleTabArr = {"全部品种", "全球指数", "加密货币"};


    @AfterViews
    void initView() {
        initData();


    }


    int type = Constants.ALL_VARIETY;

    private void initData() {

        if (type == Constants.ALL_VARIETY) { //=0全部品种
            MarketListChildFragment marketListChildFragment = MarketListChildFragment_.builder().build();
          if(marketListChildFragment!=null)  marketListChildFragment.setPageTitle(marketsTtitleArr[Constants.ALL_VARIETY]);
            fragmentList.add(marketListChildFragment);
            //initViewPager(type,marketListChildFragment);


        } else if (type == Constants.GLOBAL_INDEX) { //=1全球指数

        } else if (type == Constants.CRYPTO_CURRENCY) { //=2加密货币


        }




        //for(int i = 0; i < marketsTtitleArr.length; i++){
        //  MarketListChildFragment marketListChildFragment = MarketListChildFragment_.builder().build();
        //   marketListChildFragment.setPageTitle(marketsTtitleArr[i]);
        //   fragmentList.add(marketListChildFragment);
        //MarketSession marketSession = new MarketSession();
        //marketSession.setName("Name：" + i);
        // pairList.add(marketSession);
        //在这里做网络请求


        //  }
    }


    //private IndicatorViewPagerAdapter mIndicatorViewPagerAdapter;
    private void initViewPager(final int type,final MarketListChildFragment marketListChildFragment) {
        MarketPagerAdapter myPagerAdapter = new MarketPagerAdapter(getChildFragmentManager(), fragmentList,titleTabArr);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
         viewPager.setCurrentItem(type);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                         if(type==position){ //=0
                             //进行网络请求
                             initLoacationAndRequestData(type,marketListChildFragment);
                         }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




    private void initLoacationAndRequestData(final int type,final MarketListChildFragment marketListChildFragment) {

        RequestPermissionUtils.requestPermission(new Runnable() {
                                                     @Override
                                                     public void run() {
                                                         initLocation(type,marketListChildFragment);
                                                     }
                                                 }, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE);
    }


    //初始化定位
    private void initLocation(final int type,final MarketListChildFragment marketListChildFragment) {

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
                        loadData(type, mLongitude, mLatitude,marketListChildFragment);

                    } else {
                        Log.e("AmapError", "location Error, ErrCode:"
                                + location.getErrorCode() + ", errInfo:"
                                + location.getErrorInfo());
                    }

                }
            }
        });


        }

    private void loadData(int type, double mLongitude, double mLatitude,MarketListChildFragment marketListChildFragment) {
         showLoading(getString(R.string.loading));
         loadNetData(type,mLongitude,mLatitude,marketListChildFragment );
    }

    private void loadNetData(int type, double mLongitude, double mLatitude,final MarketListChildFragment marketListChildFragment) {
        MyApplication instance = MyApplication.getInstance();
        String device_identifier = DeviceUtil.getUdid(instance);
        int all_variety = Constants.ALL_VARIETY;
        int deviceType = Constants.ANDROID;
        RequestDescriptionBean requestDescriptionBean = new RequestDescriptionBean();
        requestDescriptionBean.setCommoditytype(all_variety); //商品类型
        requestDescriptionBean.setDeviceType(deviceType); //设备类型
        requestDescriptionBean.setDevcieModel(Build.MODEL); //手机型号(华为)
        requestDescriptionBean.setChannelId(Constants.channelId); ////白标ID
        requestDescriptionBean.setVersion(AppUtil.getAppVersionName(MyApplication.getInstance())); // APP版本号
        requestDescriptionBean.setDeviceId(HttpInterceptor.silentURLEncode(device_identifier)); //设备ID
        if (mLatitude != 0.0) requestDescriptionBean.setLatitude(mLatitude); //维度
        if (mLongitude != 0.0) requestDescriptionBean.setLongitude(mLongitude); //精度

        Gson gson = new Gson();
        String jsonStr = gson.toJson(requestDescriptionBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                jsonStr);

       /* UserRepo.getInstance().getSymbols(requestBody)
                .subscribe(new HttpSubscriber<ReponseData<SymbolBean>>() {
                    @Override
                    protected void onSuccess(ReponseData<SymbolBean> repoData) {
                        dismissLoading();
                        LogUtils.d(repoData.toString());
                        SymbolBean symbolBean = repoData.getResult();
                      if(!IsEmptyUtils.isEmpty(symbolBean))  marketListChildFragment.transmitData(symbolBean);

                        }

                    @Override
                    protected void onFailure(ApiException e) {
                        dismissLoading();
                        ToastUtils.showErrorImage(e.getCode() + "获取数据失败");
                    }
                });*/



    }



}

















