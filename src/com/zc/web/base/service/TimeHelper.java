package com.zc.web.base.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {

	/*
	 * 将时间戳转换为时间
	 */
	public static String getDate() {

		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String time = myFmt1.format(now);

		return time;
	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String getTime() {

		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String time = myFmt1.format(now);

		return time;
	}

	/*
	 * 将时间戳转换为时间
	 */

	public static String getTimeMS() {

		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date now = new Date();
		String time = myFmt1.format(now);

		return time;
	}

	/*
	 * 将时间戳转换为时间
	 */

	public static String getDateYMD() {

		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
		String time = myFmt1.format(now);

		return time;
	}

	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	/*
	 * linux 转时间戳格式
	 * 
	 * 例 如"yyyy-MM-dd HH:mm:ss"
	 */
	public String TimeStamp2Date(String timestampString, String formats) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat(formats).format(new java.util.Date(timestamp));
		return date;
	}
}
