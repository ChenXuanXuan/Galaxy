package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

import java.util.List;

public class HoldPositionBean  extends BaseBean{


    /**
     * code : 200
     * data : {"canusedamount":4839444.57,"frozenamout":18982.8571,"list":[{"bstype":1,"buyprice":70.841,"defaultpnum":0,"isclosing":0,"margin":100,"maxstoploss":70,"newprice":70.765,"noneprofit":0,"ordertype":2,"posid":87,"postime":"2018-05-14 20:59","quantity":1,"showno":87,"stoploss":70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"tradestatus":4,"usedcoupon":0},{"bstype":1,"buyprice":70.846,"defaultpnum":0,"isclosing":0,"margin":100,"maxstoploss":70,"newprice":70.765,"noneprofit":0,"ordertype":2,"posid":86,"postime":"2018-05-14 20:59","quantity":1,"showno":86,"stoploss":70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"tradestatus":4,"usedcoupon":0},{"bstype":1,"buyprice":70.857,"defaultpnum":0,"isclosing":0,"margin":100,"maxstoploss":70,"newprice":70.765,"noneprofit":0,"ordertype":2,"posid":85,"postime":"2018-05-14 20:59","quantity":1,"showno":85,"stoploss":70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"tradestatus":4,"usedcoupon":0},{"bstype":1,"buyprice":70.808,"defaultpnum":0,"isclosing":1,"margin":100,"maxstoploss":70,"newprice":70.765,"noneprofit":0,"ordertype":2,"posid":77,"postime":"2018-05-14 20:41","quantity":0,"showno":77,"stoploss":70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"tradestatus":4,"usedcoupon":0}],"ordernum":1,"posnum":4,"totalprofit":-1898,"update":1}
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
         * canusedamount : 4839444.57
         * frozenamout : 18982.8571
         * list : [{"bstype":1,"buyprice":70.841,"defaultpnum":0,"isclosing":0,"margin":100,"maxstoploss":70,"newprice":70.765,"noneprofit":0,"ordertype":2,"posid":87,"postime":"2018-05-14 20:59","quantity":1,"showno":87,"stoploss":70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"tradestatus":4,"usedcoupon":0},{"bstype":1,"buyprice":70.846,"defaultpnum":0,"isclosing":0,"margin":100,"maxstoploss":70,"newprice":70.765,"noneprofit":0,"ordertype":2,"posid":86,"postime":"2018-05-14 20:59","quantity":1,"showno":86,"stoploss":70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"tradestatus":4,"usedcoupon":0},{"bstype":1,"buyprice":70.857,"defaultpnum":0,"isclosing":0,"margin":100,"maxstoploss":70,"newprice":70.765,"noneprofit":0,"ordertype":2,"posid":85,"postime":"2018-05-14 20:59","quantity":1,"showno":85,"stoploss":70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"tradestatus":4,"usedcoupon":0},{"bstype":1,"buyprice":70.808,"defaultpnum":0,"isclosing":1,"margin":100,"maxstoploss":70,"newprice":70.765,"noneprofit":0,"ordertype":2,"posid":77,"postime":"2018-05-14 20:41","quantity":0,"showno":77,"stoploss":70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"tradestatus":4,"usedcoupon":0}]
         * ordernum : 1
         * posnum : 4
         * totalprofit : -1898.0
         * update : 1
         */
         private double totalamount; //总资金(总共多少钱)
        private double canusedamount;//可用余额(还可用用的钱)
        private double frozenamout;//冻结保证金
        private int ordernum;//委托数量
        private int posnum;//持仓数量
        private double totalprofit;//总盈亏(总浮动盈亏)
        private int update;//是否自动更新默认等于=1
        private List<ListBean> list;



        public double getTotalamount() {
            return totalamount;
        }

        public void setTotalamount(double totalamount) {
            this.totalamount = totalamount;
        }



        public double getCanusedamount() {
            return canusedamount;
        }

        public void setCanusedamount(double canusedamount) {
            this.canusedamount = canusedamount;
        }

        public double getFrozenamout() {
            return frozenamout;
        }

        public void setFrozenamout(double frozenamout) {
            this.frozenamout = frozenamout;
        }

        public int getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(int ordernum) {
            this.ordernum = ordernum;
        }

        public int getPosnum() {
            return posnum;
        }

        public void setPosnum(int posnum) {
            this.posnum = posnum;
        }

        public double getTotalprofit() {
            return totalprofit;
        }

        public void setTotalprofit(double totalprofit) {
            this.totalprofit = totalprofit;
        }

        public int getUpdate() {
            return update;
        }

        public void setUpdate(int update) {
            this.update = update;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {


            /**
             * bstype : 1
             * buyprice : 70.841
             * defaultpnum : 0.0
             * isclosing : 0
             * margin : 100.0
             * maxstoploss : 70.0
             * newprice : 70.765
             * noneprofit : 0
             * ordertype : 2
             * posid : 87
             * postime : 2018-05-14 20:59
             * quantity : 1
             * showno : 87
             * stoploss : 70.0
             * stopprofit : 140.0
             * symbol : USOUSD
             * tradefee : 1000.0
             * tradestatus : 4
             * usedcoupon : 0.0
             */

            private int bstype;//买涨/买跌
            private double buyprice; //买入价 (开仓价格 )
            private double defaultpnum;//默认止盈点数（设置止盈止损界面用）
            private int isclosing;//1是平仓中（此时止盈止损、快速平仓按钮是灰的）
            private double margin;//占用保证金
            private double maxstoploss;//最大止损价
            private double newprice;//最新价(平仓价格)
            private int noneprofit;
            private int ordertype;//订单类型
            private int posid;//持仓ID(订单号)（平仓用）
            private double profit;//盈亏 903.5
            private String postime;//持仓时间(开仓时间)
            private int quantity;//持仓量(手数)
            private long showno;//交易单号
            private double stoploss;//止损
            private double stopprofit;//止盈
            private String symbol;
            private double tradefee;//交易费用
            private int tradestatus;//1是委托条目、4是持仓条目，不同的条目取的字段不同
            private double usedcoupon;//使用优惠券金额



            public double getProfit() {
                return profit;
            }

            public void setProfit(double profit) {
                this.profit = profit;
            }

            public int getBstype() {
                return bstype;
            }

            public void setBstype(int bstype) {
                this.bstype = bstype;
            }

            public double getBuyprice() {
                return buyprice;
            }

            public void setBuyprice(double buyprice) {
                this.buyprice = buyprice;
            }

            public double getDefaultpnum() {
                return defaultpnum;
            }

            public void setDefaultpnum(double defaultpnum) {
                this.defaultpnum = defaultpnum;
            }

            public int getIsclosing() {
                return isclosing;
            }

            public void setIsclosing(int isclosing) {
                this.isclosing = isclosing;
            }

            public double getMargin() {
                return margin;
            }

            public void setMargin(double margin) {
                this.margin = margin;
            }

            public double getMaxstoploss() {
                return maxstoploss;
            }

            public void setMaxstoploss(double maxstoploss) {
                this.maxstoploss = maxstoploss;
            }

            public double getNewprice() {
                return newprice;
            }

            public void setNewprice(double newprice) {
                this.newprice = newprice;
            }

            public int getNoneprofit() {
                return noneprofit;
            }

            public void setNoneprofit(int noneprofit) {
                this.noneprofit = noneprofit;
            }

            public int getOrdertype() {
                return ordertype;
            }

            public void setOrdertype(int ordertype) {
                this.ordertype = ordertype;
            }

            public int getPosid() {
                return posid;
            }

            public void setPosid(int posid) {
                this.posid = posid;
            }

            public String getPostime() {
                return postime;
            }

            public void setPostime(String postime) {
                this.postime = postime;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public long getShowno() {
                return showno;
            }

            public void setShowno(long showno) {
                this.showno = showno;
            }

            public double getStoploss() {
                return stoploss;
            }

            public void setStoploss(double stoploss) {
                this.stoploss = stoploss;
            }

            public double getStopprofit() {
                return stopprofit;
            }

            public void setStopprofit(double stopprofit) {
                this.stopprofit = stopprofit;
            }

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }

            public double getTradefee() {
                return tradefee;
            }

            public void setTradefee(double tradefee) {
                this.tradefee = tradefee;
            }

            public int getTradestatus() {
                return tradestatus;
            }

            public void setTradestatus(int tradestatus) {
                this.tradestatus = tradestatus;
            }

            public double getUsedcoupon() {
                return usedcoupon;
            }

            public void setUsedcoupon(double usedcoupon) {
                this.usedcoupon = usedcoupon;
            }
        }
    }
}
