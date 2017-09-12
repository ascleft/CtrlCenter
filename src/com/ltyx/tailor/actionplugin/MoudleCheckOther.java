package com.ltyx.tailor.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCheckOther extends ZCBaseActionSupportPlugin {

	public MoudleCheckOther(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			String uskin_code_type = getReqParamString("uskin_code_type");
			String uskin_code = getReqParamString("uskin_code");

			if ("PBC".equals(uskin_code_type) && uskin_code.length() < 5) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的客供面料运单号";
				return false;
			}
			if ("USKIN".equals(uskin_code_type) && uskin_code.length() < 5) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的USKIN编码";
				return false;
			}

		}

		{
			String garment_type = getReqParamString("garment_type");
			String style_name = getReqParamString("style_name");

			if (("man".equals(garment_type) || "woman".equals(garment_type)) && style_name.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的设计师推荐款编码或名称";
				return false;
			}

			if ("other".equals(garment_type) && style_name.length() < 3) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的商品编码";
				return false;
			}
		}

		{
			String customer_tel = getReqParamString("customer_tel");
			if (customer_tel.indexOf("@") == -1) {
				if (customer_tel.length() != 11) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "请填写正确的手机号码";
					return false;
				}
			}

			String customer_name = getReqParamString("customer_name");
			if (customer_name.length() < 2) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写客户姓名";
				return false;
			}

			String customer_tel_target = getReqParamString("customer_tel_target");
			if (customer_tel_target.length() != 11) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的被量体人手机号";
				return false;
			}

		}

		{
			String uskin_code_type = getReqParamString("uskin_code_type");// 0基本款，1推荐款，2自由搭配
			String uskin_code_length = getReqParamString("uskin_code_length");
			String uskin_code_color = getReqParamString("uskin_code_color");
			if ("PBC".equals(uskin_code_type) && uskin_code_length.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写客供面料用量（米）";
				return false;
			}
			if ("PBC".equals(uskin_code_type) && uskin_code_color.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写客供面料颜色描述";
				return false;
			}

		}

		{
			String[] style_name = getReqParamStrings("style_name");// 自主报价

			if (style_name.length == 2) {
				if (style_name[0].length() == 0 && style_name[1].length() == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "当前选择的商品是设计师推荐款，必须填写其设计师推荐款编号";
					return false;
				}
			}
		}

		{
			String prices_now = getReqParamString("prices_now");// 自主报价
			String prices_defult = getReqParamString("prices_defult");// 原价
			String prices_desc = getReqParamString("prices_desc");// 价差说明

			if ((prices_now.length() > 0) && (prices_defult.length() > 0)) {
				if ((!prices_now.equals(prices_defult)) && prices_desc.length() < 1) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "原价与结算价不一致时，必须填写报价说明";
					return false;
				}
			} else {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写原价与结算价";
				return false;
			}
		}

		return true;
	}

}
