package com.ltyx.tailor.action;

import net.sf.json.JSONObject;

import com.zc.web.base.doman.ZCActionSupport;
import com.zc.web.base.doman.ZCHttpParam;
import com.zc.web.base.service.ZCHttpReq;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.URLConfigHelper;

public class LoginAction extends ZCActionSupport {

	/**
	 * 登录Action
	 *
	 * session中的user为用户信息，role中0为最高权限
	 *
	 * session中的isOnline：1为已经登录，0为未登录。
	 */
	private static final long serialVersionUID = 10086L;

	/**
	 * 注销接口
	 *
	 */
	public String loginPage() {
		return "succ";
	}

	/**
	 * 登录接口
	 *
	 */
	public String login() {

		Log.Nano.tag("登录接口", "开始");

		init(true);

		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String role = request.getParameter("role");

		if (null == role) {

			Log.Nano.tag("登录接口数据缺失 from Web", "name:" + name, "pwd:" + pwd, "role:" + role);

			ERRCODE = "1";
			ERRDESC = "fail";
			data = "弱鸡，少特么调戏接口";

			writeResp("登录接口 resp to Web");

			return null;

		} else if (null == name || null == pwd) {

			Log.Nano.tag("登录接口数据缺失 from Web", "name:" + name, "pwd:" + pwd, "role:" + role);

			ERRCODE = "1";
			ERRDESC = "fail";
			data = "请输入完整的用户名和密码";

			writeResp("登录接口 resp to Web");

			return null;

		} else {

			ZCHttpParam param = new ZCHttpParam();
			param.addParam("name", name);
			param.addParam("pwd", pwd);
			param.addParam("role", role);

			// String httpResp =
			// CCHttpReq.sendGet("http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/LoginEC.action",
			// param);
			String httpResp = ZCHttpReq.sendGet(URLConfigHelper.Url_LoginEC, param);

			String isOnline;
			String ec_user_id;
			String ec_user_name;
			String ec_user_role;

			Log.Nano.tag("登录接口 from EC", httpResp);

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
				data = "EC错误码：" + jsonERRCODE + " EC错误描述：" + jsonERRDESC;

			}

			writeResp("登录接口 resp to Web");

			return null;
		}

	}

	/**
	 * 注销接口
	 *
	 */
	public String logout() {
		init(true);
		session.setAttribute("isOnline", "0");
		session.setAttribute("ec_user_id", null);
		session.setAttribute("ec_user_name", null);
		session.setAttribute("ec_user_role", null);
		Log.Nano.tag("注销", "完成");
		return "succ";
	}

	private String getMenuList() {
		String menuList = "<li><a href=\"/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action\">高级模式</a></li>"//
				+ "<li><a href=\"/CtrlCenter/LTYX/Tailor/TailorForm/Design.action\">设计师推荐款</a></li>"//
				+ "<li><a href=\"/CtrlCenter/LTYX/Tailor/TailorForm/Advisor.action\">定制顾问</a></li>" //
				+ "<li><a href=\"/CtrlCenter/LTYX/Tailor/TailorForm/Other.action\">其他商品</a></li>" //
				+ "<li><a href=\"/CtrlCenter/UcsPlus/GetSearchPage.action\">库存查询</a></li>";
		return menuList;

	}

}
