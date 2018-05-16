package com.mex.GalaxyChain.bean.requestbean;

public class RequestTradeHomeBean {


    /**
     * usertoken : string
     * contractid : 0
     * symbol : string
     * deviceType : 0
     * devcieModel : string
     * channelId : 0
     * version : string
     * deviceId : string
     * latitude : 0
     * longitude : 0
     */

    private String usertoken;
    private int contractid;//合约ID
    private String symbol;
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

    public int getContractid() {
        return contractid;
    }

    public void setContractid(int contractid) {
        this.contractid = contractid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
