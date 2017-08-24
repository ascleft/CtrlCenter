package com.ltyx.tailor.action;

import com.zc.web.support.config.ConfigHelperDB;
import com.zc.web.support.config.ConfigHelperURL;
import com.zc.web.support.doman.ZCBaseActionSupport;

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
		data += "当前登录地址:" + ConfigHelperURL.Url_LoginEC;
		data += "<br />";
		data += "当前提交地址:" + ConfigHelperURL.Url_SubTailor;
		data += "<br />";
		data += "当前数据库:" + ConfigHelperDB.NAME;
		data += "<br />";
		if ("jycsFactal150428!".equals(ConfigHelperDB.PWD)) {
			data += "当前数据库密码:" + "正式库";
		} else if ("junyi000726".equals(ConfigHelperDB.PWD)) {
			data += "当前数据库密码:" + "测试库";
		} else {
			data += "当前数据库密码:" + "喵喵喵？？？";
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
