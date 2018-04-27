package com.zc.support.service;

import java.io.File;

public class TextLogHelper {
	public static void white(String line) {
		File day = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), TimeHelper.getDateYMD() + ".txt");
		File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), TimeHelper.getTimeH() + ".txt");
		FileHelper.writeFile(day, line);
		FileHelper.writeFile(hour, line);
	}

	public static void white(String line, String type) {
		switch (type) {
		case LogType.Normal:
			white(line);
			break;

		default:
			break;
		}
	}

}
