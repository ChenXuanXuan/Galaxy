package com.mex.GalaxyChain.common.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.utils.DateUtils;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;


/**
 * Created by shangerle on 17/9/5.
 */

public class CommonPtrDefaultHeader extends LinearLayout implements PtrUIHandler {

	private ImageView mArrowImageView;
	private TextView mHintTextView;
	private TextView mDateTimeTextView;
	private ProgressBar mProgressBar;
	private View timeLayout;

	private RotateAnimation mFlipAnimation;
	private RotateAnimation mReverseFlipAnimation;

	public CommonPtrDefaultHeader(Context context) {
		this(context, null);
	}

	public CommonPtrDefaultHeader(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CommonPtrDefaultHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	private void initView(Context context) {
		View view = View.inflate(context, R.layout.xlistview_header, this);
		setGravity(Gravity.BOTTOM);

		mArrowImageView = (ImageView) view.findViewById(R.id.xlistview_header_arrow);
		mHintTextView = (TextView) view.findViewById(R.id.xlistview_header_hint_textview);
		mProgressBar = (ProgressBar) view.findViewById(R.id.xlistview_header_progressbar);
		timeLayout = view.findViewById(R.id.timeLayout);
		mDateTimeTextView = (TextView) view.findViewById(R.id.xlistview_header_time);
		mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		mFlipAnimation.setInterpolator(new LinearInterpolator());
		int mRotateAniTime = 150;
		mFlipAnimation.setDuration(mRotateAniTime);
		mFlipAnimation.setFillAfter(true);

		mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
		mReverseFlipAnimation.setDuration(mRotateAniTime);
		mReverseFlipAnimation.setFillAfter(true);
		resetView();
	}

	private void resetView() {
		hideRotateView();
		mProgressBar.setVisibility(INVISIBLE);
	}

	private void hideRotateView() {
		mArrowImageView.clearAnimation();
		mArrowImageView.setVisibility(INVISIBLE);
	}

	@Override
	public void onUIReset(PtrFrameLayout frame) {
		resetView();
	}

	@Override
	public void onUIRefreshPrepare(PtrFrameLayout frame) {
		mProgressBar.setVisibility(INVISIBLE);

		mArrowImageView.setVisibility(VISIBLE);
		mHintTextView.setVisibility(VISIBLE);
		mHintTextView.setText(getResources().getString(R.string.xlistview_header_hint_normal));

		if (mDateTimeTextView != null) {
			if (lastRefreshTime > 0) {
				timeLayout.setVisibility(VISIBLE);
				mDateTimeTextView.setText(DateUtils.getRefreshTime(lastRefreshTime));
			} else {
				timeLayout.setVisibility(GONE);
			}
		}
	}

	@Override
	public void onUIRefreshBegin(PtrFrameLayout frame) {
		hideRotateView();
		mProgressBar.setVisibility(VISIBLE);
		mHintTextView.setVisibility(VISIBLE);
		mHintTextView.setText(R.string.xlistview_header_hint_loading);
	}

	long lastRefreshTime;

	@Override
	public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
		hideRotateView();
		mProgressBar.setVisibility(INVISIBLE);
		mHintTextView.setVisibility(VISIBLE);
		mHintTextView.setText(getResources().getString(R.string.xlistview_header_hint_complete));
		lastRefreshTime = System.currentTimeMillis();
	}

	@Override
	public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
		final int mOffsetToRefresh = frame.getOffsetToRefresh();
		final int currentPos = ptrIndicator.getCurrentPosY();
		final int lastPos = ptrIndicator.getLastPosY();

		if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
			if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
				crossRotateLineFromBottomUnderTouch(frame);
				if (mArrowImageView != null) {
					mArrowImageView.clearAnimation();
					mArrowImageView.startAnimation(mReverseFlipAnimation);
				}
			}
		} else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
			if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
				crossRotateLineFromTopUnderTouch(frame);
				if (mArrowImageView != null) {
					mArrowImageView.clearAnimation();
					mArrowImageView.startAnimation(mFlipAnimation);
				}
			}
		}
	}

	private void crossRotateLineFromBottomUnderTouch(PtrFrameLayout frame) {
		mHintTextView.setVisibility(VISIBLE);
		mHintTextView.setText(getResources().getString(R.string.xlistview_header_hint_normal));
	}

	private void crossRotateLineFromTopUnderTouch(PtrFrameLayout frame) {
		if (!frame.isPullToRefresh()) {
			mHintTextView.setVisibility(VISIBLE);
			mHintTextView.setText(R.string.xlistview_header_hint_ready);
		}
	}
}
