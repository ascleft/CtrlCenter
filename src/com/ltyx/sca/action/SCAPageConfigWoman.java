package com.ltyx.sca.action;

import java.util.ArrayList;

import com.zc.support.service.DBHelper;

public class SCAPageConfigWoman {

	// 领型
	public static String get_list_LZX_01() {

		String list = "";

		list += cooly("基础款(女装)", "true", "LZX_01", "11");
		list += cooly("复杂款(女装)", "false", "LZX_01", "12");
		list += cooly("客供款", "pbc", "LZX_01", "13");

		return list;
	}

	// 袖头
	public static String get_list_LZX_02() {

		String list = "";

		list += cooly("基础款(女装)", "true", "LZX_02", "11");
		list += cooly("复杂款(女装)", "false", "LZX_02", "12");
		list += cooly("客供款", "pbc", "LZX_02", "13");

		return list;

	}

	// 门襟
	public static String get_list_LZX_03() {

		String list = "";

		list += cooly("基础款(女装)", "true", "LZX_03", "11");
		list += cooly("复杂款(女装)", "false", "LZX_03", "12");
		list += cooly("客供款", "pbc", "LZX_03", "13");

		return list;
	}

	// 口袋
	public static String get_list_LZX_04() {

		String list = "";

		list += cooly("基础款(女装)", "true", "LZX_04", "11");
		list += cooly("复杂款(女装)", "false", "LZX_04", "12");
		list += cooly("客供款", "pbc", "LZX_04", "13");

		return list;

	}

	// 主唛
	public static String get_list_LZX_08() {

		String list = "";

		list += cooly("基础款", "true", "LZX_08", "11");
		list += cooly("复杂款", "false", "LZX_08", "12");
		list += cooly("客供款", "pbc", "LZX_08", "13");

		return list;

	}

	// 袖褶
	public static String get_list_LZX_120() {

		String list = "";

		list += cooly("基础款(女装)", "true", "LZX_120", "1");
		list += cooly("复杂款(女装)", "false", "LZX_120", "2");
		list += cooly("客供款", "pbc", "LZX_120", "3");

		return list;

	}

	// 下摆
	public static String get_list_LZX_06() {

		String list = "";

		list += cooly("基础款(女装)", "true", "LZX_06", "1");
		list += cooly("复杂款(女装)", "false", "LZX_06", "2");
		list += cooly("客供款", "pbc", "LZX_06", "3");

		return list;

	}

	// 后背款式
	public static String get_list_LZX_17() {

		String list = "";

		list += cooly("基础款(女装)", "true", "LZX_17", "11");
		list += cooly("复杂款(女装)", "false", "LZX_17", "12");
		list += cooly("客供款", "pbc", "LZX_17", "13");

		return list;

	}

	// 侧缝底摆贴布
	public static String get_list_LZX_26() {

		String list = "";

		list += cooly("基础款(女装)", "true", "LZX_26", "1");
		list += cooly("复杂款(女装)", "false", "LZX_26", "2");
		list += cooly("客供款", "pbc", "LZX_26", "3");

		return list;

	}

	// 刺绣位置
	public static String get_list_LZX_13() {

		String list = "";

		list += cooly("基础款", "true", "LZX_13", "1");
		list += cooly("复杂款", "false", "LZX_13", "2");
		list += cooly("客供款", "pbc", "LZX_13", "3");

		return list;

	}

	// 织带
	public static String get_list_zhidai() {

		String list = "";

		list += cooly("基础款", "true", "zhidai", "1");
		list += cooly("复杂款", "false", "zhidai", "2");
		list += cooly("客供款", "pbc", "zhidai", "3");

		return list;

	}

	// 颜色
	public static String get_list_color() {

		String list = "";

		list += cooly("基础款", "true", "color", "1");
		list += cooly("复杂款", "false", "color", "2");
		list += cooly("客供款", "pbc", "color", "3");

		return list;

	}

	// 扣子
	public static String get_list_kouzi() {

		String list = "";

		list += cooly("基础款", "true", "kouzi", "1");
		list += cooly("复杂款", "false", "kouzi", "2");
		list += cooly("客供款", "pbc", "kouzi", "3");

		return list;

	}

	// 宽松度
	public static String get_list_easy_type() {

		String list = "";

		list += cooly("基础款(女装)", "true", "easy_type", "11");
		list += cooly("复杂款(女装)", "false", "easy_type", "12");
		list += cooly("客供款", "pbc", "easy_type", "13");

		return list;

	}

	// 领撑
	public static String get_list_lingcheng() {

		String list = "";

		list += cooly("基础款(女装)", "true", "lingcheng", "11");
		list += cooly("复杂款(女装)", "false", "lingcheng", "12");
		list += cooly("客供款", "pbc", "lingcheng", "13");

		return list;

	}

	// 明线
	public static String get_list_mingxian() {

		String list = "";

		list += cooly("基础款", "true", "mingxian", "11");
		list += cooly("复杂款", "false", "mingxian", "12");
		list += cooly("客供款", "pbc", "mingxian", "13");

		return list;

	}

	// 侧缝工艺
	public static String get_list_cefeng() {

		String list = "";

		list += cooly("基础款(女装)", "true", "cefeng", "11");
		list += cooly("复杂款(女装)", "false", "cefeng", "12");
		list += cooly("客供款", "pbc", "cefeng", "13");

		return list;

	}

	// 嵌条
	public static String get_list_qiantiao() {

		String list = "";

		list += cooly("基础款", "true", "qiantiao", "1");
		list += cooly("复杂款", "false", "qiantiao", "2");
		list += cooly("客供款", "pbc", "qiantiao", "3");

		return list;

	}

	// 衬布
	public static String get_list_chenbu() {

		String list = "";

		list += cooly("基础款", "true", "chenbu", "1");
		list += cooly("复杂款", "false", "chenbu", "2");
		list += cooly("客供款", "pbc", "chenbu", "3");

		return list;

	}

	// 织带位置
	public static String get_list_weizhi_zhidai() {

		String list = "";

		list += cooly("基础款", "true", "weizhi_zhidai", "1");
		list += cooly("复杂款", "false", "weizhi_zhidai", "2");
		list += cooly("客供款", "pbc", "weizhi_zhidai", "3");

		return list;

	}

	// 配色位置
	public static String get_list_weizhi_peise() {

		String list = "";

		list += cooly("基础款", "true", "weizhi_peise", "1");
		list += cooly("复杂款", "false", "weizhi_peise", "2");
		list += cooly("客供款", "pbc", "weizhi_peise", "3");

		return list;

	}

	// 包装顾问
	public static String get_list_baozhuang_aide() {

		String list = "";

		list += cooly("定制顾问", "true", "baozhuang", "1");

		return list;

	}

	// 包装定制店
	public static String get_list_baozhuang_shop() {

		String list = "";

		list += cooly("定制店", "true", "baozhuang", "2");

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
				list += "\" stylebase=\"" + stylebase + "\">";
				list += al_temp.get(i).get("code") + " " + al_temp.get(i).get("ch");
				list += "</option>";
			}
			list += "</optgroup>";
		}

		return list;

	}

}
