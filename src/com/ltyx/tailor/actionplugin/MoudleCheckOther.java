package com.ltyx.tailor.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.web.base.doman.ZCActionPluginBase;
import com.zc.web.base.service.Log;

public class MoudleCheckOther extends ZCActionPluginBase {

	public MoudleCheckOther(HttpServletRequest req) {
		this.req = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			String uskin_code_type = getString("uskin_code_type");
			String uskin_code = getString("uskin_code");

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
			String garment_type = getString("garment_type");
			String style_name = getString("style_name");

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
			String customer_tel = getString("customer_tel");
			if (customer_tel.indexOf("@") == -1) {
				if (customer_tel.length() != 11) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "请填写正确的手机号码";
					return false;
				}
			}

			String customer_name = getString("customer_name");
			if (customer_name.length() < 2) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写客户姓名";
				return false;
			}

			String customer_tel_target = getString("customer_tel_target");
			if (customer_tel_target.length() != 11) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的被量体人手机号";
				return false;
			}

		}

		{
			String uskin_code_type = getString("uskin_code_type");// 0基本款，1推荐款，2自由搭配
			String uskin_code_length = getString("uskin_code_length");
			String uskin_code_color = getString("uskin_code_color");
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
			Log.Nano.tag("uskin_code_type", uskin_code_type);
		}

		{
			String tailor_type = getString("tailor_type");
			String duanxiu_chang = getString("duanxiu_chang");
			String duanxiu_kouwei_zuo = getString("duanxiu_kouwei_zuo");
			String duanxiu_kouwei_you = getString("duanxiu_kouwei_you");
			if ("27".equals(tailor_type) && (duanxiu_chang.length() == 0 || duanxiu_kouwei_zuo.length() == 0 || duanxiu_kouwei_you.length() == 0)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请补全短袖长及左右短袖口围信息";
				return false;
			}
		}

		{
			String tailor_type = getString("tailor_type");
			String xiu_chang_zuo = getString("xiu_chang_zuo");
			String xiu_chang_you = getString("xiu_chang_you");
			String xiutouchang_zuo = getString("xiutouchang_zuo");
			String xiutouchang_you = getString("xiutouchang_you");
			String xiuzhou_fei = getString("xiuzhou_fei");
			if ("26".equals(tailor_type)
					&& (xiu_chang_zuo.length() == 0 || xiu_chang_you.length() == 0 || xiutouchang_zuo.length() == 0 || xiutouchang_you.length() == 0 || xiuzhou_fei.length() == 0)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请补全左右袖长、袖头长及袖肘肥信息";
				return false;
			}
		}

		return true;
	}

}
