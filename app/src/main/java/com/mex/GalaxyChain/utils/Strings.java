package com.mex.GalaxyChain.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by albert on 11/24/14.
 */
public class Strings {
	public static String[] parseJsonList(String msg) {
		try {
			ArrayParse arrayParse = parseJsonList(msg, ArrayParse.class);
			if (arrayParse != null) return arrayParse.key;
		} catch (Exception e) {
		}
		return null;
	}

	public static String[][] parseJsonList2ArrayArray(String msg) {
		try {
			ArrayArrayParse arrayParse = parseJsonList(msg, ArrayArrayParse.class);
			if (arrayParse != null) {
				return arrayParse.getKey();
			}
		} catch (Exception e) {
		}
		return null;
	}

	private static class ArrayArrayParse implements Serializable {
		private String[][] key;

		public String[][] getKey() {
			return key;
		}

		public void setKey(String[][] key) {
			this.key = key;
		}
	}

	private static class ArrayParse implements Serializable {
		private String[] key;

		public String[] getKey() {
			return key;
		}

		public void setKey(String[] key) {
			this.key = key;
		}
	}

	public static <T> T parseJsonList(String arrayString, Class<T> classOfT) {
		if (TextUtils.isEmpty(arrayString)
				|| arrayString.length() < 3
				|| !arrayString.startsWith("[") || !arrayString.endsWith("]")) {
			return null;
		}
		T arrayParse = new Gson().fromJson("{" + "key:" + arrayString + "}", classOfT);
		return arrayParse;
	}

	public static String toMD5(String source) {
		if (null == source || "".equals(source)) return null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(source.getBytes());
			return toHex(digest.digest());
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static String toHex(byte[] buf) {
		if (buf == null) return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private final static String HEX = "0123456789abcdef";

	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}

	public static String hmacDigest(String msg, String keyString, String algo) {
		String digest = null;
		try {
			SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
			Mac mac = Mac.getInstance(algo);
			mac.init(key);

			byte[] bytes = mac.doFinal(msg.getBytes("UTF-8"));

			StringBuilder hash = new StringBuilder();
			for (byte aByte : bytes) {
				String hex = Integer.toHexString(0xFF & aByte);
				if (hex.length() == 1) {
					hash.append('0');
				}
				hash.append(hex);
			}
			digest = hash.toString();
		} catch (UnsupportedEncodingException ignored) {
		} catch (InvalidKeyException ignored) {
		} catch (NoSuchAlgorithmException ignored) {
		}
		return digest;
	}

	public static String doubleTrans(double d) {
		DecimalFormat df = new DecimalFormat("0.##");//最多保留几位小数，就用几个#，最少位就用0来确定
		return df.format(d);
	}

	public static String doubleTrans(double d, int maxDigits) {
		//第二种方法:
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(maxDigits);  //最大小数位数
		return numberFormat.format(d);
	}

	public static boolean isNullOrEmpty(String string) {
		return string == null || string.length() == 0 || "null".equals(string.toLowerCase().trim());
	}

	public static String emptyToNull(String string) {
		return isNullOrEmpty(string) ? null : string;
	}

	public static String nullToEmpty(String string) {
		return (string == null) ? "" : string;
	}
}
