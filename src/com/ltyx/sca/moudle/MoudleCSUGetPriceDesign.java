package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;
import com.zc.support.service.TimeHelper;

import net.sf.json.JSONObject;

public class MoudleCSUGetPriceDesign extends ZCBaseActionSupportPlugin {

	public MoudleCSUGetPriceDesign(HttpServletRequest req) {
		this.name = "定制店 设计师款 报价";
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		TimeHelper.Timer timer = new TimeHelper.Timer();

		double price = 0;

		MoudleCSDParamUtil paramUtil = new MoudleCSDParamUtil(request);
		ZCHttpReqParam param = paramUtil.getCSUPriceDesign();

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshop_get_price_design.getUrl(), param);

		timer.stop(null);
		log.ec.addSrcReq(ConfigHelperURL.Url_customshop_get_price_design.getUrl(), param);
		log.ec.addSrcResp(httpResp);
		log.ec.addTimer(timer);

		JSONObject jsonHttpResp;
		String jsonERRCODE;
		String jsonERRDESC;
		String jsonData;

		JSONObject MainData;

		try {
			jsonHttpResp = JSONObject.fromObject(httpResp);
			jsonERRCODE = jsonHttpResp.getString("ERRCODE");
			jsonERRDESC = jsonHttpResp.getString("ERRDESC");
			jsonData = jsonERRDESC;
		} catch (Exception e) {
			// TODO: handle exception
			Log.Nano.tag(ConfigHelperURL.Url_customshop_get_price_design.getDesc() + "Resp From EC", httpResp);
			Log.Nano.tag("EC服务器响应错误，结构异常", httpResp);
			jsonHttpResp = null;
			jsonERRCODE = "0";
			jsonERRDESC = "fail";
			jsonData = "EC服务器响应错误，结构异常";
		}

		if ("0".equals(jsonERRCODE) && "succ".equals(jsonERRDESC)) {

			double jsonFabric;
			double jsonCraft;
			double jsonAdditives;
			double jsonOther;
			double jsonTotal;

			try {
				MainData = jsonHttpResp.getJSONObject("data");
				MainData = MainData.getJSONObject("prices");

				jsonFabric = MainData.getDouble("fabric");
				jsonCraft = MainData.getDouble("craft");
				jsonAdditives = MainData.getDouble("additives");
				jsonOther = MainData.getDouble("other");
				jsonTotal = MainData.getDouble("total");

				price = calPrice(jsonFabric, jsonCraft, jsonAdditives, jsonOther, jsonTotal);

				ERRCODE = "0";
				ERRDESC = "succ";
				data = "" + price;

			} catch (Exception e) {
				// TODO: handle exception
				Log.Nano.tag(ConfigHelperURL.Url_customshop_get_price_design.getDesc() + "Resp From EC", httpResp);
				Log.Nano.tag("EC服务器响应错误，第二级格式异常", httpResp);

				e.printStackTrace();

				ERRCODE = "0";
				ERRDESC = "fail";
				data = "EC服务器响应错误，第二级格式异常";
			}

			return true;

		} else {

			ERRCODE = "0";
			ERRDESC = "fail";
			data = jsonData;

			return false;
		}
	}

	private double calPrice(double Fabric, double Craft, double Additives, double Other, double Total) {

		double price_temp = 0;

		// price_temp += Fabric;
		// price_temp += Craft;
		// price_temp += Additives;
		// price_temp += Other;
		price_temp += Total;

		return price_temp;

	}

}
