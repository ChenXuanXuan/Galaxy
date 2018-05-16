package com.mex.GalaxyChain.mychart.chart.linechat.charting.formatter;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider.LineDataProvider;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.datasets.ILineDataSet;

/**
 * Interface for providing StatementEntity custom logic to where the filling line of StatementEntity LineDataSet
 * should end. This of course only works if setFillEnabled(...) is set to true.
 * 
 * @author Philipp Jahoda
 */
public interface FillFormatter {

    /**
     * Returns the vertical (y-axis) position where the filled-line of the
     * LineDataSet should end.
     *
     * @param dataSet the ILineDataSet that is currently drawn
     * @param dataProvider
     * @return
     */
    float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider);
}
