package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.mychart;

import android.content.Context;
import android.widget.ImageView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.components.MarkerView;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.Entry;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.highlight.Highlight;

import java.text.DecimalFormat;


public class MyCenterMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with StatementEntity custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    private ImageView markerTv;
    private float num;
    private  DecimalFormat mFormat;
    public MyCenterMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
//        mFormat=new DecimalFormat("#0.00");
        markerTv = (ImageView) findViewById(R.id.marker_iv);
    }

//    public void setData(float num){
//
//        this.num=num;
//    }
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        markerTv.setVisibility(VISIBLE);
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
