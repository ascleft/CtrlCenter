package com.ltyx.top.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zc.support.doman.ZCBaseServlet;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.Log;
import com.zc.support.service.TextLogHelper;

public class ParamTestServlet extends ZCBaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 参数过滤器，用于检测请求内容是否正确，详情将在控制台中进行输出打印
	 */

	@Override
	public void doExeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Log.i("doExeRequest start");

		ZCReqIntroGetter.showParams("Servlet测试", request, TextLogHelper.Type.OTHER_UCSPLUS_INTERFACETEST);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "测试结束";

		writeResp();

		Log.i("doExeRequest finish");

	}

}
