package com.mex.GalaxyChain.ui.mine.activity;

import android.view.InputEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.UrlTools;
import com.mex.GalaxyChain.utils.LogUtils;

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
 *
 * 充值
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

@ViewById(R.id.back)
    ImageView back;

	@ViewById
	TextView mTitle;

	@AfterViews
	void  init(){
		mTitle.setText("充值");
		back.setVisibility(View.VISIBLE);
	}

	@Click({R.id.bibi, R.id.fabi,R.id.back})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.bibi://币币充值账户(H5)
				 // InpourChannelAct_.intent(this).start();
                 String url= UrlTools.H5_URL_BIBICHONGZHI+"?userId=10007&"+"token="+ UserGolbal.getInstance().getUserToken();
                LogUtils.d("TAG-->H5:URL",url);
                UIHelper.ToCoinsCoinsRechargeH5Activity(this,url);
				break;

			case R.id.fabi: //法币充值(H5)
				//InpourChannelAct_.intent(this).start();
                String url_fabi= UrlTools.H5_URL_FABICHONGZHI;
                UIHelper.ToFaBiRechargeH5Activity(this,url_fabi);
				break;
            case R.id.back:
                finish();
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
