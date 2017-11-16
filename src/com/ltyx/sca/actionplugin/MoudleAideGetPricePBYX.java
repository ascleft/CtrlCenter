package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;

public class MoudleAideGetPricePBYX extends ZCBaseActionSupportPlugin {

	public MoudleAideGetPricePBYX(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		double price = 0;

		String uskin_code = getReqParamString("uskin_code");

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("uskin_code", uskin_code);
		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_aide_get_price_pbyx, param);

		Log.Nano.tag("Save Resp From EC", httpResp);

		JSONObject jsonHttpResp;
		String jsonERRCODE;
		String jsonERRDESC;
		String jsonData;

		JSONObject MainData;

		try {
			jsonHttpResp = JSONObject.fromObject(httpResp);
			jsonERRCODE = jsonHttpResp.getString("ERRCODE");
			jsonERRDESC = jsonHttpResp.getString("ERRDESC");
			jsonData = "EC错误码：" + jsonERRCODE + " EC错误描述：" + jsonERRDESC;
			Log.Nano.byLine("ERRCODE", jsonERRCODE, "ERRDESC", jsonERRDESC);
		} catch (Exception e) {
			// TODO: handle exception
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
				Log.Nano.tag("EC服务器响应错误，第二级格式异常", httpResp);

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
		price_temp = Fabric;

		return price_temp;

	}

}