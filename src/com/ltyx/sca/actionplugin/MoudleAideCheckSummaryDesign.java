package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleAideCheckSummaryDesign extends ZCBaseActionSupportPlugin {

	public MoudleAideCheckSummaryDesign(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			String design_code = getReqParamString("design_code");
			if (design_code.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的设计师款名称或编号";
				return false;
			}
		}

		return true;

	}

}
