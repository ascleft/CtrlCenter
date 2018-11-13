package com.ltyx.core.action;

import java.util.ArrayList;

import com.zc.support.doman.CCActionSupport;
import com.zc.support.service.DBHelper;
import com.zc.support.service.StringHelper;
import com.zc.support.service.DBHelper.SelectBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LogMgrtAction extends CCActionSupport {

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

		String lines = getReqParamString("lines");

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

		if (id_code.length() > 0) {

			sql += "WHERE ";

			if (id_type.equals("tel")) {
				sql += "u.user='" + id_code + "' ";
			} else if (id_type.equals("ecid")) {
				sql += "u.ecid='" + id_code + "' ";
			} else if (id_type.equals("id")) {
				sql += "u.id='" + id_code + "' ";
			} else {
				sql += "u.name like '%" + id_code + "%' ";
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

		} else {

			sql += "WHERE ";

			sql += "main.tags like '%" + " " + "%' ";

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
		}

		sql += "ORDER BY main.id DESC LIMIT 0," + lines + "";

		ArrayList<SelectBean> searchResult = DBHelper.select("m_keys", "m_tags", "m_snapshot", "m_time", "u_name", "u_tel", "u_ecid", "e_snapshot").condition(sql).exe();
		JSONArray jsonArray = new JSONArray();
		for (SelectBean bean : searchResult) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("m_keys", bean.get("m_keys"));
			jsonObject.put("m_tags", bean.get("m_tags"));
			jsonObject.put("m_snapshot", makel(bean.get("m_snapshot")));
			jsonObject.put("m_time", bean.get("m_time"));
			jsonObject.put("e_snapshot", bean.get("e_snapshot"));
			jsonObject.put("u_name", bean.get("u_name"));
			jsonObject.put("u_tel", bean.get("u_tel"));
			jsonObject.put("u_ecid", bean.get("u_ecid"));
			jsonArray.add(jsonObject);
		}

		return jsonArray.toString();

	}

	public String makel(String snapshot) {

		String[][] dic = new String[][] {

				{ "0", "订单", "" }, //
				{ "1", "收货人姓名", "customer_name" }, //
				{ "1", "收货人手机号（已隐藏）", "customer_tel" }, //
				{ "1", "收货地址（已隐藏）", "customer_address" }, //
				{ "1", "账户手机号（客户经理使用）", "customer_tel_target" }, //
				{ "1", "订单备注", "customer_tips" }, //
				{ "1", "操作员ＩＤ（已隐藏）", "operator_id" }, //
				{ "1", "操作员姓名（已隐藏）", "operator_name" }, //
				//
				{ "0", "摘要", "" }, //
				{ "1", "设计师款编号", "design_code" }, //
				{ "1", "主面料", "uskin_code" }, //
				{ "1", "衬衫类型", "tailor_type" }, //
				{ "1", "宽松度", "easy_type" }, //
				{ "1", "主唛", "YX_08" }, //
				{ "1", "包装", "YX_09" }, //
				{ "1", "订单交期", "order_delivery_time" }, //
				{ "1", "订单类型", "order_mtm_type" }, //
				{ "1", "订单工艺费", "order_processing_cost" }, //
				{ "1", "订单商品数量", "order_production_count" }, //
				//
				{ "0", "尺寸", "" }, //
				{ "1", "尺寸类型", "measure_type" }, //
				{ "1", "尺码", "size" }, //
				{ "1", "领围", "ling_wei" }, //
				{ "1", "胸围", "xiong_wei" }, //
				{ "1", "中腰围（腰围）", "yao_wei" }, //
				{ "1", "肚围", "du_wei" }, //
				{ "1", "底边（臀围）", "dibian" }, //
				{ "1", "内外穿", "neiwaichuan" }, //
				{ "1", "后身长（内穿）", "houshen_chang_nei" }, //
				{ "1", "后身长（外穿）", "houshen_chang_wai" }, //
				{ "1", "左袖头长（左腕围）", "xiutouchang_zuo" }, //
				{ "1", "右袖头长（右腕围）", "xiutouchang_you" }, //
				{ "1", "袖肥（大臂围）", "xiu_fei" }, //
				{ "1", "袖肘肥（小臂围）", "xiuzhou_fei" }, //
				{ "1", "肩宽", "jian_kuan" }, //
				{ "1", "长袖长", "xiu_chang" }, //
				{ "1", "短袖长", "duanxiu_chang" }, //
				{ "1", "短袖口围", "duanxiu_kouwei" }, //
				{ "1", "胸高", "chest_height" }, //
				{ "1", "胸距", "chest_distance" }, //
				{ "1", "前身长", "qianshen_chang" }, //
				{ "1", "前胸宽", "qianxiong_kuan" }, //
				{ "1", "后背宽", "houbei_kuan" }, //
				{ "1", "身高", "height" }, //
				{ "1", "体重", "weight" }, //
				//
				{ "0", "尺寸-特体", "" }, //
				{ "1", "前冲后掰肩", "spc_b_sho_f" }, //
				{ "1", "左肩型", "spc_b_sho_l" }, //
				{ "1", "右肩型", "spc_b_sho_r" }, //
				{ "1", "前弓后仰体", "spc_b_spi_s" }, //
				{ "1", "驼背", "spc_b_spi_h" }, //
				{ "1", "胸型", "spc_b_che_n" }, //
				{ "1", "肚型", "spc_b_abd_n" }, //
				{ "1", "袖子臂型", "spc_b_sle_n" }, //
				{ "1", "袖窿深下挖", "spc_b_muf_d" }, //
				{ "1", "袖窿深上调", "spc_b_muf_u" }, //
				//
				{ "0", "工艺", "" }, //
				{ "1", "领型", "LZX_01" }, //
				{ "1", "领撑", "lingcheng" }, //
				{ "1", "袖头", "LZX_02" }, //
				{ "1", "门襟", "LZX_03" }, //
				{ "1", "口袋", "LZX_04" }, //
				{ "1", "袖褶", "LZX_120" }, //
				{ "1", "后片款式", "LZX_17" }, //
				{ "1", "下摆弧度", "LZX_06" }, //
				{ "1", "侧缝底摆贴布", "LZX_26" }, //
				{ "1", "翻领袖头明线", "mingxian" }, //
				{ "1", "侧缝工艺", "cefeng" }, //
				{ "1", "特殊工艺", "special_technics" }, //
				//
				{ "0", "辅料", "" }, //
				{ "1", "纽扣", "kouzi" }, //
				{ "1", "织带", "zhidai" }, //
				{ "1", "织带位置", "weizhi_zhidai" }, //
				{ "1", "缝制线（已隐藏）", "line_color_location_1" }, //
				{ "1", "装饰线（已隐藏）", "line_color_location_2" }, //
				{ "1", "钉扣线（已隐藏）", "line_color_location_3" }, //
				{ "1", "锁眼线（已隐藏）", "line_color_location_4" }, //
				{ "1", "衬布", "chenbu" }, //
				{ "1", "嵌条（已隐藏）", "qiantiao" }, //
				//
				{ "0", "刺绣", "" }, //
				{ "1", "是否使用文字刺绣", "LZX_11_FOR_CHAR_SWITCH" }, //
				{ "1", "刺绣文字位置", "LZX_13_FOR_CHAR" }, //
				{ "1", "刺绣文字颜色", "LZX_11_CHAR_COLOR" }, //
				{ "1", "刺绣文字字体", "LZX_11_CHAR_TYPE" }, //
				{ "1", "刺绣文字高度", "LZX_11_CHAR_SIZE" }, //
				{ "1", "刺绣文字内容", "LZX_11_CHAR_WORD" }, //
				{ "1", "是否使用图片刺绣", "LZX_11_FOR_PIC_SWITCH" }, //
				{ "1", "刺绣图案位置", "LZX_13_FOR_PIC" }, //
				{ "1", "刺绣图案颜色", "LZX_11_PIC_COLOR" }, //
				{ "1", "刺绣图案系列", "LZX_11_PIC_TYPE" }, //
				{ "1", "刺绣图案高度", "LZX_11_PIC_SIZE" }, //
				{ "1", "刺绣图案编号", "LZX_11_PIC_NUM" }, //
				{ "1", "客供图案刺绣数量", "cixiu_kegong_num" }, //
				//
				{ "0", "配色", "" }, //
				{ "1", "配色面料", "uskin_code_2" }, //
				{ "1", "配色面料位置", "weizhi_peise" }, //
				//
				{ "0", "报价", "" }, //
				{ "1", "差价描述", "prices_desc" }, //
				{ "1", "系统报价", "prices_system" }, //
				{ "1", "自主报价", "prices_now" }, //

		};

		String newSnapshot = "";
		String newSrcReq = "";

		try {
			JSONObject src_snapshot = JSONObject.fromObject(snapshot);
			JSONObject reqsrc = src_snapshot.getJSONObject("src_req");
			for (int i = 0; i < dic.length; i++) {
				String itemTemp = "";
				try {
					if ("0".equals(dic[i][0])) {
						itemTemp += "<br/>" + "一一一一一一一一一" + dic[i][1] + "一一一一一一一一一" + "<br/>";
					} else if ("1".equals(dic[i][0])) {
						itemTemp += StringHelper.fillRightMIX(dic[i][1], 15, "一") + ">";
						itemTemp += (reqsrc.get(dic[i][2]) != null) ? reqsrc.get(dic[i][2]) : "当前接口中未定义该参数";
					}
				} catch (Exception e) {
					// TODO: handle exception
					itemTemp += dic[i][1] + "快照未包含";
				}
				newSrcReq += itemTemp + "<br/>";
			}
			src_snapshot.put("src_req", newSrcReq);
			newSnapshot = src_snapshot.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			newSnapshot += snapshot;
		}

		return newSnapshot;

	}

}
