package com.mex.GalaxyChain.net;

import com.mex.GalaxyChain.net.bean.BaseBean;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by LSJ on 17/3/25.
 */

public class HttpFunc<T extends BaseBean> implements Func1<ReponseData<T>, Observable<ReponseData<T>>> {

    @Override
    public Observable<ReponseData<T>> call(final ReponseData<T> reponseData) {
        return Observable.create(new Observable.OnSubscribe<ReponseData<T>>() {

            @Override
            public void call(Subscriber<? super ReponseData<T>> subscriber) {
                if (reponseData != null && (reponseData.getCode() == UrlTools.NET_SUCCESS||reponseData.getCode() == 200)) {
                    subscriber.onNext(reponseData);
                    subscriber.onCompleted();
                }else {
                    subscriber.onError(new ApiException(reponseData.getCode(), reponseData.getMsg()));
                }
            }
        });
    }
}
