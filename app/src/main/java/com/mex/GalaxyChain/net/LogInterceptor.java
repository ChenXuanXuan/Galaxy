package com.mex.GalaxyChain.net;

import com.mex.GalaxyChain.utils.LNLog;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by LSJ on 17/6/14.
 */
public class LogInterceptor implements Interceptor {
	@Override
	public Response intercept(Chain chain) throws IOException {
		okhttp3.Request request = chain.request();

		long t1 = System.nanoTime();
		LNLog.i("onRequest", String.format("Sending request %s on %s%n%s",
				request.url(), chain.connection(), request.headers()));

		Response response = chain.proceed(request);
		long t2 = System.nanoTime();

		LNLog.i("onResponse" , String.format("Received response for %s in %.1fms%n%s",
				response.request().url(), (t2 - t1) / 1e6d, response.headers()));
		return response;
	}
}
