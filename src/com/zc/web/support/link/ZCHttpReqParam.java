package com.zc.web.support.link;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class ZCHttpReqParam {

	private HashMap<String, String> map = null;

	public ZCHttpReqParam() {

		init();

	}

	private void init() {

		map = new HashMap<String, String>();

	}

	/**
	 * 清空参数信息
	 *
	 * */
	public void wipeParam() {

		map = new HashMap<String, String>();

	}

	/**
	 * 获取拼接好的参数，采取典型的&拼接方式
	 *
	 * */
	public void addParam(String key, String value) {

		if (null == map) {
			init();
		}
		map.put(Encode(key), Encode(value));

	}

	/**
	 * 获取拼接好的参数，采取典型的&拼接方式，POST正文和GET后缀均可使用
	 *
	 * */
	public String getParam() {

		String params = "";

		for (String obj : map.keySet()) {
			String value = map.get(obj);
			params += obj + "=" + value + "&";
		}

		return params;

	}

	// 私有编码方法，在add方法中即被调用
	private static String Encode(String input) {

		String output = null;

		try {
			output = URLEncoder.encode(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output = "Encode err";
		}

		return output;

	}

	// 私有解码方法，未启用
	// private static String Decode(String input) {
	//
	// String output = null;
	//
	// try {
	// output = URLDecoder.decode(input, "UTF-8");
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// output = "Decode err";
	// }
	//
	// return output;
	// }
}
