package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCSGetPricePBC extends ZCBaseActionSupportPlugin {

	public MoudleCSGetPricePBC(HttpServletRequest req) {
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
		price_temp = Fabric + getReqParamDouble("order_processing_cost");

		return price_temp;

	}

}
