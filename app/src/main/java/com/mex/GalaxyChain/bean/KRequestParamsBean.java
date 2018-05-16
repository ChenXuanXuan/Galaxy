package com.mex.GalaxyChain.bean;

public class KRequestParamsBean  {

    String symbol;
    double mLongitude;
    double mLatitude;
    double perprofit;// 收益
    private double perprofitnumber;//收益点数
    private String closetime;//平仓时间

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }





    public double getPerprofitnumber() {
        return perprofitnumber;
    }

    public void setPerprofitnumber(double perprofitnumber) {
        this.perprofitnumber = perprofitnumber;
    }






    public double getPerprofit() {
        return perprofit;
    }

    public void setPerprofit(double perprofit) {
        this.perprofit = perprofit;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }



    @Override
    public String toString() {
        return "KRequestParamsBean{" +
                "symbol='" + symbol + '\'' +
                ", mLongitude=" + mLongitude +
                ", mLatitude=" + mLatitude +
                '}';
    }




}
