package com.mex.GalaxyChain.net.websocket;


import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.event.TickerEvent;
import com.mex.GalaxyChain.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.net.URI;

import okhttp3.Response;
import okhttp3.WebSocket;

/**
 * name：
 * describe:
 * author: LSJ
 * time 16/4/18 下午2:52
 */
public class PairSocketClient {
    public static MyWebSocketClient connectSocketPairs(final WebSocketHelper.WSocketNullCreator wSocketNullCreator) {
        URI webUrl = null;
        try {
//			webUrl = URI.create(WsClient.getWebSocketHost() + "all@ticker");
            webUrl = URI.create(WsClient.getWebSocketHost() + "TickData");
        } catch (ErrHostException e) {
            return null;
        }
        return WsClient.makeWSocketClient(webUrl, new IMessageListener() {

            @Override
            public void onReceiveMessage(String msg) {
                String[] temp = null;
                temp = msg.split("\\|");
                EventBus.getDefault().post(new TickerEvent(temp[3]));
            }

            @Override
            public void onClose() {
                LogUtils.d("*********************推送onClose:");
                if (wSocketNullCreator != null) {
                    wSocketNullCreator.onClose();
                }
            }

            @Override
            public void onError() {
                LogUtils.d("*********************推送onError:");
                if (wSocketNullCreator != null) {
                    wSocketNullCreator.onClose();
                }
            }

            /**可以向服务器端发送消息*/
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                if (UserGolbal.getInstance().getTag() == Constants.ALL_VARIETY) { //全部品种
                    String strSymbol = UserGolbal.getInstance().getSymbol();
                    String subscibeStr = "10001|0|2|{\"Symbol\":" + "\"" + strSymbol + "\"" + ",\"Operate\":1} ";
                    LogUtils.d("TAG1", subscibeStr);
                    webSocket.send(subscibeStr);
                } else if (UserGolbal.getInstance().getTag() == Constants.GLOBAL_INDEX) { //全球指数
                    String strSymbol = UserGolbal.getInstance().getSymbol();
                    String subscibeStr = "10001|0|2|{\"Symbol\":" + "\"" + strSymbol + "\"" + ",\"Operate\":1} ";
                    LogUtils.d("TAG2", subscibeStr);
                    webSocket.send(subscibeStr);
                } else if (UserGolbal.getInstance().getTag() == Constants.CRYPTO_CURRENCY) { //加密货币
                    String strSymbol = UserGolbal.getInstance().getSymbol();
                    String subscibeStr = "10001|0|2|{\"Symbol\":" + "\"" + strSymbol + "\"" + ",\"Operate\":1} ";
                    LogUtils.d("TAG3", subscibeStr);
                    webSocket.send(subscibeStr);
                }


            }

            @Override
            public void waitingNetEvent() {
                if (wSocketNullCreator != null) {
                    wSocketNullCreator.waitingNetEvent();
                }
            }
        });
    }

}
