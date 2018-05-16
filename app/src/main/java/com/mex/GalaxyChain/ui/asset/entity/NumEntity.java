package com.mex.GalaxyChain.ui.asset.entity;

import com.mex.GalaxyChain.mychart.BaseEntitiy;

/**
 * name：
 * describe:
 * author: LSJ
 * time 26/4/18 下午9:25
 */
public class NumEntity extends BaseEntitiy {


    private int id;
	private String handSum;// 1手(几手)
	private boolean isSelcet;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





	public boolean isSelcet() {
		return isSelcet;
	}

	public void setSelcet(boolean selcet) {
		isSelcet = selcet;
	}

	public String getHandSum() {
		return handSum == null ? "" : handSum;
	}

	public void setHandSum(String handSum) {
		this.handSum = handSum;
	}
}
