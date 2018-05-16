package com.mex.GalaxyChain.bean.requestbean;

public class RequestOrderBuyBean {


    /*
     *
     *  {"bstype":1,"channelId":42798,"currencytype":"USD","devcieModel":"SM-G9350","deviceId":"352709080958846","deviceType":2,"latitude":116.455705,"longitude":116.455705,"symbol":"USOUSD","usertoken":"f4fea5ac0444c1143f15217915f6397f","version":"1.0.0"}
     *
     * */

    /**
     * bstype : 1
     * channelId : 42798
     * currencytype : USD
     * devcieModel : SM-G9350
     * deviceId : 352709080958846
     * deviceType : 2
     * latitude : 116.455705
     * longitude : 116.455705
     * symbol : USOUSD
     * usertoken : f4fea5ac0444c1143f15217915f6397f
     * version : 1.0.0
     */

    private int bstype;
    private int channelId;
    private String currencytype;
    private String devcieModel;
    private String deviceId;
    private int deviceType;
    private double latitude;
    private double longitude;
    private String symbol;
    private String usertoken;
    private String version;

    public int getBstype() {
        return bstype;
    }

    public void setBstype(int bstype) {
        this.bstype = bstype;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getCurrencytype() {
        return currencytype;
    }

    public void setCurrencytype(String currencytype) {
        this.currencytype = currencytype;
    }

    public String getDevcieModel() {
        return devcieModel;
    }

    public void setDevcieModel(String devcieModel) {
        this.devcieModel = devcieModel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }






}
