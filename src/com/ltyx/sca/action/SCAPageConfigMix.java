package com.ltyx.sca.action;

import java.util.ArrayList;

import com.zc.support.service.DBHelper;

public class SCAPageConfigMix {
	// 领型
	public static String get_list_LZX_01() {

		String list = "";

		list += SCAPageConfigMan.get_list_LZX_01();
		list += SCAPageConfigWoman.get_list_LZX_01();

		return list;
	}

	// 袖头
	public static String get_list_LZX_02() {

		String list = "";

		list += SCAPageConfigMan.get_list_LZX_02();
		list += SCAPageConfigWoman.get_list_LZX_02();

		return list;

	}

	// 门襟
	public static String get_list_LZX_03() {

		String list = "";

		list += SCAPageConfigMan.get_list_LZX_03();
		list += SCAPageConfigWoman.get_list_LZX_03();

		return list;

	}

	// 口袋
	public static String get_list_LZX_04() {

		String list = "";

		list += SCAPageConfigMan.get_list_LZX_04();
		list += SCAPageConfigWoman.get_list_LZX_04();

		return list;

	}

	// 主唛
	public static String get_list_LZX_08() {

		String list = "";

		list += SCAPageConfigMan.get_list_LZX_08();
		list += SCAPageConfigWoman.get_list_LZX_08();

		return list;

	}

	// 袖褶
	public static String get_list_LZX_120() {

		String list = "";

		list += SCAPageConfigMan.get_list_LZX_120();
		list += SCAPageConfigWoman.get_list_LZX_120();

		return list;

	}

	// 下摆
	public static String get_list_LZX_06() {

		String list = "";

		list += SCAPageConfigMan.get_list_LZX_06();
		list += SCAPageConfigWoman.get_list_LZX_06();

		return list;

	}

	// 后背款式
	public static String get_list_LZX_17() {

		String list = "";

		list += SCAPageConfigMan.get_list_LZX_17();
		list += SCAPageConfigWoman.get_list_LZX_17();

		return list;

	}

	// 侧缝底摆贴布
	public static String get_list_LZX_26() {

		String list = "";

		list += SCAPageConfigMan.get_list_LZX_26();
		list += SCAPageConfigWoman.get_list_LZX_26();

		return list;

	}

	// 刺绣位置
	public static String get_list_LZX_13() {

		String list = "";

		list += SCAPageConfigMan.get_list_LZX_13();

		return list;

	}

	// 织带
	public static String get_list_zhidai() {

		String list = "";

		list += SCAPageConfigMan.get_list_zhidai();

		return list;

	}

	// 颜色
	public static String get_list_color() {

		String list = "";

		list += SCAPageConfigMan.get_list_color();

		return list;

	}

	// 扣子
	public static String get_list_kouzi() {

		String list = "";

		list += SCAPageConfigMan.get_list_kouzi();

		return list;

	}

	// 宽松度
	public static String get_list_easy_type() {

		String list = "";

		list += SCAPageConfigMan.get_list_easy_type();
		list += SCAPageConfigWoman.get_list_easy_type();

		return list;

	}

	// 领撑
	public static String get_list_lingcheng() {

		String list = "";

		list += SCAPageConfigMan.get_list_lingcheng();
		list += SCAPageConfigWoman.get_list_lingcheng();

		return list;

	}

	// 明线
	public static String get_list_mingxian() {

		String list = "";

		list += SCAPageConfigMan.get_list_mingxian();

		return list;

	}

	// 侧缝
	public static String get_list_cefeng() {

		String list = "";

		list += SCAPageConfigMan.get_list_cefeng();

		return list;

	}

	// 嵌条
	public static String get_list_qiantiao() {

		String list = "";

		list += SCAPageConfigMan.get_list_qiantiao();

		return list;

	}

	// 衬布
	public static String get_list_chenbu() {

		String list = "";

		list += SCAPageConfigMan.get_list_chenbu();

		return list;

	}

	// 织带位置
	public static String get_list_weizhi_zhidai() {

		String list = "";

		list += SCAPageConfigMan.get_list_weizhi_zhidai();

		return list;

	}

	// 配色位置
	public static String get_list_weizhi_peise() {

		String list = "";

		list += SCAPageConfigMan.get_list_weizhi_peise();

		return list;

	}

	// 设计师款编号
	public static String get_list_design_code() {

		String list = "";

		list += cooly("男装", "true", "design_code", "1");
		list += cooly("女装", "true", "design_code", "11");
		list += cooly("男装已下架", "disabled", "design_code", "0");
		list += cooly("女装已下架", "disabled", "design_code", "10");

		return list;

	}

	// 包装定制顾问
	public static String get_list_baozhuang_aide() {

		String list = "";

		list += SCAPageConfigMan.get_list_baozhuang_aide();

		return list;

	}

	// 包装定制店
	public static String get_list_baozhuang_shop() {

		String list = "";

		list += SCAPageConfigMan.get_list_baozhuang_shop();

		return list;

	}

	/**
	 * 
	 * @param label
	 *            基础款 复杂款 客供款
	 * @param stylebase
	 *            true false pbc
	 * @param name
	 * @param state
	 * @return
	 */
	public static String cooly(String label, String stylebase, String name, String state) {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_temp = DBHelper.select("code", "ch").from("technology").where("name1 = '" + name + "'", "state = " + state).exe();

		if (al_temp.size() > 0) {
			list += "<optgroup label=\"" + label + "\">";
			for (int i = 0; i < al_temp.size(); i++) {
				list += "<option value=\"" + al_temp.get(i).get("code");
				list += "\" ";
				if ("disabled".equals(stylebase)) {
					list += "disabled ";
				} else {
					list += "stylebase=\"" + stylebase + "\"";
				}
				list += ">";
				list += al_temp.get(i).get("code") + " " + al_temp.get(i).get("ch");
				list += "</option>";
			}
			list += "</optgroup>";
		}

		return list;

	}

}
