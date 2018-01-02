package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleAideGetPriceSubcontract extends ZCBaseActionSupportPlugin {

	public MoudleAideGetPriceSubcontract(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		double price = 0;

		price = calPrice(0, 0, 0, 0, 0);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "" + price;

		return true;

	}

	private double calPrice(double Fabric, double Craft, double Additives, double Other, double Total) {
		double price_temp = 0;

		String subcontract_price_type = getReqParamString("subcontract_price_type");
		double subcontract_peice_final = getReqParamDouble("subcontract_peice_final");
		double subcontract_fabric_unit_cost = getReqParamDouble("subcontract_fabric_unit_cost");
		double subcontract_process_cost = getReqParamDouble("subcontract_process_cost");
		double subcontract_fabric_length = getReqParamDouble("subcontract_fabric_length");
		double subcontract_ratio = getReqParamDouble("subcontract_ratio");

		if ("一口价".equals(subcontract_price_type)) {
			price_temp = subcontract_peice_final;
		} else if ("计算价格".equals(subcontract_price_type)) {
			price_temp = (//
					(subcontract_fabric_unit_cost * subcontract_fabric_length)//
					+ subcontract_process_cost)//
					* subcontract_ratio //
			;
		} else {
			price_temp = 999999999;
		}

		return price_temp;

	}
}