package com.zc.support.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonHelper {

	/**
	 * 判断该字符串是否是Json格式
	 * 
	 * @param json
	 * @return
	 */
	public static boolean isJson(String json) {
		boolean isJson = false;
		try {
			JSONObject.fromObject(json);
			isJson = true;
		} catch (Exception e) {
			// TODO: handle exception
			isJson = false;
		}
		return isJson;
	}

	public static boolean isJsonArray(String json) {
		boolean isJson = false;
		try {
			JSONArray.fromObject(json);
			isJson = true;
		} catch (Exception e) {
			// TODO: handle exception
			isJson = false;
		}
		return isJson;
	}

	public static JSONObject getJSONObject(String json) {

		boolean isJson = false;
		JSONObject jsonObjectIn = null;
		JSONObject jsonObjectOut = null;

		isJson = isJson(json);

		if (isJson) {
			jsonObjectIn = JSONObject.fromObject(json);
			jsonObjectOut = jsonObjectIn;
		}

		return jsonObjectOut;

	}

	public static JSONArray getJSONArray(String json) {

		boolean isJson = false;
		JSONArray jsonObjectIn = null;
		JSONArray jsonObjectOut = null;

		isJson = isJsonArray(json);

		if (isJson) {
			jsonObjectIn = JSONArray.fromObject(json);
			jsonObjectOut = jsonObjectIn;
		}

		return jsonObjectOut;

	}

	public static boolean haveKey(String json, String key) {

		boolean isJson = false;
		boolean haveKey = false;
		JSONObject jsonObjectIn = null;

		isJson = isJson(json);

		if (isJson) {
			jsonObjectIn = getJSONObject(json);
			try {
				jsonObjectIn.get(key);
				haveKey = true;
			} catch (Exception e) {
				// TODO: handle exception
				haveKey = false;
			}
		}

		return haveKey;
	}

	public static boolean haveObjectKey(String json, String key) {

		boolean isJson = false;
		boolean haveKey = false;
		JSONObject jsonObjectIn = null;

		isJson = isJson(json);

		if (isJson) {
			jsonObjectIn = getJSONObject(json);
			try {
				jsonObjectIn.getJSONObject(key);
				haveKey = true;
			} catch (Exception e) {
				// TODO: handle exception
				haveKey = false;
			}
		}

		return haveKey;
	}

	public static boolean haveArrayKey(String json, String key) {

		boolean isJson = false;
		boolean haveKey = false;
		JSONObject jsonObjectIn = null;

		isJson = isJson(json);

		if (isJson) {
			jsonObjectIn = getJSONObject(json);
			try {
				jsonObjectIn.getJSONArray(key);
				haveKey = true;
			} catch (Exception e) {
				// TODO: handle exception
				haveKey = false;
			}
		}

		return haveKey;
	}

	public static JSONObject getJSONObject(String json, String key) {

		boolean isJson = false;
		JSONObject jsonObjectIn = null;
		JSONObject jsonObjectOut = null;

		try {
			jsonObjectIn = JSONObject.fromObject(json);
			isJson = true;
		} catch (Exception e) {
			// TODO: handle exception
			isJson = false;
		}
		if (isJson) {
			jsonObjectOut = jsonObjectIn.getJSONObject(key);
		}
		return jsonObjectOut;
	}

	public static JSONArray getJSONArray(String json, String key) {
		JSONObject jsonObjectIn = null;
		JSONArray jsonArrayOut = null;
		boolean isJson = false;
		try {
			jsonObjectIn = JSONObject.fromObject(json);
			isJson = true;
		} catch (Exception e) {
			// TODO: handle exception
			isJson = false;
		}
		if (isJson) {
			jsonArrayOut = jsonObjectIn.getJSONArray(key);
		}
		return jsonArrayOut;
	}

	public static String getString(String json, String key) {
		JSONObject jsonObjectIn = null;
		String stringOut = null;
		boolean isJson = false;
		try {
			jsonObjectIn = JSONObject.fromObject(json);
			isJson = true;
		} catch (Exception e) {
			// TODO: handle exception
			isJson = false;
		}
		if (isJson) {
			stringOut = jsonObjectIn.getString(key);
		}
		return stringOut;
	}

}
