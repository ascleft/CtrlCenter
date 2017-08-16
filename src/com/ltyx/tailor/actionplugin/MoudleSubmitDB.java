package com.ltyx.tailor.actionplugin;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.zc.web.base.doman.ZCActionPluginBase;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.TimeHelper;

public class MoudleSubmitDB extends ZCActionPluginBase {

	// ����
	private String order_keys[] = new String[] { "customer_name", "customer_tel", "customer_email", "customer_address", "customer_tips", "operator_id", "operator_name",
			"uskin_code" };
	// ���³ߴ�
	private String measure_keys[] = new String[] { "ling_wei", "xiong_wei", "yao_wei", "du_wei", "dibian", "houshen_chang", "jian_kuan", "jian_kuan_qian", "xiu_chang_zuo",
			"xiu_chang_you", "xiutouchang_zuo", "xiutouchang_you", "xiu_fei", "xiuzhou_fei", "qianshen_chang", "qianxiong_kuan", "houbei_kuan", "duanxiu_chang",
			"duanxiu_kouwei_zuo", "duanxiu_kouwei_you" };
	// ����
	private String tech_LZX_01_keys[] = new String[] { "LZX_01", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// ����
	private String tech_LZX_02_keys[] = new String[] { "LZX_02", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// ��ͷ
	private String tech_LZX_03_keys[] = new String[] { "LZX_03", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// �Ž�
	private String tech_LZX_04_keys[] = new String[] { "LZX_04", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// �ڴ�
	private String tech_LZX_120_keys[] = new String[] { "LZX_120", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// ����
	private String tech_LZX_06_keys[] = new String[] { "LZX_06", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// �°ڻ���
	private String tech_LZX_17_keys[] = new String[] { "LZX_17", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// ��Ƭ��ʽ
	private String tech_LZX_26_keys[] = new String[] { "LZX_26", "sequence", "name", "property_1", "property_2", "property_3", "tips" };// ���װ�����
	// ����
	private String LZX_11_CHAR[] = new String[] { "LZX_11_CHAR_TYPE", "sequence", "name", "LZX_11_CHAR_COLOR", "LZX_13_FOR_CHAR", "LZX_11_CHAR_WORD", "tips" };// ��������
	private String LZX_11_PIC[] = new String[] { "LZX_11_PIC_TYPE", "sequence", "name", "LZX_11_PIC_COLOR", "LZX_13_FOR_PIC", "LZX_11_PIC_NUM", "tips" };// ����ͼ��

	public MoudleSubmitDB(HttpServletRequest req) {
		this.req = req;
	}

	@Override
	public boolean doJobs() {

		boolean actionState = false;

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
				if ("1".equals(getString("LZX_11_FOR_CHAR_SWITCH"))) {
					pstmt.executeUpdate(getSqlInsertTech(LZX_11_CHAR, autoIncKey, 11, "��������"));
				}
				if ("1".equals(getString("LZX_11_FOR_PIC_SWITCH"))) {
					pstmt.executeUpdate(getSqlInsertTech(LZX_11_PIC, autoIncKey, 12, "����ͼ��"));
				}

			} else {
				// throw an exception from here
				Log.err("�޷����붩�����޷���������ID");
			}

			actionState = true;

		} catch (SQLException e) {
			e.printStackTrace();

			actionState = false;

		} finally {
			try {
				DBconn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return actionState;

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

	private ArrayList<KV> makeAl(String keys[]) {
		ArrayList<KV> al = new ArrayList<KV>();
		for (String key : keys) {
			al.add(new KV(key, getString(key)));
		}
		return al;
	}

	private class KV {

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

}
