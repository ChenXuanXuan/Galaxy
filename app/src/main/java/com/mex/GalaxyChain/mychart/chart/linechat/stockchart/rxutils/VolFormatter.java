package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.rxutils;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.components.YAxis;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.formatter.YAxisValueFormatter;

import java.text.DecimalFormat;


public class VolFormatter implements YAxisValueFormatter {

    private final int unit;
    private DecimalFormat mFormat;
    private String u;
    public VolFormatter(int unit) {
        if (unit == 1) {
            mFormat = new DecimalFormat("#0");
        } else {
            mFormat = new DecimalFormat("#0.00");
        }
        this.unit = unit;
        this.u=MyUtils.getVolUnit(unit);
    }


    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        value = value / unit;
        if(value==0){
            return u;
        }
        return mFormat.format(value);
    }
}
