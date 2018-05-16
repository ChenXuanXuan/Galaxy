package com.mex.GalaxyChain.mychart.chart.linechat.charting.data.realm.implementation;

import android.graphics.Typeface;

import com.mex.GalaxyChain.mychart.chart.linechat.charting.charts.ScatterChart;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.components.YAxis;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.DataSet;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.Entry;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.realm.base.RealmLineScatterCandleRadarDataSet;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.formatter.ValueFormatter;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.datasets.IScatterDataSet;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.utils.ColorTemplate;

import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;



/**
 * Created by Philipp Jahoda on 07/11/15.
 */
public class RealmScatterDataSet<T extends RealmObject> extends RealmLineScatterCandleRadarDataSet<T, Entry> implements IScatterDataSet {

    /**
     * the size the scattershape will have, in density pixels
     */
    private float mShapeSize = 10f;

    /**
     * the type of shape that is set to be drawn where the values are at,
     * default ScatterShape.SQUARE
     */
    private ScatterChart.ScatterShape mScatterShape = ScatterChart.ScatterShape.SQUARE;

    /**
     * The radius of the hole in the shape (applies to Square, Circle and Triangle)
     * - default: 0.0
     */
    private float mScatterShapeHoleRadius = 0f;

    /**
     * Color for the hole in the shape.
     * Setting to `ColorTemplate.COLOR_NONE` will behave as transparent.
     * - default: ColorTemplate.COLOR_NONE
     */
    private int mScatterShapeHoleColor = ColorTemplate.COLOR_NONE;

    /**
     * Constructor for creating StatementEntity ScatterDataSet with realm data.
     *
     * @param result       the queried results from the realm database
     * @param yValuesField the name of the field in your data object that represents the y-value
     */
    public RealmScatterDataSet(RealmResults<T> result, String yValuesField) {
        super(result, yValuesField);

        build(this.results);
        calcMinMax(0, results.size());
    }

    /**
     * Constructor for creating StatementEntity ScatterDataSet with realm data.
     *
     * @param result       the queried results from the realm database
     * @param yValuesField the name of the field in your data object that represents the y-value
     * @param xIndexField  the name of the field in your data object that represents the x-index
     */
    public RealmScatterDataSet(RealmResults<T> result, String yValuesField, String xIndexField) {
        super(result, yValuesField, xIndexField);

        build(this.results);
        calcMinMax(0, results.size());
    }

    /**
     * Sets the size in density pixels the drawn scattershape will have. This
     * only applies for non custom shapes.
     *
     * @param size
     */
    public void setScatterShapeSize(float size) {
        mShapeSize = size;
    }

    @Override
    public float getScatterShapeSize() {
        return mShapeSize;
    }

    /**
     * Sets the shape that is drawn on the position where the values are at. If
     * "CUSTOM" is chosen, you need to call setCustomScatterShape(...) and
     * provide StatementEntity path object that is drawn as the custom scattershape.
     *
     * @param shape
     */
    public void setScatterShape(ScatterChart.ScatterShape shape) {
        mScatterShape = shape;
    }

    @Override
    public ScatterChart.ScatterShape getScatterShape() {
        return mScatterShape;
    }

    /**
     * Sets the radius of the hole in the shape
     *
     * @param holeRadius
     */
    public void setScatterShapeHoleRadius(float holeRadius) {
        mScatterShapeHoleRadius = holeRadius;
    }

    @Override
    public float getScatterShapeHoleRadius() {
        return mScatterShapeHoleRadius;
    }

    /**
     * Sets the color for the hole in the shape
     *
     * @param holeColor
     */
    public void setScatterShapeHoleColor(int holeColor) {
        mScatterShapeHoleColor = holeColor;
    }

    @Override
    public int getScatterShapeHoleColor() {
        return mScatterShapeHoleColor;
    }

    @Override
    public Entry getEntryForXIndex(int xIndex, DataSet.Rounding rounding) {
        return null;
    }

    @Override
    public int getEntryIndex(int xIndex, DataSet.Rounding rounding) {
        return 0;
    }

    @Override
    public int getIndexInEntries(int xIndex) {
        return 0;
    }

    @Override
    public boolean removeFirst() {
        return false;
    }

    @Override
    public boolean removeLast() {
        return false;
    }

    @Override
    public boolean removeEntry(int xIndex) {
        return false;
    }

    @Override
    public boolean contains(Entry entry) {
        return false;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public void setLabel(String label) {

    }

    @Override
    public YAxis.AxisDependency getAxisDependency() {
        return null;
    }

    @Override
    public void setAxisDependency(YAxis.AxisDependency dependency) {

    }

    @Override
    public List<Integer> getColors() {
        return null;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public int getColor(int index) {
        return 0;
    }

    @Override
    public boolean isHighlightEnabled() {
        return false;
    }

    @Override
    public void setHighlightEnabled(boolean enabled) {

    }

    @Override
    public void setValueFormatter(ValueFormatter f) {

    }

    @Override
    public ValueFormatter getValueFormatter() {
        return null;
    }

    @Override
    public void setValueTextColor(int color) {

    }

    @Override
    public void setValueTextColors(List<Integer> colors) {

    }

    @Override
    public void setValueTypeface(Typeface tf) {

    }

    @Override
    public void setValueTextSize(float size) {

    }

    @Override
    public int getValueTextColor() {
        return 0;
    }

    @Override
    public int getValueTextColor(int index) {
        return 0;
    }

    @Override
    public Typeface getValueTypeface() {
        return null;
    }

    @Override
    public float getValueTextSize() {
        return 0;
    }

    @Override
    public void setDrawValues(boolean enabled) {

    }

    @Override
    public boolean isDrawValuesEnabled() {
        return false;
    }

    @Override
    public void setVisible(boolean visible) {

    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
