package com.zc.support.service;

import net.sf.json.JSONObject;

public class TinyJson {

	public static boolean isJson(String JsonString) {
		boolean isJson = false;
		try {
			JSONObject.fromObject(JsonString);
			isJson = true;
		} catch (Exception e) {
			// TODO: handle exception
			isJson = false;
		}
		return isJson;
	}

	public static JSONObject getJSONObject(String jsonString, String key) {
		JSONObject jsonObjectIn;
		JSONObject jsonObjectOut;
		try {
			jsonObjectIn = JSONObject.fromObject(jsonString);
			jsonObjectOut = jsonObjectIn.getJSONObject(key);
		} catch (Exception e) {
			// TODO: handle exception
			jsonObjectOut = null;
		}
		return jsonObjectOut;
	}

	public static JSONObject getJSONObject(JSONObject jsonObject, String key) {
		JSONObject jsonObjectOut;
		try {
			jsonObjectOut = jsonObject.getJSONObject(key);
		} catch (Exception e) {
			// TODO: handle exception
			jsonObjectOut = null;
		}
		return jsonObjectOut;
	}
}
