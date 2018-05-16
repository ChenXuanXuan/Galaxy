package com.mex.GalaxyChain.adapter.viewpagerAdapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mex.GalaxyChain.common.BaseFragment;

import java.util.List;


public class IndicatorViewPagerAdapter  extends CacheFragmentStatePagerAdapter {

    private final List<BaseFragment> fragmentList;
    private final String[] titleTabArr;



    public IndicatorViewPagerAdapter(FragmentManager fm,List<BaseFragment> fragmentList, String[] titleTabArr ) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleTabArr=titleTabArr;
    }



   /* @Override
    public T getItem(int position) {
        return fragmentList.get(position);
    }*/

    @Override
    protected Fragment createItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //假数据 从后台获取
        return titleTabArr[position];
    }



}
