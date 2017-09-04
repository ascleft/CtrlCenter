package com.ltyx.ucsplus.action;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.Log;

public class InterfaceTestAction extends ZCBaseActionSupport {

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

		String target_url = getReqParamString("target_url");

		ZCHttpReqParam param = new ZCHttpReqParam();

		for (int i = 0; i < 100; i++) {
			String Key = getReqParamString("param_key_" + i);
			String Value = getReqParamString("param_value_" + i);
			if (!"".equals(Key)) {
				param.addParam(Key, Value);
			}

		}

//		ZCReqIntroGetter.showParams("接口连通性测试 输入参数", request);

		String httpResp = ZCHttpReqSender.sendGet(target_url, param);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = httpResp;

		writeResp("接口连通性测试");
//		writeResp();

		return null;

	}
}