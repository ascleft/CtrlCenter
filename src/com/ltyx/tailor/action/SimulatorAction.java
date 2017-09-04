package com.ltyx.tailor.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.Log;

public class SimulatorAction extends ZCBaseActionSupport {

	/**
	 * 该Action用于获取填写表单
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

			if (!"鸿安·Adrian".equals(name)) {

				JsonObject user = new JsonObject();
				user.addProperty("ec_user_name", "鸿安·Adrian客户经理");
				user.addProperty("ec_user_id", "100");
				user.addProperty("ec_user_rank", "0");

				data.add("user", user);

				result.addProperty("ERRCODE", "0");
				result.addProperty("ERRDESC", "succ");
				result.add("data", data);

			} else if ("鸿安·Adria".equals(name)) {

				JsonObject user = new JsonObject();
				user.addProperty("ec_user_name", "鸿安·Adrian定制顾问");
				user.addProperty("ec_user_id", "200");
				user.addProperty("ec_user_rank", "11");

				data.add("user", user);

				result.addProperty("ERRCODE", "0");
				result.addProperty("ERRDESC", "succ");
				result.add("data", data);

			} else if ("鸿安·Adri".equals(name)) {

				JsonObject user = new JsonObject();
				user.addProperty("ec_user_name", "鸿安·Adrian定制店");
				user.addProperty("ec_user_id", "300");
				user.addProperty("ec_user_rank", "21");

				data.add("user", user);

				result.addProperty("ERRCODE", "0");
				result.addProperty("ERRDESC", "succ");
				result.add("data", data);

			} else {

				result.addProperty("ERRCODE", "1");
				result.addProperty("ERRDESC", "账户不存在");
				result.add("data", data);

			}

			Log.Nano.tag("仿真EC服务器 响应数据", result.toString());

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

			Log.Nano.tag("仿真EC服务器 响应数据", result.toString());

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

			Log.Nano.tag("仿真EC服务器 响应数据", result.toString());

			out.print(result.toString());
			out.flush();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String getPageActionFormPage() {

		return "succ";

	}

	public String getPageActionForm() {

		Log.Nano.tag("模拟服务 动态表单", "开始");

		init(true);

		ZCReqIntroGetter.showParams("模拟服务 动态表单", request);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "{\"TABLES\":[{\"NAME\":\"男士量体表\",\"LIST\":[{\"KEY\":\"1\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"胸围\"},{\"KEY\":\"2\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"腰围\"},{\"KEY\":\"2\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"臂长\"},{\"KEY\":\"2\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"臀围\"}]},{\"NAME\":\"女士量体表\",\"LIST\":[{\"KEY\":\"3\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"胸围\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"臀围\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"领围\"}]},{\"NAME\":\"童装体表\",\"LIST\":[{\"KEY\":\"5\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"胸高\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"身高\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"腰围\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"领围\"}]}]}";

		writeResp("模拟服务 动态表单");

		Log.Nano.tag("模拟服务 动态表单", "结束");

		return null;

	}

}
