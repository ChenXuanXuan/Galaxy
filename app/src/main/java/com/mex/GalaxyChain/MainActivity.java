package com.mex.GalaxyChain;

import android.Manifest;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.mex.GalaxyChain.bean.QuitEvent;
import com.mex.GalaxyChain.bean.eventbean.CloseBean;
import com.mex.GalaxyChain.bean.eventbean.TagBean;
import com.mex.GalaxyChain.bean.eventbean.ToMarketFragBean;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.event.NetEvent;
import com.mex.GalaxyChain.ui.asset.fragment.AssetFragment_;
import com.mex.GalaxyChain.ui.common.AppManager;
import com.mex.GalaxyChain.ui.common.afragmentTask.BackHandledFragment;
import com.mex.GalaxyChain.ui.common.afragmentTask.BackHandledInterface;
import com.mex.GalaxyChain.ui.exchange.ExchangeFragment2_;
import com.mex.GalaxyChain.ui.market.MarketFragment_;
import com.mex.GalaxyChain.ui.mine.MineFragment_;
import com.mex.GalaxyChain.ui.mine.activity.LoginActivity_;
import com.mex.GalaxyChain.utils.IsEmptyUtils;
import com.mex.GalaxyChain.utils.RequestPermissionUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements BackHandledInterface {
    private static final int QUIT_INTERVAL = 2000;
    @ViewById(R.id.main_tab_market)
    TextView marketButton;
    @ViewById(R.id.main_tab_exchange)
    TextView exchangeButton;
    @ViewById(R.id.main_tab_asset)
    TextView assetButton;
    @ViewById(R.id.main_tab_mine)
    TextView mineButton;
    @ViewById(R.id.rootView)
    RelativeLayout rootView;
    @Extra
    int index = Constants.INDEX_MARKET;
    @Extra
    int sideType = 1;
    @Extra
    String tag;
    List<BaseFragment> fragmentList;
    private BaseFragment fragment;
    private BackHandledFragment mBackHandedFragment;
    private long lastBackPressed;
    private int fragIndex = Constants.INDEX_MARKET;
    private AMapLocationClientOption mAMapLocationClientOption;

    @Click({R.id.main_tab_mine, R.id.main_tab_exchange, R.id.main_tab_asset, R.id.main_tab_market})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tab_market:
                showFragment(Constants.INDEX_MARKET);
                break;
            case R.id.main_tab_exchange: //持仓
                //if (ConfigManager.getIsLogin())
                //	showFragment(Constants.INDEX_EXCHANGE);
                //else
                //	showLoginActivity();
                if (!IsEmptyUtils.isEmpty(UserGolbal.getInstance().getUserToken())) {
                    showFragment(Constants.INDEX_EXCHANGE);
                } else {
                    UIHelper.jumptoChiCangUnLoginActivity(this);
                    finish();
                }

                break;
            case R.id.main_tab_asset:
                showFragment(Constants.INDEX_ASSET);
                break;
            case R.id.main_tab_mine:

               if (!IsEmptyUtils.isEmpty(UserGolbal.getInstance().getUserToken())) {
                    showFragment(Constants.INDEX_MINE);
                 } else {
                    UIHelper.jumptoPhoneNumLoginActivity(this, "");
                     finish();
                }


                break;
        }
    }


    private void showFragment(int fragmentIndex) {

        if (this.fragIndex != fragmentIndex || fragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (fragment != null) {
                transaction.hide(fragment);
            }
            fragment = fragmentList.get(fragmentIndex);
            if (!fragment.isAdded()) {
                transaction.add(R.id.content, fragment).show(fragment).commitAllowingStateLoss();
            } else {
                transaction.show(fragment).commitAllowingStateLoss();
            }
            onTabChanged(fragmentIndex);
            this.fragIndex = fragmentIndex;
        }
    }

    private void showLoginActivity() {
//    	MarketMainAct.launch(this);
        LoginActivity_.intent(this).start();
    }

    private void onTabChanged(int index) {
        switch (index) {
            case Constants.INDEX_MARKET:
                modifyButtonStatu(marketButton);
                break;
            case Constants.INDEX_EXCHANGE:
                modifyButtonStatu(exchangeButton);
                break;
            case Constants.INDEX_ASSET:
                modifyButtonStatu(assetButton);
                break;
            case Constants.INDEX_MINE:
                modifyButtonStatu(mineButton);
                break;
        }
    }

    private void modifyButtonStatu(View view) {
        marketButton.setSelected(view == marketButton);
        exchangeButton.setSelected(view == exchangeButton);
        assetButton.setSelected(view == assetButton);
        mineButton.setSelected(view == mineButton);
    }

    @AfterViews
    void init() {
        EventBus.getDefault().register(this);
        AppManager.addActivity(this);
          if(!IsEmptyUtils.isEmpty(tag)){
              if(tag.equals(Constants.FROM_CHICANG_UNLOGIN)){//持仓未登录界面  --->登陆界面--->持仓已登录界面(MainActivity 1)
                 initViewPager(Constants.INDEX_EXCHANGE);
             } //else if(tag.equals(Constants.FROM_K_BUTTON_PAYORDERMORE)){//K线下单 确定做多  按钮买单 提交成功 ----> 持仓已登录界面
              //   initViewPager(Constants.INDEX_EXCHANGE);
           //  } //没效果




         }else{
              //其他正常情况下(未带标签) 进入主界面  0
             initViewPager(index);
         }



            initLocation();


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(QuitEvent event){}

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTagBean(TagBean tagBean){
       if(tagBean.getTag().equals(Constants.FROM_K_BUTTON_PAYORDERMORE)){
           initViewPager(Constants.INDEX_EXCHANGE); //直接到持仓界面 1
       }

       //ToastUtils.showTextInMiddle(tagBean.getSymbolName()+tagBean.getCloseTime()+"mainAct");
        //UserGolbal.getInstance().setSymbolname_ch(tagBean.getSymbolName_ch());
        //UserGolbal.getInstance().setClosetime_ch(tagBean.getCloseTime_ch());
    }

    private void initLocation() {
        RequestPermissionUtils.requestPermission(new Runnable() {
                                                     @Override
                                                     public void run() {
                                                         initLocations();
                                                     }
                                                 }, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE);
    }

    private AMapLocationClient locationClient = null;

    //在MainA 里做定位 请求 逻辑代码放在UserGal 里
    private void initLocations() {
        UserGolbal.getInstance().requestLocation();


       /* //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //locationOption = getDefaultOption();
        mAMapLocationClientOption = new AMapLocationClientOption();
        mAMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mAMapLocationClientOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mAMapLocationClientOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        mAMapLocationClientOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mAMapLocationClientOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mAMapLocationClientOption.setInterval(200000);
        //给定位客户端对象设置定位参数
        locationClient.setLocationOption(mAMapLocationClientOption);
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
                        UserGolbal.getInstance().setLongitude(mLongitude);
                        UserGolbal.getInstance().setLatitude(mLatitude);
                    } else {
                        Log.e("AmapError", "location Error, ErrCode:"
                                + location.getErrorCode() + ", errInfo:"
                                + location.getErrorInfo());
                    }

                }
            }
        });*/

    }

    private void initViewPager(int index) {
        fragmentList = new ArrayList<>();
        fragmentList.add(MarketFragment_.builder().build());
        // fragmentList.add(MarketFragment2_.builder().build());
        // fragmentList.add(HolderFragment_.builder().build());
        // fragmentList.add(ExchangeFragment_.builder().build());
        fragmentList.add(ExchangeFragment2_.builder().build());
        fragmentList.add(AssetFragment_.builder().build());
        fragmentList.add(MineFragment_.builder().build());
        showFragment(index);

    }

    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }


    @Override
    public void onBackPressed() {
        if (mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                long backPressed = System.currentTimeMillis();
                if (backPressed - lastBackPressed > QUIT_INTERVAL) {
                    lastBackPressed = backPressed;
                    Toast.makeText(this, getResources().getString(R.string.tab_main_back), Toast.LENGTH_LONG).show();
                } else {
                    finish();
                }
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //切入前台后关闭后台定位功能
        // if(null != locationClient) {
        //     locationClient.disableBackgroundLocation(true);
        //   }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NetEvent event) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CloseBean closeBean) {
        finish();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void toMarketFragEvent(ToMarketFragBean toMarketFragBean) {
        showFragment(Constants.INDEX_MARKET);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            mAMapLocationClientOption = null;

        }
    }


}
