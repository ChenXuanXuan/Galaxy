package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

public class PostPayInBean extends BaseBean {


    /**
     * data : {"orderid":2}
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
         * orderid : 2
         */

        private int orderid;

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }
    }
}
