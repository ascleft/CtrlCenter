package com.zc.web.support.config;

public class ConfigHelperURL {

	final public static String STATE_RELEASE = "STATE_RELEASE";
	final public static String STATE_TEST = "STATE_TEST";;
	final public static String STATE_CUSTOM = "STATE_CUSTOM";

	private static String STATE = STATE_RELEASE;

	private static String BaseUrl_RELEASE = "http://www.utailor.com.cn";
	private static String BaseUrl_TEST = "http://101.200.159.174";

	private static String url_LoginEC = "/index.php/openapi/uskinapi/tool_check_login";
	public static String Url_LoginEC = "";
	private static String url_SubTailor = "/index.php/openapi/uskinapi/tool_add_cart";
	public static String Url_SubTailor = "";

	public static void init() {
		if (STATE.equals(STATE_RELEASE)) {
			setUrl(BaseUrl_RELEASE);
		} else {
			setUrl(BaseUrl_TEST);
		}
	}

	public static void customUrl(String baseurl) {
		setUrl(baseurl);
		STATE = STATE_CUSTOM;
	}

	private static void setUrl(String baseurl) {
		Url_LoginEC = baseurl + url_LoginEC;
		Url_SubTailor = baseurl + url_SubTailor;
	}

}
