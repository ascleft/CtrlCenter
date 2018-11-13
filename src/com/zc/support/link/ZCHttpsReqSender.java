package com.zc.support.link;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.zc.support.service.StringHelper;
import com.zc.support.service.TextLogHelper;
import com.zc.support.service.TimeHelper;

import sun.net.www.protocol.https.Handler;

@SuppressWarnings("restriction")

public class ZCHttpsReqSender {
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
		System.out.println(StringHelper.fillRightMIX("url ", 6, " ") + url + "?" + param.getParam());
		TextLogHelper.white(TimeHelper.getTimeHMSS());
		TextLogHelper.white(StringHelper.fillRightMIX("url ", 6, " ") + url + "?" + param.getParam());

		String result = "";
		BufferedReader in = null;
		try {
			// 创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("SSL");
			TrustManager[] tm = { new ZCDefaultX509TrustManager() };
			// 初始化
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 获取SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			// 创建URL
			URL realUrl = new URL(null, url + "?" + param.getParam(), new Handler());

			// 打开和URL之间的连接
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();

			// 填充Header
			property.setConnPropertys(conn);

			// 禁用连接缓存
			conn.setUseCaches(false);
			// 指定连接类型为POST
			conn.setRequestMethod("GET");

			// 设置当前实例使用的SSLSoctetFactory
			conn.setSSLSocketFactory(ssf);
			conn.connect();

			// 获取所有响应头字段
			// ZCReqIntroGetter.showHeaders("HTTPS-GET", conn);

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
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

		timer.stop("HTTPS-GET通讯 " + url);

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
		System.out.println(StringHelper.fillRightMIX("url ", 6, " ") + url);
		System.out.println(StringHelper.fillRightMIX("body", 6, " ") + url + param.getParam());
		TextLogHelper.white(TimeHelper.getTimeHMSS());
		TextLogHelper.white(StringHelper.fillRightMIX("url ", 6, " ") + url);
		TextLogHelper.white(StringHelper.fillRightMIX("body", 6, " ") + url + param.getParam());

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			// 创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("SSL");
			TrustManager[] tm = { new ZCDefaultX509TrustManager() };
			// 初始化SSLContext
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 获取SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			// 创建URL
			URL realUrl = new URL(null, url, new Handler());

			// 打开和URL之间的连接
			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();

			// 填充Header
			property.setConnPropertys(conn);

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 禁用连接缓存
			conn.setUseCaches(false);
			// 指定连接类型为POST
			conn.setRequestMethod("POST");
			// 设置当前实例使用的SSLSoctetFactory
			conn.setSSLSocketFactory(ssf);
			conn.connect();

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

			// 获取所有响应头字段
			ZCReqIntroGetter.showHeaders("HTTPS-POST", conn);

		} catch (Exception e) {
			System.out.println("发送 POST请求出现异常！" + e);
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

		timer.stop("HTTPS-POST通讯 " + url);

		return result;

	}

	private static class ZCDefaultX509TrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			// TODO Auto-generated method stub

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
	}

}