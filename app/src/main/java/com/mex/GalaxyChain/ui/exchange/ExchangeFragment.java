package com.mex.GalaxyChain.ui.exchange;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.QuitEvent;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.common.view.BaseSmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LSJ on 18/3/4.
 *
 * 持仓界面
 */
@EFragment(R.layout.fragment_exchange)
public class ExchangeFragment extends BaseFragment implements OnRefreshListener, OnLoadmoreListener {

	@ViewById
	BaseSmartRefreshLayout refreshLayout;

	@ViewById
	ListView listView;

	@ViewById
	LinearLayout noData;

	private int page = 1;
	private SearchAdapter searchAdapter;
    @Click({ R.id.tv_isLogined_jiesuan })
    void onClick(View view) {
        switch (view.getId()) {
           // case R.id.tv_Login:
                // 持仓未登录状态下  手机号登陆界面
                //   UIHelper.jumptoPhoneNumLoginActivity(getActivity());
            //    break;
            case R.id.tv_isLogined_jiesuan:
                gotoJieSuan();
                break;

        }
    }


    boolean isLogined=false;
    private void gotoJieSuan() {

        if(isLogined){ //已登陆 去结算界面
            UIHelper.toJieSuanActivity(getActivity());

        }else{ // 没有登陆  去登陆界面
            // 持仓未登录状态下  手机号登陆界面
            UIHelper.jumptoPhoneNumLoginActivity(getActivity(),"");
                 isLogined=true;
        }
    }




    @AfterViews
	void init() {
		searchAdapter = new SearchAdapter(getActivity());
		initData(page);
		initView();
	}

	private void initData(int page) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add("" + i);
		}
		if (page == 1 && list.size()==0){
			noData.setVisibility(View.VISIBLE);
			listView.setEmptyView(noData);
		}

		//if (page == 1)
		 //	searchAdapter.setItems(list);
		//else
		 // 	searchAdapter.addItems(list);

		if (page == 5)
			refreshLayout.setLoadmoreFinished(true);
		else
			refreshLayout.setLoadmoreFinished(false);
		refreshComplete();
	}

	private void initView() {
		listView.setAdapter(searchAdapter);
		//全屏水滴
		//refreshLayout.setRefreshHeader();  //new PhoenixHeader(getActivity())
		//refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setDefaultLoadingHeaderView();
        refreshLayout.setDefaultLoadingFooterView();
		refreshLayout.setPrimaryColorsId(R.color.white, R.color.btn_bg_blue);
		refreshLayout.setOnRefreshListener(this);
		refreshLayout.setOnLoadmoreListener(this);
	}

	private void refreshComplete() {
		if (refreshLayout != null) {
			if (page == 1)
				refreshLayout.finishRefresh();
			else
				refreshLayout.finishLoadmore();
		}
	}

	@Override
	public void onLoadmore(RefreshLayout refreshlayout) {
		page++;
		initData(page);
	}

	@Override
	public void onRefresh(RefreshLayout refreshlayout) {
		page = 1;
		initData(page);
	}


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(QuitEvent event) {

    }



}

