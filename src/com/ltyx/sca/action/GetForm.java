package com.ltyx.sca.action;

import java.util.Random;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.service.Log;

public class GetForm extends ZCBaseActionSupport {

	/**
	 * 该Action用于获取填写表单
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		session.setAttribute("QRurl", PageConfig.get_QR_url(request));

		{
			String rank = "" + session.getAttribute("ec_user_rank");
			if ("0".equals(rank)) {// 客户经理
				session.setAttribute("menulist", PageConfig.get_menu_list_jingli());
			} else if ("10".equals(rank)) {// 定制顾问
				session.setAttribute("menulist", PageConfig.get_menu_list_guwen());
			} else if ("20".equals(rank)) {// 定制店
				session.setAttribute("menulist", PageConfig.get_menu_list_dingzhidian());
			} else {
				if ("张弛".equals(session.getAttribute("ec_user_name")) || "zc".equals(session.getAttribute("ec_user_name"))) {
					session.setAttribute("menulist", PageConfig.get_menu_list_all());
				}
			}
		}

		session.setAttribute("list_LZX_01", PageConfig.get_list_LZX_01());
		session.setAttribute("list_LZX_02", PageConfig.get_list_LZX_02());
		session.setAttribute("list_LZX_03", PageConfig.get_list_LZX_03());
		session.setAttribute("list_LZX_04", PageConfig.get_list_LZX_04());
		session.setAttribute("list_LZX_08", PageConfig.get_list_LZX_08());
		session.setAttribute("list_LZX_120", PageConfig.get_list_LZX_120());
		session.setAttribute("list_LZX_06", PageConfig.get_list_LZX_06());
		session.setAttribute("list_LZX_17", PageConfig.get_list_LZX_17());
		session.setAttribute("list_LZX_26", PageConfig.get_list_LZX_26());
		session.setAttribute("list_LZX_13", PageConfig.get_list_LZX_13());
		session.setAttribute("list_zhidai", PageConfig.get_list_zhidai());
		session.setAttribute("list_color", PageConfig.get_list_color());
		session.setAttribute("list_kouzi", PageConfig.get_list_kouzi());
		session.setAttribute("list_shenxing", PageConfig.get_list_shenxing());
		session.setAttribute("list_lingcheng", PageConfig.get_list_lingcheng());
		session.setAttribute("list_mingxian", PageConfig.get_list_mingxian());
		session.setAttribute("list_cefeng", PageConfig.get_list_cefeng());
		session.setAttribute("list_qiantiao", PageConfig.get_list_qiantiao());
		session.setAttribute("list_chenbu", PageConfig.get_list_chenbu());

		session.setAttribute("list_weizhi_zhidai", PageConfig.get_list_weizhi_zhidai());
		session.setAttribute("list_weizhi_peise", PageConfig.get_list_weizhi_peise());

		return "succ";

	}

	public String getFormWithCode() {

		init(true);

		session.setAttribute("QRurl", PageConfig.get_QR_url(request));

		{
			String rank = "" + session.getAttribute("ec_user_rank");
			if ("0".equals(rank)) {// 客户经理
				session.setAttribute("menulist", PageConfig.get_menu_list_jingli());
			} else if ("10".equals(rank)) {// 定制顾问
				session.setAttribute("menulist", PageConfig.get_menu_list_guwen());
			} else if ("20".equals(rank)) {// 定制店
				session.setAttribute("menulist", PageConfig.get_menu_list_dingzhidian());
			} else {
				if ("张弛".equals(session.getAttribute("ec_user_name")) || "zc".equals(session.getAttribute("ec_user_name"))) {
					session.setAttribute("menulist", PageConfig.get_menu_list_all());
				}
			}
		}
		
		session.setAttribute("list_LZX_01", PageConfig.get_list_LZX_01());
		session.setAttribute("list_LZX_02", PageConfig.get_list_LZX_02());
		session.setAttribute("list_LZX_03", PageConfig.get_list_LZX_03());
		session.setAttribute("list_LZX_04", PageConfig.get_list_LZX_04());
		session.setAttribute("list_LZX_08", PageConfig.get_list_LZX_08());
		session.setAttribute("list_LZX_120", PageConfig.get_list_LZX_120());
		session.setAttribute("list_LZX_06", PageConfig.get_list_LZX_06());
		session.setAttribute("list_LZX_17", PageConfig.get_list_LZX_17());
		session.setAttribute("list_LZX_26", PageConfig.get_list_LZX_26());
		session.setAttribute("list_LZX_13", PageConfig.get_list_LZX_13());
		session.setAttribute("list_zhidai", PageConfig.get_list_zhidai());
		session.setAttribute("list_color", PageConfig.get_list_color());
		session.setAttribute("list_kouzi", PageConfig.get_list_kouzi());
		session.setAttribute("list_shenxing", PageConfig.get_list_shenxing());
		session.setAttribute("list_lingcheng", PageConfig.get_list_lingcheng());
		session.setAttribute("list_mingxian", PageConfig.get_list_mingxian());
		session.setAttribute("list_cefeng", PageConfig.get_list_cefeng());
		session.setAttribute("list_qiantiao", PageConfig.get_list_qiantiao());
		session.setAttribute("list_chenbu", PageConfig.get_list_chenbu());

		session.setAttribute("list_weizhi_zhidai", PageConfig.get_list_weizhi_zhidai());
		session.setAttribute("list_weizhi_peise", PageConfig.get_list_weizhi_peise());

		String code = getReqParamString("code");
		session.setAttribute("code", code);

		return "succ";
	}

	public String getPrice() {

		init(true);

		Random random = new Random();
		int price = random.nextInt(4);
		price *= 259;
		price += 259;

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "" + price;

		writeResp("报价");

		return null;
	}

	public String submitOrder() {

		init(true);

		Random random = new Random();
		int price = random.nextInt(2);
		if (price == 1) {
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "提交成功";
		} else {
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "库存不足";
		}

		if ((getReqParamInt("prices_system") != getReqParamInt("prices_now")) && (getReqParamString("prices_desc").length() == 0)) {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "报价与系统价格不符，请填写差价说明";

		}

		Log.Nano.TagByLine("价格比较", getReqParamInt("prices_system"), getReqParamInt("prices_now"));

		writeResp("提交购物车");

		return null;
	}
}
