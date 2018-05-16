package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

import java.util.List;

public class HistoryKLineBean extends BaseBean {


    /**
     * code : 200
     * data : [{"close":1.18451,"high":1.1847,"low":1.1845,"open":1.18461,"times":1525788600000,"val":1.409624393078E9,"vol":1.1899998E9},{"close":1.1847,"high":1.18474,"low":1.18448,"open":1.1845,"times":1525788660000,"val":3.210895295E9,"vol":2.7105E9},{"close":1.1847,"high":1.18474,"low":1.1846,"open":1.18465,"times":1525788720000,"val":3.397715455E9,"vol":2.868E9},{"close":1.18506,"high":1.18509,"low":1.18473,"open":1.18473,"times":1525788780000,"val":4.551448976964E9,"vol":3.8409992E9},{"close":1.18512,"high":1.18516,"low":1.18503,"open":1.18508,"times":1525788840000,"val":3.8498222E9,"vol":3.2485E9},{"close":1.18475,"high":1.18514,"low":1.18469,"open":1.18512,"times":1525788900000,"val":2.63543395E9,"vol":2.224E9},{"close":1.18494,"high":1.18501,"low":1.18455,"open":1.18476,"times":1525788960000,"val":5.492506815E9,"vol":4.636E9},{"close":1.18503,"high":1.18503,"low":1.18471,"open":1.18493,"times":1525789020000,"val":2.802042595E9,"vol":2.365E9},{"close":1.18461,"high":1.18503,"low":1.18461,"open":1.18502,"times":1525789080000,"val":3.20196136024E9,"vol":2.702496E9},{"close":1.18426,"high":1.18466,"low":1.18421,"open":1.18456,"times":1525789140000,"val":3.29149945E9,"vol":2.779E9}]
     * msg : 成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * close : 1.18451
         * high : 1.1847
         * low : 1.1845
         * open : 1.18461
         * times : 1525788600000
         * val : 1.409624393078E9
         * vol : 1.1899998E9
         */

        private double close;
        private double high;
        private double low;
        private double open;
        private long times;//utc时间戳
        private double val;//成交额
        private double vol;//成交量


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

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public long getTimes() {
            return times;
        }

        public void setTimes(long times) {
            this.times = times;
        }

        public double getVal() {
            return val;
        }

        public void setVal(double val) {
            this.val = val;
        }

        public double getVol() {
            return vol;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }
    }
}
