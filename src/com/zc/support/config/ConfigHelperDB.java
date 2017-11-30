package com.zc.support.config;

public class ConfigHelperDB {

	final public static String STATE_RELEASE = "STATE_RELEASE";
	final public static String STATE_TEST = "STATE_TEST";
	final public static String STATE_CUSTOM = "STATE_CUSTOM";

	private static String STATE = STATE_RELEASE;

	public static String URL = "";
	public static String NAME = "";
	public static String PWD = "";

	public static void init() {
		if (STATE.equals(STATE_RELEASE)) {
			setDB("root", "jycsFactal150428!");
		} else {
			setDB("root", "junyi000726");
		}
	}

	public static void customDB(String NAME, String PWD) {
		setDB(NAME, PWD);
		STATE = STATE_CUSTOM;
	}

	private static void setDB(String NAME, String PWD) {
		ConfigHelperDB.URL = "jdbc:mysql://localhost:3306/ctrlcenter";
		ConfigHelperDB.NAME = NAME;
		ConfigHelperDB.PWD = PWD;
	}

}
