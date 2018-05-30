package com.mex.GalaxyChain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.MoneyFlowBean;
import com.mex.GalaxyChain.common.control.BaseAbsListAdapter;
import com.mex.GalaxyChain.common.control.BaseViewHolder;
import com.mex.GalaxyChain.utils.DecimalFormatUtils;

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
        public void loadDataToView(int position, MoneyFlowBean.DataBean.ListBean data) {
            super.loadDataToView(position, data);
            //VarietyHoldPosiBean mVarietyHoldPosiBean= ConfigManager.getVarietyHold();
           // mVarietyHoldPosiBean.getHashMap();
           // symbolName.setText();
            tv_time.setText(data.getBiztime());
            tv_canuseAmount.setText(DecimalFormatUtils.getDecimal(data.getCanusedamount(),2));
    }
    }





}
