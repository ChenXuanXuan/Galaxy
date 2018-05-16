package com.mex.GalaxyChain.bean;

public class VarietyHoldPosiForAdat {


    public String symbolname;//品种名称(欧元兑美元
    public String closetime;//自动平仓时间


    @Override
    public String toString() {
        return "VarietyHoldPosiForAdat{" +
                "symbolname='" + symbolname + '\'' +
                ", closetime='" + closetime + '\'' +
                '}';
    }
}
