package com.zc.web.base.service;

import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class CCReqManager {
	/**
	 * ��ӡ�����е�����header
	 * 
	 * @param request
	 * @return
	 */
	public static void showHeaders(String reqName, URLConnection connection) {

		Map<String, List<String>> map = connection.getHeaderFields();

		Log.Pro.start();
		Log.Pro.whiteLine(reqName + " �� " + " header");
		Log.Pro.whiteCut();
		for (String key : map.keySet()) {
			Log.Pro.whiteLine(key + "--->" + map.get(key));
		}
		Log.Pro.finish();

	}

	/**
	 * ��ӡ�����е������ύ����
	 * 
	 * @param request
	 * @return
	 */
	public static void showParams(HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues != null) {
				if (paramValues.length <= 1) {
					String paramValue = paramValues[0];
					if (paramValue.length() != 0) {
						map.put(paramName, paramValue);
					}
				} else {
					String paramValue = "";
					for (String temp_paramValue : paramValues) {
						paramValue += temp_paramValue + " , ";
					}
					if (paramValue.length() != 0) {
						map.put(paramName, paramValue);
					}
				}
			}
		}

		Set<Map.Entry<String, String>> set = map.entrySet();

		Log.Pro.start();
		Log.Pro.whiteLine("�������");
		Log.Pro.whiteCut();
		for (Map.Entry<String, String> entry : set) {
			Log.Pro.whiteLine(entry.getKey() + ":" + entry.getValue());
		}
		Log.Pro.finish();

	}

	/**
	 * ��ȡ�û���ʵIP��ַ����ʹ��request.getRemoteAddr();��ԭ�����п����û�ʹ���˴��������ʽ������ʵIP��ַ,
	 * 
	 * ���ǣ����ͨ���˶༶�������Ļ���X-Forwarded-For��ֵ����ֹһ��������һ��IPֵ�������ĸ������������û��˵���ʵIP�أ�
	 * ����ȡX-Forwarded-For�е�һ����unknown����ЧIP�ַ�����
	 * 
	 * �磺X-Forwarded-For��192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 
	 * �û���ʵIPΪ�� 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
