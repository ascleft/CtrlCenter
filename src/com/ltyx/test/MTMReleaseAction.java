package com.ltyx.test;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqProperty;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.link.ZCHttpsReqSender;
import com.zc.support.service.StringHelper;
import com.zc.support.service.TimeHelper;

import net.sf.json.JSONObject;

public class MTMReleaseAction extends ZCBaseActionSupport {

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
		property.addProperty("Cookie", getReqParamString("Cookie"));

		String resp = "";

		if ("HTTPS".equals(getReqParamString("protocoltype"))) {
			resp = ZCHttpsReqSender.sendPost(//
					"https://" + getReqParamString("url"), //
					param, //
					property);
		} else {
			resp = ZCHttpReqSender.sendPost(//
					"http://" + getReqParamString("url"), //
					param, //
					property);
		}

		analyze(resp);

		writeResp("MTM test ReleaseServer");

		System.out.println(resp);

		return null;
	}

	public void analyze(String html) {

		String analyzeType = getReqParamString("analyzetype");

		String analyzeResult = "";

		switch (analyzeType) {
		case "type_keywords":
			analyzeResult = analyze_keywords(html);
			break;

		default:
			analyzeResult = "未指定分析模式";
			break;
		}

		String htmlNew = html;
		htmlNew = htmlNew.replace(" ", "&nbsp");
		htmlNew = htmlNew.replace("<", "&lt");
		htmlNew = htmlNew.replace(">", "&gt");
		htmlNew = htmlNew.replace("\"", "&quot");

		JSONObject analyze = new JSONObject();
		analyze.put("analyzeResult", analyzeResult);
		analyze.put("html",
				"UtailorSpider" //
						+ "<br />"//
						+ "resp length : " + html.length()//
						+ "<br />"//
						+ "resp time : " + TimeHelper.getTimeHMSS()//
						+ "<br /><br />" //
						+ htmlNew);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = analyze.toString();

	}

	public String analyze_keywords(String html) {
		String[] keys = getReqParamStrings("keyword");
		String log = "";
		for (String key : keys) {
			String result = "";
			int index = html.indexOf(key);
			if (index == -1) {
				result = StringHelper.fillRight(key, 20, "-") + ">" + "不存在";
			} else {
				result = StringHelper.fillRight(key, 20, "-") + ">" + "索引位置：" + index;
			}
			log += result + "<br />";
		}
		return log;
	}

}
