package com.zc.support.service;

import java.io.File;

public class TextLogHelper2 {

	public static class Type {

		public static final String[][] GLOBAL = new String[][] { { "Global" } };

		public static final String[][] USKIN_LOGIN_SUCC = new String[][] { { "USKIN", "LOGIN", "SUCC" }, GLOBAL[0] };
		public static final String[][] USKIN_LOGIN_FAIL = new String[][] { { "USKIN", "LOGIN", "FAIL" }, GLOBAL[0] };
		public static final String[][] USKIN_LOGIN_NSRC = new String[][] { { "USKIN", "LOGIN", "NSRC" }, GLOBAL[0] };

		public static final String[][] USKIN_AIDE_SUCC = new String[][] { { "USKIN", "AIDE", "SUCC" }, GLOBAL[0] };
		public static final String[][] USKIN_AIDE_FAIL = new String[][] { { "USKIN", "AIDE", "FAIL" }, GLOBAL[0] };
		public static final String[][] USKIN_AIDE_NSRC = new String[][] { { "USKIN", "AIDE", "NSRC" }, GLOBAL[0] };

		public static final String[][] USKIN_USER_SUCC = new String[][] { { "USKIN", "USER", "SUCC" }, GLOBAL[0] };
		public static final String[][] USKIN_USER_FAIL = new String[][] { { "USKIN", "USER", "FAIL" }, GLOBAL[0] };
		public static final String[][] USKIN_USER_NSRC = new String[][] { { "USKIN", "USER", "NSRC" }, GLOBAL[0] };

		public static final String[][] USKIN_YBR_SUCC = new String[][] { { "USKIN", "YBR", "SUCC" }, GLOBAL[0] };
		public static final String[][] USKIN_YBR_FAIL = new String[][] { { "USKIN", "YBR", "FAIL" }, GLOBAL[0] };
		public static final String[][] USKIN_YBR_NSRC = new String[][] { { "USKIN", "YBR", "NSRC" }, GLOBAL[0] };

		public static final String[][] UNITY_SEARCH_SUCC = new String[][] { { "UNITY", "SEARCH", "SUCC" }, GLOBAL[0] };
		public static final String[][] UNITY_SEARCH_FAIL = new String[][] { { "UNITY", "SEARCH", "FAIL" }, GLOBAL[0] };
		public static final String[][] UNITY_SEARCH_NSRC = new String[][] { { "UNITY", "SEARCH", "NSRC" }, GLOBAL[0] };

		public static final String[][] TEST_JSJ_NSRC = new String[][] { { "TEST", "JSJ", "NSRC" }, GLOBAL[0] };

	}

	public static void white(String line, String logType[][]) {
		for (int i = 0; i < logType.length; i++) {
			for (int j = 0; j < logType[i].length; j++) {

				String[] floders_root = new String[3];
				String[] floders_day_ = new String[j + 1 + 3];
				String[] floders_hour = new String[j + 1 + 3];
				for (int k = 0; k < j + 1; k++) {
					floders_day_[k + 2] = logType[i][k];
					floders_hour[k + 2] = logType[i][k];
				}

				floders_root[0] = "LOG2CtrlCenter";
				floders_root[1] = TimeHelper.getDateYMD();
				floders_root[2] = TimeHelper.getDateYMD() + ".txt";

				floders_day_[0] = "LOG2CtrlCenter";
				floders_day_[1] = TimeHelper.getDateYMD();
				floders_day_[j + 1 + 2] = TimeHelper.getDateYMD() + ".txt";

				floders_hour[0] = "LOG2CtrlCenter";
				floders_hour[1] = TimeHelper.getDateYMD();
				floders_hour[j + 1 + 2] = TimeHelper.getTimeH() + ".txt";

				File root = FileHelper.getFile(floders_root);
				File day_ = FileHelper.getFile(floders_day_);
				File hour = FileHelper.getFile(floders_hour);
				FileHelper.writeFile(root, line);
				FileHelper.writeFile(day_, line);
				FileHelper.writeFile(hour, line);
				
			}
		}
	}

	public static void white(String line) {

		white(line, Type.GLOBAL);

	}

}
