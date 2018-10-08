package com.ltyx.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.Log;
import com.zc.support.service.TextLogHelper;

import net.sf.json.JSONObject;

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
			JSONObject result = new JSONObject();
			JSONObject data = new JSONObject();

			if (!"鸿安·Adrian".equals(name)) {

				JSONObject user = new JSONObject();
				user.put("ec_user_name", "鸿安·Adrian客户经理");
				user.put("ec_user_id", "100");
				user.put("ec_user_rank", "0");

				data.put("user", user);

				result.put("ERRCODE", "0");
				result.put("ERRDESC", "succ");
				result.put("data", data);

			} else if ("鸿安·Adria".equals(name)) {

				JSONObject user = new JSONObject();
				user.put("ec_user_name", "鸿安·Adrian定制顾问");
				user.put("ec_user_id", "200");
				user.put("ec_user_rank", "11");

				data.put("user", user);

				result.put("ERRCODE", "0");
				result.put("ERRDESC", "succ");
				result.put("data", data);

			} else if ("鸿安·Adri".equals(name)) {

				JSONObject user = new JSONObject();
				user.put("ec_user_name", "鸿安·Adrian定制店");
				user.put("ec_user_id", "300");
				user.put("ec_user_rank", "21");

				data.put("user", user);

				result.put("ERRCODE", "0");
				result.put("ERRDESC", "succ");
				result.put("data", data);

			} else {

				result.put("ERRCODE", "1");
				result.put("ERRDESC", "账户不存在");
				result.put("data", data);

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
			JSONObject result = new JSONObject();
			JSONObject data = new JSONObject();

			JSONObject orderinfo = new JSONObject();
			orderinfo.put("order_id", "170523183209008");
			orderinfo.put("member_id", "01029987655");

			data.put("orderinfo", orderinfo);

			result.put("ERRCODE", "0");
			result.put("ERRDESC", "succ");
			result.put("data", data);

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
			JSONObject result = new JSONObject();
			JSONObject data = new JSONObject();

			JSONObject orderinfo = new JSONObject();
			orderinfo.put("order_id", "170523183209008");
			orderinfo.put("member_id", "01029987655");

			data.put("orderinfo", orderinfo);

			result.put("ERRCODE", "0");
			result.put("ERRDESC", "succ");
			result.put("data", data);

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

		ZCReqIntroGetter.showParams("模拟服务 动态表单", request, TextLogHelper.Type.UNDEFINED);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "{\"TABLES\":[{\"NAME\":\"男士量体表\",\"LIST\":[{\"KEY\":\"1\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"胸围\"},{\"KEY\":\"2\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"腰围\"},{\"KEY\":\"2\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"臂长\"},{\"KEY\":\"2\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"臀围\"}]},{\"NAME\":\"女士量体表\",\"LIST\":[{\"KEY\":\"3\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"胸围\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"臀围\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"领围\"}]},{\"NAME\":\"童装体表\",\"LIST\":[{\"KEY\":\"5\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"胸高\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"身高\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"腰围\"},{\"KEY\":\"4\",\"MAX\":\"100\",\"MIN\":\"1\",\"NAME\":\"领围\"}]}]}";

		writeResp("模拟服务 动态表单", TextLogHelper.Type.UNDEFINED);

		Log.Nano.tag("模拟服务 动态表单", "结束");

		return null;

	}

}
