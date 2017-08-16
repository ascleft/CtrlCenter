package com.ltyx.tailor.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zc.web.base.service.Log;

public class SimulatorAction extends ActionSupport {

	/**
	 * ��Action���ڻ�ȡ��д��
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String login() {

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
		response.setContentType("text/html;charset=utf-8");

		String name = request.getParameter("name");

		try {
			PrintWriter out;
			out = response.getWriter();
			JsonObject result = new JsonObject();
			JsonObject data = new JsonObject();

			if (!"�谲��Adrian".equals(name)) {

				JsonObject user = new JsonObject();
				user.addProperty("ec_user_name", "�谲��Adrian�ͻ�����");
				user.addProperty("ec_user_id", "100");
				user.addProperty("ec_user_rank", "0");

				data.add("user", user);

				result.addProperty("ERRCODE", "0");
				result.addProperty("ERRDESC", "succ");
				result.add("data", data);

			} else if ("�谲��Adria".equals(name)) {

				JsonObject user = new JsonObject();
				user.addProperty("ec_user_name", "�谲��Adrian���ƹ���");
				user.addProperty("ec_user_id", "200");
				user.addProperty("ec_user_rank", "11");

				data.add("user", user);

				result.addProperty("ERRCODE", "0");
				result.addProperty("ERRDESC", "succ");
				result.add("data", data);

			} else if ("�谲��Adri".equals(name)) {

				JsonObject user = new JsonObject();
				user.addProperty("ec_user_name", "�谲��Adrian���Ƶ�");
				user.addProperty("ec_user_id", "300");
				user.addProperty("ec_user_rank", "21");

				data.add("user", user);

				result.addProperty("ERRCODE", "0");
				result.addProperty("ERRDESC", "succ");
				result.add("data", data);

			} else {

				result.addProperty("ERRCODE", "1");
				result.addProperty("ERRDESC", "�˻�������");
				result.add("data", data);

			}

			Log.Nano.tag("����EC������ ��Ӧ����", result.toString());

			out.print(result.toString());
			out.flush();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String addCart() {

		// HttpServletRequest request = (HttpServletRequest)
		// ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
		response.setContentType("text/html;charset=utf-8");

		try {
			PrintWriter out;
			out = response.getWriter();
			JsonObject result = new JsonObject();
			JsonObject data = new JsonObject();

			JsonObject orderinfo = new JsonObject();
			orderinfo.addProperty("order_id", "170523183209008");
			orderinfo.addProperty("member_id", "01029987655");

			data.add("orderinfo", orderinfo);

			result.addProperty("ERRCODE", "0");
			result.addProperty("ERRDESC", "succ");
			result.add("data", data);

			Log.Nano.tag("����EC������ ��Ӧ����", result.toString());

			out.print(result.toString());
			out.flush();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String addMeasureCustomer() {

		// HttpServletRequest request = (HttpServletRequest)
		// ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
		response.setContentType("text/html;charset=utf-8");

		try {
			PrintWriter out;
			out = response.getWriter();
			JsonObject result = new JsonObject();
			JsonObject data = new JsonObject();

			JsonObject orderinfo = new JsonObject();
			orderinfo.addProperty("order_id", "170523183209008");
			orderinfo.addProperty("member_id", "01029987655");

			data.add("orderinfo", orderinfo);

			result.addProperty("ERRCODE", "0");
			result.addProperty("ERRDESC", "succ");
			result.add("data", data);

			Log.Nano.tag("����EC������ ��Ӧ����", result.toString());

			out.print(result.toString());
			out.flush();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
