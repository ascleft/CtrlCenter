package com.zc.web.base.doman;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zc.web.base.service.DBConfigHelper;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.ReqParamGatter;

public class ZCBaseActionSupport extends ActionSupport {

	private static final long serialVersionUID = 10086L;

	public HttpServletResponse response;
	public HttpServletRequest request;

	public HttpSession session;

	private JSONObject result;
	public String ERRCODE;
	public String ERRDESC;
	public String data;

	public void init(boolean allowCORS) {
		response = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		if (allowCORS) {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST,GET");
			response.setContentType("text/html;charset=utf-8");
		}

		session = request.getSession();
		session.setMaxInactiveInterval(30 * 60);
	}

	public int getReqParamInt(String key) {
		int return_value = ReqParamGatter.getParamInt(request, key);
		return return_value;
	}

	public int[] getReqParamInts(String key) {
		int[] return_value = ReqParamGatter.getParamInts(request, key);
		return return_value;
	}

	public double getReqParamDouble(String key) {
		double return_value = ReqParamGatter.getParamDouble(request, key);
		return return_value;
	}

	public double[] getReqParamDoubles(String key) {
		double[] return_value = ReqParamGatter.getParamDoubles(request, key);
		return return_value;
	}

	public String getReqParamString(String key) {
		String return_value = ReqParamGatter.getParamString(request, key);
		return return_value;
	}

	public String[] getReqParamStrings(String key) {
		String[] return_value = ReqParamGatter.getParamStrings(request, key);
		return return_value;
	}

	public void writeResp(String tab) {
		writeResp();
		Log.Nano.tag(tab, result.toString());
	}

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

			String url = DBConfigHelper.URL;
			String username = DBConfigHelper.NAME;
			String password = DBConfigHelper.PWD;

			DBconn = (Connection) DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			Log.i("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		} catch (SQLException se) {
			Log.i("数据库连接失败！");
			se.printStackTrace();
		}
	}

}
