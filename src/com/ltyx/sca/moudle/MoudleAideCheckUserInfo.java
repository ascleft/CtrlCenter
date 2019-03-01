package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleAideCheckUserInfo extends ZCBaseActionSupportPlugin {

	public MoudleAideCheckUserInfo(HttpServletRequest req) {
		this.name = "定制顾问 校验 用户信息";
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

		{
			String customer_tel = getReqParamString("customer_tel");
			if (customer_tel.length() != 11) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的穿衣人手机号";
				return false;
			}
		}

		// {
		// String customer_tel = getReqParamString("customer_address");
		// if (customer_tel.length() != 11) {
		// ERRCODE = "0";
		// ERRDESC = "fail";
		// data = "请填写正确的收货人地址";
		// return false;
		// }
		// }

		// {
		// String customer_tips = getReqParamString("customer_tips");
		// if (customer_tips.length() != 11) {
		// ERRCODE = "0";
		// ERRDESC = "fail";
		// data = "请填写正确的订单备注";
		// return false;
		// }
		// }

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
