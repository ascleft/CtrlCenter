package com.ltyx.tailor.action;

import com.zc.web.base.doman.ZCBaseActionSupport;
import com.zc.web.base.service.DBConfigHelper;
import com.zc.web.base.service.URLConfigHelper;

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
		data += "当前登录地址:" + URLConfigHelper.Url_LoginEC;
		data += "<br />";
		data += "当前提交地址:" + URLConfigHelper.Url_SubTailor;
		data += "<br />";
		data += "当前数据库:" + DBConfigHelper.NAME;
		data += "<br />";
		if ("jycsFactal150428!".equals(DBConfigHelper.PWD)) {
			data += "当前数据库密码:" + "正式库";
		} else if ("junyi000726".equals(DBConfigHelper.PWD)) {
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
			URLConfigHelper.customUrl(url);
			DBConfigHelper.customDB("root", DBpwd);
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
