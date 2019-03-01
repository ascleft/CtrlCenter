package com.zc.support.config;

import java.util.ArrayList;

import com.zc.support.service.StringHelper;

public class ConfigHelperURL {

	final public static String STATE_RELEASE = "STATE_RELEASE";
	final public static String STATE_TEST = "STATE_TEST";;
	final public static String STATE_CUSTOM = "STATE_CUSTOM";

	private static String STATE = STATE_RELEASE;

	private static String BaseUrl_RELEASE = "http://www.utailor.com.cn";
	private static String BaseUrl_TEST = "http://tool.uskin.net.cn";

	// 下单工具1.0
	public static ZCUrl Url_LoginEC = new ZCUrl("下单工具1.0 登录", "/index.php/openapi/uskinapi/tool_check_login");
	public static ZCUrl Url_SubTailor = new ZCUrl("下单工具1.0 提交", "/index.php/openapi/uskinapi/tool_add_cart");

	// 下单工具2.0 SCA
	public static ZCUrl Url_aide_get_price_pbyx = new ZCUrl("下单工具2.0 定制顾问 优纤男装 报价 ", "/index.php/openapi/uskinapi/body_trainer_freechoice_price");
	public static ZCUrl Url_aide_get_price_design = new ZCUrl("下单工具2.0 定制顾问 设计师款 报价", "/index.php/openapi/uskinapi/body_trainer_recommendlist_price");
	public static ZCUrl Url_aide_get_price_subcontract = new ZCUrl("下单工具2.0 定制顾问 其他商品 报价", "/index.php/openapi/uskinapi/body_trainer_other_goods_price");
	public static ZCUrl Url_aide_add_cart_pbyx = new ZCUrl("下单工具2.0 定制顾问 优纤男装 提交 ", "/index.php/openapi/uskinapi/body_trainer_freechoice_add_cart");
	public static ZCUrl Url_aide_add_cart_design = new ZCUrl("下单工具2.0 定制顾问 设计师款 提交", "/index.php/openapi/uskinapi/body_trainer_recommendlist_add_cart");
	public static ZCUrl Url_aide_add_cart_subcontract = new ZCUrl("下单工具2.0 定制顾问 其他商品 提交", "/index.php/openapi/uskinapi/body_trainer_other_goods_add_cart");
	public static ZCUrl Url_aide_add_cart_woman = new ZCUrl("下单工具2.0 定制顾问 优纤女装 提交 ", "/index.php/openapi/uskinapi/body_trainer_add_cart_for_mygirl");

	public static ZCUrl Url_customshop_get_price_man_pbyx = new ZCUrl("下单工具2.0 定制店 男装优纤 报价", "/index.php/openapi/uskinapi/bespoke_shop_freechoice_price");
	public static ZCUrl Url_customshop_get_price_man_pbc = new ZCUrl("下单工具2.0 定制店 男装客供 报价", "/index.php/openapi/uskinapi/get_cust_fabric_price");
	public static ZCUrl Url_customshop_get_price_woman_pbyx = new ZCUrl("下单工具2.0 定制店 女装优纤 报价", "/index.php/openapi/uskinapi/bespoke_shop_freechoice_price_girl");
	public static ZCUrl Url_customshop_get_price_woman_pbc = new ZCUrl("下单工具2.0 定制店 女装客供 报价", "/index.php/openapi/uskinapi/get_cust_fabric_price");
	public static ZCUrl Url_customshop_get_price_design = new ZCUrl("下单工具2.0 定制店 设计师款 报价", "/index.php/openapi/uskinapi/bespoke_shop_recommendlist_price");
	public static ZCUrl Url_customshop_add_cart_man_pbyx = new ZCUrl("下单工具2.0 定制店 男装优纤 提交", "/index.php/openapi/uskinapi/bespoke_shop_freechoice_add_cart");
	public static ZCUrl Url_customshop_add_cart_man_pbc = new ZCUrl("下单工具2.0 定制店 男装客供 提交", "/index.php/openapi/uskinapi/bespoke_shop_cust_fabric_add_cart");
	public static ZCUrl Url_customshop_add_cart_woman_pbyx = new ZCUrl("下单工具2.0 定制店 女装优纤 提交", "/index.php/openapi/uskinapi/bespoke_shop_add_cart_for_mygirl");
	public static ZCUrl Url_customshop_add_cart_woman_pbc = new ZCUrl("下单工具2.0 定制店 女装客供 提交", "/index.php/openapi/uskinapi/bespoke_shop_cust_fabric_add_cart_girl");
	public static ZCUrl Url_customshop_add_cart_design = new ZCUrl("下单工具2.0 定制店 设计师款 提交", "/index.php/openapi/uskinapi/bespoke_shop_recommendlist_add_cart");

	public static ZCUrl Url_customshop_get_factory_order = new ZCUrl("下单工具2.0 定制店 历史订单 查询", "/index.php/openapi/uskinapi/getBaseInfoForFX");
	public static ZCUrl Url_customshop_add_cart_repair = new ZCUrl("下单工具2.0 定制店 返修订单 提交", "/index.php/openapi/uskinapi/addCartForFX");
	public static ZCUrl Url_customshop_get_price_pbyx_sample = new ZCUrl("下单工具2.0 定制店 工艺部件 报价", "/index.php/openapi/uskinapi/get_tech_sample_price");
	public static ZCUrl Url_customshop_add_cart_pbyx_sample = new ZCUrl("下单工具2.0 定制店 工艺部件 提交", "/index.php/openapi/uskinapi/tech_sample_add_cart");

	public static ZCUrl Url_customshopaide_get_price_man_pbyx = new ZCUrl("下单工具2.0 客户经理 男装优纤 报价", "/index.php/openapi/uskinapi/bespoke_shop_freechoice_price");
	public static ZCUrl Url_customshopaide_get_price_man_pbc = new ZCUrl("下单工具2.0 客户经理 男装客供 报价", "/index.php/openapi/uskinapi/get_cust_fabric_price");
	public static ZCUrl Url_customshopaide_get_price_woman_pbyx = new ZCUrl("下单工具2.0 客户经理 女装优纤 报价", "/index.php/openapi/uskinapi/bespoke_shop_freechoice_price_girl");
	public static ZCUrl Url_customshopaide_get_price_woman_pbc = new ZCUrl("下单工具2.0 客户经理 女装客供 报价", "/index.php/openapi/uskinapi/get_cust_fabric_price");
	public static ZCUrl Url_customshopaide_get_price_design = new ZCUrl("下单工具2.0 客户经理 设计师款 报价", "/index.php/openapi/uskinapi/bespoke_shop_recommendlist_price");
	public static ZCUrl Url_customshopaide_add_cart_man_pbyx = new ZCUrl("下单工具2.0 客户经理 男装优纤 提交", "/index.php/openapi/uskinapi/manager_freechoice_add_cart");
	public static ZCUrl Url_customshopaide_add_cart_man_pbc = new ZCUrl("下单工具2.0 客户经理 男装客供 提交", "/index.php/openapi/uskinapi/manager_custfabric_add_cart");
	public static ZCUrl Url_customshopaide_add_cart_woman_pbyx = new ZCUrl("下单工具2.0 客户经理 女装优纤 提交", "/index.php/openapi/uskinapi/manager_add_cart_for_mygirl");
	public static ZCUrl Url_customshopaide_add_cart_woman_pbc = new ZCUrl("下单工具2.0 客户经理 女装客供 提交", "/index.php/openapi/uskinapi/manager_custfabric_add_cart_for_mygirl");
	public static ZCUrl Url_customshopaide_add_cart_design = new ZCUrl("下单工具2.0 客户经理 设计师款 提交", "/index.php/openapi/uskinapi/manager_recommendlist_add_cart");

	public static ZCUrl Url_customshopaide_add_cart_ybr_pbyx = new ZCUrl("下单工具2.0 对外 衣帮人优纤 提交", "/index.php/openapi/uskinapi/ybr_add_cart");
	public static ZCUrl Url_customshopaide_add_cart_ybr_pbc = new ZCUrl("下单工具2.0 对外 衣帮人客供 提交", "/index.php/openapi/uskinapi/ybr_cust_fabric_add_cart");

	public static ArrayList<ZCUrl> list = null;

	public static void init() {

		list = new ArrayList<ConfigHelperURL.ZCUrl>();
		// 下单工具1.0
		{
			// 登录
			list.add(Url_LoginEC);
			// 添加到购物车
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
			list.add(Url_customshopaide_get_price_man_pbyx);
			list.add(Url_customshopaide_get_price_man_pbc);
			list.add(Url_customshopaide_get_price_woman_pbyx);
			list.add(Url_customshopaide_get_price_woman_pbc);
			list.add(Url_customshopaide_get_price_design);
			list.add(Url_customshopaide_add_cart_man_pbyx);
			list.add(Url_customshopaide_add_cart_man_pbc);
			list.add(Url_customshopaide_add_cart_woman_pbyx);
			list.add(Url_customshopaide_add_cart_woman_pbc);
			list.add(Url_customshopaide_add_cart_design);
			// 定制店
			list.add(Url_customshop_get_price_man_pbyx);
			list.add(Url_customshop_get_price_man_pbc);
			list.add(Url_customshop_get_price_woman_pbyx);
			list.add(Url_customshop_get_price_woman_pbc);
			list.add(Url_customshop_get_price_design);
			list.add(Url_customshop_add_cart_man_pbyx);
			list.add(Url_customshop_add_cart_man_pbc);
			list.add(Url_customshop_add_cart_woman_pbyx);
			list.add(Url_customshop_add_cart_woman_pbc);
			list.add(Url_customshop_add_cart_design);
			list.add(Url_customshop_get_factory_order);
			list.add(Url_customshop_add_cart_repair);
			list.add(Url_customshop_get_price_pbyx_sample);
			list.add(Url_customshop_add_cart_pbyx_sample);
			// 衣帮人
			list.add(Url_customshopaide_add_cart_ybr_pbyx);
			list.add(Url_customshopaide_add_cart_ybr_pbc);
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
			return StringHelper.fillRightMIX(nameCH, 26, "—") + ">" + urlFull;
		}

	}

}
