package com.mex.GalaxyChain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.MoneyFlowBean;
import com.mex.GalaxyChain.bean.eventbean.VarietyHoldPosi;
import com.mex.GalaxyChain.bean.eventbean.VarietyHoldPosiBean;
import com.mex.GalaxyChain.common.control.BaseAbsListAdapter;
import com.mex.GalaxyChain.common.control.BaseViewHolder;

import java.util.Map;

public class   OpenPositionAdapter extends
        BaseAbsListAdapter<MoneyFlowBean.DataBean.ListBean,
                OpenPositionAdapter.OpenPositionViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final VarietyHoldPosiBean mVarietyHoldPosiBean;

    public OpenPositionAdapter(Context context,VarietyHoldPosiBean mVarietyHoldPosiBean) {
        super(context);
        this.mVarietyHoldPosiBean=mVarietyHoldPosiBean;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public OpenPositionAdapter.OpenPositionViewHolder onCreateViewHolder(int viewType) {
        View itemView=  mLayoutInflater.inflate(R.layout.layout_moneyflow_item_openposition, null);
        OpenPositionViewHolder openPositionViewHolder=new  OpenPositionViewHolder(itemView,this);
        return  openPositionViewHolder;
    }


    public class OpenPositionViewHolder extends BaseViewHolder<MoneyFlowBean.DataBean.ListBean> {
        TextView symbolName,tv_tradeFee,tv_margin,tv_time,tv_canuseAmount;

        public OpenPositionViewHolder(View convertView, BaseAbsListAdapter absListAdapter) {
            super(convertView, absListAdapter);
            symbolName=(TextView)find(R.id.symbolName);
            tv_tradeFee=(TextView)find(R.id.tv_tradeFee);  //扣除交易费用
            tv_margin=(TextView)find(R.id.tv_margin); //冻结保证金
            tv_time=(TextView)find(R.id.tv_time);

            tv_canuseAmount=(TextView)find(R.id.tv_canuseAmount);
        }


        @Override
        public void loadDataToView(int position, MoneyFlowBean.DataBean.ListBean listBean) {
            super.loadDataToView(position, data);
            Map<String, VarietyHoldPosi> holdPosiMap=mVarietyHoldPosiBean.getHashMap();
            for (String key : holdPosiMap.keySet()) {
                if(key.equals(listBean.getSymbol())){
                    VarietyHoldPosi  varietyHoldPosi=holdPosiMap.get(listBean.getSymbol());
                    symbolName.setText(varietyHoldPosi.symbolname+listBean.getQuantity()+"手");
                }

            }

            double tradefee=listBean.getTradefee();
             if(tradefee<0){
                 tv_tradeFee.setTextColor(context.getResources().getColor(R.color.light_green));
             }else{
                 tv_tradeFee.setTextColor(context.getResources().getColor(R.color.device_button_normal));
             }
             tv_tradeFee.setText(tradefee+"");


             double margin =listBean.getMargin();
             if(margin<0){
                 tv_margin.setTextColor(context.getResources().getColor(R.color.light_green));
             }else{
                 tv_margin.setTextColor(context.getResources().getColor(R.color.device_button_normal));
             }
            tv_margin.setText(margin+"");
             tv_time.setText(listBean.getBiztime());
            tv_canuseAmount.setText("可用余额 "+listBean.getCanusedamount());
    }
    }





}
