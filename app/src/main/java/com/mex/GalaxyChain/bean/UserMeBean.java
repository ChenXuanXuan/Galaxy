package com.mex.GalaxyChain.bean;

import com.mex.GalaxyChain.net.bean.BaseBean;

import java.util.List;

public class UserMeBean extends BaseBean {


    /**
     * code : 0
     * data : {"auth":{"click":"go-c2","level":1,"status":1,"type":"C"},"notice":{"title":"测试APP"},"open_coins":[{"symbol":"OMG","withdraw_one_max":"150000","withdraw_one_min":"1"},{"symbol":"USDT","withdraw_one_max":"1000","withdraw_one_min":"1"},{"symbol":"BTC","withdraw_one_max":"1000","withdraw_one_min":"0.01"},{"symbol":"ETH","withdraw_one_max":"100","withdraw_one_min":"0.01"},{"symbol":"AUT","withdraw_one_max":"1000","withdraw_one_min":"0.01"},{"symbol":"MEX","withdraw_one_max":"1000000","withdraw_one_min":"100"},{"symbol":"GWT","withdraw_one_max":"1000000","withdraw_one_min":"10000"},{"symbol":"TRX","withdraw_one_max":"20000000","withdraw_one_min":"50"},{"symbol":"SNT","withdraw_one_max":"20000000","withdraw_one_min":"100"},{"symbol":"ICX","withdraw_one_max":"200000","withdraw_one_min":"10"},{"symbol":"DGD","withdraw_one_max":"10000","withdraw_one_min":"0.1"},{"symbol":"LTC","withdraw_one_max":"10000","withdraw_one_min":"0.1"},{"symbol":"BCH","withdraw_one_max":"10000","withdraw_one_min":"0.01"},{"symbol":"ETC","withdraw_one_max":"10000","withdraw_one_min":"0.5"},{"symbol":"AGT","withdraw_one_max":"10000","withdraw_one_min":"0.5"},{"symbol":"JPC","withdraw_one_max":"100000","withdraw_one_min":"100"},{"symbol":"JYF","withdraw_one_max":"1000000","withdraw_one_min":"400"},{"symbol":"GWST","withdraw_one_max":"1000000","withdraw_one_min":"1000"},{"symbol":"YY"},{"symbol":"AE","withdraw_one_max":"100000","withdraw_one_min":"5"},{"symbol":"ZIL","withdraw_one_max":"1000000","withdraw_one_min":"50"},{"symbol":"VEN","withdraw_one_max":"100000","withdraw_one_min":"5"}],"open_markets":[{"symbol":"YYCNY"},{"limit_min":"0.00001","market_buy_min":"0.000001","market_sell_min":"0.00001","symbol":"ETHBTC"},{"limit_min":"1","market_buy_min":"0.00000001","market_sell_min":"1","symbol":"MEXBTC"},{"limit_min":"500","market_buy_min":"0.00000001","market_sell_min":"500","symbol":"GWTBTC"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"DGDBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"LTCBTC"},{"limit_min":"0.001","market_buy_min":"0.00000001","market_sell_min":"0.001","symbol":"BCHBTC"},{"limit_min":"0.001","market_buy_min":"0.00000001","market_sell_min":"0.001","symbol":"ETCBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"OMGBTC"},{"limit_min":"0.1","market_buy_min":"0.00000001","market_sell_min":"0.1","symbol":"TRXBTC"},{"limit_min":"1","market_buy_min":"0.00000001","market_sell_min":"1","symbol":"SNTBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"ICXBTC"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"AGTBTC"},{"limit_min":"0.0001","market_buy_min":"0.00000001","market_sell_min":"0.0001","symbol":"JPCBTC"},{"limit_min":"1","market_buy_min":"0.00000001","market_sell_min":"1","symbol":"JYFBTC"},{"limit_min":"0.1","market_buy_min":"0.00000001","market_sell_min":"0.1","symbol":"GWSTBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"AEBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"ZILBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"VENBTC"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"LTCETH"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"BCHETH"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.01","symbol":"ETCETH"},{"limit_min":"10","market_buy_min":"0.00000001","market_sell_min":"10","symbol":"MEXETH"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"DGDETH"},{"limit_min":"0.01","market_buy_min":"0.000001","market_sell_min":"0.01","symbol":"OMGETH"},{"limit_min":"1","market_buy_min":"0.00000001","market_sell_min":"1","symbol":"TRXETH"},{"limit_min":"1","market_buy_min":"0.00000001","market_sell_min":"1","symbol":"SNTETH"},{"limit_min":"0.01","market_buy_min":"0.000001","market_sell_min":"0.01","symbol":"ICXETH"},{"symbol":"YYETH"},{"limit_min":"0.01","market_buy_min":"0.000001","market_sell_min":"0.01","symbol":"AGTETH"},{"limit_min":"0.000001","market_buy_min":"0.000001","market_sell_min":"0.000001","symbol":"BTCAUT"},{"limit_min":"0.0001","market_buy_min":"0.000001","market_sell_min":"0.0001","symbol":"ETHAUT"},{"limit_min":"10","market_buy_min":"0.00000001","market_sell_min":"10","symbol":"MEXAUT"},{"limit_min":"0.00001","market_buy_min":"0.01","market_sell_min":"0.00001","symbol":"AUTUSDT"},{"limit_min":"0.001","market_buy_min":"0.0001","market_sell_min":"0.001","symbol":"MEXUSDT"},{"limit_min":"0.000001","market_buy_min":"0.01","market_sell_min":"0.000001","symbol":"BTCUSDT"},{"limit_min":"0.00001","market_buy_min":"0.01","market_sell_min":"0.00001","symbol":"ETHUSDT"},{"limit_min":"500","market_buy_min":"0.0001","market_sell_min":"500","symbol":"GWTMEX"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"AGTMEX"}],"user":{"capital_status":1,"countrycode":"86","email":"","exchange_verify":2,"google_authenticator_status":0,"invite_code":"EZLLLV","mobile":"15101650501","mobile_authenticator_status":1,"uid":147776,"username":"方明飞"}}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * auth : {"click":"go-c2","level":1,"status":1,"type":"C"}
         * notice : {"title":"测试APP"}
         * open_coins : [{"symbol":"OMG","withdraw_one_max":"150000","withdraw_one_min":"1"},{"symbol":"USDT","withdraw_one_max":"1000","withdraw_one_min":"1"},{"symbol":"BTC","withdraw_one_max":"1000","withdraw_one_min":"0.01"},{"symbol":"ETH","withdraw_one_max":"100","withdraw_one_min":"0.01"},{"symbol":"AUT","withdraw_one_max":"1000","withdraw_one_min":"0.01"},{"symbol":"MEX","withdraw_one_max":"1000000","withdraw_one_min":"100"},{"symbol":"GWT","withdraw_one_max":"1000000","withdraw_one_min":"10000"},{"symbol":"TRX","withdraw_one_max":"20000000","withdraw_one_min":"50"},{"symbol":"SNT","withdraw_one_max":"20000000","withdraw_one_min":"100"},{"symbol":"ICX","withdraw_one_max":"200000","withdraw_one_min":"10"},{"symbol":"DGD","withdraw_one_max":"10000","withdraw_one_min":"0.1"},{"symbol":"LTC","withdraw_one_max":"10000","withdraw_one_min":"0.1"},{"symbol":"BCH","withdraw_one_max":"10000","withdraw_one_min":"0.01"},{"symbol":"ETC","withdraw_one_max":"10000","withdraw_one_min":"0.5"},{"symbol":"AGT","withdraw_one_max":"10000","withdraw_one_min":"0.5"},{"symbol":"JPC","withdraw_one_max":"100000","withdraw_one_min":"100"},{"symbol":"JYF","withdraw_one_max":"1000000","withdraw_one_min":"400"},{"symbol":"GWST","withdraw_one_max":"1000000","withdraw_one_min":"1000"},{"symbol":"YY"},{"symbol":"AE","withdraw_one_max":"100000","withdraw_one_min":"5"},{"symbol":"ZIL","withdraw_one_max":"1000000","withdraw_one_min":"50"},{"symbol":"VEN","withdraw_one_max":"100000","withdraw_one_min":"5"}]
         * open_markets : [{"symbol":"YYCNY"},{"limit_min":"0.00001","market_buy_min":"0.000001","market_sell_min":"0.00001","symbol":"ETHBTC"},{"limit_min":"1","market_buy_min":"0.00000001","market_sell_min":"1","symbol":"MEXBTC"},{"limit_min":"500","market_buy_min":"0.00000001","market_sell_min":"500","symbol":"GWTBTC"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"DGDBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"LTCBTC"},{"limit_min":"0.001","market_buy_min":"0.00000001","market_sell_min":"0.001","symbol":"BCHBTC"},{"limit_min":"0.001","market_buy_min":"0.00000001","market_sell_min":"0.001","symbol":"ETCBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"OMGBTC"},{"limit_min":"0.1","market_buy_min":"0.00000001","market_sell_min":"0.1","symbol":"TRXBTC"},{"limit_min":"1","market_buy_min":"0.00000001","market_sell_min":"1","symbol":"SNTBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"ICXBTC"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"AGTBTC"},{"limit_min":"0.0001","market_buy_min":"0.00000001","market_sell_min":"0.0001","symbol":"JPCBTC"},{"limit_min":"1","market_buy_min":"0.00000001","market_sell_min":"1","symbol":"JYFBTC"},{"limit_min":"0.1","market_buy_min":"0.00000001","market_sell_min":"0.1","symbol":"GWSTBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"AEBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"ZILBTC"},{"limit_min":"0.01","market_buy_min":"0.00000001","market_sell_min":"0.01","symbol":"VENBTC"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"LTCETH"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"BCHETH"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.01","symbol":"ETCETH"},{"limit_min":"10","market_buy_min":"0.00000001","market_sell_min":"10","symbol":"MEXETH"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"DGDETH"},{"limit_min":"0.01","market_buy_min":"0.000001","market_sell_min":"0.01","symbol":"OMGETH"},{"limit_min":"1","market_buy_min":"0.00000001","market_sell_min":"1","symbol":"TRXETH"},{"limit_min":"1","market_buy_min":"0.00000001","market_sell_min":"1","symbol":"SNTETH"},{"limit_min":"0.01","market_buy_min":"0.000001","market_sell_min":"0.01","symbol":"ICXETH"},{"symbol":"YYETH"},{"limit_min":"0.01","market_buy_min":"0.000001","market_sell_min":"0.01","symbol":"AGTETH"},{"limit_min":"0.000001","market_buy_min":"0.000001","market_sell_min":"0.000001","symbol":"BTCAUT"},{"limit_min":"0.0001","market_buy_min":"0.000001","market_sell_min":"0.0001","symbol":"ETHAUT"},{"limit_min":"10","market_buy_min":"0.00000001","market_sell_min":"10","symbol":"MEXAUT"},{"limit_min":"0.00001","market_buy_min":"0.01","market_sell_min":"0.00001","symbol":"AUTUSDT"},{"limit_min":"0.001","market_buy_min":"0.0001","market_sell_min":"0.001","symbol":"MEXUSDT"},{"limit_min":"0.000001","market_buy_min":"0.01","market_sell_min":"0.000001","symbol":"BTCUSDT"},{"limit_min":"0.00001","market_buy_min":"0.01","market_sell_min":"0.00001","symbol":"ETHUSDT"},{"limit_min":"500","market_buy_min":"0.0001","market_sell_min":"500","symbol":"GWTMEX"},{"limit_min":"0.001","market_buy_min":"0.000001","market_sell_min":"0.001","symbol":"AGTMEX"}]
         * user : {"capital_status":1,"countrycode":"86","email":"","exchange_verify":2,"google_authenticator_status":0,"invite_code":"EZLLLV","mobile":"15101650501","mobile_authenticator_status":1,"uid":147776,"username":"方明飞"}
         */

        private AuthBean auth;
        private NoticeBean notice;
        private UserBean user;
        private List<OpenCoinsBean> open_coins;
        private List<OpenMarketsBean> open_markets;

        public AuthBean getAuth() {
            return auth;
        }

        public void setAuth(AuthBean auth) {
            this.auth = auth;
        }

        public NoticeBean getNotice() {
            return notice;
        }

        public void setNotice(NoticeBean notice) {
            this.notice = notice;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<OpenCoinsBean> getOpen_coins() {
            return open_coins;
        }

        public void setOpen_coins(List<OpenCoinsBean> open_coins) {
            this.open_coins = open_coins;
        }

        public List<OpenMarketsBean> getOpen_markets() {
            return open_markets;
        }

        public void setOpen_markets(List<OpenMarketsBean> open_markets) {
            this.open_markets = open_markets;
        }

        public static class AuthBean {
            /**
             * click : go-c2
             * level : 1
             * status : 1
             * type : C
             */

            private String click;
            private int level;
            private int status;
            private String type;

            public String getClick() {
                return click;
            }

            public void setClick(String click) {
                this.click = click;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class NoticeBean {
            /**
             * title : 测试APP
             */

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class UserBean {
            /**
             * capital_status : 1
             * countrycode : 86
             * email :
             * exchange_verify : 2
             * google_authenticator_status : 0
             * invite_code : EZLLLV
             * mobile : 15101650501
             * mobile_authenticator_status : 1
             * uid : 147776
             * username : 方明飞
             */

            private int capital_status;
            private String countrycode;
            private String email;
            private int exchange_verify;
            private int google_authenticator_status;
            private String invite_code;
            private String mobile;
            private int mobile_authenticator_status;
            private int uid;
            private String username;

            public int getCapital_status() {
                return capital_status;
            }

            public void setCapital_status(int capital_status) {
                this.capital_status = capital_status;
            }

            public String getCountrycode() {
                return countrycode;
            }

            public void setCountrycode(String countrycode) {
                this.countrycode = countrycode;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getExchange_verify() {
                return exchange_verify;
            }

            public void setExchange_verify(int exchange_verify) {
                this.exchange_verify = exchange_verify;
            }

            public int getGoogle_authenticator_status() {
                return google_authenticator_status;
            }

            public void setGoogle_authenticator_status(int google_authenticator_status) {
                this.google_authenticator_status = google_authenticator_status;
            }

            public String getInvite_code() {
                return invite_code;
            }

            public void setInvite_code(String invite_code) {
                this.invite_code = invite_code;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getMobile_authenticator_status() {
                return mobile_authenticator_status;
            }

            public void setMobile_authenticator_status(int mobile_authenticator_status) {
                this.mobile_authenticator_status = mobile_authenticator_status;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class OpenCoinsBean {
            /**
             * symbol : OMG
             * withdraw_one_max : 150000
             * withdraw_one_min : 1
             */

            private String symbol;
            private String withdraw_one_max;
            private String withdraw_one_min;

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }

            public String getWithdraw_one_max() {
                return withdraw_one_max;
            }

            public void setWithdraw_one_max(String withdraw_one_max) {
                this.withdraw_one_max = withdraw_one_max;
            }

            public String getWithdraw_one_min() {
                return withdraw_one_min;
            }

            public void setWithdraw_one_min(String withdraw_one_min) {
                this.withdraw_one_min = withdraw_one_min;
            }
        }

        public static class OpenMarketsBean {
            /**
             * symbol : YYCNY
             * limit_min : 0.00001
             * market_buy_min : 0.000001
             * market_sell_min : 0.00001
             */

            private String symbol;
            private String limit_min;
            private String market_buy_min;
            private String market_sell_min;

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }

            public String getLimit_min() {
                return limit_min;
            }

            public void setLimit_min(String limit_min) {
                this.limit_min = limit_min;
            }

            public String getMarket_buy_min() {
                return market_buy_min;
            }

            public void setMarket_buy_min(String market_buy_min) {
                this.market_buy_min = market_buy_min;
            }

            public String getMarket_sell_min() {
                return market_sell_min;
            }

            public void setMarket_sell_min(String market_sell_min) {
                this.market_sell_min = market_sell_min;
            }
        }
    }
}
