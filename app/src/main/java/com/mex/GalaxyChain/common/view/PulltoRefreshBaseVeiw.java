package com.mex.GalaxyChain.common.view;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;


/**
 * Created by shangerle on 17/9/5.
 */

public class PulltoRefreshBaseVeiw extends PtrClassicFrameLayout {

	private CommonPtrDefaultFooter defaultFooter;
	private CommonPtrDefaultHeader defaultHeader;

	public PulltoRefreshBaseVeiw(Context context) {
		this(context, null);
	}

	public PulltoRefreshBaseVeiw(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PulltoRefreshBaseVeiw(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		defaultHeader = new CommonPtrDefaultHeader(getContext());
		defaultFooter = new CommonPtrDefaultFooter(getContext());
		setDefaultLoadingHeaderView();
		setDefaultLoadingFooterView();
	}

	/**
	 * 设置默认头部
	 */
	public void setDefaultLoadingHeaderView() {
		setLoadingHeaderView(defaultHeader);
	}

	/**
	 * 设置默认底部
	 */
	public void setDefaultLoadingFooterView() {
		setLoadingFooterView(defaultFooter);
	}

	/**
	 * 设置头部HeaderView
	 */
	public void setLoadingHeaderView(PtrUIHandler ptrUIHandler) {
		if (ptrUIHandler != null) {
			if (ptrUIHandler instanceof View) {
				setHeaderView((View) ptrUIHandler);
				addPtrUIHandler(ptrUIHandler);
			} else {
				throw new UnsupportedOperationException("ptrUIHandler is not a View so can't setHeaderView");
			}
		}
	}

	/**
	 * 设置底部FooterView
	 */
	public void setLoadingFooterView(PtrUIHandler ptrUIHandler) {
		if (ptrUIHandler != null) {
			if (ptrUIHandler instanceof View) {
				setFooterView((View) ptrUIHandler);
				addPtrUIHandler(ptrUIHandler);
			} else {
				throw new UnsupportedOperationException("ptrUIHandler is not a View so can't setFooterView");
			}
		}
	}

	/**
	 * 设置footer没有更多时文案
	 *
	 * @param resId
	 */
	public void setNoMoreDataText(@StringRes int resId) {
		defaultFooter.setNoMoreDataText(resId);
	}

	public void setLastPage(boolean value) {
		defaultFooter.setLastPage(value);

	}

	public boolean isLastPage() {
		return defaultFooter.isLastPage();
	}
}
