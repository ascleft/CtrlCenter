package com.zc.support.link;

import java.net.URLConnection;
import java.util.HashMap;

public class ZCHttpReqProperty {

	private HashMap<String, String> map = null;

	public static enum Terminal {
		PC, iPhone, Android, WeChat
	}

	public static enum BaseProperty {
		TYPE_1, TYPE_2
	}

	public ZCHttpReqProperty() {
		init();
	}

	private void init() {
		map = new HashMap<String, String>();
	}

	/**
	 * 清空参数信息
	 * 
	 * */
	public void wipeProperty() {
		map = new HashMap<String, String>();
	}

	/**
	 * 获取拼接好的参数，采取典型的&拼接方式
	 * 
	 **/
	public void addProperty(String key, String value) {
		if (null == map) {
			init();
		}
		map.put(key, value);
	}

	public void importBase(BaseProperty type) {
		switch (type) {
		case TYPE_1:
			addProperty("accept", "*/*");
			addProperty("connection", "Keep-Alive");
			break;
		case TYPE_2:
			addProperty("accept", "*/*");
			addProperty("connection", "Keep-Alive");
			break;
		default:
			addProperty("accept", "*/*");
			addProperty("connection", "Keep-Alive");
		}
	}

	public void importUserAgent(Terminal type) {
		switch (type) {
		case PC:
			addProperty("user-agent", //
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0");
			break;
		case iPhone:
			addProperty("user-agent", //
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.2924.87 Safari/537.36 LBBROWSER");
			break;
		case Android:
			addProperty("user-agent",//
					"Mozilla/5.0 (Linux; U; Android 7.1.2; zh-CN; 2014811 Build/NJH47F) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/40.0.2214.89 Quark/2.0.4.956 Mobile Safari/537.36");
			break;
		case WeChat:
			addProperty("user-agent",//
					"Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_3 like Mac OS X) AppleWebKit/603.3.8 (KHTML, like Gecko) Mobile/14G60 MicroMessenger/6.5.15 NetType/WIFI Language/zh_CN");
			break;
		default:
			addProperty("user-agent", // 默认PC
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0");
		}
	}

	/**
	 * 将Headers采用setRequestProperty()函数添加到URLConnection中
	 * 
	 **/

	public URLConnection setConnPropertys(URLConnection conn) {
		for (String key : map.keySet()) {
			String value = map.get(key);
			conn.setRequestProperty(key, value);
		}
		return conn;
	}

}
