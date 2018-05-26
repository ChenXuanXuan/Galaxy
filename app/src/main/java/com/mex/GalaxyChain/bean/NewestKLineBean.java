package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

public class NewestKLineBean extends BaseBean {


    /**
     * data : {"open":7619.78,"close":7619.78,"high":7619.78,"low":7619.78,"vol":0.0515,"val":392.41866999999996,"times":1527229740000}
     * code : 200
     * msg : 成功
     */

    private DataBean data;
    private int code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * open : 7619.78
         * close : 7619.78
         * high : 7619.78
         * low : 7619.78
         * vol : 0.0515
         * val : 392.41866999999996
         * times : 1527229740000
         */

        private double open;
        private double close;
        private double high;
        private double low;
        private double vol;
        private double val;
        private long times;

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getVol() {
            return vol;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }

        public double getVal() {
            return val;
        }

        public void setVal(double val) {
            this.val = val;
        }

        public long getTimes() {
            return times;
        }

        public void setTimes(long times) {
            this.times = times;
        }
    }
}
