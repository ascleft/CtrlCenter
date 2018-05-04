package com.ltyx.test;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqProperty;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.LogType;

public class MTMTestAction extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 100894456L;

	public String submit() {
		init(true);

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("retail_customer_email", "zhinengzhizao_test@lttc.com");
		param.addParam("measurements[domain_id]", "399");
		param.addParam("profile_id", "0");
		param.addParam("form_build_id", "form-MN2ojAoUFAn2n6efgLAtGINGIk7R9VXo3y5-rk5aagY");
		param.addParam("form_token", "Zvovt0tTlPinbt6Gvc4TyRmAvUkkCrQAef1ylbJ-Esw");
		param.addParam("form_id", "tailogic_measurements_form");
		param.addParam("retail_customer_first_name", "888888");
		param.addParam("retail_customer_last_name", "666666");
		param.addParam("op", "Save");

		ZCHttpReqProperty property = new ZCHttpReqProperty();
		property.importBase(ZCHttpReqProperty.BaseProperty.TYPE_1);
		property.importUserAgent(ZCHttpReqProperty.Terminal.PC);
		property.addProperty("Cookie", "has_js=1; SESS254d6241dc85dcb04eee3d2ef73422a5=8IqC9JWM8p4WwgOEgQTH2GIJI59BOU6faaQjDmntxHE");

		String resp = ZCHttpReqSender.sendPost("http://test.tailor.tailogic.com/tailogic/constructor/choose-style/75", param, property);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "已尝试注入Tailogic服务器";
		data += "----->";
		data += "http://test.tailor.tailogic.com/tailogic/constructor/choose-style/75";
		data += "----->";
		data += "服务端响应长度" + resp.length();

		writeResp("MTM 测试", LogType.NORMAL);

		return null;
	}

}
