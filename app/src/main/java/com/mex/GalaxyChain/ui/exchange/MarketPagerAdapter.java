package com.mex.GalaxyChain.ui.exchange;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mex.GalaxyChain.common.BaseFragment;

import java.util.List;

/**
 * name：
 * describe:
 * author: LSJ
 * time 22/3/18 下午4:50
 */
public class MarketPagerAdapter<T extends BaseFragment & ITitleCreator> extends FragmentPagerAdapter {
    private final String[] titleTabArr;
    private List<T> fragments;

	public MarketPagerAdapter(FragmentManager fm, List<T> fragments,String[] titleTabArr) {
		super(fm);
		this.fragments = fragments;
		this.titleTabArr=titleTabArr;
	}

     

    @Override
	public T getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount(){
		      return     fragments.size();
	}

	public void setList(List<T> list) {
		this.fragments = list;
	}

	public List<T> getList() {
		return fragments;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return getItem(position).getPageTitle();
	}
}
