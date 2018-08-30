package com.ltyx.sca.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

public class AuthorizeAssistan {

	public static HashMap<String, String> dic = null;

	public static void initAuthorizeAssistan() {
		
		dic = new HashMap<String, String>();

		dic.put("0", "/CtrlCenter/LTYX/SCA/Main/AidePBYX.action");// 定制顾问

		dic.put("10", "/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYX.action");// 客户经理

		dic.put("20", "/CtrlCenter/LTYX/SCA/Main/CustomShopPBYX.action");// 定制店
		dic.put("21", "/CtrlCenter/LTYX/SCA/Main/CustomShopPBYX.action");// 定制店

	}

	public static void check(String role, HttpServletResponse response, String... pageAuth) {

		boolean isLegal = false;

		if (dic == null) {
			initAuthorizeAssistan();
		}

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
