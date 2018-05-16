package com.mex.GalaxyChain.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.ui.market.entity.MarketSession;

import org.androidannotations.annotations.EViewGroup;

/**
 * des:市场列表
 * Created by LSJ on 18/3/10.
 */
@EViewGroup(R.layout.item_market_pair_view)
public class MarketPairItemView extends LinearLayout {

	public MarketPairItemView(Context context) {
		super(context);
	}

	public MarketPairItemView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MarketPairItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void bindView(MarketSession pairItem) {
	}
}
