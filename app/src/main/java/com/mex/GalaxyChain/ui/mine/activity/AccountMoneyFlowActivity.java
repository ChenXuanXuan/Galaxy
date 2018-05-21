package com.mex.GalaxyChain.ui.mine.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.adapter.viewpagerAdapter.IndicatorViewPagerAdapter;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.ui.mine.fragment.AllFragment;
import com.mex.GalaxyChain.ui.mine.fragment.AllFragment_;
import com.mex.GalaxyChain.ui.mine.fragment.DrawOutFragment;
import com.mex.GalaxyChain.ui.mine.fragment.DrawOutFragment_;
import com.mex.GalaxyChain.ui.mine.fragment.InPutFragment;
import com.mex.GalaxyChain.ui.mine.fragment.InPutFragment_;
import com.mex.GalaxyChain.ui.mine.fragment.OpenPositionFragment;
import com.mex.GalaxyChain.ui.mine.fragment.OpenPositionFragment_;
import com.mex.GalaxyChain.ui.mine.fragment.SettleAccountsFragment;
import com.mex.GalaxyChain.ui.mine.fragment.SettleAccountsFragment_;
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
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_moneyflow)
public class AccountMoneyFlowActivity extends BaseActivity {

    @ViewById(R.id.mTitle)
    TextView mTitle;
    @ViewById(R.id.magic_indicator4)
    MagicIndicator magic_indicator4;
    @ViewById(R.id.vp_hq_fragment)
    ViewPager vp_hq_fragment;


    @ViewById(R.id.back)
    ImageView back;

    @Click(R.id.back)
    void onClick(View view){
         finish();
    }

    String[]  titleTabArr = {"全部","充值","提现","开仓","结算"};
    List<BaseFragment> mFragmentList;
    private IndicatorViewPagerAdapter mIndicatorViewPagerAdapter;

    @AfterViews
    void init(){
        mTitle.setText("资金明细");
        back.setVisibility(View.VISIBLE);
        intFragmentList();
        initViewpager();
        initMagicIndicator();
        initListener();
    }




    private void intFragmentList() {
         mFragmentList =new ArrayList<>();
        AllFragment allFragment = AllFragment_.builder().build();
        InPutFragment inPutFragment = InPutFragment_.builder().build();
        DrawOutFragment drawOutFragment = DrawOutFragment_.builder().build();
        OpenPositionFragment openPositionFragment =  OpenPositionFragment_.builder().build();
        SettleAccountsFragment settleAccountsFragment =  SettleAccountsFragment_.builder().build();
        mFragmentList.add(allFragment);
        mFragmentList.add(inPutFragment);
        mFragmentList.add(drawOutFragment);
        mFragmentList.add(openPositionFragment);
        mFragmentList.add(settleAccountsFragment);

    }
    private void  initViewpager(){
        mIndicatorViewPagerAdapter = new IndicatorViewPagerAdapter(getSupportFragmentManager(), mFragmentList, titleTabArr);
        vp_hq_fragment.setAdapter(mIndicatorViewPagerAdapter);
        vp_hq_fragment.setOffscreenPageLimit(5);
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



    private void initListener() {
    }

}
