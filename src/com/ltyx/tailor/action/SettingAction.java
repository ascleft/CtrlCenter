package com.ltyx.tailor.action;

import com.zc.support.config.ConfigHelperDB;
import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupport;

public class SettingAction extends ZCBaseActionSupport {

	/**
	 * 该Action用于获取填写表单
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------

	public String getPage() {

		return "succ";

	}

	public String getState() {

		init(true);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "";
		
		{
			data += "当前数据库:" + ConfigHelperDB.NAME;
			data += "<br />";
			if ("jycsFactal150428!".equals(ConfigHelperDB.PWD)) {
				data += "当前数据库密码:" + "正式库";
			} else if ("junyi000726".equals(ConfigHelperDB.PWD)) {
				data += "当前数据库密码:" + "测试库";
			} else {
				data += "当前数据库密码:" + "喵喵喵？？？";
			}
			data += "<br />";
		}

		data += "<br />";
		data += "<br />";
		
		{
			data += "下单工具1.0";
			data += "<br />";
			data += "当前登录地址:  " + ConfigHelperURL.Url_LoginEC;
			data += "<br />";
			data += "当前提交地址:  " + ConfigHelperURL.Url_SubTailor;
			data += "<br />";
		}
		
		data += "<br />";
		data += "<br />";
		
		{
			data += "下单工具2.0（SCA）";
			data += "<br />";
			data += "当前定制顾问 自由搭配 报价:  " + ConfigHelperURL.Url_aide_get_price_pbyx;
			data += "<br />";
			data += "当前定制顾问 自由搭配 提交:  " + ConfigHelperURL.Url_aide_add_cart_pbyx;
			data += "<br />";
			data += "定制顾问 设计师推荐款 报价:  " + ConfigHelperURL.Url_aide_get_price_design;
			data += "<br />";
			data += "定制顾问 设计师推荐款 提交:  " + ConfigHelperURL.Url_aide_add_cart_design;
			data += "<br />";
			data += "定制顾问 其他商品 提交:  " + ConfigHelperURL.Url_aide_add_cart_subcontract;
			data += "<br />";
			data += "定制店 自由搭配 报价:  " + ConfigHelperURL.Url_customshop_get_price_pbyx;
			data += "<br />";
			data += "定制店 自由搭配 提交:  " + ConfigHelperURL.Url_customshop_add_cart_pbyx;
			data += "<br />";
			data += "定制店 自由搭配 提交:  " + ConfigHelperURL.Url_customshop_add_cart_pbc;
			data += "<br />";
			data += "定制店 自由搭配 报价:  " + ConfigHelperURL.Url_customshopaide_get_price_pbyx;
			data += "<br />";
			data += "定制店 自由搭配 提交:  " + ConfigHelperURL.Url_customshopaide_add_cart_pbyx;
			data += "<br />";
			data += "客户经理 客供面料 提交:  " + ConfigHelperURL.Url_customshopaide_add_cart_pbc;
			data += "<br />";
		}

		writeResp();

		return null;

	}

	public String setting() {

		init(true);

		String url = getReqParamString("url");
		String DBpwd = getReqParamString("dbpwd");
		String pwd = getReqParamString("pwd");

		if ("4008900726".equals(pwd)) {
			ConfigHelperURL.customUrl(url);
			ConfigHelperDB.customDB("root", DBpwd);
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "succ";

		} else {
			ERRCODE = "1";
			ERRDESC = "fail";
			data = "授权码错误，别瞎鸡巴乱搞。";
		}

		writeResp();

		return null;

	}

}
