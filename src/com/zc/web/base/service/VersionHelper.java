package com.zc.web.base.service;

import net.sf.json.JSONObject;

public class VersionHelper {

	private static String CORE;
	private static String NAME;
	private static String VERSION;

	public static void init() {
		CORE = "Seed7改07" + "计算核心";
		NAME = "Seed7" + "数据整合平台";
		VERSION = TimeHelper.getDateYMD() + " " + "beta";
		showVersion();
	}

	public static void showVersion() {
		Log.Nano.TagByLine(NAME, CORE, "version:" + " " + VERSION);
	}

	public static JSONObject getVersionJSON() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", NAME);
		jsonObject.put("version", NAME);

		return jsonObject;
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
