package com.mex.GalaxyChain.bean.requestbean;

public class PayInMoneyBean {

    public  String usertoken;
    public double payinmoney;//入金金额

    public int payintype; //入金类型1=币币，2=法币

    public String sourcecurrency;//目标币种
    public int deviceType;//设备类型(1=IOS，2=安卓,3=UWP,4=PC)
    public String devcieModel;
    public int channelId;
    public String version;
    public String deviceId;
    public  double latitude;
    public double longitude;



}
