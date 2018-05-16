package com.mex.GalaxyChain.mychart.chart.linechat.charting.highlight;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.ChartData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.CombinedData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.datasets.IDataSet;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.utils.SelectionDetail;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Philipp Jahoda on 12/09/15.
 */
public class CombinedHighlighter extends ChartHighlighter<BarLineScatterCandleBubbleDataProvider> {

    public CombinedHighlighter(BarLineScatterCandleBubbleDataProvider chart) {
        super(chart);
    }

    /**
     * Returns StatementEntity list of SelectionDetail object corresponding to the given xIndex.
     *
     * @param xIndex
     * @return
     */
    @Override
    protected List<SelectionDetail> getSelectionDetailsAtIndex(int xIndex, int dataSetIndex) {

        List<SelectionDetail> vals = new ArrayList<>();
        float[] pts = new float[2];

        CombinedData data = (CombinedData) mChart.getData();

        // get all chartdata objects
        List<ChartData> dataObjects = data.getAllData();

        for (int i = 0; i < dataObjects.size(); i++) {

            for(int j = 0; j < dataObjects.get(i).getDataSetCount(); j++) {

                IDataSet dataSet = dataObjects.get(i).getDataSetByIndex(j);

                // dont include datasets that cannot be highlighted
                if (!dataSet.isHighlightEnabled())
                    continue;

                // extract all y-values from all DataSets at the given x-index
                final float[] yVals = dataSet.getYValsForXIndex(xIndex);
                for (float yVal : yVals) {
                    pts[1] = yVal;

                    mChart.getTransformer(dataSet.getAxisDependency()).pointValuesToPixel(pts);

                    if (!Float.isNaN(pts[1])) {
                        vals.add(new SelectionDetail(pts[1], yVal, i, j, dataSet));
                    }
                }
            }
        }

        return vals;
    }
}
