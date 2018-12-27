package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.JsonHelper;
import com.zc.support.service.Log;

public class MoudleAideGetPriceSubcontract extends ZCBaseActionSupportPlugin {

	String uskin_code;
	String subcontract_price_type;
	double subcontract_peice_final;
	double subcontract_fabric_unit_cost;
	double subcontract_process_cost;
	double subcontract_fabric_length;
	double subcontract_ratio;

	public MoudleAideGetPriceSubcontract(HttpServletRequest req) {
		this.name = "定制顾问 其他商品 报价";
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		ERRCODE = "0";
		ERRDESC = "succ";

		calPrice();

		return true;

	}

	private void calPrice() {

		double price_temp = 0;

		uskin_code = getReqParamString("uskin_code");
		subcontract_price_type = getReqParamString("subcontract_price_type");
		subcontract_peice_final = getReqParamDouble("subcontract_peice_final");
		subcontract_fabric_unit_cost = getReqParamDouble("subcontract_fabric_unit_cost");
		subcontract_process_cost = getReqParamDouble("subcontract_process_cost");
		subcontract_fabric_length = getReqParamDouble("subcontract_fabric_length");
		subcontract_ratio = getReqParamDouble("subcontract_ratio");

		if (uskin_code.length() > 1) {

			if ("一口价".equals(subcontract_price_type)) {
				price_temp = subcontract_peice_final;
				ERRCODE = "0";
				ERRDESC = "succ";
				data = "" + price_temp;
				return;
			} else if ("计算价格".equals(subcontract_price_type)) {
				if ("体验店价格".equals(getReqParamString("subcontract_fabric_unit_cost"))) {
					if (!correct_subcontract_fabric_unit_cost()) {
						ERRCODE = "0";
						ERRDESC = "fail";
						return;
					}
				}

				price_temp = (//
						(subcontract_fabric_unit_cost * subcontract_fabric_length)//
						+ subcontract_process_cost)//
						* subcontract_ratio //
				;

				ERRCODE = "0";
				ERRDESC = "succ";
				data = "" + price_temp;

				return;

			} else {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "输入信息不合法";
				return;
			}
		} else {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "请填写面料编号";
			return;
		}
	}

	public boolean correct_subcontract_fabric_unit_cost() {

		boolean isSucc = false;

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("uskin_code", getReqParamString("uskin_code"));
		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_aide_get_price_subcontract.getUrl(), param);

		Log.Nano.tag("Resp From EC", httpResp);

		if (JsonHelper.isJson(httpResp)) {
			ERRCODE = JsonHelper.getString(httpResp, "ERRCODE");
			ERRDESC = JsonHelper.getString(httpResp, "ERRDESC");
		} else {
			Log.Nano.tag("EC服务器响应错误，结构异常", httpResp);
			data = "EC服务器响应错误，结构异常";
			return false;
		}

		if ("0".equals(ERRCODE) && "succ".equals(ERRDESC)) {
			try {
				subcontract_fabric_unit_cost = JsonHelper.getJSONObject(httpResp, "data").getJSONObject("prices").getDouble("fabric");
				isSucc = true;
			} catch (Exception e) {
				// TODO: handle exception
				Log.Nano.tag("EC服务器响应错误，第二级格式异常", httpResp);
				data = "EC服务器响应错误，结构异常";
				isSucc = false;
			}
		} else {
			data = ERRDESC;
			isSucc = false;
		}

		return isSucc;
	}
}