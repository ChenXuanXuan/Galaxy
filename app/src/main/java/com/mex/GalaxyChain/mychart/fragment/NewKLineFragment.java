package com.mex.GalaxyChain.mychart.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lsj.kchart.kchartlib.chart.BaseKChartView;
import com.lsj.kchart.kchartlib.chart.KChartView;
import com.lsj.kchart.kchartlib.chart.formatter.DateFormatter;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.HistoryKLineBean;
import com.mex.GalaxyChain.bean.NewestKLineBean;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.mychart.chart.kchart.DataHelper;
import com.mex.GalaxyChain.mychart.chart.kchart.chart.KChartAdapter;
import com.mex.GalaxyChain.mychart.chart.kchart.chart.KLineEntity;
import com.mex.GalaxyChain.mychart.obser.IObserver;
import com.mex.GalaxyChain.mychart.utils.LogUtils;
import com.mex.GalaxyChain.mychart.utils.NewColorUtil;
import com.mex.GalaxyChain.net.HttpInterceptor;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.ui.asset.activity.LandMarketMainAct;
import com.mex.GalaxyChain.ui.asset.activity.MarketMainAct;
import com.mex.GalaxyChain.ui.asset.fragment.LineBaseFragment;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.DeviceUtil;
import com.mex.GalaxyChain.utils.IsEmptyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import rx.Subscriber;


/**
 * Created by LSJ on 2017/5/25.
 */
public class NewKLineFragment extends LineBaseFragment implements KChartView.KChartRefreshListener, IObserver, BaseKChartView.OnSelectedChangedListener {
    private KChartView mKChartView;
    //滑动十字线时，显示对应点详情的地方
    private TextView msgText;
    private TextView messageTwo;
    private String instID;
    private String type;
    private String selType;
    private LinearLayout msg;
    private MarketMainAct newMarketMainAct;
    private LandMarketMainAct landMarketMainAct;
    private LinearLayout llMa;
    private KChartAdapter mAdapter;
    private TextView kai, gao, fu, msgChengjiao, shou, di, e, junjia;
    private List<KLineEntity> data;
    private boolean isLoading = false;
    private long starttime = 0;
    private String interval;
    private String symbol;
    private Boolean isFirstLoading = true;


    // private NewestKLineBean.DataBean mNewestKLineBeanData;
    private List<KLineEntity> mKLineEntityArrayList;
    private List<KLineEntity> mKlineData;

    private List<HistoryKLineBean.DataBean> mDataBeanList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.achart_kline_new_frag, null);
    }


    private void initView() {
        mKChartView = (KChartView) getView().findViewById(R.id.kchart_view);
        msg = (LinearLayout) getView().findViewById(R.id.msg);
        kai = (TextView) getView().findViewById(R.id.kai);
        gao = (TextView) getView().findViewById(R.id.gao);
        fu = (TextView) getView().findViewById(R.id.fu);
        msgChengjiao = (TextView) getView().findViewById(R.id.chengjiao);
        shou = (TextView) getView().findViewById(R.id.shou);
        di = (TextView) getView().findViewById(R.id.di);
        e = (TextView) getView().findViewById(R.id.e);
        junjia = (TextView) getView().findViewById(R.id.junjia);
        llMa = (LinearLayout) getView().findViewById(R.id.ll_Ma);
        mAdapter = new KChartAdapter();
        mKChartView.setAdapter(mAdapter);
        mKChartView.setDateTimeFormatter(new DateFormatter());
        mKChartView.setGridRows(4);
        mKChartView.setGridColumns(4);
        mKChartView.setOnSelectedChangedListener(this);
    }


    public void setType(String instID, String type, String selType, String interval, String symbol) {
        this.interval = interval;
        this.symbol = symbol;
        initData();
    }


    public void initData() {
        isLoading = false;
        isFirstLoading = true;
       mShowDialog();
        mKChartView.setRefreshListener(this);
        mKChartView.refreshEnd();
        starttime = 0;
        onLoadKData();
//   onLoadMoreBegin(mKChartView);
        loadDataGetNewestK();


    }


    double mLongitude, mLatitude;
    long times_frist;

    private void onLoadKData() {
        if (UserGolbal.getInstance().locationSuccess()) {
            HashMap<String, Object> paramMap = new HashMap<>();
            mLongitude = UserGolbal.getInstance().getLongitude();
            mLatitude = UserGolbal.getInstance().getLatitude();
            paramMap.put("latitude", mLatitude);
            paramMap.put("longitude", mLongitude);
            //  String symbol = "BTCUSDT";
            paramMap.put("symbol", symbol);//todo 变化的
            paramMap.put("starttime", starttime);//返回每次数量的最后一条蜡烛数据的时间撮
            int count = -500;//(- 向左 每次取500条   + 向右 每次取500)一开始时间为节点，正直是向右取，负值是向左取(每次返回的条数)
            paramMap.put("count", count);
            // String interval = Constants.ONE_MIN; //默认周期  1分钟 todo 变化的
            paramMap.put("interval", interval);
            int vol = 500; //交易量
            paramMap.put("vol", vol);
            final int deviceType = Constants.ANDROID;//设备类型(1=IOS，2=安卓,3=UWP,4=PC)
            paramMap.put("deviceType", deviceType);
            String devcieModel = Build.MODEL;  //手机型号(如:华为)
            paramMap.put("devcieModel", devcieModel);
            int channelId = Constants.channelId;////白标ID
            paramMap.put("channelId", channelId);
            paramMap.put("version", AppUtil.getAppVersionName(MyApplication.getInstance()));
            MyApplication instance = MyApplication.getInstance();
            String device_identifier = DeviceUtil.getUdid(instance);
            String deviceID = HttpInterceptor.silentURLEncode(device_identifier);
            paramMap.put("deviceId", deviceID);

            UserRepo.getInstance().getHistoryKLine(paramMap)
                    .subscribe(new Subscriber<HistoryKLineBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(HistoryKLineBean historyKLineBean) {
                            mDismissDialog();
                            mKLineEntityArrayList = new ArrayList<>();
                            mKlineData = new ArrayList<>();
                            //K历史数据集合
                            mDataBeanList = historyKLineBean.getData();
                            // LogUtils.d( "TAG:K线--->数据" + new Gson().toJson(mDataBeanList));
                            starttime = mDataBeanList.get(mDataBeanList.size() - 1).getTimes();
                            times_frist = mDataBeanList.get(0).getTimes();
                            LogUtils.d("TAG:K线--->每页第一条time:" + times_frist);
                            LogUtils.d("TAG:K线--->每页第一条time:" + AppUtil.getDateToStringDetail(times_frist));
                            // LogUtils.d("TAG:K线--->每页最后一条time:" + starttime);
                            // LogUtils.d("TAG:K线--->每页最后一条time:" + AppUtil.getDateToStringDetail(starttime));
                            if (mDataBeanList != null && mDataBeanList.size() > 0) {
                                if (mDataBeanList.size() == 1) { //当第一页都不够500条 如何
                                    mKChartView.refreshEnd();
                                    return;
                                }
                                for (HistoryKLineBean.DataBean dataBean : mDataBeanList) {
                                    KLineEntity kLineEntity = new KLineEntity();
                                    kLineEntity.Open = (float) dataBean.getOpen();
                                    kLineEntity.Close = (float) dataBean.getClose();
                                    kLineEntity.Date = AppUtil.getDateToStringDetail(dataBean.getTimes());
                                    kLineEntity.High = (float) dataBean.getHigh();
                                    kLineEntity.Low = (float) dataBean.getLow();
                                    kLineEntity.Volume = (float) dataBean.getVol();
                                    mKLineEntityArrayList.add(kLineEntity);
                                }
                                if (isFirstLoading)
                                    mKlineData = mKLineEntityArrayList;
                                isFirstLoading = false;
                                Collections.reverse(mKLineEntityArrayList); // 倒序排列kLineEntityArrayList 否者K线显示方向不对
                                DataHelper.calculate(mKLineEntityArrayList);
                            }

                            //第一次加载时开始动画
                            if (mAdapter.getCount() == 0) {
                                mKChartView.startAnimation();
                            }
                            if (isLoading) {
                                mAdapter.addFooterData(mKLineEntityArrayList);

                            } else {
                                mAdapter.clearItems();
                                mAdapter.setItems(mKLineEntityArrayList);
                                isLoading = true;
                            }
                            //加载完成，还有更多数据
                            if (mKLineEntityArrayList.size() >= 50) {
                                mKChartView.refreshComplete();
                                mKChartView.resetLoadMoreEnd();
                            }
                            //加载完成，没有更多数据
                            else {
                                mKChartView.refreshEnd();
                            }
                        }
                    });
        } else {
            UserGolbal.getInstance().requestLocation();
        }

    }


    @Override
    public void onLoadMoreBegin(KChartView chart) {
        if (!isFirstLoading)
            onLoadKData();
    }


    public void setObject(MarketMainAct newMarketMainAct) {
        this.newMarketMainAct = newMarketMainAct;
    }

    public void setLangObject(LandMarketMainAct landMarketMainAct) {
        this.landMarketMainAct = landMarketMainAct;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
    }

    @Override
    public void Response(int i) {
        if (i == 0) {//十字线显示
//			msg.setVisibility(View.VISIBLE);
            if (newMarketMainAct != null)
                newMarketMainAct.setVisAndGone(0);
            if (landMarketMainAct != null)
                landMarketMainAct.setVisAndGone(0);
        } else {
//			msg.setVisibility(View.GONE);
            if (newMarketMainAct != null)
                newMarketMainAct.setVisAndGone(1);
            if (landMarketMainAct != null)
                landMarketMainAct.setVisAndGone(1);
        }
    }

    @Override
    public void onSelectedChanged(BaseKChartView view, Object point, int index) {
        KLineEntity data = (KLineEntity) point;
        kai.setText(Html.fromHtml(NewColorUtil.getNewKai(data)));
        gao.setText(Html.fromHtml(NewColorUtil.getNewGao(data)));
        fu.setText(Html.fromHtml(NewColorUtil.getNewFu(data)));
        msgChengjiao.setText(Html.fromHtml(NewColorUtil.getNewChengjiao(data)));
        shou.setText(Html.fromHtml(NewColorUtil.getNewShou(data)));
        di.setText(Html.fromHtml(NewColorUtil.getNewDi(data)));
        e.setText(Html.fromHtml(NewColorUtil.getNewE(data)));
        junjia.setText(Html.fromHtml(NewColorUtil.getNewJunjia(data)));

    }


    MyCountDownTimer myCountDownTimer;

    private void startTimerGetNewK() {
        if (myCountDownTimer == null) {
            myCountDownTimer = new MyCountDownTimer(2 * 1000, 1000);
        }
        myCountDownTimer.start();

    }


    private class MyCountDownTimer extends CountDownTimer {


        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            loadDataGetNewestK();
            myCountDownTimer.start();
        }
    }


    private void loadDataGetNewestK() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.  put("latitude", mLatitude);
        paramMap.put("longitude", mLongitude);
        paramMap.put("symbol", symbol);
        paramMap.put("interval", interval);
        paramMap.put("deviceType", Constants.ANDROID);
        paramMap.put("devcieModel", Build.MODEL);
        paramMap.put("channelId", Constants.channelId);
        paramMap.put("version", AppUtil.getAppVersionName(MyApplication.getInstance()));
        MyApplication instance = MyApplication.getInstance();
        String device_identifier = DeviceUtil.getUdid(instance);
        String deviceID = HttpInterceptor.silentURLEncode(device_identifier);
        paramMap.put("deviceId", deviceID);
        UserRepo.getInstance().getNewestKLine(paramMap)
                .subscribe(new Subscriber<NewestKLineBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(NewestKLineBean newestKLineBean) {
                        NewestKLineBean.DataBean mNewestKLineBeanData = newestKLineBean.getData();//请求的最新一条K线数据对象
                        long newKLineTime = mNewestKLineBeanData.getTimes();
                        if (!IsEmptyUtils.isEmpty(times_frist, newKLineTime)) {
                            if (times_frist == newKLineTime) {
                                List<KLineEntity> newKDataList = new ArrayList<>();
                                KLineEntity kLineEntity = new KLineEntity();
                                kLineEntity.High = (float) mNewestKLineBeanData.getHigh();
                                kLineEntity.Low = (float) mNewestKLineBeanData.getLow();
                                kLineEntity.Close = (float) mNewestKLineBeanData.getClose();
                                kLineEntity.Open = (float) mNewestKLineBeanData.getOpen();
                                kLineEntity.Date = AppUtil.getDateToStringDetail(newKLineTime);
                                kLineEntity.Volume = (float) mNewestKLineBeanData.getVol();
                                newKDataList.add(kLineEntity);
                                DataHelper.calculate(newKDataList, mKlineData);
                                mAdapter.changeItem(mAdapter.getCount() - 1, kLineEntity);
                            } else {
                                List<KLineEntity> newKDataList = new ArrayList<>();
                                KLineEntity kLineEntity = new KLineEntity();
                                kLineEntity.High = (float) mNewestKLineBeanData.getHigh();
                                kLineEntity.Low = (float) mNewestKLineBeanData.getLow();
                                kLineEntity.Close = (float) mNewestKLineBeanData.getClose();
                                kLineEntity.Open = (float) mNewestKLineBeanData.getOpen();
                                kLineEntity.Date = AppUtil.getDateToStringDetail(newKLineTime);
                                kLineEntity.Volume = (float) mNewestKLineBeanData.getVol();
                                newKDataList.add(kLineEntity);
                                mKlineData.add(kLineEntity);
                                mAdapter.addHeaderData(newKDataList);
                                times_frist = newKLineTime;
                                DataHelper.calculate(newKDataList);
                            }
//     startTimerGetNewK();
                        }
                    }
                });
        startTimerGetNewK();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        end();
    }


    @Override
    public void onStop() {
        super.onStop();
        end();
    }

    @Override
    public void onPause() {
        super.onPause();
        end();
    }

    public void end() {
        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();

        }
    }


}
