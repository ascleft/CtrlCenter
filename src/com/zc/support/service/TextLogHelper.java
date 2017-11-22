package com.zc.support.service;

import java.io.File;

public class TextLogHelper {
	public static void white(String line) {
		File day = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), TimeHelper.getDateYMD() + ".txt");
		File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), TimeHelper.getTimeH() + ".txt");
		FileHelper.whiteFile(day, line);
		FileHelper.whiteFile(hour, line);
	}
}
