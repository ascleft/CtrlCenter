package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleAideCheckSummaryClothes extends ZCBaseActionSupportPlugin {

	public MoudleAideCheckSummaryClothes(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			String uskin_code = getReqParamString("uskin_code");
			if (uskin_code.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的面料编号";
				return false;
			}
		}

		return true;

	}

}
