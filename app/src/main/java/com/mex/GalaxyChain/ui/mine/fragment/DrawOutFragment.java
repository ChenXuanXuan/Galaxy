package com.mex.GalaxyChain.ui.mine.fragment;

import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.adapter.DrawOutAdapter;
import com.mex.GalaxyChain.bean.MoneyFlowBean;
import com.mex.GalaxyChain.bean.PayOutListBean;
import com.mex.GalaxyChain.bean.eventbean.RefleshDrawOutBean;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.view.BaseSmartRefreshLayout;
import com.mex.GalaxyChain.utils.LoadNetDataForMoneyFlowUtil;
import com.mex.GalaxyChain.utils.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/*
*
* 提现
*
* */
@EFragment(R.layout.fragment_moneyflow)
public class DrawOutFragment extends BaseFragment implements OnLoadmoreListener,OnRefreshListener{



    @ViewById(R.id.refreshLayout)
    BaseSmartRefreshLayout refreshLayout;

    @ViewById(R.id.noData)
    TextView noData;
    @ViewById(R.id.listView)
    ListView listView;


    int currentPage = 1;
    //private MoneyFlowAdapter mMoneyFlowAdapter;

   // private List<MoneyFlowBean.DataBean.ListBean> mListBeanList;
    private  List<PayOutListBean.DataBean.ListBean> mListBeanList;
    private DrawOutAdapter mDrawOutAdapter;

    // PayOutListBean
    @AfterViews
    void init() {

            EventBus.getDefault().register(this);
           // mMoneyFlowAdapter = new MoneyFlowAdapter(getActivity());
        mDrawOutAdapter = new DrawOutAdapter(getActivity());
        listView.setAdapter(mDrawOutAdapter);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.autoRefresh();//我们让提现也自动刷新 一进入此界面
    }


    private void loadNetData(final int currentPage, int biztype) {
        LoadNetDataForMoneyFlowUtil.getMoneyFlowInstance().loadNetData(currentPage, biztype);
        LoadNetDataForMoneyFlowUtil.getMoneyFlowInstance().setLoadMeneyFlowCallBackListener(new LoadNetDataForMoneyFlowUtil.LoadMeneyFlowsSuccessCallBackListener() {
            //MoneyFlowBean moneyFlowBean 不需要  PayOutListBean 是提现记录的回调数据
            @Override
            public void onSuccessCallBack(MoneyFlowBean moneyFlowBean, PayOutListBean payOutListBean) {
                refreshComplete();
                LogUtils.d("TAG-->成功回调&资金明细&提现", payOutListBean.getData().getList().size()+new Gson().toJson(payOutListBean));
                PayOutListBean.DataBean dataBean=payOutListBean.getData();
                if(dataBean==null) return;
                mListBeanList = dataBean.getList();
                if(currentPage==1){
                    if(mListBeanList ==null|| mListBeanList.size()==0){
                        listView.setEmptyView(noData);
                    }
                    mDrawOutAdapter.setItems(mListBeanList);
                }else{
                    mDrawOutAdapter.addItems(mListBeanList);
                }

                refreshLayout.setLoadmoreFinished(mListBeanList == null || mListBeanList.size() == 0||mListBeanList.size()<Constants.PAGESIZE);
            }


            @Override
            public void onFailtueCallBack() {
                refreshComplete();
                LogUtils.d("TAG-->失败回调&资金明细&提现", "提现");
            }
        });

    }



    private void refreshComplete() {
        dismissLoading();
        if(refreshLayout != null){
            if(currentPage==1){
                refreshLayout.finishRefresh();
            }else{
                refreshLayout.finishLoadmore();
            }
        }
    }



    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        currentPage++;
        loadNetData(currentPage,Constants.TIXIAN);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        currentPage=1;
        loadNetData(currentPage,Constants.TIXIAN);
    }

    public void setRefresh() {
        if (refreshLayout!=null)
            refreshLayout.autoRefresh();
    }

    public void finishRefresh() {
        if (refreshLayout!=null)
            refreshLayout.finishRefresh();
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(RefleshDrawOutBean refleshDrawOutBean) {
        setRefresh();
        }

}
