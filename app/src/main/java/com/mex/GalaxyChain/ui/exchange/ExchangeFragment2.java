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
import com.mex.GalaxyChain.bean.eventbean.TagBean;
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
import com.mex.GalaxyChain.utils.DecimalFormatUtils;
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

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 *
 * 持仓界面(已登陆)
 */
@EFragment(R.layout.fragment_exchange2)
public class ExchangeFragment2 extends BaseFragment implements OnRefreshListener {

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
    TextView tv_canusedamount; //可用余额

    @ViewById(R.id.tv_post)
    TextView tv_post;//仓位

    private HomeHeaderView headerView;


    private TagBean tagBean;
    private VarietyHoldPosiBean mVarietyHoldPosiBean;

    @Click({R.id.tv_isLogined_jiesuan})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_isLogined_jiesuan:
                gotoJieSuan();
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
        headerView = HomeHeaderView_.build(getActivity());
        listView.addHeaderView(headerView);
        mVarietyHoldPosiBean = ConfigManager.getVarietyHold();
        EventBus.getDefault().register(this);
        searchAdapter = new SearchAdapter(getActivity(), new SearchAdapter.setBack() {
            @Override
            public void back() {
                loadNetData(mVarietyHoldPosiBean);
            }
        });
        listView.setAdapter(searchAdapter);
        refreshLayout.setOnRefreshListener(this);
        //refreshLayout.autoRefresh();
        setOnItemClickForListView();
    }


    @Override
    public void onResume() {
        super.onResume();
        refreshLayout.autoRefresh();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            refreshLayout.autoRefresh();
        }
    }



    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        loadNetData(mVarietyHoldPosiBean);
    }



    private void loadNetData(final VarietyHoldPosiBean varietyHoldPosiBean) {
        if (UserGolbal.getInstance().locationSuccess()) {
            RequestTradeHomeBean requestTradeHomeBean = new RequestTradeHomeBean();
            // requestTradeHomeBean.setUsertoken(UserGolbal.getInstance().getUserToken());
            requestTradeHomeBean.setUsertoken(ConfigManager.getUserToken());
            requestTradeHomeBean.setLatitude(UserGolbal.getInstance().getLatitude());
            requestTradeHomeBean.setLongitude(UserGolbal.getInstance().getLongitude());

            if (tagBean != null) requestTradeHomeBean.setSymbol(tagBean.getSymbol());
            requestTradeHomeBean.setDeviceType(Constants.ANDROID);
            requestTradeHomeBean.setDevcieModel(Build.MODEL);
            requestTradeHomeBean.setChannelId(Constants.channelId);
            requestTradeHomeBean.setVersion(AppUtil.getAppVersionName(MyApplication.getInstance()));
            MyApplication instance = MyApplication.getInstance();
            String device_identifier = DeviceUtil.getUdid(instance);
            String deviceID = HttpInterceptor.silentURLEncode(device_identifier);
            requestTradeHomeBean.setDeviceId(deviceID);
            Gson gson = new Gson();
            String jsonStr = gson.toJson(requestTradeHomeBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
            UserRepo.getInstance().PostHoldPosition(requestBody)
                    .subscribe(new Subscriber<HoldPositionBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            dismissLoading();
                            if (refreshLayout != null) refreshLayout.finishRefresh();//刷新完成

                        }

                        @Override
                        public void onNext(HoldPositionBean holdPositionBean) {
                            dismissLoading();
                            if (refreshLayout != null) refreshLayout.finishRefresh();
                            if (holdPositionBean.getCode() == 200) {
                                LogUtils.d("TAG-->刷新持仓列表数据",holdPositionBean.getData().getList().size()+"");
                                HoldPositionBean.DataBean dataBean = holdPositionBean.getData();
                                tv_total_amount.setText("总资金: " + DecimalFormatUtils.getDecimal(dataBean.getTotalamount(),2));//总资金
                                tv_canusedamount.setText(String.valueOf(dataBean.getCanusedamount()));//可用余额
                                tv_total_float_lossprofit.setText(DecimalFormatUtils.getDecimal(dataBean.getTotalprofit(),2));//总盈亏(总浮动盈亏)
                                    //  仓位= 冻结保证金/( 冻结保证金+可用余额)*100%
                                tv_post.setText(DecimalFormatUtils.getDecimal(dataBean.getFrozenamout()/(dataBean.getFrozenamout()+dataBean.getCanusedamount())*100,2)+"%");
                                if (dataBean == null) {
                                    return;
                                }

                                List<HoldPositionBean.DataBean.ListBean> listBeanList = dataBean.getList();
                                if (listBeanList == null || listBeanList.size() == 0) {  //listBeanList 没有数据  暂无持仓
                                    headerView.bindView(true);

                                }else{
                                    headerView.bindView(false);
                                }
                                searchAdapter.setItems(listBeanList);
                                searchAdapter.setItemData(varietyHoldPosiBean);
                                } else {
                                ToastUtils.showTextInMiddle("获取持仓失败");
                            }

                        }
                    });


        } else { //重新去请求经纬度 在进行赋值
             UserGolbal.getInstance().requestLocation();
        }


    }


    private void setOnItemClickForListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showTextInMiddle("持仓item" + position);
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(QuitEvent event) {}


}

