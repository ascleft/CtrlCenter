package com.zc.support.service;

import java.util.ArrayList;

public class LogHelper {

	private static ArrayList<String> infos = null;

	public static ArrayList<String> init() {

		Log.init(true, Log.STYLE_OPEN);

		infos = new ArrayList<String>();
		infos.add("Log.debug" + " : " + Log.debug);
		infos.add("Log.style" + " : " + Log.style);

		return infos;
	}

	public static ArrayList<String> getState() {
		return infos;
	}

	public static void showState() {
		Log.Pro.start();
		for (String info : infos) {
			Log.Pro.whiteLine(info);
		}
		Log.Pro.finish();
	}

}
