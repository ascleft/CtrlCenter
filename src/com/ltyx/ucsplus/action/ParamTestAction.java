package com.ltyx.ucsplus.action;

import java.util.Map;

import com.zc.support.doman.CCActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

import net.sf.json.JSONObject;

public class ParamTestAction extends CCActionSupport {

	/**
	 * 该Action用于测试并打印任意接口的输入情况
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String showParam() {
		init(true);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "测试结束";

		JSONObject reqFull = new JSONObject();
		JSONObject reqHeader = new JSONObject();
		JSONObject reqBody = new JSONObject();

		Map<String, String> headersMap = ZCReqIntroGetter.showHeaders("参数测试接口", request);
		Map<String, String> paramsMap = ZCReqIntroGetter.showParams("参数测试接口", request, TextLogHelper.Type.OTHER_UCSPLUS_INTERFACETEST);

		for (Map.Entry<String, String> entry : headersMap.entrySet()) {
			reqHeader.put(entry.getKey(), entry.getValue());
		}
		for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
			reqBody.put(entry.getKey(), entry.getValue());
		}

		reqFull.put("header", reqHeader);
		reqFull.put("body", reqBody);

		data = reqFull.toString();

		writeResp();

		return null;

	}

}
