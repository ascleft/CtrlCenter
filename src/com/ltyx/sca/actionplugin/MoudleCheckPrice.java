package com.ltyx.sca.actionplugin;

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

		return true;

	}

}
