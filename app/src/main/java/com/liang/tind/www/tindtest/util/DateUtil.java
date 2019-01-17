package com.liang.tind.www.tindtest.util;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/3/30.
 */

public class DateUtil {

    private final static String PATTERN_DATE = "yyyy-MM-dd";
    private final static String PATTERN_YEAR_MONTH = "yyyy-MM";
    private final static String PATTERN_WAF = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    private final static SimpleDateFormat mDateFormat = new SimpleDateFormat(PATTERN_DATE, Locale.getDefault());

    public final static int DEFAULT_INTERVAL = 1;

    /**
     * 获取当天的日期
     * @return yyyy-MM-dd
     */
    public static String getDate() {
        return getDate(0);
    }
    /**
     * 获取当天的日期
     * @return yyyy-MM-dd
     */
    public static String getDate(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, offset);
        return new SimpleDateFormat(PATTERN_DATE, Locale.getDefault()).format(calendar.getTime());
    }
 /**
     * 获取当月
     * @param pattern
     * @return yyyy-MM-dd
     */
    public static String getDate(String pattern) {
        Calendar calendar = Calendar.getInstance();
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(calendar.getTime());
    }

    /**
     * 获取 这个月 的日期
     * @return yyyy-MM
     */
    public static String getYearMonth() {
       return getYearMonth(0);
    }
    /**
     * 获取 这个月 + offset 月的日期
     * @param offset
     * @return yyyy-MM
     */
    public static String getYearMonth(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, offset);
        return new SimpleDateFormat(PATTERN_YEAR_MONTH, Locale.getDefault()).format(calendar.getTime());
    }
    /**
     * 获取 今天 + offset 天的日期
     * @param pattern
     * @param offset
     * @return yyyy-MM-dd
     */
    public static String getDate(String pattern, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, offset);
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(calendar.getTime());
    }

    /**
     * 获取当天是当月的第几天
     * @return day
     */
    public static int getDaysOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取这个月 + offset 月的天数
     * @param offset
     * @return int
     */
    public static int getDaysOfMonth(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, offset);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取所给日期的yyyy-MM-dd 的day
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getIntDay(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat(PATTERN_DATE, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(date));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    /**
     * 获取所给日期的yyyy-MM-dd 的month
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getIntMonth(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat(PATTERN_DATE, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(date));
        return calendar.get(Calendar.MONTH);
    }
    public static String getYearMonth(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat(PATTERN_DATE, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(date));
        return new SimpleDateFormat(PATTERN_YEAR_MONTH, Locale.getDefault()).format(calendar.getTime());
    }

    public static Calendar getYearMonthCalendar(String yearMonth) throws ParseException {
        DateFormat format = new SimpleDateFormat(PATTERN_YEAR_MONTH, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(yearMonth));
        return calendar;
    }

    public static int getDaysOfYearMonth(String yearMonth) throws ParseException {
        DateFormat format = new SimpleDateFormat(PATTERN_YEAR_MONTH, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(yearMonth));
        int month = calendar.get(Calendar.MONTH);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        return calendar.get(Calendar.DATE);
    }

    public static int getYearMonthNumber(String yearMonth) throws ParseException {
        DateFormat format = new SimpleDateFormat(PATTERN_YEAR_MONTH, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(yearMonth));
        return  Integer.parseInt(new SimpleDateFormat("yyyyMM", Locale.getDefault()).format(calendar.getTime()));
    }


    public static String getYearMonthFromDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat(PATTERN_WAF, Locale.getDefault());
        Date d = format.parse(date);
        return new SimpleDateFormat(PATTERN_YEAR_MONTH, Locale.getDefault()).format(d);
    }

    /**
     * 获取当月
     * @return
     */
    public static int getIntMonth() {
        return getIntMonth(0);
    }

    /**
     * 获取当月 + offset月
     * @param offset
     * @return
     */
    public static int getIntMonth(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, offset);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static String getCalendar(String ym, String day, int offset) throws ParseException {
        String date = ym + "-" + day;
        DateFormat format = new SimpleDateFormat(PATTERN_DATE, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(date));
        calendar.add(Calendar.DATE, offset);
        return new SimpleDateFormat(PATTERN_DATE, Locale.getDefault()).format(calendar.getTime());
    }

    /**
     * 获取格式化后的当月，小于10的前面补0
     * @return
     */
    public static String getStringMonth() {
        return getStringMonth(0);
    }

    /**
     * 获取格式化后的当月 + offset月，小于10的前面补0
     * @param offset
     * @return
     */
    public static String getStringMonth(int offset) {
        return transformDigitForString(getIntMonth(offset));
    }

    /**
     * 将int类型转化成string类型，小于10的前面补0
     * @param digit
     * @return
     */
    public static String transformDigitForString(int digit) {
        if (digit < 10) {
            return "0" + digit;
        } else return "" + digit;
    }

    public static long calibrationTime(String serverTimeStr, long serverTimeRecordTime) {
        if (!TextUtils.isEmpty(serverTimeStr) && serverTimeRecordTime > 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
            try {
                Date date = format.parse(serverTimeStr);
                return date.getTime() + (System.currentTimeMillis() - serverTimeRecordTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return System.currentTimeMillis();
    }

    public static long calcDelay(int interval) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int allM = hour * 60 + minute;
        int count = allM/interval + 1;
        int nextAllM = count * interval;
        int nextHour = nextAllM/60;
        int nextMinute = nextAllM%60;
        long prevTimestamp = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, nextHour);
        calendar.set(Calendar.MINUTE, nextMinute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long nextTimestamp = calendar.getTimeInMillis();
        return nextTimestamp - prevTimestamp;
    }

    public static int calcVersion(int interval, String serverTimeStr, long curTimestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calcServerTimeStamp(serverTimeStr, curTimestamp));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int allM = hour * 60 + minute;
        int count = allM/interval;
        return count;
    }

    public static long calcServerTimeStamp(String serverTimeStr, long curTimestamp) {
        // long curTimestamp = System.currentTimeMillis();
        if (!TextUtils.isEmpty(serverTimeStr) && serverTimeStr.contains("+")) {
            String timeZone = serverTimeStr.substring(serverTimeStr.indexOf("+"));
            TimeZone serverTimeZone = TimeZone.getTimeZone("GMT" + timeZone);
            TimeZone localTimeZone = TimeZone.getDefault();
            return curTimestamp - localTimeZone.getRawOffset() + serverTimeZone.getRawOffset();
        }
        return curTimestamp;
    }

    public static String calcDateByServerTime(String serverTimeStr, long curTimestamp) {
        long timestamp = calcServerTimeStamp(serverTimeStr, curTimestamp);
        return mDateFormat.format(timestamp);
    }

    @Deprecated
    public static int calcLocalVersion(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int allM = hour * 60 + minute + 1;
        int count = allM/DEFAULT_INTERVAL;
        return count;
    }

    @Deprecated
    public static long calcLocalDelay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int minute = calendar.get(Calendar.MINUTE);
        int nextMinute = minute + 1;
        long curTimestamp = calendar.getTimeInMillis();
        calendar.set(Calendar.MINUTE, nextMinute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long nowTimestamp = calendar.getTimeInMillis();
        return nowTimestamp - curTimestamp;
    }
}
