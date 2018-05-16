package com.mex.GalaxyChain.data.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.mex.GalaxyChain.utils.Serialize;

import java.io.Serializable;

/**
 * Created by LSJ on 17/3/30.
 */

public class SharePStore {

	private static final String DEFAULT_SP_NAME = "def_sp";
	private static SharedPreferences sp;
	private static SharedPreferences.Editor editor;

	private SharePStore(Context context, String config) {
		Context appContext = context.getApplicationContext();
		String pre = appContext.getPackageName().replace(".", "_");
		sp = appContext.getSharedPreferences(pre + config, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public static SharePStore getStore(Context context, String config) {
		return new SharePStore(context, config);
	}

	public static SharePStore getDefaultStore(Context context) {
		return new SharePStore(context, DEFAULT_SP_NAME);
	}



	public void save(String key, int value) {
		sp.edit().putInt(key, value).apply();
	}

	public void save(String key, String value) {
		sp.edit().putString(key, value).apply();
	}

	public void save(String key, long value) {
		sp.edit().putLong(key, value).apply();
	}

	public void save(String key, double value) {
		sp.edit().putLong(key, Double.doubleToRawLongBits(value)).apply();
	}

	public void save(String key, float value) {
		sp.edit().putFloat(key, value).apply();
	}

	public void save(String key, boolean value) {
		sp.edit().putBoolean(key, value).apply();
	}

	public void save(String key, Serializable obj) {
		sp.edit().putString(key, Serialize.toString(obj)).apply();
	}

	public int getInt(String key, int defValue) {
		return sp.getInt(key, defValue);
	}

	public long getLong(String key, long defValue) {
		return sp.getLong(key, defValue);
	}

	public float getFloat(String key, float defValue) {
		return sp.getFloat(key, defValue);
	}

	public boolean getBoolean(String key, boolean defValue) {
		return sp.getBoolean(key, defValue);
	}

	public String getString(String key, String defValue) {
		return sp.getString(key, defValue);
	}

	public double getDouble(String key, double defValue) {
		if (!sp.contains(key)) {
			return defValue;
		} else {
			return Double.longBitsToDouble(sp.getLong(key, 0));
		}
	}

	public <T extends Serializable> T getObject(String key, Class<T> clazz) {
		String val = sp.getString(key, null);
		if (TextUtils.isEmpty(val)) {
			return null;
		} else {
			try {
				return Serialize.fromString(val, clazz);
			} catch (Exception e) {
				return null;
			}
		}
	}

	public void clear() {
		sp.edit().clear().apply();
	}

	public void remove(String key) {
		sp.edit().remove(key).apply();
	}
}
