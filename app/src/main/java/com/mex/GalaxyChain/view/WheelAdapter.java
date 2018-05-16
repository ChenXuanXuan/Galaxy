package com.mex.GalaxyChain.view;

/**
 * name：
 * describe:
 * author: LSJ
 * time 25/4/18 下午9:53
 */
public interface WheelAdapter {
	public int getItemsCount();

	public String getItem(int index);

	public int getMaximumLength();

	public String getCurrentId(int index);
}
