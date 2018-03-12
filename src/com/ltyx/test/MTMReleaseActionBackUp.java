package com.ltyx.test;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqProperty;
import com.zc.support.link.ZCHttpsReqSender;
import com.zc.support.service.Log;

public class MTMReleaseActionBackUp extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 100894456L;

	public String getPage() {

		return "succ";
	}

	public String submit() {
		init(true);

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("retail_customer_email", "zhinengzhizao_test@lttc.com");
		param.addParam("measurements[domain_id]", "399");
		param.addParam("profile_id", "0");
		param.addParam("form_build_id", "form-RMSm-CVM2TwXa3N3MHlpnT5MEcE-Jv6HxPrAM7ZyCo4");
		param.addParam("form_token", "UQ6kLeeZXx4atTS9dWj7RoOIA3ImKI_N74yV-cC8sVQ");
		param.addParam("form_id", "tailogic_measurements_form");
		param.addParam("retail_customer_first_name", "test_first_name");
		param.addParam("retail_customer_last_name", "test_last_name");
		param.addParam("op", "Save");

		ZCHttpReqProperty property = new ZCHttpReqProperty();
		property.importBase(ZCHttpReqProperty.BaseProperty.TYPE_1);
		property.importUserAgent(ZCHttpReqProperty.Terminal.PC);
		property.addProperty("Cookie",
				"SSESS75bd0a9204b50aaf348ad7ab7f8b9104=NhbKfIrWjk_zv4UdneI5Lbc4e5UW93SVK9SvBJHQYnE; has_js=1");

		String resp = "";

		resp = ZCHttpsReqSender.sendPost(//
				"https://tailor.tailogic.com/tailogic/constructor/choose-style/75", //
				param, //
				property);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "已尝试注入Tailogic服务器";
		data += "----->";
		data += "http://tailor.tailogic.com/tailogic/constructor/choose-style/75";
		data += "----->";
		data += "服务端响应长度" + resp.length();

		writeResp("MTM test ReleaseServer");

		System.out.println(resp);

		return null;
	}

	public void analyze(String html, String[] keys) {

		String analyzeType = getReqParamString("type");
		
		
		for (String string : keys) {
			
		}
		
		
		
	}

}
