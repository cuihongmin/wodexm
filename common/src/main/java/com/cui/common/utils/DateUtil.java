package com.cui.common.utils;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 
 * @project spiritframework-core
 * @description 日期处理支持类，提供日常工作中的所有日期的操作处理
 * @author yekairush $<a href="mailto:yekairush@163.com">联系作者</a>$
 * @version $id:DateUtil.java,Revision:v1.0,Date:2008-12-14 下午08:01:19 $
 * @Modification $Date:2012-8-27 {对日期的格式化增加了locale功能}
 */
@SuppressWarnings({ "deprecation", "unused" })
public class DateUtil {
	/**
	 * 定义日志
	 */
	private final static Logger logger = Logger.getLogger(com.cui.common.utils.DateUtil.class);

	private static final String[] CHINA_DATE_KEYS = { "○", "О", "0", "Ο", "O",
			"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "年", "月",
			"日", "时", "分", "秒" };
	private static final String[] CHINA_DATE_KEY_MAP = { "0", "0", "0", "0",
			"0", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "-",
			"-", "", ":", ":", "" };

	/**
	 * 天干
	 */
	private static String[] Gan = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛",
			"壬", "癸" };

	/**
	 * 地支
	 */
	private static String[] Zhi = { "子", "丑", "寅", "卯", "辰", "巳", "午", "未",
			"申", "酉", "戌", "亥" };

	/**
	 * 生肖
	 */
	private final String[] Animals = { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊",
			"猴", "鸡", "狗", "猪" };
	/**
	 * 英文月份名称
	 */
	private static final String[] MONTH_ENGLISH_NAME = { "January", "February",
			"March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	/**
	 * 中文星期的名称
	 */
	private static final String[] WEEK_CHINA_NAME = { "星期一", "星期二", "星期三",
			"星期四", "星期五", "星期六", "星期日" };

	/**
	 * 英文星期的名称
	 */
	private static final String[] WEEK_ENGLISH_NAME = { "Monday", "Tuesday",
			"Wensday", "Tursday", "Friday", "Saturday", "Sunday" };

	/**
	 * 定义日期的格式
	 */
	public final static class FORMAT {
		/**
		 * 8位日期格式
		 */
		public final static String DATE_8CHAR = "yyyyMMdd";

		/**
		 * 点号日期格式
		 */
		public final static String DATE_DOT = "yyyy.MM.dd";

		/**
		 * 反斜杠日期格式
		 */
		public final static String DATE_SLASH = "yyyy/MM/dd";

		/**
		 * 横杠日期格式
		 */
		public final static String DATE_HORIZONTAL = "yyyy-MM-dd";

		/**
		 * 日期时间(日期点号分割)
		 */
		public final static String DATATIME_DOT = "yyyy.MM.dd HH:mm:ss";
		
		
		/**
		 * 日期时间
		 */
		public final static String DATETIME_HORIZONTAL1= "yyyyMMddHHmmss";

		/**
		 * 日期时间(日期反斜杠)
		 */
		public final static String DATETIME_SLASH = "yyyy/MM/dd HH:mm:ss";

		/**
		 * 日期时间(日期横杠)
		 */
		public final static String DATETIME_HORIZONTAL = "yyyy-MM-dd HH:mm:ss";
		
		/**
		 * 日期时间(日期横杠)
		 */
		public final static String DATETIME_HORIZONTAL_MMDD = "MM-dd HH:mm:ss";
		
		
		
	}

	/**
	 * @todo 将日期字符串或时间转换成时间类型 日期字符串中的日期分隔符可是:"/",".","-"， 返回时间具体到秒 只提供常用的日期格式处理
	 * @param str
	 * @return Date
	 */
	public static Date parse(Object data, String format) {
		return parse(data, format, null);
	}

	public static Date parse(Object data, String format, String local) {
		if (null == data) {
			logger.error("The date string is null!");
			return null;
		}
		if (data instanceof String) {
			String dateString = data.toString();
			if ("".equals(dateString)
					|| "null".equals(dateString.toLowerCase())) {
				logger.error("The date string is null!");
				return null;
			}
			return parseString(dateString, format, local);
		} else {
			String result = formatDate(data, format, local);
			return parseString(result, format, local);
		}
	}

	public static Date parseString(String dateStr) {
		return parseString(dateStr, null, null);
	}

	/**
	 * @todo 将日期字符串或时间转换成时间类型 日期字符串中的日期分隔符可是:"/",".","-"， 返回时间具体到秒 只提供常用的日期格式处理
	 * @param dateStr
	 * @return
	 */
	public static Date parseString(String dateStr, String dateFormat,
			String locale) {
		if (dateStr == null)
			return null;
		dateStr = dateStr.trim();
		if ("".equals(dateStr))
			return null;
		String realDF;
		if (StringUtil.isNotNullAndBlank(dateFormat)) {
			realDF = dateFormat;
		} else {
			if (StringUtil.matches(dateStr, "^\\d{1,2}\\:\\d{1,2}\\:\\d{1,2}$"))
				realDF = "HH:mm:ss";
			else if (StringUtil.matches(dateStr,
					"^\\d{1,2}\\:\\d{1,2}\\:\\d{1,2}\\.\\d{1,3}$"))
				realDF = "HH:mm:ss.SSS";
			else if (StringUtil.matches(dateStr,
					"^\\d{1,2}\\.\\d{1,2}\\.\\d{1,2}$"))
				realDF = "HH.mm.ss";
			else if (StringUtil.matches(dateStr, "^\\d{1,2}\\.\\d{1,2}$"))
				realDF = "HH.mm";
			else if (StringUtil.matches(dateStr, "^\\d{1,2}\\:\\d{1,2}$"))
				realDF = "HH:mm";
			else {
				dateStr = dateStr.replace("-", "/").replace(".", "/")
						.replace(":", "/").replace("\\", "/");
				// 按概率高低排序
				if (StringUtil.matches(dateStr,
						"^\\d{4}\\/\\d{1,2}\\/\\d{1,2}$"))
					realDF = "yyyy/MM/dd";
				else if (StringUtil
						.matches(dateStr,
								"^\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\s+\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}$"))
					realDF = "yyyy/MM/dd HH/mm/ss";
				else if (StringUtil
						.matches(dateStr,
								"^\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\s+\\d{1,2}\\/\\d{1,2}$"))
					realDF = "yyyy/MM/dd HH/mm";
				else if (StringUtil
						.matches(dateStr,
								"^\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\s+\\d{1,2}$"))
					realDF = "yyyy/MM/dd HH";
				else if (StringUtil.matches(dateStr, "^\\d{8}$"))
					realDF = "yyyyMMdd";
				else if (StringUtil.matches(dateStr, "^\\d{8}\\s+\\d{6}$"))
					realDF = "yyyyMMdd HHmmss";
				else if (StringUtil.matches(dateStr, "^\\d{4}\\/\\d{1,2}$"))
					realDF = "yyyy/MM";
				else if (StringUtil.matches(dateStr, "^\\d{6}$"))
					realDF = "yyyyMM";
				else if (StringUtil
						.matches(dateStr,
								"^\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\s+\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,3}$"))
					realDF = "yyyy/MM/dd HH/mm/ss/SSS";
				else if (StringUtil.matches(dateStr,
						"^\\d{2}\\/\\d{1,2}\\/\\d{1,2}$"))
					realDF = "yy/MM/dd";
				else if (StringUtil.matches(dateStr, "^\\d{2}\\/\\d{2}$"))
					realDF = "yy/MM";
				else if (StringUtil
						.matches(dateStr,
								"^\\d{2}\\/\\d{1,2}\\/\\d{1,2}\\s+\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}$"))
					realDF = "yy/MM/dd HH/mm/ss";
				else if (StringUtil
						.matches(dateStr,
								"^\\d{2}\\/\\d{1,2}\\/\\d{1,2}\\s+\\d{1,2}\\/\\d{1,2}$"))
					realDF = "yy/MM/dd HH/mm";
				else if (StringUtil
						.matches(dateStr,
								"^\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}$"))
					realDF = "yyyy/MM/dd/HH/mm/ss";
				else if (StringUtil
						.matches(dateStr,
								"^\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,3}$"))
					realDF = "yyyy/MM/dd/HH/mm/ss/SSS";
				else if (StringUtil
						.matches(dateStr,
								"^\\d{2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}$"))
					realDF = "yy/MM/dd/HH/mm/ss";
				else if (StringUtil.matches(dateStr,
						"^\\d{2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}\\/\\d{1,2}$"))
					realDF = "yy/MM/dd/HH/mm";
				else if (StringUtil.matches(dateStr, "^\\d{6}\\s+\\d{6}$"))
					realDF = "yyMMdd HHmmss";
				else if (StringUtil.matches(dateStr, "^\\d{7,9}$"))
					realDF = "HHmmssSSS";
				else if (StringUtil.matches(dateStr, "^\\d{14}$"))
					realDF = "yyyyMMddHHmmss";
				else if (StringUtil.matches(dateStr, "^\\d{15,17}$"))
					realDF = "yyyyMMddHHmmssSSS";
				else if (StringUtil.matches(dateStr, "^\\d{12}$"))
					realDF = "yyMMddHHmmss";
				else if (StringUtil.matches(dateStr, "^\\d{4}$"))
					realDF = "yyyy";
				else
					realDF = "yyyy/MM/dd";
			}
		}
		DateFormat df = (StringUtil.isNullOrBlank(locale)) ? new SimpleDateFormat(
				realDF) : new SimpleDateFormat(realDF, new Locale(locale));
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			logger.error("将日期字符串或时间转换成时间类型 异常：",e);
		}
		return null;
	}

	public static Date convertDateObject(Object dt) {
		return convertDateObject(dt, null, null);
	}

	/**
	 * @todo 日期对象类型转换
	 * @param dt
	 * @param format
	 * @param local
	 * @return
	 */
	public static Date convertDateObject(Object dt, String format,
			String local) {
		if (dt == null) {
			System.err.println("日期不能为空,请正确输入!");
			return null;
		}
		if (!(dt instanceof String) && !(dt instanceof java.sql.Date)
				&& !(dt instanceof Date)
				&& !(dt instanceof Integer)
				&& !(dt instanceof Long))
			throw new IllegalArgumentException(
					"日期格式必须是String、Date、Long、Integer类型,请正确输入!");
		Date result = null;

		if (dt instanceof String) {
			result = parseString((String) dt, format, local);
		} else if (dt instanceof Date)
			result = new Date(((Date) dt).getTime());
		else if (dt instanceof java.sql.Date)
			result = new Date(((java.sql.Date) dt).getTime());
		else if (dt instanceof Number)
			result = new Date(((Number) dt).longValue());
		else
			result = parseString(dt.toString(), format, local);
		return result;
	}

	/**
	 * @todo 格式化日期
	 * @param dt
	 * @param fmt
	 * @return
	 */
	public static String formatDate(Object dt, String fmt) {
		return formatDate(dt, fmt, null);
	}

	public static String formatDate(Object dt, String fmt, String locale) {
		if (dt == null)
			return null;
		if (fmt.equalsIgnoreCase("MM"))
			return Integer.toString(getMonth(dt));
		else if (fmt.equalsIgnoreCase("dd"))
			return Integer.toString(getDay(dt));
		DateFormat df = StringUtil.isNullOrBlank(locale) ? new SimpleDateFormat(
				fmt) : new SimpleDateFormat(fmt, new Locale(locale));
		Date tmp = convertDateObject(dt, null,locale);
		return (null == tmp) ? null : df.format(tmp);
	}

	public static String formatDate(Object dt, String fmt, String targetFmt,
			String locale) {
		Date result = parse(dt, fmt, locale);
		return formatDate(result, targetFmt);
	}

	/**
	 * @todo 获取当前操作系统的日期
	 * @return Date
	 */
	public static java.sql.Date getSqlDate() {
		return getSqlDate(new Date());
	}

	/**
	 * @todo 获取当前以sql.date的日期
	 * @param date
	 * @return
	 */
	public static java.sql.Date getSqlDate(Object date) {
		return new java.sql.Date(convertDateObject(
				date == null ? new Date() : date).getTime());
	}

	public static Timestamp getTimestamp(Object date) {
		Date tmp = convertDateObject(date == null ? new Date() : date);
		return new Timestamp(tmp.getTime());
	}

	/**
	 * @todo 获取当前操作系统的时间
	 * @return 当前操作系统的时间
	 */
	public static Date getNowTime() {
		return Calendar.getInstance().getTime();
	}

	// Add millisecond
	public static Date addMilliSecond(Object dt, long millisecond) {
		Date result = convertDateObject(dt);
		result.setTime(result.getTime() + millisecond);
		return result;
	}

	public static Date addSecond(Object dt, long second) {
		return addMilliSecond(dt, 1000L * second);
	}

	public static Date addSecond(Object dt, double second) {
		Double millisecond = new Double(1000.0 * second);
		return addMilliSecond(dt, millisecond.longValue());
	}

	public static Date addMinute(Object dt, long minute) {
		return addMilliSecond(dt, 1000L * 60L * minute);
	}

	public static Date addMinute(Object dt, double minute) {
		Double millisecond = new Double(1000.0 * 60.0 * minute);
		return addMilliSecond(dt, millisecond.longValue());
	}

	public static Date addHour(Object dt, long hour) {
		return addMilliSecond(dt, 1000L * 60L * 60L * hour);
	}

	public static Date addHour(Object dt, double hour) {
		Double millisecond = new Double(1000.0 * 60.0 * 60.0 * hour);
		return addMilliSecond(dt, millisecond.longValue());
	}

	public static Date addDay(Object dt, long day) {
		return addMilliSecond(dt, 1000L * 60L * 60L * 24L * day);
	}

	public static Date addDay(Object dt, double day) {
		Double millisecond = new Double(1000.0 * 60.0 * 60.0 * 24.0 * day);
		return addMilliSecond(dt, millisecond.longValue());
	}

	// add month
	public static Date addMonth(Object dt, int month) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(convertDateObject(dt));
		gc.add(2, month);
		return gc.getTime();
	}

	/**
	 * add year
	 * 
	 * @param dt
	 * @param year
	 * @return
	 */
	public static Date addYear(Object dt, int year) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(convertDateObject(dt));
		gc.add(1, year);
		return gc.getTime();
	}

	/**
	 * @todo 在给定的日期上做年的加减运算
	 * @param dt
	 * @param year
	 * @return
	 */
	public static Date addYear(Object dt, double year) {
		// 如果日期为空表示以当前日期为基数进行加减
		Date tmp = convertDateObject(dt);
		double floorYear = Math.floor(year);
		double decimalYear = year - floorYear;
		tmp = addYear(tmp, (new Double(floorYear)).intValue());
		Calendar cal = Calendar.getInstance();
		cal.setTime(tmp);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
		Date nextdt = cal.getTime();
		long yearMillisecond = nextdt.getTime() - tmp.getTime();
		double millisecond = yearMillisecond * decimalYear;
		return addMilliSecond(tmp, (long) millisecond);
	}

	public static int getYear() {
		return getYear(null);
	}

	public static int getYear(Object dateValue) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if (dateValue != null)
			currentDate.setTime(convertDateObject(dateValue));
		return currentDate.get(Calendar.YEAR);
	}

	public static String getShortYear(Object dateValue) {
		int year = getYear(dateValue);
		return Integer.toString(year).substring(2);
	}

	public static int getMonth() {
		return getMonth(null);
	}

	/**
	 * @todo 获取月份的英文名称
	 * @param dateValue
	 * @return
	 */
	public static String getMonthName(Object dateValue) {
		return MONTH_ENGLISH_NAME[getMonth(dateValue) - 1];
	}

	public static int getMonth(Object dateValue) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if (dateValue != null)
			currentDate.setTime(convertDateObject(dateValue));
		return currentDate.get(Calendar.MONTH) + 1;
	}

	public static int getDay() {
		return getDay(null);
	}

	public static int getDay(Object dateValue) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if (null != dateValue)
			currentDate.setTime(convertDateObject(dateValue));
		return currentDate.get(Calendar.DAY_OF_MONTH);
	}

	public static int getHour() {
		return getHour(null);
	}

	public static int getHour(Object dateValue) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if (null != dateValue)
			currentDate.setTime(convertDateObject(dateValue));
		return currentDate.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute() {
		return getMinute(null);
	}

	public static int getMinute(Object dateValue) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if (null != dateValue)
			currentDate.setTime(convertDateObject(dateValue));
		return currentDate.get(Calendar.MINUTE);
	}

	public static int getSecond() {
		return getSecond(null);
	}

	public static int getSecond(Object dateValue) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if (dateValue != null)
			currentDate.setTime(convertDateObject(dateValue));
		return currentDate.get(Calendar.SECOND);
	}

	/**
	 * @todo 获取当前日历
	 * @return
	 */
	public static GregorianCalendar getCalendar() {
		return new GregorianCalendar();
	}

	public static int getDayOfWeek() {
		return getDayOfWeek(null);
	}

	/**
	 * @todo 获取指定日期是星期几
	 * @param dateValue
	 * @return
	 */
	public static int getDayOfWeek(Object dateValue) {
		GregorianCalendar currentDate = getCalendar();
		if (dateValue != null)
			currentDate.setTime(convertDateObject(dateValue));
		int week = currentDate.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0)
			return 7;
		return week;
	}

	/**
	 * @todo 获取日期的中文名称
	 * @param dateValue
	 * @return
	 */
	public static String getDayOfWeekChinaName(Object dateValue) {
		return WEEK_CHINA_NAME[getDayOfWeek(dateValue) - 1];
	}

	/**
	 * @todo 获取日期的中文名称
	 * @param dateValue
	 * @return
	 */
	public static String getDayOfWeekEnglishName(Object dateValue) {
		return WEEK_ENGLISH_NAME[getDayOfWeek(dateValue) - 1];
	}

	public static int getWeekOfMonth() {
		return getWeekOfMonth(null);
	}

	/**
	 * @todo 获取给定日期所在月的第几周
	 * @param dateValue
	 * @return
	 */
	public static int getWeekOfMonth(Object dateValue) {
		GregorianCalendar currentDate = getCalendar();
		if (dateValue != null)
			currentDate.setTime(convertDateObject(dateValue));
		return currentDate.get(Calendar.WEEK_OF_MONTH) - 1;
	}

	public static int getWeekOfYear() {
		return getWeekOfYear(null);
	}

	/**
	 * @todo 获取给定日期所在年的第几周
	 * @param dateValue
	 * @return
	 */
	public static int getWeekOfYear(Object dateValue) {
		GregorianCalendar currentDate = getCalendar();
		if (dateValue != null)
			currentDate.setTime(convertDateObject(dateValue));
		return currentDate.get(Calendar.WEEK_OF_YEAR) - 1;
	}

	/**
	 * @todo 获取相隔两个时间的周数
	 * @param floorDate
	 * @param goalDate
	 * @return
	 */
	public static double getIntervalWeeks(Object floorDate, Object goalDate) {
		return getIntervalHours(floorDate, goalDate) / (7 * 24);
	}

	/**
	 * @todo 获取两个时间间隔的天数
	 * @param floorDate
	 * @param goalDate
	 * @return
	 */
	public static long getIntervalDays(Object floorDate, Object goalDate) {
		return getIntervalMillSeconds(floorDate, goalDate) / (3600 * 1000 * 24);
	}

	/**
	 * @todo 获取两个时间间隔的小时数
	 * @param floorDate
	 * @param goalDate
	 * @return
	 */
	public static double getIntervalHours(Object floorDate, Object goalDate) {
		return getIntervalMillSeconds(floorDate, goalDate) / (3600 * 1000);
	}

	/**
	 * @todo 获取两时间的间隔分钟
	 * @param floorDate
	 * @param goalDate
	 * @return
	 */
	public static double getIntervalMinutes(Object floorDate, Object goalDate) {
		return getIntervalMillSeconds(floorDate, goalDate) / (60 * 1000);
	}

	/**
	 * @todo 获取两时间的间隔分钟
	 * @param floorDate
	 * @param goalDate
	 * @return
	 */
	public static double getIntervalSeconds(Object floorDate, Object goalDate) {
		return getIntervalMillSeconds(floorDate, goalDate) / (1000);
	}

	/**
	 * @todo 获取两时间间隔的毫秒数
	 * @param floorDate
	 * @param goalDate
	 * @return
	 */
	public static long getIntervalMillSeconds(Object floorDate, Object goalDate) {
		return convertDateObject(goalDate).getTime()
				- convertDateObject(floorDate).getTime();
	}

	/**
	 * @todo 获取两个时间之间的间隔并以格�?:xx天xx小时xx分钟xx秒输出
	 * @param floorDate
	 * @param goalDate
	 * @return
	 */
	public static String getIntervalDate(Object floorDate, Object goalDate) {
		long intervalSeconds = getIntervalMillSeconds(floorDate, goalDate) / 1000;
		StringBuilder intervalDate = new StringBuilder();
		if (intervalSeconds < 0)
			intervalSeconds = -intervalSeconds;
		// 不单纯为了性能，而是直观的表达计算的方式
		long days = intervalSeconds / (24 * 60 * 60);
		intervalSeconds = intervalSeconds - days * 24 * 60 * 60;
		long hours = intervalSeconds / (60 * 60);
		intervalSeconds = intervalSeconds - hours * 3600;
		long minuts = intervalSeconds / 60;
		intervalSeconds = intervalSeconds - minuts * 60;
		if (days > 0)
			intervalDate.append(days + "天");
		if (hours > 0)
			intervalDate.append(hours + "小时");
		if (minuts > 0)
			intervalDate.append(minuts + "分钟");
		if (intervalSeconds > 0)
			intervalDate.append(intervalSeconds + "秒");

		return intervalDate.toString();
	}

	/**
	 * @todo <b>获取两时间间隔的年数</b>
	 * @param floorDate
	 * @param goalDate
	 * @return
	 */
	public static long getIntervalYears(Object floorDate, Object goalDate) {
		return convertDateObject(goalDate).getYear()
				- convertDateObject(floorDate).getYear();
	}

	/**
	 * @todo <b>获取两时间间隔的月数</b>
	 * @param floorDate
	 * @param goalDate
	 * @return
	 */
	public static long getIntervalMonths(Object floorDate, Object goalDate) {
		Date date1 = convertDateObject(goalDate);
		Date date2 = convertDateObject(floorDate);
		return date1.getYear() * 12 + date1.getMonth() - date2.getYear() * 12
				- date2.getMonth();
	}

	/**
	 * @todo 获取给定日期的月份的天数
	 * @param date
	 * @return
	 */
	public static int getMonthDays(Object date) {
		Date target = convertDateObject(date);
		int year = target.getYear();
		int month = target.getMonth();
		GregorianCalendar beginCalendar = new GregorianCalendar();
		beginCalendar.set(year, month, 1);
		GregorianCalendar endCalendar = new GregorianCalendar();
		endCalendar.set(year, month + 1, 1);
		return new Double(getIntervalHours(beginCalendar.getTime(),
				endCalendar.getTime()) / 24).intValue();
	}

	/**
	 * @todo 将日期转化为中文格式
	 * @param dateValue
	 * @return
	 */
	public static String format2China(Object dateValue) {
		Date date = convertDateObject(dateValue);
		if (null == date)
			return null;
		GregorianCalendar pointDate = new GregorianCalendar();
		pointDate.setTime(convertDateObject(dateValue));
		String tmpDate;
		StringBuilder result = new StringBuilder();
		if (dateValue instanceof String) {
			tmpDate = (String) dateValue;
			if (tmpDate.length() >= 4)
				result.append(pointDate.get(Calendar.YEAR) + "年");
			if (tmpDate.length() >= 6)
				result.append((pointDate.get(Calendar.MONTH) + 1) + "月");
			if (tmpDate.length() >= 8)
				result.append(pointDate.get(Calendar.DAY_OF_MONTH) + "日");
			if (tmpDate.length() > 10) {
				result.append(pointDate.get(Calendar.HOUR_OF_DAY) + "时");
				result.append(pointDate.get(Calendar.MINUTE) + "分");
				result.append(pointDate.get(Calendar.SECOND) + "秒");
			}
		} else {
			result.append(pointDate.get(Calendar.YEAR) + "年");
			result.append((pointDate.get(Calendar.MONTH) + 1) + "月");
			result.append(pointDate.get(Calendar.DAY_OF_MONTH) + "日");
			result.append(pointDate.get(Calendar.HOUR_OF_DAY) + "时");
			result.append(pointDate.get(Calendar.MINUTE) + "分");
			result.append(pointDate.get(Calendar.SECOND) + "秒");
		}
		return result.toString();

	}

	/**
	 * @todo 转换中文日期为指定格式的英文日期形式
	 * @param chinaDate
	 * @param fmt
	 * @return
	 */
	public static String parseChinaDate(String chinaDate, String fmt) {
		if (StringUtil.isNullOrBlank(chinaDate))
			return null;
		String tmp = chinaDate;
		for (int i = 0; i < CHINA_DATE_KEYS.length; i++) {
			tmp = tmp.replaceAll(CHINA_DATE_KEYS[i], CHINA_DATE_KEY_MAP[i]);
		}
		if (StringUtil.isNullOrBlank(fmt))
			return tmp.toString();
		else
			return formatDate(tmp, fmt);
	}

	/**
	 * @todo 转换中文日期为英文格式
	 * @param chinaDate
	 * @return
	 */
	public static String parseChinaDate(String chinaDate) {
		return parseChinaDate(chinaDate, null);
	}

	/**
	 * @todo 获取月份的第一天
	 * @param objectDate
	 * @return
	 */
	public static Date firstDayOfMonth(Object objectDate) {
		Date date = convertDateObject(objectDate);
		if (null == date)
			return null;
		else {
			String tmp = formatDate(date, "yyyy-MM-dd");
			return parse(tmp, "yyyy-MM");
		}
	}

	/**
	 * @todo 获取月份的最后一天
	 * @param objectDate
	 * @return
	 */
	public static Date lastDayOfMonth(Object objectDate) {
		Date date = convertDateObject(objectDate);
		if (null == date)
			return null;
		else {
			String tmp = formatDate(date, "yyyy-MM-dd");
			Date result = parse(tmp, "yyyy-MM");
			result = addMonth(result, 1);
			result = addDay(result, -1);
			return result;
		}
	}

	/**
	 * @todo 获取指定日期属于当年中第几个季度
	 * @param objDate
	 * @return
	 */
	public static int getQuarter(Object objDate) {
		Date date = convertDateObject(objDate);
		if (null == date)
			date = com.cui.common.utils.DateUtil.getNowTime();
		int month = getMonth(date);
		return (month + 2) / 3;
	}

	/**
	 * @todo <b>阳历转农历</b>
	 * @param solarDate
	 * @return
	 */
	public static Date getLunarDate(Date solarDate) {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(solarDate);
		String result = ChinaCalendar.sCalendarSolarToLundar(
				currentDate.get(Calendar.YEAR),
				currentDate.get(Calendar.MONTH) + 1,
				currentDate.get(Calendar.DAY_OF_MONTH));
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			return df.parse(result);
		} catch (ParseException e) {
			logger.error("阳历转农历异常：",e);
		}
		return null;
	}

	/**
	 * @todo <b>农历转阳历</b>
	 * @param lunarDate
	 * @return
	 */
	public static Date getSolarDate(Date lunarDate) {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(lunarDate);
		String result = ChinaCalendar.sCalendarLundarToSolar(
				currentDate.get(Calendar.YEAR),
				currentDate.get(Calendar.MONTH) + 1,
				currentDate.get(Calendar.DAY_OF_MONTH));
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			return df.parse(result);
		} catch (ParseException e) {
			logger.error("农历转阳历异常：",e);
		}
		return null;
	}

	/**
	 * @todo <b>获取农历年的生肖</b>
	 * @param year
	 * @return
	 */
	public String getAnimalByLunarYear(int year) {
		return Animals[(year - 4) % 12];
	}

	/***
	 * <P>@Description: 将date日期转换为 String 格式[yyyy-MM-dd HH:mm:ss]</P> 
	 * @param date 日期
	 * @return
	 * @author xianlong@huaxiafinance.com
	 * @date 2016年9月21日 下午3:26:02
	 * @Company:华夏信财投资管理(上海)有限责任公司
	 * @version V1.0
	 */
	public static String getDateToStrTime(Date date) {
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String formatedDate = sdf.format(date);
		    return formatedDate;
		} 
		return "";
	}
	
	/***
	 * <P>@Description: 将date日期转换为 String 格式[yyyy-MM-dd HH:mm:ss]</P> 
	 * @param date 日期
	 * @return
	 * @author xianlong@huaxiafinance.com
	 * @date 2016年9月21日 下午3:26:02
	 * @Company:华夏信财投资管理(上海)有限责任公司
	 * @version V1.0
	 */
	public static String getDateToStrTime(Date date,String formate) {
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
		    String formatedDate = sdf.format(date);
		    return formatedDate;
		} 
		return "";
	}
	
	/***
	 * <P>@Description: 将date日期转换为 String 格式[yyyy-MM-dd HH:mm]</P> 
	 * @param date 日期
	 * @return
	 * @author xianlong@huaxiafinance.com
	 * @date 2016年9月21日 下午3:26:02
	 * @Company:华夏信财投资管理(上海)有限责任公司
	 * @version V1.0
	 */
	public static String covDateToStrTime(Date date) {
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    String formatedDate = sdf.format(date);
		    return formatedDate;
		}
		return "";
	}

	
	/***
	 * <P>@Description: 将date日期转换为 String 格式[yyyy-MM-dd HH:mm]</P> 
	 * @param date 日期
	 * @return
	 * @author xianlong@huaxiafinance.com
	 * @date 2016年9月21日 下午3:26:02
	 * @Company:华夏信财投资管理(上海)有限责任公司
	 * @version V1.0
	 */
	public static Date covStrTimeToDate(String date) {
		if(date != null){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date formatedDate;
				formatedDate = sdf.parse(date);
				return formatedDate;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		    
		}
		return null;
	}
	
	public static Date covStrTimeToDate(String date,String format) {
		if(date != null){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date formatedDate;
				formatedDate = sdf.parse(date);
				return formatedDate;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		    
		}
		return null;
	}
	
	public static boolean isLastDay(Date date){
		Calendar b = Calendar.getInstance();
        b.setTime(date);
        int lastDay = b.getActualMaximum(Calendar.DAY_OF_MONTH);
        int now = b.get(Calendar.DAY_OF_MONTH);
        return now == lastDay;
	}
	
	public static Date increase(Date date) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(getDateCurrDate());
		calo.add(Calendar.HOUR, date.getHours());
		calo.add(Calendar.MINUTE, date.getMinutes());
		calo.add(Calendar.SECOND, date.getSeconds());
		return calo.getTime();
	}
	
	public static Date getDateCurrDate(){
		Date time=null;
		try {
			SimpleDateFormat sdfLong = new SimpleDateFormat(FORMAT.DATE_HORIZONTAL);
			time= sdfLong.parse(sdfLong.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 获得指定日期的前一天
	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(String specifiedDay){
		Calendar c = Calendar.getInstance();
		Date date=null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day-1);
		String dayBefore=new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		return dayBefore;
	}
	/**
	 * 获得指定日期的前一天
//	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(Date date,String format){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day-1);
		String dayBefore=new SimpleDateFormat(format).format(c.getTime());
		return dayBefore;
	}
	
	
	public static Date transferLongToDate(Long millSec) {
		Date date = new Date(millSec);
		return date;
	}
	
	
	private void test(){
		System.out.println("ssss");
	    Date ss= com.cui.common.utils.DateUtil.parse("2017-06-29", "yyyy-MM-dd");
	    //剩余天数
		long lastDays = com.cui.common.utils.DateUtil.getIntervalDays(com.cui.common.utils.DateUtil.formatDate(com.cui.common.utils.DateUtil.getNowTime(), "yyyy-MM-dd"),ss);
		System.out.println(lastDays);	
		Date expireTime= com.cui.common.utils.DateUtil.parse("2018-06-26 20:35:48", "yyyy-MM-dd HH:mm:dd");
		boolean s=expireTime.before(com.cui.common.utils.DateUtil.addDay(com.cui.common.utils.DateUtil.getNowTime(), 27));
		System.out.println(s);
	}
	
	public static long getDaySub(Date beginDate,Date endDate) { 
		long day=0; 
			day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000); 
		return day; 
	} 
	
}
