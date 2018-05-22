package com.mex.GalaxyChain.ui.exchange;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.eventbean.ToMarketFragBean;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

/**
 * name：
 * describe:
 * author: LSJ
 * time 2018/5/21 17:38
 */
@EViewGroup(R.layout.layout_view_headerview)
public class HomeHeaderView extends LinearLayout {
    private Context mContext;
    @ViewById(R.id.tv_sart_profit)
    TextView tv_sart_profit;//开创盈利

    @ViewById
    LinearLayout noData;

    public HomeHeaderView(Context context) {
        super(context);
        this.mContext = context;
    }

    public HomeHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public HomeHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Click({R.id.tv_sart_profit})
    void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_sart_profit:
                EventBus.getDefault().post(new ToMarketFragBean());
                break;

        }
    }

    public void bindView(boolean isNoData) {
        if (isNoData)
            noData.setVisibility(VISIBLE);
        else
            noData.setVisibility(GONE);
    }
}
