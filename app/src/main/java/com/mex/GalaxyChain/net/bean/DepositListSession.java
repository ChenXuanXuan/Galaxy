package com.mex.GalaxyChain.net.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LSJ on 18/3/13.
 */

public class DepositListSession extends BaseBean {

	private List<ListBean> list;

	public List<ListBean> getList() {
		return list;
	}

	public void setList(List<ListBean> list) {
		this.list = list;
	}

	public static class ListBean implements Serializable{
		/**
		 * txno : 02180107050601874830
		 * symbol : bitCNY
		 * address : kkcoin-wallet
		 * memo :  47655050
		 * amt : 5,0000
		 * reward_amt : 0
		 * txid : 1.11.112603923
		 * state : 1
		 * confirm_num : 1
		 * symbol_confirm_num : 1
		 * create_timestamp : 1515301561
		 */

		private String txno;
		private String symbol;
		private String address;
		private String memo;
		private String amt;
		private String reward_amt;
		private String txid;
		private int state;
		private int confirm_num;
		private int symbol_confirm_num;
		private int create_timestamp;
		private String name;
		private String img;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getTxno() {
			return txno;
		}

		public void setTxno(String txno) {
			this.txno = txno;
		}

		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public String getAmt() {
			return amt;
		}

		public void setAmt(String amt) {
			this.amt = amt;
		}

		public String getReward_amt() {
			return reward_amt;
		}

		public void setReward_amt(String reward_amt) {
			this.reward_amt = reward_amt;
		}

		public String getTxid() {
			return txid;
		}

		public void setTxid(String txid) {
			this.txid = txid;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public int getConfirm_num() {
			return confirm_num;
		}

		public void setConfirm_num(int confirm_num) {
			this.confirm_num = confirm_num;
		}

		public int getSymbol_confirm_num() {
			return symbol_confirm_num;
		}

		public void setSymbol_confirm_num(int symbol_confirm_num) {
			this.symbol_confirm_num = symbol_confirm_num;
		}

		public int getCreate_timestamp() {
			return create_timestamp;
		}

		public void setCreate_timestamp(int create_timestamp) {
			this.create_timestamp = create_timestamp;
		}
	}
}
