package com.mex.GalaxyChain.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by LSJ on 18/4/5.
 */

public class ListviewInScrollView extends ListView {
	public ListviewInScrollView(Context context) {
		super(context);
	}

	public ListviewInScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListviewInScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
