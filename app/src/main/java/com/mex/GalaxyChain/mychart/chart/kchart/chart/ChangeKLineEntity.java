package com.mex.GalaxyChain.mychart.chart.kchart.chart;

import com.lsj.kchart.kchartlib.chart.entity.KLineImpl;

import java.io.Serializable;

/**
 * K线实体
 * Created by LSJ on 2016/5/16.
 */

public class ChangeKLineEntity implements Serializable {

    private float ma5;
    private  float ma10;
    private float ma20;

    public float getMa5() {
        return ma5;
    }

    public void setMa5(float ma5) {
        this.ma5 = ma5;
    }

    public float getMa10() {
        return ma10;
    }

    public void setMa10(float ma10) {
        this.ma10 = ma10;
    }

    public float getMa20() {
        return ma20;
    }

    public void setMa20(float ma20) {
        this.ma20 = ma20;
    }
}
