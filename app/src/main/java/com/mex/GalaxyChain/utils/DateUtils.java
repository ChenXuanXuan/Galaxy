package com.mex.GalaxyChain.utils;

import android.annotation.SuppressLint;

import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by lenote on 2016/1/11.
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {

	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String YMDHM = "yyyy-MM-dd HH:mm";
	public static final String YMDHM_2_LINE = "yyyy-MM-dd\nHH:mm";
	public static final String YMD = "yyyy-MM-dd";
	public static final String MD = "MM-dd";
	public static final String HM = "HH:mm";
	public static final String HMS = "HH:mm:ss";
	public static final String MDHM = "MM-dd HH:mm";
	public static final long DAY_MILLIONS = 24 * 60 * 60 * 1000;

	/**
	 * 将Date转换成对应的时间格式字符串
	 *
	 * @param date
	 * @param model
	 * @return
	 */
	public static String date2String(Date date, String model) {
		String result = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(model);
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将时间字符串 转化成想要的时间字符串
	 *
	 * @param value
	 * @param inModel
	 * @param outModel
	 * @return
	 */
	public static String date2String(String value, String inModel, String outModel) {
		if (value == null || value.equals("")) {
			return "";
		}
		SimpleDateFormat in = new SimpleDateFormat(inModel);
		SimpleDateFormat out = new SimpleDateFormat(outModel);
		try {
			return out.format((in.parse(value)));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 将时间字符串 转化成想要的时间字符串
	 *
	 * @param value
	 * @param inModel
	 * @return
	 */
	public static Date string2Data(String value, String inModel) {
		Date date = string2DataErrNull(value, inModel);
		if (date == null) return new Date();
		return date;
	}

	public static Date string2DataErrNull(String value, String inModel) {
		if (value == null || value.equals("")) {
			return null;
		}
		value = value.replace("\n", "");
		SimpleDateFormat in = new SimpleDateFormat(inModel);
		try {
			return in.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 根据出生日期计算年龄
	 *
	 * @return
	 */
	public static int getAge(Date birthDay) {
		if (birthDay == null) {
			return 0;
		}

		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) {
			return 0;
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}

		if (age <= 0)
			age = 1;
		return age;
	}

	/***
	 * 返回星期几
	 *
	 * @param date
	 * @return
	 */
	public static String getWeekNum(Date date) {
		String wStr = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int type = cal.get(Calendar.DAY_OF_WEEK);

			switch (type) {
				case 1:
					wStr = "星期天";
					break;
				case 2:
					wStr = "星期一";
					break;
				case 3:
					wStr = "星期二";
					break;
				case 4:
					wStr = "星期三";
					break;
				case 5:
					wStr = "星期四";
					break;
				case 6:
					wStr = "星期五";
					break;
				case 7:
					wStr = "星期六";
					break;

				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wStr;
	}

	public static String getTimeString(long intime) {
		Date date = new Date(intime);
		return date2String(date, HMS);
	}

	public static String getHTimetringByFormat(long intime, String format) {
		Date date = new Date(intime);
		return date2String(date, format);
	}

	public static String getDateTimeString(long intime) {
		Date date = new Date(intime);
		return date2String(date, YMDHMS);
	}

	/**
	 * 时间戳转换成时间
	 */
	public static String getDateToString(long intime) {
		SimpleDateFormat format = new SimpleDateFormat(YMDHMS,
				Locale.getDefault());
		return format.format(new Date(intime * 1000));
	}

	public static String getRefreshTime(long intime) {
		try {
			if (intime <= 100) {
				return "";
			}
			Date date = new Date(intime);
			if (intime > getStartTimeStamp(0)) {
				return MyApplication.getInstance().getString(R.string.lbl_date_today) + " " + date2String(date, HM);
			}
			return date2String(date, MDHM);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}


	public static Date YMDtoString(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, 0, 0, 0);
		return cal.getTime();
	}

	public static String parseDateByFormat(long millions, String ymdhm) {
		Date date = new Date(millions);
		return date2String(date, ymdhm);
	}

	public static String getToday() {
		return date2String(new Date(), YMD);
	}

	public static long getLimitTimeStamp() {

		long current = System.currentTimeMillis();
		long zero = current / DAY_MILLIONS * DAY_MILLIONS - TimeZone.getDefault().getRawOffset();
		long twelve = zero + DAY_MILLIONS - 1;

		System.out.println(getDateTimeString(twelve));
		return twelve;
	}

	public static long getStartTimeStamp(int days) {
		long current = System.currentTimeMillis();
		long zero = current / DAY_MILLIONS * DAY_MILLIONS - days * DAY_MILLIONS - TimeZone.getDefault().getRawOffset();
		System.out.println(getDateTimeString(zero));
		return zero;
	}

	public static void main(String[] args) {
		getLimitTimeStamp();
		getStartTimeStamp(0);
		getStartTimeStamp(6);
		getStartTimeStamp(29);
		getStartTimeStamp(89);
	}
}
