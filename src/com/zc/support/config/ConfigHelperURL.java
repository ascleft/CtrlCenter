package com.zc.support.config;

import java.util.ArrayList;

import com.zc.support.service.StringHelper;

public class ConfigHelperURL {

	final public static String STATE_RELEASE = "STATE_RELEASE";
	final public static String STATE_TEST = "STATE_TEST";;
	final public static String STATE_CUSTOM = "STATE_CUSTOM";

	private static String STATE = STATE_RELEASE;

	private static String BaseUrl_RELEASE = "http://www.utailor.com.cn";
	private static String BaseUrl_TEST = "http://tool.uskin.net.cn/";

	// 下单工具1.0
	public static ZCUrl Url_LoginEC = new ZCUrl("下单工具1.0 登录", "/index.php/openapi/uskinapi/tool_check_login");
	public static ZCUrl Url_SubTailor = new ZCUrl("下单工具1.0 添加购物车", "/index.php/openapi/uskinapi/tool_add_cart");

	// 下单工具2.0 SCA
	public static ZCUrl Url_aide_get_price_pbyx            = new ZCUrl("下单工具2.0 定制顾问 衬衫 报价 ", "/index.php/openapi/uskinapi/body_trainer_freechoice_price");
	public static ZCUrl Url_aide_add_cart_pbyx             = new ZCUrl("下单工具2.0 定制顾问 衬衫 提交 ", "/index.php/openapi/uskinapi/body_trainer_freechoice_add_cart");
	public static ZCUrl Url_aide_get_price_design          = new ZCUrl("下单工具2.0 定制顾问 设计师款 报价", "/index.php/openapi/uskinapi/body_trainer_recommendlist_price");
	public static ZCUrl Url_aide_add_cart_design           = new ZCUrl("下单工具2.0 定制顾问 设计师款 提交", "/index.php/openapi/uskinapi/body_trainer_recommendlist_add_cart");
	public static ZCUrl Url_aide_get_price_subcontract     = new ZCUrl("下单工具2.0 定制顾问 其他商品 报价", "/index.php/openapi/uskinapi/body_trainer_other_goods_price");
	public static ZCUrl Url_aide_add_cart_subcontract      = new ZCUrl("下单工具2.0 定制顾问 其他商品 提交", "/index.php/openapi/uskinapi/body_trainer_other_goods_add_cart");
	public static ZCUrl Url_aide_add_cart_woman            = new ZCUrl("下单工具2.0 定制顾问 女装 提交 ", "/index.php/openapi/uskinapi/body_trainer_add_cart_for_mygirl");

	public static ZCUrl Url_customshop_get_price_pbyx      = new ZCUrl("下单工具2.0 定制店 衬衫 报价", "/index.php/openapi/uskinapi/bespoke_shop_freechoice_price");
	public static ZCUrl Url_customshop_get_price_pbc       = new ZCUrl("下单工具2.0 定制店 客供 报价", "/index.php/openapi/uskinapi/get_cust_fabric_price");
	public static ZCUrl Url_customshop_add_cart_pbyx       = new ZCUrl("下单工具2.0 定制店 衬衫 提交", "/index.php/openapi/uskinapi/bespoke_shop_freechoice_add_cart");
	public static ZCUrl Url_customshop_add_cart_pbc        = new ZCUrl("下单工具2.0 定制店 衬衫 客供面料 提交", "/index.php/openapi/uskinapi/bespoke_shop_cust_fabric_add_cart");
	public static ZCUrl Url_customshop_add_cart_woman      = new ZCUrl("下单工具2.0 定制店 女装 提交", "/index.php/openapi/uskinapi/bespoke_shop_add_cart_for_mygirl");
	public static ZCUrl Url_customshop_add_cart_design     = new ZCUrl("下单工具2.0 定制店 设计师款 提交", "/index.php/openapi/shirtapi/bespoke_shop_recommendlist_add_cart");

	public static ZCUrl Url_customshopaide_get_price_pbyx  = new ZCUrl("下单工具2.0 客户经理 衬衫 报价", "/index.php/openapi/uskinapi/bespoke_shop_freechoice_price");
	public static ZCUrl Url_customshopaide_get_price_pbc   = new ZCUrl("下单工具2.0 客户经理 客供 报价", "/index.php/openapi/uskinapi/get_cust_fabric_price");
	public static ZCUrl Url_customshopaide_add_cart_pbyx   = new ZCUrl("下单工具2.0 客户经理 衬衫 提交", "/index.php/openapi/uskinapi/manager_freechoice_add_cart");
	public static ZCUrl Url_customshopaide_add_cart_pbc    = new ZCUrl("下单工具2.0 客户经理 客供面料 提交", "/index.php/openapi/uskinapi/manager_custfabric_add_cart");
	public static ZCUrl Url_customshopaide_add_cart_woman  = new ZCUrl("下单工具2.0 客户经理 女装 提交", "/index.php/openapi/uskinapi/manager_add_cart_for_mygirl");
	public static ZCUrl Url_customshopaide_add_cart_design = new ZCUrl("下单工具2.0 客户经理 设计师款 提交", "/index.php/openapi/shirtapi/manager_recommendlist_add_cart");

	public static ZCUrl Url_customshopaide_add_cart_ybr_pbyx = new ZCUrl("下单工具2.0 客户经理 衬衫 优纤面料 提交", "/index.php/openapi/uskinapi/ybr_add_cart");
	public static ZCUrl Url_customshopaide_add_cart_ybr_pbc  = new ZCUrl("下单工具2.0 客户经理 衬衫 客供面料 提交", "/index.php/openapi/uskinapi/ybr_cust_fabric_add_cart");

	public static ArrayList<ZCUrl> list = null;

	public static void init() {

		list = new ArrayList<ConfigHelperURL.ZCUrl>();
		// 下单工具1.0
		{
			list.add(Url_LoginEC);
			list.add(Url_SubTailor);
		}
		// 下单工具2.0 SCA
		{
			// 定制顾问
			list.add(Url_aide_get_price_pbyx);
			list.add(Url_aide_add_cart_pbyx);
			list.add(Url_aide_get_price_design);
			list.add(Url_aide_add_cart_design);
			list.add(Url_aide_get_price_subcontract);
			list.add(Url_aide_add_cart_subcontract);
			list.add(Url_aide_add_cart_woman);
			// 客户经理
			list.add(Url_customshopaide_get_price_pbyx);
			list.add(Url_customshopaide_get_price_pbc);
			list.add(Url_customshopaide_add_cart_pbyx);
			list.add(Url_customshopaide_add_cart_ybr_pbyx);
			list.add(Url_customshopaide_add_cart_ybr_pbc);
			list.add(Url_customshopaide_add_cart_pbc);
			list.add(Url_customshopaide_add_cart_woman);
			list.add(Url_customshopaide_add_cart_design);
			// 定制店
			list.add(Url_customshop_get_price_pbyx);
			list.add(Url_customshop_get_price_pbc);
			list.add(Url_customshop_add_cart_pbyx);
			list.add(Url_customshop_add_cart_pbc);
			list.add(Url_customshop_add_cart_woman);
			list.add(Url_customshop_add_cart_design);
		}

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
		for (ZCUrl url : list) {
			url.setUrl(baseurl);
		}
	}

	public static class ZCUrl {
		String nameCH;
		String nameEN;
		String urlAPI;
		String urlFull;

		public ZCUrl(String name, String API) {
			nameCH = name;
			nameEN = "";
			urlAPI = API;
			urlFull = "";
		}

		public void setUrl(String baseUrl) {
			urlFull = baseUrl + urlAPI;
		}

		public String getUrl() {
			return urlFull;
		}

		public String getDesc() {
			return StringHelper.fillRight(nameCH, 26, "—") + ">" + urlFull;
		}

	}

}
