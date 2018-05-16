package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

import java.util.List;

public class TradeDetailBean extends BaseBean {


    /**
     * code : 200
     * data : {"list":[{"bstype":1,"buyprice":71.554,"closetime":"05-15 20:13:11","contractid":12,"opentime":"05-15 20:13:10","profit":195,"sellprice":71.564,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":3000,"transid":205,"usedcoupon":0},{"bstype":1,"buyprice":71.369,"closetime":"05-15 17:58:30","contractid":12,"opentime":"05-15 17:58:24","profit":507,"sellprice":71.395,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":3000,"transid":203,"usedcoupon":0},{"bstype":1,"buyprice":71.375,"closetime":"05-15 17:58:04","contractid":12,"opentime":"05-15 17:58:03","profit":370.5,"sellprice":71.394,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":3000,"transid":202,"usedcoupon":0},{"bstype":1,"buyprice":71.399,"closetime":"05-15 17:55:55","contractid":12,"opentime":"05-15 17:55:43","profit":273,"sellprice":71.413,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":3000,"transid":201,"usedcoupon":0},{"bstype":1,"buyprice":71.399,"closetime":"05-15 17:56:12","contractid":12,"opentime":"05-15 17:55:26","profit":71.5,"sellprice":71.41,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":200,"usedcoupon":0},{"bstype":1,"buyprice":71.388,"closetime":"05-15 17:52:43","contractid":12,"opentime":"05-15 17:52:08","profit":0,"sellprice":71.388,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":199,"usedcoupon":0},{"bstype":1,"buyprice":71.4,"closetime":"05-15 17:52:05","contractid":12,"opentime":"05-15 17:52:03","profit":39,"sellprice":71.406,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":198,"usedcoupon":0},{"bstype":1,"buyprice":71.344,"closetime":"05-15 17:49:09","contractid":12,"opentime":"05-15 17:48:03","profit":260,"sellprice":71.384,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":197,"usedcoupon":0},{"bstype":1,"buyprice":71.136,"closetime":"05-15 17:32:54","contractid":12,"opentime":"05-15 17:32:51","profit":812.5,"sellprice":71.161,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":5000,"transid":196,"usedcoupon":0},{"bstype":1,"buyprice":71.124,"closetime":"05-15 17:32:46","contractid":12,"opentime":"05-15 17:32:45","profit":1885,"sellprice":71.153,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":10000,"transid":195,"usedcoupon":0},{"bstype":1,"buyprice":71.183,"closetime":"05-15 17:29:36","contractid":12,"opentime":"05-15 17:29:35","profit":214.5,"sellprice":71.194,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":3000,"transid":194,"usedcoupon":0},{"bstype":1,"buyprice":71.177,"closetime":"05-15 17:29:12","contractid":12,"opentime":"05-15 17:29:07","profit":520,"sellprice":71.193,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":5000,"transid":193,"usedcoupon":0},{"bstype":1,"buyprice":71.18,"closetime":"05-15 17:28:55","contractid":12,"opentime":"05-15 17:28:53","profit":1560,"sellprice":71.204,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":10000,"transid":192,"usedcoupon":0},{"bstype":1,"buyprice":71.188,"closetime":"05-15 17:28:43","contractid":12,"opentime":"05-15 17:28:39","profit":117,"sellprice":71.206,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":191,"usedcoupon":0},{"bstype":1,"buyprice":71.211,"closetime":"05-15 17:27:37","contractid":12,"opentime":"05-15 17:27:33","profit":182,"sellprice":71.239,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":190,"usedcoupon":0}],"totalnum":135,"totalpage":9}
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
         * list : [{"bstype":1,"buyprice":71.554,"closetime":"05-15 20:13:11","contractid":12,"opentime":"05-15 20:13:10","profit":195,"sellprice":71.564,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":3000,"transid":205,"usedcoupon":0},{"bstype":1,"buyprice":71.369,"closetime":"05-15 17:58:30","contractid":12,"opentime":"05-15 17:58:24","profit":507,"sellprice":71.395,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":3000,"transid":203,"usedcoupon":0},{"bstype":1,"buyprice":71.375,"closetime":"05-15 17:58:04","contractid":12,"opentime":"05-15 17:58:03","profit":370.5,"sellprice":71.394,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":3000,"transid":202,"usedcoupon":0},{"bstype":1,"buyprice":71.399,"closetime":"05-15 17:55:55","contractid":12,"opentime":"05-15 17:55:43","profit":273,"sellprice":71.413,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":3000,"transid":201,"usedcoupon":0},{"bstype":1,"buyprice":71.399,"closetime":"05-15 17:56:12","contractid":12,"opentime":"05-15 17:55:26","profit":71.5,"sellprice":71.41,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":200,"usedcoupon":0},{"bstype":1,"buyprice":71.388,"closetime":"05-15 17:52:43","contractid":12,"opentime":"05-15 17:52:08","profit":0,"sellprice":71.388,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":199,"usedcoupon":0},{"bstype":1,"buyprice":71.4,"closetime":"05-15 17:52:05","contractid":12,"opentime":"05-15 17:52:03","profit":39,"sellprice":71.406,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":198,"usedcoupon":0},{"bstype":1,"buyprice":71.344,"closetime":"05-15 17:49:09","contractid":12,"opentime":"05-15 17:48:03","profit":260,"sellprice":71.384,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":197,"usedcoupon":0},{"bstype":1,"buyprice":71.136,"closetime":"05-15 17:32:54","contractid":12,"opentime":"05-15 17:32:51","profit":812.5,"sellprice":71.161,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":5000,"transid":196,"usedcoupon":0},{"bstype":1,"buyprice":71.124,"closetime":"05-15 17:32:46","contractid":12,"opentime":"05-15 17:32:45","profit":1885,"sellprice":71.153,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":10000,"transid":195,"usedcoupon":0},{"bstype":1,"buyprice":71.183,"closetime":"05-15 17:29:36","contractid":12,"opentime":"05-15 17:29:35","profit":214.5,"sellprice":71.194,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":3000,"transid":194,"usedcoupon":0},{"bstype":1,"buyprice":71.177,"closetime":"05-15 17:29:12","contractid":12,"opentime":"05-15 17:29:07","profit":520,"sellprice":71.193,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":5000,"transid":193,"usedcoupon":0},{"bstype":1,"buyprice":71.18,"closetime":"05-15 17:28:55","contractid":12,"opentime":"05-15 17:28:53","profit":1560,"sellprice":71.204,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":10000,"transid":192,"usedcoupon":0},{"bstype":1,"buyprice":71.188,"closetime":"05-15 17:28:43","contractid":12,"opentime":"05-15 17:28:39","profit":117,"sellprice":71.206,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":191,"usedcoupon":0},{"bstype":1,"buyprice":71.211,"closetime":"05-15 17:27:37","contractid":12,"opentime":"05-15 17:27:33","profit":182,"sellprice":71.239,"stoploss":-70,"stopprofit":140,"symbol":"USOUSD","tradefee":1000,"transid":190,"usedcoupon":0}]
         * totalnum : 135
         * totalpage : 9
         */

        private int totalnum;//总数量
        private int totalpage;//总页数
        private List<ListBean> list;

        public int getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(int totalnum) {
            this.totalnum = totalnum;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
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
             * buyprice : 71.554
             * closetime : 05-15 20:13:11
             * contractid : 12
             * opentime : 05-15 20:13:10
             * profit : 195.0
             * sellprice : 71.564
             * stoploss : -70.0
             * stopprofit : 140.0
             * symbol : USOUSD
             * tradefee : 3000.0
             * transid : 205
             * usedcoupon : 0.0
             */

            private int bstype;////买涨/买跌
            private double buyprice;//买入价(开仓)
            private String closetime;//平仓时间
            private int contractid;//合约ID
            private String opentime;//开仓时间
            private double profit;//盈亏
            private double sellprice;//卖出价(平仓)
            private double stoploss;////止损
            private double stopprofit;//止盈
            private String symbol;//产品
            private double tradefee;//每手交易费
            private int transid;//事务ID
            private double usedcoupon;//用户优惠券
            private long quantity;//手数
            private long closeorderno;// 订单号

            public long getQuantity() {
                return quantity;
            }

            public void setQuantity(long quantity) {
                this.quantity = quantity;
            }

            public long getCloseorderno() {
                return closeorderno;
            }

            public void setCloseorderno(long closeorderno) {
                this.closeorderno = closeorderno;
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

            public String getClosetime() {
                return closetime;
            }

            public void setClosetime(String closetime) {
                this.closetime = closetime;
            }

            public int getContractid() {
                return contractid;
            }

            public void setContractid(int contractid) {
                this.contractid = contractid;
            }

            public String getOpentime() {
                return opentime;
            }

            public void setOpentime(String opentime) {
                this.opentime = opentime;
            }

            public double getProfit() {
                return profit;
            }

            public void setProfit(double profit) {
                this.profit = profit;
            }

            public double getSellprice() {
                return sellprice;
            }

            public void setSellprice(double sellprice) {
                this.sellprice = sellprice;
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

            public int getTransid() {
                return transid;
            }

            public void setTransid(int transid) {
                this.transid = transid;
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
