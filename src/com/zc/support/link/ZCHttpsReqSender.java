package com.zc.support.link;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import sun.net.www.protocol.https.Handler;

import com.zc.support.service.StringHelper;
import com.zc.support.service.TextLogHelper;
import com.zc.support.service.TimeHelper;

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
		System.out.println(StringHelper.fillRight("body", 6, " ") + url + param.getParam());
		TextLogHelper.white(TimeHelper.getTimeHMSS());
		TextLogHelper.white(StringHelper.fillRight("url ", 6, " ") + url);
		TextLogHelper.white(StringHelper.fillRight("body", 6, " ") + url + param.getParam());

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {

			// 创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("SSL");
			TrustManager[] tm = { new MyX509TrustManager() };
			// 初始化
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 获取SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL realUrl = new URL(null, url, new Handler());
			// // 打开和URL之间的连接
			// URLConnection conn = realUrl.openConnection();
			// // 填充Header
			// property.setConnPropertys(conn);
			// // 发送POST请求必须设置如下两行
			// conn.setDoOutput(true);
			// conn.setDoInput(true);

			HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
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

	private static class MyX509TrustManager implements X509TrustManager {
		/*
		 * The default X509TrustManager returned by SunX509. We'll delegate
		 * decisions to it, and fall back to the logic in this class if the
		 * default X509TrustManager doesn't trust it.
		 */
		X509TrustManager sunJSSEX509TrustManager;

		MyX509TrustManager() throws Exception {
			// create a "default" JSSE X509TrustManager.
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream("trustedCerts"), "passphrase".toCharArray());
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
			tmf.init(ks);
			TrustManager tms[] = tmf.getTrustManagers();
			/*
			 * Iterate over the returned trustmanagers, look for an instance of
			 * X509TrustManager. If found, use that as our "default" trust
			 * manager.
			 */
			for (int i = 0; i < tms.length; i++) {
				if (tms[i] instanceof X509TrustManager) {
					sunJSSEX509TrustManager = (X509TrustManager) tms[i];
					return;
				}
			}
			/*
			 * Find some other way to initialize, or else we have to fail the
			 * constructor.
			 */
			throw new Exception("Couldn't initialize");
		}

		/*
		 * Delegate to the default trust manager.
		 */
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			try {
				sunJSSEX509TrustManager.checkClientTrusted(chain, authType);
			} catch (CertificateException excep) {
				// do any special handling here, or rethrow exception.
			}
		}

		/*
		 * Delegate to the default trust manager.
		 */
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			try {
				sunJSSEX509TrustManager.checkServerTrusted(chain, authType);
			} catch (CertificateException excep) {
				/*
				 * Possibly pop up a dialog box asking whether to trust the cert
				 * chain.
				 */
			}
		}

		/*
		 * Merely pass this through.
		 */
		public X509Certificate[] getAcceptedIssuers() {
			return sunJSSEX509TrustManager.getAcceptedIssuers();
		}
	}

	/*
	 * 处理https GET/POST请求 请求地址、请求方法、参数
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = null;
		try {
			// 创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("SSL");
			TrustManager[] tm = { new MyX509TrustManager() };
			// 初始化
			sslContext.init(null, tm, new java.security.SecureRandom());

			// 获取SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(null, requestUrl, new Handler());
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			// 设置当前实例使用的SSLSoctetFactory
			conn.setSSLSocketFactory(ssf);
			conn.connect();

			// 往服务器端写内容
			if (null != outputStr) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}

			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}