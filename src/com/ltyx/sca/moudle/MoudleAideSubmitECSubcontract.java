package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;
import com.zc.support.service.TimeHelper;

public class MoudleAideSubmitECSubcontract extends ZCBaseActionSupportPlugin {

	public MoudleAideSubmitECSubcontract(HttpServletRequest req) {
		this.name = "定制顾问 其他商品 提交购物车";
		this.request = req;
	}

	public boolean doJobs() {

		TimeHelper.Timer timer = new TimeHelper.Timer();

		MoudleCSDParamUtil paramUtil = new MoudleCSDParamUtil(request);
		ZCHttpReqParam param = paramUtil.getCSAOrderSubcontract();

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_aide_add_cart_subcontract.getUrl(), param);

		timer.stop(null);
		log.ec.addSrcReq(ConfigHelperURL.Url_aide_add_cart_subcontract.getUrl(), param);
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
