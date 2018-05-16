package com.mex.GalaxyChain.bean;

import com.google.gson.annotations.SerializedName;
import com.mex.GalaxyChain.net.bean.BaseBean;

import java.util.List;

public class SymbolBean   extends BaseBean {


    /**
     * code : 200
     * data : {"handNumS":[{"id":1,"name":"1手"},{"id":2,"name":"2手"},{"id":3,"name":"3手"},{"id":5,"name":"5手"},{"id":10,"name":"10手"}],"stopLossTimes":[{"id":1,"name":"1倍"},{"id":2,"name":"2倍"},{"id":3,"name":"3倍"},{"id":4,"name":"4倍"}],"symbolInfos":[{"closetime":"16:10","commoditytype":1,"contractid":9,"contractname":"EURUSD","currencytype":"USD","decimalplace":4,"lasttradingdate":"1804","margin":100,"perprofit":11,"perprofitnumber":1,"stoplossratio":0.7,"symbol":"EURUSD","symbolname":"欧元兑美元"},{"closetime":"16:10","commoditytype":1,"contractid":10,"contractname":"天然气","currencytype":"USD","decimalplace":2,"lasttradingdate":"1804","margin":100,"perprofit":11,"perprofitnumber":1,"stoplossratio":0.7,"symbol":"XNGUSD","symbolname":"天然气"}],"symbolStatusDefine":{"1":"开盘中","2":"未开盘","3":"交易中","4":"暂停交易"}}
     * msg : 成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * handNumS : [{"id":1,"name":"1手"},{"id":2,"name":"2手"},{"id":3,"name":"3手"},{"id":5,"name":"5手"},{"id":10,"name":"10手"}]
         * stopLossTimes : [{"id":1,"name":"1倍"},{"id":2,"name":"2倍"},{"id":3,"name":"3倍"},{"id":4,"name":"4倍"}]
         * symbolInfos : [{"closetime":"16:10","commoditytype":1,"contractid":9,"contractname":"EURUSD","currencytype":"USD","decimalplace":4,"lasttradingdate":"1804","margin":100,"perprofit":11,"perprofitnumber":1,"stoplossratio":0.7,"symbol":"EURUSD","symbolname":"欧元兑美元"},{"closetime":"16:10","commoditytype":1,"contractid":10,"contractname":"天然气","currencytype":"USD","decimalplace":2,"lasttradingdate":"1804","margin":100,"perprofit":11,"perprofitnumber":1,"stoplossratio":0.7,"symbol":"XNGUSD","symbolname":"天然气"}]
         * symbolStatusDefine : {"1":"开盘中","2":"未开盘","3":"交易中","4":"暂停交易"}
         */

        private SymbolStatusDefineBean symbolStatusDefine;
        private List<HandNumSBean> handNumS;
        private List<StopLossTimesBean> stopLossTimes;
        private List<SymbolInfosBean> symbolInfos;

        public SymbolStatusDefineBean getSymbolStatusDefine() {
            return symbolStatusDefine;
        }

        public void setSymbolStatusDefine(SymbolStatusDefineBean symbolStatusDefine) {
            this.symbolStatusDefine = symbolStatusDefine;
        }

        public List<HandNumSBean> getHandNumS() {
            return handNumS;
        }

        public void setHandNumS(List<HandNumSBean> handNumS) {
            this.handNumS = handNumS;
        }

        public List<StopLossTimesBean> getStopLossTimes() {
            return stopLossTimes;
        }

        public void setStopLossTimes(List<StopLossTimesBean> stopLossTimes) {
            this.stopLossTimes = stopLossTimes;
        }

        public List<SymbolInfosBean> getSymbolInfos() {
            return symbolInfos;
        }

        public void setSymbolInfos(List<SymbolInfosBean> symbolInfos) {
            this.symbolInfos = symbolInfos;
        }

        public static class SymbolStatusDefineBean {
            /**
             * 1 : 开盘中
             * 2 : 未开盘
             * 3 : 交易中
             * 4 : 暂停交易
             */

            @SerializedName("1")
            private String _$1;
            @SerializedName("2")
            private String _$2;
            @SerializedName("3")
            private String _$3;
            @SerializedName("4")
            private String _$4;

            public String get_$1() {
                return _$1;
            }

            public void set_$1(String _$1) {
                this._$1 = _$1;
            }

            public String get_$2() {
                return _$2;
            }

            public void set_$2(String _$2) {
                this._$2 = _$2;
            }

            public String get_$3() {
                return _$3;
            }

            public void set_$3(String _$3) {
                this._$3 = _$3;
            }

            public String get_$4() {
                return _$4;
            }

            public void set_$4(String _$4) {
                this._$4 = _$4;
            }
        }

        public static class HandNumSBean {
            /**
             * id : 1
             * name : 1手
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class StopLossTimesBean {
            /**
             * id : 1
             * name : 1倍
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class SymbolInfosBean {
            /**
             * closetime : 16:10
             * commoditytype : 1
             * contractid : 9
             * contractname : EURUSD
             * currencytype : USD
             * decimalplace : 4
             * lasttradingdate : 1804
             * margin : 100.0
             * perprofit : 11.0
             * perprofitnumber : 1.0
             * stoplossratio : 0.7
             * symbol : EURUSD
             * symbolname : 欧元兑美元
             */

            private String closetime;//自动平仓时间
            private int commoditytype;//商品类型
            private int contractid; //合约ID
            private String contractname;//合约名字

            private int decimalplace;//小数点精度
            private String lasttradingdate;//最后交易日期
            private double margin;//履约保证金
            private String currencytype;//币种类型
            private double perprofit;//收益(赚了多少钱(11.0))
            private double perprofitnumber;//收益点数(涨了多少个点)
            private double stoplossratio;//履约保证金比率
            private String symbol;//品种EURUSD
            private String symbolname;//品种名称(欧元兑美元

            public String getClosetime() {
                return closetime;
            }

            public void setClosetime(String closetime) {
                this.closetime = closetime;
            }

            public int getCommoditytype() {
                return commoditytype;
            }

            public void setCommoditytype(int commoditytype) {
                this.commoditytype = commoditytype;
            }

            public int getContractid() {
                return contractid;
            }

            public void setContractid(int contractid) {
                this.contractid = contractid;
            }

            public String getContractname() {
                return contractname;
            }

            public void setContractname(String contractname) {
                this.contractname = contractname;
            }

            public String getCurrencytype() {
                return currencytype;
            }

            public void setCurrencytype(String currencytype) {
                this.currencytype = currencytype;
            }

            public int getDecimalplace() {
                return decimalplace;
            }

            public void setDecimalplace(int decimalplace) {
                this.decimalplace = decimalplace;
            }

            public String getLasttradingdate() {
                return lasttradingdate;
            }

            public void setLasttradingdate(String lasttradingdate) {
                this.lasttradingdate = lasttradingdate;
            }

            public double getMargin() {
                return margin;
            }

            public void setMargin(double margin) {
                this.margin = margin;
            }

            public double getPerprofit() {
                return perprofit;
            }

            public void setPerprofit(double perprofit) {
                this.perprofit = perprofit;
            }

            public double getPerprofitnumber() {
                return perprofitnumber;
            }

            public void setPerprofitnumber(double perprofitnumber) {
                this.perprofitnumber = perprofitnumber;
            }

            public double getStoplossratio() {
                return stoplossratio;
            }

            public void setStoplossratio(double stoplossratio) {
                this.stoplossratio = stoplossratio;
            }

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }

            public String getSymbolname() {
                return symbolname;
            }

            public void setSymbolname(String symbolname) {
                this.symbolname = symbolname;
            }
        }
    }
}
