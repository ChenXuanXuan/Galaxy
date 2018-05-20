package com.mex.GalaxyChain.ui.market;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.adapter.viewpagerAdapter.IndicatorViewPagerAdapter;
import com.mex.GalaxyChain.common.BaseFragment;


import com.mex.GalaxyChain.view.magicindicator.MagicIndicator;
import com.mex.GalaxyChain.view.magicindicator.ViewPagerHelper;
import com.mex.GalaxyChain.view.magicindicator.buildins.UIUtil;
import com.mex.GalaxyChain.view.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.mex.GalaxyChain.view.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.mex.GalaxyChain.view.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.mex.GalaxyChain.view.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.mex.GalaxyChain.view.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.mex.GalaxyChain.view.magicindicator.buildins.commonnavigator.titles.ScaleTransitionPagerTitleView;
import com.mex.GalaxyChain.view.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LSJ on 18/3/4.
 * <p>
 * 行情页面
 */
@EFragment(R.layout.fragment_market)
public class MarketFragment extends BaseFragment {
	@ViewById
	MagicIndicator magic_indicator4;
	@ViewById
	 ViewPager vp_hq_fragment;
   // @ViewById
    //LazyViewPager  vp_hq_fragment;

	//private final String[] FRAGMENT_TITLES = new String[]{"全部品种", "全球指数", "加密货币"};
	String[] titleTabArr = {"全部品种", "全球指数", "加密货币"};
    List<BaseFragment> mFragmentList;
	//private List<String> titleList = Arrays.asList(titleTabArr);
    private IndicatorViewPagerAdapter mIndicatorViewPagerAdapter;

    @AfterViews
	void initView() {
		intThreeFragmentList();
		initViewpager();
		initMagicIndicator();
        initListener();
	}



    private void intThreeFragmentList() {
        mFragmentList = new ArrayList<>();
		AllVarietyFragment allVarietyFragment = AllVarietyFragment_.builder().build();

		GlobalIndexFragment globalIndexFragment = GlobalIndexFragment_.builder().build();
		CryptocurrencyFragment cryptocurrencyFragment = CryptocurrencyFragment_.builder().build();
		mFragmentList.add(allVarietyFragment);
		mFragmentList.add(globalIndexFragment);
        mFragmentList.add(cryptocurrencyFragment);


	}

	private void initViewpager() {
        mIndicatorViewPagerAdapter = new IndicatorViewPagerAdapter(getChildFragmentManager(), mFragmentList, titleTabArr);
		  vp_hq_fragment.setAdapter(mIndicatorViewPagerAdapter);
		  vp_hq_fragment.setOffscreenPageLimit(3);
		 vp_hq_fragment.setCurrentItem(0);
		 //额外添加的
        vp_hq_fragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                      for(int index = 0; index < mFragmentList.size(); index++ ){
                          if (index == position) {
                              //开启此当前的fragment的定时器
                              mFragmentList.get(index).onSelect();
                          }else{
                              //关闭其他页面fragment的定时器
                              mFragmentList.get(index).onLeave();
                          }
                      }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

	}

	private void initMagicIndicator() {
        magic_indicator4.setBackgroundColor(Color.TRANSPARENT);
		CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setScrollPivotX(0.8f);
		commonNavigator.setAdapter(new CommonNavigatorAdapter() {
			@Override
			public int getCount() {
				return mFragmentList.size();
			}

			@Override
			public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.light_gray));
                simplePagerTitleView.setTextSize(14);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.light_bule));
                simplePagerTitleView.setText(titleTabArr[index]);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp_hq_fragment.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
			}

			@Override
			public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setStartInterpolator(new AccelerateInterpolator());
                linePagerIndicator.setEndInterpolator(new DecelerateInterpolator(1.6f));
                linePagerIndicator.setLineHeight(UIUtil.dip2px(context, 1));
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResources().getColor(R.color.light_bule));
                linePagerIndicator.setYOffset(14);
				return linePagerIndicator;
			}
		});

		magic_indicator4.setNavigator(commonNavigator);
		ViewPagerHelper.bind(magic_indicator4, vp_hq_fragment);
	}

    private void initListener() {
        //需不需要对viewpage滑动进行监听,当滑动到某当前一个界面 开启此定时器  关闭其他页面定时器
        vp_hq_fragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) { }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });
    }





}
