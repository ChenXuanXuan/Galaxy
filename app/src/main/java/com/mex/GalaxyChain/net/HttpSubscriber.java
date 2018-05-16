package com.mex.GalaxyChain.net;

import android.os.Looper;
import android.text.TextUtils;

import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.utils.CommonUtil;
import com.mex.GalaxyChain.utils.NetWorkUtils;
import com.mex.GalaxyChain.utils.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import rx.Subscriber;

/**
 * Created by LSJ on 17/4/30.
 */

public abstract class HttpSubscriber<T> extends Subscriber<T> {
	private static final int TIME_OUT = 301;
	private static final int UNAUTHORIZED = 401;
	private static final int FORBIDDEN = 403;
	private static final int NOT_FOUND = 404;
	private static final int REQUEST_TIMEOUT = 408;
	private static final int INTERNAL_SERVER_ERROR = 500;
	private static final int BAD_GATEWAY = 502;
	private static final int SERVICE_UNAVAILABLE = 503;
	private static final int GATEWAY_TIMEOUT = 504;
	private static final int VERSION_UPDATE = 1004;

	private static final String networkMsg = MyApplication.appContext.getResources().getString(R.string.net_exception);
	private static final String parseMsg = MyApplication.appContext.getResources().getString(R.string.net_data_exception);
	private static final String unknownMsg = MyApplication.appContext.getResources().getString(R.string.net_unknow_exception);
	private static final String connectMsg = MyApplication.appContext.getResources().getString(R.string.net_conneciton_exception);

	@Override
	public void onStart() {

	}

	@Override
	public void onCompleted() {
		onFinally();
	}

	private void onFinally() {

	}

	@Override
	public void onError(Throwable e) {

		Throwable throwable = e;
		//获取最根源的异常
		while (throwable.getCause() != null) {
			e = throwable;
			throwable = throwable.getCause();
		}
		ApiException ex;
		if (e instanceof HttpException) {
			//HTTP错误
			HttpException httpException = (HttpException) e;
			ex = new ApiException(e, httpException.code());
			switch (httpException.code()) {
				case UNAUTHORIZED:
				case FORBIDDEN:
					onPermissionError(ex);
					//权限错误，需要实现
					break;
				case NOT_FOUND:
				case REQUEST_TIMEOUT:
				case GATEWAY_TIMEOUT:
				case INTERNAL_SERVER_ERROR:
				case BAD_GATEWAY:
				case SERVICE_UNAVAILABLE:
				default:
					ex.setDisplayMessage(networkMsg);
					//均视为网络错误
					handleErrMsg(ex);
					break;
			}
		}/* else if (e instanceof JsonParseException
				//|| e instanceof JSONException
				|| e instanceof ParseException
				|| e instanceof com.google.gson.stream.MalformedJsonException) {
			ex = new ApiException(e, ApiException.PARSE_ERROR);
			ex.setDisplayMessage(parseMsg);
			handleErrMsg(ex);
		} */else if (e instanceof ConnectException) {
			ex = new ApiException(e, NOT_FOUND);
			ex.setDisplayMessage(connectMsg);  //均视为网络错误
			handleErrMsg(ex);
		} else if (e instanceof ApiException) {
			handleErrMsg(((ApiException) e));
		} else if (e instanceof SocketTimeoutException) {
			ex = new ApiException(e, TIME_OUT);
			ex.setDisplayMessage(MyApplication.appContext.getResources().getString(R.string.tab_mine_timeout));  //均视为网络错误
			handleErrMsg(ex);
		} else {
			ex = new ApiException(e, ApiException.UNKNOWN);
			if (!NetWorkUtils.isNetworkAvailable(MyApplication.appContext)) {
				ToastUtils.showErrorImage(MyApplication.appContext.getResources().getString(R.string.net_conneciton_exception));
				onFailure(ex);
				return;
			}
			ex.setDisplayMessage(unknownMsg + e.getMessage());
			//未知错误
			handleUnKnowErrMsg(ex);
		}

		onFinally();
	}

	@Override
	public void onNext(T t) {
		onSuccess(t);
	}

	protected abstract void onSuccess(T repoData);

	private void onPermissionError(ApiException ex) {

	}

	private void handleErrMsg(ApiException ex) {
		if (checkMainThread() && ex.getCode() != ApiException.PARSE_ERROR
				&& ex.getCode() != ApiException.PARSE_GOOGLECHECK) {
			if (ex.getCode() == ApiException.PARSE_HMAC) {
			}
			showErrorMsg(ex.getDisplayMessage());
		}
		onFailure(ex);
	}

	protected abstract void onFailure(ApiException e);

	private void handleUnKnowErrMsg(ApiException ex) {
		if (checkMainThread() && ex.getCode() != ApiException.PARSE_ERROR
				&& ex.getCode() != ApiException.PARSE_GOOGLECHECK) {
			if (ex.getCode() == ApiException.PARSE_HMAC) {
			}
		}
		onFailure(ex);
	}

	private boolean checkMainThread() {
		return Thread.currentThread() == Looper.getMainLooper().getThread();
	}

	protected void showErrorMsg(String msg) {
		if (TextUtils.isEmpty(msg)) return;
		CommonUtil.showSimpleInfo(msg);
	}
}
