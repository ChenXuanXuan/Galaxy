package com.mex.GalaxyChain.bean.requestbean;

import com.mex.GalaxyChain.net.bean.BaseBean;

import java.util.List;

public class GoodsPriceBean extends BaseBean {


   /*{
    "code": 200,
    "data": [
        {
            "bidPrice": 12840.34,
            "offerPrice": 12841.99,
            "preClose": 12840.34,
            "status": 2,
            "symbol": "D30EUR"
        },
        {
            "bidPrice": 1.19591,
            "offerPrice": 1.19635,
            "preClose": 1.19591,
            "status": 2,
            "symbol": "EURUSD"
        },
        {
            "bidPrice": 29922.0,
            "offerPrice": 29931.0,
            "preClose": 29922.0,
            "status": 2,
            "symbol": "H33HKD"
        },
        {
            "bidPrice": 69.687,
            "offerPrice": 69.736,
            "preClose": 69.687,
            "status": 2,
            "symbol": "USOUSD"
        },
        {
            "bidPrice": 2.717,
            "offerPrice": 2.737,
            "preClose": 2.717,
            "status": 2,
            "symbol": "XNGUSD"
        }
    ],
    "msg": "成功"
}
   *
   * */
    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

        private double bidPrice; //买价 12840.34     1.19591   29922.0
        private double offerPrice;//最新价 (卖价)   12841.99     1.19635  29931.0
        private double preClose; //昨日收盘价12840.34  1.19591  29922.0
        private int status; //交易状态
        private String symbol; //商品

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
