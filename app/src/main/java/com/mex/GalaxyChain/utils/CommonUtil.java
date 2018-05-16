package com.mex.GalaxyChain.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;

/**
 * Created by LSJ on 18/3/6.
 */

public class CommonUtil {
	public static void showSimpleInfo(String msg) {
		int length = msg.length();
		if (length > 15) {
			Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
		}
	}

	public static void setMarketViewTextColor(TextView marketView, double checkValue) {
		int colorRes;
		if (checkValue > 0) {
			colorRes = R.color.market_green;
		} else if (checkValue == 0) {
			colorRes = R.color.font_white;
		} else {
			colorRes = R.color.market_red;
		}
		marketView.setTextColor(marketView.getContext().getResources().getColor(colorRes));
	}

	public static void menuDrawableRight(Context context, TextView oprView, View menuPanel, boolean panelOpen) {
		Drawable rightDrawable;
		if (panelOpen) {
			rightDrawable = context.getResources().getDrawable(R.drawable.icon_white_arrow_up);
			if (menuPanel != null) {
				menuPanel.setVisibility(View.VISIBLE);
			}
		} else {
			rightDrawable = context.getResources().getDrawable(R.drawable.icon_white_arrow_down);
			if (menuPanel != null) {
				menuPanel.setVisibility(View.GONE);
			}
		}
		rightDrawable.setBounds(0, 0, rightDrawable.getIntrinsicWidth(), rightDrawable.getIntrinsicHeight());
		oprView.setCompoundDrawables(null, null, rightDrawable, null);
	}
}
