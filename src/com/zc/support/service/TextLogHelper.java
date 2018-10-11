package com.zc.support.service;

import java.io.File;

public class TextLogHelper {

	public static class Type {

		public static final String[][] GLOBAL = new String[][] { { "Global" } };

		public static final String[][] UNDEFINED = new String[][] { { "Undefined" }, GLOBAL[0] };

		public static final String[][] USKIN_LOGIN_SUCC = new String[][] { { "USKIN", "LOGIN", "SUCC" }, GLOBAL[0] };
		public static final String[][] USKIN_LOGIN_FAIL = new String[][] { { "USKIN", "LOGIN", "FAIL" }, GLOBAL[0] };
		public static final String[][] USKIN_LOGIN_NSRC = new String[][] { { "USKIN", "LOGIN", "NSRC" }, GLOBAL[0] };
		public static final String[][] USKIN_LOGOUT = new String[][] { { "USKIN", "LOGOUT" }, GLOBAL[0] };

		public static final String[][] USKIN_AIDE_PRICE_SUCC = new String[][] { { "USKIN", "AIDE", "PRICE", "SUCC" }, GLOBAL[0] };
		public static final String[][] USKIN_AIDE_PRICE_FAIL = new String[][] { { "USKIN", "AIDE", "PRICE", "FAIL" }, GLOBAL[0] };
		public static final String[][] USKIN_AIDE_PRICE_NSRC = new String[][] { { "USKIN", "AIDE", "PRICE", "NSRC" }, GLOBAL[0] };
		public static final String[][] USKIN_AIDE_ORDER_SUCC = new String[][] { { "USKIN", "AIDE", "ORDER", "SUCC" }, GLOBAL[0] };
		public static final String[][] USKIN_AIDE_ORDER_FAIL = new String[][] { { "USKIN", "AIDE", "ORDER", "FAIL" }, GLOBAL[0] };
		public static final String[][] USKIN_AIDE_ORDER_NSRC = new String[][] { { "USKIN", "AIDE", "ORDER", "NSRC" }, GLOBAL[0] };

		public static final String[][] USKIN_USER_PRICE_SUCC = new String[][] { { "USKIN", "USER", "PRICE", "SUCC" }, GLOBAL[0] };
		public static final String[][] USKIN_USER_PRICE_FAIL = new String[][] { { "USKIN", "USER", "PRICE", "FAIL" }, GLOBAL[0] };
		public static final String[][] USKIN_USER_PRICE_NSRC = new String[][] { { "USKIN", "USER", "PRICE", "NSRC" }, GLOBAL[0] };
		public static final String[][] USKIN_USER_ORDER_SUCC = new String[][] { { "USKIN", "USER", "ORDER", "SUCC" }, GLOBAL[0] };
		public static final String[][] USKIN_USER_ORDER_FAIL = new String[][] { { "USKIN", "USER", "ORDER", "FAIL" }, GLOBAL[0] };
		public static final String[][] USKIN_USER_ORDER_NSRC = new String[][] { { "USKIN", "USER", "ORDER", "NSRC" }, GLOBAL[0] };

		public static final String[][] USKIN_YBR_SUCC = new String[][] { { "USKIN", "YBR", "SUCC" }, GLOBAL[0] };
		public static final String[][] USKIN_YBR_FAIL = new String[][] { { "USKIN", "YBR", "FAIL" }, GLOBAL[0] };
		public static final String[][] USKIN_YBR_NSRC = new String[][] { { "USKIN", "YBR", "NSRC" }, GLOBAL[0] };

		public static final String[][] UNITY_SEARCH_SUCC = new String[][] { { "UNITY", "SEARCH", "SUCC" }, GLOBAL[0] };
		public static final String[][] UNITY_SEARCH_FAIL = new String[][] { { "UNITY", "SEARCH", "FAIL" }, GLOBAL[0] };
		public static final String[][] UNITY_SEARCH_NSRC = new String[][] { { "UNITY", "SEARCH", "NSRC" }, GLOBAL[0] };

		public static final String[][] ERP600_SUBMIT_SUCC = new String[][] { { "ERP600", "SUBMIT", "SUCC" }, GLOBAL[0] };
		public static final String[][] ERP600_SUBMIT_FAIL = new String[][] { { "ERP600", "SUBMIT", "FAIL" }, GLOBAL[0] };
		public static final String[][] ERP600_SUBMIT_NSRC = new String[][] { { "ERP600", "SUBMIT", "NSRC" }, GLOBAL[0] };

		public static final String[][] ERP600_ORDER_STATE = new String[][] { { "ERP600", "ORDER_STATE" }, GLOBAL[0] };

		public static final String[][] OTHER_UCSPLUS_SEARCH = new String[][] { { "OTHER", "UCSPLUS", "SEARCH" }, GLOBAL[0] };
		public static final String[][] OTHER_UCSPLUS_INTERFACETEST = new String[][] { { "OTHER", "UCSPLUS", "INTERFACETEST" }, GLOBAL[0] };

		public static final String[][] TEST_JSJ_NSRC = new String[][] { { "TEST", "JSJ", "NSRC" }, GLOBAL[0] };
		public static final String[][] TEST_MTM_NSRC = new String[][] { { "TEST", "MTM", "NSRC" }, GLOBAL[0] };

	}

	public static void white(String line, String logType[][]) {
		
		for (int i = 0; i < logType.length; i++) {
			for (int j = 0; j < logType[i].length; j++) {

				String[] floders_day_ = new String[j + 1 + 3];
				String[] floders_hour = new String[j + 1 + 3];
				for (int k = 0; k < j + 1; k++) {
					floders_day_[k + 2] = logType[i][k];
					floders_hour[k + 2] = logType[i][k];
				}

				floders_day_[0] = "LOG2CtrlCenter";
				floders_day_[1] = TimeHelper.getDateYMD();
				floders_day_[j + 1 + 2] = TimeHelper.getDateYMD() + ".txt";

				floders_hour[0] = "LOG2CtrlCenter";
				floders_hour[1] = TimeHelper.getDateYMD();
				floders_hour[j + 1 + 2] = TimeHelper.getTimeH() + ".txt";

				File day_ = FileHelper.getFile(floders_day_);
				File hour = FileHelper.getFile(floders_hour);
				FileHelper.writeFile(day_, line);
				FileHelper.writeFile(hour, line);

			}
		}

		String[] floders_root = new String[3];
		floders_root[0] = "LOG2CtrlCenter";
		floders_root[1] = TimeHelper.getDateYMD();
		floders_root[2] = TimeHelper.getDateYMD() + ".txt";
		File root = FileHelper.getFile(floders_root);
		FileHelper.writeFile(root, line);

	}

	public static void white(String line) {

		white(line, Type.UNDEFINED);

	}

}
