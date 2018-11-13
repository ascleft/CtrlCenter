package com.ltyx.sca.action;

import com.zc.support.doman.CCActionSupport;

public class SearchAction extends CCActionSupport {

	/**
	 *
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		session = SCAPageConfigCommon.manageMenu(session);
		session = SCAPageConfigCommon.manageTechnologyMan(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		return "succ";

	}

}
