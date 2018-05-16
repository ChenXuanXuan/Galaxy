package com.mex.GalaxyChain.bean.requestbean;

public class RequestClosePositionBean {


    /**
     * usertoken : string
     * posid : 0
     * ordertype : 0
     * price : 0
     * quantity : 0
     * deviceid : string
     * mobilemode : string
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
    private int posid;
    private int ordertype;
    private double price;
    private int quantity;//手数
    private String deviceid;
    private String mobilemode;
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

    public int getPosid() {
        return posid;
    }

    public void setPosid(int posid) {
        this.posid = posid;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getMobilemode() {
        return mobilemode;
    }

    public void setMobilemode(String mobilemode) {
        this.mobilemode = mobilemode;
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
