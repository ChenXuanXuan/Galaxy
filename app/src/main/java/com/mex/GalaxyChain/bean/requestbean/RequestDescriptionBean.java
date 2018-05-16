package com.mex.GalaxyChain.bean.requestbean;

import java.io.Serializable;

public class RequestDescriptionBean   implements Serializable {


    /**
     * channelId : 0
     * commoditytype : 0
     * devcieModel : string
     * deviceId : string
     * deviceType : 0
     * latitude : 0
     * longitude : 0
     * version : string
     */

    private int channelId;
    private int commoditytype;
    private String devcieModel;
    private String deviceId;
    private int deviceType;
    private double latitude;
    private double longitude;
    private String version;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getCommoditytype() {
        return commoditytype;
    }

    public void setCommoditytype(int commoditytype) {
        this.commoditytype = commoditytype;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    @Override
    public String toString() {
        return "RequestDescriptionBean{" +
                "channelId=" + channelId +
                ", commoditytype=" + commoditytype +
                ", devcieModel='" + devcieModel + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", deviceType=" + deviceType +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", version='" + version + '\'' +
                '}';
    }
}
