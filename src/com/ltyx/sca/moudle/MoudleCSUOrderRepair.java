package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;
import com.zc.support.service.TextLogHelper;
import com.zc.support.service.TimeHelper;

import net.sf.json.JSONObject;

public class MoudleCSUOrderRepair extends ZCBaseActionSupportPlugin {

	public MoudleCSUOrderRepair(HttpServletRequest req) {
		this.name = "定制店 返修订单 提交购物车";
		this.request = req;
	}

	public boolean doJobs() {

		TimeHelper.Timer timer = new TimeHelper.Timer();

		{
			String FactoryID = getReqParamString("FactoryID");
			String ExpressNO = getReqParamString("ExpressNO");
			String Tips = getReqParamString("Tips");

			if (FactoryID.trim().length() == 0) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "原订单号不得为空";
				return false;
			}
			if (ExpressNO.trim().length() == 0) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "寄厂单号不得为空";
				return false;
			}
			if (Tips.trim().length() == 0) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "生产备注不得为空";
				return false;
			}
		}

		MoudleCSDParamUtil paramUtil = new MoudleCSDParamUtil(request);
		ZCHttpReqParam param = paramUtil.getCSURepair();

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshop_add_cart_repair.getUrl(), param, TextLogHelper.Type.USKIN_USER_ORDER_NSRC);
		Log.Nano.tag(ConfigHelperURL.Url_customshop_add_cart_repair.getDesc() + "Resp From EC", httpResp);

		timer.stop(null);
		log.ec.addSrcReq(ConfigHelperURL.Url_customshop_add_cart_repair.getUrl(), param);
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
			jsonData = jsonHttpResp.getString("data");
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
