package com.mex.GalaxyChain.net.bean.galaxychainbean;

import com.mex.GalaxyChain.net.bean.BaseBean;

public class LoginBean extends BaseBean {



    /**
     * code : 0
     * data : {"expire":1525601172325,"token":"987ee70ba348872e85ced94f9ff94fea"}
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
         * expire : 1525601172325
         * token : 987ee70ba348872e85ced94f9ff94fea
         */

        private long expire;
        private String token;

        public long getExpire() {
            return expire;
        }

        public void setExpire(long expire) {
            this.expire = expire;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }







}
