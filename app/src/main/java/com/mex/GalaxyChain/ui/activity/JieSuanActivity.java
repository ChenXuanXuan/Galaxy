package com.mex.GalaxyChain.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.adapter.viewpagerAdapter.IndicatorViewPagerAdapter;
import com.mex.GalaxyChain.bean.QuitEvent;
import com.mex.GalaxyChain.bean.eventbean.VarietyHoldPosiBean;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.ui.market.FlowSheetFragment;
import com.mex.GalaxyChain.ui.market.FlowSheetFragment_;
import com.mex.GalaxyChain.ui.market.SettleFragment;
import com.mex.GalaxyChain.ui.market.SettleFragment_;
import com.mex.GalaxyChain.utils.LogUtils;
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
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@EActivity(R.layout.activity_jiesuan)
public class JieSuanActivity extends BaseActivity {

    @ViewById(R.id.mTitle)
    TextView mTitle;
    @ViewById
    MagicIndicator magic_indicator4;
    @ViewById
    ViewPager vp_hq_fragment;

    String[]  titleTabArr = {"结算","流单"};
    private List<String> titleList = Arrays.asList(titleTabArr);
    List<BaseFragment> mFragmentList =new ArrayList<>();

    @AfterViews
    void initView(){
        EventBus.getDefault().register(this);
        intThreeFragmentList();
        initViewpager();
        initMagicIndicator();
         initViewData();


    }



    private void initViewData() {
        mTitle.setText("结算");
    }



    private void intThreeFragmentList() {
        SettleFragment settlement = SettleFragment_.builder().build();
        FlowSheetFragment flowSheetFragment  = FlowSheetFragment_.builder().build();
        mFragmentList.add(settlement);
        mFragmentList.add(flowSheetFragment);
    }


    private IndicatorViewPagerAdapter mIndicatorViewPagerAdapter;
    private void  initViewpager(){
        mIndicatorViewPagerAdapter = new IndicatorViewPagerAdapter(getSupportFragmentManager(), mFragmentList, titleTabArr);
        vp_hq_fragment.setAdapter(mIndicatorViewPagerAdapter);
        vp_hq_fragment.setOffscreenPageLimit(0); //我们的页面不能进行缓存
        vp_hq_fragment.setCurrentItem(0);
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





    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(QuitEvent event) {

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getVarietyHoldPosi(VarietyHoldPosiBean varietyHoldPosiBean) {
        // loadNetData(currentPage,varietyHoldPosiBean);
     //   mSettleAdapter.setItemData(varietyHoldPosiBean);
        LogUtils.d("TAG-->结算A:接收:VarietyHoldPosiBean",varietyHoldPosiBean.getHashMap().size()+"");
    }
}
