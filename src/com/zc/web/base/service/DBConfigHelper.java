package com.zc.web.base.service;

public class DBConfigHelper {

	final public static String STATE_RELEASE = "STATE_RELEASE";
	final public static String STATE_TEST = "STATE_TEST";;
	final public static String STATE_CUSTOM = "STATE_CUSTOM";

	private static String STATE = STATE_TEST;

	public static String URL = "";
	public static String NAME = "";
	public static String PWD = "";

	public static void init() {
		if (STATE.equals(STATE_RELEASE)) {
			URL = "jdbc:mysql://localhost:3306/ctrlcenter";
			NAME = "root";
			PWD = "jycsFactal150428!";
		} else {
			URL = "jdbc:mysql://localhost:3306/ctrlcenter";
			NAME = "root";
			PWD = "junyi000726";
		}
	}

	public static void customDB(String NAME, String PWD) {
		setDB(NAME, PWD);
		STATE = STATE_CUSTOM;
	}

	private static void setDB(String NAME, String PWD) {
		DBConfigHelper.URL = "jdbc:mysql://localhost:3306/ctrlcenter";
		DBConfigHelper.NAME = NAME;
		DBConfigHelper.PWD = PWD;
	}

}
