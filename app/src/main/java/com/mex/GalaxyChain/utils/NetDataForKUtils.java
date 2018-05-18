package com.mex.GalaxyChain.utils;

import android.os.Build;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.bean.MultiplBean;
import com.mex.GalaxyChain.bean.OrderBuyBean;
import com.mex.GalaxyChain.bean.requestbean.RequestOrderBuyBean;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.view.CustomWheelView;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

public class NetDataForKUtils {
    private static double mMLongitude;
    private static double mMLatitude;
    private static String mToken;
    private static NetDataForKUtils netDataForKUtils;


    public static  NetDataForKUtils getInstance(){
        if(IsEmptyUtils.isEmpty(netDataForKUtils)){
             synchronized (NetDataForKUtils.class){
                if(IsEmptyUtils.isEmpty(netDataForKUtils)){
                    netDataForKUtils = new NetDataForKUtils();
                }

             }
             }

   return  netDataForKUtils;
       }


  private  NetDataForKUtilsCallBackListencer mNetDataForKUtilsCallBackListencer;
    public void setNetDataForKUtilsCallBackListencer(NetDataForKUtilsCallBackListencer mNetDataForKUtilsCallBackListencer ){
        this.mNetDataForKUtilsCallBackListencer=mNetDataForKUtilsCallBackListencer;
    }
    public interface NetDataForKUtilsCallBackListencer{
        void callBackSuccessData(OrderBuyBean.DataBean mDataBean);

    }



    //一旦点击看涨买多按钮 弹出一个对话框  就立马做一个网络请求操作
    //宾哥的 号码 登陆  18612875467 配置了资金账号
 public   void    loadOrderPayBuyData(int bsType, final String closeTime, final double perprofitnumber,
                                         final double perprofit, List<MultiplBean> multiplBeanList, final String symbol,
                                         final String currencytype, CustomWheelView wheelView,
                                         final TextView tv_closeTime, final TextView tv_current_cny_rate,
                                         final TextView mTv_lossAmount, final int mDefault_handNum_one, final int mDefautl_beishu_one,
                                         final TextView mTv_shiyin_amount,final TextView tv_deal_perrmbmargin,
                                         final TextView tv_sum_amount,final TextView tv_enable_amount){


     if (UserGolbal.getInstance().locationSuccess()) {
         mMLongitude = UserGolbal.getInstance().getLongitude();
         mMLatitude = UserGolbal.getInstance().getLatitude();
         mToken = UserGolbal.getInstance().getUserToken();
         RequestOrderBuyBean requestOrderBuyBean = new RequestOrderBuyBean();
         //  int bsType=Constants.BUY_RISE; //1买涨
         requestOrderBuyBean.setBstype(bsType);
         requestOrderBuyBean.setUsertoken(mToken);
         requestOrderBuyBean.setSymbol(symbol);
         requestOrderBuyBean.setCurrencytype(currencytype);//币种类型
         requestOrderBuyBean.setDeviceType(Constants.ANDROID);
         requestOrderBuyBean.setDevcieModel(Build.MODEL);
         requestOrderBuyBean.setChannelId(Constants.channelId);
         requestOrderBuyBean.setVersion(AppUtil.getAppVersionName(MyApplication.getInstance()));
         MyApplication instance = MyApplication.getInstance();
         String device_identifier = DeviceUtil.getUdid(instance);
         String deviceID = HttpInterceptor.silentURLEncode(device_identifier);
         requestOrderBuyBean.setDeviceId(deviceID);
         requestOrderBuyBean.setLatitude(mMLatitude);
         requestOrderBuyBean.setLongitude(mMLongitude);
         Gson gson = new Gson();
         String jsonStr = gson.toJson(requestOrderBuyBean);
         RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
         UserRepo.getInstance().GetBuyPage(requestBody)
                 .subscribe(new Subscriber<OrderBuyBean>() {


                     @Override
                     public void onCompleted() {
                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onNext(OrderBuyBean orderBuyBean) {
                         //返回编码(200=成功,400=参数错误,402=数据错误,403=操作失败,404=数据不存在,500=服务器异常)
                         if (orderBuyBean.getCode() == 200)
                         {
                             ToastUtils.showTextInMiddle(orderBuyBean.getMsg() + symbol);
                             OrderBuyBean.DataBean mDataBean = orderBuyBean.getData();
                              if(!IsEmptyUtils.isEmpty(mNetDataForKUtilsCallBackListencer)){
                                  LogUtils.d("TAG-->回调:回",new Gson().toJson(mDataBean));
                                  mNetDataForKUtilsCallBackListencer.callBackSuccessData(mDataBean);//数据回调

                              }


                             double rate = mDataBean.getRate(); //汇率
                             //USD=美元，EUR=欧元，HKD=港币
                             String currencytype_zh = null;
                             if (currencytype.equals("USD")) {
                                 currencytype_zh = Constants.DOLLAR;
                             } else if (currencytype.equals("EUR")) {
                                 currencytype_zh = Constants.EURO;
                             } else if (currencytype.equals("HKD")) {
                                 currencytype_zh = Constants.HONGKONG_DOLLAR;
                             }

                             double count_CNY = perprofitnumber * perprofit * rate; //?CNY=点数*收益*汇率(rate)
                             tv_closeTime.setText(closeTime + "自动平仓,涨"
                                     + perprofitnumber + "个点赚"
                                     + perprofit + currencytype_zh
                                     + "(" + count_CNY + Constants.RMB + ")");

                             tv_current_cny_rate.setText("当前汇率:  " + "1" + currencytype_zh + "=" + rate + Constants.RMB); //人民币汇率
                             //=================================================
                             double  mPerrmbfee = mDataBean.getPerrmbfee(); //每笔手续费(交易费用)perrmbfee todo 回返
                             double perrmbmargin = mDataBean.getPerrmbmargin();//(履约保证金)    todo 回返
                             double   mStoplossratio = mDataBean.getStoplossratio();//止损率     todo 回返
                             double  mPer_stoploss_amount = perrmbmargin * mStoplossratio;//单价触发止损金额(不带倍手)= 履约保证金*止损率
                             // 一进入界面 默认(1手1倍)  显示 默认止损金额  默认止盈金额  默认占用保证金    todo 回返

                             mTv_lossAmount.setText("-" + DecimalFormatUtils.getDecimal(mPer_stoploss_amount * mDefault_handNum_one * mDefautl_beishu_one * 1.0,2)); //(负)默认触发止损金额(默认1手1倍)
                             mTv_shiyin_amount.setText(DecimalFormatUtils.getDecimal(mPer_stoploss_amount * mDefault_handNum_one * mDefautl_beishu_one * 2.0 ,2)); //(正)默认触发止盈 =默认止损金额(默认1手1倍)*2
                             //默认占用保证金=(正)默认触发止损金额/止损率
                             tv_deal_perrmbmargin.setText("交易费用: " + mPerrmbfee
                                     + " + 占用保证金: " + (mPer_stoploss_amount * mDefault_handNum_one * mDefautl_beishu_one * 1.0) / mStoplossratio
                                     + " = ");//交易费用: 0 + 占用保证金: 0 =
                             //默认合计
                             tv_sum_amount.setText(mPerrmbfee + (mPer_stoploss_amount * mDefault_handNum_one * mDefautl_beishu_one * 1.0) / mStoplossratio + "");
                             //==================================
                             double mBalance = mDataBean.getBalance(); //可用余额(可用资金)
                             if (!IsEmptyUtils.isEmpty(mBalance))
                                 tv_enable_amount.setText("可用资金: " +DecimalFormatUtils.getDecimal(mBalance,2) );


                         } else {
                             ToastUtils.showTextInMiddle(orderBuyBean.getMsg());
                         }
                     }
                 });


     }else{
         //重新去请求经纬度 在进行赋值
         UserGolbal.getInstance().requestLocation();
     }



 }

}
