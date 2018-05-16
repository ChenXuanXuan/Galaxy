package com.mex.GalaxyChain.net.bean;

public class ChainBaseBean {
  //    {"code":"0","msg":null,"data":""}

    /**
     * code : 0
     * msg : null
     * data :
     */

    private String code;
    private Object msg;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
