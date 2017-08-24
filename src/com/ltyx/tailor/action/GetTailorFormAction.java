package com.ltyx.tailor.action;

import com.zc.support.doman.ZCBaseActionSupport;

public class GetTailorFormAction extends ZCBaseActionSupport {

	/**
	 * 该Action用于获取填写表单
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getForm() {

		init(true);

		session.setAttribute("QRurl", FormConfig.get_QR_url(request));

		session.setAttribute("menulist", FormConfig.get_menu_list());

		session.setAttribute("list_LZX_01", FormConfig.get_list_LZX_01());
		session.setAttribute("list_LZX_02", FormConfig.get_list_LZX_02());
		session.setAttribute("list_LZX_03", FormConfig.get_list_LZX_03());
		session.setAttribute("list_LZX_04", FormConfig.get_list_LZX_04());
		session.setAttribute("list_LZX_08", FormConfig.get_list_LZX_08());
		session.setAttribute("list_LZX_120", FormConfig.get_list_LZX_120());
		session.setAttribute("list_LZX_06", FormConfig.get_list_LZX_06());
		session.setAttribute("list_LZX_17", FormConfig.get_list_LZX_17());
		session.setAttribute("list_LZX_26", FormConfig.get_list_LZX_26());
		session.setAttribute("list_LZX_13", FormConfig.get_list_LZX_13());
		session.setAttribute("list_zhidai", FormConfig.get_list_zhidai());
		session.setAttribute("list_color", FormConfig.get_list_color());
		session.setAttribute("list_kouzi", FormConfig.get_list_kouzi());
		session.setAttribute("list_shenxing", FormConfig.get_list_shenxing());
		session.setAttribute("list_lingcheng", FormConfig.get_list_lingcheng());
		session.setAttribute("list_mingxian", FormConfig.get_list_mingxian());
		session.setAttribute("list_cefeng", FormConfig.get_list_cefeng());
		session.setAttribute("list_qiantiao", FormConfig.get_list_qiantiao());
		session.setAttribute("list_chenbu", FormConfig.get_list_chenbu());

		session.setAttribute("list_weizhi_zhidai", FormConfig.get_list_weizhi_zhidai());
		session.setAttribute("list_weizhi_peise", FormConfig.get_list_weizhi_peise());

		return "succ";

	}

	public String getFormWithCode() {

		init(true);

		session.setAttribute("QRurl", FormConfig.get_QR_url(request));

		session.setAttribute("menulist", FormConfig.get_menu_list());

		session.setAttribute("list_LZX_01", FormConfig.get_list_LZX_01());
		session.setAttribute("list_LZX_02", FormConfig.get_list_LZX_02());
		session.setAttribute("list_LZX_03", FormConfig.get_list_LZX_03());
		session.setAttribute("list_LZX_04", FormConfig.get_list_LZX_04());
		session.setAttribute("list_LZX_08", FormConfig.get_list_LZX_08());
		session.setAttribute("list_LZX_120", FormConfig.get_list_LZX_120());
		session.setAttribute("list_LZX_06", FormConfig.get_list_LZX_06());
		session.setAttribute("list_LZX_17", FormConfig.get_list_LZX_17());
		session.setAttribute("list_LZX_26", FormConfig.get_list_LZX_26());
		session.setAttribute("list_LZX_13", FormConfig.get_list_LZX_13());
		session.setAttribute("list_zhidai", FormConfig.get_list_zhidai());
		session.setAttribute("list_color", FormConfig.get_list_color());
		session.setAttribute("list_kouzi", FormConfig.get_list_kouzi());
		session.setAttribute("list_shenxing", FormConfig.get_list_shenxing());
		session.setAttribute("list_lingcheng", FormConfig.get_list_lingcheng());
		session.setAttribute("list_mingxian", FormConfig.get_list_mingxian());
		session.setAttribute("list_cefeng", FormConfig.get_list_cefeng());
		session.setAttribute("list_qiantiao", FormConfig.get_list_qiantiao());
		session.setAttribute("list_chenbu", FormConfig.get_list_chenbu());

		session.setAttribute("list_weizhi_zhidai", FormConfig.get_list_weizhi_zhidai());
		session.setAttribute("list_weizhi_peise", FormConfig.get_list_weizhi_peise());

		String code = getReqParamString("code");
		session.setAttribute("code", code);

		return "succ";
	}

}
