package com.ltyx.tailor.action;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.zc.web.base.doman.ZCActionSupport;
import com.zc.web.base.doman.ZCHttpParam;
import com.zc.web.base.service.ZCHttpReq;
import com.zc.web.base.service.ZCReqManager;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.TimeHelper;
import com.zc.web.base.service.URLConfigHelper;

public class SubmitTailorFormActionBackUp extends ZCActionSupport {

	/**
	 * 该Action用于获取填写表单
	 *
	 */
	private static final long serialVersionUID = 10087L;
	// 订单
	String order_keys[] = new String[] { "customer_name", "customer_tel", "customer_email", "customer_address", "customer_tips", "operator_id", "operator_name", "uskin_code" };
	// 成衣尺寸
	String measure_keys[] = new String[] { "ling_wei", "xiong_wei", "yao_wei", "du_wei", "dibian", "houshen_chang", "jian_kuan", "jian_kuan_qian", "xiu_chang_zuo",
			"xiu_chang_you", "xiutouchang_zuo", "xiutouchang_you", "xiu_fei", "xiuzhou_fei", "qianshen_chang", "qianxiong_kuan", "houbei_kuan", "duanxiu_chang",
			"duanxiu_kouwei_zuo", "duanxiu_kouwei_you" };
	// 工艺
	String tech_LZX_01_keys[] = new String[] { "LZX_01", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// 领型
	String tech_LZX_02_keys[] = new String[] { "LZX_02", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// 袖头
	String tech_LZX_03_keys[] = new String[] { "LZX_03", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// 门襟
	String tech_LZX_04_keys[] = new String[] { "LZX_04", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// 口袋
	String tech_LZX_120_keys[] = new String[] { "LZX_120", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// 袖褶
	String tech_LZX_06_keys[] = new String[] { "LZX_06", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// 下摆弧度
	String tech_LZX_17_keys[] = new String[] { "LZX_17", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// 后片款式
	String tech_LZX_26_keys[] = new String[] { "LZX_26", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// 侧缝底摆贴布
	// 刺绣
	String LZX_11_CHAR[] = new String[] { "LZX_11_CHAR_TYPE", "sequence", "name", "LZX_11_CHAR_COLOR", "LZX_13_FOR_CHAR", "LZX_11_CHAR_WORD", "tips" };// 刺绣文字
	String LZX_11_PIC[] = new String[] { "LZX_11_PIC_TYPE", "sequence", "name", "LZX_11_PIC_COLOR", "LZX_13_FOR_PIC", "LZX_11_PIC_NUM", "tips" };// 刺绣图案

	public String submit() {
		Log.Nano.tag("提交衬衫信息订单", "开始");

		init(true);

		ZCReqManager.showParams(request);

		boolean CI_SUCC = checkInput();
		System.out.println("CI_SUCC " + CI_SUCC);

		if (CI_SUCC) {

			boolean EC_SUCC = saveEC();
			System.out.println("EC_SUCC " + EC_SUCC);

			if (EC_SUCC) {

				boolean DB_SUCC = saveDB();
				System.out.println("DB_SUCC" + DB_SUCC);

				if (DB_SUCC) {
					ERRCODE = "0";
					ERRDESC = "succ";
					JSONObject datacontent = new JSONObject();
					datacontent.put("CONTENT", getCSV());
					datacontent.put("NAME", "鲁泰优纤智能下单系统" + TimeHelper.getTimeMS());
					data = datacontent.toString();
				}
			}
		}

		writeResp("提交衬衫信息订单");

		Log.Nano.tag("提交衬衫信息订单", "结束");

		return null;

	}

	public boolean checkInput() {

		{
			String uskin_code_type = getReqParamValue("uskin_code_type");
			String uskin_code = getReqParamValue("uskin_code");

			if ("PBC".equals(uskin_code_type) && uskin_code.length() < 5) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的客供面料运单号";
				return false;
			}
			if ("USKIN".equals(uskin_code_type) && uskin_code.length() < 5) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的USKIN编码";
				return false;
			}

		}
		{
			String garment_type = getReqParamValue("garment_type");
			String style_name = getReqParamValue("style_name");

			if (("man".equals(garment_type) || "woman".equals(garment_type)) && style_name.length() < 3) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的设计师推荐款编码或名称";
				return false;
			}

			if ("other".equals(garment_type) && style_name.length() < 3) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的商品编码";
				return false;
			}
		}
		{
			String customer_tel = getReqParamValue("customer_tel");
			if (customer_tel.indexOf("@") == -1) {
				if (customer_tel.length() != 11) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "请填写正确的手机号码";
					return false;
				}
			}

			String customer_name = getReqParamValue("customer_name");
			if (customer_name.length() < 2) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写客户姓名";
				return false;
			}

			String customer_tel_target = getReqParamValue("customer_tel_target");
			if (customer_tel_target.length() != 11) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写正确的被量体人手机号";
				return false;
			}

		}

		{
			String uskin_code_type = getReqParamValue("uskin_code_type");// 0基本款，1推荐款，2自由搭配
			String uskin_code_length = getReqParamValue("uskin_code_length");
			String uskin_code_color = getReqParamValue("uskin_code_color");
			if ("PBC".equals(uskin_code_type) && uskin_code_length.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写客供面料用量（米）";
				return false;
			}
			if ("PBC".equals(uskin_code_type) && uskin_code_color.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请填写客供面料颜色描述";
				return false;
			}
			Log.Nano.tag("uskin_code_type", uskin_code_type);
		}

		{
			String tailor_type = getReqParamValue("tailor_type");
			String duanxiu_chang = getReqParamValue("duanxiu_chang");
			String duanxiu_kouwei_zuo = getReqParamValue("duanxiu_kouwei_zuo");
			String duanxiu_kouwei_you = getReqParamValue("duanxiu_kouwei_you");
			if ("27".equals(tailor_type) && (duanxiu_chang.length() == 0 || duanxiu_kouwei_zuo.length() == 0 || duanxiu_kouwei_you.length() == 0)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请补全短袖长及左右短袖口围信息";
				return false;
			}
		}
		{
			String tailor_type = getReqParamValue("tailor_type");
			String xiu_chang_zuo = getReqParamValue("xiu_chang_zuo");
			String xiu_chang_you = getReqParamValue("xiu_chang_you");
			String xiutouchang_zuo = getReqParamValue("xiutouchang_zuo");
			String xiutouchang_you = getReqParamValue("xiutouchang_you");
			String xiuzhou_fei = getReqParamValue("xiuzhou_fei");
			if ("26".equals(tailor_type)
					&& (xiu_chang_zuo.length() == 0 || xiu_chang_you.length() == 0 || xiutouchang_zuo.length() == 0 || xiutouchang_you.length() == 0 || xiuzhou_fei.length() == 0)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请补全左右袖长、袖头长及袖肘肥信息";
				return false;
			}
		}

		{
			ArrayList<String[]> al_key_toast = new ArrayList<String[]>();
			// 成衣尺寸
			al_key_toast.add(new String[] { "ling_wei", "请填写领围信息" });
			al_key_toast.add(new String[] { "xiong_wei", "请填写胸围信息" });
			al_key_toast.add(new String[] { "yao_wei", "请填写腰围信息" });
			// al_key_toast.add(new String[] { "du_wei", "请填写肚围信息" });
			al_key_toast.add(new String[] { "dibian", "请填写底边信息" });
			al_key_toast.add(new String[] { "houshen_chang", "请填写后身长信息" });
			al_key_toast.add(new String[] { "jian_kuan", "请填写肩宽信息" });
			// al_key_toast.add(new String[] { "jian_kuan_qian", "请填写前肩宽信息" });
			al_key_toast.add(new String[] { "xiu_fei", "请填写袖肥信息" });
			al_key_toast.add(new String[] { "qianshen_chang", "请填写前身长信息" });
			al_key_toast.add(new String[] { "qianxiong_kuan", "请填写前胸宽信息" });
			al_key_toast.add(new String[] { "houbei_kuan", "请填写后背宽信息" });

			al_key_toast.add(new String[] { "prices", "请填写报价" });

			for (String[] temp_key : al_key_toast) {
				if (getReqParamValue(temp_key[0]).length() == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = temp_key[1];
					return false;
				}
			}

		}

		{
			String LZX_11_FOR_CHAR_SWITCH = getReqParamValue("LZX_11_FOR_CHAR_SWITCH");
			String LZX_11_CHAR_WORD = getReqParamValue("LZX_11_CHAR_WORD");
			if ("1".equals(LZX_11_FOR_CHAR_SWITCH) && LZX_11_CHAR_WORD.length() == 0) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "当前选择为使用文字刺绣，请补全刺绣内容。";
				return false;
			}
		}

		return true;
	}

	public boolean saveEC() {
		ZCHttpParam param = new ZCHttpParam();

		param.addParam("bn", getReqParamValue("uskin_code")); // USKIN编码
		param.addParam("quantity", "1"); // 商品数量
		param.addParam("total_amount", getReqParamValue("prices")); // 总金额 总金额
		if ("PBC".equals(getReqParamValue("uskin_code_type"))) {
			param.addParam("memo", getReqParamValue("customer_tips") + // 订单附言
					" 面料用量：" + getReqParamValue("uskin_code_length") + // 面料用量
					" 面料颜色：" + getReqParamValue("uskin_code_color") + // 面料颜色
					" 面料花型：" + getReqParamValue("uskin_code_pattern")// 订单花型
			);
		} else {
			param.addParam("memo", getReqParamValue("customer_tips"));// 订单附言
		}
		param.addParam("shipping_dlytype", ""); // 配送ID
		param.addParam("is_tax", ""); // 是否开发票：true 为开发票，false为不开发票 是否开发票：
		param.addParam("tax_title", ""); // 发票抬头 发票抬头
		param.addParam("tax_content", ""); // 发票内容1为服装，2商品明细
		param.addParam("tax_type", ""); // 发票类型： 发票类型 1个人，2企业
		param.addParam("consignee_name", getReqParamValue("customer_name")); // 收货人姓名
		param.addParam("consignee_addr", getReqParamValue("customer_address")); // 收货人详细地址
		param.addParam("consignee_mobile", getReqParamValue("customer_tel")); // 收货人手机
		param.addParam("design_style_id", getReqParamValue("design_style_id")); // 推荐款式ID
		param.addParam("embroidery_ids_3", getReqParamValue("LZX_13_FOR_CHAR")); // 绣字位置
		param.addParam("embroidery_ids_2", getReqParamValue("LZX_11_CHAR_TYPE")); // 绣字字体
		param.addParam("embroidery_ids_words", getReqParamValue("LZX_11_CHAR_WORD")); // 绣字文字
		param.addParam("type_ids_collar", getReqParamValue("LZX_01")); // 领型
		param.addParam("type_ids_cuff", getReqParamValue("LZX_02")); // 袖口
		param.addParam("type_ids_frontfly", getReqParamValue("LZX_03")); // 门襟
		param.addParam("style_name", getReqParamValue("style_name")); // 搭配名称例2015SS-001
		param.addParam("style_id", ""); // 基础款式ID 基础款式ID，例：2015SS-001的ID是13
		param.addParam("style_type", getReqParamValue("style_type")); // 款式类型0基本款，1推荐款，2自由搭配
		param.addParam("easy_ids", "25"); // 宽松度：24修身，25舒适 宽松度
		param.addParam("sleeve_ids", getReqParamValue("tailor_type")); // 长短袖：26长袖，27短袖
		param.addParam("collocation_ids_collar", getReqParamValue("LZX_01")); // 领型款式
		param.addParam("collocation_ids_cuff", getReqParamValue("LZX_02")); // 袖口款式
		param.addParam("collocation_ids_frontfly", getReqParamValue("LZX_03")); // 门襟款式
		param.addParam("position_ids_10", getReqParamValue("LZX_04")); // 口袋款式
		param.addParam("position_ids_11", ""); // 纽扣款式 纽扣款式
		param.addParam("position_ids_12", "30"); // 领撑款式 领撑款式
		param.addParam("position_ids_9", ""); // 领标款式 领标款式
		param.addParam("data_type", getReqParamValue("measure_type")); // 量体体数据类型：
		param.addParam("customer_name", getReqParamValue("customer_name") + "4j0u0n8y9i00726" + getReqParamValue("customer_tel_target")); // 被量人姓名
		param.addParam("customer_mobile", getReqParamValue("customer_tel")); // 被量人手机
		param.addParam("height", getReqParamValue("height")); // 身高 （暂时不用，可置空）
		// 身高
		param.addParam("weight", getReqParamValue("weight")); // 体重 （暂时不用，可置空）
		// 体重
		param.addParam("neck_around", getReqParamValue("ling_wei")); // 领围
		param.addParam("chest_around", getReqParamValue("xiong_wei")); // 胸围
		param.addParam("mid_waist_around", getReqParamValue("yao_wei")); // 中腰围
		param.addParam("waist_around", getReqParamValue("yao_wei")); // 腰围
		param.addParam("hip_around", getReqParamValue("dibian")); // 臀围 臀围
		param.addParam("back_length", getReqParamValue("houshen_chang")); // 后衣长
		param.addParam("outside_back_length", getReqParamValue("houshen_chang")); // 后衣长|外穿
		param.addParam("shoulder_width", getReqParamValue("jian_kuan")); // 肩宽
		param.addParam("long_sleeve_length", getReqParamValue("xiu_chang_zuo")); // 长袖长
		param.addParam("left_wrist_around", getReqParamValue("xiutouchang_zuo")); // 左腕围
		param.addParam("right_wrist_around", getReqParamValue("xiutouchang_you")); // 右腕围
		param.addParam("bicep_around", getReqParamValue("xiu_fei")); // 臂围
		param.addParam("forearm_around", getReqParamValue("xiuzhou_fei")); // 小臂围
		param.addParam("front_length", getReqParamValue("qianshen_chang")); // 前身长
		param.addParam("chest_width", getReqParamValue("qianxiong_kuan")); // 胸宽
		param.addParam("back_width", getReqParamValue("houbei_kuan")); // 后背宽
		param.addParam("short_sleeve_length", getReqParamValue("duanxiu_chang")); // 短袖长
		param.addParam("short_sleeve_opening_around", getReqParamValue("duanxiu_kouwei_zuo")); // 短袖口围
		param.addParam("chest_height", getReqParamValue("chest_height")); // 胸高
		// （暂时不用，可置空）
		// 胸高
		param.addParam("chest_distance", getReqParamValue("chest_distance")); // 胸距
		// （暂时不用，可置空）
		// 胸距
		param.addParam("measurement_remark", ""); // 量体备注
		param.addParam("special_remark", getReqParamValue("customer_tips")); // 特体备注
		param.addParam("member_mobile", getReqParamValue("customer_tel")); // 会员手机号/会员邮箱/会员名
		param.addParam("operator_id", getReqParamValue("operator_id")); // 操作人id
		param.addParam("gift_box_ids", getReqParamValue("YX_09")); // 包装
		param.addParam("garment_type", getReqParamValue("garment_type")); // 量体、服装类型

		// String httpResp =
		// CCHttpReq.sendGet("http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/SubTailorEC.action",
		// param);
		String httpResp = ZCHttpReq.sendGet(URLConfigHelper.Url_SubTailor, param);
		Log.Nano.tag("Save Resp From EC", httpResp);

		JSONObject jsonHttpResp = JSONObject.fromObject(httpResp);
		String jsonERRCODE = jsonHttpResp.getString("ERRCODE");
		String jsonERRDESC = jsonHttpResp.getString("ERRDESC");

		if ("0".equals(jsonERRCODE) && "succ".equals(jsonERRDESC)) {
			return true;
		} else {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "EC错误码：" + jsonERRCODE + " EC错误描述：" + jsonERRDESC;
			return false;
		}

	}

	public boolean saveDB() {

		creatDBConn();

		String autoIncKey = "";

		try {
			String sql_insert_order = getSqlInsertOrder(order_keys);
			PreparedStatement pstmt = (PreparedStatement) DBconn.prepareStatement(sql_insert_order);
			pstmt.executeUpdate();
			ResultSet rs = (ResultSet) pstmt.getGeneratedKeys(); // 获取结果
			if (rs.next()) {
				autoIncKey = rs.getString(1);// 获取自增ID

				// 成衣尺寸
				pstmt.executeUpdate(getSqlInsertMeasure(measure_keys, autoIncKey));

				// 工艺信息
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_01_keys, autoIncKey, 1, "领型"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_02_keys, autoIncKey, 2, "袖头"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_03_keys, autoIncKey, 3, "门襟"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_04_keys, autoIncKey, 4, "口袋"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_120_keys, autoIncKey, 5, "袖褶"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_06_keys, autoIncKey, 6, "下摆弧度"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_17_keys, autoIncKey, 7, "后片款式"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_26_keys, autoIncKey, 8, "侧缝底摆贴布"));

				// 刺绣信息
				if ("1".equals(getReqParamValue("LZX_11_FOR_CHAR_SWITCH"))) {
					pstmt.executeUpdate(getSqlInsertTech(LZX_11_CHAR, autoIncKey, 11, "刺绣文字"));
				}
				if ("1".equals(getReqParamValue("LZX_11_FOR_PIC_SWITCH"))) {
					pstmt.executeUpdate(getSqlInsertTech(LZX_11_PIC, autoIncKey, 12, "刺绣图案"));
				}

			} else {
				// throw an exception from here
				Log.err("无法插入订单：无法捕获自增ID");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBconn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;

	}

	private String getSql(String ACTION, String TABLE, ArrayList<KV> kval) {
		String keyTemp = "";
		String valtemp = "";

		keyTemp += " (";
		valtemp += " ('";
		for (KV kv : kval) {
			keyTemp += kv.key + ", ";
			valtemp += kv.val + "', '";
		}
		keyTemp = keyTemp.substring(0, keyTemp.length() - 2);
		valtemp = valtemp.substring(0, valtemp.length() - 4);
		keyTemp += " )";
		valtemp += "')";

		String sql = ACTION + " " + TABLE + keyTemp + " VALUES" + valtemp;

		return sql;
	}

	private String getSqlInsertOrder(String keys[]) {
		ArrayList<KV> kval = makeAl(keys);
		kval.add(new KV("operator_time", TimeHelper.getTime()));
		kval.add(new KV("quickphoto", ""));
		String sql = getSql("INSERT INTO", "order4user", kval);
		Log.Nano.tag("sql getSqlInsertOrder", sql);
		return sql;
	}

	private String getSqlInsertMeasure(String keys[], String orderId) {
		ArrayList<KV> kval = makeAl(keys);
		kval.add(new KV("orderid", orderId));
		String sql = getSql("INSERT INTO", "measure4user", kval);
		Log.Nano.tag("sql getSqlInsertMeasure", sql);
		return sql;
	}

	private String getSqlInsertTech(String keys[], String orderId, int sequence, String name) {
		ArrayList<KV> kval = makeAl(keys);
		kval.add(new KV("orderid", orderId));
		kval.get(0).key = "code";
		kval.get(1).key = "sequence";
		kval.get(2).key = "name";
		kval.get(3).key = "property_1";
		kval.get(4).key = "property_2";
		kval.get(5).key = "property_3";
		kval.get(6).key = "tips";
		kval.get(1).val = "" + sequence;
		kval.get(2).val = name;
		String sql = getSql("INSERT INTO", "tech4user", kval);
		Log.Nano.tag("sql getSqlInsertTech", sql);
		return sql;
	}

	public ArrayList<KV> makeAl(String keys[]) {
		ArrayList<KV> al = new ArrayList<KV>();
		for (String key : keys) {
			al.add(new KV(key, getReqParamValue(key)));
		}
		return al;
	}

	public String getReqParamValue(String key) {

		String return_value = null;
		String[] input_values = null;

		input_values = request.getParameterValues(key);

		if (input_values != null) {
			if (input_values.length == 1) {
				return_value = input_values[0].toString().trim();
			} else {
				return_value = "";
				for (String temp_input_value : input_values) {
					return_value += temp_input_value.trim() + ",";
				}
				Log.Nano.tag(key, "出现多选选项", return_value);
			}
		} else {
			return_value = "";
			Log.Nano.tag(key, "请求动作中未包含该参数");
		}

		return return_value;

	}

	class KV {

		public KV(String key, String value) {
			super();
			this.key = key;
			this.val = value;
		}

		String key;
		String val;
		String def;
		String toast;
	}

	public String getCSV() {
		String csvTitle_1 = "";
		String csvBody_1 = "";

		String csvBody_1_1 = "";
		String csvBody_1_2 = "";
		String csvBody_1_3 = "";
		String csvBody_1_4 = "";
		String csvBody_1_5 = "";

		String csvTitle_2 = "";
		String csvBody_2 = "";

		csvTitle_1 += "客户姓名" + ",";
		csvBody_1 += getReqParamValue("customer_name") + ",";
		csvTitle_1 += "定制店名称" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "定制店编号" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "收货地址" + ",";
		csvBody_1 += getReqParamValue("customer_address") + ",";
		csvTitle_1 += "收货人" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "收货联系方式" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "量体内容" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "身高" + ",";
		csvBody_1 += getReqParamValue("shengao") + ",";
		csvTitle_1 += "体重" + ",";
		csvBody_1 += getReqParamValue("tizhong") + ",";
		csvTitle_1 += "领围" + ",";
		csvBody_1 += getReqParamValue("ling_wei") + ",";
		csvTitle_1 += "胸围" + ",";
		csvBody_1 += getReqParamValue("xiong_wei") + ",";
		csvTitle_1 += "腰围" + ",";
		csvBody_1 += getReqParamValue("yao_wei") + ",";
		csvTitle_1 += "肚围" + ",";
		csvBody_1 += getReqParamValue("du_wei") + ",";
		csvTitle_1 += "底边" + ",";
		csvBody_1 += getReqParamValue("dibian") + ",";
		csvTitle_1 += "后衣长" + ",";
		csvBody_1 += getReqParamValue("houshen_chang") + ",";
		csvTitle_1 += "肩宽" + ",";
		csvBody_1 += getReqParamValue("jian_kuan") + ",";
		csvTitle_1 += "长袖长" + ",";
		csvBody_1 += getReqParamValue("xiu_chang_zuo") + ",";
		csvTitle_1 += "左腕围" + ",";
		csvBody_1 += getReqParamValue("xiutouchang_zuo") + ",";
		csvTitle_1 += "右腕围" + ",";
		csvBody_1 += getReqParamValue("xiutouchang_you") + ",";
		csvTitle_1 += "袖肥" + ",";
		csvBody_1 += getReqParamValue("xiu_fei") + ",";
		csvTitle_1 += "小臂围" + ",";
		csvBody_1 += getReqParamValue("xiuzhou_fei") + ",";
		csvTitle_1 += "前身长" + ",";
		csvBody_1 += getReqParamValue("qianshen_chang") + ",";
		csvTitle_1 += "前胸宽" + ",";
		csvBody_1 += getReqParamValue("qianxiong_kuan") + ",";
		csvTitle_1 += "后背宽" + ",";
		csvBody_1 += getReqParamValue("houbei_kuan") + ",";
		csvTitle_1 += "短袖长" + ",";
		csvBody_1 += getReqParamValue("duanxiu_chang") + ",";
		csvTitle_1 += "短袖口" + ",";
		csvBody_1 += getReqParamValue("duanxiu_kouwei_zuo") + ",";
		csvTitle_1 += "号衣尺码" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "客供面料快递单号" + ",";

		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += "DP后成衣尺寸" + ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";

		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += "面料缩率" + ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";

		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += "DP前尺寸" + ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";

		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += "参考尺寸" + ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";

		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += "调整数据" + ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";

		csvTitle_2 += "订单编号" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "快递方式" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "优纤面料" + ",";
		csvBody_2 += getReqParamValue("uskin_code") + ",";
		csvTitle_2 += "客供面料编号" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "配色面料" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "配色部位" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "身型" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "后背款式" + ",";
		csvBody_2 += getReqParamValue("LZX_17") + ",";
		csvTitle_2 += "长短袖" + ",";
		csvBody_2 += getReqParamValue("tailor_type") + ",";
		csvTitle_2 += "领型" + ",";
		csvBody_2 += getReqParamValue("LZX_01") + ",";
		csvTitle_2 += "领插片" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "袖头" + ",";
		csvBody_2 += getReqParamValue("LZX_02") + ",";
		csvTitle_2 += "门襟" + ",";
		csvBody_2 += getReqParamValue("LZX_03") + ",";
		csvTitle_2 += "口袋" + ",";
		csvBody_2 += getReqParamValue("LZX_04") + ",";
		csvTitle_2 += "纽扣" + ",";
		csvBody_2 += getReqParamValue("kouzi") + ",";
		csvTitle_2 += "主唛" + ",";
		csvBody_2 += getReqParamValue("YX_08") + ",";
		csvTitle_2 += "明线宽" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "侧缝工艺" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "嵌条" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "衬布" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "刺绣字体" + ",";
		csvBody_2 += getReqParamValue("LZX_11_CHAR_TYPE") + ",";
		csvTitle_2 += "刺绣大小" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "刺绣位置" + ",";
		csvBody_2 += getReqParamValue("LZX_13_FOR_CHAR") + ",";
		csvTitle_2 += "刺绣内容" + ",";
		csvBody_2 += getReqParamValue("LZX_11_CHAR_WORD") + ",";
		csvTitle_2 += "刺绣颜色" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "洗唛成分" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "包装" + ",";
		csvBody_2 += getReqParamValue("YX_09") + ",";
		csvTitle_2 += "快递方式" + ",";
		csvBody_2 += getReqParamValue("") + ",";

		return csvTitle_1 + "\n" + csvBody_1 + "\n" + csvBody_1_1 + "\n" + csvBody_1_2 + "\n" + csvBody_1_3 + "\n" + csvBody_1_4 + "\n" + csvBody_1_5 + "\n" + csvTitle_2 + "\n"
				+ csvBody_2;
	}

}
