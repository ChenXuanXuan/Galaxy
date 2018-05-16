package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

public class OrderBuyBean extends BaseBean {


    /**
     * code : 200
     * data : {"balance":4891613.07,"cantrade":"1","maydealpx":70.524,"minmargin":100,"newpx":70.524,"perrmbfee":1000,"perrmbmargin":100,"rate":6.5,"stoplossratio":0.7,"tradestatus":"3","updown":0.049000000000006594,"updownrate":0.07}
     * msg : 成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * balance : 4891613.07
         * cantrade : 1
         * maydealpx : 70.524
         * minmargin : 100.0
         * newpx : 70.524
         * perrmbfee : 1000.0
         * perrmbmargin : 100.0
         * rate : 6.5
         * stoplossratio : 0.7
         * tradestatus : 3
         * updown : 0.049000000000006594
         * updownrate : 0.07
         */

        private double balance;//账号的可用余额(可用资金)
        private String cantrade;//能否交易
        private double maydealpx;//预计成交价
        private double minmargin;//一手最低保证金
        private double perrmbmargin;//每笔保证金(履约保证金)
        private double stoplossratio; //止损率
        private double newpx;//现价
        private double perrmbfee;//每笔手续费(交易费用)

        private double rate;//汇率

        private String tradestatus;
        private double updown;//涨跌
        private double updownrate;//涨跌幅

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String getCantrade() {
            return cantrade;
        }

        public void setCantrade(String cantrade) {
            this.cantrade = cantrade;
        }

        public double getMaydealpx() {
            return maydealpx;
        }

        public void setMaydealpx(double maydealpx) {
            this.maydealpx = maydealpx;
        }

        public double getMinmargin() {
            return minmargin;
        }

        public void setMinmargin(double minmargin) {
            this.minmargin = minmargin;
        }

        public double getNewpx() {
            return newpx;
        }

        public void setNewpx(double newpx) {
            this.newpx = newpx;
        }

        public double getPerrmbfee() {
            return perrmbfee;
        }

        public void setPerrmbfee(double perrmbfee) {
            this.perrmbfee = perrmbfee;
        }

        public double getPerrmbmargin() {
            return perrmbmargin;
        }

        public void setPerrmbmargin(double perrmbmargin) {
            this.perrmbmargin = perrmbmargin;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getStoplossratio() {
            return stoplossratio;
        }

        public void setStoplossratio(double stoplossratio) {
            this.stoplossratio = stoplossratio;
        }

        public String getTradestatus() {
            return tradestatus;
        }

        public void setTradestatus(String tradestatus) {
            this.tradestatus = tradestatus;
        }

        public double getUpdown() {
            return updown;
        }

        public void setUpdown(double updown) {
            this.updown = updown;
        }

        public double getUpdownrate() {
            return updownrate;
        }

        public void setUpdownrate(double updownrate) {
            this.updownrate = updownrate;
        }
    }
}
