package com.mex.GalaxyChain.ui.asset.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.MultiplBean;
import com.mex.GalaxyChain.bean.OrderBuyBean;
import com.mex.GalaxyChain.bean.PostOrderBean;
import com.mex.GalaxyChain.bean.QuitEvent;
import com.mex.GalaxyChain.bean.TickeBean;
import com.mex.GalaxyChain.bean.eventbean.TagBean;
import com.mex.GalaxyChain.bean.requestbean.RequestOrderBuyBean;
import com.mex.GalaxyChain.bean.requestbean.RequestPostOrderMore;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.event.TickerEvent;
import com.mex.GalaxyChain.mychart.fragment.NewKLineFragment;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.net.websocket.WebSocketHelper;
import com.mex.GalaxyChain.ui.asset.adapter.PriseAdapter;
import com.mex.GalaxyChain.ui.asset.entity.NumEntity;
import com.mex.GalaxyChain.ui.asset.fragment.FenshiFragment;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.DecimalFormatUtils;
import com.mex.GalaxyChain.utils.DeviceUtil;
import com.mex.GalaxyChain.utils.IPutils;
import com.mex.GalaxyChain.utils.LogUtils;
import com.mex.GalaxyChain.utils.StringUtils;
import com.mex.GalaxyChain.utils.ToastUtils;
import com.mex.GalaxyChain.view.CustomWheelView;
import com.mex.GalaxyChain.view.HorizontalListView;
import com.mex.GalaxyChain.view.IndexViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

import static android.view.View.GONE;
import static android.view.View.inflate;

/**
 * @author LSJ
 * @Description:
 * @date 2017/3/16
 * @time 14:40
 */
public class MarketMainAct extends BaseActivity implements View.OnClickListener,
        PopupWindow.OnDismissListener {
    //protected String[] mProvinceDatas;
    private RelativeLayout tv_top_bar_left;
    private RelativeLayout iv_top_bar_right;
    private TextView tv_top_bar_middle;
    private ImageView iv_top_image;
    private TextView fenshi, days, week, month, min;
    private PopupWindow pwMyPopWindow;
    private String type = "1";
    private String typeKx = "0";
    private TextView text_notuse;
    private String instID = "Au(T+D)";
    private IndexViewPager mPaper;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private FenshiFragment fsFragmet;
    private NewKLineFragment klFragment;
    private MyCount mc = new MyCount(10000, 1000);
    private int isRequest = 0;
    private boolean isVisible;
    private LinearLayout llInditor;
    private String selType = "0";//0：分时 1：两日 2：日K...
    private String interval;
    private Dialog dialog;
    private TextView stopLoss, exchange, tv_closeTime, tv_current_cny_rate, tv_open_price;
    private ImageView iv_down_arrow;
    private Dialog wheelDialog;
    private CustomWheelView wheelView;
    // private String value;
    private HorizontalListView hListView;
    private PriseAdapter priseAdapter;
    //  private List<NumEntity> praizeList = new ArrayList<>();
    private TextView tvCurrent, open, high, close, low, tv_downUp_rate, tv_downUp;
    List<String> stoplossAmountWithBSList = new ArrayList<>();// 本地集合 存储  触发止损金额*倍数
    private TextView tv_touch_StopLoss_amount, tv_touch_StopProfi_amount, tv_occupy_perrmbmargin;
    private double mStoplossratio;

    private double mPer_stoploss_amount;

    private TextView mTv_lossAmount;
    private TextView mTv_shiyin_amount, tv_enable_amount, tv_deal_perrmbmargin, tv_sum_amount, tv_confirm_postOrder_makeMoreOrLoss;
    private double mPerrmbfee;
    private double mMLongitude;
    private double mMLatitude;
    private String mToken;

    private int mSelectedHandSum;
    private int mDefault_handNum_one;

    private int mGet_selectedBeiShuNum;
    private int mDefautl_beishu_one;


    public static void launch(Context mContext) {
        mContext.startActivity(new Intent(mContext, MarketMainAct.class));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * i:根据十字线判断导航栏是否隐藏
     * 0：隐藏
     * 1：显示
     */
    public void setVisAndGone(int i) {
        if (i == 0) {
            llInditor.setVisibility(GONE);
        } else {
            llInditor.setVisibility(View.VISIBLE);
        }

    }

    int bsType;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_top_image://全屏
                LandMarketMainAct.launch(this);
                break;

            case R.id.exchange://触发止盈
                showExchange(0);
                break;

            //  case R.id.confirm:
            //   ToastUtils.showCorrectImage(value);
            //    wheelDialog.dismiss();
            //     break;

            case R.id.stopLoss://触发止损
                showExchange(1);
                break;

            //case R.id.lossAmount:
//	 showWheelDialog();
            //  break;

            case R.id.ll_make_nothing: //看跌做空
                bsType = Constants.BUY_DROP; // 2 买跌做空
                showMakeMoreOrLossDialog(bsType);
                break;

            case R.id.ll_makeMore:  //看涨做多
                bsType = Constants.BUY_RISE; //1买涨
                showMakeMoreOrLossDialog(bsType);
                break;

            case R.id.tv_confirm_postOrder_makeMoreOrLoss: //   确定看涨做多/确定看跌做空  按钮
                loadNetPostOrderMakeMoreOrLoss(bsType);

                break;


            case R.id.fenshi://分时
                type = "1";
                selType = "0";
                fsFragmet.setType(instID, type, selType);
                changeType(1);
                break;

            case R.id.days://1分钟
                typeKx = "6";
                selType = "2";
                interval = Constants.ONE_MIN; //  1分钟
                //symbol
                klFragment.setType(instID, typeKx, selType, interval, symbol);
                changeType(3);
                break;

            case R.id.week: //3分钟
                typeKx = "7";
                selType = "3";
                interval = Constants.THREE_MIN; //  3分钟
                klFragment.setType(instID, typeKx, selType, interval, symbol);
                changeType(4);
                break;

            case R.id.month://日K
                typeKx = "8";
                selType = "4";
                interval = Constants.DAY_K; //  日K
                klFragment.setType(instID, typeKx, selType, interval, symbol);
                changeType(5);
                break;

            case R.id.min://分钟(弹出popu)
                showPopu();
                break;

            case R.id.minutes1://5分钟
                typeKx = "5";
                selType = "5";
                interval = Constants.FIVE_MIN; //  5分钟
                klFragment.setType(instID, typeKx, selType, interval, symbol);
                changeMin(1);
                break;

            case R.id.minutes2://15分钟
                typeKx = "4";
                selType = "6";
                interval = Constants.FIFTEEN_MIN; //  15分钟
                klFragment.setType(instID, typeKx, selType, interval, symbol);
                changeMin(2);
                break;

            case R.id.minutes3://30分钟
                changeMin(3);
                typeKx = "3";
                selType = "7";
                interval = Constants.THIRTY_MIN; //  30分钟
                klFragment.setType(instID, typeKx, selType, interval, symbol);
                break;

            case R.id.minutes4://60分钟
                changeMin(4);
                typeKx = "2";
                selType = "8";
                interval = Constants.SIXTY_MIN; //  30分钟
                klFragment.setType(instID, typeKx, selType, interval, symbol);
                break;

        }
    }


    private void showExchange(int type) {
        View popupView = getLayoutInflater().inflate(R.layout.exchange_popu, null);
        PopupWindow window = new PopupWindow(popupView, 800, 600);
//		window.setAnimationStyle(R.style.popup_window_anim);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.update();
        switch (type) {
            case 0:
                window.showAsDropDown(exchange, 0, 20);
                break;

            case 1:
                window.showAsDropDown(stopLoss, 0, 20);
                break;
        }

    }


    //做多对话框 : 判断是否登陆
    public void showMakeMoreOrLossDialog(int bsType) {
        if (UserGolbal.getInstance().isLogin()) {
            dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_dialog_make_more, null);
            initDialog(bsType, inflate);
            dialog.setContentView(inflate);
            Window win = dialog.getWindow();
            win.setGravity(Gravity.BOTTOM);
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.FILL_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);
            dialog.show();
        } else {
            UIHelper.jumptoPhoneNumLoginActivity(this, Constants.FROM_PAYORDER_K_MOKEMORE);
        }


    }


    private void initDialog(int bsType, View inflate) {
        tv_closeTime = (TextView) inflate.findViewById(R.id.tv_closeTime);//平仓时间和涨点挣
        tv_current_cny_rate = (TextView) inflate.findViewById(R.id.tv_current_cny_rate); //当前人民币汇率
        tv_open_price = (TextView) inflate.findViewById(R.id.tv_open_price); //开仓价
        hListView = (HorizontalListView) inflate.findViewById(R.id.horiListView); //几手
        stopLoss = (TextView) inflate.findViewById(R.id.stopLoss);
        exchange = (TextView) inflate.findViewById(R.id.exchange);
        iv_down_arrow = (ImageView) inflate.findViewById(R.id.iv_down_arrow);
        exchange.setOnClickListener(this);
        stopLoss.setOnClickListener(this);
        // inflate.findViewById(R.id.lossAmount).setOnClickListener(this);
        mTv_lossAmount = (TextView) inflate.findViewById(R.id.tv_lossAmount);//触发止损
        mTv_shiyin_amount = (TextView) inflate.findViewById(R.id.tv_shiyin_amount);//触发止盈
        tv_enable_amount = (TextView) inflate.findViewById(R.id.tv_enable_amount); //可用资金
        tv_deal_perrmbmargin = (TextView) inflate.findViewById(R.id.tv_deal_perrmbmargin);//交易费用0 +占用保证金0
        tv_sum_amount = (TextView) inflate.findViewById(R.id.tv_sum_amount);  //合计   tv_confirm_postOrder_makeMoreOrLoss
        tv_confirm_postOrder_makeMoreOrLoss = (TextView) inflate.findViewById(R.id.tv_confirm_postOrder_makeMoreOrLoss);//确定做多 按钮
        if (bsType == Constants.BUY_RISE) {
            tv_confirm_postOrder_makeMoreOrLoss.setText("确定看涨做多");
            tv_confirm_postOrder_makeMoreOrLoss.setBackgroundColor(getResources().getColor(R.color.market_red));
        } else {
            tv_confirm_postOrder_makeMoreOrLoss.setText("确定看跌做空");
            tv_confirm_postOrder_makeMoreOrLoss.setBackgroundColor(getResources().getColor(R.color.rgb_150_177_248));
        }

        tv_confirm_postOrder_makeMoreOrLoss.setOnClickListener(this);

        // private List<NumEntity> praizeList = new ArrayList<>();
        //  final   List<NumEntity>  numEntityList =UserGolbal.getInstance().getNumEntityList(); //几手集合从那个界面来??
        // UserGolbal.getInstance().setPerprofitnumber(symbolInfosBean.getPerprofitnumber());  //perprofitnumber 收益点数
        //UserGolbal.getInstance().setPerprofit(symbolInfosBean.getPerprofit()); //perprofit 收益
        //UserGolbal.getInstance().setCurrencytype(symbolInfosBean.getCurrencytype());  //currencytype;//币种类型


        // double perprofitnumber = 0; //收益点数
        //  double perprofit = 0;  //perprofit 收益
        //  String currencytype = null; //币种类型

        // List<NumEntity> numEntityList = null;//几手集合从那个界面来??
        // List<MultiplBean> multiplBeanList = null;//存储 几倍 集合
        /*if (UserGolbal.getInstance().getTag() == Constants.ALL_VARIETY) {
            closeTime = UserGolbal.getInstance().getClosetime();
            symbol = UserGolbal.getInstance().getSymbol();
            perprofitnumber = UserGolbal.getInstance().getPerprofitnumber();
            perprofit = UserGolbal.getInstance().getPerprofit();
            currencytype = UserGolbal.getInstance().getCurrencytype();
            numEntityList = UserGolbal.getInstance().getNumEntityList();
            multiplBeanList = UserGolbal.getInstance().getMultiplBeanList();

        } else if (UserGolbal.getInstance().getTag() == Constants.GLOBAL_INDEX) {
            closeTime = UserGolbal.getInstance().getClosetime();
            symbol = UserGolbal.getInstance().getSymbol();
            perprofitnumber = UserGolbal.getInstance().getPerprofitnumber();
            perprofit = UserGolbal.getInstance().getPerprofit();
            currencytype = UserGolbal.getInstance().getCurrencytype();
            numEntityList = UserGolbal.getInstance().getNumEntityList();
            multiplBeanList = UserGolbal.getInstance().getMultiplBeanList();

        } else if (UserGolbal.getInstance().getTag() == Constants.CRYPTO_CURRENCY) {
            closeTime = UserGolbal.getInstance().getClosetime();
            symbol = UserGolbal.getInstance().getSymbol();
            perprofitnumber = UserGolbal.getInstance().getPerprofitnumber();
            perprofit = UserGolbal.getInstance().getPerprofit();
            currencytype = UserGolbal.getInstance().getCurrencytype();
            numEntityList = UserGolbal.getInstance().getNumEntityList();
            multiplBeanList = UserGolbal.getInstance().getMultiplBeanList();

        }*/

        if (numEntityList != null && numEntityList.size() > 0) {
            for (int i = 0; i < numEntityList.size(); i++) {
                NumEntity numEntity = numEntityList.get(i);
                if (i == 0) {
                    numEntity.setSelcet(true);//默认1手是选中的
                    String[] defautl_handNum_one = numEntity.getHandSum().split("手");
                    mDefault_handNum_one = Integer.valueOf(defautl_handNum_one[0]);//默认 mDefault_handNum_one=1(手)
                } else {
                    numEntity.setSelcet(false);
                }

            }
            mSelectedHandSum = mDefault_handNum_one;
            priseAdapter = new PriseAdapter(MarketMainAct.this);
            priseAdapter.setItems(numEntityList);
            hListView.setAdapter(priseAdapter);
            priseAdapter.notifyDataSetChanged();
            setOnItemClickHandsNumForHorizontalListView(hListView, numEntityList);
        }

        //默认倍数 1(倍)
        if (multiplBeanList != null && multiplBeanList.size() > 0) {
            String[] striArr = multiplBeanList.get(0).getName().split("倍");
            mDefautl_beishu_one = Integer.valueOf(striArr[0]);
            mGet_selectedBeiShuNum = mDefautl_beishu_one;
        }

        //一旦点击看涨买多按钮 弹出一个对话框  就立马做一个网络请求操作
        loadOrderPayBuyData(bsType, closeTime, perprofitnumber, perprofit, multiplBeanList, symbol, currencytype, wheelView);

        //  箭头iv_down_arrow弹出 设置 倍数(1234倍)的对话框,是对应的 止损 止盈 发生变化
        setOnClicekForChangeBeiShuDialog(multiplBeanList);


    }


    // 显示 wheel 对话框(止盈止损对话框)
    //https://blog.csdn.net/fancylovejava/article/details/21617553 对话框
    private void showWheelDialog(List<MultiplBean> multiplBeanList) {
        //ToastUtils.showTextInMiddle(multiplBeanList.get(0).getName()+"");//测试那个界面哪一行 的倍数(没问题)
        wheelDialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_dialog_wheel, null);
        initWheelDialog(inflate, multiplBeanList);
        wheelDialog.setContentView(inflate);
        Window win = wheelDialog.getWindow();
        win.setGravity(Gravity.BOTTOM);
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay(); // 获取屏幕宽、高用

        WindowManager.LayoutParams lp = win.getAttributes(); // 获取对话框当前的参数值
        // lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        // lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = (int) (display.getHeight() * 0.7);// 高度设置为屏幕的0.5
        lp.width = (int) (display.getWidth() * 0.8); // 宽度设置为屏幕的0.65
        lp.y = 150; // 对话框居下偏移150 新位置Y坐标
        lp.alpha = 0.8f; // 对话框的透明度
        win.setAttributes(lp);
        wheelDialog.show();
    }
    String value;

    private void initWheelDialog(View inflate, List<MultiplBean> multiplBeanList) {//multiplBeanList 倍数集合
        stoplossAmountWithBSList.clear();
        for (MultiplBean multiplBean : multiplBeanList) { //便利倍数集合
            String beiShu = multiplBean.getName();//?倍
            String[] striArr = beiShu.split("倍");
            int beishu = Integer.valueOf(striArr[0]);
            LogUtils.d("TAG", "倍数->" + beishu);
            if (!isEmpty(mSelectedHandSum) && mSelectedHandSum != 0) {
                LogUtils.d("TAG", "手数->" + mSelectedHandSum);
                if (!isEmpty(mPer_stoploss_amount)) {
                    String sum_stoplossAmountWithBeiShou = "-" + mPer_stoploss_amount * beishu * mSelectedHandSum * 1.0;//(负)(总)触发止损金额=(负)单价止损金额*?手*?倍
                    stoplossAmountWithBSList.add(sum_stoplossAmountWithBeiShou);
                }
            }

        }
        wheelView = (CustomWheelView) inflate.findViewById(R.id.wheelView);
        wheelView.setSeletion(0);
        wheelView.setItems(stoplossAmountWithBSList);
        tv_touch_StopLoss_amount = (TextView) inflate.findViewById(R.id.tv_touch_StopLoss_amount);//显示(总)(负)触发止损金额
        tv_touch_StopProfi_amount = (TextView) inflate.findViewById(R.id.tv_touch_StopProfi_amount);//触发止盈金额
        tv_occupy_perrmbmargin = (TextView) inflate.findViewById(R.id.tv_occupy_perrmbmargin); //占用保证金
        wheelView.setOnWheelViewListener(new CustomWheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                //int select = selectedIndex - 1 < 0 ? 0 : selectedIndex - 1;
                value = item;

                //如何计算倍数 =触发止损金额(带倍数手数)item/单价止损金额(mPer_stoploss_amount)/手数(mSelectedHandSum)
                if (!isEmpty(mPer_stoploss_amount)) {
                    mGet_selectedBeiShuNum = (int) (Math.abs(Double.valueOf(item)) / mPer_stoploss_amount / mSelectedHandSum);

                }

                tv_touch_StopLoss_amount.setText(Double.valueOf(item) + "");//(负)(总)触发止损金额(带倍数手数)=item=value
                tv_touch_StopProfi_amount.setText(Math.abs(Double.valueOf(item)) * 2.0 + ""); //触发止盈金额=(正)(总)触发止损金额(带倍数手数)*2
                if (!isEmpty(mStoplossratio) && mStoplossratio != 0.0) {
                    tv_occupy_perrmbmargin.setText(Math.abs(Double.valueOf(item)) / mStoplossratio + "");//占用保证金=(正)触发止损金额/止损率
                    LogUtils.d("TAG", "止损率" + mStoplossratio);
                }

            }
        });

        inflate.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheelDialog.dismiss();

                if (!isEmpty(value)) {
                    mTv_lossAmount.setText(Double.valueOf(value) + "");//(负)(总价)止损金额
                    mTv_shiyin_amount.setText(DecimalFormatUtils.getDecimal(Math.abs(Double.valueOf(value)) * 2.0,2));//触发止盈
                }

                if (!isEmpty(mPerrmbfee, value, mStoplossratio) && mStoplossratio != 0.0) {
                    tv_deal_perrmbmargin.setText("交易费用: " + mPerrmbfee
                            + " + 占用保证金: " + Math.abs(Double.valueOf(value)) / mStoplossratio + " = ");
                    tv_sum_amount.setText(mPerrmbfee + Math.abs(Double.valueOf(value)) / mStoplossratio + "");//价格合计
                }


            }
        });
    }


    //下单确定买涨做多
    private void loadNetPostOrderMakeMoreOrLoss(int bsType) {
        RequestPostOrderMore requestPostOrderMore = new RequestPostOrderMore();
        if (!isEmpty(mMLongitude, mMLatitude)) {
            requestPostOrderMore.setLatitude(mMLatitude);
            requestPostOrderMore.setLongitude(mMLongitude);

        } else {
            //重新去请求经纬度 在进行赋值
            UserGolbal.getInstance().requestLocation();
        }

        if (!isEmpty(mToken)) requestPostOrderMore.setUsertoken(mToken);
        if (!isEmpty(symbol)) requestPostOrderMore.setSymbol(symbol);
        requestPostOrderMore.setOrdertype(Constants.MARKET_PRICE);
        // ******
        ////委托价格(价格合计)
        String str_sum_amout = tv_sum_amount.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(str_sum_amout))
            return;
        requestPostOrderMore.setPrice(Double.valueOf(str_sum_amout));
        if (isEmpty(str_sum_amout)) {
            ToastUtils.showTextInMiddle("合计金额不能为空");
            return;
        }
        //  int bsType=Constants.BUY_RISE;
        if (bsType == Constants.BUY_RISE) {
            requestPostOrderMore.setBstype(Constants.BUY_RISE);//1.买涨
        } else if (bsType == Constants.BUY_DROP) {
            requestPostOrderMore.setBstype(Constants.BUY_DROP);//2  买跌
        } else {
            return;
        }


        requestPostOrderMore.setQuantity(mSelectedHandSum);//手数
        if (isEmpty(mSelectedHandSum)) {
            ToastUtils.showTextInMiddle("请选择手数");
            return;
        }


//  mTv_shiyin_amount.setText(Math.abs(Double.valueOf(value))*2+"");//触发止盈
        //  if(!isEmpty(value)){  //(负)触发止损金额(带倍数手数)=item=value
        //    requestPostOrderMore.setStoploss(Double.valueOf(value)); //止损
        //    requestPostOrderMore.setStopprofit(Math.abs(Double.valueOf(value))*2);  // 止盈
        // }else{
        //     ToastUtils.showTextInMiddle("触发止损不能为空");
        //      return;
        //  }

        requestPostOrderMore.setStoplosstimes(mGet_selectedBeiShuNum);//止损倍数
        if (isEmpty(mGet_selectedBeiShuNum)) {
            ToastUtils.showTextInMiddle("请选择倍数");
            return;
        }
        String IP = IPutils.getIPAddress(MyApplication.appContext);
        requestPostOrderMore.setIp(IP); //IP
        requestPostOrderMore.setDeviceType(Constants.ANDROID);
        requestPostOrderMore.setDevcieModel(Build.MODEL);
        requestPostOrderMore.setChannelId(Constants.channelId);
        requestPostOrderMore.setVersion(AppUtil.getAppVersionName(MyApplication.getInstance()));
        MyApplication instance = MyApplication.getInstance();
        String device_identifier = DeviceUtil.getUdid(instance);
        String deviceID = HttpInterceptor.silentURLEncode(device_identifier);
        requestPostOrderMore.setDeviceId(deviceID);
        showLoading("正在下单中...");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(requestPostOrderMore);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);

        UserRepo.getInstance().PostOrderMore(requestBody)
                .subscribe(new Subscriber<PostOrderBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissLoading();
                        ToastUtils.showTextInMiddle("下单失败请重试");
                    }

                    @Override
                    public void onNext(PostOrderBean postOrderBean) {

                        dismissLoading();
                        int code = postOrderBean.getCode();
                        if (code == 200) {
                            ToastUtils.showTextInMiddle("下单成功");
                            UIHelper.jumptoMainActivity(MarketMainAct.this, "");//下单成功,跳到对应的已登陆持仓界面
                            TagBean tagBean = new TagBean();
                            tagBean.setTag(Constants.FROM_K_BUTTON_PAYORDERMORE);
                            //tagBean.setSymbol(symbol);
                            // tagBean.setSymbolName_ch(symbolName);
                            //tagBean.setCloseTime_ch(closeTime);
                            EventBus.getDefault().post(tagBean);//eventbus 发送 标签到MainActivity 制定1 持仓
                            dialog.dismiss(); //网络请求成功后 要关闭对话框后跳转到 持仓界面
                            finish();
                        } else if (code == 402) {
                            ToastUtils.showTextInMiddle("非交易时间不可下单交易");
                            return;
                        } else if (code == 404) {
                            ToastUtils.showTextInMiddle("商品数据不存在");
                            return;
                        }
                    }
                });


    }


    //宾哥的 号码 登陆  18612875467 配置了资金账号
    private void loadOrderPayBuyData(int bsType, final String closeTime, final double perprofitnumber, final double perprofit, final List<MultiplBean> multiplBeanList, final String symbol, final String currencytype, final CustomWheelView wheelView) {

        // 做网络请求 下单买入

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
                            if (orderBuyBean.getCode() == 200) {
                                ToastUtils.showTextInMiddle(orderBuyBean.getMsg() + symbol);
                                OrderBuyBean.DataBean dataBean = orderBuyBean.getData();
                                double rate = dataBean.getRate(); //汇率
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
                                mPerrmbfee = dataBean.getPerrmbfee(); //每笔手续费(交易费用)perrmbfee
                                double perrmbmargin = dataBean.getPerrmbmargin();//(履约保证金)
                                mStoplossratio = dataBean.getStoplossratio();//止损率
                                mPer_stoploss_amount = perrmbmargin * mStoplossratio;//单价触发止损金额(不带倍手)= 履约保证金*止损率
                                // 一进入界面 默认(1手1倍)  显示 默认止损金额  默认止盈金额  默认占用保证金

                                mTv_lossAmount.setText("-" + DecimalFormatUtils.getDecimal(mPer_stoploss_amount * mDefault_handNum_one * mDefautl_beishu_one * 1.0,2)); //(负)默认触发止损金额(默认1手1倍)


                                mTv_shiyin_amount.setText(DecimalFormatUtils.getDecimal(mPer_stoploss_amount * mDefault_handNum_one * mDefautl_beishu_one * 2.0 ,2)); //(正)默认触发止盈 =默认止损金额(默认1手1倍)*2
                                //默认占用保证金=(正)默认触发止损金额/止损率
                                tv_deal_perrmbmargin.setText("交易费用: " + mPerrmbfee
                                        + " + 占用保证金: " + (mPer_stoploss_amount * mDefault_handNum_one * mDefautl_beishu_one * 1.0) / mStoplossratio
                                        + " = ");//交易费用: 0 + 占用保证金: 0 =
                                //默认合计
                                tv_sum_amount.setText(mPerrmbfee + (mPer_stoploss_amount * mDefault_handNum_one * mDefautl_beishu_one * 1.0) / mStoplossratio + "");
                                //==================================
                                double mBalance = dataBean.getBalance(); //可用余额(可用资金)
                                if (!isEmpty(mBalance))
                                    tv_enable_amount.setText("可用资金: " +DecimalFormatUtils.getDecimal(mBalance,2) );


                            } else {
                                ToastUtils.showTextInMiddle(orderBuyBean.getMsg());
                            }
                        }
                    });

        } else {
            //重新去请求经纬度 在进行赋值
            UserGolbal.getInstance().requestLocation();


        }
    }


    private void changeType(int i) {
        Drawable arrow = ContextCompat.getDrawable(getActivity(), R.drawable.arrow);
        arrow.setBounds(0, 0, arrow.getMinimumWidth(), arrow.getMinimumHeight());
        Drawable market_pre = ContextCompat.getDrawable(getActivity(), R.drawable.market_pre);
        market_pre.setBounds(0, 0, market_pre.getMinimumWidth(), market_pre.getMinimumHeight());
        Drawable market_nor = ContextCompat.getDrawable(getActivity(), R.drawable.market_nor);
        market_nor.setBounds(0, 0, market_nor.getMinimumWidth(), market_nor.getMinimumHeight());

        switch (i) {
            case 1:
                mPaper.setCurrentItem(0);
                min.setText("分钟");
                fenshi.setTextColor(Color.parseColor("#ea3e3e"));
                days.setTextColor(Color.parseColor("#333333"));
                week.setTextColor(Color.parseColor("#333333"));
                month.setTextColor(Color.parseColor("#333333"));
                min.setTextColor(Color.parseColor("#333333"));
                fenshi.setCompoundDrawables(null, null, null, market_pre);
                days.setCompoundDrawables(null, null, null, market_nor);
                week.setCompoundDrawables(null, null, null, market_nor);
                month.setCompoundDrawables(null, null, null, market_nor);
                min.setCompoundDrawables(null, null, arrow, market_nor);
                break;

            case 2:
                mPaper.setCurrentItem(0);
                min.setText("分钟");
                fenshi.setTextColor(Color.parseColor("#333333"));
                days.setTextColor(Color.parseColor("#333333"));
                week.setTextColor(Color.parseColor("#333333"));
                month.setTextColor(Color.parseColor("#333333"));
                min.setTextColor(Color.parseColor("#333333"));
                fenshi.setCompoundDrawables(null, null, null, market_nor);
                days.setCompoundDrawables(null, null, null, market_nor);
                week.setCompoundDrawables(null, null, null, market_nor);
                month.setCompoundDrawables(null, null, null, market_nor);
                min.setCompoundDrawables(null, null, arrow, market_nor);
                break;

            case 3:
                mPaper.setCurrentItem(1);
                min.setText("分钟");
                fenshi.setTextColor(Color.parseColor("#333333"));
                days.setTextColor(Color.parseColor("#ea3e3e"));
                week.setTextColor(Color.parseColor("#333333"));
                month.setTextColor(Color.parseColor("#333333"));
                min.setTextColor(Color.parseColor("#333333"));
                fenshi.setCompoundDrawables(null, null, null, market_nor);
                days.setCompoundDrawables(null, null, null, market_pre);
                week.setCompoundDrawables(null, null, null, market_nor);
                month.setCompoundDrawables(null, null, null, market_nor);
                min.setCompoundDrawables(null, null, arrow, market_nor);
                break;

            case 4:
                mPaper.setCurrentItem(1);
                min.setText("分钟");
                fenshi.setTextColor(Color.parseColor("#333333"));
                days.setTextColor(Color.parseColor("#333333"));
                week.setTextColor(Color.parseColor("#ea3e3e"));
                month.setTextColor(Color.parseColor("#333333"));
                min.setTextColor(Color.parseColor("#333333"));
                fenshi.setCompoundDrawables(null, null, null, market_nor);
                days.setCompoundDrawables(null, null, null, market_nor);
                week.setCompoundDrawables(null, null, null, market_pre);
                month.setCompoundDrawables(null, null, null, market_nor);
                min.setCompoundDrawables(null, null, arrow, market_nor);
                break;

            case 5:
                mPaper.setCurrentItem(1);
                min.setText("分钟");
                fenshi.setTextColor(Color.parseColor("#333333"));
                days.setTextColor(Color.parseColor("#333333"));
                week.setTextColor(Color.parseColor("#333333"));
                month.setTextColor(Color.parseColor("#ea3e3e"));
                min.setTextColor(Color.parseColor("#333333"));
                fenshi.setCompoundDrawables(null, null, null, market_nor);
                days.setCompoundDrawables(null, null, null, market_nor);
                week.setCompoundDrawables(null, null, null, market_nor);
                month.setCompoundDrawables(null, null, null, market_pre);
                min.setCompoundDrawables(null, null, arrow, market_nor);
                break;

        }
    }

    private void showPopu() {
        if (pwMyPopWindow.isShowing()) {
            pwMyPopWindow.dismiss();
        } else {
            int xpos = getActivity().getWindowManager().getDefaultDisplay().getWidth() - pwMyPopWindow.getWidth();
            pwMyPopWindow.showAsDropDown(min, 50, 0);// 显示
            backgroundAlpha();
        }
    }

    private void changeMin(int i) {
        pwMyPopWindow.dismiss();
        mPaper.setCurrentItem(1);
        switch (i) {
            case 1:
                min.setText("5分钟");
                break;

            case 2:
                min.setText("15分钟");
                break;

            case 3:
                min.setText("30分钟");
                break;

            case 4:
                min.setText("60分钟");
                break;

        }

        Drawable arrow = ContextCompat.getDrawable(getActivity(), R.drawable.arrow);
        arrow.setBounds(0, 0, arrow.getMinimumWidth(), arrow.getMinimumHeight());
        Drawable market_pre = ContextCompat.getDrawable(getActivity(), R.drawable.market_pre);
        market_pre.setBounds(0, 0, market_pre.getMinimumWidth(), market_pre.getMinimumHeight());
        Drawable market_nor = ContextCompat.getDrawable(getActivity(), R.drawable.market_nor);
        market_nor.setBounds(0, 0, market_nor.getMinimumWidth(), market_nor.getMinimumHeight());
        min.setTextColor(Color.parseColor("#ea3e3e"));
        days.setTextColor(Color.parseColor("#333333"));
        week.setTextColor(Color.parseColor("#333333"));
        month.setTextColor(Color.parseColor("#333333"));
        fenshi.setTextColor(Color.parseColor("#333333"));
        fenshi.setCompoundDrawables(null, null, null, market_nor);
        days.setCompoundDrawables(null, null, null, market_nor);
        week.setCompoundDrawables(null, null, null, market_nor);
        month.setCompoundDrawables(null, null, null, market_nor);
        min.setCompoundDrawables(null, null, arrow, market_pre);
    }


    private void setOnClicekForChangeBeiShuDialog(final List<MultiplBean> multiplBeanList) {
        iv_down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWheelDialog(multiplBeanList);
            }
        });


    }

    private void setOnItemClickHandsNumForHorizontalListView(HorizontalListView hListView, final List<NumEntity> numEntityList) {
        hListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                //需不需要默认选中1手 不知道
                for (int i = 0; i < numEntityList.size(); i++) {
                    if (i == pos) { //被选中的几手
                        // 被选中的?手 对象  NumEntity
                        NumEntity numEntity = numEntityList.get(i);
                        numEntity.setSelcet(true);//?手 被选中的状态
                        String mSelected_handSum = numEntity.getHandSum();//?手  被选中的 手数
                        String[] selectedHandSumArr = mSelected_handSum.split("手");
                        String handSumStr = selectedHandSumArr[0];
                        mSelectedHandSum = Integer.valueOf(handSumStr);


                        //切换手数(1 2 3 5 10 )(改变默认手数)  金额改变 对应的 止损金额 止盈金额  占用保证金  合计
                        mTv_lossAmount.setText("-" + DecimalFormatUtils.getDecimal(mPer_stoploss_amount * mSelectedHandSum * mDefautl_beishu_one * 1.0,2)); //(负)改变触发止损金额(改变?手1倍)
                        mTv_shiyin_amount.setText(DecimalFormatUtils.getDecimal(mPer_stoploss_amount * mSelectedHandSum * mDefautl_beishu_one * 2.0,2)); //(正)改变触发止盈金额 =改变的触发止损金额(改变?手1倍)*2
                        //改变占用保证金=(正)改变触发止损金额(改变?手1倍)/止损率
                        tv_deal_perrmbmargin.setText("交易费用: " + DecimalFormatUtils.getDecimal(mPerrmbfee,2)
                                + " + 占用保证金: " +DecimalFormatUtils.getDecimal((mPer_stoploss_amount * mSelectedHandSum * mDefautl_beishu_one * 1.0) / mStoplossratio,2)
                                + " = ");
                        //改变的合计
                        tv_sum_amount.setText(DecimalFormatUtils.getDecimal(mPerrmbfee + (mPer_stoploss_amount * mSelectedHandSum * mDefautl_beishu_one * 1.0) / mStoplossratio,2));
                    } else {
                        numEntityList.get(i).setSelcet(false);
                    }
                }
                priseAdapter.notifyDataSetChanged();
            }
        });

    }

    public void backgroundAlpha() {
        ColorDrawable cd = new ColorDrawable(0x000000);
        pwMyPopWindow.setBackgroundDrawable(cd);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.9f;
        getActivity().getWindow().setAttributes(lp);
    }

    @SuppressLint("NewApi")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TickerEvent event) {
        if (event != null) {
            TickeBean tickeBean = event.getTickeBean();
            if (tvCurrent != null) {
                String newestPrice = tvCurrent.getText().toString();
                if (tickeBean != null && !isEmpty(newestPrice) && tickeBean.getOfferPrice() != null) {
                    double offerPrice = Double.parseDouble(tickeBean.getOfferPrice()); //最新价 (卖价) 12841.99
                    double preClose = Double.parseDouble(tickeBean.getPreClose());  //昨日收盘价12840.34
                    //  涨跌幅度=(最新价 -  昨日收盘价)100% /  昨日收盘价
                    double upsAndDowns_rate = (offerPrice - preClose) * 100 / preClose;
                    if (offerPrice == preClose) {
                        tv_downUp_rate.setTextColor(getResources().getColor(R.color.device_button_normal));
                        tv_downUp.setTextColor(getResources().getColor(R.color.device_button_normal));
                    } else if (offerPrice > preClose) {
                        tv_downUp_rate.setTextColor(getResources().getColor(R.color.red));
                        tv_downUp.setTextColor(getResources().getColor(R.color.red));
                    } else if (offerPrice < preClose) {
                        tv_downUp_rate.setTextColor(getResources().getColor(R.color.light_green));
                        tv_downUp.setTextColor(getResources().getColor(R.color.light_green));
                    } else {
                        tv_downUp_rate.setTextColor(getResources().getColor(R.color.device_button_normal));
                        tv_downUp.setTextColor(getResources().getColor(R.color.device_button_normal));
                    }


                    tvCurrent.setText(tickeBean.getOfferPrice()); //最新价
                    NumberFormat mNumberFormat = MyApplication.getInstance().mNumberFormat;
                    mNumberFormat.setMaximumFractionDigits(2);
                    tv_downUp_rate.setText(mNumberFormat.format(upsAndDowns_rate) + "%"); //涨跌幅
                    tv_downUp.setText(mNumberFormat.format(offerPrice - preClose));    //涨跌=(最新价 -  昨日收盘价)
                    open.setText(tickeBean.getOpen());//今开
                    high.setText(tickeBean.getHigh());//最高
                    low.setText(tickeBean.getLow());//最低
                    close.setText(tickeBean.getPreClose());//昨收

                }


            }

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.anew_market_main);
        initView();
        initData();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        WebSocketHelper.startSocketPairs();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        WebSocketHelper.closeSocketPairs();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        if (mc != null) {
            mc.cancel();
        }
//		WebSocketHelper.closeSocketPairs();
        super.onPause();
    }

    @Override
    protected void onResume() {
        WebSocketHelper.checkPairSocket();
        super.onResume();
    }

    private void initView() {
        tvCurrent = (TextView) findViewById(R.id.tvCurrent); //最新价
        high = (TextView) findViewById(R.id.high); // 最高价
        low = (TextView) findViewById(R.id.low);//最低价
        open = (TextView) findViewById(R.id.open); //今开
        close = (TextView) findViewById(R.id.close); // 昨收价
        tv_downUp_rate = (TextView) findViewById(R.id.tv_downUp_rate); //涨跌幅
        tv_downUp = (TextView) findViewById(R.id.tv_downUp); //涨跌
        mPaper = (IndexViewPager) findViewById(R.id.view_pager);
        mPaper.setScanScroll(false);
        text_notuse = (TextView) findViewById(R.id.text_notuse);
        text_notuse.requestFocus();
        findViewById(R.id.tv_top_bar_left).setVisibility(View.INVISIBLE);
        llInditor = (LinearLayout) findViewById(R.id.ll_inditor);

        findViewById(R.id.ll_makeMore).setOnClickListener(this);

        findViewById(R.id.ll_make_nothing).setOnClickListener(this);
        initOthreView();
        initFragment();
    }

    public void initData() {
    }


    String symbolName = "";
    String symbol = null;//symbol不同
    String closeTime = null; // 平仓时间
    double perprofitnumber = 0; //收益点数
    double perprofit = 0;  //perprofit 收益
    String currencytype = null; //币种类型
    List<NumEntity> numEntityList = null;//几手集合从那个界面来??
    List<MultiplBean> multiplBeanList = null;//存储 几倍 集合

    private void initOthreView() {
        fenshi = (TextView) findViewById(R.id.fenshi);
        days = (TextView) findViewById(R.id.days);
        week = (TextView) findViewById(R.id.week);
        month = (TextView) findViewById(R.id.month);
        min = (TextView) findViewById(R.id.min);
        tv_top_bar_middle = (TextView) findViewById(R.id.tv_top_bar_middle);
        tv_top_bar_left = (RelativeLayout) findViewById(R.id.tv_top_bar_left);
        iv_top_image = (ImageView) findViewById(R.id.iv_top_image);
        iv_top_bar_right = (RelativeLayout) findViewById(R.id.iv_top_bar_right);
        iv_top_image = (ImageView) findViewById(R.id.iv_top_image);
        iv_top_image.setOnClickListener(this);


        if (UserGolbal.getInstance().getTag() == Constants.ALL_VARIETY) { // 全部品种
            symbolName = UserGolbal.getInstance().getSymbolname();

        } else if (UserGolbal.getInstance().getTag() == Constants.GLOBAL_INDEX) { ////全球指数
            symbolName = UserGolbal.getInstance().getSymbolname();

        } else if (UserGolbal.getInstance().getTag() == Constants.CRYPTO_CURRENCY) { //加密货币
            symbolName = UserGolbal.getInstance().getSymbolname();
        }
        tv_top_bar_middle.setText(symbolName);


        if (UserGolbal.getInstance().getTag() == Constants.ALL_VARIETY) {
            closeTime = UserGolbal.getInstance().getClosetime();
            symbol = UserGolbal.getInstance().getSymbol();
            perprofitnumber = UserGolbal.getInstance().getPerprofitnumber();
            perprofit = UserGolbal.getInstance().getPerprofit();
            currencytype = UserGolbal.getInstance().getCurrencytype();
            numEntityList = UserGolbal.getInstance().getNumEntityList();
            multiplBeanList = UserGolbal.getInstance().getMultiplBeanList();

        } else if (UserGolbal.getInstance().getTag() == Constants.GLOBAL_INDEX) {
            closeTime = UserGolbal.getInstance().getClosetime();
            symbol = UserGolbal.getInstance().getSymbol();
            perprofitnumber = UserGolbal.getInstance().getPerprofitnumber();
            perprofit = UserGolbal.getInstance().getPerprofit();
            currencytype = UserGolbal.getInstance().getCurrencytype();
            numEntityList = UserGolbal.getInstance().getNumEntityList();
            multiplBeanList = UserGolbal.getInstance().getMultiplBeanList();

        } else if (UserGolbal.getInstance().getTag() == Constants.CRYPTO_CURRENCY) {
            closeTime = UserGolbal.getInstance().getClosetime();
            symbol = UserGolbal.getInstance().getSymbol();
            perprofitnumber = UserGolbal.getInstance().getPerprofitnumber();
            perprofit = UserGolbal.getInstance().getPerprofit();
            currencytype = UserGolbal.getInstance().getCurrencytype();
            numEntityList = UserGolbal.getInstance().getNumEntityList();
            multiplBeanList = UserGolbal.getInstance().getMultiplBeanList();

        }


        fenshi.setOnClickListener(this);
        days.setOnClickListener(this);
        week.setOnClickListener(this);
        month.setOnClickListener(this);
        min.setOnClickListener(this);
        iv_top_bar_right.setOnClickListener(this);
        tv_top_bar_left.setOnClickListener(this);
        tv_top_bar_middle.setOnClickListener(this);
        initPopu();
    }

    private void initFragment() {
        fsFragmet = new FenshiFragment();
        klFragment = new NewKLineFragment();
        klFragment.setObject(MarketMainAct.this);
        fsFragmet.setObject(MarketMainAct.this);

        mFragments.add(fsFragmet);
        mFragments.add(klFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
        };
        mPaper.setAdapter(mAdapter);
        mPaper.setOffscreenPageLimit(4);
    }

    private void initPopu() {
        View view = inflate(getActivity(), R.layout.account_min_popu, null);
        pwMyPopWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
        pwMyPopWindow.setBackgroundDrawable(new BitmapDrawable());
        pwMyPopWindow.setOutsideTouchable(true);
        pwMyPopWindow.setFocusable(true);
        pwMyPopWindow.update();
        pwMyPopWindow.setOnDismissListener(this);
        pwMyPopWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        pwMyPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        TextView minutes1 = (TextView) view.findViewById(R.id.minutes1);
        TextView minutes2 = (TextView) view.findViewById(R.id.minutes2);
        TextView minutes3 = (TextView) view.findViewById(R.id.minutes3);
        TextView minutes4 = (TextView) view.findViewById(R.id.minutes4);

        minutes1.setOnClickListener(this);
        minutes2.setOnClickListener(this);
        minutes3.setOnClickListener(this);
        minutes4.setOnClickListener(this);

    }

    @Override
    public void onDismiss() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 1f;
        getActivity().getWindow().setAttributes(lp);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(QuitEvent event) {

    }


    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (isRequest == 0 && mc != null) {
                mc.start();
            }
        }
    }

}
