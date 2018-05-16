package com.mex.GalaxyChain.common;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LSJ on 18/3/9.
 */

public class TabLayout extends LinearLayout {
	private View lastTab;
	private Map<View, String> keyList = new HashMap<>();

	public TabLayout(Context context) {
		this(context, null);
	}

	public TabLayout(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	private int index;
	private int size;

	@Override
	public void removeAllViews() {
		super.removeAllViews();
		index = 0;
		size = 0;
	}

	public void addView(String key, View child) {
		super.addView(child);
		child.setTag(size);
		keyList.put(child, key);
		child.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				lastTab.setSelected(false);
				v.setSelected(true);
				index = (int) v.getTag();
				onTabChanged(keyList.get(v));
				lastTab = v;
			}
		});
		if (index == size) {
			lastTab = child;
		}
		size++;
	}

	private String getTabKey(int index) {
		return null;
	}

	public void initStatus() {
		showTabStatu();
	}

	private void showTabStatu() {
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			getChildAt(i).setSelected(i == index);
			if (i == index) {
				lastTab = getChildAt(i);
			}
		}
	}

	private ITabChangeListener tabChangeListener;

	public void setTabChangeListener(ITabChangeListener tabChangeListener) {
		this.tabChangeListener = tabChangeListener;
	}

	public void setIndex(int i) {
		index = i;
		showTabStatu();
	}

	public interface ITabChangeListener {

		void onTabChanged(String key);
	}

	private void onTabChanged(String key) {
		if (tabChangeListener != null) {
			tabChangeListener.onTabChanged(key);
		}
	}


}
