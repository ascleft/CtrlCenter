package com.ltyx.open.erp600.submit;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqProperty;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.JsonHelper;
import com.zc.support.service.TextLogHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OrderStateActionMoudle extends ZCBaseActionSupportPlugin {

	JSONObject order = null;
	JSONArray list = null;

	public OrderStateActionMoudle(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		order = new JSONObject();
		list = new JSONArray();

		checkOrderMsg();
		checkListMsg();
		String raw = submit();
		String resp = getCore(raw);
		String respString[] = change(resp);

		if ("获取成功".equals(respString[0])) {
			ERRCODE = "0";
			ERRDESC = "succ";
			JSONObject orderIdTemp = new JSONObject();

			orderIdTemp.put("ExpressNO", respString[1]);// 运单号，在Content标签中
			orderIdTemp.put("Notes", respString[2]);// 说明，在Content标签中
			orderIdTemp.put("OPTime", respString[3]);// 操作时间，在Content标签中
			orderIdTemp.put("OrderNO", respString[4]);// 鲁泰订单号，在Content标签中
			orderIdTemp.put("State", respString[5]);// 状态，在Content标签中

			data = orderIdTemp.toString();
		} else {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = respString[1];
		}

		return true;
	}

	public boolean checkOrderMsg() {

		boolean isSucc = false;

		String BPartnerCode = "BJYX001";// 鲁泰分配给合作方的账号
		String BPartnerKey = "123";// 鲁泰分配给合作方的验证码

		String OrderNO = getReqParamString("OrderNO");// 订单编号*

		order.put("BPartnerCode", BPartnerCode);
		order.put("BPartnerKey", BPartnerKey);

		order.put("OrderNO", OrderNO);

		isSucc = true;

		return isSucc;

	}

	public boolean checkListMsg() {

		boolean isSucc = false;

		JSONArray EClist = JsonHelper.getJSONArray(getReqParamString("OrderDetail"));

		// System.out.println(getReqParamString("OrderDetail"));
		// System.out.println(JsonHelper.isJsonArray(getReqParamString("OrderDetail")));
		// System.out.println(EClist.size());

		if (null != EClist) {
			for (int i = 0; i < EClist.size(); i++) {

				JSONObject ECObject = EClist.getJSONObject(i);

				JSONObject tempObject = new JSONObject();

				String OrderNO = ECObject.getString("OrderNO"); // 订单编号
				String LinesNO = "";// 行号
				String ProCode = ECObject.getString("ProCode");// 产品编码
				String Specifications = "默认规格";// 规格
				String Unit = "米";// 单位
				String Quantity = ECObject.getString("Quantity");// 数量
				String Price = "0";// 单价
				String RebateAmount = "0";// 折扣金额
				String Amount = "0";// 金额
				String DetailNotes = ECObject.getString("DetailNotes");// 明细备注

				tempObject.put("OrderNO", OrderNO);
				tempObject.put("LinesNO", LinesNO);
				tempObject.put("ProCode", ProCode);
				tempObject.put("Specifications", Specifications);
				tempObject.put("Unit", Unit);
				tempObject.put("Quantity", Quantity);
				tempObject.put("Price", Price);
				tempObject.put("RebateAmount", RebateAmount);
				tempObject.put("Amount", Amount);
				tempObject.put("DetailNotes", DetailNotes);

				list.add(tempObject);

			}
		} else {
			list = new JSONArray();
		}

		order.put("OrderDetail", list);

		isSucc = true;

		return isSucc;

	}

	private String submit() {

		ZCHttpReqProperty property = new ZCHttpReqProperty();
		property.importBase(ZCHttpReqProperty.BaseProperty.TYPE_1);
		property.importUserAgent(ZCHttpReqProperty.Terminal.PC);
		property.addProperty("Content-Type", "text/xml; charset=utf-8");
		property.addProperty("SOAPAction", "\"http://www.lttc.com.cn/OrderState\"");

		String req = "";
		req += "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		req += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		req += "<soap:Body>";
		req += "<OrderState xmlns=\"http://www.lttc.com.cn/\">";
		req += "<BOrderNO>";
		req += order.toString();
		req += "</BOrderNO>";
		req += "</OrderState>";
		req += "</soap:Body>";
		req += "</soap:Envelope>";

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addBody(req);
		String resp = ZCHttpReqSender.sendPost("http://www.lttc.com.cn/LTGlobalServices/BJYXInterface/LTGlobalServices.asmx", param, property, TextLogHelper.Type.ERP600_ORDER_STATE);

		// return order.toString();
		return resp;
	}

	private String getCore(String in) {

		String resp = "";

		if (in.length() >= 385) {
			resp = in.substring(288, in.length() - 68);
		}

		System.out.println(in);
		System.out.println(in.length());
		System.out.println(resp);
		System.out.println(resp.length());

		return resp;
	}

	private String[] change(String in) {

		JSONObject jsonObject = JSONObject.fromObject(in);

		String code = jsonObject.get("ResultType").toString();// 结果类型(1:成功，0:未处理！，-1:数据格式错误！,-2:鲁泰全球服务
																// Connect
																// Fail！)
		String desc = jsonObject.get("ResultMsg").toString();// 结果信息

		String content[] = new String[6];

		if (code.equals("1") && desc.equals("成功")) {
			// content = jsonObject.get("Content").toString();
			content[0] = "获取成功";

//			content[1] = jsonObject.getJSONObject("Content").get("ExpressNO").toString();// 运单号，在Content标签中
//			content[2] = jsonObject.getJSONObject("Content").get("Notes").toString();// 说明，在Content标签中
//			content[3] = jsonObject.getJSONObject("Content").get("OPTime").toString();// 操作时间，在Content标签中
//			content[4] = jsonObject.getJSONObject("Content").get("OrderNO").toString();// 鲁泰订单号，在Content标签中
//			content[5] = jsonObject.getJSONObject("Content").get("State").toString();// 状态，在Content标签中

			try {
				content[1] = jsonObject.getJSONArray("Content").getJSONObject(0).get("ExpressNO").toString();// 运单号，在Content标签中
				content[2] = jsonObject.getJSONArray("Content").getJSONObject(0).get("Notes").toString();// 说明，在Content标签中
				content[3] = jsonObject.getJSONArray("Content").getJSONObject(0).get("OPTime").toString();// 操作时间，在Content标签中
				content[4] = jsonObject.getJSONArray("Content").getJSONObject(0).get("OrderNO").toString();// 鲁泰订单号，在Content标签中
				content[5] = jsonObject.getJSONArray("Content").getJSONObject(0).get("State").toString();// 状态，在Content标签中
			} catch (Exception e) {
				// TODO: handle exception
				content[1] = "ERP600结构异常，请及时查看日志";// 运单号，在Content标签中
				content[2] = "ERP600结构异常，请及时查看日志";// 说明，在Content标签中
				content[3] = "ERP600结构异常，请及时查看日志";// 操作时间，在Content标签中
				content[4] = "ERP600结构异常，请及时查看日志";// 鲁泰订单号，在Content标签中
				content[5] = "ERP600结构异常，请及时查看日志";// 状态，在Content标签中

			}

		} else {
			content[0] = "获取失败";
			content[1] = desc;
		}

		return content;

	}

}
