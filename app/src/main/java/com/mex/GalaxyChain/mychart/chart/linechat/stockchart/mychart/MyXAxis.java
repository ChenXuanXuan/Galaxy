package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart;

import android.util.SparseArray;

import com.mex.GalaxyChain.mychart.chart.linechat.charting.components.XAxis;


//将要显示的labels封装进去，方便set和get，不封装也行，这样好看点。
public class MyXAxis extends XAxis {
    private SparseArray<String> labels;

    public SparseArray<String> getXLabels() {
        return labels;
    }

    public void setXLabels(SparseArray<String> labels) {
        this.labels = labels;
    }
}
