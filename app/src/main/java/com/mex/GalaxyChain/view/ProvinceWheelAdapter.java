package com.mex.GalaxyChain.view;

import android.content.Context;

/**
 * name：
 * describe:
 * author: LSJ
 * time 26/4/18 上午12:55
 */
public class ProvinceWheelAdapter implements WheelAdapter {

//	private List<String> provinces;
	String [] provinces;

	public ProvinceWheelAdapter(Context context, String [] provinces) {
		this.provinces = provinces;
	}

	@Override
	public int getItemsCount() {
		return provinces == null ? 0 : provinces.length;
	}

	@Override
	public String getItem(int index) {
		return index <= provinces.length- 1 ? provinces[index] : null;
	}

	@Override
	public int getMaximumLength() {
		return 5;
	}

	@Override
	public String getCurrentId(int index) {
		return "";
	}

}

