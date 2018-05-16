package com.mex.GalaxyChain.mychart.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by fangzhu on 2015/2/6.
 * 格式数字  包括价格  成交额等
 */
public class NumberUtil {
	public static final int DATA_INT = 0;
	public static final int DATA_FLOAT = 1;
	public static final int DATA_DOUBLE = 2;
	public static final int DATA_DEFAULT = -1;
	public static final int DATA_BE_EQUAL_TO = 0;
	public static final int DATA_LESS_THAN = 1;
	public static final int DATA_GREATER_THAN = 2;

	/**
	 * 把所有的double类型的小数保留两位有效数字
	 * 大于1000的不显示小数也不四舍五入
	 */
//    public static String formartDouble(double targer) {
//        //modify by fangzhu
//        try {
//            if(targer >= 1000) {
//                return (int)targer + "";//不要四舍五入
//            }else{
//                //格式化两位小数
//                BigDecimal big = new BigDecimal(targer);
//                double res = big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//                DecimalFormat df   = new DecimalFormat("######0.00");
//                return df.format(res);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//            //原样返回
//            return "" + targer ;
//        }
//    }

	/**
	 * 格式化数字 默认两位小数点
	 *
	 * @param d
	 * @return
	 */
	public static String beautifulDouble(double d) {
		return beautifulDouble(d, 2);
	}

	/**
	 * 格式化数字
	 *
	 * @param d
	 * @param scale 小数点位数
	 * @return
	 */
	public static String beautifulDouble(double d, int scale) {
		String str = String.valueOf(d);
		try {
			BigDecimal big = new BigDecimal(d);
			double res = big.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
			return String.format("%." + scale + "f", res);
//            DecimalFormat df = new DecimalFormat("######0.000");
//            return df.format(res);
//            return res + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d + "";
	}

	public static String beautifulDouble(String d) {
		return beautifulDouble(Double.parseDouble(d), 2);
	}

	/**
	 * 格式化数字
	 *
	 * @param d     数字的str
	 * @param scale 小数点位数
	 * @return
	 */
	public static String beautifulDouble(String d, int scale) {
		try {
			if (d == null || "null".equalsIgnoreCase(d)) {
//                return "";
				d = "0";
			}

			d = d.replace("%", "");
			d = d.replace("--", "");
			Double dd = Double.parseDouble(d);
			return beautifulDouble(dd, scale);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 金额格式化
	 *
	 * @param moneyStr 金额
	 * @param scale    小数点位数
	 * @return
	 */
	public static String formatNumberStr(String moneyStr, int scale) {
		if (TextUtils.isEmpty(moneyStr)) {
			return "";
		}
		double money = 0;
		try {
			money = Float.valueOf(moneyStr);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return beautifulDouble(money, scale);
	}

	/**
	 * 根据double  获取 万  亿  万亿
	 *
	 * @param d
	 * @return
	 */
	public static String getMoneyString(double d) {

		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		double wan = 10000;
		double fwan = -10000;
		double yi = 100000000; //  一亿以下
		double fyi = -100000000; //  一亿以下
		double wyi = 1000000000000d; //  万亿以下
		double fwyi = -1000000000000d; //  万亿以下

		if (d < wan && d > fwan) {
			return df.format(d);
		} else if (d < yi && d > fyi) {
			return df.format(d / Math.pow(10, 4)) + "万";
		} else if (d < wyi && d > fwyi) {
			return df.format(d / Math.pow(10, 8)) + "亿";
		} else {
			return df.format(d / Math.pow(10, 12)) + "万亿";
		}

	}

	public static String getMaxPriceString(double d) {
		DecimalFormat df;
		df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		return df.format(d + 0.01);
	}

	public static String getMinPriceString(double d) {
		DecimalFormat df;
		df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		return df.format(d - 0.01);
	}

	public static String getCountString(double d) {

		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		double wan = 10000;
		double fwan = -10000;
		double yi = 100000000; //  一亿以下
		double fyi = -100000000; //  一亿以下
		double wyi = 1000000000000d; //  万亿以下
		double fwyi = -1000000000000d; //  万亿以下

		if (d < wan && d > fwan) {
			return String.valueOf((int) d);
		} else if (d < yi && d > fyi) {
			return df.format(d / Math.pow(10, 4)) + "万";
		} else if (d < wyi && d > fwyi) {
			return df.format(d / Math.pow(10, 8)) + "亿";
		} else {
			return df.format(d / Math.pow(10, 12)) + "万亿";
		}

	}

	/**
	 * 成交量  成交额
	 * 根据double  获取 万  亿  万亿
	 *
	 * @param d
	 * @return
	 */
	public static String formartBigNumber(double d) {
		//万以下 直接返回
		if (d < 10000)
			return beautifulDouble(d, 2);

		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);

		double wan = Math.pow(10, 8); //  一亿以下
		double yi = Math.pow(10, 12); //  万亿以下

		if (d < wan) {
			return df.format(d / Math.pow(10, 4)) + "万";
		} else if (d < yi) {
			return df.format(d / Math.pow(10, 8)) + "亿";
		} else {
			return df.format(d / Math.pow(10, 12)) + "万亿";
		}

	}

	public static String getPercentString(double d) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		return (Double.parseDouble(df.format(d * 100)) > (d * 100) ? df.format(d * 100) : df.format((d + 0.0001) * 100)) + "%";
	}

	public static String getPercentStringThree(double d) {
		DecimalFormat df = new DecimalFormat("0.000");
		df.setMaximumFractionDigits(3);
		return df.format(d * 100) + "%";
	}

	public static String getKeepTwoString(double d) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		return df.format(d);
	}

	public static String getAdd(String firstValue, String secondValue) {
		BigDecimal first = new BigDecimal(firstValue);
		BigDecimal second = new BigDecimal(secondValue);
		first = first.add(second);
		return first.toString();
	}


	public static double convertNumberString(String valueStr) {
		double number = 0;
		if (!TextUtils.isEmpty(valueStr)) {
			//匹配是否可转换
			if (valueStr.matches("-?(0|[1-9]\\d*)(\\.\\d+)?")) {
				number = Double.valueOf(valueStr);
			}
		}
		return number;
	}

	/**
	 * 整型转化string封装
	 *
	 * @param value
	 * @return
	 */
	public static String integerToString(int value) {
		return String.valueOf(value);
	}

	/**
	 * 格式化小数点
	 *
	 * @param targer
	 * @return
	 */
	public static double doubleDecimal(double targer) {
		try {
			int count = 2;

			if (targer < 10) {
				count = 4;
			} else if (targer < 100) {
				count = 3;
			}
			BigDecimal bigDecimal = new BigDecimal(targer);
			return bigDecimal.setScale(count, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}

	/**
	 * 比较大小
	 */
	public static int compareBigAndSmall(String firstValue, String secondValue, int dataType) {
		switch (dataType) {
			case DATA_INT:
				if (Integer.parseInt(firstValue) < Integer.parseInt(secondValue)) {
					return DATA_LESS_THAN;
				}
				if (Integer.parseInt(firstValue) < Integer.parseInt(secondValue)) {
					return DATA_GREATER_THAN;
				}
				if (Integer.parseInt(firstValue) == Integer.parseInt(secondValue)) {
					return DATA_BE_EQUAL_TO;
				}
				break;
			case DATA_FLOAT:
				if (Float.parseFloat(firstValue) < Float.parseFloat(secondValue)) {
					return DATA_LESS_THAN;
				}
				if (Float.parseFloat(firstValue) < Float.parseFloat(secondValue)) {
					return DATA_GREATER_THAN;
				}
				if (Float.parseFloat(firstValue) == Float.parseFloat(secondValue)) {
					return DATA_BE_EQUAL_TO;
				}
				break;
			case DATA_DOUBLE:
				if (Double.parseDouble(firstValue) < Double.parseDouble(secondValue)) {
					return DATA_LESS_THAN;
				}
				if (Double.parseDouble(firstValue) < Double.parseDouble(secondValue)) {
					return DATA_GREATER_THAN;
				}
				if (Double.parseDouble(firstValue) == Double.parseDouble(secondValue)) {
					return DATA_BE_EQUAL_TO;
				}
				break;
		}
		return DATA_DEFAULT;
	}

	public static int compareBigAndSmall(float firstValue, float secondValue) {
		if (firstValue < secondValue) {
			return DATA_LESS_THAN;
		}
		if (firstValue < secondValue) {
			return DATA_GREATER_THAN;
		}
		if (firstValue == secondValue) {
			return DATA_BE_EQUAL_TO;
		}
		return DATA_DEFAULT;
	}

	public static int compareBigAndSmall(int firstValue, int secondValue) {
		if (firstValue < secondValue) {
			return DATA_LESS_THAN;
		}
		if (firstValue < secondValue) {
			return DATA_GREATER_THAN;
		}
		if (firstValue == secondValue) {
			return DATA_BE_EQUAL_TO;
		}
		return DATA_DEFAULT;
	}

	public static int compareBigAndSmall(double firstValue, double secondValue) {
		if (firstValue < secondValue) {
			return DATA_LESS_THAN;
		}
		if (firstValue < secondValue) {
			return DATA_GREATER_THAN;
		}
		if (firstValue == secondValue) {
			return DATA_BE_EQUAL_TO;
		}
		return DATA_DEFAULT;
	}
}
