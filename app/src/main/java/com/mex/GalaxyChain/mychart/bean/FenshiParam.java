package com.mex.GalaxyChain.mychart.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mac on 15-12-20.
 * until
 * 每次绘图的时候 如果后面没有数据 直接画直线到这个时间就可以了
 *
 */
public class FenshiParam implements Parcelable {

//    "last": "24199.74",
//            "duration": "9:30-11:30|13:00-15:00",
//            "length": 14400,
//            "until": 1450335600


    private double last  ;
    private int count ;
    private int length ;
    private long until ;
    private String duration;
    private int yesDataSize;

    public int getYesDataSize() {
        return yesDataSize;
    }

    public void setYesDataSize(int yesDataSize) {
        this.yesDataSize = yesDataSize;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getUntil() {
        return until;
    }

    public void setUntil(long until) {
        this.until = until;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.last);
        dest.writeInt(this.count);
        dest.writeInt(this.length);
        dest.writeLong(this.until);
        dest.writeString(this.duration);
    }

    public FenshiParam() {
    }

    protected FenshiParam(Parcel in) {
        this.last = in.readDouble();
        this.count = in.readInt();
        this.length = in.readInt();
        this.until = in.readLong();
        this.duration = in.readString();
    }

    public static final Creator<FenshiParam> CREATOR = new Creator<FenshiParam>() {
        @Override
        public FenshiParam createFromParcel(Parcel source) {
            return new FenshiParam(source);
        }

        @Override
        public FenshiParam[] newArray(int size) {
            return new FenshiParam[size];
        }
    };
}
