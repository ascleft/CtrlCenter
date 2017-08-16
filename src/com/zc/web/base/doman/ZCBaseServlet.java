package com.zc.web.base.doman;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zc.web.base.service.Log;

public abstract class ZCBaseServlet extends HttpServlet {
	/**
	 * 鲁泰优纤 基础 HttpServlet类
	 */
	private static final long serialVersionUID = 1L;

	public String CLASSNAME = "基础Servlet";

	public int ERRCODE = 0;
	public String ERRDESC = "";

	public ZCBaseServlet() {
		CLASSNAME = "no name";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Log.i(CLASSNAME, getClass().toString(), "get start");

		doExeRequest(req, resp);

		Log.i(CLASSNAME, getClass().toString(), "get finish");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Log.i(CLASSNAME, getClass().toString(), "post start");

		doExeRequest(req, resp);

		Log.i(CLASSNAME, getClass().toString(), "post finish");
	}

	public abstract void doExeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
