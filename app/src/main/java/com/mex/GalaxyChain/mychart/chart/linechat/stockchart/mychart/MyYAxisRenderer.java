package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart;

import android.graphics.Canvas;
import android.text.TextUtils;

import com.mex.GalaxyChain.mychart.chart.linechat.charting.renderer.YAxisRenderer;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.utils.Transformer;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.utils.Utils;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.utils.ViewPortHandler;


public class MyYAxisRenderer extends YAxisRenderer {
    protected MyYAxis mYAxis;

    public MyYAxisRenderer(ViewPortHandler viewPortHandler, MyYAxis yAxis, Transformer trans) {
        super(viewPortHandler, yAxis, trans);
        mYAxis = yAxis;
    }

    @Override
    protected void computeAxisValues(float min, float max) {
        /*只显示最大最小情况下*/
        if (mYAxis.isShowOnlyMinMaxEnabled()) {
            mYAxis.mEntryCount = 2;
            mYAxis.mEntries = new float[2];
            mYAxis.mEntries[0] = min;
            mYAxis.mEntries[1] = max;
            return;
        }
        /*折线图左边没有basevalue，则调用系统*/
        if (Float.isNaN(mYAxis.getBaseValue())) {
            super.computeAxisValues(min, max);
            return;
        }
        float base = mYAxis.getBaseValue();
        float yMin = min;
        int labelCount = mYAxis.getLabelCount();
        float interval = (base - yMin) / labelCount;
        int n = labelCount * 2 + 1;
        mYAxis.mEntryCount = n;
        mYAxis.mEntries = new float[n];
        int i;
        float f;
        for (f = min, i = 0; i < n; f += interval, i++) {
            mYAxis.mEntries[i] = f;
        }
    }

    /**
     * 分时图画Y周数据
     * @param c
     * @param fixedPosition
     * @param positions
     * @param offset
     */
    @Override
    protected void drawYLabels(Canvas c, float fixedPosition, float[] positions, float offset) {
       /*当有最小text的时候*/
        if (!TextUtils.isEmpty(mYAxis.getMinValue()) && mYAxis.isShowOnlyMinMaxEnabled()) {
            for (int i = 0; i < mYAxis.mEntryCount; i++) {
                /*获取对应位置的值*/
                String text = mYAxis.getFormattedLabel(i);
                if (i == 0) {
                    text = mYAxis.getMinValue();
                }
                if (i == 1) {
                    c.drawText(text, fixedPosition, mViewPortHandler.offsetTop()+2*offset+5 , mAxisLabelPaint);
                } else if (i == 0) {
                    c.drawText(text, fixedPosition, mViewPortHandler.contentBottom() - 3, mAxisLabelPaint);
                }
            }
        }
        else {
            for (int i = 0; i < mYAxis.mEntryCount; i++) {

                String text = mYAxis.getFormattedLabel(i);
                if (!mYAxis.isDrawTopYLabelEntryEnabled() && i >= mYAxis.mEntryCount - 1)
                    return;

                int labelHeight = Utils.calcTextHeight(mAxisLabelPaint, text);
                float pos = positions[i * 2 + 1] + offset;

                if ((pos - labelHeight) < mViewPortHandler.contentTop()) {

                    pos = mViewPortHandler.contentTop() + offset * 2.5f + 3;
                } else if ((pos + labelHeight / 2) > mViewPortHandler.contentBottom()) {
                    pos = mViewPortHandler.contentBottom() - 3;
                }
                c.drawText(text, fixedPosition, pos, mAxisLabelPaint);
            }


        }
    }

//    @Override
//    public void renderAxisLabels(Canvas canvas ){
//        super.renderAxisLabels(canvas);
//
//        if (!mYAxis.isDrawGridLinesEnabled() || !mYAxis.isEnabled())
//            return;
//        float[] position = new float[]{
//                0f, 0f
//        };
//
//        int count = mYAxis.getYabels().size();
//        if (!mChart.isScaleXEnabled()) {
//            count -= 1;
//        }
//
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(0.5f);
//        paint.setColor(Color.parseColor("#d2d0d0"));//颜色可以自己设置
//        Path path = new Path();
////        path.moveTo(0, 0);//起始坐标
////        path.lineTo(0, 500);//终点坐标
//        PathEffect effects = new DashPathEffect(new float[]{8, 8, 8, 8}, 1);//设置虚线的间隔和点的长度
//        paint.setPathEffect(effects);
//
//        for (int i = 0; i < count; i++) {
//            int ix = mYAxis.getYabels().keyAt(i);
//            position[0] = ix;
//            mTrans.pointValuesToPixel(position);
////            canvas.drawLine(position[0], mViewPortHandler.offsetTop(), position[0],
////                    mViewPortHandler.contentBottom(), mGridPaint);
//            path.moveTo(position[0], mViewPortHandler.offsetTop());//起始坐标
//            path.lineTo(position[0], mViewPortHandler.contentBottom());//终点坐标
//            canvas.drawPath(path, paint);
//        }
//
//    }
}
