package com.zc.web.base.service;

public class DebugManager {

	public static void init() {
		Log.init(true, Log.STYLE_OPEN);
		showDebugList();
	}

	private static void showDebugList() {
		if (Log.debug) {
			Log.Nano.TagByLine("Log Section", "Log.debug" + " : " + Log.debug, "Log.style" + " : " + Log.style);
		} else {
			System.err.println("Log System off !");
		}
	}

}
