package com.mex.GalaxyChain.common;

import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.bean.MultiplBean;
import com.mex.GalaxyChain.ui.asset.entity.NumEntity;
import com.mex.GalaxyChain.utils.IsEmptyUtils;
import com.mex.GalaxyChain.utils.LogUtils;

import java.util.List;

public class UserGolbal {


    double mLongitude;
    double mLatitude;
    String mUserToken = "";
    private String symbol;
    private String symbolname;
    private String closetime;//自动平仓时间
    private String symbolname_ch;
    private String closetime_ch;


    public String getSymbolname_ch() {
        return symbolname_ch;
    }

    public void setSymbolname_ch(String symbolname_ch) {
        this.symbolname_ch = symbolname_ch;
    }

    public String getClosetime_ch() {
        return closetime_ch;
    }

    public void setClosetime_ch(String closetime_ch) {
        this.closetime_ch = closetime_ch;
    }



    private double margin;//履约保证金



    private long last_times_first; //第一次请求的第500条的时间
    private boolean  isFirst_tag;
    private long last_times_after_first;
    private  int tag;
    private String currencytype;//币种类型
    private double perprofit;//收益(赚了多少钱(11.0))
    private double perprofitnumber;//收益点数(涨了多少个点)
    private double rate;//汇率
    private List<NumEntity>  mNumEntityList; //几手
    private String selected_handSum; //选中几手
    private  List<MultiplBean> multiplBeanList; //几倍
    private double perrmbmargin;//每笔保证金(履约保证金)
    private double stoplossratio; //止损率
    private int realnamestatus; //开户状态(1=开户(已实名认证),2=销户)

    public double frozenmargin;//冻结保证金(占用保证金)
    public double amount;//总金额
    public double totalprofit;//总盈亏(浮动)
    public double canusedamount;//可用余额()


    public String drawedYYAcount;// 提现的YY数量

    public int uid=0;
    private String phoneNum;
    private  int  status_auth_c1; //高杰C1 实名认证




    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStatus_auth_c1() {
        return status_auth_c1;
    }

    public void setStatus_auth_c1(int status_auth_c1) {
        this.status_auth_c1 = status_auth_c1;
    }


    //是否C1 认证成功
    public boolean isRealnameAuthC1Success(){
        if(getStatus_auth_c1()==Constants.RENZHENG_C1){
            return  true;

         }else{
             return false;
         }

         }






    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }





    public int getRealnamestatus() {
        return realnamestatus;
    }

    public void setRealnamestatus(int realnamestatus) {
        this.realnamestatus = realnamestatus;
    }







    public double getPerrmbmargin() {
        return perrmbmargin;
    }

    public void setPerrmbmargin(double perrmbmargin) {
        this.perrmbmargin = perrmbmargin;
    }

    public double getStoplossratio() {
        return stoplossratio;
    }

    public void setStoplossratio(double stoplossratio) {
        this.stoplossratio = stoplossratio;
    }





    public List<MultiplBean> getMultiplBeanList() {
        return multiplBeanList;
    }

    public void setMultiplBeanList(List<MultiplBean> multiplBeanList) {
        this.multiplBeanList = multiplBeanList;
    }



    public String getSelected_handSum() {
        return selected_handSum;
    }

    public void setSelected_handSum(String selected_handSum) {
        this.selected_handSum = selected_handSum;
    }









    public List<NumEntity> getNumEntityList() {
        return mNumEntityList;
    }

    public void setNumEntityList(List<NumEntity> numEntityList) {
        mNumEntityList = numEntityList;
    }















    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCurrencytype() {
        return currencytype;
    }

    public void setCurrencytype(String currencytype) {
        this.currencytype = currencytype;
    }
    public String getSymbolname() {
        return symbolname;
    }

    public void setSymbolname(String symbolname) {
        this.symbolname = symbolname;
    }
    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }



    public long getLast_times_after_first() {
        return last_times_after_first;
    }

    public void setLast_times_after_first(long last_times_after_first) {
        this.last_times_after_first = last_times_after_first;
    }




    public boolean isFirst_tag() {
        return isFirst_tag;
    }

    public void setFirst_tag(boolean first_tag) {
        isFirst_tag = first_tag;
    }







    public long getLast_times_first() {
        return last_times_first;
    }

    public void setLast_times_first(long last_times_first) {
        this.last_times_first = last_times_first;
    }










    public String getClosetime() {
        return closetime;
    }
    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }
    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getPerprofit() {
        return perprofit;
    }

    public void setPerprofit(double perprofit) {
        this.perprofit = perprofit;
    }

    public double getPerprofitnumber() {
        return perprofitnumber;
    }

    public void setPerprofitnumber(double perprofitnumber) {
        this.perprofitnumber = perprofitnumber;
    }







    public String getUserToken() {
        return mUserToken;
    }

    public void setUserToken(String userToken) {
        mUserToken = userToken;
    }

    private static UserGolbal userGolbal;

    public static UserGolbal getInstance() {
        if (IsEmptyUtils.isEmpty(userGolbal)) {
            synchronized (UserGolbal.class) {
                if (IsEmptyUtils.isEmpty(userGolbal)) {
                    userGolbal = new UserGolbal();
                }
            }
        }

        return userGolbal;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public boolean isLogin() {//true 登陆成功  false 未登录，应开启登陆界面
        return !TextUtils.isEmpty(mUserToken);
    }

  public void setLoginOut(){
        //  setUserToken("");
         //  setUid(0);

  }


    public boolean locationSuccess() {
        return (mLatitude != 0.0 && mLongitude != 0.0);
    }


     //定位逻辑 代码放在UserGabol 里
    public void requestLocation() {
        boolean success = UserGolbal.getInstance().locationSuccess();
        if (success) {
            //定位成功  直接用下面经纬度参数
            mLatitude = UserGolbal.getInstance().getLatitude();
            mLongitude =UserGolbal.getInstance().getLongitude();

        }else{
            //开启定位
            //初始化client
            final AMapLocationClient locationClient = new AMapLocationClient(MyApplication.appContext);
            //locationOption = getDefaultOption();
            AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
            aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置是否返回地址信息（默认返回地址信息）
            aMapLocationClientOption.setNeedAddress(true);
            //设置是否只定位一次,默认为false
            aMapLocationClientOption.setOnceLocation(true);
            //设置是否强制刷新WIFI，默认为强制刷新
            aMapLocationClientOption.setWifiActiveScan(true);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            aMapLocationClientOption.setMockEnable(false);
            //设置定位间隔,单位毫秒,默认为2000ms
            aMapLocationClientOption.setInterval(200000);
            //给定位客户端对象设置定位参数
            locationClient.setLocationOption(aMapLocationClientOption);
            // 启动定位
            locationClient.startLocation();
            // 设置定位监听
            locationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation location) {
                    if (location != null) {
                        if (location.getErrorCode() == 0) {
                            LogUtils.d("经度:" + location.getLongitude() + " 纬度:" + location.getLatitude());
                            double mLongitude = location.getLongitude();
                            double mLatitude = location.getLatitude();
                             //从新请求经纬度进行存储
                            UserGolbal.getInstance().setLatitude(mLatitude);
                            UserGolbal.getInstance().setLongitude(mLongitude);

                        } else {
                            Log.e("AmapError", "location Error, ErrCode:" + location.getErrorCode() + ", errInfo:" + location.getErrorInfo());
                        }
                        locationClient.stopLocation();
                    }
                }
            });
        }


    }



}
