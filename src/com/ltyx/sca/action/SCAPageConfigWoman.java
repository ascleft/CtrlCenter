package com.ltyx.sca.action;

import java.util.ArrayList;

import com.zc.support.service.DBHelper;

public class SCAPageConfigWoman {

	public static String get_list_LZX_01() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'YXL_01'", "state = 11").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch") + " " + "女装";
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;
	}

	public static String get_list_LZX_02() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'YXL_02'", "state = 11").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + "女装";
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_LZX_03() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'YXL_17'", "state = 11").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch") + " " + "女装";
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_LZX_04() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'YXL_05'", "state = 11").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + "女装";
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_LZX_08() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'LZX_08'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;
	}

	public static String get_list_LZX_120() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'LZX_120'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_LZX_06() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'LZX_06'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_LZX_17() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'YXL_03'", "state = 11").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch") + " " + "女装";
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_LZX_26() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'LZX_26'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_LZX_13() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'LZX_13'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_zhidai() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'zhidai'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_color() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'color'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_kouzi() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'kouzi'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_easy_type() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'easy_type'", "state = 11").exe();
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch") + " " + "女装";
			list += "</option>";
		}
		return list;

	}

	public static String get_list_lingcheng() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'lingcheng'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_mingxian() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'mingxian'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_cefeng() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'cefeng'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_qiantiao() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'qiantiao'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_chenbu() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'chenbu'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch") + " " + "女装";
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_weizhi_zhidai() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'weizhi_zhidai'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_weizhi_peise() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'weizhi_peise'", "state = 1").exe();
		list += "<optgroup label=\"基础款\">";
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}
		list += "</optgroup>";//

		return list;

	}

	public static String get_list_baozhuang_aide() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'baozhuang'", "state = 1").exe();
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}

		return list;

	}
	
	public static String get_list_baozhuang_shop() {

		String list = "";

		ArrayList<DBHelper.SelectBean> al_1 = DBHelper.select("code", "ch").from("technology").where("name1 = 'baozhuang'", "state = 2").exe();
		for (int i = 0; i < al_1.size(); i++) {
			list += "<option value=\"" + al_1.get(i).get("code");
			list += "\" stylebase=\"true\">";
			list += al_1.get(i).get("code") + " " + al_1.get(i).get("ch");
			list += "</option>";
		}

		return list;

	}

}
