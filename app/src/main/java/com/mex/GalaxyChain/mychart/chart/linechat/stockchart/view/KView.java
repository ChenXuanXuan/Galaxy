package com.mex.GalaxyChain.mychart.chart.linechat.stockchart.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.mex.GalaxyChain.R;


/**
 * K线图view
 */
public class KView extends FrameLayout{

    private Context mCtx;

    public KView(Context context) {
        super(context);
        initView(context);
    }

    public KView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public KView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public KView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context){
        mCtx = context;
        View view = View.inflate(mCtx, R.layout.k_view, null);
    }




}
