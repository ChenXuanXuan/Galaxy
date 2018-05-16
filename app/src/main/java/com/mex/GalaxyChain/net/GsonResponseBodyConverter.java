package com.mex.GalaxyChain.net;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by LSJ on 17/9/15.
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
	private final Gson gson;
	private final Type type;

	GsonResponseBodyConverter(Gson gson, Type type) {
		this.gson = gson;
		this.type = type;
	}

	/**
	 * 针对数据返回成功、错误不同类型字段处理
	 */
	@Override
	public T convert(ResponseBody value) throws IOException {
		String response = value.string();
		if (TextUtils.isEmpty(response))
			return gson.fromJson(ReponseData.getParseErrObj(null), type);
		try {
			try {
				try {
					return gson.fromJson(response, type);
				} catch (JsonSyntaxException e) {
					return gson.fromJson(ReponseData.getParseErrObj(e.getMessage()), type);
				}
			} finally {
				value.close();
			}
		} finally {
			value.close();
		}
	}
}
