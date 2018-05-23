package com.mex.GalaxyChain.net.repo;

import com.mex.GalaxyChain.bean.HistoryKLineBean;
import com.mex.GalaxyChain.bean.HoldPositionBean;
import com.mex.GalaxyChain.bean.LoginOutBean;
import com.mex.GalaxyChain.bean.MoneyFlowBean;
import com.mex.GalaxyChain.bean.OrderBuyBean;
import com.mex.GalaxyChain.bean.PayOutMeneyBean;
import com.mex.GalaxyChain.bean.PostCloseOrderBean;
import com.mex.GalaxyChain.bean.PostLoginBean;
import com.mex.GalaxyChain.bean.PostOrderBean;
import com.mex.GalaxyChain.bean.RealNameAuthBean;
import com.mex.GalaxyChain.bean.ResetPWBean;
import com.mex.GalaxyChain.bean.SymbolBean;
import com.mex.GalaxyChain.bean.TradeDetailBean;
import com.mex.GalaxyChain.bean.UserAccountInfoBean;
import com.mex.GalaxyChain.bean.VerifycodeBean;
import com.mex.GalaxyChain.bean.requestbean.GoodsPriceBean;
import com.mex.GalaxyChain.net.BaseRepo;
import com.mex.GalaxyChain.net.bean.galaxychainbean.LoginBean;
import com.mex.GalaxyChain.net.bean.galaxychainbean.RegistBean;
import com.mex.GalaxyChain.net.service.IUserService;

import java.util.HashMap;

import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LSJ on 18/3/6.
 */

public class UserRepo extends BaseRepo<IUserService> {
	private static UserRepo instance;

	private UserRepo() {
	}

	public static UserRepo getInstance() {
		if (instance == null) {
			instance = new UserRepo();
		}
		return instance;
	}



	/*public Observable<ReponseData<BaseBean>> getCode(String country,
                                                     String mobilePhone,
                                                     String otype,
                                                     String time,
                                                     String sign) {

		return getService().getCode(country,mobilePhone,otype,time,sign)
				.flatMap(new HttpFunc<BaseBean>())
				.compose(new UICompose<ReponseData<BaseBean>>());
	}*/

    public Observable<VerifycodeBean> getCode(String country,
                                              String mobilePhone,
                                              String otype,
                                              String time,
                                              String sign) {

        return getService().getCode(country,mobilePhone,otype,time,sign)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread());

    }




   /* public Observable<> getCode(String country,
                                                     String mobilePhone,
                                                     String otype,
                                                     String time,
                                                     String sign) {

        return getService().getCode(country,mobilePhone,otype,time,sign);


    }
*/


    //忘记密码
    /*public Observable<ReponseData<BaseBean>>  resetPhonePW(String phoneNumber,
                                                           String country,
                                                           String et_verify_passwordString,
                                                           String confirm_newPW,
                                                           String timeStamp,
                                                           String sign) {
           return getService().getReSetPW(phoneNumber,country,et_verify_passwordString,confirm_newPW,timeStamp,sign)
                      .flatMap(new HttpFunc<BaseBean>())
                      .compose(new UICompose<ReponseData<BaseBean>>());


    }*/

    public Observable<ResetPWBean>  resetPhonePW(String phoneNumber,
                                                 String country,
                                                 String et_verify_passwordString,
                                                 String confirm_newPW,
                                                 String timeStamp,
                                                 String sign) {
        return getService().getReSetPW(phoneNumber,country,et_verify_passwordString,confirm_newPW,timeStamp,sign)
                  .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread());



    }




 //T=RegistBean={“token”:"xxxxxxxxxxxxxxxxxxxxxxx","expire":"2018-09-02 23:22:22"}
   /* public Observable<ReponseData<RegistBean>>  getRegistCode(String country,
                                                              String mobilePhone,
                                                              String et_verify_passwordString,
                                                              String et_phone_passwordString,
                                                              String timeStamp,
                                                              String sign) {

	    return getService().register(country,mobilePhone,et_verify_passwordString,
                            et_phone_passwordString,timeStamp,sign)
                             .flatMap(new HttpFunc<RegistBean>())
                             .compose(new UICompose<ReponseData<RegistBean>>());

    }*/

    public Observable<RegistBean>  getRegistCode(String country,
                                                              String mobilePhone,
                                                              String et_verify_passwordString,
                                                              String et_phone_passwordString,
                                                              String timeStamp,

                                                              String sign) {

        return getService().register(country,mobilePhone,et_verify_passwordString,
                et_phone_passwordString,timeStamp,sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());




    }



    //登陆
    /*public Observable<ReponseData<LoginBean>>  getLoginCode(String country,
                                                            String mobilePhone,
                                                           String et_phone_passwordString,
                                                           String timeStamp,
                                                           String sign) {
	    return getService().login(country,mobilePhone,et_phone_passwordString,timeStamp,sign)
                           .flatMap(new HttpFunc<LoginBean>())
                           .compose(new UICompose<ReponseData<LoginBean>>());
    }*/

     public Observable<LoginBean>  getLoginCode(String country,
                                                             String mobilePhone,
                                                             String et_phone_passwordString,
                                                             String timeStamp,
                                                             String sign) {
        return getService().login(country,mobilePhone,et_phone_passwordString,timeStamp,sign)
                      .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread());
        }







    /*public Observable<ReponseData<SymbolBean>> getSymbols(Map<String, Object> params) {
	      return getService().symbols(params)
                .flatMap(new HttpFunc<SymbolBean>())
                .compose(new UICompose<ReponseData<SymbolBean>>());
    }*/

     public Observable<SymbolBean> getSymbols(RequestBody requestBody ) {
	     return getService().symbols(requestBody)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread());


    }

    /*public Observable<ReponseData<PostLoginBean>> RequestPostLogin(RequestBody requestBody) {
        return  getService().postLogin(requestBody)
                .flatMap(new HttpFunc<PostLoginBean>())
                .compose(new UICompose<ReponseData<PostLoginBean>>());

    }*/


    //王皓登陆
    public Observable<PostLoginBean> RequestPostLogin(RequestBody requestBody) {
        Observable< PostLoginBean> postLoginBeanObservable= getService().postLogin(requestBody)
                               .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
        return  postLoginBeanObservable;



    }



   /* public Observable<ReponseData<GoodsPriceBean2>>  getGoodsPrice(RequestBody requestBody) {

         return getService().goodsPrice(requestBody)
                 .flatMap(new HttpFunc<GoodsPriceBean2>())
                 .compose(new UICompose<ReponseData<GoodsPriceBean2>>());

    }*/

    public Observable<GoodsPriceBean>  getGoodsPrice(RequestBody requestBody) {

        return getService().goodsPrice(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


    }

    public Observable<HoldPositionBean>  PostHoldPosition(RequestBody requestBody) {
        return getService().holdPosition(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<LoginOutBean> getPostLoginOut(RequestBody requestBody) {
        return getService().loginout(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    public Observable<RealNameAuthBean> checkIDNum(RequestBody requestBody) {
        return getService().CheckRealNameAuth(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

                // RealNameAuthBean
    }



    public Observable<OrderBuyBean> GetBuyPage(RequestBody requestBody) {
         return getService().orderBuyPage(requestBody)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread());
          //OrderBuyBean

    }

    public Observable<PostOrderBean> PostOrderMore(RequestBody requestBody) {
        return getService().postOrder(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        //PostOrderBean
    }

    public Observable<PostCloseOrderBean> postCloseOrder(RequestBody requestBody) {
        return getService().PostCloseOrder(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        //PostCloseOrderBean
    }

    public Observable<TradeDetailBean> PostTradeDetail(RequestBody requestBody) {
        return getService().tradeDetail(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

                // TradeDetailBean

        }

    public Observable<PayOutMeneyBean> postPayOutMeney(RequestBody requestBody) {
        return getService().payOutMoney(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());



        //PayOutMeneyBean
    }









    public  Observable<HistoryKLineBean>  getHistoryKLine(HashMap<String, Object> paramMap ) {
        return getService().getHistoryKLine(paramMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        }


    public Observable<UserAccountInfoBean> getUserAccountInfo(HashMap<String, Object> paramMap) {
        return getService().userAccountInfo(paramMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        // UserAccountInfoBean

    }


    public Observable<MoneyFlowBean> getMoneyFlow(HashMap<String, Object> paramMap) {

        return getService().MoneyFlow(paramMap)
                 .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

                // MoneyFlowBean

    }



}
