package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;

import net.sf.json.JSONObject;

public class MoudleCSAGetPricePBC extends ZCBaseActionSupportPlugin {

	public MoudleCSAGetPricePBC(HttpServletRequest req) {
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

		String order_delivery_time = getReqParamString("order_delivery_time");
		String order_processing_cost = getReqParamString("order_processing_cost");
		String order_mtm_type = getReqParamString("order_mtm_type");
		String order_production_count = getReqParamString("order_production_count");
		String LZX_11_FOR_CHAR_SWITCH = getReqParamString("LZX_11_FOR_CHAR_SWITCH");
		String LZX_11_FOR_PIC_SWITCH = getReqParamString("LZX_11_FOR_PIC_SWITCH");

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("customer_tel_target", customer_tel_target);
		param.addParam("uskin_code", uskin_code.toUpperCase());
		param.addParam("kouzi", kouzi);
		param.addParam("tailor_type", tailor_type);

		param.addParam("order_delivery_time", chooseNumber(order_delivery_time));
		param.addParam("order_processing_cost", order_processing_cost);
		param.addParam("order_mtm_type", order_mtm_type);
		param.addParam("order_production_count", chooseNumber(order_production_count));
		param.addParam("LZX_11_FOR_CHAR_SWITCH", LZX_11_FOR_CHAR_SWITCH);
		param.addParam("LZX_11_FOR_PIC_SWITCH", LZX_11_FOR_PIC_SWITCH);

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshopaide_get_price_pbc.getUrl(), param);

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

	private String chooseNumber(String numberString) {
		String numberInt = "";
		switch (numberString) {
		case "11-30":
			numberInt = "11";
			break;
		case "31-100":
			numberInt = "31";
			break;
		case "101-500":
			numberInt = "101";
			break;
		case "501-1500":
			numberInt = "501";
			break;
		case "10-15":
			numberInt = "10";
			break;
		case "15-25":
			numberInt = "15";
			break;
		default:
			numberInt = numberString;
			break;
		}

		return numberInt;
	}

}