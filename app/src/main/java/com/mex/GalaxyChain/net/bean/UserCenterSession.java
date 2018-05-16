package com.mex.GalaxyChain.net.bean;

import java.io.Serializable;

/**
 * Created by LSJ on 18/3/13.
 */

public class UserCenterSession extends BaseBean {
	/**
	 * info : {"email":"342835747@qq.com","google_state":1,"kk_pay":1}
	 * userinfo : {"state":2}
	 */

	private InfoBean info;
	private UserinfoBean userinfo;

	public InfoBean getInfo() {
		return info;
	}

	public void setInfo(InfoBean info) {
		this.info = info;
	}

	public UserinfoBean getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserinfoBean userinfo) {
		this.userinfo = userinfo;
	}

	public static class InfoBean implements Serializable{
		/**
		 * email : 342835747@qq.com
		 * google_state : 1
		 * kk_pay : 1
		 */

		private String email;
		private int google_state;
		private int kk_pay;

		private String trade_fee_desc_en;
		private String trade_fee_desc_cn;

		public String getTrade_fee_desc_en() {
			return trade_fee_desc_en == null ? "" : trade_fee_desc_en;
		}

		public void setTrade_fee_desc_en(String trade_fee_desc_en) {
			this.trade_fee_desc_en = trade_fee_desc_en;
		}

		public String getTrade_fee_desc_cn() {
			return trade_fee_desc_cn == null ? "" : trade_fee_desc_cn;
		}

		public void setTrade_fee_desc_cn(String trade_fee_desc_cn) {
			this.trade_fee_desc_cn = trade_fee_desc_cn;
		}


		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getGoogle_state() {
			return google_state;
		}

		public void setGoogle_state(int google_state) {
			this.google_state = google_state;
		}

		public int getKk_pay() {
			return kk_pay;
		}

		public void setKk_pay(int kk_pay) {
			this.kk_pay = kk_pay;
		}
	}

	public static class UserinfoBean implements Serializable{
		/**
		 * state : 2
		 */

		private int state;

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}
	}

}
