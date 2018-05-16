package com.mex.GalaxyChain.net;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by LSJ on 17/9/15.
 */
public final class GsonDConverterFactory extends Converter.Factory {

	public static GsonDConverterFactory create() {
		return create(new Gson());
	}

	public static GsonDConverterFactory create(Gson gson) {
		return new GsonDConverterFactory(gson);
	}

	private final Gson gson;

	private GsonDConverterFactory(Gson gson) {
		if (gson == null) throw new NullPointerException("gson == null");
		this.gson = gson;
	}

	//responseBodyConverter 返回请求结果的转换器
	@Override
	public Converter <ResponseBody, ?>responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		return new GsonResponseBodyConverter < >(gson, type);
	}

//requestBodyConverter 返回一个请求参数的转换器


}
