package com.zc.support.service;

import java.util.ArrayList;

public class VersionHelper {

	private static ArrayList<String> infos = null;

	private static String CORE;
	private static String NAME;
	private static String VERSION;

	public static ArrayList<String> init() {
		CORE = "Seed8改05" + "计算核心（通讯支持）";
		NAME = "Seed8" + "数据整合平台";
		VERSION = TimeHelper.getDateYMD() + " " + "beta";

		infos = new ArrayList<String>();
		infos.add("NAME" + " : " + NAME);
		infos.add("CORE" + " : " + CORE);
		infos.add("VERSION" + " : " + VERSION);

		return infos;

	}

	public static ArrayList<String> getState() {
		return infos;
	}

	public static void show() {
		Log.Pro.start();
		for (String info : infos) {
			Log.Pro.whiteLine(info);
		}
		Log.Pro.finish();
	}

	public static String getCORE() {
		return CORE;
	}

	public static String getNAME() {
		return NAME;
	}

	public static String getVERSION() {
		return VERSION;
	}
}
