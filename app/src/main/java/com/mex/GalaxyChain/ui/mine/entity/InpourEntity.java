package com.mex.GalaxyChain.ui.mine.entity;

import com.mex.GalaxyChain.net.bean.BaseBean;

/**
 * name：
 * describe:
 * author: LSJ
 * time 23/4/18 下午9:58
 */
public class InpourEntity extends BaseBean {
	private String name;

	public String getName() {
		return name == null ? "" : name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
