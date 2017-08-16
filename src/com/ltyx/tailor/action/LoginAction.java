package com.ltyx.tailor.action;

import net.sf.json.JSONObject;

import com.zc.web.base.doman.ZCActionSupport;
import com.zc.web.base.doman.ZCHttpParam;
import com.zc.web.base.service.CCHttpReq;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.URLConfigHelper;

public class LoginAction extends ZCActionSupport {

	/**
	 * ��¼Action
	 * 
	 * session�е�userΪ�û���Ϣ��role��0Ϊ���Ȩ��
	 * 
	 * session�е�isOnline��1Ϊ�Ѿ���¼��0Ϊδ��¼��
	 */
	private static final long serialVersionUID = 10086L;

	/**
	 * ע���ӿ�
	 * 
	 */
	public String loginPage() {
		return "succ";
	}

	/**
	 * ��¼�ӿ�
	 * 
	 */
	public String login() {

		Log.Nano.tag("��¼�ӿ�", "��ʼ");

		init(true);

		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String role = request.getParameter("role");

		if (null == role) {

			Log.Nano.tag("��¼�ӿ�����ȱʧ from Web", "name:" + name, "pwd:" + pwd, "role:" + role);

			ERRCODE = "1";
			ERRDESC = "fail";
			data = "����������ô��Ϸ�ӿ�";

			writeResp("��¼�ӿ� resp to Web");

			return null;

		} else if (null == name || null == pwd) {

			Log.Nano.tag("��¼�ӿ�����ȱʧ from Web", "name:" + name, "pwd:" + pwd, "role:" + role);

			ERRCODE = "1";
			ERRDESC = "fail";
			data = "�������������û���������";

			writeResp("��¼�ӿ� resp to Web");

			return null;

		} else {

			ZCHttpParam param = new ZCHttpParam();
			param.addParam("name", name);
			param.addParam("pwd", pwd);
			param.addParam("role", role);

			// String httpResp =
			// CCHttpReq.sendGet("http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/LoginEC.action",
			// param);
			String httpResp = CCHttpReq.sendGet(URLConfigHelper.Url_LoginEC, param);

			String isOnline;
			String ec_user_id;
			String ec_user_name;
			String ec_user_role;

			Log.Nano.tag("��¼�ӿ� from EC", httpResp);

			JSONObject jsonHttpResp = JSONObject.fromObject(httpResp);
			String jsonERRCODE = jsonHttpResp.getString("ERRCODE");
			String jsonERRDESC = jsonHttpResp.getString("ERRDESC");

			if ("0".equals(jsonERRCODE)) {

				JSONObject jsonData = jsonHttpResp.getJSONObject("data");
				JSONObject jsonUser = jsonData.getJSONObject("user");

				isOnline = "1";
				ec_user_id = jsonUser.getString("ec_user_id");
				ec_user_name = jsonUser.getString("ec_user_name");
				ec_user_role = jsonUser.getString("ec_user_rank");

				session.setAttribute("isOnline", isOnline);
				session.setAttribute("ec_user_id", ec_user_id);
				session.setAttribute("ec_user_name", ec_user_name);
				session.setAttribute("ec_user_rank", ec_user_role);

				session.setAttribute("menulist", getMenuList());

				ERRCODE = "0";
				ERRDESC = "succ";
				data = "succ";

			} else {

				ERRCODE = "0";
				ERRDESC = "succ";
				data = "EC�����룺" + jsonERRCODE + " EC����������" + jsonERRDESC;

			}

			writeResp("��¼�ӿ� resp to Web");

			return null;
		}

	}

	/**
	 * ע���ӿ�
	 * 
	 */
	public String logout() {
		init(true);
		session.setAttribute("isOnline", "0");
		session.setAttribute("ec_user_id", null);
		session.setAttribute("ec_user_name", null);
		session.setAttribute("ec_user_role", null);
		Log.Nano.tag("ע��", "���");
		return "succ";
	}

	private String getMenuList() {
		String menuList = "<li><a href=\"/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action\">�߼�ģʽ</a></li>"//
				+ "<li><a href=\"/CtrlCenter/LTYX/Tailor/TailorForm/Design.action\">���ʦ�Ƽ���</a></li>"//
				+ "<li><a href=\"/CtrlCenter/LTYX/Tailor/TailorForm/Advisor.action\">���ƹ���</a></li>" //
				+ "<li><a href=\"/CtrlCenter/LTYX/Tailor/TailorForm/Other.action\">������Ʒ</a></li>" //
				+ "<li><a href=\"/CtrlCenter/UcsPlus/GetSearchPage.action\">����ѯ</a></li>";
		return menuList;

	}

}
