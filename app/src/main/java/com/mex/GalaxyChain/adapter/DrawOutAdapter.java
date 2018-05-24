package com.mex.GalaxyChain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.PayOutListBean;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.control.BaseAbsListAdapter;
import com.mex.GalaxyChain.common.control.BaseViewHolder;
import com.mex.GalaxyChain.utils.DecimalFormatUtils;


public class DrawOutAdapter extends BaseAbsListAdapter<PayOutListBean.DataBean.ListBean,
        DrawOutAdapter.MoneyFlowViewHolder> {
    private final LayoutInflater mLayoutInflater;

    public DrawOutAdapter(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MoneyFlowViewHolder onCreateViewHolder(int viewType) {
        View itemView=  mLayoutInflater.inflate(R.layout.layout_moneyflow_item_drawout, null);
        MoneyFlowViewHolder moneyFlowViewHolder=new MoneyFlowViewHolder(itemView,this);
        return moneyFlowViewHolder;
    }


    public class MoneyFlowViewHolder extends BaseViewHolder<PayOutListBean.DataBean.ListBean> {
        TextView tv_left,tv_middle,tv_right,tv_time,tv_canuseAmount;


        public MoneyFlowViewHolder(View convertView, BaseAbsListAdapter absListAdapter) {
            super(convertView, absListAdapter);
              tv_left=(TextView)find(R.id.tv_left);//左
             tv_middle=(TextView)find(R.id.tv_middle);//中
            tv_right=(TextView)find(R.id.tv_right);//右
            tv_time=(TextView)find(R.id.tv_time); //提现时间
            tv_canuseAmount=(TextView)find(R.id.tv_canuseAmount);//可用余额YY

        }


        @Override
        public void loadDataToView(int position, PayOutListBean.DataBean.ListBean data) {
            super.loadDataToView(position, data);
             tv_left.setText(data.getRemarks());
            tv_middle.setText(data.getStatustring());//提现状态的显示
            tv_right.setText("提现金额 "+DecimalFormatUtils.getDecimal(data.getAmount(),2)+ Constants.YY);
             tv_time.setText("提现时间 "+data.getWithdrawalstime());
               tv_canuseAmount.setText("可用余额 "+data.getCanusedamount()+Constants.YY );




        }
    }
}
