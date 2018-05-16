package com.mex.GalaxyChain.net.websocket;

import okhttp3.Response;
import okhttp3.WebSocket;

/**
 * name：
 * describe:
 * author: LSJ
 * time 16/4/18 下午2:50
 */
public interface IMessageListener {
	void onReceiveMessage(String msg);

	void onClose();

	void onError();

	void onOpen(WebSocket webSocket, Response response);

	void waitingNetEvent();
}
