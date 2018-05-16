package com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.datasets;

import android.graphics.DashPathEffect;

import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.Entry;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.LineDataSet;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.formatter.FillFormatter;


/**
 * Created by Philpp Jahoda on 21/10/15.
 */
public interface ILineDataSet extends ILineRadarDataSet<Entry>,IBarLineScatterCandleBubbleDataSet<Entry> {

    /**
     * Returns the drawing mode for this line dataset
     *
     * @return
     */
    LineDataSet.Mode getMode();

    /**
     * Returns the intensity of the cubic lines (the effect intensity).
     * Max = 1f = very cubic, Min = 0.05f = low cubic effect, Default: 0.2f
     *
     * @return
     */
    float getCubicIntensity();

    /**
     * @deprecated Kept for backward compatibility.
     * @return
     */
    @Deprecated
    boolean isDrawCubicEnabled();

    /**
     * @deprecated Kept for backward compatibility.
     * @return
     */
    @Deprecated
    boolean isDrawSteppedEnabled();

    /**
     * Returns the size of the drawn circles.
     */
    float getCircleRadius();

    /**
     * Returns the hole radius of the drawn circles.
     */
    float getCircleHoleRadius();

    /**
     * Returns the color at the given index of the DataSet's circle-color array.
     * Performs StatementEntity IndexOutOfBounds check by modulus.
     *
     * @param index
     * @return
     */
    int getCircleColor(int index);

    /**
     * Returns true if drawing circles for this DataSet is enabled, false if not
     *
     * @return
     */
    boolean isDrawCirclesEnabled();

    /**
     * Returns the color of the inner circle (the circle-hole).
     *
     * @return
     */
    int getCircleHoleColor();

    /**
     * Returns true if drawing the circle-holes is enabled, false if not.
     *
     * @return
     */
    boolean isDrawCircleHoleEnabled();

    /**
     * Returns the DashPathEffect that is used for drawing the lines.
     *
     * @return
     */
    DashPathEffect getDashPathEffect();

    /**
     * Returns true if the dashed-line effect is enabled, false if not.
     * If the DashPathEffect object is null, also return false here.
     *
     * @return
     */
    boolean isDashedLineEnabled();

    /**
     * Returns the FillFormatter that is set for this DataSet.
     *
     * @return
     */
    FillFormatter getFillFormatter();
}