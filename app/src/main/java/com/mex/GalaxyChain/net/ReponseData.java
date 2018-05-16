package com.mex.GalaxyChain.net;

import android.text.TextUtils;

import com.google.gson.JsonObject;

/**
 * Created by LSJ on 17/3/25.
 */

public class ReponseData<T> {

    //{"code":"100004","msg":null,"data":""}
    //{“code”:0,"msg":"suc","data":“”}
	private int code;
	private String msg;
	 private T result;

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

	 public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return code == 1;
	}

	public static String getParseErrObj(String info) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", ApiException.PARSE_ERROR);
		jsonObject.addProperty("msg", TextUtils.isEmpty(info) ? "数据解析异常" : info);
	 	jsonObject.add("result", new JsonObject());
		return jsonObject.toString();
	}
}
