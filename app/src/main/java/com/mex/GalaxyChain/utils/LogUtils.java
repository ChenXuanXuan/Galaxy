package com.mex.GalaxyChain.utils;

import android.util.Log;


public class LogUtils {
	/**
	 * 日志输出级别NONE
	 */
	public static final int LEVEL_NONE = 0;
	/**
	 * 日志输出级别E
	 */
	public static final int LEVEL_ERROR = 1;
	/**
	 * 日志输出级别W
	 */
	public static final int LEVEL_WARN = 2;
	/**
	 * 日志输出级别I
	 */
	public static final int LEVEL_INFO = 3;
	/**
	 * 日志输出级别D
	 */
	public static final int LEVEL_DEBUG = 4;
	/**
	 * 日志输出级别V
	 */
	public static final int LEVEL_VERBOSE = 5;

	/**
	 * 日志输出时的TAG
	 */
	private static String mTag = "LogUtils";
	/**
	 * 是否允许输出log
	 */
	private static int mDebuggable = 100;
	//可以全局控制是否打印log日志
	private static boolean isPrintLog = true;
	private static int LOG_MAXLENGTH = 2000;

	/**
	 * 以级别为 d 的形式输出LOG
	 */
	public static void v(String msg) {
		if (mDebuggable >= LEVEL_VERBOSE) {
			Log.v(mTag, msg);
		}
	}

	/**
	 * 以级别为 d 的形式输出LOG
	 */
	public static void d(String msg) {
		if (mDebuggable >= LEVEL_DEBUG) {
			if (isPrintLog) {
				int strLength = msg.length();
				int start = 0;
				int end = LOG_MAXLENGTH;
				for (int i = 0; i < 100; i++) {
					if (strLength > end) {
						Log.d(mTag + i, msg.substring(start, end));
						start = end;
						end = end + LOG_MAXLENGTH;
					} else {
						Log.d(mTag + i, msg.substring(start, strLength));
						break;
					}
				}
			}

		}
	}

	/**
	 * 以级别为 i 的形式输出LOG
	 */
	public static void i(String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			Log.i(mTag, msg);
		}
	}

	/**
	 * 以级别为 w 的形式输出LOG
	 */
	public static void w(String msg) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, msg);
		}
	}

	/**
	 * 以级别为 w 的形式输出Throwable
	 */
	public static void w(Throwable tr) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, "", tr);
		}
	}

	/**
	 * 以级别为 w 的形式输出LOG信息和Throwable
	 */
	public static void w(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_WARN && null != msg) {
			Log.w(mTag, msg, tr);
		}
	}

	/**
	 * 以级别为 e 的形式输出LOG
	 */
	public static void e(String msg) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, msg);
		}
	}

	/**
	 * 以级别为 e 的形式输出Throwable
	 */
	public static void e(Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, "", tr);
		}
	}

	/**
	 * 以级别为 e 的形式输出LOG信息和Throwable
	 */
	public static void e(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR && null != msg) {
			Log.e(mTag, msg, tr);
		}
	}

	public static void v(String tagName, String msg) {
		if (isPrintLog) {
			int strLength = msg.length();
			int start = 0;
			int end = LOG_MAXLENGTH;
			for (int i = 0; i < 100; i++) {
				if (strLength > end) {
					Log.v(tagName + i, msg.substring(start, end));
					start = end;
					end = end + LOG_MAXLENGTH;
				} else {
					Log.v(tagName + i, msg.substring(start, strLength));
					break;
				}
			}
		}
	}

	public static void d(String tagName, String msg) {
		if (isPrintLog) {
			int strLength = msg.length();
			int start = 0;
			int end = LOG_MAXLENGTH;
			for (int i = 0; i < 100; i++) {
				if (strLength > end) {
					Log.d(tagName + i, msg.substring(start, end));
					start = end;
					end = end + LOG_MAXLENGTH;
				} else {
					Log.d(tagName + i, msg.substring(start, strLength));
					break;
				}
			}
		}
	}


	public static void i(String tagName, String msg) {
		if (isPrintLog) {
			int strLength = msg.length();
			int start = 0;
			int end = LOG_MAXLENGTH;
			for (int i = 0; i < 100; i++) {
				if (strLength > end) {
					Log.i(tagName + i, msg.substring(start, end));
					start = end;
					end = end + LOG_MAXLENGTH;
				} else {
					Log.i(tagName + i, msg.substring(start, strLength));
					break;
				}
			}
		}
	}


	public static void w(String tagName, String msg) {
		if (isPrintLog) {
			int strLength = msg.length();
			int start = 0;
			int end = LOG_MAXLENGTH;
			for (int i = 0; i < 100; i++) {
				if (strLength > end) {
					Log.w(tagName + i, msg.substring(start, end));
					start = end;
					end = end + LOG_MAXLENGTH;
				} else {
					Log.w(tagName + i, msg.substring(start, strLength));
					break;
				}
			}
		}
	}

	public static void e(String tagName, String msg) {
		if (isPrintLog) {
			int strLength = msg.length();
			int start = 0;
			int end = LOG_MAXLENGTH;
			for (int i = 0; i < 100; i++) {
				if (strLength > end) {
					Log.e(tagName + i, msg.substring(start, end));
					start = end;
					end = end + LOG_MAXLENGTH;
				} else {
					Log.e(tagName + i, msg.substring(start, strLength));
					break;
				}
			}
		}
	}
}
