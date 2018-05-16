package com.mex.GalaxyChain.ui.market.entity;

import com.mex.GalaxyChain.net.bean.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * name：
 * describe:
 * author: LSJ
 * time 21/4/18 下午11:51
 */
public class MarketSession extends BaseBean {
	private String name;

	public List<ItemBean> getItmeList() {
		if (itmeList == null) {
			return new ArrayList<>();
		}
		return itmeList;
	}

	public void setItmeList(List<ItemBean> itmeList) {
		this.itmeList = itmeList;
	}

	private List<ItemBean> itmeList;



	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private class ItemBean implements Serializable{
		private String itemName;

		public String getItemName() {
			return itemName == null ? "" : itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
	}
}
