package com.mex.GalaxyChain.net.websocket;


import com.mex.GalaxyChain.event.TickerEvent;
import com.mex.GalaxyChain.mychart.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * name：
 * describe:
 * author: LSJ
 * time 23/4/18 上午10:52
 */
public final class EchoWebSocketListener extends WebSocketListener {

	@Override
	public void onOpen(WebSocket webSocket, Response response) {

		webSocket.send("hello world");
		webSocket.send("welcome");
		webSocket.send(ByteString.decodeHex("adef"));
//		webSocket.close(1000, "再见");
	}

	@Override
	public void onMessage(WebSocket webSocket, String text) {
		EventBus.getDefault().post(new TickerEvent(text));
	}

	@Override
	public void onMessage(WebSocket webSocket, ByteString bytes) {
		LogUtils.d("onMessage byteString: " + bytes);
	}

	@Override
	public void onClosing(WebSocket webSocket, int code, String reason) {
		webSocket.close(1000, null);
		LogUtils.d("onClosing: " + code + "/" + reason);
	}

	@Override
	public void onClosed(WebSocket webSocket, int code, String reason) {
		LogUtils.d("onClosed: " + code + "/" + reason);
	}

	@Override
	public void onFailure(WebSocket webSocket, Throwable t, Response response) {
		LogUtils.d("onFailure: " + t.getMessage());
	}
}
