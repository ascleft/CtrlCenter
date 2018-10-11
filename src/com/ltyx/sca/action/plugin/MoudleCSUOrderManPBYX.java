package com.ltyx.sca.action.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;
import com.zc.support.service.TextLogHelper;

import net.sf.json.JSONObject;

public class MoudleCSUOrderManPBYX extends ZCBaseActionSupportPlugin {

	public MoudleCSUOrderManPBYX(HttpServletRequest req) {
		this.request = req;
	}

	public boolean doJobs() {

		MoudleCSParamUtil paramUtil = new MoudleCSParamUtil(request);
		ZCHttpReqParam param = paramUtil.getCSUOrderManPBYX();

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshop_add_cart_man_pbyx.getUrl(), param, TextLogHelper.Type.USKIN_USER_ORDER_NSRC);
		Log.Nano.tag(ConfigHelperURL.Url_customshop_add_cart_man_pbyx.getDesc() + "Resp From EC", httpResp);

		JSONObject jsonHttpResp;
		String jsonERRCODE;
		String jsonERRDESC;
		String jsonData;
		try {
			jsonHttpResp = JSONObject.fromObject(httpResp);
			jsonERRCODE = jsonHttpResp.getString("ERRCODE");
			jsonERRDESC = jsonHttpResp.getString("ERRDESC");
			jsonData = jsonERRDESC;
		} catch (Exception e) {
			// TODO: handle exception
			Log.Nano.tag("EC服务器响应错误", httpResp);
			jsonERRCODE = "0";
			jsonERRDESC = "fail";
			jsonData = "EC服务器响应错误";
		}

		if ("0".equals(jsonERRCODE) && "succ".equals(jsonERRDESC)) {
			return true;
		} else {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = jsonData;
			return false;
		}

	}

}