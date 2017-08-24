package com.zc.support.service;

import com.zc.support.config.ConfigHelperDB;
import com.zc.support.config.ConfigHelperURL;

public class SystemInitHelper {

	public static void init() {
		Log.init(true, Log.STYLE_OPEN);
		VersionHelper.init();
		ConfigHelperURL.init();
		ConfigHelperDB.init();
		LogHelper.init();
	}

}
