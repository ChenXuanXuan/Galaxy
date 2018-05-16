
package com.mex.GalaxyChain.mychart.chart.linechat.charting.utils;

/**
 * Point encapsulating two double values.
 *
 * @author Philipp Jahoda
 */
public class PointD {

    public double x;
    public double y;

    public PointD(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returns StatementEntity string representation of the object
     */
    @Override
    public String toString() {
        return "PointD, x: " + x + ", y: " + y;
    }
}
