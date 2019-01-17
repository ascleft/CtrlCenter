package com.ltyx.sca.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizeAssistan {

	private static HashMap<String, String> dic = null;
	private static String[][] dic_su = { //
			{ "129", "0", "SU 张弛-客户经理" }, //
			{ "3071", "20", "SU 张弛-定制店" }, //
			{ "3071", "21", "SU 张弛-定制店-独立密码" }, //
			{ "3000", "20", "SU 顾振-定制店" }, //
			{ "3000", "21", "SU 顾振-定制店-独立密码" }, //
			{ "21000", "20", "SU 刘亚朋" }, //
			{ "21000", "21", "SU 刘亚朋-独立密码" },//
	};

	private static void initAuthorizeAssistan() {

		dic = new HashMap<String, String>();

		dic.put("0", "/CtrlCenter/LTYX/SCA/Main/AidePBYX.action");// 定制顾问

		dic.put("10", "/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYX.action");// 客户经理

		dic.put("20", "/CtrlCenter/LTYX/SCA/Main/CustomShopPBYX.action");// 定制店
		dic.put("21", "/CtrlCenter/LTYX/SCA/Main/CustomShopPBYX.action");// 定制店

	}

	public static void check(HttpSession session, HttpServletResponse response, String... pageAuth) {

		boolean isLegal = false;

		String role = session.getAttribute("ec_user_rank").toString();

		if (dic == null) {
			initAuthorizeAssistan();
		}

		if (!isSU(session)) {

			for (int i = 0; i < pageAuth.length; i++) {
				isLegal = pageAuth[i].equals(role);
				if (isLegal) {
					break;
				}
			}

			if (!isLegal) {
				for (Entry<String, String> entry : dic.entrySet()) {
					if (entry.getKey().equals(role)) {
						try {
							response.sendRedirect(entry.getValue());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
				}
			}

		}

	}

	public static boolean isSU(HttpSession session) {

		boolean isSU = false;

		{
			String idTemp = "" + session.getAttribute("ec_user_id");
			String rankTemp = "" + session.getAttribute("ec_user_rank");
			for (int i = 0; i < dic_su.length; i++) {
				if (idTemp.equals(dic_su[i][0]) && rankTemp.equals(dic_su[i][1])) {
					isSU = true;
				}
			}
		}

		return isSU;
	}

	public static String getSU(HttpSession session) {

		String name = "";

		{
			String idTemp = "" + session.getAttribute("ec_user_id");
			String rankTemp = "" + session.getAttribute("ec_user_rank");
			for (int i = 0; i < dic_su.length; i++) {
				if (idTemp.equals(dic_su[i][0]) && rankTemp.equals(dic_su[i][1])) {
					name = dic_su[i][2];
				}
			}
		}

		return name;
	}

}
