package com.mex.GalaxyChain.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.TradeDetailBean;
import com.mex.GalaxyChain.bean.eventbean.VarietyHoldPosi;
import com.mex.GalaxyChain.bean.eventbean.VarietyHoldPosiBean;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.control.BaseAbsListAdapter;
import com.mex.GalaxyChain.common.control.BaseViewHolder;

import java.util.Map;


public class SettleAdapter  extends BaseAbsListAdapter<TradeDetailBean.DataBean.ListBean,SettleAdapter.SettleViewHolder> {
    private VarietyHoldPosiBean varietyHoldPosiBean;

    public SettleAdapter(Context context) {
        super(context);
    }

    @Override
    public SettleAdapter.SettleViewHolder onCreateViewHolder(int viewType) {
      View itemView=  View.inflate(context, R.layout.layout_settle_item, null);

        SettleViewHolder settleViewHolder=new SettleViewHolder(itemView,this);

        return settleViewHolder;
    }

    public void setItemData(VarietyHoldPosiBean varietyHoldPosiBean) {
         this.varietyHoldPosiBean=varietyHoldPosiBean;
    }

    public class SettleViewHolder extends BaseViewHolder<TradeDetailBean.DataBean.ListBean> {



        TextView tv_sysbmol_name,tv_shoushu,tv_loss,tv_user_make,tv_yingkui_num,tv_oddNumbers,tv_stop_Profit
                ,tv_stop_loss,tv_open_position,tv_close_position,tv_close_position_time,tv_trade_free,tv_open_position_time,tv_onceAgain;




        public SettleViewHolder(View convertView, BaseAbsListAdapter absListAdapter) {
            super(convertView, absListAdapter);
            tv_sysbmol_name=(TextView)find(R.id.tv_sysbmol_name); //商品名称
            tv_shoushu=(TextView)find(R.id.tv_shoushu);//手数
            tv_loss=(TextView)find(R.id.tv_loss); //做空做多
            tv_user_make  =(TextView)find(R.id.tv_user_make);//用户操作
            tv_yingkui_num  =(TextView)find(R.id.tv_yingkui_num);//盈亏
            tv_oddNumbers  =(TextView)find(R.id.tv_oddNumbers);//单号
            tv_stop_Profit  =(TextView)find(R.id.tv_stop_Profit);//触发止盈
            tv_stop_loss  =(TextView)find(R.id.tv_stop_loss); //触发止损
            tv_open_position  =(TextView)find(R.id.tv_open_position); //开仓
            tv_close_position  =(TextView)find(R.id.tv_close_position); //平仓
            tv_close_position_time  =(TextView)find(R.id.tv_close_position_time);  //平仓时间
            tv_trade_free  =(TextView)find(R.id.tv_trade_free); //交易费用
            tv_open_position_time  =(TextView)find(R.id.tv_open_position_time); //开仓时间
            tv_onceAgain= (TextView)find(R.id.tv_onceAgain);

        }


        @Override
        public void loadDataToView(int position, TradeDetailBean.DataBean.ListBean listBean) {
            super.loadDataToView(position, data);
            if(varietyHoldPosiBean!=null){
                Map<String, VarietyHoldPosi> holdPosiMap=varietyHoldPosiBean.getHashMap();

                for (String key : holdPosiMap.keySet()) {
                       if(key.equals(listBean.getSymbol())){
                           VarietyHoldPosi  varietyHoldPosi=holdPosiMap.get(listBean.getSymbol());
                           tv_sysbmol_name.setText(varietyHoldPosi.symbolname);
                       }
                }
            }//else{tv_sysbmol_name.setText(listBean.getSymbol());}







             tv_shoushu.setText(String.valueOf(listBean.getQuantity()));  // 手数 quantity
           int bstype= listBean.getBstype();
           if(bstype==1){
               tv_loss.setText(Constants.UP);
           }else{
               tv_loss.setText(Constants.DROP);
           }
            tv_yingkui_num.setText(String.valueOf(listBean.getProfit()));
            tv_oddNumbers.setText(String.valueOf(listBean.getCloseorderno()));//单号closeorderno
            tv_stop_Profit.setText(String.valueOf(listBean.getStopprofit()));
            tv_stop_loss.setText(String.valueOf(listBean.getStoploss()));
            tv_open_position.setText(String.valueOf(listBean.getBuyprice()));//买入价(开仓)
            tv_close_position.setText(String.valueOf(listBean.getSellprice()));//卖出价(平仓)
            tv_close_position_time.setText(listBean.getClosetime());   //平仓时间
            tv_trade_free.setText(String.valueOf(listBean.getTradefee()));
            tv_open_position_time.setText(listBean.getOpentime());

            tv_onceAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     UIHelper.toKLineActivity(context);   //调到K线详情页面

                }
            });
        }
    }
}
