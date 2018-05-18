package com.mex.GalaxyChain.ui.asset.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.QuitEvent;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.mychart.fragment.NewKLineFragment;
import com.mex.GalaxyChain.ui.asset.fragment.FenshiFragment;
import com.mex.GalaxyChain.view.IndexViewPager;
import com.mex.GalaxyChain.view.CustomWheelView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.inflate;

/**
 * @author LSJ
 * @Description:
 * @date 2017/3/16
 * @time 14:40
 */
public class LandMarketMainAct extends BaseActivity implements View.OnClickListener,
		PopupWindow.OnDismissListener {
	protected String[] mProvinceDatas;
	private TextView fenshi, days, week, month, min;
	private PopupWindow pwMyPopWindow;
	private String type = "1";
	private String typeKx = "0";
	private String instID = "Au(T+D)";
	private IndexViewPager mPaper;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();
	private FenshiFragment fsFragmet;
	private NewKLineFragment klFragment;
	private MyCount mc = new MyCount(10000, 1000);
	private int isRequest = 0;
	private boolean isVisible;
	private LinearLayout llInditor;
	private String selType = "0";//0：分时 1：两日 2：日K...
	private Dialog dialog;
	private TextView stopLoss, exchange;
	private Dialog wheelDialog;
	private CustomWheelView wheelView;
	private String value;
	private TextView makeMore,makeEmpty;

	public static void launch(Context mContext) {
		Intent intent = new Intent(mContext, LandMarketMainAct.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	/**
	 * i:根据十字线判断导航栏是否隐藏
	 * 0：隐藏
	 * 1：显示
	 */
	public void setVisAndGone(int i) {
		if (i == 0) {
			llInditor.setVisibility(GONE);
		} else {
			llInditor.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

			//case R.id.makeMore://做多
			//	break;

			//case R.id.makeEmpty://做空
			//	break;

			case R.id.fenshi:
				type = "1";
				selType = "0";
				fsFragmet.setType(instID, type, selType);
				changeType(1);
				break;

			case R.id.days:
				typeKx = "6";
				selType = "2";
				klFragment.setType(instID, typeKx, selType,"","");
				changeType(3);
				break;

			case R.id.week:
				typeKx = "7";
				selType = "3";
				klFragment.setType(instID, typeKx, selType,"","");
				changeType(4);
				break;

			case R.id.month:
				typeKx = "8";
				selType = "4";
				klFragment.setType(instID, typeKx, selType,"","");
				changeType(5);
				break;

			case R.id.min:
				showPopu();
				break;

			case R.id.minutes1://240分钟
				typeKx = "5";
				selType = "5";
				klFragment.setType(instID, typeKx, selType,"","");
				changeMin(1);
				break;

			case R.id.minutes2://60分钟
				typeKx = "4";
				selType = "6";
				klFragment.setType(instID, typeKx, selType,"","");
				changeMin(2);
				break;

			case R.id.minutes3://30分钟
				changeMin(3);
				typeKx = "3";
				selType = "7";
				klFragment.setType(instID, typeKx, selType,"","");
				break;

			case R.id.minutes4://15分钟
				changeMin(4);
				typeKx = "2";
				selType = "8";
				klFragment.setType(instID, typeKx, selType,"","");
				break;


		}
	}

	private void changeType(int i) {
		Drawable arrow = ContextCompat.getDrawable(getActivity(), R.drawable.arrow);
		arrow.setBounds(0, 0, arrow.getMinimumWidth(), arrow.getMinimumHeight());
		Drawable market_pre = ContextCompat.getDrawable(getActivity(), R.drawable.market_pre);
		market_pre.setBounds(0, 0, market_pre.getMinimumWidth(), market_pre.getMinimumHeight());
		Drawable market_nor = ContextCompat.getDrawable(getActivity(), R.drawable.market_nor);
		market_nor.setBounds(0, 0, market_nor.getMinimumWidth(), market_nor.getMinimumHeight());

		switch (i) {
			case 1:
				mPaper.setCurrentItem(0);
				min.setText("分钟");
				fenshi.setTextColor(Color.parseColor("#ea3e3e"));
				days.setTextColor(Color.parseColor("#333333"));
				week.setTextColor(Color.parseColor("#333333"));
				month.setTextColor(Color.parseColor("#333333"));
				min.setTextColor(Color.parseColor("#333333"));
				fenshi.setCompoundDrawables(null, null, null, market_pre);
				days.setCompoundDrawables(null, null, null, market_nor);
				week.setCompoundDrawables(null, null, null, market_nor);
				month.setCompoundDrawables(null, null, null, market_nor);
				min.setCompoundDrawables(null, null, arrow, market_nor);
				break;

			case 2:
				mPaper.setCurrentItem(0);
				min.setText("分钟");
				fenshi.setTextColor(Color.parseColor("#333333"));
				days.setTextColor(Color.parseColor("#333333"));
				week.setTextColor(Color.parseColor("#333333"));
				month.setTextColor(Color.parseColor("#333333"));
				min.setTextColor(Color.parseColor("#333333"));
				fenshi.setCompoundDrawables(null, null, null, market_nor);
				days.setCompoundDrawables(null, null, null, market_nor);
				week.setCompoundDrawables(null, null, null, market_nor);
				month.setCompoundDrawables(null, null, null, market_nor);
				min.setCompoundDrawables(null, null, arrow, market_nor);
				break;

			case 3:
				mPaper.setCurrentItem(1);
				min.setText("分钟");
				fenshi.setTextColor(Color.parseColor("#333333"));
				days.setTextColor(Color.parseColor("#ea3e3e"));
				week.setTextColor(Color.parseColor("#333333"));
				month.setTextColor(Color.parseColor("#333333"));
				min.setTextColor(Color.parseColor("#333333"));
				fenshi.setCompoundDrawables(null, null, null, market_nor);
				days.setCompoundDrawables(null, null, null, market_pre);
				week.setCompoundDrawables(null, null, null, market_nor);
				month.setCompoundDrawables(null, null, null, market_nor);
				min.setCompoundDrawables(null, null, arrow, market_nor);
				break;

			case 4:
				mPaper.setCurrentItem(1);
				min.setText("分钟");
				fenshi.setTextColor(Color.parseColor("#333333"));
				days.setTextColor(Color.parseColor("#333333"));
				week.setTextColor(Color.parseColor("#ea3e3e"));
				month.setTextColor(Color.parseColor("#333333"));
				min.setTextColor(Color.parseColor("#333333"));
				fenshi.setCompoundDrawables(null, null, null, market_nor);
				days.setCompoundDrawables(null, null, null, market_nor);
				week.setCompoundDrawables(null, null, null, market_pre);
				month.setCompoundDrawables(null, null, null, market_nor);
				min.setCompoundDrawables(null, null, arrow, market_nor);
				break;

			case 5:
				mPaper.setCurrentItem(1);
				min.setText("分钟");
				fenshi.setTextColor(Color.parseColor("#333333"));
				days.setTextColor(Color.parseColor("#333333"));
				week.setTextColor(Color.parseColor("#333333"));
				month.setTextColor(Color.parseColor("#ea3e3e"));
				min.setTextColor(Color.parseColor("#333333"));
				fenshi.setCompoundDrawables(null, null, null, market_nor);
				days.setCompoundDrawables(null, null, null, market_nor);
				week.setCompoundDrawables(null, null, null, market_nor);
				month.setCompoundDrawables(null, null, null, market_pre);
				min.setCompoundDrawables(null, null, arrow, market_nor);
				break;

		}
	}

	private void showPopu() {
		if (pwMyPopWindow.isShowing()) {
			pwMyPopWindow.dismiss();
		} else {
			int xpos = getActivity().getWindowManager().getDefaultDisplay().getWidth() - pwMyPopWindow.getWidth() - 10;
			pwMyPopWindow.showAsDropDown(min, xpos, 0);// 显示
			backgroundAlpha();
		}
	}

	private void changeMin(int i) {
		pwMyPopWindow.dismiss();
		mPaper.setCurrentItem(1);
		switch (i) {
			case 1:
				min.setText("5分钟");
				break;

			case 2:
				min.setText("15分钟");
				break;

			case 3:
				min.setText("30分钟");
				break;

			case 4:
				min.setText("60分钟");
				break;

		}

		Drawable arrow = ContextCompat.getDrawable(getActivity(), R.drawable.arrow);
		arrow.setBounds(0, 0, arrow.getMinimumWidth(), arrow.getMinimumHeight());
		Drawable market_pre = ContextCompat.getDrawable(getActivity(), R.drawable.market_pre);
		market_pre.setBounds(0, 0, market_pre.getMinimumWidth(), market_pre.getMinimumHeight());
		Drawable market_nor = ContextCompat.getDrawable(getActivity(), R.drawable.market_nor);
		market_nor.setBounds(0, 0, market_nor.getMinimumWidth(), market_nor.getMinimumHeight());
		min.setTextColor(Color.parseColor("#ea3e3e"));
		days.setTextColor(Color.parseColor("#333333"));
		week.setTextColor(Color.parseColor("#333333"));
		month.setTextColor(Color.parseColor("#333333"));
		fenshi.setTextColor(Color.parseColor("#333333"));
		fenshi.setCompoundDrawables(null, null, null, market_nor);
		days.setCompoundDrawables(null, null, null, market_nor);
		week.setCompoundDrawables(null, null, null, market_nor);
		month.setCompoundDrawables(null, null, null, market_nor);
		min.setCompoundDrawables(null, null, arrow, market_pre);
	}


	public void backgroundAlpha() {
		ColorDrawable cd = new ColorDrawable(0x000000);
		pwMyPopWindow.setBackgroundDrawable(cd);
		WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
		lp.alpha = 0.4f;
		getActivity().getWindow().setAttributes(lp);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.land_anew_market_main);
		initView();
		initData();
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onPause() {
		if (mc != null) {
			mc.cancel();
		}
		super.onPause();
	}

	private void initView() {
		mPaper = (IndexViewPager) findViewById(R.id.view_pager);
		mPaper.setScanScroll(false);
		llInditor = (LinearLayout) findViewById(R.id.ll_inditor);
		initOthreView();
		initFragment();
	}

	public void initData() {}

	private void initOthreView() {
		fenshi = (TextView) findViewById(R.id.fenshi);
		days = (TextView) findViewById(R.id.days);
		week = (TextView) findViewById(R.id.week);
		month = (TextView) findViewById(R.id.month);
		min = (TextView) findViewById(R.id.min);
		//makeMore = (TextView) findViewById(R.id.makeMore);
	//	makeEmpty = (TextView) findViewById(R.id.makeEmpty);
		fenshi.setOnClickListener(this);
		days.setOnClickListener(this);
		week.setOnClickListener(this);
		month.setOnClickListener(this);
		min.setOnClickListener(this);
	//	makeMore.setOnClickListener(this);
		//makeEmpty.setOnClickListener(this);
		initPopu();
	}

	private void initFragment() {
		fsFragmet = new FenshiFragment();
		klFragment = new NewKLineFragment();
		klFragment.setLangObject(LandMarketMainAct.this);
		fsFragmet.setLangObject(LandMarketMainAct.this);

		mFragments.add(fsFragmet);
		mFragments.add(klFragment);

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int position) {
				return mFragments.get(position);
			}
		};
		mPaper.setAdapter(mAdapter);
		mPaper.setOffscreenPageLimit(4);
	}

	private void initPopu() {
		View view = inflate(getActivity(), R.layout.account_min_popu, null);
		pwMyPopWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
		pwMyPopWindow.setBackgroundDrawable(new BitmapDrawable());
		pwMyPopWindow.setOutsideTouchable(true);
		pwMyPopWindow.setFocusable(true);
		pwMyPopWindow.update();
		pwMyPopWindow.setOnDismissListener(this);
		pwMyPopWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
		pwMyPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

		TextView minutes1 = (TextView) view.findViewById(R.id.minutes1);
		TextView minutes2 = (TextView) view.findViewById(R.id.minutes2);
		TextView minutes3 = (TextView) view.findViewById(R.id.minutes3);
		TextView minutes4 = (TextView) view.findViewById(R.id.minutes4);

		minutes1.setOnClickListener(this);
		minutes2.setOnClickListener(this);
		minutes3.setOnClickListener(this);
		minutes4.setOnClickListener(this);

	}

	@Override
	public void onDismiss() {
		WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
		lp.alpha = 1f;
		getActivity().getWindow().setAttributes(lp);
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMoonEvent(QuitEvent event) {

	}


	class MyCount extends CountDownTimer {
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {

		}

		@Override
		public void onFinish() {
			if (isRequest == 0 && mc != null) {
				mc.start();
			}
		}
	}

}
