package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

import java.util.List;

public class PayOutListBean extends BaseBean {


    /**
     * code : 200
     * data : {"list":[{"amount":15500,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"f6ba8717-6060-4800-a554-83da26362f84","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 23:33:20"},{"amount":1550,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"eab910f7-9604-44ea-bb5c-6b257d589775","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 23:32:52"},{"amount":155,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"cf2a1d5f-bc00-4fbc-84e2-5e2c54f1dcbb","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 23:32:21"},{"amount":155,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"a55ac374-2cae-4753-8f69-8f0b4da65ae2","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 23:32:01"},{"amount":1164643,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"2d241e2b-0573-436a-9a65-83fd036cfdda","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 22:21:05"},{"amount":1164643,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"d7e3fe9c-49cf-4ee0-9eb3-2b23ad6e4a4d","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 22:17:16"},{"amount":886633,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"51fc445a-a59f-4975-85a0-964d32523d41","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 22:13:00"},{"amount":225,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"2131dda7-4a06-4141-a6a6-702283f139dc","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 22:11:41"},{"amount":1555,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"e0cc6574-a412-47f5-98bf-6c158b7b10a5","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 21:58:51"},{"amount":256,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"f2d4dcab-85c1-41fe-b476-4ee2656da1a1","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 21:53:00"},{"amount":152,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"880c095f-440a-4d18-bcfd-45327602cf05","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 21:50:40"},{"amount":258,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"21bb537e-b76d-4243-80eb-1b95bde96037","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 21:48:21"},{"amount":152,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"4009d1a0-7715-4d58-a55e-8d540615a728","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 21:38:33"},{"amount":111,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"befdfda0-152b-4c06-97ad-647a24b37adb","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 19:59:29"},{"amount":5002,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"4621c1c1-0fc4-487d-b695-5b449add7797","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 19:53:55"}],"totalnum":49,"totalpage":4}
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
         * list : [{"amount":15500,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"f6ba8717-6060-4800-a554-83da26362f84","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 23:33:20"},{"amount":1550,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"eab910f7-9604-44ea-bb5c-6b257d589775","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 23:32:52"},{"amount":155,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"cf2a1d5f-bc00-4fbc-84e2-5e2c54f1dcbb","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 23:32:21"},{"amount":155,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"a55ac374-2cae-4753-8f69-8f0b4da65ae2","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 23:32:01"},{"amount":1164643,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"2d241e2b-0573-436a-9a65-83fd036cfdda","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 22:21:05"},{"amount":1164643,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"d7e3fe9c-49cf-4ee0-9eb3-2b23ad6e4a4d","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 22:17:16"},{"amount":886633,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"51fc445a-a59f-4975-85a0-964d32523d41","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 22:13:00"},{"amount":225,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"2131dda7-4a06-4141-a6a6-702283f139dc","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 22:11:41"},{"amount":1555,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"e0cc6574-a412-47f5-98bf-6c158b7b10a5","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 21:58:51"},{"amount":256,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"f2d4dcab-85c1-41fe-b476-4ee2656da1a1","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 21:53:00"},{"amount":152,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"880c095f-440a-4d18-bcfd-45327602cf05","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 21:50:40"},{"amount":258,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"21bb537e-b76d-4243-80eb-1b95bde96037","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 21:48:21"},{"amount":152,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"4009d1a0-7715-4d58-a55e-8d540615a728","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 21:38:33"},{"amount":111,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"befdfda0-152b-4c06-97ad-647a24b37adb","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 19:59:29"},{"amount":5002,"currenttype":"YYB","fundaccountid":8000006,"payouttype":2,"remarks":"提现","serialnumber":"4621c1c1-0fc4-487d-b695-5b449add7797","status":10,"statustring":"提现审核中","withdrawalstime":"2018/5/23 19:53:55"}]
         * totalnum : 49
         * totalpage : 4
         */

        private int totalnum;
        private int totalpage;
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
             * amount : 15500.0
             * currenttype : YYB
             * fundaccountid : 8000006
             * payouttype : 2
             * remarks : 提现
             * serialnumber : f6ba8717-6060-4800-a554-83da26362f84
             * status : 10
             * statustring : 提现审核中
             * withdrawalstime : 2018/5/23 23:33:20
             */

            private double amount;//提现金额
            private double canusedamount;//可用余额
            private String currenttype;//提现币种
            private int fundaccountid;//资金账号ID
            private int payouttype;//出金类型(1=币币,2=法币)

            private String remarks;//备注说明
            private String serialnumber;//流水号
            private int status;//提现状态1=提现审核中,2=提现成功，3=提现失败
            private String statustring;//状态中文
            private String withdrawalstime;//提现时间


            public double getCanusedamount() {
                return canusedamount;
            }

            public void setCanusedamount(double canusedamount) {
                this.canusedamount = canusedamount;
            }




            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getCurrenttype() {
                return currenttype;
            }

            public void setCurrenttype(String currenttype) {
                this.currenttype = currenttype;
            }

            public int getFundaccountid() {
                return fundaccountid;
            }

            public void setFundaccountid(int fundaccountid) {
                this.fundaccountid = fundaccountid;
            }

            public int getPayouttype() {
                return payouttype;
            }

            public void setPayouttype(int payouttype) {
                this.payouttype = payouttype;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getSerialnumber() {
                return serialnumber;
            }

            public void setSerialnumber(String serialnumber) {
                this.serialnumber = serialnumber;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStatustring() {
                return statustring;
            }

            public void setStatustring(String statustring) {
                this.statustring = statustring;
            }

            public String getWithdrawalstime() {
                return withdrawalstime;
            }

            public void setWithdrawalstime(String withdrawalstime) {
                this.withdrawalstime = withdrawalstime;
            }
        }
    }
}
