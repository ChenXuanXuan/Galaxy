package com.mex.GalaxyChain.mychart.chart.linechat.charting.formatter;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.Entry;

/**
 * Interface that can be used to return StatementEntity customized color instead of setting
 * colors via the setColor(...) method of the DataSet.
 * 
 * @author Philipp Jahoda
 */
public interface ColorFormatter {

    int getColor(Entry e, int index);
}