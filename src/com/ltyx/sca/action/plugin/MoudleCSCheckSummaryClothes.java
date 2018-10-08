package com.ltyx.sca.action.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCSCheckSummaryClothes extends ZCBaseActionSupportPlugin {

	public MoudleCSCheckSummaryClothes(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			String design_code = getReqParamString("design_code");
			String uskin_code = getReqParamString("uskin_code");
			if (uskin_code.length() < 1) {
				if (design_code.trim().length() == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "请填写正确的面料编号";
					return false;
				}
			}
		}

		{
			String YX_08 = getReqParamString("YX_08", "");
			if (YX_08.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "客供主唛必须填写说明";
				return false;
			}
		}

		{
			String kouzi = getReqParamString("kouzi", "");
			if (kouzi.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "客供扣子必须填写说明";
				return false;
			}
		}

		return true;

	}

}
