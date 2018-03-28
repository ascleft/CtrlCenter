package com.ltyx.open.ybr;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;

public class MoudleYBR_OBO_GetPrice extends ZCBaseActionSupportPlugin {

	double price = 0;

	public MoudleYBR_OBO_GetPrice(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		price = 0;

		String uskin_code = getReqParamString("uskin_code");
		String kouzi = getReqParamString("kouzi");

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("uskin_code", uskin_code.toUpperCase());
		param.addParam("kouzi", kouzi);
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

				price = calPrice(0, 0, 0, 0, 0);

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
		if ("1".equals(getReqParamString("LZX_11_FOR_CHAR_SWITCH"))) {
			price_temp += 5f;
		}
		// if ("1".equals(getReqParamString("LZX_11_FOR_PIC_SWITCH"))) {
		// price_temp += 5f;
		// }

		price_temp += getReqParamDouble("order_processing_cost");

		// if (isDP()) {
		// price_temp += 20;
		// }

		price_temp += Fabric;
		price_temp += Craft;
		price_temp += Additives;
		price_temp += Other;

		return price_temp;

	}

	// public boolean isDP() {
	// boolean isDP = false;
	// String uskin_code = getReqParamString("uskin_code").trim().toUpperCase();
	// int dpIndex = uskin_code.indexOf("DP");
	// int codeLength = uskin_code.length();
	// if (codeLength == 7 && dpIndex == 0) {
	// isDP = true;
	// }
	// return isDP;
	// }

	public double getPrice() {
		return price;
	}
}
