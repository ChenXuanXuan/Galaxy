package com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
