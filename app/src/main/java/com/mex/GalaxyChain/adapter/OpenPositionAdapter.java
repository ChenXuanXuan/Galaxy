package com.mex.GalaxyChain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.MoneyFlowBean;
import com.mex.GalaxyChain.common.control.BaseAbsListAdapter;
import com.mex.GalaxyChain.common.control.BaseViewHolder;

public class OpenPositionAdapter extends
        BaseAbsListAdapter<MoneyFlowBean.DataBean.ListBean,
                OpenPositionAdapter.OpenPositionViewHolder> {
    private final LayoutInflater mLayoutInflater;

    public OpenPositionAdapter(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public OpenPositionAdapter.OpenPositionViewHolder onCreateViewHolder(int viewType) {
        View itemView=  mLayoutInflater.inflate(R.layout.layout_moneyflow_item_openposition, null);
        OpenPositionViewHolder openPositionViewHolder=new  OpenPositionViewHolder(itemView,this);
        return  openPositionViewHolder;
    }


    public class OpenPositionViewHolder extends BaseViewHolder<MoneyFlowBean.DataBean.ListBean> {
        public OpenPositionViewHolder(View convertView, BaseAbsListAdapter absListAdapter) {
            super(convertView, absListAdapter);
        }
    }
}
