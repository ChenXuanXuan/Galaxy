package com.mex.GalaxyChain.mychart.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lsj.kchart.kchartlib.chart.BaseKChartView;
import com.lsj.kchart.kchartlib.chart.KChartView;
import com.lsj.kchart.kchartlib.chart.formatter.DateFormatter;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.bean.HistoryKLineBean;
import com.mex.GalaxyChain.common.Constants;
import com.mex.GalaxyChain.common.UserGolbal;
import com.mex.GalaxyChain.mychart.chart.kchart.DataHelper;
import com.mex.GalaxyChain.mychart.chart.kchart.chart.KChartAdapter;
import com.mex.GalaxyChain.mychart.chart.kchart.chart.KLineEntity;
import com.mex.GalaxyChain.mychart.obser.IObserver;
import com.mex.GalaxyChain.mychart.utils.LogUtils;
import com.mex.GalaxyChain.mychart.utils.NewColorUtil;
import com.mex.GalaxyChain.net.repo.UserRepo;
import com.mex.GalaxyChain.ui.asset.activity.LandMarketMainAct;
import com.mex.GalaxyChain.ui.asset.activity.MarketMainAct;
import com.mex.GalaxyChain.ui.asset.fragment.LineBaseFragment;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.ToastUtils;

import java.util.ArrayList;
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
    private boolean isLoading;
    private int page = 0;
    private  long starttime = 0;

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

    public void initData() {

        mKChartView.showLoading();
        mKChartView.setRefreshListener(this);
        starttime = 0;
        onLoadKData();
//        onLoadMoreBegin(mKChartView);
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


    public void setType(String instID, String type, String selType) {
        mAdapter.clearItems();
        this.instID = instID;
        this.type = type;
        this.selType = selType;
        isLoading = false;
        page = 0;
        mKChartView.resetLoadMoreEnd();
        initData();
    }


    private void onLoadKData() {
        if (UserGolbal.getInstance().locationSuccess()) {
            HashMap<String, Object> paramMap = new HashMap<>();
            double mLongitude = UserGolbal.getInstance().getLongitude();
            double mLatitude = UserGolbal.getInstance().getLongitude();
            paramMap.put("latitude", mLatitude);
            paramMap.put("longitude", mLongitude);
            String symbol = "BTCUSDT";
            paramMap.put("symbol", symbol);
            paramMap.put("starttime", starttime);//返回每次数量的最后一条蜡烛数据的时间撮
            int count = -1500;//(- 向左 每次取500条   + 向右 每次取500)一开始时间为节点，正直是向右取，负值是向左取(每次返回的条数)
            paramMap.put("count", count);
            String interval = Constants.ONE_MIN; //默认周期  1分钟
            paramMap.put("interval", interval);
            int vol = 500; //交易量
            paramMap.put("vol", vol);
            final int deviceType = Constants.ANDROID;//设备类型(1=IOS，2=安卓,3=UWP,4=PC)
            paramMap.put("deviceType", deviceType);
            String devcieModel = Build.MODEL;  //手机型号(如:华为)
            paramMap.put("devcieModel", devcieModel);
            int channelId = Constants.channelId;////白标ID
            paramMap.put("channelId", channelId);
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
                            List<KLineEntity> kLineEntityArrayList = new ArrayList<>();
                            //K历史数据
                            List<HistoryKLineBean.DataBean> dataBeanList = historyKLineBean.getData();
                            LogUtils.d("******************K线："+new Gson().toJson(dataBeanList));
                            starttime = dataBeanList.get(dataBeanList.size()-1).getTimes();
                            if (dataBeanList != null && dataBeanList.size() > 0) {
                                if (dataBeanList.size()==1){
                                    mKChartView.refreshEnd();
                                    ToastUtils.showCorrectImage("没有更多数据了");
                                    return;
                                }
                                for (HistoryKLineBean.DataBean dataBean : dataBeanList) {
                                    KLineEntity kLineEntity = new KLineEntity();
                                    kLineEntity.Open = (float) dataBean.getOpen();
                                    kLineEntity.Close = (float) dataBean.getClose();
                                    kLineEntity.Date = AppUtil.getDateToString(dataBean.getTimes());
                                    kLineEntity.High = (float) dataBean.getHigh();
                                    kLineEntity.Low = (float) dataBean.getLow();
                                    kLineEntity.Volume = (float) dataBean.getVol();
                                    kLineEntityArrayList.add(kLineEntity);
                                }
                                DataHelper.calculate(kLineEntityArrayList);
                                List<KLineEntity> dataNew = new ArrayList<>();
                                int start = Math.max(0, kLineEntityArrayList.size() - 1 - mAdapter.getCount() - 500);
                                int stop = Math.min(kLineEntityArrayList.size(), kLineEntityArrayList.size() - mAdapter.getCount());
                                for (int i = start; i < stop; i++) {
                                    dataNew.add(kLineEntityArrayList.get(i));
                                }
                            }else {
                                mKChartView.refreshEnd();
                            }

                            //第一次加载时开始动画
                            if (mAdapter.getCount() == 0) {
                                mKChartView.startAnimation();
                            }
                            if (isLoading) {
                                mAdapter.addFooterData(kLineEntityArrayList);
                            } else {
                                mAdapter.clearItems();
                                mAdapter.setItems(kLineEntityArrayList);
                                isLoading = true;
                            }
                            //加载完成，还有更多数据
                            if (kLineEntityArrayList.size() > 0) {
                                mKChartView.refreshComplete();
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
}
