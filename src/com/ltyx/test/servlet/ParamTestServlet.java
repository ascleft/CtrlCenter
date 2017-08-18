package com.ltyx.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.zc.web.base.doman.ZCBaseServlet;
import com.zc.web.base.service.ZCReqManager;
import com.zc.web.base.service.Log;

public class ParamTestServlet extends ZCBaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 参数过滤器，用于检测请求内容是否正确，详情将在控制台中进行输出打印
	 */

	public void doExeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Log.i("doExeRequest start");

		ERRCODE = 0;

		try {
			System.out.println("servlet---->" + req.getContentType());

			Log.i(req.toString());
			ZCReqManager.showParams(req);

		} catch (Exception e) {
			// TODO: handle exception
			Log.i(e.toString());
			ERRCODE = 1;
		}

		switch (ERRCODE) {
			case 0:
				ERRDESC = "成功";
				break;
			default:
				ERRDESC = "运行时异常";
				break;
		}

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		JsonObject result = new JsonObject();
		result.addProperty("ERRCODE", ERRCODE);
		result.addProperty("ERRDESC", ERRDESC);

		Log.i("result", result.toString());

		Log.i("" + req.getParameterValues("duoxuan")[0]);

		out.print(result.toString());
		out.flush();
		out.close();

		Log.i("doExeRequest get finish");

	}

}
