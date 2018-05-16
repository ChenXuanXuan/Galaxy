package com.mex.GalaxyChain.bean.requestbean;

public class RequestPostOrderMore {


    /**
     * usertoken : string
     * symbol : string
     * ordertype : 0
     * price : 0
     * bstype : 0
     * quantity : 0
     * stoplosstimes : 0
     * stopprofit : 0
     * stoploss : 0
     * ip : string
     * deviceType : 0
     * devcieModel : string
     * channelId : 0
     * version : string
     * deviceId : string
     * latitude : 0
     * longitude : 0
     */

    private String usertoken;
    private String symbol;
    private int ordertype;
    private double price;
    private int bstype;
    private int quantity;
    private int stoplosstimes;
    private double stopprofit;
    private double stoploss;
    private String ip;
    private int deviceType;
    private String devcieModel;
    private int channelId;
    private String version;
    private String deviceId;
    private double latitude;
    private double longitude;

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBstype() {
        return bstype;
    }

    public void setBstype(int bstype) {
        this.bstype = bstype;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStoplosstimes() {
        return stoplosstimes;
    }

    public void setStoplosstimes(int  stoplosstimes) {
        this.stoplosstimes = stoplosstimes;
    }

    public double getStopprofit() {
        return stopprofit;
    }

    public void setStopprofit(double stopprofit) {
        this.stopprofit = stopprofit;
    }

    public double getStoploss() {
        return stoploss;
    }

    public void setStoploss(double stoploss) {
        this.stoploss = stoploss;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getDevcieModel() {
        return devcieModel;
    }

    public void setDevcieModel(String devcieModel) {
        this.devcieModel = devcieModel;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
}
