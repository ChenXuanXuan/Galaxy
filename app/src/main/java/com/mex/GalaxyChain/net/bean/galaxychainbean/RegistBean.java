package com.mex.GalaxyChain.net.bean.galaxychainbean;

import com.mex.GalaxyChain.net.bean.BaseBean;

public class RegistBean extends BaseBean {
    /**
     * code : 0
     * msg : 成功
     * data : {"expire":1525773557058,"token":"efcd08cd92667520fcc8b93ba6d47797"}
     */

    /**
     * {"code":"0","msg":"成功","data":{"expire":1525773557058,"token":"efcd08cd92667520fcc8b93ba6d47797"}}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * expire : 1525773557058
         * token : efcd08cd92667520fcc8b93ba6d47797
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
