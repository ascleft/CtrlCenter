package com.ltyx.test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zc.web.base.doman.ZCBaseServlet;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.ZCReqManager;

public class ParamTestServlet extends ZCBaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 参数过滤器，用于检测请求内容是否正确，详情将在控制台中进行输出打印
	 */

	@Override
	public void doExeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Log.i("doExeRequest start");

		ZCReqManager.showParams("Servlet测试", request);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "测试结束";

		writeResp();

		Log.i("doExeRequest finish");

	}

}
