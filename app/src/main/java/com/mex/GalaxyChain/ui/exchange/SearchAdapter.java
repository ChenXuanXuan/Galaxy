package com.mex.GalaxyChain.ui.exchange;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.HoldPositionBean;
import com.mex.GalaxyChain.bean.PostCloseOrderBean;
import com.mex.GalaxyChain.bean.eventbean.VarietyHoldPosi;
import com.mex.GalaxyChain.bean.eventbean.VarietyHoldPosiBean;
import com.mex.GalaxyChain.bean.requestbean.RequestClosePositionBean;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.common.control.BaseAbsListAdapter;
import com.mex.GalaxyChain.common.control.BaseViewHolder;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.DeviceUtil;
import com.mex.GalaxyChain.utils.IPutils;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;


/**
 * Created by LSJ on 26/4/18.
 */

public class SearchAdapter extends BaseAbsListAdapter<HoldPositionBean.DataBean.ListBean, SearchAdapter.PlatHolder> {
    private VarietyHoldPosiBean varietyHoldPosiBean;
    private setBack setBackListener;

    public SearchAdapter(Context context, setBack setBackListener) {
        super(context);
        this.setBackListener = setBackListener;
    }

    @Override
    public PlatHolder onCreateViewHolder(int viewType) {
        PlatHolder platHolder = new PlatHolder(View.inflate(context, R.layout.layout_adapter_search, null), this);
        return platHolder;
    }

    public void setItemData(VarietyHoldPosiBean varietyHoldPosiBean) {
        this.varietyHoldPosiBean = varietyHoldPosiBean;
    }


    class PlatHolder extends BaseViewHolder<HoldPositionBean.DataBean.ListBean> {

        RelativeLayout rootView;
        RelativeLayout popular;
        ImageView imageView;
        TextView textDes;
        TextView textTime;
        ImageView itemImageView;
        TextView itemText, tv_symbolName, tv_handsNum, tv_direction, tv_lossProfit, tv_stopprofit, tv_stoploss, tv_details_symbolName, tv_details_handsNum, tv_details_direction, tv_autiPinCang_time, tv_lossProfit_detail, tv_oddNumbers, tv_stopprofit_detail, tv_stoploss_detail, tv_kaicang_price, tv_pincang_price, tv_margin,
                tv_trade_free, tv_open_time, tv_fast_pinCang;
        LinearLayout ll_down_arrow, ll_chicang_item_detail, ll_up_arrow, ll_chicang_item;

        public PlatHolder(View convertView, BaseAbsListAdapter absListAdapter) {
            super(convertView, absListAdapter);
            // 持仓列表
            tv_symbolName = (TextView) find(R.id.tv_symbolName); //品种名称
            tv_handsNum = (TextView) find(R.id.tv_handsNum); //手数
            tv_direction = (TextView) find(R.id.tv_direction); //方向
            tv_lossProfit = (TextView) find(R.id.tv_lossProfit);//盈亏
            tv_stopprofit = (TextView) find(R.id.tv_stopprofit);//触发止盈
            tv_stoploss = (TextView) find(R.id.tv_stoploss);//触发止损

            //持仓详情
            tv_details_symbolName = (TextView) find(R.id.tv_details_symbolName);//品种名称
            tv_details_handsNum = (TextView) find(R.id.tv_details_handsNum);//手
            tv_details_direction = (TextView) find(R.id.tv_details_direction);//做多
            tv_autiPinCang_time = (TextView) find(R.id.tv_autiPinCang_time);  //自动平仓时间
            tv_lossProfit_detail = (TextView) find(R.id.tv_lossProfit_detail);//盈亏
            tv_oddNumbers = (TextView) find(R.id.tv_oddNumbers);//单号
            tv_stopprofit_detail = (TextView) find(R.id.tv_stopprofit_detail);//触发止盈
            tv_stoploss_detail = (TextView) find(R.id.tv_stoploss_detail);//触发止损
            tv_kaicang_price = (TextView) find(R.id.tv_kaicang_price);//开仓(价格)
            tv_pincang_price = (TextView) find(R.id.tv_pincang_price);//平仓(价格)
            tv_margin = (TextView) find(R.id.tv_margin); //履约保证金
            tv_trade_free = (TextView) find(R.id.tv_trade_free); //交易费用
            tv_open_time = (TextView) find(R.id.tv_open_time); //开仓时间

            tv_fast_pinCang = (TextView) find(R.id.tv_fast_pinCang); //  快速平仓按钮

            //向下箭头:隐藏持仓item  显示持仓item详情
            ll_down_arrow = (LinearLayout) convertView.findViewById(R.id.ll_down_arrow);
            //持仓的item 的详情布局
            ll_chicang_item_detail = (LinearLayout) convertView.findViewById(R.id.ll_chicang_item_detail);
            // 向上箭头:隐藏item详情,显示item
            ll_up_arrow = (LinearLayout) convertView.findViewById(R.id.ll_up_arrow);
            //持仓的item布局
            ll_chicang_item = (LinearLayout) convertView.findViewById(R.id.ll_chicang_item);


            //向下箭头  扩展
            ll_down_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_chicang_item.setVisibility(View.GONE);
                    ll_chicang_item_detail.setVisibility(View.VISIBLE);
                }
            });


            //向上箭头 收缩
            ll_up_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_chicang_item_detail.setVisibility(View.GONE);
                    ll_chicang_item.setVisibility(View.VISIBLE);


                }
            });

        }

        @Override
        public void loadDataToView(final int position, final HoldPositionBean.DataBean.ListBean listBean) {
            super.loadDataToView(position, listBean);
            if (varietyHoldPosiBean != null) {
                Map<String, VarietyHoldPosi> holdPosiMap = varietyHoldPosiBean.getHashMap();
                for (String key : holdPosiMap.keySet()) {
                    if (key.equals(listBean.getSymbol())) {
                        VarietyHoldPosi varietyHoldPosi = holdPosiMap.get(listBean.getSymbol());
                        tv_symbolName.setText(varietyHoldPosi.symbolname);//商品名称
                        tv_details_symbolName.setText(varietyHoldPosi.symbolname);//商品名称
                        tv_autiPinCang_time.setText(varietyHoldPosi.closetime + "自动平仓"); // 平仓时间
                    }
                }

            }
            tv_handsNum.setText(listBean.getQuantity() + "");
            int bstype = listBean.getBstype();
            if (bstype == 1) {
                tv_direction.setText(Constants.UP);
                tv_details_direction.setText(Constants.UP);
            } else {
                tv_direction.setText(Constants.DROP);
                tv_details_direction.setText(Constants.DROP);
            }

            tv_lossProfit.setText(String.valueOf(listBean.getProfit()));
            tv_stopprofit.setText(String.valueOf(listBean.getStopprofit()));
            tv_stoploss.setText(String.valueOf(listBean.getStoploss()));
            tv_details_handsNum.setText(listBean.getQuantity() + "");
            tv_lossProfit_detail.setText(String.valueOf(listBean.getProfit()));
            tv_oddNumbers.setText(listBean.getShowno() + "");
            tv_stopprofit_detail.setText(String.valueOf(listBean.getStopprofit()));
            tv_stoploss_detail.setText(String.valueOf(listBean.getStoploss()));
            tv_kaicang_price.setText(String.valueOf(listBean.getBuyprice()));
            tv_pincang_price.setText(String.valueOf(listBean.getNewprice()));
            tv_margin.setText(String.valueOf(listBean.getMargin()));
            tv_trade_free.setText(String.valueOf(listBean.getTradefee()));
            tv_open_time.setText(String.valueOf(listBean.getPostime()));

            tv_fast_pinCang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (UserGolbal.getInstance().locationSuccess()) {
                        RequestClosePositionBean requestClosePositionBean = new RequestClosePositionBean();
                        double mMLongitude = UserGolbal.getInstance().getLongitude();
                        double mMLatitude = UserGolbal.getInstance().getLatitude();
                        requestClosePositionBean.setLatitude(mMLatitude);
                        requestClosePositionBean.setLongitude(mMLongitude);
                        requestClosePositionBean.setUsertoken(UserGolbal.getInstance().getUserToken());
                        requestClosePositionBean.setPosid(listBean.getPosid());//持仓ID(订单号)（平仓用）
                        requestClosePositionBean.setOrdertype(listBean.getOrdertype());//订单类型（限价委托 = 1、市价委托 = 2）
                        requestClosePositionBean.setPrice(listBean.getNewprice());  //平仓价格
                        requestClosePositionBean.setQuantity(listBean.getQuantity()); //下单手数
                        requestClosePositionBean.setDeviceType(Constants.ANDROID);
                        requestClosePositionBean.setDevcieModel(Build.MODEL);
                        requestClosePositionBean.setChannelId(Constants.channelId);
                        MyApplication instance = MyApplication.getInstance();
                        String device_identifier = DeviceUtil.getUdid(instance);
                        String deviceID = HttpInterceptor.silentURLEncode(device_identifier);
                        requestClosePositionBean.setDeviceId(deviceID);
                        String IP = IPutils.getIPAddress(MyApplication.appContext);
                        requestClosePositionBean.setIp(IP); //IP
                        requestClosePositionBean.setVersion(AppUtil.getAppVersionName(MyApplication.getInstance()));
                        Gson gson = new Gson();
                        String jsonStr = gson.toJson(requestClosePositionBean);
                        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
                        UserRepo.getInstance().postCloseOrder(requestBody)
                                .subscribe(new Subscriber<PostCloseOrderBean>() {
                                    @Override
                                    public void onCompleted() {
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                    }

                                    @Override
                                    public void onNext(PostCloseOrderBean postCloseOrderBean) {
                                        if (postCloseOrderBean.getCode() == 200) {
                                            ToastUtils.showTextInMiddle(postCloseOrderBean.getMsg());
                                            setBackListener.back();
                                        } else if (postCloseOrderBean.getCode() == 402) {
                                            ToastUtils.showTextInMiddle("已在平仓状态");
                                        } else {
                                            ToastUtils.showTextInMiddle("平仓下单失败,请重试");
                                        }


                                    }
                                });
                    } else {
                        //重新去请求经纬度 在进行赋值
                        UserGolbal.getInstance().requestLocation();
                    }
                }
            });

        }


    }

    public interface setBack {
        void back();
    }
}

