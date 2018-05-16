package com.mex.GalaxyChain.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * name：
 * describe:
 * author: LSJ
 * time 21/4/18 下午7:26
 */
public class SystemUtil {
	public static void initAppLanguage(Context context) {
		if (context == null) {
			return;
		}
		Locale.setDefault(Locale.ENGLISH);
		Configuration config = context.getResources().getConfiguration();
		LogUtils.d("*************" + config.locale + "");
		config.locale = Locale.CHINA;
		context.getResources().updateConfiguration(config
				, context.getResources().getDisplayMetrics());
	}
}
