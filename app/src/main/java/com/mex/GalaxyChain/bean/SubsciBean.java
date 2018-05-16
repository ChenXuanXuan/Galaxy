package com.mex.GalaxyChain.bean;

public class SubsciBean {


    /**
     * Operate : 1
     * Symbol : EURUSD
     */

    private int Operate;
    private String Symbol;

    public int getOperate() {
        return Operate;
    }

    public void setOperate(int Operate) {
        this.Operate = Operate;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    @Override
    public String toString() {
        return "SubsciBean{" + "Operate=" + Operate + ", Symbol='" + Symbol + '\'' + '}';
    }
}
