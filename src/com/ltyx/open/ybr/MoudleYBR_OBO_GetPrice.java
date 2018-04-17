package com.ltyx.open.ybr;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleYBR_OBO_GetPrice extends ZCBaseActionSupportPlugin {

	double price = 0;

	public MoudleYBR_OBO_GetPrice(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

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

		price_temp += getReqParamDouble("order_processing_cost");

		price_temp += Fabric;
		price_temp += Craft;
		price_temp += Additives;
		price_temp += Other;

		return price_temp;

	}

	public double getPrice() {
		return price;
	}
}
