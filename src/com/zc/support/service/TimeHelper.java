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
			time_start_stamp = TimeHelper.getTimeMS();

			pointList = new ArrayList<String>();

		}

		public void addPoint() {

			if (null == pointList) {
				pointList = new ArrayList<String>();
			}

			time_start = new Date();
			time_start_stamp = TimeHelper.getTimeMS();

		}

		public long stop(String name) {

			Log.Pro.start();
			Log.Pro.whiteLine("计时器统计 " + "--->" + name);
			Log.Pro.whiteCut();

			interval = showTimerPartable(name);

			Log.Pro.finish();

			return interval;

		}

		public long showTimerPartable(String name) {

			time_stop = new Date();
			time_stop_stamp = TimeHelper.getTimeMS();

			if (null == time_start) {
				Log.i("计时器未启动，请按顺序依次调用 start() stop() 方法");
				return 0;
			} else {
				interval = time_stop.getTime() - time_start.getTime();

				Log.Pro.whiteLine("开始时间" + ": " + time_start_stamp);
				if (null != pointList || pointList.size() > 0) {
					for (int i = 0; i < pointList.size(); i++) {
						Log.Pro.whiteLine("第" + (i + 1) + "次" + ": " + pointList.get(i));
					}
				}
				Log.Pro.whiteLine("结束时间" + ": " + time_stop_stamp);
				Log.Pro.whiteCut();
				Log.Pro.whiteLine("总时长" + interval / 1000 + "秒"//
						+ "(" + interval + "毫秒)");

				return interval;
			}

		}

	}

}
