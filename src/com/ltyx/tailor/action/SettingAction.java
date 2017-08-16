package com.ltyx.tailor.action;

import com.zc.web.base.doman.ZCActionSupport;
import com.zc.web.base.service.DBConfigHelper;
import com.zc.web.base.service.URLConfigHelper;

public class SettingAction extends ZCActionSupport {

	/**
	 * ��Action���ڻ�ȡ��д��
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
		data += "��ǰ��¼��ַ:" + URLConfigHelper.Url_LoginEC;
		data += "<br />";
		data += "��ǰ�ύ��ַ:" + URLConfigHelper.Url_SubTailor;
		data += "<br />";
		data += "��ǰ���ݿ�:" + DBConfigHelper.NAME;
		data += "<br />";
		if ("jycsFactal150428!".equals(DBConfigHelper.PWD)) {
			data += "��ǰ���ݿ�����:" + "��ʽ��";
		} else if ("junyi000726".equals(DBConfigHelper.PWD)) {
			data += "��ǰ���ݿ�����:" + "���Կ�";
		} else {
			data += "��ǰ���ݿ�����:" + "������������";
		}

		writeResp();

		return null;

	}

	public String setting() {

		init(true);

		String url = getReqParamValue("url");
		String DBpwd = getReqParamValue("dbpwd");
		String pwd = getReqParamValue("pwd");

		if ("4008900726".equals(pwd)) {
			URLConfigHelper.customUrl(url);
			DBConfigHelper.customDB("root", DBpwd);
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "succ";

		} else {
			ERRCODE = "1";
			ERRDESC = "fail";
			data = "��Ȩ�����";
		}

		writeResp();

		return null;

	}

}
