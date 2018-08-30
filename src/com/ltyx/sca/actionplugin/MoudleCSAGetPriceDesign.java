package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;

import net.sf.json.JSONObject;

public class MoudleCSAGetPriceDesign extends ZCBaseActionSupportPlugin {

	public MoudleCSAGetPriceDesign(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		double price = 0;

		String customer_tel_target = getReqParamString("customer_tel_target");
		String design_code = getReqParamString("design_code");
		String uskin_code = getReqParamString("uskin_code");
		String kouzi = getReqParamString("kouzi");
		String tailor_type = getReqParamString("tailor_type");

		String order_delivery_time = getReqParamString("order_delivery_time");
		String order_processing_cost = getReqParamString("order_processing_cost");
		String order_mtm_type = getReqParamString("order_mtm_type");
		String order_production_count = getReqParamString("order_production_count");
		String LZX_11_FOR_CHAR_SWITCH = getReqParamString("LZX_11_FOR_CHAR_SWITCH");
		String LZX_11_FOR_PIC_SWITCH = getReqParamString("LZX_11_FOR_PIC_SWITCH");

		String special_technics = getReqParamString("special_technics");

		String LZX_01 = getReqParamString("LZX_01");// 领型
		String LZX_02 = getReqParamString("LZX_02");// 袖头,短袖头
		String LZX_03 = getReqParamString("LZX_03");// 门襟
		String LZX_04 = getReqParamString("LZX_04");// 口袋
		String weizhi_zhidai = getReqParamString("weizhi_zhidai");// 织带位置
		String LZX_26 = getReqParamString("LZX_26");// 底摆配布
		String cixiu_kegong_num = getReqParamString("cixiu_kegong_num");// 客供图案刺绣数量

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("customer_tel_target", customer_tel_target);
		param.addParam("design_code", design_code);
		param.addParam("uskin_code", uskin_code.toUpperCase());
		param.addParam("kouzi", kouzi);
		param.addParam("tailor_type", tailor_type);

		param.addParam("order_delivery_time", chooseNumber(order_delivery_time));
		param.addParam("order_processing_cost", order_processing_cost);
		param.addParam("order_mtm_type", order_mtm_type);
		param.addParam("order_production_count", chooseNumber(order_production_count));
		param.addParam("LZX_11_FOR_CHAR_SWITCH", LZX_11_FOR_CHAR_SWITCH);
		param.addParam("LZX_11_FOR_PIC_SWITCH", LZX_11_FOR_PIC_SWITCH);

		param.addParam("special_technics", special_technics);

		param.addParam("LZX_01", LZX_01);// 领型
		param.addParam("LZX_02", LZX_02);// 袖头,短袖头
		param.addParam("LZX_03", LZX_03);// 门襟
		param.addParam("LZX_04", LZX_04);// 口袋
		param.addParam("weizhi_zhidai", weizhi_zhidai);// 织带位置
		param.addParam("LZX_26", LZX_26);// 底摆配布
		param.addParam("cixiu_kegong_num", cixiu_kegong_num);// 客供图案刺绣数量

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshop_get_price_design.getUrl(), param);

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
