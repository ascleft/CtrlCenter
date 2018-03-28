package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;

public class MoudleCSAGetPricePBYX extends ZCBaseActionSupportPlugin {

	public MoudleCSAGetPricePBYX(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		double price = 0;

		String customer_tel_target = getReqParamString("customer_tel_target");
		String uskin_code = getReqParamString("uskin_code");
		String kouzi = getReqParamString("kouzi");
		String tailor_type = getReqParamString("tailor_type");

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("customer_tel_target", customer_tel_target);
		param.addParam("uskin_code", uskin_code.toUpperCase());
		param.addParam("kouzi", kouzi); 
		param.addParam("tailor_type", tailor_type);
		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshopaide_get_price_pbyx.getUrl(), param);

		Log.Nano.tag("Resp From EC", httpResp);

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
		if ("1".equals(getReqParamString("LZX_11_FOR_CHAR_SWITCH"))) {
			price_temp += 5f;
		}
		if ("1".equals(getReqParamString("LZX_11_FOR_PIC_SWITCH"))) {
			price_temp += 5f;
		}

		price_temp += getReqParamDouble("order_processing_cost");

		if (isDP()) {
			price_temp += 20;
		}

		price_temp += Fabric;
		price_temp += Craft;
		price_temp += Additives;
		price_temp += Other;

		return price_temp;

	}

	public boolean isDP() {
		boolean isDP = false;
		String uskin_code = getReqParamString("uskin_code").trim().toUpperCase();
		int dpIndex = uskin_code.indexOf("DP");
		int codeLength = uskin_code.length();
		if (codeLength == 7 && dpIndex == 0) {
			isDP = true;
		}
		return isDP;
	}
}
