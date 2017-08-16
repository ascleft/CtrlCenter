package com.zc.web.base.service;

public class SystemInitHelper {

	public static void init() {
		Log.init(true, Log.STYLE_OPEN);
		VersionHelper.init();
		URLConfigHelper.init();
		DBConfigHelper.init();
		DebugManager.init();
	}

}
