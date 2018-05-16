package com.mex.GalaxyChain.net;


import com.mex.GalaxyChain.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by LSJ on 17/3/25.
 */

public class HttpClient {
    static HttpClient instance;
    Retrofit mRetrofit;

    private HttpClient() {
        createRetrofit();
    }

    private void createRetrofit() {


        mRetrofit = new Retrofit.Builder()
                .client(createHttpClient())
                .addConverterFactory(GsonDConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(UrlTools.getBaseHost())
                .build();


    }

    private OkHttpClient createHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(UrlTools.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(UrlTools.READ_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(UrlTools.WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new LogInterceptor());
        builder.addInterceptor(new HttpInterceptor());
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        return builder.build();
    }

    public static HttpClient getInstance() {
        if (instance == null) {
            instance = new HttpClient();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

}
