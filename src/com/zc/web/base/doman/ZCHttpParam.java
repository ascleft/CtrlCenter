package com.zc.web.base.doman;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class ZCHttpParam {

	private HashMap<String, String> map = null;

	public ZCHttpParam() {

		init();

	}

	private void init() {

		map = new HashMap<String, String>();

	}

	/**
	 * ��ղ�����Ϣ
	 * 
	 * */
	public void wipeParam() {

		map = new HashMap<String, String>();

	}

	/**
	 * ��ȡƴ�ӺõĲ�������ȡ���͵�&ƴ�ӷ�ʽ
	 * 
	 * */
	public void addParam(String key, String value) {

		if (null == map) {
			init();
		}
		map.put(Encode(key), Encode(value));

	}

	/**
	 * ��ȡƴ�ӺõĲ�������ȡ���͵�&ƴ�ӷ�ʽ��POST���ĺ�GET��׺����ʹ��
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

	// ˽�б��뷽������add�����м�������
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

	// ˽�н��뷽����δ����
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
