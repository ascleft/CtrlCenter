package com.zc.support.config;

public class ConfigHelperURL {

	final public static String STATE_RELEASE = "STATE_RELEASE";
	final public static String STATE_TEST = "STATE_TEST";;
	final public static String STATE_CUSTOM = "STATE_CUSTOM";

	private static String STATE = STATE_RELEASE;

	private static String BaseUrl_RELEASE = "http://www.utailor.com.cn";
	private static String BaseUrl_TEST = "http://tool.uskin.net.cn/";

	// 下单工具
	private static String url_LoginEC = "/index.php/openapi/uskinapi/tool_check_login";
	public static String Url_LoginEC = "";
	private static String url_SubTailor = "/index.php/openapi/uskinapi/tool_add_cart";
	public static String Url_SubTailor = "";

	// SCA
	private static String url_aide_get_price_pbyx = "/index.php/openapi/uskinapi/body_trainer_freechoice_price";
	public static String Url_aide_get_price_pbyx = "";// 定制顾问 衬衫 报价 ↑
	private static String url_aide_add_cart_pbyx = "/index.php/openapi/uskinapi/body_trainer_freechoice_add_cart";
	public static String Url_aide_add_cart_pbyx = "";// 定制顾问 衬衫 提交 ↑
	private static String url_aide_get_price_design = "/index.php/openapi/uskinapi/body_trainer_recommendlist_price";
	public static String Url_aide_get_price_design = "";// 定制顾问 设计师款 报价 ↑
	private static String url_aide_add_cart_design = "/index.php/openapi/uskinapi/body_trainer_recommendlist_add_cart";
	public static String Url_aide_add_cart_design = "";// 定制顾问 设计师款 提交 ↑
	private static String url_aide_add_cart_subcontract = "/index.php/openapi/uskinapi/body_trainer_other_goods_add_cart";
	public static String Url_aide_add_cart_subcontract = "";// 定制顾问 其他商品 提交 ↑
	private static String url_aide_add_cart_woman = "/index.php/openapi/uskinapi/body_trainer_other_goods_add_cart";
	public static String Url_aide_add_cart_woman = "";// 定制顾问 女装 提交 ↑--------------------------------------

	private static String url_customshop_get_price_pbyx = "/index.php/openapi/uskinapi/bespoke_shop_freechoice_price";
	public static String Url_customshop_get_price_pbyx = "";// 定制店 衬衫 报价 ↑
	private static String url_customshop_add_cart_pbyx = "/index.php/openapi/uskinapi/bespoke_shop_freechoice_add_cart";
	public static String Url_customshop_add_cart_pbyx = "";// 定制店 衬衫 提交 ↑
	private static String url_customshop_add_cart_pbc = "/index.php/openapi/uskinapi/bespoke_shop_cust_fabric_add_cart";
	public static String Url_customshop_add_cart_pbc = "";// 定制店 衬衫 客供面料 提交 ↑
	private static String url_customshop_add_cart_woman = "/index.php/openapi/uskinapi/bespoke_shop_freechoice_add_cart";
	public static String Url_customshop_add_cart_woman = "";// 定制店 女装 提交 ↑--------------------------------------

	private static String url_customshopaide_get_price_pbyx = "/index.php/openapi/uskinapi/bespoke_shop_freechoice_price";
	public static String Url_customshopaide_get_price_pbyx = "";// 客户经理 衬衫 报价 ↑
	private static String url_customshopaide_add_cart_pbyx = "/index.php/openapi/uskinapi/manager_freechoice_add_cart";
	public static String Url_customshopaide_add_cart_pbyx = "";// 客户经理 衬衫 提交 ↑
	private static String url_customshopaide_add_cart_pbc = "/index.php/openapi/uskinapi/manager_custfabric_add_cart";
	public static String Url_customshopaide_add_cart_pbc = "";// 客户经理 客供面料 提交 ↑
	private static String url_customshopaide_add_cart_woman = "/index.php/openapi/uskinapi/manager_freechoice_add_cart";
	public static String Url_customshopaide_add_cart_woman = "";// 客户经理 女装 提交 ↑--------------------------------------

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

		// 定制顾问 自由搭配
		Url_aide_get_price_pbyx = baseurl + url_aide_get_price_pbyx;
		Url_aide_add_cart_pbyx = baseurl + url_aide_add_cart_pbyx;
		// 定制顾问 设计师推荐款
		Url_aide_get_price_design = baseurl + url_aide_get_price_design;
		Url_aide_add_cart_design = baseurl + url_aide_add_cart_design;
		// 定制顾问 其他商品
		Url_aide_add_cart_subcontract = baseurl + url_aide_add_cart_subcontract;
		// 定制顾问 女装
		Url_customshopaide_add_cart_woman= baseurl + url_customshopaide_add_cart_woman;

		// 定制店 自由搭配
		Url_customshop_get_price_pbyx = baseurl + url_customshop_get_price_pbyx;
		Url_customshop_add_cart_pbyx = baseurl + url_customshop_add_cart_pbyx;
		// 定制店 客供面料
		Url_customshop_add_cart_pbc = baseurl + url_customshop_add_cart_pbc;
		// 定制店 女装
		Url_customshop_add_cart_woman= baseurl + url_customshop_add_cart_woman;

		// 客户经理 自由搭配
		Url_customshopaide_get_price_pbyx = baseurl + url_customshopaide_get_price_pbyx;
		Url_customshopaide_add_cart_pbyx = baseurl + url_customshopaide_add_cart_pbyx;
		// 客户经理 客供面料
		Url_customshopaide_add_cart_pbc = baseurl + url_customshopaide_add_cart_pbc;
		// 客户经理 女装
		Url_customshop_add_cart_woman= baseurl + url_customshop_add_cart_woman;

	}

}
