package com.ltyx.test;

import com.zc.support.doman.CCActionSupport;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.JsonHelper;
import com.zc.support.service.TextLogHelper;

public class XSSubmitAction extends CCActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {
		return "succ";
	}

	public String submit() {

		init(true);

		String methodName = "熊爽服务端测试";

		if (doSubmit()) {
		}

		writeResp(methodName, TextLogHelper.Type.UNDEFINED);

		return null;

	}

	public boolean doSubmit() {

		boolean succ = false;
		int i = 0;

		while (!succ) {
			ZCHttpReqParam param = new ZCHttpReqParam();
			param.addParam("signcode", "7d128351-43ce-3522-8824-924a1d924e83");
			param.addParam("password", "" + i);
			param.addParam("validcode", "2510");
			param.addParam("mobile", "15901259081");

			String resp = ZCHttpReqSender.sendPost("http://t1.bygcc.cc/Api/Login/login", param);

			String msg = JsonHelper.getString(resp, "returnMsg");

			if ("登陆成功".equals(msg)) {
				succ = true;
			}

			i++;

		}

		return succ;

	}

}
