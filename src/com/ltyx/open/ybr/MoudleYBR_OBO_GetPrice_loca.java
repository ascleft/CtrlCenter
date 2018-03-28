package com.ltyx.open.ybr;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleYBR_OBO_GetPrice_loca extends ZCBaseActionSupportPlugin {

	double price = 0;

	public MoudleYBR_OBO_GetPrice_loca(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		price = 0;

		double fabric = getReqParamDouble("fabric");
		double craft = getReqParamDouble("craft");
		double additives = getReqParamDouble("additives");
		double other = getReqParamDouble("other");
		double total = getReqParamDouble("total");

		price = calPrice(fabric, craft, additives, other, total);
		if (price >= 50) {
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "" + price;
			return true;
		} else {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "价格过低";
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

	public double getPrice() {
		return price;
	}
}
