package com.mex.GalaxyChain.ui.mine.fragment;


import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.adapter.MoneyFlowAdapter;
import com.mex.GalaxyChain.bean.MoneyFlowBean;
import com.mex.GalaxyChain.bean.PayOutListBean;
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

import java.util.List;


/*
*  开仓
* */
@EFragment(R.layout.fragment_moneyflow)
public class OpenPositionFragment extends BaseFragment implements OnLoadmoreListener, OnRefreshListener {


    @ViewById(R.id.refreshLayout)
    BaseSmartRefreshLayout refreshLayout;

    @ViewById(R.id.noData)
    TextView noData;
    @ViewById(R.id.listView)
    ListView listView;


    int currentPage = 1;
    private MoneyFlowAdapter mMoneyFlowAdapter;
    private List<MoneyFlowBean.DataBean.ListBean> mListBeanList;

    @AfterViews
    void init() {
        mMoneyFlowAdapter = new MoneyFlowAdapter(getActivity());
        listView.setAdapter(mMoneyFlowAdapter);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
    }

    private void loadNetData(final int currentPage, int biztype) {
        LoadNetDataForMoneyFlowUtil.getMoneyFlowInstance().loadNetData(currentPage, biztype);
        LoadNetDataForMoneyFlowUtil.getMoneyFlowInstance().setLoadMeneyFlowCallBackListener(new LoadNetDataForMoneyFlowUtil.LoadMeneyFlowsSuccessCallBackListener() {
            @Override
            public void onSuccessCallBack(MoneyFlowBean moneyFlowBean,PayOutListBean payOutListBean) {
                refreshComplete();
                LogUtils.d("TAG-->成功回调&资金明细&开仓", moneyFlowBean.getData().getList().size() + new Gson().toJson(moneyFlowBean));
                MoneyFlowBean.DataBean dataBean = moneyFlowBean.getData();
                if (dataBean == null) return;
                mListBeanList = dataBean.getList();
                if (currentPage == 1) {
                    if (mListBeanList == null || mListBeanList.size() == 0) {
                        listView.setEmptyView(noData);
                    }
                    mMoneyFlowAdapter.setItems(mListBeanList);
                } else {
                    mMoneyFlowAdapter.addItems(mListBeanList);
                }

                refreshLayout.setLoadmoreFinished(mListBeanList == null || mListBeanList.size() == 0 || mListBeanList.size() < Constants.PAGESIZE);
            }

            @Override
            public void onFailtueCallBack() {
                refreshComplete();
                LogUtils.d("TAG-->失败回调&资金明细&开仓", "开仓");
            }
        });

    }


    private void refreshComplete() {
        dismissLoading();
        if (refreshLayout != null) {
            if (currentPage == 1) {
                refreshLayout.finishRefresh();
            } else {
                refreshLayout.finishLoadmore();
            }
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        currentPage++;
        loadNetData(currentPage, Constants.KAICHANG);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        currentPage = 1;
        loadNetData(currentPage, Constants.KAICHANG);
    }

    public void setRefresh() {
        if (refreshLayout != null)
            refreshLayout.autoRefresh();
    }

    public void finishRefresh() {
        if (refreshLayout != null)
            refreshLayout.finishRefresh();
    }
}
