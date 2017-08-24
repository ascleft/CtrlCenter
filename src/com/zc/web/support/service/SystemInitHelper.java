package com.zc.web.support.service;

import com.zc.web.support.config.ConfigHelperDB;
import com.zc.web.support.config.ConfigHelperURL;

public class SystemInitHelper {

	public static void init() {
		Log.init(true, Log.STYLE_OPEN);
		VersionHelper.init();
		ConfigHelperURL.init();
		ConfigHelperDB.init();
		DebugManager.init();
	}

}
