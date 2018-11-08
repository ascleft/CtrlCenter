package com.ltyx.sca.action.log;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.service.DBHelper;
import com.zc.support.service.TimeHelper;

public class ActionLogBeanLogin extends ZCBaseActionSupportPlugin {

	String name;
	String role;
	String ecid;
	String user;
	String code;
	String time;

	public ActionLogBeanLogin(HttpServletRequest req) {
		this.request = req;
	}

	public void addSrcParam(String name, String role, String ecid, String user, String code) {
		this.name = name;
		this.role = role;
		this.ecid = ecid;
		this.user = user;
		this.code = code;
		this.time = TimeHelper.getTimeHMSS();

	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		return true;
	}

	public String check() {

		String id = null;

		ArrayList<DBHelper.SelectBean> al_temp = DBHelper.select("id", "ecid").from("log_user").where("ecid = '" + ecid + "'").exe();
		if (al_temp.size() == 0) {
			// 记录登录账户
			id = DBHelper//
					.insert(name, role, ecid, user, code, time)//
					.columns("name", "role", "ecid", "user", "code", "time")//
					.into("log_user")//
					.exe();
		} else {
			id = al_temp.get(0).get("id");
		}

		return id;

	}

}
