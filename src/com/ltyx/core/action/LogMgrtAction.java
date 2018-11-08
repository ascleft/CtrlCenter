package com.ltyx.core.action;

import java.util.ArrayList;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.service.DBHelper;
import com.zc.support.service.DBHelper.SelectBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LogMgrtAction extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		return "succ";

	}

	public String search() {

		init(true);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = type_1().toString();

		writeResp();

		return null;

	}

	public String type_1() {

		String tags_1 = getReqParamString("tag_1");
		String tags_2 = getReqParamString("tag_2");
		String tags_3 = getReqParamString("tag_3");

		String id_type = getReqParamString("id_type");
		String id_code = getReqParamString("id_code");

		String key_1 = getReqParamString("key_1");
		String key_2 = getReqParamString("key_2");
		String key_3 = getReqParamString("key_3");

		// 时间
		// 帐号 名称 手机号 ECID
		// 关键词 穿衣人 主面料 刺绣
		// 入站源
		// 站内流转 url 响应
		// 出站源
		// 进度 tags

		// String sql = "";
		// sql += "SELECT ";
		// sql += "main.keys AS m_keys , mian.tags AS m_tags , main.snapshot AS
		// m_snapshot , main.date AS m_date , ";
		// sql += "u.name AS u_name , u.user AS u_tel , u.ecid AS u_ecid , ";
		// sql += "ec.details AS e_snapshot ";
		// sql += "FROM table log_main AS main ";
		// sql += "LEFT JOIN TABLE log_details AS ec ON ec.fid=main.id ";
		// sql += "LEFT JOIN TABLE log_user AS u ON u.id=main.user ";
		// sql += "WHERE ";
		// sql += "u.user='手机号' ";
		// sql += "u.ecid='ECID' ";
		// sql += "AND main.tags like '%模块%' ";
		// sql += "AND main.tags like '%动作%' ";
		// sql += "AND main.tags like '%状态%' ";
		// sql += "AND main.keys like '%穿衣人%' ";
		// sql += "AND main.keys like '%主面料%' ";
		// sql += "AND main.keys like '%刺绣%' ";

		String sql = "";
		sql += "SELECT ";
		sql += "main.keys AS m_keys , main.tags AS m_tags , main.snapshot AS m_snapshot , main.time AS m_time , ";
		sql += "u.name AS u_name , u.user AS u_tel , u.ecid AS u_ecid , ";
		sql += "ec.details AS e_snapshot ";
		sql += "FROM log_main AS main ";
		sql += "LEFT JOIN log_details AS ec ON ec.fid=main.id ";
		sql += "LEFT JOIN log_user AS u ON u.id=main.user ";
		sql += "WHERE ";

		if (id_type.equals("tel")) {
			sql += "u.user='" + id_code + "' ";
		} else {
			sql += "u.ecid='" + id_code + "' ";
		}

		if (!tags_1.equals("all")) {// 模块
			sql += "AND main.tags like '%" + tags_1 + "%' ";
		}
		if (!tags_2.equals("all")) {// 动作
			sql += "AND main.tags like '%" + tags_2 + "%' ";
		}
		if (!tags_3.equals("all")) {// 状态
			sql += "AND main.tags like '%" + tags_3 + "%' ";
		}

		if (key_1.trim().length() > 0) {// 穿衣人
			sql += "AND main.keys like '%" + key_1 + "%' ";
		}
		if (key_2.trim().length() > 0) {// 主面料
			sql += "AND main.keys like '%" + key_2 + "%' ";
		}
		if (key_3.trim().length() > 0) {// 刺绣
			sql += "AND main.keys like '%" + key_3 + "%' ";
		}

		System.out.println(sql);

		ArrayList<SelectBean> searchResult = DBHelper.select("m_keys", "m_tags", "m_snapshot", "m_time", "u_name", "u_tel", "u_ecid","e_snapshot").condition(sql).exe();
		JSONArray jsonArray = new JSONArray();
		for (SelectBean bean : searchResult) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("m_keys", bean.get("m_keys"));
			jsonObject.put("m_tags", bean.get("m_tags"));
			jsonObject.put("m_snapshot", bean.get("m_snapshot"));
			jsonObject.put("m_time", bean.get("m_time"));
			jsonObject.put("e_snapshot", bean.get("e_snapshot"));
			jsonObject.put("u_name", bean.get("u_name"));
			jsonObject.put("u_tel", bean.get("u_tel"));
			jsonObject.put("u_ecid", bean.get("u_ecid"));
			jsonArray.add(jsonObject);
		}

		return jsonArray.toString();

	}

}
