package com.mex.GalaxyChain.common;

/**
 * Created by LSJ on 18/3/11.
 */

public interface Constants {
	String MARKET_FAV = "fav";
	String MARKET_INN = "inn";
	int INDEX_MARKET = 0;
	int INDEX_EXCHANGE = 1;
	int INDEX_ASSET = 2;
	int INDEX_MINE = 3;
	String EXTRA_EMAIL = "email";
	String EXTRA_PASSWORD = "password";
	String EXTRA_GOOGLE_CODE = "google_code";
	String EXCHANGE_TYPE = "exchange_type";
	String EXTRA_DATA = "extra_data";

	int MAX_DATA_SHOW = 10;
	String EXTRA_CANDLE_TYPE = "extra_candle_type";
	String EXTRA_PAIR_BEAN = "extra_pair_bean";
	int MAX_LEN = 7;
	int PAGE_SIZE = 20;
	String CANDLE_TYPE_TIME_LINE = "1m_0";
	int TYPE_FULL_SCREEN = 2;
	int TYPE_ZOOM_NORMAL = 1;
	int BOOK_LIST_MAX_COUNT = 12;



    int ALL_VARIETY = 0; //全部品种
    int GLOBAL_INDEX = 1;//全球指数
    int CRYPTO_CURRENCY = 2;// 加密货币

     /*
     *  1 : 开盘中
      * 2 : 未开盘
      * 3 : 交易中
      * 4 : 暂停交易
     *
     * */
  int OPENING=1;
 int CLOSING=2;
 int TRADING=3;
int SUSPEND_TRADING=4;


    int IOS=1;
   int ANDROID=2;
    int channelId=0123456;



    /*
    * MinuteKey[1] = "1m";
            MinuteKey[5] = "5m";
            MinuteKey[15] = "15m";
            MinuteKey[30] = "30m";
            MinuteKey[60] = "1h";
            MinuteKey[120] = "2h";
            MinuteKey[240] = "4h";
            MinuteKey[360] = "6h";
            MinuteKey[480] = "8h";
            MinuteKey[720] = "12h";

            DayKey[1] = "1d";
            WeekKey[1] = "1w";
    *
    *
    *
    * */
    String ONE_MIN="1m";
    String THREE_MIN="3m";
     String FIVE_MIN="5m";
     String FIFTEEN_MIN="15m";
    String THIRTY_MIN="30m";



   String FROM_PAYORDER_K_MOKEMORE="from_payorder_k_mokemore";
   String FROM_CHICANG_UNLOGIN="from_chicang_unlogin";
   String  FROM_K_BUTTON_PAYORDERMORE="from_k_button_payordermore"; //点击下单按钮做多 跳转到持仓界面


    int BUY_RISE=1;//买涨
    int BUY_DROP=2;//买跌；


     String DOLLAR="美元";
    String  EURO="欧元";
    String  HONGKONG_DOLLAR="港币";
    String RMB="人民币";


    //订单类型（限价委托 = 1、市价委托 = 2）
      int LIMIT_PRICE=1;
     int  MARKET_PRICE = 2;

     String  UP="买涨做多";
     String   DROP="买跌做空";

    int PAGESIZE=15;
}
