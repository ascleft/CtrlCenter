package com.ltyx.sca.action;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.link.ZCReqIntroGetter;

public class SCAPageConfigCommon {

	public static String get_QR_url(HttpServletRequest request) {

		String IP = ZCReqIntroGetter.getIpAddress(request);
		String PORT = ("61.50.122.58".equals(IP)) ? "8029" : "8080";

		String url = "";
		url += "http://pan.baidu.com/share/qrcode?w=150&h=150&url=http://";
		url += IP + ":" + PORT;
		url += "/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action";

		return url;
	}

	public static String get_menu_list_all() {

		String menuList = "";//
		menuList += get_menu_list_guwen();
		menuList += get_menu_list_jingli();
		menuList += get_menu_list_dingzhidian();

		return menuList;
	}

	public static String get_menu_list_guwen() {

		String menuList = ""//
				+ "<li>"//
				+ "<a class=\"subheader\">定制顾问功能</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/AidePBYX.action\">定制顾问 衬衫</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/AideDesign.action\">定制顾问 设计师款</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/AideWoman.action\">定制顾问 女装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/AideSubcontract.action\">定制顾问 其他商品</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/AideSearch.action\">定制顾问 即时库存查询</a>"//
				+ "</li>";//

		return menuList;
	}

	public static String get_menu_list_jingli() {

		String menuList = ""//
				+ "<li>"//
				+ "<a class=\"subheader\">客户经理功能</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYX.action\">客户经理 优纤面料</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAideSearch.action\">客户经理 客供面料</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAideWoman.action\">客户经理 女装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAideSearch.action\">客户经理 即时库存查询</a>"//
				+ "</li>";//

		return menuList;

	}

	public static String get_menu_list_dingzhidian() {

		String menuList = ""//
				+ "<li>"//
				+ "<a class=\"subheader\">定制店功能</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopPBYX.action\">定制店 优纤面料</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopSearch.action\">定制店 客供面料</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopWoman.action\">定制店 女装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopSearch.action\">定制店 即时库存查询</a>"//
				+ "</li>";//

		return menuList;
	}

}
