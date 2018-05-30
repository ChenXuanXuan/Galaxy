package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

import java.util.List;

public class MoneyFlowBean extends BaseBean {


    /**
     * code : 200
     * data : {"list":[{"amount":58575,"bizname":"平仓委托成交","biztime":"2018-05-20T04:55:05.94","biztype":7,"canusedamount":4586737.82,"comment":"用户:8000006,委托号:790,平仓成交扣掉手续费盈亏:3575,还原保证金55000,成交数量:10","contractid":16,"currenttype":"YYB","flowid":783,"fundaccountid":8000006,"margin":55000,"objectid":"","orderno":790,"profit":3575,"quantity":10,"tradefee":0},{"amount":29000,"bizname":"平仓委托成交","biztime":"2018-05-20T04:55:05.15","biztype":7,"canusedamount":4528162.82,"comment":"用户:8000006,委托号:789,平仓成交扣掉手续费盈亏:6500,还原保证金22500,成交数量:5","contractid":17,"currenttype":"YYB","flowid":782,"fundaccountid":8000006,"margin":22500,"objectid":"","orderno":789,"profit":6500,"quantity":5,"tradefee":0},{"amount":58000,"bizname":"平仓委托成交","biztime":"2018-05-20T04:55:04.357","biztype":7,"canusedamount":4499162.82,"comment":"用户:8000006,委托号:788,平仓成交扣掉手续费盈亏:13000,还原保证金45000,成交数量:10","contractid":17,"currenttype":"YYB","flowid":781,"fundaccountid":8000006,"margin":45000,"objectid":"","orderno":788,"profit":13000,"quantity":10,"tradefee":0},{"amount":268775,"bizname":"平仓委托成交","biztime":"2018-05-20T04:55:03.56","biztype":7,"canusedamount":4441162.82,"comment":"用户:8000006,委托号:787,平仓成交扣掉手续费盈亏:8775,还原保证金260000,成交数量:10","contractid":15,"currenttype":"YYB","flowid":780,"fundaccountid":8000006,"margin":260000,"objectid":"","orderno":787,"profit":8775,"quantity":10,"tradefee":0},{"amount":5785,"bizname":"平仓委托成交","biztime":"2018-05-20T04:55:02.763","biztype":7,"canusedamount":4172387.82,"comment":"用户:8000006,委托号:786,平仓成交扣掉手续费盈亏:585,还原保证金5200,成交数量:1","contractid":14,"currenttype":"YYB","flowid":779,"fundaccountid":8000006,"margin":5200,"objectid":"","orderno":786,"profit":585,"quantity":1,"tradefee":0}],"totalnum":269,"totalpage":54}
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
         * list : [{"amount":58575,"bizname":"平仓委托成交","biztime":"2018-05-20T04:55:05.94","biztype":7,"canusedamount":4586737.82,"comment":"用户:8000006,委托号:790,平仓成交扣掉手续费盈亏:3575,还原保证金55000,成交数量:10","contractid":16,"currenttype":"YYB","flowid":783,"fundaccountid":8000006,"margin":55000,"objectid":"","orderno":790,"profit":3575,"quantity":10,"tradefee":0},{"amount":29000,"bizname":"平仓委托成交","biztime":"2018-05-20T04:55:05.15","biztype":7,"canusedamount":4528162.82,"comment":"用户:8000006,委托号:789,平仓成交扣掉手续费盈亏:6500,还原保证金22500,成交数量:5","contractid":17,"currenttype":"YYB","flowid":782,"fundaccountid":8000006,"margin":22500,"objectid":"","orderno":789,"profit":6500,"quantity":5,"tradefee":0},{"amount":58000,"bizname":"平仓委托成交","biztime":"2018-05-20T04:55:04.357","biztype":7,"canusedamount":4499162.82,"comment":"用户:8000006,委托号:788,平仓成交扣掉手续费盈亏:13000,还原保证金45000,成交数量:10","contractid":17,"currenttype":"YYB","flowid":781,"fundaccountid":8000006,"margin":45000,"objectid":"","orderno":788,"profit":13000,"quantity":10,"tradefee":0},{"amount":268775,"bizname":"平仓委托成交","biztime":"2018-05-20T04:55:03.56","biztype":7,"canusedamount":4441162.82,"comment":"用户:8000006,委托号:787,平仓成交扣掉手续费盈亏:8775,还原保证金260000,成交数量:10","contractid":15,"currenttype":"YYB","flowid":780,"fundaccountid":8000006,"margin":260000,"objectid":"","orderno":787,"profit":8775,"quantity":10,"tradefee":0},{"amount":5785,"bizname":"平仓委托成交","biztime":"2018-05-20T04:55:02.763","biztype":7,"canusedamount":4172387.82,"comment":"用户:8000006,委托号:786,平仓成交扣掉手续费盈亏:585,还原保证金5200,成交数量:1","contractid":14,"currenttype":"YYB","flowid":779,"fundaccountid":8000006,"margin":5200,"objectid":"","orderno":786,"profit":585,"quantity":1,"tradefee":0}]
         * totalnum : 269
         * totalpage : 54
         */

        private int totalnum;//总条数
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
             * amount : 58575.0
             * bizname : 平仓委托成交
             * biztime : 2018-05-20T04:55:05.94
             * biztype : 7
             * canusedamount : 4586737.82
             * comment : 用户:8000006,委托号:790,平仓成交扣掉手续费盈亏:3575,还原保证金55000,成交数量:10
             * contractid : 16
             * currenttype : YYB
             * flowid : 783
             * fundaccountid : 8000006
             * margin : 55000.0
             * objectid :
             * orderno : 790
             * profit : 3575.0
             * quantity : 10
             * tradefee : 0.0
             */

            private double amount;//金额
            private String bizname;//操作类型
            private String biztime;//操作时间
            /*(8=充值，9=提现，7=平仓委托成交,11=开仓委托成交) */
            private int biztype;
            private double canusedamount;//可用余额
            private String comment;//描述
            private int contractid;//合约ID
            private String currenttype;//当前币种
            private int flowid;//流水ID
            private int fundaccountid;//资金账号ID
            private double margin;//保证金(冻结保证金)
            private String objectid;//对象ID
            private int orderno;//订单号
            private double profit;//止盈
            private int quantity;//手数
            private double tradefee;//手续费(扣除交易费用)
            private String symbol;
            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }





            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getBizname() {
                return bizname;
            }

            public void setBizname(String bizname) {
                this.bizname = bizname;
            }

            public String getBiztime() {
                return biztime;
            }

            public void setBiztime(String biztime) {
                this.biztime = biztime;
            }

            public int getBiztype() {
                return biztype;
            }

            public void setBiztype(int biztype) {
                this.biztype = biztype;
            }

            public double getCanusedamount() {
                return canusedamount;
            }

            public void setCanusedamount(double canusedamount) {
                this.canusedamount = canusedamount;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public int getContractid() {
                return contractid;
            }

            public void setContractid(int contractid) {
                this.contractid = contractid;
            }

            public String getCurrenttype() {
                return currenttype;
            }

            public void setCurrenttype(String currenttype) {
                this.currenttype = currenttype;
            }

            public int getFlowid() {
                return flowid;
            }

            public void setFlowid(int flowid) {
                this.flowid = flowid;
            }

            public int getFundaccountid() {
                return fundaccountid;
            }

            public void setFundaccountid(int fundaccountid) {
                this.fundaccountid = fundaccountid;
            }

            public double getMargin() {
                return margin;
            }

            public void setMargin(double margin) {
                this.margin = margin;
            }

            public String getObjectid() {
                return objectid;
            }

            public void setObjectid(String objectid) {
                this.objectid = objectid;
            }

            public int getOrderno() {
                return orderno;
            }

            public void setOrderno(int orderno) {
                this.orderno = orderno;
            }

            public double getProfit() {
                return profit;
            }

            public void setProfit(double profit) {
                this.profit = profit;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public double getTradefee() {
                return tradefee;
            }

            public void setTradefee(double tradefee) {
                this.tradefee = tradefee;
            }
        }
    }
}
