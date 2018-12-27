package com.ltyx.open.ybr.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleYBR_OBO_Check extends ZCBaseActionSupportPlugin {

	public MoudleYBR_OBO_Check(HttpServletRequest req) {
		this.name = "对外接口 衣邦人 校验";
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			String document_pull_date = getReqParamString("document_pull_date");
			if (document_pull_date.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的合同发货日期";
				return false;
			}
		}
		{
			String financial_auth_time = getReqParamString("financial_auth_time");
			if (financial_auth_time.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的财审通过时间";
				return false;
			}
		}

		return true;

	}

}
