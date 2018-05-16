package com.mex.GalaxyChain.net;

/**
 * Created by LSJ on 17/7/31.
 */
public class ApiException extends Exception {
	public static final int UNKNOWN = 1000;
	public static final int PARSE_ERROR = 1001;
	public static final int PARSE_HMAC = 1002;
	public static final int PARSE_GOOGLECHECK = 1003;
	private static final long serialVersionUID = 1138061954075999547L;
	private final int code;
	private String displayMessage;

	public ApiException(Throwable throwable, int code) {
		super(throwable);
		this.code = code;
	}

	public ApiException(int code, String msg) {
		this.code = code;
		this.displayMessage = msg;
	}


	public int getCode() {
		return code;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String msg) {
		this.displayMessage = msg;
	}

}