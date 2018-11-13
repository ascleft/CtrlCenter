package com.ltyx.ucsplus.action;

import com.zc.support.doman.CCActionSupport;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqProperty;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

public class InterfaceTestAction extends CCActionSupport {

	/**
	 * 该Action用于测试并打印任意接口的输入情况
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {
		return "succ";
	}

	public String test() {
		init(true);

		ZCReqIntroGetter.showParams("接口连通性测试 输入参数", request, TextLogHelper.Type.OTHER_UCSPLUS_INTERFACETEST);

		if (!checkURL()) {
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "当前URL不合法，请检查。";
			writeResp("接口连通性测试", TextLogHelper.Type.OTHER_UCSPLUS_INTERFACETEST);
			return null;
		}
		String httpResp = getResp();
		if (!checkResp(httpResp)) {
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "当前请求无返回值。";
			writeResp("接口连通性测试", TextLogHelper.Type.OTHER_UCSPLUS_INTERFACETEST);
			return null;
		}

		ERRCODE = "0";
		ERRDESC = "succ";
		data = httpResp;

		writeResp("接口连通性测试", TextLogHelper.Type.OTHER_UCSPLUS_INTERFACETEST);

		return null;

	}

	private boolean checkURL() {

		String URL = getReqParamString("target_url");

		int indexHttp = URL.indexOf("http://");
		int indexHttps = URL.indexOf("https://");

		if ((indexHttp + indexHttps) == -1 && URL.length() > 13) {
			return true;
		} else {
			return false;
		}
	}

	private String getResp() {

		ZCHttpReqParam param = new ZCHttpReqParam();

		for (int i = 0; i < 100; i++) {
			String Key = getReqParamString("param_key_" + i);
			String Value = getReqParamString("param_value_" + i);
			if (!"".equals(Key)) {
				param.addParam(Key, Value);
			}

		}

		String httpURL = getReqParamString("target_url");
		String httpType = getReqParamString("http_type");
		String terminalType = getReqParamString("terminal_type");

		String httpResp = null;

		ZCHttpReqProperty property = new ZCHttpReqProperty();
		property.importBase(ZCHttpReqProperty.BaseProperty.TYPE_1);

		if (httpType.equals("POST")) {
			if ("Android".equals(terminalType)) {
				property.importUserAgent(ZCHttpReqProperty.Terminal.Android);
			} else if ("iPhone".equals(terminalType)) {
				property.importUserAgent(ZCHttpReqProperty.Terminal.iPhone);
			} else if ("WeChat".equals(terminalType)) {
				property.importUserAgent(ZCHttpReqProperty.Terminal.WeChat);
			} else {
				property.importUserAgent(ZCHttpReqProperty.Terminal.PC);
			}
			httpResp = ZCHttpReqSender.sendPost(httpURL, param, property);
		} else {
			if ("Android".equals(terminalType)) {
				property.importUserAgent(ZCHttpReqProperty.Terminal.Android);
			} else if ("iPhone".equals(terminalType)) {
				property.importUserAgent(ZCHttpReqProperty.Terminal.iPhone);
			} else if ("WeChat".equals(terminalType)) {
				property.importUserAgent(ZCHttpReqProperty.Terminal.WeChat);
			} else {
				property.importUserAgent(ZCHttpReqProperty.Terminal.PC);
			}
			httpResp = ZCHttpReqSender.sendGet(httpURL, param, property,TextLogHelper.Type.OTHER_UCSPLUS_INTERFACETEST);
		}

		return httpResp;
	}

	private boolean checkResp(String resp) {

		if (null == resp) {
			return false;
		} else {
			return true;
		}
	}

}