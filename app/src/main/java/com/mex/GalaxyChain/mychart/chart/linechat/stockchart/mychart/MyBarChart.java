package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.mex.GalaxyChain.mychart.chart.linechat.charting.charts.BarChart;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.components.YAxis;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.Entry;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.highlight.Highlight;
import com.mex.GalaxyChain.mychart.chart.linechat.stockchart.bean.DataParse;
import com.mex.GalaxyChain.mychart.utils.NumberUtils;


/**
 * 作者：ajiang
 * 邮箱：1025065158
 * 博客：http://blog.csdn.net/qqyanjiang
 */
public class MyBarChart extends BarChart {
    //    private MyLeftMarkerView myMarkerViewLeft;
//    private MyRightMarkerView myMarkerViewRight;
//    private MyBottomMarkerView mMyBottomMarkerView;
    private MyBarLeftMarkerView myBarLeftMarkerView;
    private DataParse minuteHelper;

    public MyBarChart(Context context) {
        super(context);
    }

    public MyBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setMarker(MyBarLeftMarkerView myBarLeftMarkerView, DataParse minuteHelper) {
        this.myBarLeftMarkerView = myBarLeftMarkerView;
        this.minuteHelper = minuteHelper;
    }

    @Override
    protected void init() {
        super.init();
        /*此处不能重新示例*/
        mXAxis = new MyXAxis();

        mAxisLeft = new MyYAxis(YAxis.AxisDependency.LEFT);
        mAxisRendererLeft = new MyYAxisRenderer(mViewPortHandler, (MyYAxis) mAxisLeft, mLeftAxisTransformer);
        mXAxisRenderer = new MyXAxisRenderer(mViewPortHandler, (MyXAxis) mXAxis, mLeftAxisTransformer, this);
        mAxisRight = new MyYAxis(YAxis.AxisDependency.RIGHT);
        mAxisRendererRight = new MyYAxisRenderer(mViewPortHandler, (MyYAxis) mAxisRight, mRightAxisTransformer);

    }

    @Override
    protected void calcModulus() {

        mXAxis.mAxisLabelModulus = 1;
    }

    /*返回转型后的左右轴*/
    @Override
    public MyYAxis getAxisLeft() {
        return (MyYAxis) super.getAxisLeft();
    }

    @Override
    public MyXAxis getXAxis() {
        return (MyXAxis) super.getXAxis();
    }


    @Override
    public MyYAxis getAxisRight() {
        return (MyYAxis) super.getAxisRight();
    }

    public void setHighlightValue(Highlight h) {
        mIndicesToHighlight = new Highlight[]{
                h};
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

//                String time = minuteHelper.getDatas().get(mIndicesToHighlight[i].getXIndex()).time;
//                mMyBottomMarkerView.setData(time);
//                mMyBottomMarkerView.refreshContent(e, mIndicesToHighlight[i]);
                float maxValue = minuteHelper.getDatas().get(mIndicesToHighlight[i].getXIndex()).maxValue;
                float volume = minuteHelper.getDatas().get(mIndicesToHighlight[i].getXIndex()).volume;
                if (maxValue >= 10000) {
                    if (volume > 1) {
                        myBarLeftMarkerView.setData(NumberUtils.numberFormatInt(volume) + "万手");
                    } else {
                        myBarLeftMarkerView.setData(NumberUtils.numberFormatInt(volume * 10000) + "手");
                    }
                }else {
                    myBarLeftMarkerView.setData(NumberUtils.numberFormatInt(volume) + "手");
                }
                myBarLeftMarkerView.refreshContent(e, mIndicesToHighlight[i]);
                /*修复bug*/
                // invalidate();
                /*重新计算大小*/
//                mMyBottomMarkerView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//                mMyBottomMarkerView.layout(0, 0, mMyBottomMarkerView.getMeasuredWidth(),
//                        mMyBottomMarkerView.getMeasuredHeight());
//                mMyBottomMarkerView.draw(canvas, pos[0] - mMyBottomMarkerView.getWidth() / 2, mViewPortHandler.contentBottom());

                myBarLeftMarkerView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                myBarLeftMarkerView.layout(0, 0, myBarLeftMarkerView.getMeasuredWidth(),
                        myBarLeftMarkerView.getMeasuredHeight());
//                myBarLeftMarkerView.draw(canvas, pos[0] - myBarLeftMarkerView.getWidth() / 2, mViewPortHandler.contentBottom());
                myBarLeftMarkerView.draw(canvas, mViewPortHandler.contentLeft() - myBarLeftMarkerView.getWidth(), pos[1] - myBarLeftMarkerView.getHeight() / 2);

            }
        }
    }
}
