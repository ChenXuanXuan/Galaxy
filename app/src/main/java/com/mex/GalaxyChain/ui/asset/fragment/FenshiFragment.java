package com.mex.GalaxyChain.ui.asset.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.mychart.ChartConstant;
import com.mex.GalaxyChain.mychart.DataRequest;
import com.mex.GalaxyChain.mychart.bean.CMinute;
import com.mex.GalaxyChain.mychart.bean.FenshiDataResponse;
import com.mex.GalaxyChain.mychart.bean.FenshiParam;
import com.mex.GalaxyChain.mychart.bean.OminuteMsg;
import com.mex.GalaxyChain.mychart.chart.kchart.chart.KLineEntity;
import com.mex.GalaxyChain.mychart.obser.IObserver;
import com.mex.GalaxyChain.mychart.obser.ISubject;
import com.mex.GalaxyChain.mychart.utils.LruJsonCache;
import com.mex.GalaxyChain.mychart.view.CrossView;
import com.mex.GalaxyChain.mychart.view.FenshiView;
import com.mex.GalaxyChain.ui.asset.activity.LandMarketMainAct;
import com.mex.GalaxyChain.ui.asset.activity.MarketMainAct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LSJ on 2017/5/25.
 */
public class FenshiFragment extends LineBaseFragment implements IObserver, ChartConstant {
	public static final int REFRUSH_TIME = 1000;
	LruJsonCache lruJsonCache = new LruJsonCache();
	private FenshiView fenshiView;
	private CrossView crossView;
	//滑动十字线时，显示对应点详情的地方
	private TextView msgText, messageTwo;
	//分时数据
	private FenshiDataResponse data;
	private TextView time, price, rose, averagePrice;
	//是否全屏
	private boolean isPause;
	private OminuteMsg ctm;
	private MarketMainAct newMarketMainAct;
	private LandMarketMainAct landMarketMainAct;
	private LinearLayout llFenshi;
	private String selType = "0";
	/**
	 * 循环刷新界面
	 */
	private Handler refrushHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (isShow && !isPause) {
			}
			if (!isPause) {
				refrushHandler.sendEmptyMessageDelayed(0, REFRUSH_TIME);
			}
		}
	};

	public void setType(String instID, String type, String selType) {
		this.selType = selType;
		indexTab.setVisibility(View.GONE);//隐藏下指标
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		findViews();
		setData();
		fenshiView.setUsedViews(crossView, llFenshi, time, price, rose, averagePrice);
		indexTab.setOnTabSelectedListener(this);
		for (String s : INDEX_FENSHI_TAB) {
			indexTab.addTab(indexTab.newTab().setText(s));
		}
		initObserver(fenshiView);
	}

	private void findViews() {
		llFenshi = (LinearLayout) getView().findViewById((R.id.ll_fenshi));
		time = (TextView) getView().findViewById(R.id.time);
		price = (TextView) getView().findViewById(R.id.price);
		rose = (TextView) getView().findViewById(R.id.rose);
		averagePrice = (TextView) getView().findViewById(R.id.averagePrice);
		fenshiView = (FenshiView) getView().findViewById(R.id.cff_fenshiview);
		crossView = (CrossView) getView().findViewById(R.id.cff_cross);
		fenshiView = (FenshiView) getView().findViewById(R.id.cff_fenshiview);
		//addListener();
	}

	private void initObserver(ISubject isubject) {
		isubject.Aimat(this);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.achart_fenshi_frag, null);
	}

	private void setData() {
		List<KLineEntity> data = DataRequest.getData(getActivity(), 0, 600);
		FenshiParam fenshiParam = new FenshiParam();
		FenshiDataResponse fsResponse = new FenshiDataResponse();
		ArrayList<CMinute> list = new ArrayList<CMinute>();
		for (int i = 0; i < data.size(); i++) {
			CMinute cMinute = new CMinute();
			cMinute.setCount((long) data.get(i).getVolume());
			cMinute.setPrice(Double.parseDouble(data.get(i).getLowPrice() + ""));
			cMinute.setTime(data.get(i).getDatetime() + "");
			cMinute.setMoney((long)data.get(i).getVolume());
			list.add(cMinute);
		}
		fsResponse.setLastSettle(190.02f);
		fsResponse.setHigh(205.30f);
		fsResponse.setLow(70f);
		fsResponse.setData(list);
		fsResponse.setError_code("0");
		fsResponse.setSuccess(1);
		fenshiParam.setLast(100f);
		fenshiParam.setDuration("20:00-2:30|9:00-11:30|13:30-15:30");
		fenshiParam.setCount(1);
		fenshiParam.setLength(660);
		fenshiParam.setUntil(0);
		fsResponse.setParam(fenshiParam);
		fenshiView.setDataAndInvalidate(fsResponse);
	}

    /*private void addListener() {
		fenshiView.setOnDoubleTapListener(this); //双击 分时 全屏的监听事件
    }*/

	@Override
	public void onResume() {
		super.onResume();
		isPause = false;
		refrushHandler.sendEmptyMessageDelayed(0, REFRUSH_TIME);
	}

	@Override
	public void onPause() {
		super.onPause();
		isPause = true;
	}

	@Override
	public void onTabSelected(TabLayout.Tab tab) {
		switch (tab.getPosition()) {
			case INDEX_VOL:
				fenshiView.showVOL();
				break;
		}
	}

	@Override
	public void Response(int i) {
		if (i == 0) {//十字线显示
			if (newMarketMainAct != null)
				newMarketMainAct.setVisAndGone(0);
			if (newMarketMainAct != null)
				newMarketMainAct.setVisAndGone(0);
		} else {
			if (newMarketMainAct != null)
				newMarketMainAct.setVisAndGone(1);
			if (newMarketMainAct != null)
				newMarketMainAct.setVisAndGone(1);
		}
	}

	public void setObject(MarketMainAct newMarketMainAct) {
		this.newMarketMainAct = newMarketMainAct;
	}

	public void setLangObject(LandMarketMainAct landMarketMainAct) {
		this.landMarketMainAct = landMarketMainAct;
	}
}
