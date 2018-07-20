package duomi.com.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * 时间处理，时间日志工具类 Created by wb-zhaofugui on 2016/12/27.
 */
public class DateUtils {

	public static final SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat SHORTDATEFORMAT = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 默认日期格式，yyyy-MM-dd
	 */
	public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * 默认时间格式，HH:mm:ss
	 */
	public static String DEFAULT_TIME_PATTERN = "HH:mm:ss";

	/**
	 * 默认日期时间格式，yyyy-MM-dd HH:mm:ss
	 */
	public static String DEFAULT_DATETIME_PATTERN = DEFAULT_DATE_PATTERN + " " + DEFAULT_TIME_PATTERN;
	/**
	 * 默认日期时间格式，yyyyMMdd
	 */
	public static String DEFAULT_YEAR_PATTERN = "yyyyMMdd";

	/**
	 * 默认日期时间格式，yyyyMMddHHmmss
	 */
	public static final String SIMPLE_DATE_TIME_PATTERN = "yyyyMMddHHmmss";

	/**
	 * 默认日期时间格式，YYYYMM
	 */
	public static final String DEFAULT_MONTH_PATTERN = "yyyyMM";

	/**
	 * 获取YYYYMM格式的日期
	 *
	 * @return 当前系统时间
	 */

	public static final String StringToData(String data) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_TIME_PATTERN);
			return getCurrentDatas(sdf.parse(data));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取yyyy-MM-dd HH:mm:ss格式的日期
	 *
	 * @return 当前系统时间
	 */

	public static final Date StringToDatas(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateFormat(String date, String formateStr) {
		try {
			if (StringUtils.isBlank(formateStr))
				formateStr = DEFAULT_DATE_PATTERN;
			SimpleDateFormat sdf = new SimpleDateFormat(formateStr);
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取yyyyMMddHHmmss格式的日期
	 *
	 * @return 当前系统时间
	 */
	public static final String getCurrentDataTime() {
		Date date = new Date();
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat();
			simpledateformat.applyPattern(DEFAULT_YEAR_PATTERN);
			return simpledateformat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取yyyyMMdd格式的日期
	 *
	 * @return 当前系统时间
	 */
	public static final String getCurrentDataTimes() {
		Date date = new Date();
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat();
			simpledateformat.applyPattern(SIMPLE_DATE_TIME_PATTERN);
			return simpledateformat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取指定格式的日期时间
	 *
	 * @param pattern
	 * @return 指定格式当前系统时间
	 */
	public static final String getCurrentDataTime(String pattern) {
		Date date = new Date();
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat();
			simpledateformat.applyPattern(pattern);
			return simpledateformat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取yyyy-MM-dd HH:mm:ss格式的日期
	 *
	 * @return 当前系统时间
	 */
	public static final String getCurrentData() {
		Date date = new Date();
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat();
			simpledateformat.applyPattern(DEFAULT_DATETIME_PATTERN);
			return simpledateformat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static final String getCurrentDatas(Date date) {
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat();
			simpledateformat.applyPattern(DEFAULT_DATETIME_PATTERN);
			return simpledateformat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取现在时间
	 *
	 * @return 返回数字类型 YYYYMMDD
	 */
	public static int getNowDay() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_YEAR_PATTERN);
		String dateString = formatter.format(currentTime);
		return Integer.parseInt(dateString);
	}

	/**
	 * 获取现在时间月第一天
	 *
	 * @return 返回数字类型 YYYYMMDD
	 */
	public static int getStratDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat(DEFAULT_YEAR_PATTERN);
		// 当前月的第一天
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date beginTime = cal.getTime();
		String beginTime1 = datef.format(beginTime);
		return Integer.parseInt(beginTime1);
	}

	/**
	 * 获取现在时间月最后一天
	 *
	 * @return 返回数字类型 YYYYMMDD
	 */
	public static int getOverDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat(DEFAULT_YEAR_PATTERN);
		// 当前月的最后一天
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		Date endTime = cal.getTime();
		String endTime1 = datef.format(endTime);
		return Integer.parseInt(endTime1);
	}

	/**
	 * 得到本季度开始日
	 *
	 * @return int
	 * @Methods Name getStratDayOfQuarter
	 */
	public static int getStratDayOfQuarter() {
		Date date = new Date();
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(date);
		int curMonth = cDay.get(Calendar.MONTH);
		if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH) {
			cDay.set(Calendar.MONTH, Calendar.JANUARY);
		}
		if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE) {
			cDay.set(Calendar.MONTH, Calendar.APRIL);
		}
		if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {
			cDay.set(Calendar.MONTH, Calendar.JULY);
		}
		if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
			cDay.set(Calendar.MONTH, Calendar.OCTOBER);
		}
		cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
		// 当前季度的第一天
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDay.getTime());
		SimpleDateFormat datef = new SimpleDateFormat(DEFAULT_YEAR_PATTERN);
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date beginTime = cal.getTime();
		String beginTime1 = datef.format(beginTime);
		return Integer.parseInt(beginTime1);
	}

	/**
	 * 得到本季度结束日
	 *
	 * @return int
	 * @Methods Name getOverDayOfQuarter
	 */
	public static int getOverDayOfQuarter() {
		Date date = new Date();
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(date);
		int curMonth = cDay.get(Calendar.MONTH);
		if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH) {
			cDay.set(Calendar.MONTH, Calendar.MARCH);
		}
		if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE) {
			cDay.set(Calendar.MONTH, Calendar.JUNE);
		}
		if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {
			cDay.set(Calendar.MONTH, Calendar.AUGUST);
		}
		if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
			cDay.set(Calendar.MONTH, Calendar.DECEMBER);
		}
		cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat(DEFAULT_YEAR_PATTERN);
		// 当前季度的最后一天
		cal.setTime(cDay.getTime());
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		Date endTime = cal.getTime();
		String endTime1 = datef.format(endTime);
		return Integer.parseInt(endTime1);
	}

	/**
	 * 获取某年第一天日期
	 *
	 * @return Date
	 */
	public static int getStratDayOfYear() {
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		Date currYearFirst = calendar.getTime();
		SimpleDateFormat datef = new SimpleDateFormat(DEFAULT_YEAR_PATTERN);
		String beginTime1 = datef.format(currYearFirst);
		return Integer.parseInt(beginTime1);
	}

	/**
	 * 获取某年最后一天日期
	 *
	 * @return Date
	 */
	public static int getOverDayOfYear() {
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		SimpleDateFormat datef = new SimpleDateFormat(DEFAULT_YEAR_PATTERN);
		String endTime1 = datef.format(currYearLast);
		return Integer.parseInt(endTime1);
	}

	/****
	 * 传入具体日期 ，加减月份。
	 * 
	 * @param date
	 *            日期(2014-04-20,-1)
	 * @return 2014-03-20
	 * @throws ParseException
	 */
	public static Date addMonth(Date date, int num) throws ParseException {
		Calendar rightNow = Calendar.getInstance();
		if (date != null)
			rightNow.setTime(date);

		rightNow.add(Calendar.MONTH, -1);
		Date retDt = rightNow.getTime();

		return retDt;
	}

	/**
	 * 根据日期格式字符串解析日期字符串
	 * 
	 * @param str
	 *            日期字符串
	 * @param parsePatterns
	 *            日期格式字符串
	 * @return 解析后日期
	 * @throws ParseException
	 */
	public static Date parseDate(String str, String parsePatterns) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(parsePatterns);
		return sdf.parse(str);
	}

	/**
	 * 根据单位字段比较两个日期
	 * 
	 * @param date
	 *            日期1
	 * @param otherDate
	 *            日期2
	 * @param withUnit
	 *            单位字段，从Calendar field取值
	 * @return 等于返回0值, 大于返回大于0的值 小于返回小于0的值
	 */
	public static int compareDate(Date date, Date otherDate, int withUnit) {
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		Calendar otherDateCal = Calendar.getInstance();
		otherDateCal.setTime(otherDate);

		switch (withUnit) {
		case Calendar.YEAR:
			dateCal.clear(Calendar.MONTH);
			otherDateCal.clear(Calendar.MONTH);
		case Calendar.MONTH:
			dateCal.set(Calendar.DATE, 1);
			otherDateCal.set(Calendar.DATE, 1);
		case Calendar.DATE:
			dateCal.set(Calendar.HOUR_OF_DAY, 0);
			otherDateCal.set(Calendar.HOUR_OF_DAY, 0);
		case Calendar.HOUR:
			dateCal.clear(Calendar.MINUTE);
			otherDateCal.clear(Calendar.MINUTE);
		case Calendar.MINUTE:
			dateCal.clear(Calendar.SECOND);
			otherDateCal.clear(Calendar.SECOND);
		case Calendar.SECOND:
			dateCal.clear(Calendar.MILLISECOND);
			otherDateCal.clear(Calendar.MILLISECOND);
		case Calendar.MILLISECOND:
			break;
		default:
			throw new IllegalArgumentException("withUnit 单位字段 " + withUnit + " 不合法！！");
		}
		return dateCal.compareTo(otherDateCal);
	}

	/**
	 * 获取前一个月时间 年份+月份 201710
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getLatestMonth() throws Exception {
		Date dttmp = DateUtils.addMonth(new Date(), -1);// 前一个月
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String month = sdf.format(dttmp);
		return month;
	}

	/**
	 * yyyy-MM-dd 当前日期
	 * 
	 */
	public static String getReqDate() {
		return SHORT_DATE_FORMAT.format(new Date());
	}

	public static void main(String[] args) {
		try {
			System.out.println(Math.round(3.6));
			String str = DateUtils.getLatestMonth();
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
