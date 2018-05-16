package com.mex.GalaxyChain.common.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mex.GalaxyChain.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by shangerle on 17/9/5.
 */

public class CommonPtrDefaultFooter extends LinearLayout implements PtrUIHandler {

	private int noMoreResId = R.string.lbl_pull_refresh_load_over;
	private boolean lastPage = false;
	private View mContentView;
	private View mProgressBar;

	private TextView mHintView;

	private String defaultDesc;

	public CommonPtrDefaultFooter(Context context) {
		this(context, null);
	}

	public CommonPtrDefaultFooter(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CommonPtrDefaultFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	private void initView(Context context) {
		View view = View.inflate(context, R.layout.xlistview_footer, this);
		mContentView = view.findViewById(R.id.xlistview_footer_content);
		mProgressBar = view.findViewById(R.id.xlistview_footer_progressbar);
		mHintView = (TextView) view.findViewById(R.id.xlistview_footer_hint_textview);
		resetView();
	}

	private void resetView() {
		mProgressBar.setVisibility(INVISIBLE);
		mHintView.setVisibility(VISIBLE);
	}

	@Override
	public void onUIReset(PtrFrameLayout frame) {
		resetView();
		mHintView.setText(getDefaultDesc());
	}

	@Override
	public void onUIRefreshPrepare(PtrFrameLayout frame) {
		mHintView.setText(getDefaultDesc());
	}

	@Override
	public void onUIRefreshBegin(PtrFrameLayout frame) {
		if (isLastPage()) return;
		mProgressBar.setVisibility(VISIBLE);
		mHintView.setVisibility(GONE);
	}

	@Override
	public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
	}

	@Override
	public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
		final int mOffsetToRefresh = frame.getOffsetToRefresh();
		final int currentPos = ptrIndicator.getCurrentPosY();
		final int lastPos = ptrIndicator.getLastPosY();

		if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
			if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
				crossRotateLineFromBottomUnderTouch(frame);
			}
		} else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
			if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
				crossRotateLineFromTopUnderTouch(frame);
			}
		}
	}

	private void crossRotateLineFromTopUnderTouch(PtrFrameLayout frame) {
		if (isLastPage()) return;
		if (!frame.isPullToRefresh()) {
			mHintView.setVisibility(VISIBLE);
			mHintView.setText(getResources().getString(R.string.xlistview_footer_hint_ready));
		}
	}

	private void crossRotateLineFromBottomUnderTouch(PtrFrameLayout frame) {
		mHintView.setVisibility(VISIBLE);
		mHintView.setText(getDefaultDesc());
	}

	@NonNull
	private String getDefaultDesc() {
		int resId = R.string.xlistview_footer_hint_normal;
		if (lastPage && noMoreResId > 0) {
			resId = noMoreResId;
		}
		return getResources().getString(resId);
	}

	public void setNoMoreDataText(@StringRes int resId) {
		noMoreResId = resId;
	}

	public void setLastPage(boolean value) {
		lastPage = value;
	}

	public boolean isLastPage() {
		return lastPage;
	}
}
