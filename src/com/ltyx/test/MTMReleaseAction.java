package com.ltyx.test;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.HttpsUtil;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqProperty;
import com.zc.support.link.ZCHttpsReqSender;

public class MTMReleaseAction extends ZCBaseActionSupport {

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
		param.addParam("form_build_id", "form-ItnE_-Mk0qS3u4untweO1EciOZxkrUVtPkyXuCA5BrY");
		param.addParam("form_token", "0ggEyWvYUaLzGV6vQYAl75ssIeoy8U_p2ov0Sww_DdY");
		param.addParam("form_id", "tailogic_measurements_form");
		param.addParam("retail_customer_first_name", "test_first_name");
		param.addParam("retail_customer_last_name", "test_last_name");
		param.addParam("op", "Save");

		ZCHttpReqProperty property = new ZCHttpReqProperty();
		property.importBase(ZCHttpReqProperty.BaseProperty.TYPE_1);
		property.importUserAgent(ZCHttpReqProperty.Terminal.PC);
		property.addProperty("Cookie", "has_js=1; SSESS75bd0a9204b50aaf348ad7ab7f8b9104=qcEzFgI6HLnXdY7kyMTPBOMXI_sxkuQ_bX7K3fntRHs");

		// String resp =
		// ZCHttpsReqSender.sendPost("http://tailor.tailogic.com/tailogic/constructor/choose-style/75",
		// param, property);
		// ZCHttpsReqSender.httpsRequest("http://tailor.tailogic.com/tailogic/constructor/choose-style/75",
		// "GET", "");
		String resp = "";

		String s = HttpsUtil.httpsRequest("https://tailor.tailogic.com/tailogic/constructor/choose-style/75", "GET", null);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "已尝试注入Tailogic服务器";
		data += "----->";
		data += "http://tailor.tailogic.com/tailogic/constructor/choose-style/75";
		data += "----->";
		data += "服务端响应长度" + resp.length();

		writeResp("MTM 测试");

		return null;
	}

}
