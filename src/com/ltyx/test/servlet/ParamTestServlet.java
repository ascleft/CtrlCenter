package com.ltyx.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.zc.web.base.doman.ZCBaseServlet;
import com.zc.web.base.service.CCReqManager;
import com.zc.web.base.service.Log;

public class ParamTestServlet extends ZCBaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ���������������ڼ�����������Ƿ���ȷ�����齫�ڿ���̨�н��������ӡ
	 */

	public void doExeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Log.i("doExeRequest start");

		ERRCODE = 0;

		try {
			System.out.println("servlet---->" + req.getContentType());

			Log.i(req.toString());
			CCReqManager.showParams(req);

		} catch (Exception e) {
			// TODO: handle exception
			Log.i(e.toString());
			ERRCODE = 1;
		}

		switch (ERRCODE) {
		case 0:
			ERRDESC = "�ɹ�";
			break;
		default:
			ERRDESC = "����ʱ�쳣";
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
