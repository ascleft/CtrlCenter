package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCSACheckUserInfo extends ZCBaseActionSupportPlugin {

	public MoudleCSACheckUserInfo(HttpServletRequest req) {
		this.name = "校验 订单用户信息(客户经理)";
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
				data = "请填写正确的收货人姓名";
				return false;
			}
		}

		{
			String customer_tel = getReqParamString("customer_tel_target");
			if (customer_tel.length() != 11) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的账户手机号";
				return false;
			}
		}

		return true;

	}

}
