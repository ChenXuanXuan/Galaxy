package com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.components.YAxis;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
