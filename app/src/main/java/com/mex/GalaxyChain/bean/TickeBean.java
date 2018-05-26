package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

/**
 * Created by lsj-pc on 2018/5/10.
 */

public class TickeBean extends BaseBean {

    /**
     * Status : 1
     * BidPrice : 1.19352（cc 买价）
     * BidSize : 4500000.0 （cc 买价交易数量）
     * OfferPrice : 1.19357
     * OfferSize : 4000000.0
     * High : 1.18695
     * Low : 1.18425
     * Open : 1.18548
     * PreClose : 1.18548
     * UtcTimeStamp : 1525959520632(cc)
     * Symbol : EURUSD
     */

    private int Status;
    private String BidPrice;
    private String BidSize;
    private String OfferPrice;
    private String OfferSize;
    private String High;
    private String Low;
    private String Open;
    private String PreClose;
    private long UtcTimeStamp;
    private String Symbol;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getBidPrice() {
        return BidPrice;
    }

    public void setBidPrice(String bidPrice) {
        BidPrice = bidPrice;
    }

    public String getBidSize() {
        return BidSize;
    }

    public void setBidSize(String bidSize) {
        BidSize = bidSize;
    }

    public String getOfferPrice() {
        return OfferPrice;
    }

    public void setOfferPrice(String offerPrice) {
        OfferPrice = offerPrice;
    }

    public String getOfferSize() {
        return OfferSize;
    }

    public void setOfferSize(String offerSize) {
        OfferSize = offerSize;
    }

    public String getHigh() {
        return High;
    }

    public void setHigh(String high) {
        High = high;
    }

    public String getLow() {
        return Low;
    }

    public void setLow(String low) {
        Low = low;
    }

    public String getOpen() {
        return Open;
    }

    public void setOpen(String open) {
        Open = open;
    }

    public String getPreClose() {
        return PreClose;
    }

    public void setPreClose(String preClose) {
        PreClose = preClose;
    }

    public long getUtcTimeStamp() {
        return UtcTimeStamp;
    }

    public void setUtcTimeStamp(long UtcTimeStamp) {
        this.UtcTimeStamp = UtcTimeStamp;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }
}
