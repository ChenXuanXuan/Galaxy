package com.mex.GalaxyChain.event;

import java.io.Serializable;

/**
 * name：
 * describe:监测网络状态event
 * author: LSJ
 * time 29/3/18 下午5:30
 */

public class NetEvent implements Serializable {

	public NetEvent(boolean isConnected) {
		this.isConnected = isConnected;
	}

	private boolean isConnected;

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean connected) {
		isConnected = connected;
	}
}
