package com.ltyx.open.erp600.search;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqProperty;
import com.zc.support.link.ZCHttpReqSender;

import net.sf.json.JSONObject;

public class InventoryActionMoudle extends ZCBaseActionSupportPlugin {

	public InventoryActionMoudle(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		String resp = "";
		String core = "";
		String changed = "";

		resp = getFull();
		core = getCore(resp);
		changed = change(core);

		if (core.length() == 35) {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "鲁泰ERP600返回异常";
			return false;
		} else {
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "" + changed;
			return true;
		}
	}

	private String getFull() {

		String BPartnerCode = "BJYX001"; // 鲁泰分配给合作方的账号
		String BPartnerKey = "123"; // 鲁泰分配给合作方的验证码
		String ProCode = getReqParamString("ProCode"); // 产品编码
		String ItemType = getReqParamString("ItemType"); // 内容类型

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("BPartnerCode", BPartnerCode);// 鲁泰分配给合作方的账号
		jsonObject.put("BPartnerKey", BPartnerKey);// 鲁泰分配给合作方的验证码
		jsonObject.put("ProCode", ProCode);// 产品编码
		jsonObject.put("ItemType", ItemType);// 内容类型

		ZCHttpReqProperty property = new ZCHttpReqProperty();
		property.importBase(ZCHttpReqProperty.BaseProperty.TYPE_1);
		property.importUserAgent(ZCHttpReqProperty.Terminal.PC);
		property.addProperty("Content-Type", "text/xml; charset=utf-8");
		property.addProperty("SOAPAction", "\"http://www.eluthai.com/ProductInventory\"");

		String req = "";
		req += "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		req += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		req += "<soap:Body>";
		req += "<ProductInventory xmlns=\"http://www.eluthai.com/\">";
		req += "<BProInfo>";
		req += jsonObject.toString();
		req += "</BProInfo>";
		req += "</ProductInventory>";
		req += "</soap:Body>";
		req += "</soap:Envelope>";

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addBody(req);
		String resp = ZCHttpReqSender
				.sendPost("http://eluthai.com/LTGlobalServices/BJYXInterface/LTGlobalServices.asmx", param, property);

		return resp;
	}

	private String getCore(String in) {
		String resp = "";

		resp = in.substring(300, in.length() - 80);

		System.out.println(in);
		System.out.println(in.length());
		System.out.println(resp);
		System.out.println(resp.length());

		return resp;
	}

	private String change(String in) {

		JSONObject jsonObject = JSONObject.fromObject(in);

		String code = jsonObject.get("ResultType").toString();
		String desc = jsonObject.get("ResultMsg").toString();

		String content = "";

		if (code.equals("0") && desc.equals("未处理！")) {
			content = "ERP600返回异常";
		} else {
			content = jsonObject.get("Content").toString();
		}

		return content;
	}

}
