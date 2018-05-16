package com.mex.GalaxyChain.net.bean;

/**
 * Created by LSJ on 18/3/13.
 */

public class OrderResult extends BaseBean {
	private int code;
	private String order_id;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
}
