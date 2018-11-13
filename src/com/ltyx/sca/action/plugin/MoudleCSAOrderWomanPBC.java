package com.ltyx.sca.action.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;
import com.zc.support.service.TextLogHelper;

import net.sf.json.JSONObject;

public class MoudleCSAOrderWomanPBC extends ZCBaseActionSupportPlugin {

	public MoudleCSAOrderWomanPBC(HttpServletRequest req) {
		this.name = "客户经理 客供女装 提交购物车";
		this.request = req;
	}

	public boolean doJobs() {

		MoudleCSParamUtil paramUtil = new MoudleCSParamUtil(request);
		ZCHttpReqParam param = paramUtil.getCSUOrderManPBC();

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshopaide_add_cart_woman_pbc.getUrl(), param, TextLogHelper.Type.USKIN_AIDE_ORDER_NSRC);
		Log.Nano.tag(ConfigHelperURL.Url_customshopaide_add_cart_woman_pbc.getDesc() + "Resp From EC", httpResp);

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
