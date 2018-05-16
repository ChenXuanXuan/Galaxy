package com.mex.GalaxyChain.net.websocket;



import com.mex.GalaxyChain.utils.LogUtils;

import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * name：
 * describe:
 * author: LSJ
 * time 16/4/18 下午2:48
 */
public class MyWebSocketClient {
	private static final String TAG = "WS_CLIENT";
	private final static Object countObj = new Object();
	private final IMessageListener listener;
	private final String socketUrl;
	//当connectCount ＝＝0 的时候，可以清除
	private int connectCount;
	private String mapKey;
	private OkHttpClient myClient;
	private boolean isClosing = false;
	private boolean isClosed = false;
	private WebSocket webSocket;


	public MyWebSocketClient(IMessageListener listener, String socketUrl) {
		this.listener = listener;
		this.socketUrl = socketUrl;
	}

	public void addConnect() {
		synchronized (countObj) {
			this.connectCount++;
		}
	}

	public void close() {
		if (isLastConnect()) {
			webSocket.close(1000, "app self");
		} else {
			downConnect();
		}
	}

	public boolean isLastConnect() {
		return connectCount <= 0;
	}

	public void downConnect() {
		synchronized (countObj) {
			this.connectCount--;
		}
	}

	public void clear() {
		synchronized (countObj) {
			connectCount = -1;
		}
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	public void connect() {
		Request request = new Request.Builder()
				.url(socketUrl)
				.build();
		myClient = new OkHttpClient();
		webSocket = myClient.newWebSocket(request, new WebSocketListener() {

			/**可以向服务器端发送消息*/
			@Override
			public void onOpen(WebSocket webSocket, Response response) {
				super.onOpen(webSocket, response);
				LogUtils.d(TAG, socketUrl + "run() returned: " + "连接到服务器");
				if (listener != null) {
					listener.onOpen(webSocket, response);
				}
			}

			@Override
			public void onMessage(WebSocket webSocket, String message) {
				super.onMessage(webSocket, message);
				if (listener != null) {
					listener.onReceiveMessage(message);
				}
				LogUtils.d(TAG, socketUrl + "run() returned: " + message);
			}

			@Override
			public void onClosing(WebSocket webSocket, int code, String reason) {
				super.onClosing(webSocket, code, reason);
				MyWebSocketClient.this.isClosing = true;
			}

			@Override
			public void onClosed(WebSocket webSocket, int code, String reason) {
				super.onClosed(webSocket, code, reason);
				MyWebSocketClient.this.isClosed = true;
				LogUtils.d(TAG, socketUrl + "onClose() returned:code:" + code + "reason: " + reason);
				if (code == 1000) {
					//什么也不做，主动断开
				} else {
					if (listener != null) {
						listener.onClose();
					}
				}
			}

			@Override
			public void onFailure(WebSocket webSocket, Throwable t, Response response) {
				super.onFailure(webSocket, t, response);
				LogUtils.d(TAG, socketUrl + "onError() returned: " + t);
				if (t instanceof UnknownHostException
						|| t instanceof SSLException) {
					if (listener != null) {
						listener.waitingNetEvent();
					}
					webSocket.close(1000, "unknow host");
					//服务器不可用
				} else {
					if (listener != null) {
						listener.onError();
					}
				}

			}

		});

		myClient.dispatcher().executorService().shutdown();
	}

	public boolean isClosed() {
		return isClosed;
	}

	public boolean isClosing() {
		return isClosing;
	}
}