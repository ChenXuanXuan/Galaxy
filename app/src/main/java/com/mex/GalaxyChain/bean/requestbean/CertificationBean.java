package com.mex.GalaxyChain.bean.requestbean;

public class CertificationBean {


    /**
     * usertoken : string
     * idcard : string
     * realname : string
     */

    private String usertoken;
    private String idcard;
    private String realname;

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
