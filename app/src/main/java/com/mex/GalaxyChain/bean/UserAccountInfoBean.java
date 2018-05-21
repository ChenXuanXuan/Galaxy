package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

public class UserAccountInfoBean  extends BaseBean{


    /**
     * code : 200
     * data : {"accountMoneyInfo":{"amount":40000,"canusedamount":3892042.82,"currenttype":"YYB","frozenamount":0,"frozenmargin":671582.8571,"fundaccountid":8000006,"realnamestatus":1,"totalamount":40000,"totalprofit":516642.82,"tradefee":985100}}
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
         * accountMoneyInfo : {"amount":40000,"canusedamount":3892042.82,"currenttype":"YYB","frozenamount":0,"frozenmargin":671582.8571,"fundaccountid":8000006,"realnamestatus":1,"totalamount":40000,"totalprofit":516642.82,"tradefee":985100}
         */

        private AccountMoneyInfoBean accountMoneyInfo;

        public AccountMoneyInfoBean getAccountMoneyInfo() {
            return accountMoneyInfo;
        }

        public void setAccountMoneyInfo(AccountMoneyInfoBean accountMoneyInfo) {
            this.accountMoneyInfo = accountMoneyInfo;
        }

        public static class AccountMoneyInfoBean {
            /**
             * amount : 40000.0
             * canusedamount : 3892042.82
             * currenttype : YYB
             * frozenamount : 0.0
             * frozenmargin : 671582.8571
             * fundaccountid : 8000006
             * realnamestatus : 1
             * totalamount : 40000.0
             * totalprofit : 516642.82
             * tradefee : 985100.0
             */

            private double amount;//总金额
            private double canusedamount;//可用余额
            private String currenttype;//币种 (YY)
            private double frozenamount;//提现冻结金额

            private double frozenmargin;//冻结保证金(占用保证金)
            private int fundaccountid;//资金账号ID
            private int realnamestatus; //开户状态(1=开户(已实名认证),2=销户)
            private double totalamount;//入金总金额
            private double totalprofit;//总盈亏(浮动)
            private double tradefee;//手续费

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public double getCanusedamount() {
                return canusedamount;
            }

            public void setCanusedamount(double canusedamount) {
                this.canusedamount = canusedamount;
            }

            public String getCurrenttype() {
                return currenttype;
            }

            public void setCurrenttype(String currenttype) {
                this.currenttype = currenttype;
            }

            public double getFrozenamount() {
                return frozenamount;
            }

            public void setFrozenamount(double frozenamount) {
                this.frozenamount = frozenamount;
            }

            public double getFrozenmargin() {
                return frozenmargin;
            }

            public void setFrozenmargin(double frozenmargin) {
                this.frozenmargin = frozenmargin;
            }

            public int getFundaccountid() {
                return fundaccountid;
            }

            public void setFundaccountid(int fundaccountid) {
                this.fundaccountid = fundaccountid;
            }

            public int getRealnamestatus() {
                return realnamestatus;
            }

            public void setRealnamestatus(int realnamestatus) {
                this.realnamestatus = realnamestatus;
            }

            public double getTotalamount() {
                return totalamount;
            }

            public void setTotalamount(double totalamount) {
                this.totalamount = totalamount;
            }

            public double getTotalprofit() {
                return totalprofit;
            }

            public void setTotalprofit(double totalprofit) {
                this.totalprofit = totalprofit;
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
