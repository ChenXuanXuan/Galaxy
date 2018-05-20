package com.mex.GalaxyChain.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.MoneyFlowBean;
import com.mex.GalaxyChain.common.control.BaseAbsListAdapter;
import com.mex.GalaxyChain.common.control.BaseViewHolder;


public class MoneyFlowAdapter   extends BaseAbsListAdapter<MoneyFlowBean.DataBean.ListBean,
        MoneyFlowAdapter.MoneyFlowViewHolder> {


    public MoneyFlowAdapter(Context context) {
        super(context);
    }

    @Override
    public MoneyFlowViewHolder onCreateViewHolder(int viewType) {
        View itemView=  View.inflate(context, R.layout.layout_moneyflow_item, null);
        MoneyFlowViewHolder moneyFlowViewHolder=new MoneyFlowViewHolder(itemView,this);
        return moneyFlowViewHolder;
    }


    public class MoneyFlowViewHolder extends BaseViewHolder<MoneyFlowBean.DataBean.ListBean> {
        TextView tv_left,tv_middle,tv_right,tv_time,tv_canuseAmount;


        public MoneyFlowViewHolder(View convertView, BaseAbsListAdapter absListAdapter) {
            super(convertView, absListAdapter);
              tv_left=(TextView)find(R.id.tv_left);
           // tv_middle=(TextView)find(R.id.tv_middle);
            tv_right=(TextView)find(R.id.tv_right);
            tv_time=(TextView)find(R.id.tv_time);
            tv_canuseAmount=(TextView)find(R.id.tv_canuseAmount);

        }


        @Override
        public void loadDataToView(int position, MoneyFlowBean.DataBean.ListBean data) {
            super.loadDataToView(position, data);
            tv_left.setText(data.getComment());
            tv_time.setText(data.getBiztime());
            tv_canuseAmount.setText("可用余额"+data.getCanusedamount());


        }
    }
}
