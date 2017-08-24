package com.zc.web.support.link;

import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zc.web.support.service.Log;

public class ZCReqManager {

	/**
	 * 打印请求中的所有Header
	 * 
	 */
	public static void showHeaders(String reqName, URLConnection connection) {

		Map<String, List<String>> map = connection.getHeaderFields();

		Log.Pro.start();
		Log.Pro.whiteLine(reqName + " " + "Header");
		Log.Pro.whiteCut();
		for (String key : map.keySet()) {
			Log.Pro.whiteLine(key + "--->" + map.get(key));
		}
		Log.Pro.finish();

	}

	/**
	 * 打印请求中的所有Header
	 * 
	 */
	public static Map<String, String> showHeaders(String reqName, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		Log.Pro.start();
		Log.Pro.whiteLine(reqName + " " + "Header");
		Log.Pro.whiteCut();
		for (String key : map.keySet()) {
			Log.Pro.whiteLine(key + "--->" + map.get(key));
		}
		Log.Pro.finish();

		return map;
	}

	/**
	 * 打印请求中的所有Param
	 * 
	 */
	public static Map<String, String> showParams(String reqName, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramKey = (String) paramNames.nextElement();
			String paramValue = ZCReqParamGetter.getParamString(request, paramKey, false);
			map.put(paramKey, paramValue);
		}

		Log.Pro.start();
		Log.Pro.whiteLine(reqName + " " + "Params");
		Log.Pro.whiteCut();
		for (String key : map.keySet()) {
			Log.Pro.whiteLine(key + "--->" + map.get(key));
		}
		Log.Pro.finish();

		Log.Nano.tag("ip",getIpAddress(request));
		
		return map;
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
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
