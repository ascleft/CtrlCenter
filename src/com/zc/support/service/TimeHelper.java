package com.zc.support.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	 * 将时间戳转换为时间 "yyyy-MM-dd HH:mm:ss.S"
	 */
	public static String getTime(String dateFormat) {

		SimpleDateFormat myFmt1 = new SimpleDateFormat(dateFormat);
		Date now = new Date();
		String time = myFmt1.format(now);

		return time;
	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String getTimeH() {

		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH");
		Date now = new Date();
		String time = myFmt1.format(now);

		return time;
	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String getTimeHM() {

		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date now = new Date();
		String time = myFmt1.format(now);

		return time;
	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String getTimeHMS() {

		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String time = myFmt1.format(now);

		return time;
	}

	/*
	 * 将时间戳转换为时间
	 */

	public static String getTimeHMSS() {

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

	public static class Timer {

		long interval = 0;

		Date time_start = null;
		Date time_stop = null;

		String time_start_stamp = "";
		String time_stop_stamp = "";

		ArrayList<String> pointList = null;

		public Timer() {

			start();

		}

		public void start() {

			time_start = new Date();
			time_start_stamp = TimeHelper.getTimeHMSS();

			pointList = new ArrayList<String>();

		}

		public void addPoint() {

			if (null == pointList) {
				pointList = new ArrayList<String>();
			}

			time_start = new Date();
			time_start_stamp = TimeHelper.getTimeHMSS();

		}

		public long stop(String name) {

			return stop(name, TextLogHelper.Type.UNDEFINED);

		}

		public long stop(String name, String[][] logType) {

			LogSyncSafe.Pro log = new LogSyncSafe.Pro();
			log.addStart(true);
			log.addMsgLine("计时器统计 ");
			log.addMsgLine(name);
			log.addCut();
			log.addMsgLines(getTimerPartableList());
			log.addfinish();
			log.flush(logType);

			return interval;

		}

		public ArrayList<String> getTimerPartableList() {

			ArrayList<String> list = new ArrayList<String>();

			time_stop = new Date();
			time_stop_stamp = TimeHelper.getTimeHMSS();

			if (null == time_start) {
				list.add("计时器未启动，请按顺序依次调用 start() stop() 方法");
				return list;
			} else {

				interval = time_stop.getTime() - time_start.getTime();

				list.add("开始时间" + ": " + time_start_stamp);
				if (null != pointList || pointList.size() > 0) {
					for (int i = 0; i < pointList.size(); i++) {
						list.add("第" + (i + 1) + "次" + ": " + pointList.get(i));
					}
				}
				list.add("结束时间" + ": " + time_stop_stamp);
				list.add("总时长" + interval / 1000 + "秒" + "(" + interval + "毫秒)");

				if (interval < 5000) {

				} else if (5000 < interval && interval < 10000) {
					list.add("TimeOut Warning 5 " + interval / 1000 + "秒" + "(" + interval + "毫秒)");
				} else if (10000 < interval && interval < 15000) {
					list.add("TimeOut Warning 5 ");
					list.add("TimeOut Warning 10 " + interval / 1000 + "秒" + "(" + interval + "毫秒)");
				} else if (15000 < interval && interval < 20000) {
					list.add("TimeOut Warning 5 ");
					list.add("TimeOut Warning 10 ");
					list.add("TimeOut Warning 15 " + interval / 1000 + "秒" + "(" + interval + "毫秒)");
				} else {
					list.add("TimeOut Warning 5 ");
					list.add("TimeOut Warning 10 ");
					list.add("TimeOut Warning 15 ");
					list.add("TimeOut Warning 20 " + interval / 1000 + "秒" + "(" + interval + "毫秒)");
				}

				return list;
			}

		}

	}

}
