package com.rezy.dialog.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: 时间工具类
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25
 */
public class DateUtil {

	// 默认时间类型格式(大写H表示24小时制)
	private static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * @Description: 时间转成时间字符串
	 * @param date：时间
	 * @param pattern：时间字符串类型
	 * @return
	 */
	public static String dateFormat(Date date, String pattern) {
		if (pattern == null) {
			pattern = PATTERN_DEFAULT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * @Description: 字符串解析成时间
	 * @param dateTimeString：字符串
	 * @param pattern：字符串对应的时间格式
	 * @return
	 * @throws ParseException
	 */
	public static Date dateParse(String dateTimeString, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateTimeString);
	}

	/**
	 * @Description: 指定日期是一周第几天(星期日-星期六分别表示一周第1-7天)
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
}
