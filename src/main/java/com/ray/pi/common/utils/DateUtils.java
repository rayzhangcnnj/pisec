package com.ray.pi.common.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类,依赖joda time相关jar包，简化操作，提高性能； DateTimeFormat 日期格式化类，线程同步，性能比JDK自带的高；
 * DateTimeFormat.forPattern创建格式化类，只有第一次是新对象，之后从缓存取（API内部实现）
 * 
 * @author zhangrui
 * 
 */
public class DateUtils {

	private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

	public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
	public static final String FULL_DATE_FORMAT_NOSPACE = "yyyyMMddHHmmss";

	public static final int YEAR = Calendar.YEAR;
	public static final int MONTH = Calendar.MONTH;
	public static final int DAY = Calendar.DATE;
	public static final int HOUR = Calendar.HOUR;
	public static final int MINUTE = Calendar.MINUTE;
	public static final int SECOND = Calendar.SECOND;
	public static final int WEEK = Calendar.WEEK_OF_YEAR;
	public static final int MILLISECOND = Calendar.MILLISECOND;

	/**
	 * 日期类型格式化成字符串
	 * 
	 * @param date
	 *            日期
	 * @return yyyy-MM-dd HH:mm:ss格式的字符串
	 */
	public static String date2String(final Date date) {
		return date2String(date, FULL_DATE_FORMAT);
	}

	/**
	 * 按传入的格式，将日期类型格式化成字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return 格式化后的字符串
	 */
	public static String date2String(final Date date, final String format) {
		log.debug("date2String,date:" + date + ",format:" + format);
		String result = null;
		if (null != date && null != format) {
			result = DateTimeFormat.forPattern(format).print(date.getTime());
		}
		log.debug("date2String,result:" + result);
		return result;
	}

	/**
	 * 按传入的格式，将字符串转化成日期对象
	 * 
	 * @param str
	 *            日期字符串
	 * @param format
	 *            格式
	 * @return 日期对象
	 */
	public static Date string2Date(final String str, final String format) {
		log.debug("string2Date,str:" + str + ",format:" + format);
		Date result = null;
		if (null != str && null != format)
			result = DateTimeFormat.forPattern(format).parseDateTime(str)
					.toDate();
		log.debug("string2Date,result:" + result);
		return result;
	}

	/**
	 * 将日期格式的字符串转换成日期对象 格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 *            日期字符串
	 * @return 日期对象
	 */
	public static Date string2Date(final String str) {
		if (null != str)
			return string2Date(str, FULL_DATE_FORMAT);
		return null;
	}

	/**
	 * 在当前日期基础上移动一定年数
	 * 
	 * @param date
	 *            传入日期
	 * @param years
	 *            需要移动的年数
	 * @return 移动后的日期
	 */
	public static Date addYears(final Date date, final int years) {
		if (null != date)
			return new DateTime(date.getTime()).plusYears(years).toDate();
		return null;
	}

	/**
	 * 在当前日期基础上移动一定月数
	 * 
	 * @param date
	 *            传入日期
	 * @param months
	 *            需要移动的月数
	 * @return 移动后的日期
	 */
	public static Date addMonths(final Date date, final int months) {
		if (null != date)
			return new DateTime(date.getTime()).plusMonths(months).toDate();
		return null;
	}

	/**
	 * 在当前日期基础上移动一定天数
	 * 
	 * @param date
	 *            传入日期
	 * @param days
	 *            需要移动的天数
	 * @return 移动后的日期
	 */
	public static Date addDays(final Date date, final int days) {
		if (null != date)
			return new DateTime(date.getTime()).plusDays(days).toDate();
		return null;
	}

	/**
	 * 在当前日期基础上移动一定小时数
	 * 
	 * @param date
	 *            传入日期
	 * @param hours
	 *            需要移动的小时数
	 * @return 移动后的日期
	 */
	public static Date addHours(final Date date, final int hours) {
		if (null != date)
			return new DateTime(date.getTime()).plusHours(hours).toDate();
		return null;
	}

	/**
	 * 在当前日期基础上移动一定分钟数
	 * 
	 * @param date
	 *            传入日期
	 * @param minutes
	 *            需要移动的分钟数
	 * @return 移动后的日期
	 */
	public static Date addMinutes(final Date date, final int minutes) {
		if (null != date)
			return new DateTime(date.getTime()).plusMinutes(minutes).toDate();
		return null;
	}

	/**
	 * 在当前日期基础上移动一定秒数
	 * 
	 * @param date
	 *            传入日期
	 * @param seconds
	 *            需要移动的秒数
	 * @return 移动后的日期
	 */
	public static Date addSeconds(final Date date, final int seconds) {
		if (null != date)
			return new DateTime(date.getTime()).plusSeconds(seconds).toDate();
		return null;
	}

	/**
	 * 在当前日期基础上移动一定周数
	 * 
	 * @param date
	 *            传入日期
	 * @param seconds
	 *            需要移动的周数
	 * @return 移动后的日期
	 */
	public static Date addWeeks(final Date date, final int weeks) {
		if (null != date)
			return new DateTime(date.getTime()).plusWeeks(weeks).toDate();
		return null;
	}

	/**
	 * 在点钱日期基础上移动一定毫秒数
	 * 
	 * @param date
	 *            传入日期
	 * @param millis
	 *            需要移动的毫秒数
	 * @return 移动后的日期
	 */
	public static Date addMilliSecond(final Date date, final int millis) {
		if (null != date)
			return new DateTime(date.getTime()).plusMillis(millis).toDate();
		return null;
	}

	/**
	 * 根据传入日期，需要移动的字段，移动的数量，修改日期
	 * 
	 * @param date
	 *            传入日期
	 * @param field
	 *            修改的字段，例如DateUtils.YEAR、DateUtils.MONTH等
	 * @param i
	 *            移动的数量
	 * @return 修改后的日期
	 */
	public static Date operateDate(final Date date, final int field, final int i) {
		Date resultDate = null;
		switch (field) {
		case YEAR:
			resultDate = addYears(date, i);
			break;
		case MONTH:
			resultDate = addMonths(date, i);
			break;
		case DAY:
			resultDate = addDays(date, i);
			break;
		case HOUR:
			resultDate = addHours(date, i);
			break;
		case MINUTE:
			resultDate = addMinutes(date, i);
			break;
		case SECOND:
			resultDate = addSeconds(date, i);
			break;
		case WEEK:
			resultDate = addWeeks(date, i);
			break;
		case MILLISECOND:
			resultDate = addMilliSecond(date, i);
			break;
		default:
			break;
		}
		return resultDate;
	}

	/**
	 * 判断时间1是否在时间2之后
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 判断结果
	 */
	public static boolean isAfter(final Date date1, final Date date2) {
		if (null == date1 && null == date2)
			return false;
		if (null == date1 || null == date2)
			return false;
		DateTime d1 = new DateTime(date1.getTime());
		return d1.isAfter(date2.getTime());
	}

	/**
	 * 判断2个时间是否相等 date1 > date2 返回1 date1 = date2 返回0 date1 < date2 返回-1
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 判断结果
	 */
	public static int compareTo(final Date date1, final Date date2) {
		if (null == date1 && null == date2)
			return 0;
		if (null == date1)
			throw new NullPointerException("参数1为空");
		if (null == date2)
			throw new NullPointerException("参数2为空");
		return new DateTime(date1.getTime()).compareTo(new DateTime(date2
				.getTime()));
	}

	/**
	 * 得到传入日期是当月的第几天
	 * 
	 * @param date
	 *            传入日期
	 * @return 当月第几天
	 */
	public static int getDayOfMonth(final Date date) {
		if (null == date)
			throw new NullPointerException("传入的日期为空");
		DateTime d = new DateTime(date.getTime());
		return d.getDayOfMonth();
	}

	/**
	 * 得到精确到毫秒的时间
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            时
	 * @param minute
	 *            分
	 * @param second
	 *            秒
	 * @param milli
	 *            毫秒
	 * @return 对应时间对象
	 */
	public static Date getDate(final int year, final int month, final int day, final int hour,
			final int minute, final int second, final int milli) {
		return new DateTime(year, month, day, hour, minute, second, milli)
				.toDate();
	}

	/**
	 * 得到精确到秒的时间
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            时
	 * @param minute
	 *            分
	 * @param second
	 *            秒
	 * @return 对应时间对象
	 */
	public static Date getDate(final int year, final int month, final int day, final int hour,
			final int minute, final int second) {
		return getDate(year, month, day, hour, minute, second, 0);
	}

	/**
	 * 得到精确到日的时间
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return 对应时间对象
	 */
	public static Date getDate(final int year, final int month, final int day) {
		return getDate(year, month, day, 0, 0, 0);
	}

	/**
	 * 得到日期所在年
	 * 
	 * @param date
	 *            日期
	 * @return 所在年
	 */
	public static int getYear(final Date date) {
		if (null == date)
			throw new NullPointerException("传入的日期为空");
		return new DateTime(date.getTime()).getYear();
	}

	/**
	 * 得到日期所在月
	 * 
	 * @param date
	 *            日期
	 * @return 所在月
	 */
	public static int getMonth(final Date date) {
		if (null == date)
			throw new NullPointerException("传入的日期为空");
		return new DateTime(date.getTime()).getMonthOfYear();
	}

	/**
	 * 得到日期所在年的第几天
	 * 
	 * @param date
	 *            日期
	 * @return 所在年的第几天
	 */
	public static int getDayOfYear(final Date date) {
		if (null == date)
			throw new NullPointerException("传入的日期为空");
		return new DateTime(date.getTime()).getDayOfYear();
	}

	/**
	 * 得到日期所在日的第几个小时
	 * 
	 * @param date
	 *            日期
	 * @return 所在日的第几个小时
	 */
	public static int getHourOfDay(final Date date) {
		if (null == date)
			throw new NullPointerException("传入的日期为空");
		return new DateTime(date.getTime()).getHourOfDay();
	}

	/**
	 * 得到日期所在小时的第几分钟
	 * 
	 * @param date
	 *            日期
	 * @return 所在小时的第几分钟
	 */
	public static int getMinuteOfHour(final Date date) {
		if (null == date)
			throw new NullPointerException("传入的日期为空");
		return new DateTime(date.getTime()).getMinuteOfHour();
	}

	/**
	 * 得到日期所在分钟的第几秒
	 * 
	 * @param date
	 *            日期
	 * @return 所在分钟的第几秒
	 */
	public static int getSecondOfMinute(final Date date) {
		if (null == date)
			throw new NullPointerException("传入的日期为空");
		return new DateTime(date.getTime()).getSecondOfMinute();
	}

	/**
	 * 得到系统当前时间
	 * 
	 * @return 当前时间
	 */
	public static Date getNow() {
		return DateTime.now().toDate();
	}

	/**
	 * 判断是否为闰年
	 * 
	 * @param date
	 *            时间
	 * @return 判断结果
	 */
	public static boolean isLeap(final Date date) {
		return new DateTime(date.getTime()).year().isLeap();
	}

	/**
	 * 判断是否为闰年
	 * 
	 * @param year
	 *            年份
	 * @return 判断结果
	 */
	public static boolean isLeap(final int year) {
		return new DateTime(year, 1, 1, 0, 0, 0).year().isLeap();
	}

	/**
	 * 得到当天的最后时间
	 * 
	 * @param date
	 *            时间
	 * @return 所在天的最后时刻
	 */
	public static Date getLastTimeOfDay(final Date date) {
		Date result = null;
		DateTime d = new DateTime(date.getTime());
		int year = d.getYear();
		int month = d.getMonthOfYear();
		int day = d.getDayOfMonth();
		int hour = 23;
		int minute = 59;
		int second = 59;
		result = getDate(year, month, day, hour, minute, second);
		return result;
	}
}
