package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart;


import android.util.SparseArray;

import com.mex.GalaxyChain.mychart.chart.linechat.charting.components.YAxis;


public class MyYAxis extends YAxis {
    private float baseValue = Float.NaN;
    private String minValue;

    private SparseArray<String> labels;

    public SparseArray<String> getYabels() {
        return labels;
    }

    public void setYLabels(SparseArray<String> labels) {
        this.labels = labels;
    }


    public MyYAxis() {
        super();
    }

    public MyYAxis(AxisDependency axis) {
        super(axis);
    }

    public void setShowMaxAndUnit(String minValue) {
        setShowOnlyMinMax(true);
        this.minValue = minValue;
    }

    public float getBaseValue() {
        return baseValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setBaseValue(float baseValue) {
        this.baseValue = baseValue;
    }
}
