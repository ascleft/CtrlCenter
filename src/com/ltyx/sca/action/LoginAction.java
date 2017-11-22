package com.ltyx.sca.action;

import net.sf.json.JSONObject;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.Log;
import com.zc.support.service.NumberHelper;

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
	public String getPage() {
		return "succ";
	}

	/**
	 * 登录接口
	 * 
	 */
	public String login() {

		init(true);

		String methodName = "SCA 登录";

		ZCReqIntroGetter.showParams(methodName, request);

		doLogin();

		writeResp(methodName);

		return null;

	}

	/**
	 * 注销接口
	 * 
	 */
	public String logout() {

		init(true);

		String methodName = "SCA 注销";

		doLogout();

		logProgress(methodName);

		return "succ";
	}

	private boolean doLogin() {

		String name = getReqParamString("name");
		String pwd = getReqParamString("pwd");
		String role = getReqParamString("role");

		if (null == role) {
			ERRCODE = "1";
			ERRDESC = "fail";
			data = "弱鸡，少特么调戏接口";
			addProgressFail("登录状态权限丢失");
		} else if (null == name || null == pwd) {
			ERRCODE = "1";
			ERRDESC = "fail";
			data = "请输入完整的用户名和密码";
			addProgressFail("登录状态用户信息丢失");
		} else {
			ZCHttpReqParam param = new ZCHttpReqParam();
			param.addParam("name", name);
			param.addParam("pwd", pwd);
			param.addParam("role", role);

			String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_LoginEC, param);

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

				String targetUrl = "";
				int roleCheck = NumberHelper.string2int(ec_user_role);
				if (0 <= roleCheck && roleCheck < 10) {
					if (jsonUser.getString("ec_user_rank").length() > 2) {
						if (ec_user_name.equals("zc") || ec_user_name.equals("张弛")) {
							ERRCODE = "0";
							ERRDESC = "succ";
							data = "权限异常";
							addProgressFail("EC权限异常,当前采用特权登录");
						} else {
							ERRCODE = "0";
							ERRDESC = "fail";
							data = "权限异常";
							addProgressFail("EC权限异常");
							return false;
						}
					}
					targetUrl = "/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYX.action";// 客户经理
				} else if (10 <= roleCheck && roleCheck < 20) {
					targetUrl = "/CtrlCenter/LTYX/SCA/Main/AidePBYX.action";// 定制顾问
				} else if (20 <= roleCheck && roleCheck < 30) {
					targetUrl = "/CtrlCenter/LTYX/SCA/Main/CustomShopPBYX.action";// 定制店
				}

				ERRCODE = "0";
				ERRDESC = "succ";
				data = targetUrl;
				addProgressSucc("登录成功:" + " name:" + ec_user_name + " id:" + ec_user_id + " role:" + ec_user_role);

			} else {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "EC错误码：" + jsonERRCODE + " EC错误描述：" + jsonERRDESC;
				addProgressFail(data);
			}
		}

		return true;
	}

	private boolean doLogout() {
		session.setAttribute("isOnline", "0");
		session.setAttribute("ec_user_id", null);
		session.setAttribute("ec_user_name", null);
		session.setAttribute("ec_user_role", null);
		addProgressSucc("注销完成");
		return true;
	}

}
