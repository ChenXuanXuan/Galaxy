package com.mex.GalaxyChain.net.websocket;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * name：
 * describe:
 * author: LSJ
 * time 16/4/18 下午2:52
 */
public class WebSocketHelper {

	private static final String TYPE_ALL = "type_all";
	static HashMap<String, MyWebSocketClient> socketMap = new HashMap<>();
	final static HashMap<String, WSocketNullCreator> waitingNetEventMap = new HashMap<>();

	public static void closeSocketPairs() {
		String type = TYPE_ALL;
		MyWebSocketClient webSocketClient = socketMap.get(type);
		closeSocketClient(webSocketClient);

	}

	/**
	 * 判断当前是否是最后一个需要这个的连接，如果是的话，才清除，否则，只进行连接数--
	 *
	 * @param webSocketClient
	 */
	private static void closeSocketClient(MyWebSocketClient webSocketClient) {
		if (webSocketClient == null || webSocketClient.isClosed() || webSocketClient.isClosing())
			return;
		if (webSocketClient.isLastConnect() && socketMap.containsValue(webSocketClient)) {
			socketMap.remove(webSocketClient.getMapKey());
		}
		webSocketClient.close();
	}

	public static void clear() {
		MyWebSocketClient webSocketClient;
		Set<String> keySet = socketMap.keySet();
		for (String key : keySet) {
			webSocketClient = socketMap.get(key);
			if (webSocketClient != null) {
				webSocketClient.clear();
				webSocketClient.close();
			}
		}
		socketMap.clear();
	}


	public static boolean checkSocket(String key) {
		MyWebSocketClient myWebSocketClient = socketMap.get(key);
		if (myWebSocketClient == null || myWebSocketClient.isClosed() || myWebSocketClient.isClosing()) {
			socketMap.put(key, null);
			return false;
		}
		return true;
	}


	private static synchronized void makeWebSocket(String key, WSocketNullCreator wSocketNullCreator) {
		MyWebSocketClient webSocketClient = socketMap.get(key);
		if (webSocketClient == null || webSocketClient.isClosed() || webSocketClient.isClosing()) {
			webSocketClient = wSocketNullCreator.onCreate();
			if (webSocketClient != null && !TextUtils.isEmpty(key)) {
				MyWebSocketClient localClient = socketMap.get(key);
				if (localClient != null) {
					webSocketClient.close();
					localClient.addConnect();
				} else {
					webSocketClient.setMapKey(key);
					socketMap.put(key, webSocketClient);
				}
			}
		} else {
			webSocketClient.addConnect();
		}
	}

	public static void checkPairSocket() {
		if (!checkSocket(TYPE_ALL)) {
			startSocketPairs();
		}
	}

	public static void startSocketPairs() {
		makeWebSocket(TYPE_ALL, new WSocketNullCreator() {
			@Override
			public MyWebSocketClient onCreate() {
				return PairSocketClient.connectSocketPairs(this);
			}

			@Override
			public void onClose() {
				socketMap.remove(TYPE_ALL);
				startSocketPairs();
			}

			@Override
			public void waitingNetEvent() {
				add2EventList(TYPE_ALL, this);

			}

		});
	}

	private static void add2EventList(String type, WSocketNullCreator creator) {
		synchronized (waitingNetEventMap) {
			waitingNetEventMap.put(type, creator);
		}
	}

	public interface WSocketNullCreator {
		MyWebSocketClient onCreate();

		void onClose();

		void waitingNetEvent();
	}

	public static void reConnect() {
		synchronized (waitingNetEventMap) {
			for (Map.Entry<String, WSocketNullCreator> set : waitingNetEventMap.entrySet()) {
				makeWebSocket(set.getKey(), set.getValue());
			}
			waitingNetEventMap.clear();
		}
	}
}
