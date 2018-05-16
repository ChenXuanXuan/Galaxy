package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart;

import android.content.Context;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.components.MarkerView;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.Entry;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.highlight.Highlight;


/**
 * author：ajiang
 * mail：1025065158@qq.com
 * blog：http://blog.csdn.net/qqyanjiang
 */
public class MyBottomMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with StatementEntity custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    private TextView markerTv;
    private String time;
    public MyBottomMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        markerTv = (TextView) findViewById(R.id.marker_tv);
        markerTv.setTextSize(10);
    }

    public void setData(String time){

        this.time=time;
    }
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        markerTv.setText(time);
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
