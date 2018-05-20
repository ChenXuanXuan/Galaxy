package com.mex.GalaxyChain.net;

/**


 * 存放所有的端口号
 */

public interface NetFuncConstants {
	String POST_REGISTER = "api/reg_mobile"; //手机注册
    String POST_LOGIN = "api/login_mobile"; //手机登陆
	String GET_CODE = "api/verifycode_sms";
	String RESET_PHONE_LOGIN_PW="api/reset_password_by_ph";//重置手机密码


    String DEBUG_MARKET_URL="http://39.105.28.205:7000/";

    String POST_SYMBOL = DEBUG_MARKET_URL+"api/Symbol/GetDescription";

    String  POST_GOODSPRICE=DEBUG_MARKET_URL+"api/Symbol/GetPrice";


    /*

      apiUrls
accountUrl	"http://39.105.28.205:5000/"
marketUrl	"http://39.105.28.205:7000/"
tradeUrl	"http://39.105.28.205:8001/"
mexUrl	"http://aaa.mex.group:81/exchange-api/"
marketWS	"ws://39.105.28.205:9092/TickData"
version	"1.0.0"

     */


     //王皓 登陆注册接口
   String ACCOUNTURL="http://39.105.28.205:5000";
    String POST_USERINFO_LOGIN =ACCOUNTURL+"/api/Userinfo/PostLogin" ;
    String POST_REALNAME_AUTH=ACCOUNTURL+"/api/Userinfo/PostRealNameAuth";  // 实名认证
    String GET_USER_ACCOUNTINFO=ACCOUNTURL+"/api/Account/GetAccountInfo";//获取账号信息
    String GET_ACCOUNT_MONEYFLOW_LIST=ACCOUNTURL+"/api/Account/GetFundAccountMoneyFlowList";//资金明细

      String TRADEURL="http://39.105.28.205:8001";
       String  GET_ORDER_BUYPAGE=TRADEURL+"/api/Order/GetBuyPage";//买入的所有信息
         String   POST_ORDER_MORE = TRADEURL+"/api/Order/PostOrder";   //下单
          String POST_CLOSE_ORDER=TRADEURL+"/api/Order/PostClosepostion"; //平仓
    String POST_TRADE_DETAIL_LIST=TRADEURL+"/api/Trade/GetTradeDetailList";  //结算列表

      String POST_HOLD_POSITION =TRADEURL+"/api/Trade/GetTradeHomeList"; //持仓列表


      String POST_LOGIN_OUT=ACCOUNTURL+"/api/Userinfo/PostLogout"; //注销


     String MARKETURL="http://39.105.28.205:7000";
    String GET_HISTORY_KLIINE=MARKETURL+"/api/KLine/GetHistory";



}
