package com.zc.support.service;

import java.util.ArrayList;

public class LogSyncSafe {

	public static int STYLE_CLOSE = 1, STYLE_OPEN = 2;
	protected static boolean debug = true;
	protected static int style = 2;
	private static int lineLen = 100;

	public static void init(boolean debug, int STYLE) {
		LogSyncSafe.debug = debug;
		style = STYLE;
	}

	private static String checkInput(Object inputs) {

		String string = null;
		if (null == inputs) {
			string = "none pointer''";
		} else {
			string = inputs.toString();
		}
		if ("".equals(string)) {
			string = "space''";
		}
		if ("".equals(string.trim())) {
			string = string.length() + "space' '";
		}

		return string;

	}

	synchronized public static void i(Object... inputs) {

		if (debug) {

			ArrayList<String> list = new ArrayList<String>();

			StringBuffer logMessage = new StringBuffer();

			for (int i = 0; i < inputs.length; i++) {
				String string = null;
				string = checkInput(inputs[i]);
				logMessage.append(string);
				logMessage.append(" ");
			}

			list.add(TimeHelper.getTimeHMSS());
			list.add(createHead());
			list.addAll(createLogMsg(logMessage.toString()));
			list.add(createFoot());

			flush(list, LogType.NORMAL);

		}
	}

	synchronized public static void err(Object... inputs) {
		if (debug) {
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < inputs.length; i++) {
				String string = null;
				string = checkInput(inputs[i]);
				sb.append(string);
				sb.append(" ");
			}

			System.err.println(TimeHelper.getTimeHMSS());
			System.err.println(sb.toString());

		}
	}

	synchronized private static String createHead() {

		String line = "";

		line += "┌";
		for (int i = 0; i < lineLen - 2 + 6; i++) {
			line += "─";
		}

		if (style == STYLE_CLOSE) {
			line += "┐";
		} else {
			line += "─";
		}

		return line;

	}

	synchronized private static String createCut() {

		String line = "";
		line += "├";
		for (int i = 0; i < lineLen - 2 + 6; i++) {
			line += "─";
		}

		if (style == STYLE_CLOSE) {
			line += "┤";
		} else {
			line += "─";
		}

		return line;

	}

	synchronized private static ArrayList<String> createLogMsg(String s) {

		ArrayList<String> tempList = new ArrayList<String>();

		int msgLen = s.length();

		int lineCont = msgLen / lineLen + 1;
		int lineTail = msgLen % lineLen;

		for (int i = 0; i < lineCont; i++) {
			String line = "";
			line += "│  ";
			if (i == lineCont - 1) {
				line += s.substring(i * lineLen, i * lineLen + lineTail);
				for (int j = 0; j < lineLen - 6 - lineTail + 6; j++) {
					line += " ";
				}
			} else {
				line += s.substring(i * lineLen, (i + 1) * lineLen);
			}

			if (style == STYLE_CLOSE) {
				line += "  │";
			} else {
				line += " ";
			}

			tempList.add(line);

		}

		return tempList;

	}

	synchronized private static String createFoot() {

		String line = "";
		line += "└";

		for (int i = 0; i < lineLen - 2 + 6; i++) {
			line += "─";
		}

		if (style == STYLE_CLOSE) {
			line += "┘";
		} else {
			line += "─";
		}

		return line;

	}

	public static class Nano {

		public ArrayList<String> list = null;

		public Nano() {
			list = new ArrayList<String>();
		}

		public void tag(Object... inputs) {

			if (debug) {

				StringBuffer sb_tag = new StringBuffer();
				StringBuffer sb_msg = new StringBuffer();

				for (int i = 0; i < inputs.length; i++) {

					if (i == 0) {
						String string = null;
						string = checkInput(inputs[i]);
						sb_tag.append(string);
						sb_tag.append(" ");
					} else {
						String string = null;
						string = checkInput(inputs[i]);
						sb_msg.append(string);
						sb_msg.append(" ");
					}

				}

				list.add(TimeHelper.getTimeHMSS());
				list.add(createHead());
				list.addAll(createLogMsg(sb_tag.toString()));
				list.add(createCut());
				list.addAll(createLogMsg(sb_msg.toString()));
				list.add(createFoot());

			}

		}

		public void byLine(Object... inputs) {

			if (debug) {

				ArrayList<String> listTemp = new ArrayList<String>();

				for (int i = 0; i < inputs.length; i++) {
					String string = null;
					string = checkInput(inputs[i]);
					listTemp.addAll(createLogMsg(string));
				}

				list.add(TimeHelper.getTimeHMSS());
				list.add(createHead());
				list.addAll(listTemp);
				list.add(createFoot());

			}

		}

		public void TagByLine(Object... inputs) {

			if (debug) {

				ArrayList<String> listTemp = new ArrayList<String>();

				StringBuffer sb_tag = new StringBuffer();

				for (int i = 0; i < inputs.length; i++) {

					if (i == 0) {
						String string = null;
						string = checkInput(inputs[i]);
						sb_tag.append(string);
						sb_tag.append(" ");
					} else {
						String string = null;
						string = checkInput(inputs[i]);
						listTemp.addAll(createLogMsg(string));
					}

				}

				list.add(TimeHelper.getTimeHMSS());
				list.add(createHead());
				list.addAll(createLogMsg(sb_tag.toString()));
				list.add(createCut());
				list.addAll(listTemp);
				list.add(createFoot());

			}

		}

		public void flush() {
			LogSyncSafe.flush(list, LogType.NORMAL);
		}

		public void flush(String logType) {
			LogSyncSafe.flush(list, logType);
		}
	}

	public static class Pro {

		public ArrayList<String> list = null;

		public Pro() {
			list = new ArrayList<String>();
		}

		public void addStart(boolean withTimestamp) {
			if (withTimestamp) {
				list.add(TimeHelper.getTimeHMSS());
			}
			list.add(createHead());
		}

		public void addCut() {
			list.add(createCut());
		}

		public void addMsgLine(Object input) {
			String string = null;
			string = checkInput(input);
			list.addAll(createLogMsg(string));
		}

		public void addMsgLines(ArrayList<String> input) {
			for (int i = 0; i < input.size(); i++) {
				String string = null;
				string = checkInput(input.get(i));
				list.addAll(createLogMsg(string));
			}
		}

		public void addMsgLineSystemStyle(Object input) {
			String string = null;
			string = checkInput(input);
			list.add(string);
		}

		public void addfinish() {
			list.add(createFoot());
		}

		public void flush() {
			LogSyncSafe.flush(list, LogType.NORMAL);
		}

		public void flush(String logType) {
			LogSyncSafe.flush(list, logType);
		}

	}

	synchronized public static void flush(ArrayList<String> lines, String logType) {

		if (null != lines) {
			for (int i = 0; i < lines.size(); i++) {
				System.out.println(lines.get(i));
				TextLogHelper.white(lines.get(i), logType);
			}
		}

	}

}
