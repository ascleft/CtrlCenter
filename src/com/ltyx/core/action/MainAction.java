package com.ltyx.core.action;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.service.VersionHelper;

public class MainAction extends ZCBaseActionSupport {

	/**
	 * 该Action用于非功能页面的自动跳转
	 * 
	 */
	private static final long serialVersionUID = 10085L;

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------

	public String getPage() {

		init(true);

		session.setAttribute("SystemCore", VersionHelper.getCORE());
		session.setAttribute("SystemName", VersionHelper.getNAME());
		session.setAttribute("SystemVersion", VersionHelper.getVERSION());

		return "succ";

	}

}
