package com.mex.GalaxyChain.mychart.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LSJ on 2016/1/28.
 */

public class DateUtils {

    public static SimpleDateFormat sf = null;

    /**
     * @param s 1:分时
     *          2：日
     *          3：周
     *          4：月
     * @return
     */
    public static String getPriceTrend(String time, String s) {
        Date d = new Date(Long.parseLong(time));
        if (IConstant.PRICETREND_MIN.equals(s)) {
            sf = new SimpleDateFormat("HH:mm");
        } else if (IConstant.PRICETREND_DAY.equals(s) || IConstant.PRICETREND_WEEK.equals(s)) {
            sf = new SimpleDateFormat("MM.dd");
        } else if (IConstant.PRICETREND_MON.equals(s)) {
            sf = new SimpleDateFormat("MM");
        } else {
            sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
        return sf.format(d);
    }

    /*获取系统时间 */
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    /*获取系统时间 */
    public static String getCurrentDate2() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(d);
    }
    /*获取系统时间 */
    public static long getCurrentDate1() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long time=getStringToDate2(sf.format(d));
        return time;
    }

    public static String getCurrentDateYear() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /*将字符串转为时间戳*/
    public static long getStringToDateTwo(String time) {
        sf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /*将字符串转为时间戳*/
    public static long getStringToDateThree(String time) {
        sf = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /*将时间戳转换为字符串*/
    public static String getDateToStringThree(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("HH:mm");
        try {
            return sf.format(d);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "--:--";
    }

    public static long getStringToDateYMD(String time) {
        sf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }


    /*时间戳转换成字符窜*/
    public static String getDateToString1(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy/MM/dd");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString4(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("MM/dd");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString5(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("MM-dd");
        return sf.format(d);
    }

    /*时间戳转换成时间*/
    public static String getDateToString2(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /*时间戳转换成时间*/
    public static String DateToStringHHMMSS(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("HH:mm:ss");
        return sf.format(d);
    }

    /*时间戳转换成时间*/
    public static String getDateToString7(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        return sf.format(d);
    }

    public static String getDateToString9(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(d);
    }

    /*时间戳转换成时间*/
    public static String getDateToStringMm(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    /*时间戳转换成时间*/
    public static String getDateToString6(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("MM月dd日");
        return sf.format(d);
    }

    /*时间戳转换成时间*/
    public static String getDateToString8(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("MM月dd日 HH:mm");
        return sf.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate1(String time) {
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }
    /*将字符串转为时间戳*/
    public static long getStringToDate2(String time) {
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString3(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return days + " days " + hours + " hours " + minutes + " minutes "
                + seconds + " seconds ";
    }

    //天
    public static long formatDuring1(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        return days;
    }

    //时
    public static long formatDuring2(long mss) {
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        return hours;
    }

    //分
    public static long formatDuring3(long mss) {
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        return minutes;
    }

    //秒
    public static long formatDuring4(long mss) {
        long seconds = (mss % (1000 * 60)) / 1000;
        return seconds;
    }

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    public static String showTime(long releaseTime) {
        long currentTime = DateUtils.getStringToDate1(DateUtils.getCurrentDate());
        long time = currentTime - releaseTime;
        int days = (int) DateUtils.formatDuring1(time);
        int hours = (int) DateUtils.formatDuring2(time);
        int minutes = (int) DateUtils.formatDuring3(time);
        if (days != 0 && days < 10) {
            return days + "天前";
        } else if (days == 0 && hours != 0) {
            return hours + "小时前";
        } else if (days == 0 && hours == 0 && minutes != 0) {
            return minutes + "分钟前";
        } else if (days < 0 || hours < 0 || minutes < 0) {
            return "";
        } else if (days == 0 && hours == 0 && minutes < 1) {
            return "刚刚";
        } else if (days >= 10) {
            return DateUtils.getDateToString6(releaseTime) + "";
        } else {
            return "";
        }
    }

    public static String showTime(String releaseTime) {
        try {
            long release = Long.parseLong(releaseTime);
            long currentTime = DateUtils.getStringToDate1(DateUtils.getCurrentDate());
            long time = currentTime - release;
            int days = (int) DateUtils.formatDuring1(time);
            int hours = (int) DateUtils.formatDuring2(time);
            int minutes = (int) DateUtils.formatDuring3(time);
            if (days != 0 && days < 10) {
                return days + "天前";
            } else if (days == 0 && hours != 0) {
                return hours + "小时前";
            } else if (days == 0 && hours == 0 && minutes != 0) {
                return minutes + "分钟前";
            } else if (days < 0 || hours < 0 || minutes < 0) {
                return "";
            } else if (days == 0 && hours == 0 && minutes < 1) {
                return "刚刚";
            } else if (days >= 10) {
                return DateUtils.getDateToString6(release) + "";
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }

    }

    public static String format(String da) {
        Date date = new Date();
        date.setTime(Long.parseLong(da));
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }


//    public static String showLastTime(String releaseTime) {
//        try {
//            long release = Long.parseLong(releaseTime);
//            long currentTime = DateUtils.getStringToDate1(DateUtils.getCurrentDate());
//            long showTime = currentTime - release;
//            int days = (int) DateUtils.formatDuring1(showTime);
//            if (days < 10 && days >= 0) {
//                return DateUtils.showTime(showTime);
//            } else {
//                return DateUtils.getDateToString6(release);
//            }
//        } catch (Exception e) {
//            return "";
//        }
//
//    }


    public static String show(String time) {

        return showTime(System.currentTimeMillis() - Long.parseLong(time));
    }


    public static String showDayTime(long ti) {
        try {
            int days = (int) DateUtils.formatDuring1(ti);
            return days + "天";
        } catch (Exception e) {
            return "";
        }

    }

    public static int getDays(long l) {
        long s1 = DateUtils.formatDuring1(l);
        int days = (int) s1;
        return days;
    }

    public static int getHours(long l) {
        long s2 = DateUtils.formatDuring2(l);//时
        int hour = (int) s2;
        return hour;
    }

    public static int getMins(long l) {
        long s3 = DateUtils.formatDuring3(l);//分
        int min = (int) s3;
        return min;
    }

    public static int getSec(long l) {
        long s4 = DateUtils.formatDuring4(l);//秒
        int sec = (int) s4;
        return sec;
    }
    public static String transTime(String l) {
        int time= Integer.parseInt(l);
        int N = time/3600;
        time = time%3600;
        int K = time/60;
        time = time%60;
        int M = time;
        return N+":"+K+":"+M;
    }

}