package com.mex.GalaxyChain.event;

import com.mex.GalaxyChain.bean.TickeBean;
import com.mex.GalaxyChain.utils.GsonUtils;

/**
 * Created by lsj-pc on 2018/5/10.
 */

public class TickerEvent {
    private String msgs;
    private TickeBean tickeBean;

    public TickeBean getTickeBean() {
        return (TickeBean) GsonUtils.getObject(TickeBean.class,msgs);
    }

    public void setTickeBean(TickeBean tickeBean) {
        this.tickeBean = tickeBean;
    }

    public TickerEvent(String msg) {
       this.msgs = msg;
    }

    public String getMsgs() {
        return msgs;
    }
}
