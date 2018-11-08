package com.ltyx.sca.action.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCSCheckUserInfo extends ZCBaseActionSupportPlugin {


	public MoudleCSCheckUserInfo(HttpServletRequest req) {
		this.request = req;
		this.name = "用户信息检测";
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
