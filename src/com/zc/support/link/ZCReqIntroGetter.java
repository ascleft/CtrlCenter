package com.zc.support.link;

import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.service.Log;
import com.zc.support.service.MapHelper;
import com.zc.support.service.StringHelper;

public class ZCReqIntroGetter {

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
		Map<String, String> map = new TreeMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		map = MapHelper.sortMapByKey(map);

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
		Map<String, String> map = new TreeMap<String, String>();
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

		map = MapHelper.sortMapByKey(map);

		Log.Pro.start();
		Log.Pro.whiteLine(reqName + " " + "Params");
		Log.Pro.whiteCut();
		for (String key : map.keySet()) {
			Log.Pro.whiteLine(StringHelper.fillRight(key, 20, "-") + "--->" + map.get(key));
		}
		Log.Pro.finish();

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

	/**
	 * 获取终端设备类型
	 * 
	 */
	public Map<String, String> getUserAgent(HttpServletRequest req) {
		Map<String, String> Sys = new HashMap<String, String>();
		String ua = req.getHeader("User-Agent").toLowerCase();
		String s;
		String msieP = "msie ([\\d.]+)";
		String firefoxP = "firefox\\/([\\d.]+)";
		String chromeP = "chrome\\/([\\d.]+)";
		String operaP = "opera.([\\d.]+)/)";
		String safariP = "version\\/([\\d.]+).*safari";

		Pattern pattern = Pattern.compile(msieP);
		Matcher mat = pattern.matcher(ua);
		if (mat.find()) {
			s = mat.group();
			Sys.put("type", "ie");
			Sys.put("version", s.split(" ")[1]);
			return Sys;
		}
		pattern = Pattern.compile(firefoxP);
		mat = pattern.matcher(ua);
		if (mat.find()) {
			s = mat.group();
			System.out.println(s);
			Sys.put("type", "firefox");
			Sys.put("version", s.split("/")[1]);
			return Sys;
		}
		pattern = Pattern.compile(chromeP);
		mat = pattern.matcher(ua);
		if (mat.find()) {
			s = mat.group();
			Sys.put("type", "chrome");
			Sys.put("version", s.split("/")[1]);
			return Sys;
		}
		pattern = Pattern.compile(operaP);
		mat = pattern.matcher(ua);
		if (mat.find()) {
			s = mat.group();
			Sys.put("type", "opera");
			Sys.put("version", s.split("\\.")[1]);
			return Sys;
		}
		pattern = Pattern.compile(safariP);
		mat = pattern.matcher(ua);
		if (mat.find()) {
			s = mat.group();
			Sys.put("type", "safari");
			Sys.put("version", s.split("/")[1].split(".")[0]);
			return Sys;
		}
		return Sys;
	}

}
