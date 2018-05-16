
package com.mex.GalaxyChain.mychart.chart.linechat.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.BarData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.BubbleData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.CandleData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.CombinedData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.Entry;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.LineData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.ScatterData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.highlight.CombinedHighlighter;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.highlight.Highlight;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider.BarDataProvider;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider.BubbleDataProvider;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider.CandleDataProvider;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider.LineDataProvider;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.dataprovider.ScatterDataProvider;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.datasets.IBubbleDataSet;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.renderer.CombinedChartRenderer;
import com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart.MyBottomMarkerView;
import com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart.MyLeftMarkerView;
import com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart.MyRightMarkerView;


/**
 * This chart class allows the combination of lines, bars, scatter and candle
 * data all displayed in one chart area.
 *
 * @author Philipp Jahoda
 */
public class CombinedChart extends BarLineChartBase<CombinedData> implements LineDataProvider,
        BarDataProvider, ScatterDataProvider, CandleDataProvider, BubbleDataProvider {

    private MyLeftMarkerView myMarkerViewLeft;
    private MyRightMarkerView myMarkerViewRight;
    private MyBottomMarkerView mMyBottomMarkerView;
    private float leftuteHelper;
    private String bottomuteHelper;

    /**
     * flag that enables or disables the highlighting arrow
     */
    private boolean mDrawHighlightArrow = false;

    /**
     * if set to true, all values are drawn above their bars, instead of below
     * their top
     */
    private boolean mDrawValueAboveBar = true;

    /**
     * if set to true, StatementEntity grey area is drawn behind each bar that indicates the
     * maximum value
     */
    private boolean mDrawBarShadow = false;

    protected DrawOrder[] mDrawOrder = new DrawOrder[]{
            DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER
    };

    /**
     * enum that allows to specify the order in which the different data objects
     * for the combined-chart are drawn
     */
    public enum DrawOrder {
        BAR, BUBBLE, LINE, CANDLE, SCATTER
    }

    public CombinedChart(Context context) {
        super(context);
    }

    public CombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CombinedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();

        setHighlighter(new CombinedHighlighter(this));

        // Old default behaviour
        setHighlightFullBarEnabled(true);

        // mRenderer = new CombinedChartRenderer(this, mAnimator,
        // mViewPortHandler);
    }

    @Override
    protected void calcMinMax() {
        super.calcMinMax();

        if (getBarData() != null || getCandleData() != null || getBubbleData() != null) {
            mXAxis.mAxisMinimum = -0.5f;
            mXAxis.mAxisMaximum = mData.getXVals().size() - 0.5f;

            if (getBubbleData() != null) {

                for (IBubbleDataSet set : getBubbleData().getDataSets()) {

                    final float xmin = set.getXMin();
                    final float xmax = set.getXMax();

                    if (xmin < mXAxis.mAxisMinimum)
                        mXAxis.mAxisMinimum = xmin;

                    if (xmax > mXAxis.mAxisMaximum)
                        mXAxis.mAxisMaximum = xmax;
                }
            }
        }

        mXAxis.mAxisRange = Math.abs(mXAxis.mAxisMaximum - mXAxis.mAxisMinimum);

        if (mXAxis.mAxisRange == 0.f && getLineData() != null && getLineData().getYValCount() > 0) {
            mXAxis.mAxisRange = 1.f;
        }
    }

    @Override
    public void setData(CombinedData data) {
        mData = null;
        mRenderer = null;
        super.setData(data);
        mRenderer = new CombinedChartRenderer(this, mAnimator, mViewPortHandler);
        mRenderer.initBuffers();
    }

    public void setMarker(MyLeftMarkerView markerLeft, MyBottomMarkerView makerBottom, float leftValue, String bottomValue) {
        this.myMarkerViewLeft = markerLeft;
        this.mMyBottomMarkerView = makerBottom;
        this.leftuteHelper = leftValue;
        this.bottomuteHelper = bottomValue;
    }

    @Override
    protected void drawMarkers(Canvas canvas) {
        if (!mDrawMarkerViews || !valuesToHighlight())
            return;
        for (int i = 0; i < mIndicesToHighlight.length; i++) {
            Highlight highlight = mIndicesToHighlight[i];
            int xIndex = mIndicesToHighlight[i].getXIndex();
            int dataSetIndex = mIndicesToHighlight[i].getDataSetIndex();
            float deltaX = mXAxis != null
                    ? mXAxis.mAxisRange
                    : ((mData == null ? 0.f : mData.getXValCount()) - 1.f);
            if (xIndex <= deltaX && xIndex <= deltaX * mAnimator.getPhaseX()) {
                Entry e = mData.getEntryForHighlight(mIndicesToHighlight[i]);
                // make sure entry not null
                if (e == null || e.getXIndex() != mIndicesToHighlight[i].getXIndex())
                    continue;

                float[] pos = getMarkerPosition(e, highlight);
                // check bounds
                if (!mViewPortHandler.isInBounds(pos[0], pos[1]))
                    continue;

//                float yValForXIndex1 = minuteHelper.getDatas().get(mIndicesToHighlight[i].getXIndex()).cjprice;
//                float yValForXIndex2 = minuteHelper.getDatas().get(mIndicesToHighlight[i].getXIndex()).per;
//                String time = minuteHelper.getDatas().get(mIndicesToHighlight[i].getXIndex()).time;

                mMyBottomMarkerView.setData(bottomuteHelper);
                myMarkerViewLeft.setData(leftuteHelper);
                myMarkerViewLeft.refreshContent(e, mIndicesToHighlight[i]);
                mMyBottomMarkerView.refreshContent(e, mIndicesToHighlight[i]);
                /*修复bug*/
                // invalidate();
                /*重新计算大小*/
                myMarkerViewLeft.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                myMarkerViewLeft.layout(0, 0, myMarkerViewLeft.getMeasuredWidth(),
                        myMarkerViewLeft.getMeasuredHeight());
                myMarkerViewLeft.draw(canvas, mViewPortHandler.contentLeft() - myMarkerViewLeft.getWidth(), pos[1] - myMarkerViewLeft.getHeight() / 2);

                mMyBottomMarkerView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                mMyBottomMarkerView.layout(0, 0, mMyBottomMarkerView.getMeasuredWidth(),
                        mMyBottomMarkerView.getMeasuredHeight());
                mMyBottomMarkerView.draw(canvas, pos[0]-mMyBottomMarkerView.getWidth()/2, mViewPortHandler.contentBottom());
            }
        }
    }

    @Override
    public LineData getLineData() {
        if (mData == null)
            return null;
        return mData.getLineData();
    }

    @Override
    public BarData getBarData() {
        if (mData == null)
            return null;
        return mData.getBarData();
    }

    @Override
    public ScatterData getScatterData() {
        if (mData == null)
            return null;
        return mData.getScatterData();
    }

    @Override
    public CandleData getCandleData() {
        if (mData == null)
            return null;
        return mData.getCandleData();
    }

    @Override
    public BubbleData getBubbleData() {
        if (mData == null)
            return null;
        return mData.getBubbleData();
    }

    @Override
    public boolean isDrawBarShadowEnabled() {
        return mDrawBarShadow;
    }

    @Override
    public boolean isDrawValueAboveBarEnabled() {
        return mDrawValueAboveBar;
    }

    @Override
    public boolean isDrawHighlightArrowEnabled() {
        return mDrawHighlightArrow;
    }

    /**
     * set this to true to draw the highlightning arrow
     *
     * @param enabled
     */
    public void setDrawHighlightArrow(boolean enabled) {
        mDrawHighlightArrow = enabled;
    }

    /**
     * If set to true, all values are drawn above their bars, instead of below
     * their top.
     *
     * @param enabled
     */
    public void setDrawValueAboveBar(boolean enabled) {
        mDrawValueAboveBar = enabled;
    }


    /**
     * If set to true, StatementEntity grey area is drawn behind each bar that indicates the
     * maximum value. Enabling his will reduce performance by about 50%.
     *
     * @param enabled
     */
    public void setDrawBarShadow(boolean enabled) {
        mDrawBarShadow = enabled;
    }

    /**
     * Returns the currently set draw order.
     *
     * @return
     */
    public DrawOrder[] getDrawOrder() {
        return mDrawOrder;
    }

    /**
     * Sets the order in which the provided data objects should be drawn. The
     * earlier you place them in the provided array, the further they will be in
     * the background. e.g. if you provide new DrawOrer[] { DrawOrder.BAR,
     * DrawOrder.LINE }, the bars will be drawn behind the lines.
     *
     * @param order
     */
    public void setDrawOrder(DrawOrder[] order) {
        if (order == null || order.length <= 0)
            return;
        mDrawOrder = order;
    }
}
