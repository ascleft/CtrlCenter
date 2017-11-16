package com.zc.support.doman;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zc.support.config.ConfigHelperDB;
import com.zc.support.link.ZCReqParamGetter;
import com.zc.support.service.Log;
import com.zc.support.service.TimeHelper;

public class ZCBaseActionSupport extends ActionSupport implements ZCImplReqParamGetter {

	private static final long serialVersionUID = 10086L;

	public HttpServletResponse response;
	public HttpServletRequest request;

	public HttpSession session;

	private JSONObject result;
	public String ERRCODE;
	public String ERRDESC;
	public String data;

	public TimeHelper.Timer timer = null;

	/**
	 * 初始化ActionSupport，同时提供跨域支持（CORS）
	 * 
	 */

	public void init(boolean allowCORS) {

		initProgress();

		timer = new TimeHelper.Timer();

		response = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		if (allowCORS) {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST,GET");
			response.setContentType("text/html;charset=utf-8");
		}

		session = request.getSession();
		// session.setMaxInactiveInterval(6 * 60 * 60);

	}

	private ArrayList<String> progressLog = null;

	public void initProgress() {
		progressLog = new ArrayList<String>();
	}

	public void addProgress(String log) {
		progressLog.add(log);
	}

	public void addProgressSucc(String log) {
		progressLog.add(log + "--->" + "成功");
	}

	public void addProgressFail(String log) {
		progressLog.add(log + "--->" + "失败");
	}

	public void logProgress(String title) {
		System.out.println(TimeHelper.getTimeMS());
		Log.Pro.start();
		Log.Pro.whiteLine(title);
		Log.Pro.whiteCut();
		for (String logNow : progressLog) {
			Log.Pro.whiteLine(logNow);
		}
		Log.Pro.finish();
	}

	public void logActionResponse(String title) {
		System.out.println(TimeHelper.getTimeMS());
		Log.Pro.start();
		Log.Pro.whiteLine(title);
		Log.Pro.whiteCut();
		Log.Pro.whiteLine("结束");
		Log.Pro.whiteCut();
		Log.Pro.whiteLine(result.toString());
		Log.Pro.whiteCut();
		timer.showTimerPartable(title);
		Log.Pro.finish();
	}

	/**
	 * 向链接中写入返回数据
	 * 
	 * 支持打印log
	 * 
	 */
	public void writeResp(String tab) {
		writeResp();
		logActionResponse(tab);
		logProgress(tab);
	}

	/**
	 * 向链接中写入返回数据
	 * 
	 */
	public void writeResp() {
		result = new JSONObject();
		result.put("ERRCODE", ERRCODE);
		result.put("ERRDESC", ERRDESC);
		result.put("data", data);
		try {
			PrintWriter out;
			out = response.getWriter();
			out.print(result.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection DBconn = null;

	public void creatDBConn() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = ConfigHelperDB.URL;
			String username = ConfigHelperDB.NAME;
			String password = ConfigHelperDB.PWD;

			DBconn = (Connection) DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			Log.i("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		} catch (SQLException se) {
			Log.i("数据库连接失败！");
			se.printStackTrace();
		}
	}

	@Override
	public int getReqParamInt(String key) {
		int return_value = ZCReqParamGetter.getParamInt(request, key, false);
		return return_value;
	}

	@Override
	public int[] getReqParamInts(String key) {
		int[] return_value = ZCReqParamGetter.getParamInts(request, key, false);
		return return_value;
	}

	@Override
	public double getReqParamDouble(String key) {
		double return_value = ZCReqParamGetter.getParamDouble(request, key, false);
		return return_value;
	}

	@Override
	public double[] getReqParamDoubles(String key) {
		double[] return_value = ZCReqParamGetter.getParamDoubles(request, key, false);
		return return_value;
	}

	@Override
	public String getReqParamString(String key) {
		String return_value = ZCReqParamGetter.getParamString(request, key, false);
		return return_value;
	}

	@Override
	public String getReqParamString(String key, String symbol) {
		String return_value = ZCReqParamGetter.getParamStringWithSymbol(request, key, symbol, true);
		return return_value;
	}

	@Override
	public String[] getReqParamStrings(String key) {
		String[] return_value = ZCReqParamGetter.getParamStrings(request, key, false);
		return return_value;
	}

	@Override
	public int getReqParamIntWithLog(String key) {
		int return_value = ZCReqParamGetter.getParamInt(request, key, true);
		return return_value;
	}

	@Override
	public int[] getReqParamIntsWithLog(String key) {
		int[] return_value = ZCReqParamGetter.getParamInts(request, key, true);
		return return_value;
	}

	@Override
	public double getReqParamDoubleWithLog(String key) {
		double return_value = ZCReqParamGetter.getParamDouble(request, key, true);
		return return_value;
	}

	@Override
	public double[] getReqParamDoublesWithLog(String key) {
		double[] return_value = ZCReqParamGetter.getParamDoubles(request, key, true);
		return return_value;
	}

	@Override
	public String getReqParamStringWithLog(String key) {
		String return_value = ZCReqParamGetter.getParamString(request, key, true);
		return return_value;
	}

	@Override
	public String[] getReqParamStringsWithLog(String key) {
		String[] return_value = ZCReqParamGetter.getParamStrings(request, key, true);
		return return_value;
	}

}
