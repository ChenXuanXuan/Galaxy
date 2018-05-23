package com.mex.GalaxyChain.net;

import android.content.Context;

import com.mex.GalaxyChain.BuildConfig;

/**
 * Created by apple on 2015/11/25.
 */
public class UrlTools {

	private static final String HOST = "https://www.mex.link/api";//线上https
	public static final String DEBUG_HOST = "http://aaa.mex.group:81/exchange-api";//测试 http
  // public static final String DEBUG_HOST = "http://192.168.1.152:8080/exchange-api";//测试 http


    public static final String H5_URL_BIBICHONGZHI="http://yanshengpin.mex.group:82/recharge-account.html";
    public static final String H5_URL_FABICHONGZHI="http://yanshengpin.mex.group:82/currency-account.html";


	public static final long READ_TIMEOUT = 60;
	public static final long WRITE_TIMEOUT = 120;
	public static final long CONNECT_TIMEOUT = 30;
	public static   int NET_SUCCESS = 0;
	public static final int NET_UPDATE = 1004;
	public static final int ERR_CODE = 0;
	public static final String ERR_MSG = "ok";


//    private static final String HOST = "http://123.57.152.239:80";//线上

	public static String getHost() {
		if (BuildConfig.DEBUG) {
			return DEBUG_HOST;
		} else {
			return HOST;
		}
	}

	public static String getBaseHost() {
		StringBuilder sb = new StringBuilder("");
		sb.append(getHost()).append("/");
		return sb.toString();
	}

	public static String getUrl(Context context, int apiId, Object... formatArgs) {

		return getBaseHost() + String.format(context.getString(apiId), formatArgs);
	}


	public static String getImgAbsPath(String relativePath) {
		if (relativePath == null || relativePath.length() <= 0) {
			return null;
		}
//        StringBuilder sb = new StringBuilder("http://");
//        sb.append(context.getString(R.string.ip_addr));
//        sb.append(":");
//        sb.append(context.getResources().getInteger(R.integer.port));
		StringBuilder sb = new StringBuilder(getHost());
		if (!relativePath.startsWith("/")) {
			sb.append("/");
		}
		sb.append(relativePath);
		return sb.toString();
	}


	public static String getWebUrl() {
		StringBuilder sb = new StringBuilder("");
		sb.append(getHost()).append("/").append("views/html5page/");
		return sb.toString();
	}
}
