package com.volunteer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class TimeUtil {

	/**
	 * 采用 ThreadLocal 避免 SimpleDateFormat 非线程安全的问题
	 * <p>
	 * Key —— 时间格式 Value —— 解析特定时间格式的 SimpleDateFormat
	 */
	private static ThreadLocal<Map<String, SimpleDateFormat>> sThreadLocal = new ThreadLocal<>();

	/**
	 * 获取解析特定时间格式的 SimpleDateFormat
	 *
	 * @param pattern
	 *            时间格式
	 */
	private static SimpleDateFormat getDateFormat(String pattern) {
		Map<String, SimpleDateFormat> strDateFormatMap = sThreadLocal.get();

		if (strDateFormatMap == null) {
			strDateFormatMap = new HashMap<>();
		}

		SimpleDateFormat simpleDateFormat = strDateFormatMap.get(pattern);
		if (simpleDateFormat == null) {
			simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
			strDateFormatMap.put(pattern, simpleDateFormat);
			sThreadLocal.set(strDateFormatMap);
		}

		return simpleDateFormat;
	}

	/**
	 * 时间格式化
	 *
	 * @param date：要格式化的时间
	 * @param pattern：要格式化的类型
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null || StringUtils.isBlank(pattern)) {
			return null;
		}

		return getDateFormat(pattern).format(date);
	}

	/**
	 * 时间格式化
	 *
	 * @param date：要格式化的时间
	 * @param pattern：要格式化的类型
	 */
	public static Date parseDate(String source, String pattern) {
		if (StringUtils.isBlank(source) || StringUtils.isBlank(pattern)) {
			return null;
		}
		try {
			return getDateFormat(pattern).parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取周第一天
	public Date getStartDayOfWeek(String date) {
		LocalDate now = LocalDate.parse(date);
		return this.getStartDayOfWeek(now);
	}

	public Date getStartDayOfWeek(TemporalAccessor date) {
		TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
		LocalDate localDate = LocalDate.from(date);
		localDate.with(fieldISO, 1);
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	// 获取周最后一天
	public Date getEndDayOfWeek(String date) {
		LocalDate localDate = LocalDate.parse(date);
		return this.getEndDayOfWeek(localDate);
	}

	public Date getEndDayOfWeek(TemporalAccessor date) {
		TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
		LocalDate localDate = LocalDate.from(date);
		localDate.with(fieldISO, 7);
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
	}
}