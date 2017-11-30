package com.ltyx.sca.action;

import com.zc.support.doman.ZCBaseActionSupport;

public class SearchAction extends ZCBaseActionSupport {

	/**
	 *
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		session = SCAPageConfigCommon.manageMenu(session);
		session = SCAPageConfigCommon.manageTechnology(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		return "succ";

	}

}
