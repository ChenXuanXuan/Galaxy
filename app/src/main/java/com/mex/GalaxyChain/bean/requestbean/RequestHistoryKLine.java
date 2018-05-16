package com.mex.GalaxyChain.bean.requestbean;

public class RequestHistoryKLine {


    String symbol;
    long  starttime;
    int  count;
    String interval;
    int vol;//交易量
    int deviceType;
    int channelId;
    double mLongitude;
    double mLatitude;





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






    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }








    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }









    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }








    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }




    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
