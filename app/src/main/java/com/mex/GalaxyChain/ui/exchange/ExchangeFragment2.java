package com.mex.GalaxyChain.ui.exchange;


import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.HoldPositionBean;
import com.mex.GalaxyChain.bean.QuitEvent;
import com.mex.GalaxyChain.bean.eventbean.ToRefleshExchang2Bean;
import com.mex.GalaxyChain.bean.eventbean.TagBean;
import com.mex.GalaxyChain.bean.eventbean.ToMarketFragBean;
import com.mex.GalaxyChain.bean.eventbean.VarietyHoldPosiBean;
import com.mex.GalaxyChain.bean.requestbean.RequestTradeHomeBean;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.common.ConfigManager;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.common.view.BaseSmartRefreshLayout;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.DeviceUtil;
import com.mex.GalaxyChain.utils.LogUtils;
import com.mex.GalaxyChain.utils.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.NumberFormat;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * Created by LSJ on 18/3/4.
 * <p>
 * 持仓界面(已登陆)
 */
@EFragment(R.layout.fragment_exchange2)
public class ExchangeFragment2 extends BaseFragment {

    @ViewById
    BaseSmartRefreshLayout refreshLayout;

    @ViewById(R.id.listView)
    ListView listView;

    @ViewById(R.id.noData)
    LinearLayout noData;  //开创盈利


    @ViewById(R.id.tv_total_amount)
    TextView tv_total_amount; //总资金


    @ViewById(R.id.tv_total_float_lossprofit)
    TextView tv_total_float_lossprofit; //总浮动盈亏

    @ViewById(R.id.tv_canusedamount)
    TextView tv_canusedamount; //可用余额=总资金

    @ViewById(R.id.tv_post)
    TextView tv_post;//仓位

    @ViewById(R.id.tv_sart_profit)
    TextView tv_sart_profit;//开创盈利




     private TagBean tagBean;
    private VarietyHoldPosiBean mVarietyHoldPosiBean;

    @Click({R.id.tv_isLogined_jiesuan,R.id.tv_sart_profit})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_isLogined_jiesuan:
                gotoJieSuan();
                break;

            case R.id.tv_sart_profit:
               // ToastUtils.showTextInMiddle("HAHAHAHA");
               //    UIHelper.jumptoMainActivity(getActivity(),"");
                EventBus.getDefault().post(new ToMarketFragBean());
                break;

                }
    }

    SearchAdapter searchAdapter;
    //boolean isLogined = false;

    private void gotoJieSuan() {
      //  if (isLogined) { //已登陆 去结算界面
            UIHelper.toJieSuanActivity(getActivity());

        // } else { // 没有登陆  去登陆界面
            // 持仓未登录状态下  手机号登陆界面
          //  UIHelper.jumptoPhoneNumLoginActivity(getActivity(),"");
         //   isLogined = true;
        //}
    }


    @AfterViews
    void init() {
        mVarietyHoldPosiBean = ConfigManager.getVarietyHold();
        EventBus.getDefault().register(this);
        searchAdapter = new SearchAdapter(getActivity());
        listView.setAdapter(searchAdapter);
           showLoading(getString(R.string.loading));
          loadNetData(mVarietyHoldPosiBean);
          refreshLayout.setDefaultLoadingHeaderView();
           refreshLayout.setDefaultLoadingFooterView();
          refreshLayout.setPrimaryColorsId(R.color.white, R.color.month_view_gray);
          refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadNetData(mVarietyHoldPosiBean);
            }
        });

        /*refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });*/

        setOnItemClickForListView();
    }








    private void  loadNetData(final VarietyHoldPosiBean varietyHoldPosiBean) {
        if(UserGolbal.getInstance().locationSuccess()){
            RequestTradeHomeBean requestTradeHomeBean = new RequestTradeHomeBean();
            requestTradeHomeBean.setUsertoken(UserGolbal.getInstance().getUserToken());
            requestTradeHomeBean.setLatitude(UserGolbal.getInstance().getLatitude());
            requestTradeHomeBean.setLongitude(UserGolbal.getInstance().getLongitude());

            if(tagBean!=null) requestTradeHomeBean.setSymbol(tagBean.getSymbol());
            requestTradeHomeBean.setDeviceType(Constants.ANDROID);
            requestTradeHomeBean.setDevcieModel(Build.MODEL);
            requestTradeHomeBean.setChannelId(Constants.channelId);
            requestTradeHomeBean.setVersion(AppUtil.getAppVersionName(MyApplication.getInstance()));
            MyApplication instance = MyApplication.getInstance();
            String device_identifier = DeviceUtil.getUdid(instance);
            String deviceID= HttpInterceptor.silentURLEncode(device_identifier);
            requestTradeHomeBean.setDeviceId(deviceID);
            Gson gson = new Gson();
            String jsonStr = gson.toJson(requestTradeHomeBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
            UserRepo.getInstance().PostHoldPosition(requestBody)
                    .subscribe(new Subscriber<HoldPositionBean>() {
                        @Override
                        public void onCompleted() {}

                        @Override
                        public void onError(Throwable e) {
                            dismissLoading();
                            if (refreshLayout != null) refreshLayout.finishRefresh();//刷新完成

                        }

                        @Override
                        public void onNext(HoldPositionBean holdPositionBean) {
                              dismissLoading();
                            if(refreshLayout!=null) refreshLayout.finishRefresh();
                              if(holdPositionBean.getCode()==200){
                                  ToastUtils.showTextInMiddle(holdPositionBean.getMsg());
                                  HoldPositionBean.DataBean dataBean =holdPositionBean.getData();
                                  tv_total_amount.setText("总资金: "+dataBean.getTotalamount());//总资金

                                  tv_canusedamount.setText(String.valueOf(dataBean.getCanusedamount()));//可用余额
                                  NumberFormat mNumberFormat= MyApplication.getInstance().mNumberFormat;
                                  mNumberFormat.setMaximumFractionDigits(2);
                                  tv_total_float_lossprofit.setText(mNumberFormat.format(dataBean.getTotalprofit()));//总盈亏(总浮动盈亏)
                                  tv_post.setText(mNumberFormat.format((dataBean.getFrozenamout()/dataBean.getTotalamount())*100)+"%");//仓位= 冻结保证金/总资金*100%
                                if(dataBean==null){ return;}
                                List<HoldPositionBean.DataBean.ListBean>  listBeanList= dataBean.getList();

                                if(listBeanList==null||listBeanList.size()==0) {  //listBeanList 没有数据  暂无持仓
                                    listView.setEmptyView(noData);
                                    tv_total_amount.setText("总资金: "+0);//总资金
                                    tv_total_float_lossprofit.setText(0+"");//总盈亏(总浮动盈亏)
                                    tv_canusedamount.setText(0+"");//可用余额
                                    tv_post.setText(0+"");
                                }

                                 searchAdapter.setItems(listBeanList);
                                 searchAdapter.setItemData(varietyHoldPosiBean);;

                                }else{
                                ToastUtils.showTextInMiddle("获取持仓失败");
                             }

                             }
                    });


        }else{ //重新去请求经纬度 在进行赋值
            UserGolbal.getInstance().requestLocation();
        }







    }


    private void setOnItemClickForListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showTextInMiddle("持仓item"+position);
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
     public void onMoonEvent(QuitEvent event) {}



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefleshBean(ToRefleshExchang2Bean refleshBean) {
         loadNetData(ConfigManager.getVarietyHold());
         LogUtils.e("TAG-->持仓ExchangeFragment2 接收SearchAdapter发送平仓消息进行刷新");
    }
}

