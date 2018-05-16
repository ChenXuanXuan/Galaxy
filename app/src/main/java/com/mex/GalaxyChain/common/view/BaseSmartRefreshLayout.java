package com.mex.GalaxyChain.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;


/**
 * name：
 * describe:
 * author: LSJ
 * time 18/4/18 下午4:21
 */
public class BaseSmartRefreshLayout extends SmartRefreshLayout {
	private ClassicsFooter classicsFooter;
	private ClassicsHeader classicsHeader;

	public BaseSmartRefreshLayout(Context context) {
		super(context);
		initView();
	}

	public BaseSmartRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public BaseSmartRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		classicsFooter = new ClassicsFooter(getContext());
		classicsHeader = new ClassicsHeader(getContext());
		setDefaultLoadingHeaderView();
		setDefaultLoadingFooterView();
	}

	/**
	 * 设置默认头部
	 */
	public void setDefaultLoadingHeaderView() {
		setLoadingHeaderView(classicsHeader);
	}

	/**
	 * 设置头部HeaderView
	 */
	private void setLoadingHeaderView(RefreshHeader ptrUIHandler) {
		if (ptrUIHandler != null) {
			if (ptrUIHandler instanceof View) {
				setRefreshHeader(ptrUIHandler);
			} else {
				throw new UnsupportedOperationException("ptrUIHandler is not a View so can't setFooterView");
			}
		}

	}

	/**
	 * 设置默认底部
	 */
	public void setDefaultLoadingFooterView() {
		setLoadingFooterView(classicsFooter);
	}

	/**
	 * 设置底部FooterView
	 */
	public void setLoadingFooterView(RefreshFooter ptrUIHandler) {
		if (ptrUIHandler != null) {
			if (ptrUIHandler instanceof View) {
				setRefreshFooter(ptrUIHandler);
			} else {
				throw new UnsupportedOperationException("ptrUIHandler is not a View so can't setFooterView");
			}
		}
	}

}
