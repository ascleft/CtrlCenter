package com.zc.support.link;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import com.zc.support.service.StringHelper;
import com.zc.support.service.TextLogHelper;
import com.zc.support.service.TimeHelper;

public class ZCHttpReqSender {
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, ZCHttpReqParam param) {
		ZCHttpReqProperty property = new ZCHttpReqProperty();
		property.importBase(ZCHttpReqProperty.BaseProperty.TYPE_1);
		property.importUserAgent(ZCHttpReqProperty.Terminal.PC);

		return sendGet(url, param, property);
	}

	public static String sendGet(String url, ZCHttpReqParam param, ZCHttpReqProperty property) {

		TimeHelper.Timer timer = new TimeHelper.Timer();

		System.out.println(TimeHelper.getTimeHMSS());
		System.out.println(StringHelper.fillRight("url ", 6, " ") + url + "?" + param.getParam());
		TextLogHelper.white(TimeHelper.getTimeHMSS());
		TextLogHelper.white(StringHelper.fillRight("url ", 6, " ") + url + "?" + param.getParam());

		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param.getParam();
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 填充Header
			property.setConnPropertys(connection);
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			// CCReqManager.showHeaders("GET", connection);
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！");
			System.out.println(e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		timer.stop("GET通讯 " + url);

		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, ZCHttpReqParam param) {
		ZCHttpReqProperty property = new ZCHttpReqProperty();
		property.importBase(ZCHttpReqProperty.BaseProperty.TYPE_1);
		property.importUserAgent(ZCHttpReqProperty.Terminal.PC);

		return sendPost(url, param, property);
	}

	public static String sendPost(String url, ZCHttpReqParam param, ZCHttpReqProperty property) {

		TimeHelper.Timer timer = new TimeHelper.Timer();

		System.out.println(TimeHelper.getTimeHMSS());
		System.out.println(StringHelper.fillRight("url ", 6, " ") + url);
		System.out.println(StringHelper.fillRight("body", 6, " ") + param.getParam());
		TextLogHelper.white(TimeHelper.getTimeHMSS());
		TextLogHelper.white(StringHelper.fillRight("url ", 6, " ") + url);
		TextLogHelper.white(StringHelper.fillRight("body", 6, " ") + param.getParam());

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 填充Header
			property.setConnPropertys(conn);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param.getParam());
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST请求出现异常！");
			System.out.println(e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		timer.stop("POST通讯 " + url);

		return result;

	}

}