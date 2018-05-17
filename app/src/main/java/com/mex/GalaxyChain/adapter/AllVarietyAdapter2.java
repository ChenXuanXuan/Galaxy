package com.mex.GalaxyChain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.SymbolBean;
import com.mex.GalaxyChain.bean.requestbean.GoodsPriceBean;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.control.BaseAbsListAdapter;
import com.mex.GalaxyChain.common.control.BaseViewHolder;

import java.text.NumberFormat;
import java.util.List;

public class AllVarietyAdapter2
        extends BaseAbsListAdapter<SymbolBean.DataBean.SymbolInfosBean,
                    AllVarietyAdapter2.AllVarietyViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private List<GoodsPriceBean.DataBean> goodsPriceList;


    public AllVarietyAdapter2(Context context) {
        super(context);
        mLayoutInflater =LayoutInflater.from(context);
    }

    @Override
    public AllVarietyAdapter2.AllVarietyViewHolder  onCreateViewHolder(int viewType) {
        View itemView= mLayoutInflater.inflate(R.layout.item_market_pair_view,null);
        AllVarietyViewHolder allVarietyViewHolder = new AllVarietyViewHolder(itemView,this);
        return allVarietyViewHolder;
    }

    public void transmitGoodsPriceList(List<GoodsPriceBean.DataBean> goodsPriceList) {
         this.goodsPriceList=goodsPriceList;
        notifyDataSetChanged();
         }


    public class AllVarietyViewHolder extends BaseViewHolder<SymbolBean.DataBean.SymbolInfosBean> {
        TextView variety_name, tv_trade_satus, sub_symbol_name, tv_mostNew_price, tv_24hoursTrade;

        public AllVarietyViewHolder(View convertView, BaseAbsListAdapter absListAdapter) {
            super(convertView, absListAdapter);
            variety_name = (TextView) find(R.id.variety_name);
            tv_trade_satus = (TextView) find(R.id.tv_trade_satus);
            sub_symbol_name = (TextView) find(R.id.sub_symbol_name);
            tv_mostNew_price = (TextView) find(R.id.tv_mostNew_price); //最新价
            tv_24hoursTrade = (TextView) find(R.id.tv_24hoursTrade);

        }


        /*  getView{
      loadDataToView(position, getItem(position)); //getItem(position)= itemDatas.get(position)=SymbolBean.SymbolInfosBean
       }
      */
        @Override
        public void loadDataToView(int position, SymbolBean.DataBean.SymbolInfosBean symbolInfosBean) {
            super.loadDataToView(position, data);
            variety_name.setText(symbolInfosBean.getSymbolname()); //品名称
            sub_symbol_name.setText(symbolInfosBean.getSymbol());

            // int OPENING=1; //开盘中
            // int CLOSING=2; //未开盘
            // int trading=3; //交易中
            //  int SUSPEND_TRADING=4; //暂停交易
//coinType.setText(context.getResources().getString(R.string.tab_asset_tobeconfirmed));
            if(goodsPriceList!=null&&goodsPriceList.size()>0){

                for (int i = 0; i < goodsPriceList.size(); i++) {
                    GoodsPriceBean.DataBean dataBean = goodsPriceList.get(i % goodsPriceList.size());
                    if (symbolInfosBean.getSymbol().equals(dataBean.getSymbol())) {
                        if (dataBean.getStatus() == Constants.OPENING) {  //开盘中  蓝色
                            tv_trade_satus.setText(context.getResources().getString(R.string.OPENING));
                            tv_trade_satus.setBackground(context.getDrawable(R.drawable.btn_bg_blue_shape));
                            } else if (dataBean.getStatus() == Constants.TRADING) { //交易中  蓝色
                            tv_trade_satus.setText(context.getResources().getString(R.string.TRADING));
                            tv_trade_satus.setBackground(context.getDrawable(R.drawable.btn_bg_blue_shape));
                        } else if (dataBean.getStatus() == Constants.CLOSING) { //未开盘 灰色
                            tv_trade_satus.setText(context.getResources().getString(R.string.CLOSING));
                            tv_trade_satus.setBackground(context.getDrawable(R.drawable.btn_bg_gray_shape));
                        } else if (dataBean.getStatus() == Constants.SUSPEND_TRADING) { //暂停交易 灰色
                            tv_trade_satus.setText(context.getResources().getString(R.string.SUSPEND_TRADING));
                            tv_trade_satus.setBackground(context.getDrawable(R.drawable.btn_bg_gray_shape));
                        }else{
                            tv_trade_satus.setText(context.getResources().getString(R.string.CLOSING));
                            tv_trade_satus.setBackground(context.getDrawable(R.drawable.btn_bg_gray_shape));
                        }

                          double offerPrice =dataBean.getOfferPrice(); //最新价 (卖价) 12841.99
                          double preClose = dataBean.getPreClose();  //昨日收盘价12840.34
                            //  涨跌幅度=最新价 -  昨日收盘价）100% /  昨日收盘价
                           double upsAndDowns =(offerPrice-preClose)*100/preClose;

                           if(offerPrice==preClose){
                              tv_mostNew_price.setTextColor(context.getResources().getColor(R.color.device_button_normal));
                              tv_24hoursTrade.setTextColor(context.getResources().getColor(R.color.device_button_normal));
                              // convertView.setBackgroundColor(context.getResources().getColor(R.color.device_button_normal));
                          }else if(offerPrice>preClose){
                              tv_mostNew_price.setTextColor(context.getResources().getColor(R.color.red));
                              tv_24hoursTrade.setTextColor(context.getResources().getColor(R.color.red));
                              // convertView.setBackgroundColor(context.getResources().getColor(R.color.red));
                          }else if(offerPrice<preClose){
                              tv_mostNew_price.setTextColor(context.getResources().getColor(R.color.light_green));
                              tv_24hoursTrade.setTextColor(context.getResources().getColor(R.color.light_green));
                              // convertView.setBackgroundColor(context.getResources().getColor(R.color.light_green));
                          }else{
                              tv_mostNew_price.setTextColor(context.getResources().getColor(R.color.device_button_normal));
                              tv_24hoursTrade.setTextColor(context.getResources().getColor(R.color.device_button_normal));
                             //  convertView.setBackgroundColor(context.getResources().getColor(R.color.device_button_normal));
                          }

                          tv_mostNew_price.setText(Double.toString(offerPrice)); //最新价
                          int decimalplace =symbolInfosBean.getDecimalplace();//保留小数位数
                          NumberFormat mNumberFormat= MyApplication.getInstance().mNumberFormat;
                         mNumberFormat.setMaximumFractionDigits(decimalplace);
                          tv_24hoursTrade.setText(mNumberFormat.format(upsAndDowns)+"%"); //涨幅度(默认保留2位 待商量)
                          //  ToastUtils.showTextInMiddle("test");
                    }

                }
            }
            }




    }

}
