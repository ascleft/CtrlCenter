package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCSDCheckUserInfo extends ZCBaseActionSupportPlugin {


	public MoudleCSDCheckUserInfo(HttpServletRequest req) {
		this.name = "校验 用户信息";
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			String customer_name = getReqParamString("customer_name");
			if (customer_name.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的穿衣人姓名";
				return false;
			}
		}

		return true;

	}

}
