package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;

import com.mex.GalaxyChain.mychart.chart.linechat.charting.charts.BarLineChartBase;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.renderer.XAxisRenderer;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.utils.Transformer;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.utils.Utils;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.utils.ViewPortHandler;


public class MyXAxisRenderer extends XAxisRenderer {
    private final BarLineChartBase mChart;
    protected MyXAxis mXAxis;

    public MyXAxisRenderer(ViewPortHandler viewPortHandler, MyXAxis xAxis, Transformer trans, BarLineChartBase chart) {
        super(viewPortHandler, xAxis, trans);
        mXAxis = xAxis;
        mChart = chart;
    }

    @Override
    protected void drawLabels(Canvas c, float pos, PointF anchor) {
        float[] position = new float[]{
                0f, 0f
        };

        int count = mXAxis.getXLabels().size();
        for (int i = 0; i < count; i++) {
              /*获取label对应key值，也就是x轴坐标0,60,121,182,242*/
            int ix = mXAxis.getXLabels().keyAt(i);
            position[0] = ix;
            /*在图表中的x轴转为像素，方便绘制text*/
            mTrans.pointValuesToPixel(position);
            /*x轴越界*/
            if (mViewPortHandler.isInBoundsX(position[0])) {
                String label = mXAxis.getXLabels().valueAt(i);
                /*文本长度*/
                int labelWidth = Utils.calcTextWidth(mAxisLabelPaint, label);
                /*右出界*/
                if ((labelWidth / 2 + position[0]) > mChart.getViewPortHandler().contentRight()) {
                    position[0] = mChart.getViewPortHandler().contentRight() - labelWidth / 2;
                } else if ((position[0] - labelWidth / 2) < mChart.getViewPortHandler().contentLeft()) {//左出界
                    position[0] = mChart.getViewPortHandler().contentLeft() + labelWidth / 2;
                }
                c.drawText(label, position[0],
                        pos + Utils.convertPixelsToDp(mChart.getViewPortHandler().offsetBottom()),
                        mAxisLabelPaint);
            }

        }
    }

    /*x轴垂直线*/
    @Override
    public void renderGridLines(Canvas canvas) {
        if (!mXAxis.isDrawGridLinesEnabled() || !mXAxis.isEnabled())
            return;
        float[] position = new float[]{
                0f, 0f
        };

        int count = mXAxis.getXLabels().size();
        if (!mChart.isScaleXEnabled()) {
            count -= 1;
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1f);
        paint.setColor(Color.parseColor("#c6c3c3"));//颜色可以自己设置
        Path path = new Path();
//        path.moveTo(0, 0);//起始坐标
//        path.lineTo(0, 500);//终点坐标
        PathEffect effects = new DashPathEffect(new float[]{8, 8, 8, 8}, 1);//设置虚线的间隔和点的长度
        paint.setPathEffect(effects);

        for (int i = 0; i < count; i++) {
            int ix = mXAxis.getXLabels().keyAt(i);
            position[0] = ix;
            mTrans.pointValuesToPixel(position);
//            canvas.drawLine(position[0], mViewPortHandler.offsetTop(), position[0],
//                    mViewPortHandler.contentBottom(), mGridPaint);
            path.moveTo(position[0], mViewPortHandler.offsetTop());//起始坐标
            path.lineTo(position[0], mViewPortHandler.contentBottom());//终点坐标
            canvas.drawPath(path, paint);
        }

    }

}
