package com.mex.GalaxyChain.bean.requestbean;

import com.mex.GalaxyChain.net.bean.BaseBean;

import java.util.List;

public class GoodsPriceBean2 extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bidPrice : 12840.34
         * offerPrice : 12841.99
         * preClose : 12840.34
         * status : 2
         * symbol : D30EUR
         */

        private double bidPrice;
        private double offerPrice;
        private double preClose;
        private int status;
        private String symbol;

        public double getBidPrice() {
            return bidPrice;
        }

        public void setBidPrice(double bidPrice) {
            this.bidPrice = bidPrice;
        }

        public double getOfferPrice() {
            return offerPrice;
        }

        public void setOfferPrice(double offerPrice) {
            this.offerPrice = offerPrice;
        }

        public double getPreClose() {
            return preClose;
        }

        public void setPreClose(double preClose) {
            this.preClose = preClose;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }
}
