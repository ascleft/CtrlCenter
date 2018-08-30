package com.ltyx.sca.action;

import com.zc.support.doman.ZCBaseActionSupport;

public class CustomShopAideSearchAction extends ZCBaseActionSupport {

	/**
	 *
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		if (!"3071".equals(session.getAttribute("ec_user_id").toString()) && !"129".equals(session.getAttribute("ec_user_id").toString())){
			AuthorizeAssistan.check(session.getAttribute("ec_user_rank").toString(), response, "0");
		}
		
		session = SCAPageConfigCommon.manageMenu(session);
		session = SCAPageConfigCommon.manageTechnologyMan(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		return "succ";

	}

}
