package com.mex.GalaxyChain.bean.requestbean;

import java.io.Serializable;
import java.util.List;

public class RequestGoodsPrice implements Serializable {


    /**
     * symbols : ["string"]
     * commoditytype : 0
     * deviceType : 0
     * devcieModel : string
     * channelId : 0
     * version : string
     * deviceId : string
     * latitude : 0
     * longitude : 0
     */

    private int commoditytype;
    private int deviceType;
    private String devcieModel;
    private int channelId;
    private String version;
    private String deviceId;
    private double latitude;
    private double longitude;
    private List<String> symbols;

    public int getCommoditytype() {
        return commoditytype;
    }

    public void setCommoditytype(int commoditytype) {
        this.commoditytype = commoditytype;
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

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }
}
