package com.mex.GalaxyChain.net;


import com.mex.GalaxyChain.utils.LNLog;
import com.mex.GalaxyChain.utils.Strings;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LSJ on 17/3/26.
 */
public class HttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
          addHeaders(builder);
        return chain.proceed(builder.build());
    }
    private void addHeaders(Request.Builder builder) {
        createHeaderMap(builder);
    }

    private void createHeaderMap(Request.Builder builder) {
      //  MyApplication instance = MyApplication.getInstance();

        //String device_time = String.valueOf(System.currentTimeMillis());

        //String appId = instance.getPackageName();

//        builder.addHeader("Device-Time", device_time);
//        builder.addHeader("Device-Name", silentURLEncode(Build.MODEL));
//        builder.addHeader("Device-Model", silentURLEncode(Build.MODEL));
//        builder.addHeader("System-Name", "Android");
//        builder.addHeader("System-Version", silentURLEncode(Build.VERSION.RELEASE));
//        builder.addHeader("App-Version", AppUtil.getAppVersionName(MyApplication.getInstance()));
//        builder.addHeader("User-Platform", "android");
//        builder.addHeader("Device-Product", silentURLEncode(Build.PRODUCT));
//        builder.addHeader("Device-Fingerprint", silentURLEncode(Build.FINGERPRINT));
//        builder.addHeader("Device-Hardware", silentURLEncode(Build.HARDWARE));
         //builder.addHeader("token", ConfigManager.getUserToken());

        /*
        *
        *
        * Language: zh_cn
    Platform-CU: Android
    Content-Type: application/x-www-form-urlencoded
    Content-Length: 120
    Host: aaa.mex.group:81
    Connection: Keep-Alive
    Accept-Encoding: gzip
    User-Agent: okhttp/3.10.0
    */

        builder.addHeader("Language", "zh_cn");
        builder.addHeader("Platform-CU", "Android");
    }


    public static String silentURLEncode(String value, String charset) {
        try {
            return URLEncoder.encode(value, charset);
        } catch (UnsupportedEncodingException ignored) {
            return value;
        }
    }

    public static String silentURLEncode(String value) {
        return silentURLEncode(value, "UTF-8");
    }


    private static String hashMac(String baseString, String token) {
        LNLog.d("HASHMAC", baseString);
        return Strings.hmacDigest(baseString, token, "HmacSHA512");
    }
}
