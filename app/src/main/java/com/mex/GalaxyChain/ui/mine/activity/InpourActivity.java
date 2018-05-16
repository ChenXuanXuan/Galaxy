package com.mex.GalaxyChain.ui.mine.activity;

import android.view.InputEvent;
import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * name：
 * describe:
 * author: LSJ
 * time 23/4/18 下午9:45
 */
@EActivity(R.layout.layout_inpour)
public class InpourActivity extends BaseActivity {

//	@ViewById
//	BaseSmartRefreshLayout refreshLayout;
//	@ViewById
//	ListView listView;
//	@ViewById
//	TextView noData;

//	private InPourAdapter topUpAdapter;
//	private int currentPageNo = 1;

	@ViewById
	TextView mTitle;

	@AfterViews
	void  init(){
		mTitle.setText("充值");
	}

	@Click({R.id.bibi, R.id.fabi})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.bibi:
				InpourChannelAct_.intent(this).start();
				break;

			case R.id.fabi:
				InpourChannelAct_.intent(this).start();
				break;
		}
	}

//	private void loadData() {
//		List<String> list = new ArrayList<>();
//		for (int i = 0; i < 2; i++) {
//			list.add("曾经沧海：" + i);
//		}
//		if (currentPageNo == 1 && list == null)
//			listView.setEmptyView(noData);
//
//		if (currentPageNo == 1)
//			topUpAdapter.setItems(list);
//		else
//			topUpAdapter.addItems(list);
//
//		refreshLayout.setLoadmoreFinished(currentPageNo == 5);
//
//	}

//	private void initView() {
//		topUpAdapter = new InPourAdapter(this);
//		refreshLayout.setOnRefreshListener(this);
//		refreshLayout.setOnLoadmoreListener(this);
//		listView.setAdapter(topUpAdapter);
//	}


	@Subscribe(threadMode = ThreadMode.MAIN)
	public void showDialog(InputEvent ex) {

	}
//
//	@Override
//	public void onLoadmore(RefreshLayout refreshlayout) {
//		currentPageNo++;
//		loadData();
//	}
//
//	@Override
//	public void onRefresh(RefreshLayout refreshlayout) {
//		currentPageNo = 1;
//		loadData();
//	}
}
