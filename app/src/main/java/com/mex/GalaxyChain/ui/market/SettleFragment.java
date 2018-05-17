package com.mex.GalaxyChain.ui.market;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.adapter.SettleAdapter;
import com.mex.GalaxyChain.bean.TradeDetailBean;
import com.mex.GalaxyChain.bean.eventbean.VarietyHoldPosiBean;
import com.mex.GalaxyChain.bean.requestbean.RequestTradeDetailListBean;
import com.mex.GalaxyChain.common.BaseFragment;
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
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
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


//结算
@EFragment(R.layout.fragment_settle)
public class SettleFragment extends BaseFragment {


    @ViewById(R.id.refreshLayout)
    BaseSmartRefreshLayout refreshLayout;

    @ViewById(R.id.noData)
    TextView noData;
    @ViewById(R.id.listView)
    ListView listView;
    private SettleAdapter mSettleAdapter;




   @Click({R.id.tv_onceAgain})
   void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_onceAgain:
                onceAgain();
                break;

        }
    }

    private void onceAgain() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        //normalDialog.setIcon(R.drawable.icon_dialog);
        normalDialog.setTitle("再来一单");
        normalDialog.setMessage("交易费用,说明文字");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });

        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });

        normalDialog.show();
    }



     int currentPage=1;
    @AfterViews
    void init() {
        EventBus.getDefault().register(this);
        mSettleAdapter = new SettleAdapter(getActivity());
        listView.setAdapter(mSettleAdapter);
        setOnItemClickForListView();
        showLoading(getString(R.string.loading));
         currentPage=1;
         loadNetData(currentPage,null);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                currentPage=1;
                loadNetData(currentPage,null);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                currentPage++;
                loadNetData(currentPage,null);
            }
        });
        }

    private void setOnItemClickForListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    private void loadNetData(final int currentPage, final VarietyHoldPosiBean varietyHoldPosiBean) {

            if(UserGolbal.getInstance().locationSuccess()){
                RequestTradeDetailListBean   requestTradeDetailListBean = new  RequestTradeDetailListBean();
                requestTradeDetailListBean.setUsertoken(UserGolbal.getInstance().getUserToken());
                requestTradeDetailListBean.setLatitude(UserGolbal.getInstance().getLatitude());
                requestTradeDetailListBean.setLongitude(UserGolbal.getInstance().getLongitude());
                requestTradeDetailListBean.setPage(currentPage);
                requestTradeDetailListBean.setPagesize(Constants.PAGESIZE);
                requestTradeDetailListBean.setDeviceType(Constants.ANDROID);
                requestTradeDetailListBean.setDevcieModel(Build.MODEL);
                requestTradeDetailListBean.setChannelId(Constants.channelId);
                requestTradeDetailListBean.setVersion(AppUtil.getAppVersionName(MyApplication.getInstance()));
                MyApplication instance = MyApplication.getInstance();
                String device_identifier = DeviceUtil.getUdid(instance);
                String deviceID= HttpInterceptor.silentURLEncode(device_identifier);
                requestTradeDetailListBean.setDeviceId(deviceID);
                Gson gson = new Gson();
                String jsonStr = gson.toJson(requestTradeDetailListBean);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
                UserRepo.getInstance().PostTradeDetail(requestBody)
                        .subscribe(new Subscriber<TradeDetailBean>() {
                            @Override
                            public void onCompleted() {}

                            @Override
                            public void onError(Throwable e) {
                                refreshComplete();
                            }

                            @Override
                            public void onNext(TradeDetailBean tradeDetailBean) {
                                refreshComplete();
                                if(tradeDetailBean.getCode()==200){
                                    TradeDetailBean.DataBean dataBean=tradeDetailBean.getData();
                                    if(dataBean==null) return;
                                    List<TradeDetailBean.DataBean.ListBean> listBeanList= dataBean.getList();

                                    if(currentPage==1){ //加载第一页的数据
                                        if(listBeanList==null||listBeanList.size()==0){
                                            listView.setEmptyView(noData);
                                        }
                                        //setItems是把之前的数据清空了添加上去数据
                                        mSettleAdapter.setItems(listBeanList);

                                    }else{ // currentPage =2 3 加载第二 三页数据 到集合里
                                       //addItems是直接在原来数据后面添加上数据
                                        mSettleAdapter.addItems(listBeanList);
                                    }

                                    //mSettleAdapter.setItemData(varietyHoldPosiBean);

                                       //是否全部加载完毕     后台返回的集合为空  或size=0  或 最有一页返回的数据条数<15条 后台没有数据返回了
                                    refreshLayout.setLoadmoreFinished(listBeanList == null || listBeanList.size() == 0||listBeanList.size()<Constants.PAGESIZE);

                                }else{
                                    ToastUtils.showTextInMiddle(tradeDetailBean.getMsg());
                                }

                            }
                        });





            }else{
                UserGolbal.getInstance().requestLocation();
            }
    }

    private void refreshComplete() {
        dismissLoading();
         if(currentPage==1) refreshLayout.finishRefresh();
         else  refreshLayout.finishLoadmore();
    }





      @Subscribe(threadMode = ThreadMode.MAIN)
     public void getVarietyHoldPosi(VarietyHoldPosiBean varietyHoldPosiBean) {
         // loadNetData(currentPage,varietyHoldPosiBean);
          mSettleAdapter.setItemData(varietyHoldPosiBean);
          LogUtils.d("TAG-->结算:接收:VarietyHoldPosiBean",varietyHoldPosiBean.getHashMap().size()+"");
       }


}
