package com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.datasets;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.Entry;

/**
 * Created by philipp on 21/10/15.
 */
public interface IBarLineScatterCandleBubbleDataSet<T extends Entry> extends IDataSet<T> {

    /**
     * Returns the color that is used for drawing the highlight indicators.
     *
     * @return
     */
    int getHighLightColor();
}
