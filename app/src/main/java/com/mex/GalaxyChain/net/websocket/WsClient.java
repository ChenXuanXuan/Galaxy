package com.mex.GalaxyChain.net.websocket;

import android.support.annotation.NonNull;


import com.mex.GalaxyChain.utils.LogUtils;

import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * Created by lsj on 18/5/10.
 */

public class WsClient {
	private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

	public static String getWebSocketHost() throws ErrHostException {
//		ConfigBean config = ConfigManager.getConfig();
//		if (config == null) {//保证必须取到config
//			throw new ErrHostException();
//		}
//		return "wss://qa-api.poker.com:4433/ws/";
		return "ws://39.105.28.205:9092/";
	}

	@NonNull
	public static MyWebSocketClient makeWSocketClient(final URI webUrl, final IMessageListener listener) {
		final String socket_url = webUrl.toString();
		final MyWebSocketClient webSocketClient = getMyWebSocketClient(listener, socket_url);
		scheduledExecutorService.execute(new Runnable() {
			@Override
			public void run() {
				LogUtils.d("SOCKET_URL", socket_url);
				webSocketClient.connect();
			}
		});
		return webSocketClient;
	}

	@NonNull
	private static MyWebSocketClient getMyWebSocketClient(final IMessageListener listener, final String socket_url) {
		return new MyWebSocketClient(listener, socket_url);
	}

}