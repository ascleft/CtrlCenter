package com.zc.support.service;

import java.util.ArrayList;

import com.zc.support.config.ConfigHelperDB;
import com.zc.support.config.ConfigHelperURL;

public class SystemInitHelper {

	public static void init() {

		ArrayList<String> infos = new ArrayList<String>();

		ConfigHelperURL.init();
		ConfigHelperDB.init();

		infos.add("运算核心");
		infos.addAll(VersionHelper.init());
		infos.add("调试模块");
		infos.addAll(LogHelper.init());

		Log.Pro.start();
		Log.Pro.whiteLine("系统初始化");
		Log.Pro.whiteCut();
		for (String info : infos) {
			Log.Pro.whiteLine(info);
		}
		Log.Pro.finish();

	}

}
