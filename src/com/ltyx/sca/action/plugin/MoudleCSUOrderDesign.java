package com.ltyx.sca.action.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;
import com.zc.support.service.TextLogHelper;
import com.zc.support.service.TimeHelper;

import net.sf.json.JSONObject;

public class MoudleCSUOrderDesign extends ZCBaseActionSupportPlugin {

	public MoudleCSUOrderDesign(HttpServletRequest req) {
		this.name = "定制店 设计师款 提交购物车";
		this.request = req;
	}

	public boolean doJobs() {

		TimeHelper.Timer timer = new TimeHelper.Timer();

		MoudleCSParamUtil paramUtil = new MoudleCSParamUtil(request);
		ZCHttpReqParam param = paramUtil.getCSUOrderDesign();

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshop_add_cart_design.getUrl(), param, TextLogHelper.Type.USKIN_USER_ORDER_NSRC);
		Log.Nano.tag(ConfigHelperURL.Url_customshop_add_cart_design.getDesc() + "Resp From EC", httpResp);

		timer.stop(null);
		log.ec.addSrcReq(ConfigHelperURL.Url_customshop_add_cart_design.getUrl(), param);
		log.ec.addSrcResp(httpResp);
		log.ec.addTimer(timer);

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
