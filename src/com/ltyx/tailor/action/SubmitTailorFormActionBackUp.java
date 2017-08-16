package com.ltyx.tailor.action;

import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.zc.web.base.doman.ZCActionSupport;
import com.zc.web.base.doman.ZCHttpParam;
import com.zc.web.base.service.CCHttpReq;
import com.zc.web.base.service.CCReqManager;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.TimeHelper;
import com.zc.web.base.service.URLConfigHelper;

public class SubmitTailorFormActionBackUp extends ZCActionSupport {

	/**
	 * ��Action���ڻ�ȡ��д��
	 * 
	 */
	private static final long serialVersionUID = 10087L;
	// ����
	String order_keys[] = new String[] { "customer_name", "customer_tel", "customer_email", "customer_address", "customer_tips", "operator_id", "operator_name", "uskin_code" };
	// ���³ߴ�
	String measure_keys[] = new String[] { "ling_wei", "xiong_wei", "yao_wei", "du_wei", "dibian", "houshen_chang", "jian_kuan", "jian_kuan_qian", "xiu_chang_zuo",
			"xiu_chang_you", "xiutouchang_zuo", "xiutouchang_you", "xiu_fei", "xiuzhou_fei", "qianshen_chang", "qianxiong_kuan", "houbei_kuan", "duanxiu_chang",
			"duanxiu_kouwei_zuo", "duanxiu_kouwei_you" };
	// ����
	String tech_LZX_01_keys[] = new String[] { "LZX_01", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// ����
	String tech_LZX_02_keys[] = new String[] { "LZX_02", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// ��ͷ
	String tech_LZX_03_keys[] = new String[] { "LZX_03", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// �Ž�
	String tech_LZX_04_keys[] = new String[] { "LZX_04", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// �ڴ�
	String tech_LZX_120_keys[] = new String[] { "LZX_120", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// ����
	String tech_LZX_06_keys[] = new String[] { "LZX_06", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// �°ڻ���
	String tech_LZX_17_keys[] = new String[] { "LZX_17", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// ��Ƭ��ʽ
	String tech_LZX_26_keys[] = new String[] { "LZX_26", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// ���װ�����
	// ����
	String LZX_11_CHAR[] = new String[] { "LZX_11_CHAR_TYPE", "sequence", "name", "LZX_11_CHAR_COLOR", "LZX_13_FOR_CHAR", "LZX_11_CHAR_WORD", "tips" };// ��������
	String LZX_11_PIC[] = new String[] { "LZX_11_PIC_TYPE", "sequence", "name", "LZX_11_PIC_COLOR", "LZX_13_FOR_PIC", "LZX_11_PIC_NUM", "tips" };// ����ͼ��

	public String submit() {
		Log.Nano.tag("�ύ������Ϣ����", "��ʼ");

		init(true);

		CCReqManager.showParams(request);

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
					datacontent.put("NAME", "³̩���������µ�ϵͳ" + TimeHelper.getTimeMS());
					data = datacontent.toString();
				}
			}
		}

		writeResp("�ύ������Ϣ����");

		Log.Nano.tag("�ύ������Ϣ����", "����");

		return null;

	}

	public boolean checkInput() {

		{
			String uskin_code_type = getReqParamValue("uskin_code_type");
			String uskin_code = getReqParamValue("uskin_code");

			if ("PBC".equals(uskin_code_type) && uskin_code.length() < 5) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "����д��ȷ�Ŀ͹������˵���";
				return false;
			}
			if ("USKIN".equals(uskin_code_type) && uskin_code.length() < 5) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "����д��ȷ��USKIN����";
				return false;
			}

		}
		{
			String garment_type = getReqParamValue("garment_type");
			String style_name = getReqParamValue("style_name");

			if (("man".equals(garment_type) || "woman".equals(garment_type)) && style_name.length() < 3) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "����д��ȷ�����ʦ�Ƽ�����������";
				return false;
			}

			if ("other".equals(garment_type) && style_name.length() < 3) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "����д��ȷ����Ʒ����";
				return false;
			}
		}
		{
			String customer_tel = getReqParamValue("customer_tel");
			if (customer_tel.indexOf("@") == -1) {
				if (customer_tel.length() != 11) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "����д��ȷ���ֻ�����";
					return false;
				}
			}

			String customer_name = getReqParamValue("customer_name");
			if (customer_name.length() < 2) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "����д�ͻ�����";
				return false;
			}

			String customer_tel_target = getReqParamValue("customer_tel_target");
			if (customer_tel_target.length() != 11) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "����д��ȷ�ı��������ֻ���";
				return false;
			}

		}

		{
			String uskin_code_type = getReqParamValue("uskin_code_type");// 0�����1�Ƽ��2���ɴ���
			String uskin_code_length = getReqParamValue("uskin_code_length");
			String uskin_code_color = getReqParamValue("uskin_code_color");
			if ("PBC".equals(uskin_code_type) && uskin_code_length.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "����д�͹������������ף�";
				return false;
			}
			if ("PBC".equals(uskin_code_type) && uskin_code_color.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "����д�͹�������ɫ����";
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
				data = "�벹ȫ���䳤�����Ҷ����Χ��Ϣ";
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
				data = "�벹ȫ�����䳤����ͷ�����������Ϣ";
				return false;
			}
		}

		{
			ArrayList<String[]> al_key_toast = new ArrayList<String[]>();
			// ���³ߴ�
			al_key_toast.add(new String[] { "ling_wei", "����д��Χ��Ϣ" });
			al_key_toast.add(new String[] { "xiong_wei", "����д��Χ��Ϣ" });
			al_key_toast.add(new String[] { "yao_wei", "����д��Χ��Ϣ" });
			// al_key_toast.add(new String[] { "du_wei", "����д��Χ��Ϣ" });
			al_key_toast.add(new String[] { "dibian", "����д�ױ���Ϣ" });
			al_key_toast.add(new String[] { "houshen_chang", "����д������Ϣ" });
			al_key_toast.add(new String[] { "jian_kuan", "����д�����Ϣ" });
			// al_key_toast.add(new String[] { "jian_kuan_qian", "����дǰ�����Ϣ" });
			al_key_toast.add(new String[] { "xiu_fei", "����д�����Ϣ" });
			al_key_toast.add(new String[] { "qianshen_chang", "����дǰ����Ϣ" });
			al_key_toast.add(new String[] { "qianxiong_kuan", "����дǰ�ؿ���Ϣ" });
			al_key_toast.add(new String[] { "houbei_kuan", "����д�󱳿���Ϣ" });

			al_key_toast.add(new String[] { "prices", "����д����" });

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
				data = "��ǰѡ��Ϊʹ�����ִ��壬�벹ȫ�������ݡ�";
				return false;
			}
		}

		return true;
	}

	public boolean saveEC() {
		ZCHttpParam param = new ZCHttpParam();

		param.addParam("bn", getReqParamValue("uskin_code")); // USKIN����
		param.addParam("quantity", "1"); // ��Ʒ����
		param.addParam("total_amount", getReqParamValue("prices")); // �ܽ�� �ܽ��
		if ("PBC".equals(getReqParamValue("uskin_code_type"))) {
			param.addParam("memo", getReqParamValue("customer_tips") + // ��������
					" ����������" + getReqParamValue("uskin_code_length") + // ��������
					" ������ɫ��" + getReqParamValue("uskin_code_color") + // ������ɫ
					" ���ϻ��ͣ�" + getReqParamValue("uskin_code_pattern")// ��������
			);
		} else {
			param.addParam("memo", getReqParamValue("customer_tips"));// ��������
		}
		param.addParam("shipping_dlytype", ""); // ����ID
		param.addParam("is_tax", ""); // �Ƿ񿪷�Ʊ��true Ϊ����Ʊ��falseΪ������Ʊ �Ƿ񿪷�Ʊ��
		param.addParam("tax_title", ""); // ��Ʊ̧ͷ ��Ʊ̧ͷ
		param.addParam("tax_content", ""); // ��Ʊ����1Ϊ��װ��2��Ʒ��ϸ
		param.addParam("tax_type", ""); // ��Ʊ���ͣ� ��Ʊ���� 1���ˣ�2��ҵ
		param.addParam("consignee_name", getReqParamValue("customer_name")); // �ջ�������
		param.addParam("consignee_addr", getReqParamValue("customer_address")); // �ջ�����ϸ��ַ
		param.addParam("consignee_mobile", getReqParamValue("customer_tel")); // �ջ����ֻ�
		param.addParam("design_style_id", getReqParamValue("design_style_id")); // �Ƽ���ʽID
		param.addParam("embroidery_ids_3", getReqParamValue("LZX_13_FOR_CHAR")); // ����λ��
		param.addParam("embroidery_ids_2", getReqParamValue("LZX_11_CHAR_TYPE")); // ��������
		param.addParam("embroidery_ids_words", getReqParamValue("LZX_11_CHAR_WORD")); // ��������
		param.addParam("type_ids_collar", getReqParamValue("LZX_01")); // ����
		param.addParam("type_ids_cuff", getReqParamValue("LZX_02")); // ���
		param.addParam("type_ids_frontfly", getReqParamValue("LZX_03")); // �Ž�
		param.addParam("style_name", getReqParamValue("style_name")); // ����������2015SS-001
		param.addParam("style_id", ""); // ������ʽID ������ʽID������2015SS-001��ID��13
		param.addParam("style_type", getReqParamValue("style_type")); // ��ʽ����0�����1�Ƽ��2���ɴ���
		param.addParam("easy_ids", "25"); // ���ɶȣ�24����25���� ���ɶ�
		param.addParam("sleeve_ids", getReqParamValue("tailor_type")); // �����䣺26���䣬27����
		param.addParam("collocation_ids_collar", getReqParamValue("LZX_01")); // ���Ϳ�ʽ
		param.addParam("collocation_ids_cuff", getReqParamValue("LZX_02")); // ��ڿ�ʽ
		param.addParam("collocation_ids_frontfly", getReqParamValue("LZX_03")); // �Ž��ʽ
		param.addParam("position_ids_10", getReqParamValue("LZX_04")); // �ڴ���ʽ
		param.addParam("position_ids_11", ""); // Ŧ�ۿ�ʽ Ŧ�ۿ�ʽ
		param.addParam("position_ids_12", "30"); // ��ſ�ʽ ��ſ�ʽ
		param.addParam("position_ids_9", ""); // ����ʽ ����ʽ
		param.addParam("data_type", getReqParamValue("measure_type")); // �������������ͣ�
		param.addParam("customer_name", getReqParamValue("customer_name") + "4j0u0n8y9i00726" + getReqParamValue("customer_tel_target")); // ����������
		param.addParam("customer_mobile", getReqParamValue("customer_tel")); // �������ֻ�
		param.addParam("height", getReqParamValue("height")); // ��� ����ʱ���ã����ÿգ�
																// ���
		param.addParam("weight", getReqParamValue("weight")); // ���� ����ʱ���ã����ÿգ�
																// ����
		param.addParam("neck_around", getReqParamValue("ling_wei")); // ��Χ
		param.addParam("chest_around", getReqParamValue("xiong_wei")); // ��Χ
		param.addParam("mid_waist_around", getReqParamValue("yao_wei")); // ����Χ
		param.addParam("waist_around", getReqParamValue("yao_wei")); // ��Χ
		param.addParam("hip_around", getReqParamValue("dibian")); // ��Χ ��Χ
		param.addParam("back_length", getReqParamValue("houshen_chang")); // ���³�
		param.addParam("outside_back_length", getReqParamValue("houshen_chang")); // ���³�|�⴩
		param.addParam("shoulder_width", getReqParamValue("jian_kuan")); // ���
		param.addParam("long_sleeve_length", getReqParamValue("xiu_chang_zuo")); // ���䳤
		param.addParam("left_wrist_around", getReqParamValue("xiutouchang_zuo")); // ����Χ
		param.addParam("right_wrist_around", getReqParamValue("xiutouchang_you")); // ����Χ
		param.addParam("bicep_around", getReqParamValue("xiu_fei")); // ��Χ
		param.addParam("forearm_around", getReqParamValue("xiuzhou_fei")); // С��Χ
		param.addParam("front_length", getReqParamValue("qianshen_chang")); // ǰ��
		param.addParam("chest_width", getReqParamValue("qianxiong_kuan")); // �ؿ�
		param.addParam("back_width", getReqParamValue("houbei_kuan")); // �󱳿�
		param.addParam("short_sleeve_length", getReqParamValue("duanxiu_chang")); // ���䳤
		param.addParam("short_sleeve_opening_around", getReqParamValue("duanxiu_kouwei_zuo")); // �����Χ
		param.addParam("chest_height", getReqParamValue("chest_height")); // �ظ�
																			// ����ʱ���ã����ÿգ�
																			// �ظ�
		param.addParam("chest_distance", getReqParamValue("chest_distance")); // �ؾ�
																				// ����ʱ���ã����ÿգ�
																				// �ؾ�
		param.addParam("measurement_remark", ""); // ���屸ע
		param.addParam("special_remark", getReqParamValue("customer_tips")); // ���屸ע
		param.addParam("member_mobile", getReqParamValue("customer_tel")); // ��Ա�ֻ���/��Ա����/��Ա��
		param.addParam("operator_id", getReqParamValue("operator_id")); // ������id
		param.addParam("gift_box_ids", getReqParamValue("YX_09")); // ��װ
		param.addParam("garment_type", getReqParamValue("garment_type")); // ���塢��װ����

		// String httpResp =
		// CCHttpReq.sendGet("http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/SubTailorEC.action",
		// param);
		String httpResp = CCHttpReq.sendGet(URLConfigHelper.Url_SubTailor, param);
		Log.Nano.tag("Save Resp From EC", httpResp);

		JSONObject jsonHttpResp = JSONObject.fromObject(httpResp);
		String jsonERRCODE = jsonHttpResp.getString("ERRCODE");
		String jsonERRDESC = jsonHttpResp.getString("ERRDESC");

		if ("0".equals(jsonERRCODE) && "succ".equals(jsonERRDESC)) {
			return true;
		} else {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "EC�����룺" + jsonERRCODE + " EC����������" + jsonERRDESC;
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
			ResultSet rs = (ResultSet) pstmt.getGeneratedKeys(); // ��ȡ���
			if (rs.next()) {
				autoIncKey = rs.getString(1);// ��ȡ����ID

				// ���³ߴ�
				pstmt.executeUpdate(getSqlInsertMeasure(measure_keys, autoIncKey));

				// ������Ϣ
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_01_keys, autoIncKey, 1, "����"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_02_keys, autoIncKey, 2, "��ͷ"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_03_keys, autoIncKey, 3, "�Ž�"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_04_keys, autoIncKey, 4, "�ڴ�"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_120_keys, autoIncKey, 5, "����"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_06_keys, autoIncKey, 6, "�°ڻ���"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_17_keys, autoIncKey, 7, "��Ƭ��ʽ"));
				pstmt.executeUpdate(getSqlInsertTech(tech_LZX_26_keys, autoIncKey, 8, "���װ�����"));

				// ������Ϣ
				if ("1".equals(getReqParamValue("LZX_11_FOR_CHAR_SWITCH"))) {
					pstmt.executeUpdate(getSqlInsertTech(LZX_11_CHAR, autoIncKey, 11, "��������"));
				}
				if ("1".equals(getReqParamValue("LZX_11_FOR_PIC_SWITCH"))) {
					pstmt.executeUpdate(getSqlInsertTech(LZX_11_PIC, autoIncKey, 12, "����ͼ��"));
				}

			} else {
				// throw an exception from here
				Log.err("�޷����붩�����޷���������ID");
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
				Log.Nano.tag(key, "���ֶ�ѡѡ��", return_value);
			}
		} else {
			return_value = "";
			Log.Nano.tag(key, "��������δ�����ò���");
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

		csvTitle_1 += "�ͻ�����" + ",";
		csvBody_1 += getReqParamValue("customer_name") + ",";
		csvTitle_1 += "���Ƶ�����" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "���Ƶ���" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "�ջ���ַ" + ",";
		csvBody_1 += getReqParamValue("customer_address") + ",";
		csvTitle_1 += "�ջ���" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "�ջ���ϵ��ʽ" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "��������" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "���" + ",";
		csvBody_1 += getReqParamValue("shengao") + ",";
		csvTitle_1 += "����" + ",";
		csvBody_1 += getReqParamValue("tizhong") + ",";
		csvTitle_1 += "��Χ" + ",";
		csvBody_1 += getReqParamValue("ling_wei") + ",";
		csvTitle_1 += "��Χ" + ",";
		csvBody_1 += getReqParamValue("xiong_wei") + ",";
		csvTitle_1 += "��Χ" + ",";
		csvBody_1 += getReqParamValue("yao_wei") + ",";
		csvTitle_1 += "��Χ" + ",";
		csvBody_1 += getReqParamValue("du_wei") + ",";
		csvTitle_1 += "�ױ�" + ",";
		csvBody_1 += getReqParamValue("dibian") + ",";
		csvTitle_1 += "���³�" + ",";
		csvBody_1 += getReqParamValue("houshen_chang") + ",";
		csvTitle_1 += "���" + ",";
		csvBody_1 += getReqParamValue("jian_kuan") + ",";
		csvTitle_1 += "���䳤" + ",";
		csvBody_1 += getReqParamValue("xiu_chang_zuo") + ",";
		csvTitle_1 += "����Χ" + ",";
		csvBody_1 += getReqParamValue("xiutouchang_zuo") + ",";
		csvTitle_1 += "����Χ" + ",";
		csvBody_1 += getReqParamValue("xiutouchang_you") + ",";
		csvTitle_1 += "���" + ",";
		csvBody_1 += getReqParamValue("xiu_fei") + ",";
		csvTitle_1 += "С��Χ" + ",";
		csvBody_1 += getReqParamValue("xiuzhou_fei") + ",";
		csvTitle_1 += "ǰ��" + ",";
		csvBody_1 += getReqParamValue("qianshen_chang") + ",";
		csvTitle_1 += "ǰ�ؿ�" + ",";
		csvBody_1 += getReqParamValue("qianxiong_kuan") + ",";
		csvTitle_1 += "�󱳿�" + ",";
		csvBody_1 += getReqParamValue("houbei_kuan") + ",";
		csvTitle_1 += "���䳤" + ",";
		csvBody_1 += getReqParamValue("duanxiu_chang") + ",";
		csvTitle_1 += "�����" + ",";
		csvBody_1 += getReqParamValue("duanxiu_kouwei_zuo") + ",";
		csvTitle_1 += "���³���" + ",";
		csvBody_1 += getReqParamValue("") + ",";
		csvTitle_1 += "�͹����Ͽ�ݵ���" + ",";

		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += "DP����³ߴ�" + ",";
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
		csvBody_1_2 += "��������" + ",";
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
		csvBody_1_3 += "DPǰ�ߴ�" + ",";
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
		csvBody_1_4 += "�ο��ߴ�" + ",";
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
		csvBody_1_5 += "��������" + ",";
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

		csvTitle_2 += "�������" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "��ݷ�ʽ" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "��������" + ",";
		csvBody_2 += getReqParamValue("uskin_code") + ",";
		csvTitle_2 += "�͹����ϱ��" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "��ɫ����" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "��ɫ��λ" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "����" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "�󱳿�ʽ" + ",";
		csvBody_2 += getReqParamValue("LZX_17") + ",";
		csvTitle_2 += "������" + ",";
		csvBody_2 += getReqParamValue("tailor_type") + ",";
		csvTitle_2 += "����" + ",";
		csvBody_2 += getReqParamValue("LZX_01") + ",";
		csvTitle_2 += "���Ƭ" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "��ͷ" + ",";
		csvBody_2 += getReqParamValue("LZX_02") + ",";
		csvTitle_2 += "�Ž�" + ",";
		csvBody_2 += getReqParamValue("LZX_03") + ",";
		csvTitle_2 += "�ڴ�" + ",";
		csvBody_2 += getReqParamValue("LZX_04") + ",";
		csvTitle_2 += "Ŧ��" + ",";
		csvBody_2 += getReqParamValue("kouzi") + ",";
		csvTitle_2 += "����" + ",";
		csvBody_2 += getReqParamValue("YX_08") + ",";
		csvTitle_2 += "���߿�" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "��칤��" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "Ƕ��" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "�Ĳ�" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "��������" + ",";
		csvBody_2 += getReqParamValue("LZX_11_CHAR_TYPE") + ",";
		csvTitle_2 += "�����С" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "����λ��" + ",";
		csvBody_2 += getReqParamValue("LZX_13_FOR_CHAR") + ",";
		csvTitle_2 += "��������" + ",";
		csvBody_2 += getReqParamValue("LZX_11_CHAR_WORD") + ",";
		csvTitle_2 += "������ɫ" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "ϴ��ɷ�" + ",";
		csvBody_2 += getReqParamValue("") + ",";
		csvTitle_2 += "��װ" + ",";
		csvBody_2 += getReqParamValue("YX_09") + ",";
		csvTitle_2 += "��ݷ�ʽ" + ",";
		csvBody_2 += getReqParamValue("") + ",";

		return csvTitle_1 + "\n" + csvBody_1 + "\n" + csvBody_1_1 + "\n" + csvBody_1_2 + "\n" + csvBody_1_3 + "\n" + csvBody_1_4 + "\n" + csvBody_1_5 + "\n" + csvTitle_2 + "\n"
				+ csvBody_2;
	}

}
