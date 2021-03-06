package com.ltyx.sca.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zc.support.link.ZCReqIntroGetter;

public class SCAPageConfigCommon {

	synchronized public static HttpSession manageMenu(HttpSession session) {

		{
			String rank = "" + session.getAttribute("ec_user_rank");

			if ("1".equals(rank)) {// 客户经理
				session.setAttribute("menulist", SCAPageConfigCommon.get_menu_list_temporary());
			} else if ("0".equals(rank)) {// 客户经理
				session.setAttribute("menulist", SCAPageConfigCommon.get_menu_list_csa());
			} else if ("10".equals(rank)) {// 定制顾问
				session.setAttribute("menulist", SCAPageConfigCommon.get_menu_list_aide());
			} else if ("20".equals(rank) || "21".equals(rank)) {// 定制店
				session.setAttribute("menulist", SCAPageConfigCommon.get_menu_list_cs());
			}

			// String[][] dic_su = { //
			// { "129", "0", "SU 张弛-客户经理" }, //
			// { "3071", "20", "SU 张弛-定制店" }, //
			// { "3071", "21", "SU 张弛-定制店-独立密码" }, //
			// { "3000", "20", "SU 顾振-定制店" }, //
			// { "3000", "21", "SU 顾振-定制店-独立密码" }, //
			// { "21000", "20", "SU 刘亚朋" }, //
			// { "21000", "21", "SU 刘亚朋-独立密码" },//
			// };
			//
			// {
			// String idTemp = "" + session.getAttribute("ec_user_id");
			// String rankTemp = "" + session.getAttribute("ec_user_rank");
			// for (int i = 0; i < dic_su.length; i++) {
			// if (idTemp.equals(dic_su[i][0]) && rankTemp.equals(dic_su[i][1]))
			// {
			// session.setAttribute("menulist",
			// SCAPageConfigCommon.get_menu_list_all());
			// session.setAttribute("ec_user_name", dic_su[i][2]);
			// }
			// }
			// }

			if (AuthorizeAssistan.isSU(session)) {
				session.setAttribute("su", "true");
				{
					session.setAttribute("menulist", SCAPageConfigCommon.get_menu_list_all());
					session.setAttribute("ec_user_name", AuthorizeAssistan.getSU(session));
				}
			} else {
				session.setAttribute("su", "false");
			}

		}

		return session;
	}

	synchronized public static HttpSession manageTechnologyMan(HttpSession session) {

		{
			session.setAttribute("list_tech_collar_full", SCAPageConfigMan.get_list_tech_collar_full());
			session.setAttribute("list_LZX_02", SCAPageConfigMan.get_list_LZX_02());
			session.setAttribute("list_LZX_03", SCAPageConfigMan.get_list_LZX_03());
			session.setAttribute("list_LZX_04", SCAPageConfigMan.get_list_LZX_04());
			session.setAttribute("list_LZX_08", SCAPageConfigMan.get_list_LZX_08());
			session.setAttribute("list_LZX_120", SCAPageConfigMan.get_list_LZX_120());
			session.setAttribute("list_LZX_06", SCAPageConfigMan.get_list_LZX_06());
			session.setAttribute("list_LZX_17", SCAPageConfigMan.get_list_LZX_17());
			session.setAttribute("list_LZX_26", SCAPageConfigMan.get_list_LZX_26());
			session.setAttribute("list_LZX_13", SCAPageConfigMan.get_list_LZX_13());
			session.setAttribute("list_zhidai", SCAPageConfigMan.get_list_zhidai());
			session.setAttribute("list_color", SCAPageConfigMan.get_list_color());
			session.setAttribute("list_button_default", SCAPageConfigMan.list_button_default());
			session.setAttribute("list_easytype", SCAPageConfigMan.get_list_easy_type());
			session.setAttribute("list_lingcheng", SCAPageConfigMan.get_list_lingcheng());
			session.setAttribute("list_mingxian", SCAPageConfigMan.get_list_mingxian());
			session.setAttribute("list_cefeng", SCAPageConfigMan.get_list_cefeng());
			session.setAttribute("list_qiantiao", SCAPageConfigMan.get_list_qiantiao());
			session.setAttribute("list_chenbu", SCAPageConfigMan.get_list_chenbu());

			session.setAttribute("list_weizhi_zhidai", SCAPageConfigMan.get_list_weizhi_zhidai());
			session.setAttribute("list_weizhi_peise", SCAPageConfigMan.get_list_weizhi_peise());

			session.setAttribute("list_baozhuang_aide", SCAPageConfigMan.get_list_baozhuang_aide());
			session.setAttribute("list_baozhuang_shop", SCAPageConfigMan.get_list_baozhuang_shop());

			session.setAttribute("list_button_decorative_code", SCAPageConfigMan.get_list_button_decorative_code());
			session.setAttribute("list_button_decorative_num", SCAPageConfigMan.get_list_button_decorative_num());
			session.setAttribute("list_button_decorative_pos", SCAPageConfigMan.get_list_button_decorative_pos());

		}

		return session;
	}

	synchronized public static HttpSession manageTechnologyWoman(HttpSession session) {

		{
			session.setAttribute("list_tech_collar_full", SCAPageConfigWoman.get_list_tech_collar_full());
			session.setAttribute("list_LZX_02", SCAPageConfigWoman.get_list_LZX_02());
			session.setAttribute("list_LZX_03", SCAPageConfigWoman.get_list_LZX_03());
			session.setAttribute("list_LZX_04", SCAPageConfigWoman.get_list_LZX_04());
			session.setAttribute("list_LZX_08", SCAPageConfigWoman.get_list_LZX_08());
			session.setAttribute("list_LZX_120", SCAPageConfigWoman.get_list_LZX_120());
			session.setAttribute("list_LZX_06", SCAPageConfigWoman.get_list_LZX_06());
			session.setAttribute("list_LZX_17", SCAPageConfigWoman.get_list_LZX_17());
			session.setAttribute("list_LZX_26", SCAPageConfigWoman.get_list_LZX_26());
			session.setAttribute("list_LZX_13", SCAPageConfigWoman.get_list_LZX_13());
			session.setAttribute("list_zhidai", SCAPageConfigWoman.get_list_zhidai());
			session.setAttribute("list_color", SCAPageConfigWoman.get_list_color());
			session.setAttribute("list_button_default", SCAPageConfigWoman.list_button_default());
			session.setAttribute("list_easytype", SCAPageConfigWoman.get_list_easy_type());
			session.setAttribute("list_lingcheng", SCAPageConfigWoman.get_list_lingcheng());
			session.setAttribute("list_mingxian", SCAPageConfigWoman.get_list_mingxian());
			session.setAttribute("list_cefeng", SCAPageConfigWoman.get_list_cefeng());
			session.setAttribute("list_qiantiao", SCAPageConfigWoman.get_list_qiantiao());
			session.setAttribute("list_chenbu", SCAPageConfigWoman.get_list_chenbu());

			session.setAttribute("list_weizhi_zhidai", SCAPageConfigWoman.get_list_weizhi_zhidai());
			session.setAttribute("list_weizhi_peise", SCAPageConfigWoman.get_list_weizhi_peise());

			session.setAttribute("list_baozhuang_aide", SCAPageConfigWoman.get_list_baozhuang_aide());
			session.setAttribute("list_baozhuang_shop", SCAPageConfigWoman.get_list_baozhuang_shop());

			session.setAttribute("list_button_decorative_code", SCAPageConfigWoman.get_list_button_decorative_code());
			session.setAttribute("list_button_decorative_num", SCAPageConfigWoman.get_list_button_decorative_num());
			session.setAttribute("list_button_decorative_pos", SCAPageConfigWoman.get_list_button_decorative_pos());

		}

		return session;
	}

	synchronized public static HttpSession manageTechnologyMix(HttpSession session) {

		{
			session.setAttribute("list_tech_collar_full", SCAPageConfigMix.get_list_tech_collar_full());
			session.setAttribute("list_LZX_02", SCAPageConfigMix.get_list_LZX_02());
			session.setAttribute("list_LZX_03", SCAPageConfigMix.get_list_LZX_03());
			session.setAttribute("list_LZX_04", SCAPageConfigMix.get_list_LZX_04());
			session.setAttribute("list_LZX_08", SCAPageConfigMix.get_list_LZX_08());
			session.setAttribute("list_LZX_120", SCAPageConfigMix.get_list_LZX_120());
			session.setAttribute("list_LZX_06", SCAPageConfigMix.get_list_LZX_06());
			session.setAttribute("list_LZX_17", SCAPageConfigMix.get_list_LZX_17());
			session.setAttribute("list_LZX_26", SCAPageConfigMix.get_list_LZX_26());
			session.setAttribute("list_LZX_13", SCAPageConfigMix.get_list_LZX_13());
			session.setAttribute("list_zhidai", SCAPageConfigMix.get_list_zhidai());
			session.setAttribute("list_color", SCAPageConfigMix.get_list_color());
			session.setAttribute("list_button_default", SCAPageConfigMix.list_button_default());
			session.setAttribute("list_easytype", SCAPageConfigMix.get_list_easy_type());
			session.setAttribute("list_lingcheng", SCAPageConfigMix.get_list_lingcheng());
			session.setAttribute("list_mingxian", SCAPageConfigMix.get_list_mingxian());
			session.setAttribute("list_cefeng", SCAPageConfigMix.get_list_cefeng());
			session.setAttribute("list_qiantiao", SCAPageConfigMix.get_list_qiantiao());
			session.setAttribute("list_chenbu", SCAPageConfigMix.get_list_chenbu());

			session.setAttribute("list_weizhi_zhidai", SCAPageConfigMix.get_list_weizhi_zhidai());
			session.setAttribute("list_weizhi_peise", SCAPageConfigMix.get_list_weizhi_peise());

			session.setAttribute("list_design_code", SCAPageConfigMix.get_list_design_code());

			session.setAttribute("list_baozhuang_aide", SCAPageConfigMix.get_list_baozhuang_aide());
			session.setAttribute("list_baozhuang_shop", SCAPageConfigMix.get_list_baozhuang_shop());

			session.setAttribute("list_button_decorative_code", SCAPageConfigMix.get_list_button_decorative_code());
			session.setAttribute("list_button_decorative_num", SCAPageConfigMix.get_list_button_decorative_num());
			session.setAttribute("list_button_decorative_pos", SCAPageConfigMix.get_list_button_decorative_pos());

		}

		return session;
	}

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
		// menuList += get_menu_list_aide();
		menuList += get_menu_list_log();
		menuList += get_menu_list_cs();
		menuList += get_menu_list_csa();

		return menuList;
	}

	public static String get_menu_list_temporary() {

		String menuList = ""//
				+ "<li>"//
				+ "<a class=\"subheader\">临时账户功能</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/UnitySearch.action\">即时库存 联合查询</a>"//
				+ "</li>";//

		return menuList;
	}

	public static String get_menu_list_aide() {

		String menuList = ""//
				+ "<li>"//
				+ "<a class=\"subheader\">定制顾问功能</a>"//
				+ "</li>";//

		return menuList;
	}

	public static String get_menu_list_csa() {

		String menuList = ""//
				+ "<li>"//
				+ "<a class=\"subheader\">客户经理功能</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXMan.action\"   >客户经理 优纤面料 男装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXWoman.action\" >客户经理 优纤面料 女装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAideSearch.action\"    >客户经理 客供面料 男装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAideSearch.action\"    >客户经理 客供面料 女装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXDesign.action\">客户经理 优纤面料 设计师款</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXSubcontract.action\">客户经理 其他商品</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAideSearch.action\"    >客户经理 即时库存查询</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAideSearchMT.action\"  >客户经理 即时库存查询(多线程)</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"http://www.uskin.net.cn/index.php/cart.html\"     target=\"_blank\">进入USKIN购物车结算(电脑版)</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"http://www.uskin.net.cn/index.php/wap/cart.html\" target=\"_blank\">进入USKIN购物车结算(手机版)</a>"//
				+ "</li>";//

		return menuList;

	}

	public static String get_menu_list_cs() {

		String menuList = ""//
				+ "<li>"//
				+ "<a class=\"subheader\">定制店功能</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopPBYXMan.action\"     >定制店 优纤面料 男装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopPBYXWoman.action\"   >定制店 优纤面料 女装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopSearch.action\"      >定制店 客供面料 男装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopSearch.action\"      >定制店 客供面料 女装</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopPBYXDesign.action\"  >定制店 优纤面料 设计师款</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopRepair.action\"      >定制店 返修订单</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopSample.action\"      >定制店 工艺部件</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopSearch.action\"      >定制店 即时库存查询</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"http://www.uskin.net.cn/index.php/cart.html\" target=\"_blank\"    >进入USKIN购物车结算(电脑版)</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"http://www.uskin.net.cn/index.php/wap/cart.html\" target=\"_blank\">进入USKIN购物车结算(手机版)</a>"//
				+ "</li>";//

		return menuList;

	}

	public static String get_menu_list_log() {

		String menuList = ""//
				+ "<li>"//
				+ "<a class=\"subheader\">系统功能</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAideSearchMT.action\">客户经理 即时库存查询(多线程)</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/Core/GetLogPage.action?type=csu\">日志管理-定制店</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/Core/GetLogPage.action?type=csa\">日志管理-客户经理</a>"//
				+ "</li>"//
				+ "<li>"//
				+ "<a class=\"waves-effect\" href=\"/CtrlCenter/LTYX/Core/GetLogPage.action?type=ybr\">日志管理-衣帮人</a>"//
				+ "</li>";//

		return menuList;

	}

}
