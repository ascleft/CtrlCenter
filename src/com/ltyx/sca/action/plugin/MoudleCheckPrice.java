package com.ltyx.sca.action.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCheckPrice extends ZCBaseActionSupportPlugin {

	public MoudleCheckPrice(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			double prices_system = getReqParamDouble("prices_system");
			double prices_now = getReqParamDouble("prices_now");
			String prices_desc = getReqParamString("prices_desc");

			if ((prices_system != prices_now) && (prices_desc.length() == 0)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "报价被更改，请填写差价说明";
				return false;
			}
		}

		{
			String prices_now_string = getReqParamString("prices_now");
			double prices_now_number = getReqParamDouble("prices_now");

			if (prices_now_number == 0) {
				if (!"0".equals(prices_now_string)) {

					ERRCODE = "0";
					ERRDESC = "fail";
					data = "自主报价必须为有效数字！";
					return false;
				}
			}
		}

		{
			double prices_now = getReqParamDouble("prices_now");

			if (prices_now < 0) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "价格不得为负！";
				return false;
			}
		}

		{
			String order_mtm_type = getReqParamString("prices_now");
			String measure_type = getReqParamString("measure_type");
			if ("标准码成衣".equals(order_mtm_type) && !"号衣尺码".equals(measure_type)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "您选择的【订单类型】是【标准码成衣】，请在【尺寸类型】中选择【号衣尺码】。";
				return false;
			}
		}

		return true;

	}

}
