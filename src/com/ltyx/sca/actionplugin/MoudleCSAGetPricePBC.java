package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCSAGetPricePBC extends ZCBaseActionSupportPlugin {

	public MoudleCSAGetPricePBC(HttpServletRequest req) {
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
		if ("1".equals(getReqParamString("LZX_11_FOR_CHAR_SWITCH"))) {
			price_temp += 5f;
		}
		if ("1".equals(getReqParamString("LZX_11_FOR_PIC_SWITCH"))) {
			price_temp += 5f;

		}

		price_temp += getReqParamDouble("order_processing_cost");

		price_temp += Fabric;

		return price_temp;

	}

}
