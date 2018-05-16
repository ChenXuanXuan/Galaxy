package com.mex.GalaxyChain.mychart.chart.linechat.charting.exception;

public class DrawingDataSetNotCreatedException extends RuntimeException {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DrawingDataSetNotCreatedException() {
		super("Have to create StatementEntity new drawing set first. Call ChartData's createNewDrawingDataSet() method");
	}

}
