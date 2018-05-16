package com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.components.YAxis;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.BarLineScatterCandleBubbleData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(YAxis.AxisDependency axis);
    int getMaxVisibleCount();
    boolean isInverted(YAxis.AxisDependency axis);
    
    int getLowestVisibleXIndex();
    int getHighestVisibleXIndex();

    BarLineScatterCandleBubbleData getData();
}
