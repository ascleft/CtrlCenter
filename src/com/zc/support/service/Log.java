package com.zc.support.service;

public class Log {

	public static int STYLE_CLOSE = 1, STYLE_OPEN = 2;
	protected static boolean debug = true;
	protected static int style = 1;
	private static int lineLen = 100;

	public static void init(boolean debug, int STYLE) {
		Log.debug = debug;
		style = STYLE;

	}

	public static String checkInput(Object inputs) {

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
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < inputs.length; i++) {
				String string = null;
				string = checkInput(inputs[i]);
				sb.append(string);
				sb.append(" ");
			}

			printlnWithLog(TimeHelper.getTimeHMSS());
			logHead();
			logMsg(sb.toString());
			logFoot();

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

	private static void logHead() {

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

		printlnWithLog(line);

	}

	private static void logCut() {

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
		printlnWithLog(line);

	}

	private static void logMsg(String s) {

		

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

			printlnWithLog(line);
		}


	}

	private static void logFoot() {
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

		printlnWithLog(line);
		
	}

	public static class Nano {
		synchronized public static void tag(Object... inputs) {

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

				printlnWithLog(TimeHelper.getTimeHMSS());
				logHead();
				logMsg(sb_tag.toString());
				logCut();
				logMsg(sb_msg.toString());
				logFoot();

			}
		}

		synchronized public static void byLine(Object... inputs) {

			if (debug) {

				printlnWithLog(TimeHelper.getTimeHMSS());
				logHead();

				for (int i = 0; i < inputs.length; i++) {
					String string = null;
					string = checkInput(inputs[i]);
					logMsg(string);
				}

				logFoot();
			}

		}

		synchronized public static void TagByLine(Object... inputs) {

			if (debug) {

				printlnWithLog(TimeHelper.getTimeHMSS());
				logHead();

				for (int i = 0; i < inputs.length; i++) {

					if (i == 0) {
						String string = null;
						string = checkInput(inputs[i]);

						logMsg(string);
						logCut();

					} else {
						String string = null;
						string = checkInput(inputs[i]);

						logMsg(string);
					}

				}

				logFoot();

			}
		}
	}

	public static class Pro {
		synchronized public static void start() {
			logHead();
		}

		synchronized public static void whiteCut() {
			logCut();
		}

		synchronized public static void whiteLine(Object input) {
			String string = null;
			string = checkInput(input);
			logMsg(string);
		}

		synchronized public static void finish() {
			logFoot();
		}
	}
	
	public static void printlnWithLog(String line){
		System.out.println(line);
		TextLogHelper.white(line);
	}

}
