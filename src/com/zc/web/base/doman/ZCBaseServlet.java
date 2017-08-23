package com.zc.web.base.doman;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.zc.web.base.service.Log;
import com.zc.web.base.service.ReqParamGatter;

public abstract class ZCBaseServlet extends HttpServlet {
	/**
	 * 鲁泰优纤 基础 HttpServlet类
	 */
	private static final long serialVersionUID = 1L;

	public HttpServletResponse response;
	public HttpServletRequest request;

	public HttpSession session;

	private JSONObject result;
	public String ERRCODE;
	public String ERRDESC;
	public String data;

	public String CLASSNAME = "基础Servlet";

	public ZCBaseServlet() {
		CLASSNAME = "no name";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Log.i("Servlet", getClass().toString(), "get start");

		init(req, resp);

		doExeRequest(req, resp);

		Log.i("Servlet", getClass().toString(), "get finish");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Log.i("Servlet", getClass().toString(), "post start");

		init(req, resp);

		doExeRequest(req, resp);

		Log.i("Servlet", getClass().toString(), "post finish");
	}

	private void init(HttpServletRequest req, HttpServletResponse resp) {

		initReqResp(req, resp);
		initSession(30);
		initCORS(true);

	}

	private void initReqResp(HttpServletRequest req, HttpServletResponse resp) {
		response = resp;
		request = req;

	}

	private void initSession(int miminute) {

		session = request.getSession();
		session.setMaxInactiveInterval(miminute * 60);
	}

	private void initCORS(boolean allowCORS) {
		if (allowCORS) {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST,GET");
			response.setContentType("text/html;charset=utf-8");
		}

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
		Log.Nano.tag("data", data);
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

	public abstract void doExeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
