package com.ltyx.tailor.action;

import net.sf.json.JSONObject;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;
import com.zc.support.service.LogType;

public class LoginAction extends ZCBaseActionSupport {

	/**
	 * 登录Action
	 * 
	 * session中的user为用户信息，role中0为最高权限
	 * 
	 * session中的isOnline：1为已经登录，0为未登录。
	 */
	private static final long serialVersionUID = 10086L;

	/**
	 * 获取登录页面
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

		String name = getReqParamString("name");
		String pwd = getReqParamString("pwd");
		String role = getReqParamString("role");

		if (null == role) {

			Log.Nano.tag("登录接口数据缺失 from Web", "name:" + name, "pwd:" + pwd, "role:" + role);

			ERRCODE = "1";
			ERRDESC = "fail";
			data = "弱鸡，少特么调戏接口";

			writeResp("登录接口 resp to Web", LogType.NORMAL);

			return null;

		} else if (null == name || null == pwd) {

			Log.Nano.tag("登录接口数据缺失 from Web", "name:" + name, "pwd:" + pwd, "role:" + role);

			ERRCODE = "1";
			ERRDESC = "fail";
			data = "请输入完整的用户名和密码";

			writeResp("登录接口 resp to Web", LogType.NORMAL);

			return null;

		} else {

			ZCHttpReqParam param = new ZCHttpReqParam();
			param.addParam("name", name);
			param.addParam("pwd", pwd);
			param.addParam("role", role);

			String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_LoginEC.getUrl(), param);

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

				ERRCODE = "0";
				ERRDESC = "succ";
				data = "succ";

			} else {

				ERRCODE = "0";
				ERRDESC = "succ";
				data = "EC错误码：" + jsonERRCODE + " EC错误描述：" + jsonERRDESC;

			}

			writeResp("登录接口 resp to Web", LogType.NORMAL);

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

}
