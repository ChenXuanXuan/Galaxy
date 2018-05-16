package com.mex.GalaxyChain.net.bean;

/**
 * Created by LSJ on 18/3/6.
 */

public class ConfigBean extends BaseBean {
	private String ws_url;
	private String service_en;
	private String service_cn;

	public String getService_en() {
		return service_en;
	}

	public void setService_en(String service_en) {
		this.service_en = service_en;
	}

	public String getService_cn() {
		return service_cn;
	}

	public void setService_cn(String service_cn) {
		this.service_cn = service_cn;
	}

	public String getWs_url() {
		return ws_url;
	}

	public void setWs_url(String ws_url) {
		this.ws_url = ws_url;
	}
}
