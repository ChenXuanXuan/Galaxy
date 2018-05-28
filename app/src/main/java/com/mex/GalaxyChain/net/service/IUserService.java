package com.mex.GalaxyChain.net.service;


import com.mex.GalaxyChain.bean.HistoryKLineBean;
import com.mex.GalaxyChain.bean.HoldPositionBean;
import com.mex.GalaxyChain.bean.LoginOutBean;
import com.mex.GalaxyChain.bean.MoneyFlowBean;
import com.mex.GalaxyChain.bean.NewestKLineBean;
import com.mex.GalaxyChain.bean.OrderBuyBean;
import com.mex.GalaxyChain.bean.PayOutListBean;
import com.mex.GalaxyChain.bean.PayOutMeneyBean;
import com.mex.GalaxyChain.bean.PostCloseOrderBean;
import com.mex.GalaxyChain.bean.PostLoginBean;
import com.mex.GalaxyChain.bean.PostOrderBean;
import com.mex.GalaxyChain.bean.PostPayInBean;
import com.mex.GalaxyChain.bean.RealNameAuthBean;
import com.mex.GalaxyChain.bean.RealNameC1Bean;
import com.mex.GalaxyChain.bean.ResetPWBean;
import com.mex.GalaxyChain.bean.SymbolBean;
import com.mex.GalaxyChain.bean.TradeDetailBean;
import com.mex.GalaxyChain.bean.UserAccountInfoBean;
import com.mex.GalaxyChain.bean.UserMeBean;
import com.mex.GalaxyChain.bean.VerifycodeBean;
import com.mex.GalaxyChain.bean.requestbean.GoodsPriceBean;
import com.mex.GalaxyChain.net.NetFuncConstants;
import com.mex.GalaxyChain.net.UrlTools;
import com.mex.GalaxyChain.net.bean.galaxychainbean.LoginBean;
import com.mex.GalaxyChain.net.bean.galaxychainbean.RegistBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by lsj on 18/3/10.
 */

public interface IUserService {
     //注册 post  reg_mobile
	/*@FormUrlEncoded
	@POST(NetFuncConstants.POST_REGISTER)
	Observable<ReponseData<RegistBean>> register(@Field("country") String country,
                                                 @Field("mobile") String mobilePhone,
                                                 @Field("verifycode") String et_verify_passwordString,
                                                 @Field("password") String et_phone_passwordString,
                                                 @Field("time") String timeStamp,
                                                 @Field("sign") String sign);*/


    @FormUrlEncoded
    @POST(UrlTools.DEBUG_HOST+NetFuncConstants.POST_REGISTER)
    Observable<RegistBean> register(@Field("country") String country,
                                                 @Field("mobile") String mobilePhone,
                                                 @Field("verifycode") String et_verify_passwordString,
                                                 @Field("password") String et_phone_passwordString,
                                                 @Field("time") String timeStamp,

                                                 @Field("sign") String sign);



	 //登陆
   /* @FormUrlEncoded
    @POST(NetFuncConstants.POST_LOGIN)
    Observable<ReponseData<LoginBean>> login(@Field("country") String country,
                                             @Field("mobile") String mobilePhone,
                                             @Field("password") String et_phone_passwordString,
                                             @Field("time") String timeStamp,
                                             @Field("sign") String sign);*/

    @FormUrlEncoded
    @POST(UrlTools.DEBUG_HOST+NetFuncConstants.POST_LOGIN)
    Observable<LoginBean> login(@Field("country") String country,
                                 @Field("mobile") String mobilePhone,
                                 @Field("password") String et_phone_passwordString,
                                 @Field("time") String timeStamp,
                                 @Field("sign") String sign);



    //mex c1认证
    @FormUrlEncoded
    @POST(UrlTools.DEBUG_HOST+NetFuncConstants.POST_REALNAME_C1)
    Observable<RealNameC1Bean> realNameC1(@Field("country_code") String country_code,
                                          @Field("type") int id_type,
                                          @Field("number") String idNum,
                                          @Field("first_name") String xing,
                                          @Field("second_name") String ming,
                                          @Field("birthday") String birthday,
                                          @Field("begin") String idcard_statTime,
                                          @Field("end") String idcard_endTime,
                                          @Field("token") String token,
                                          @Field("time") String timeStamp,
                                          @Field("sign") String sign);




   //获取userid
  // @FormUrlEncoded
   @GET(UrlTools.DEBUG_HOST+NetFuncConstants.GET_USERID)
    Observable<UserMeBean>  userme(@Query("token") String token,
                                   @Query("time") String timeStamp,
                                   @Query("sign") String sign);







    //忘记密码
    /*@FormUrlEncoded
    @POST(NetFuncConstants.RESET_PHONE_LOGIN_PW)
    Observable<ReponseData<BaseBean>> getReSetPW(@Field("mobile") String phoneNumber,
                                                 @Field("country") String country,
                                                 @Field("verifycode") String et_verify_passwordString,
                                                 @Field("loginpwd") String confirm_newPW,
                                                 @Field("time") String timeStamp,
                                                 @Field("sign") String sign);*/

    @FormUrlEncoded
    @POST(UrlTools.DEBUG_HOST+NetFuncConstants.RESET_PHONE_LOGIN_PW)
    Observable<ResetPWBean> getReSetPW(@Field("mobile") String phoneNumber,
                                       @Field("country") String country,
                                       @Field("verifycode") String et_verify_passwordString,
                                       @Field("loginpwd") String confirm_newPW,
                                       @Field("time") String timeStamp,
                                       @Field("sign") String sign);



	@GET(UrlTools.DEBUG_HOST+NetFuncConstants.GET_CODE)
	Observable<VerifycodeBean> getCode(@Query("country") String country,
                                                    @Query("mobile") String login,
                                                    @Query("otype") String otype,
                                                    @Query("time") String time,
                                                    @Query("sign") String sign);



    @POST(NetFuncConstants.POST_SYMBOL) //POST_SYMBOL = "http://39.105.28.205:7000/api/Symbol/GetDescription/";
    Observable<SymbolBean> symbols(@Body RequestBody requestBody );



  //  @POST(NetFuncConstants.POST_GOODSPRICE)
    //Observable<ReponseData<GoodsPriceBean2>> goodsPrice(@Body RequestBody requestBody);

    @POST(NetFuncConstants.POST_GOODSPRICE)
    Observable<GoodsPriceBean> goodsPrice(@Body RequestBody requestBody);


  //  @POST(NetFuncConstants.POST_USERINFO_LOGIN)
  //  Observable<ReponseData<PostLoginBean>> postLogin(@Body RequestBody requestBody);


     @POST(NetFuncConstants.POST_USERINFO_LOGIN)
       Observable<PostLoginBean> postLogin(@Body RequestBody requestBody);

    @POST(NetFuncConstants.POST_HOLD_POSITION) //持仓列表
     Observable<HoldPositionBean>  holdPosition(@Body RequestBody requestBody);


     @POST(NetFuncConstants.POST_LOGIN_OUT)
     Observable<LoginOutBean> loginout(@Body RequestBody requestBody);

    //获取历史K线数据
     @GET(NetFuncConstants.GET_HISTORY_KLIINE)
     Observable<HistoryKLineBean> getHistoryKLine(@QueryMap Map<String, Object> paramMap);

   //获取最新一条K线数据
    @GET(NetFuncConstants.GET_NEWEST_KLIINE)
    Observable<NewestKLineBean> getNewestKData(@QueryMap Map<String, Object> paramMap);

    //获取中户账号信息
    @GET(NetFuncConstants.GET_USER_ACCOUNTINFO)
     Observable<UserAccountInfoBean> userAccountInfo(@QueryMap HashMap<String, Object> paramMap);

 //资金明细
 @GET(NetFuncConstants.GET_ACCOUNT_MONEYFLOW_LIST)
    Observable<MoneyFlowBean>  MoneyFlow(@QueryMap HashMap<String, Object> paramMap);

  //提现记录
  @GET(NetFuncConstants.GET_ACCOUNT_PAYOUT_LIST)
    Observable<PayOutListBean> PayOutList(@QueryMap HashMap<String, Object> paramMap);


    @POST(NetFuncConstants.GET_ORDER_BUYPAGE)
     Observable<OrderBuyBean> orderBuyPage(@Body RequestBody requestBody);

    @POST(NetFuncConstants.POST_ORDER_MORE)
    Observable<PostOrderBean> postOrder(@Body RequestBody requestBody);

    @POST(NetFuncConstants.POST_CLOSE_ORDER)
    Observable<PostCloseOrderBean> PostCloseOrder(@Body RequestBody requestBody);

    @POST(NetFuncConstants.POST_TRADE_DETAIL_LIST)
    Observable<TradeDetailBean> tradeDetail(@Body RequestBody requestBody);

    @POST(NetFuncConstants.POST_REALNAME_AUTH)
    Observable<RealNameAuthBean>  CheckRealNameAuth(@Body RequestBody requestBody);

    @POST(NetFuncConstants.POST_PAYOUT_MONEY)//出金
    Observable<PayOutMeneyBean> payOutMoney(@Body RequestBody requestBody);

    @POST(NetFuncConstants.POST_PAYIN_MONEY) //入金
    Observable<PostPayInBean> PostPayInMoney(@Body RequestBody requestBody);



}
