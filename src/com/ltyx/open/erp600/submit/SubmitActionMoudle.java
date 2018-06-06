package com.ltyx.open.erp600.submit;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqProperty;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.JsonHelper;
import com.zc.support.service.LogType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubmitActionMoudle extends ZCBaseActionSupportPlugin {

	JSONObject order = null;
	JSONArray list = null;

	public SubmitActionMoudle(HttpServletRequest req) {
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

		if ("提交成功".equals(respString[0])) {
			ERRCODE = "0";
			ERRDESC = "succ";
			JSONObject orderIdTemp = new JSONObject();
			orderIdTemp.put("id", respString[1]);
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
		String CusCompanyLTCode = "216022400040";// 客户鲁泰编码
		String CustomCompany = getReqParamString("CustomCompany");// 客户公司 *
		String CustomName = getReqParamString("CustomName");// 顾客姓名 *
		String OrderTime = getReqParamString("OrderTime");// 下单时间 2018-06-03
															// 10:30*
		String CurrencyName = "人民币";// 货币名称
		String TotalAmount = getReqParamString("TotalAmount");// 金额 *
		String Address = getReqParamString("Address");// 详细地址 *
		String Nation = getReqParamString("Nation");// 国家
		String Province = getReqParamString("Province"); // 省州市
		String Area = getReqParamString("Area");// 市区县
		String Street = getReqParamString("Street");// 街道
		// String PostCode = "";// 邮编
		// String Destination = "";// 目的地
		String RealName = getReqParamString("RealName");// 收货人*
		String CommunicateInfo = getReqParamString("CommunicateInfo");// 通信信息 *
		// String Email = "";// 邮箱
		String Packing = "袋装";// 包装方式 袋装
		String ExPaymentWay = getReqParamString("ExPaymentWay");// 运费支付方式,预付、到付、第三方到付
		String Remark = "";// 客户备注
		String ExpressNO = "";// 运单号
		String OPTime = "";// 操作时间
		String State = "5";// 订单状态

		order.put("BPartnerCode", BPartnerCode);
		order.put("BPartnerKey", BPartnerKey);

		order.put("OrderNO", OrderNO);
		order.put("CusCompanyLTCode", CusCompanyLTCode);
		order.put("CustomCompany", CustomCompany);
		order.put("CustomName", CustomName);
		order.put("OrderTime", OrderTime);
		order.put("CurrencyName", CurrencyName);
		order.put("TotalAmount", TotalAmount);
		order.put("Address", Address);
		order.put("Nation", Nation);
		order.put("Province", Province);
		order.put("Area", Area);
		order.put("Street", Street);
		// order.put("PostCode", PostCode);
		// order.put("Destination", Destination);
		order.put("RealName", RealName);
		order.put("CommunicateInfo", CommunicateInfo);
		// order.put("Email", Email);
		order.put("Packing", Packing);
		order.put("ExPaymentWay", ExPaymentWay);
		order.put("Remark", Remark);
		order.put("ExpressNO", ExpressNO);
		order.put("OPTime", OPTime);
		order.put("State", State);

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
		property.addProperty("SOAPAction", "\"http://www.lttc.com.cn/LoadOrder\"");

		String req = "";
		req += "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		req += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		req += "<soap:Body>";
		req += "<LoadOrder xmlns=\"http://www.lttc.com.cn/\">";
		req += "<BOrderInfo>";
		req += order.toString();
		req += "</BOrderInfo>";
		req += "</LoadOrder>";
		req += "</soap:Body>";
		req += "</soap:Envelope>";

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addBody(req);
		String resp = ZCHttpReqSender.sendPost("http://www.lttc.com.cn/LTGlobalServices/BJYXInterface/LTGlobalServices.asmx", param, property, LogType.ERP600_ORDER_SUBMIT);

		// return order.toString();
		return resp;
	}

	private String getCore(String in) {

		String resp = "";

		if (in.length() >= 385) {
			resp = in.substring(286, in.length() - 66);
		}

		// System.out.println(in);
		// System.out.println(in.length());
		// System.out.println(resp);
		// System.out.println(resp.length());

		return resp;
	}

	private String[] change(String in) {

		JSONObject jsonObject = JSONObject.fromObject(in);

		String code = jsonObject.get("ResultType").toString();
		String desc = jsonObject.get("ResultMsg").toString();

		String content[] = new String[2];

		if (code.equals("1") && desc.equals("成功")) {
			// content = jsonObject.get("Content").toString();
			content[0] = "提交成功";
			content[1] = jsonObject.get("LTOrderNO").toString();
		} else {
			content[0] = "提交失败";
			content[1] = desc;
		}

		return content;
	}

}
