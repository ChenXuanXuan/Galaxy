package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart;

import android.content.Context;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.components.MarkerView;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.Entry;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.highlight.Highlight;

import java.text.DecimalFormat;


public class MyRightMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with StatementEntity custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    private TextView markerTv;
    private float num;
    private DecimalFormat mFormat;

    public MyRightMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        mFormat = new DecimalFormat("#0.00");
        markerTv = (TextView) findViewById(R.id.marker_tv);
        markerTv.setTextSize(10);

    }

    public void setData(float num) {
        this.num = num;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        markerTv.setText(mFormat.format(num * 100) + "%");
    }

    @Override
    public int getXOffset(float xpos) {

        return 0;
    }

    @Override
    public int getYOffset(float ypos) {
        return 0;
    }
}
