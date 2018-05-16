
package com.mex.GalaxyChain.mychart.chart.linechat.charting.charts;

import android.content.Context;
import android.util.AttributeSet;

import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.CandleData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider.CandleDataProvider;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.renderer.CandleStickChartRenderer;


/**
 * Financial chart type that draws candle-sticks (OHCL chart).
 * 
 * @author Philipp Jahoda
 */
public class CandleStickChart extends BarLineChartBase<CandleData> implements CandleDataProvider {

    public CandleStickChart(Context context) {
        super(context);
    }

    public CandleStickChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CandleStickChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();




        mRenderer = new CandleStickChartRenderer(this, mAnimator, mViewPortHandler);
        mXAxis.mAxisMinimum = -0.5f;
    }

    @Override
    protected void calcMinMax() {
        super.calcMinMax();

        mXAxis.mAxisMaximum += 0.5f;
        mXAxis.mAxisRange = Math.abs(mXAxis.mAxisMaximum - mXAxis.mAxisMinimum);
    }

    @Override
    public CandleData getCandleData() {
        return mData;
    }
}
