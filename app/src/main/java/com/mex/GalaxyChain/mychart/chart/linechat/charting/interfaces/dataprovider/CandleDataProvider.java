package com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
