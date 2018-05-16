package com.mex.GalaxyChain.net;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LSJ on 17/4/30.
 */
public class UICompose<T> implements Observable.Transformer<T, T> {
	@Override
	public Observable<T> call(Observable<T> observable) {
		return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
	}
}
