/**
 * Model基类
 * <p/>
 * LSJ
 * 2016年8月15日
 */
package com.mex.GalaxyChain.mychart;


import java.io.Serializable;


/**
 * @author LSJ
 * @date 2016年8月15日
 */
public class BaseEntitiy implements Serializable {
    private String code;
    private String msg;

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

}
