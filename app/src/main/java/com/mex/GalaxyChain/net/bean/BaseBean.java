package com.mex.GalaxyChain.net.bean;

import java.io.Serializable;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/3/18 下午3:31
 */
public class BaseBean implements Serializable{


    /**
     * url : http://www.kkcoin.com
     * desc : 马上升级:
     1.体验优化
     2.bug修复
     3.请升级后继续体验

     */

    private String url;
    private String desc;
    private String version;

    public String getVersion() {
        return version == null ? "" : version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc == null ? "" : desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
