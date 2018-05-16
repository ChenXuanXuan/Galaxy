package com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.BarData;

public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BarData getBarData();
    boolean isDrawBarShadowEnabled();
    boolean isDrawValueAboveBarEnabled();
    boolean isDrawHighlightArrowEnabled();
}
