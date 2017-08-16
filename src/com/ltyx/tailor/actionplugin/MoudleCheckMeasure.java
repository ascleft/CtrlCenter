package com.ltyx.tailor.actionplugin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zc.web.base.doman.ZCActionPluginBase;
import com.zc.web.base.service.Log;

public class MoudleCheckMeasure extends ZCActionPluginBase {

	private Map<String, Cell> maleBodyMeasure = null;

	public MoudleCheckMeasure(HttpServletRequest req) {
		this.req = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub
		if (maleBodyMeasure == null) {
			init();
		}
		// ���ߴ�����ָ��Ϊ��needless��ʱֱ������У����
		if ("needless".equals(getString("measure_type"))) {
			return true;
		}
		// ���Ա�������У��
		if (!check_maleMeasure()) {
			return false;
		}
		// ˳��ִ�з���
		return true;
	}

	private boolean check_maleMeasure() {
		for (String key : maleBodyMeasure.keySet()) {
			if (!maleBodyMeasure.get(key).check(getInt(key))) {
				Log.Nano.TagByLine(maleBodyMeasure.get(key) + "δͨ��", "web-key:" + key, "web-value:" + getString(key), "����ת��ֵ:" + getInt(key), "�߾���ת��:" + getDouble(key));
				return false;
			}
//			Log.Nano.TagByLine(maleBodyMeasure.get(key) + "ͨ��", "web-key:" + key, "web-value:" + getString(key), "����ת��ֵ:" + getInt(key), "�߾���ת��:" + getDouble(key));

		}
		return true;
	}

	private void init() {
		maleBodyMeasure = new HashMap<String, Cell>();
		maleBodyMeasure.put("ling_wei", new Cell(true, "��Χ", 0, 100));
		maleBodyMeasure.put("xiong_wei", new Cell(true, "��Χ", 0, 100));
		maleBodyMeasure.put("yao_wei", new Cell(true, "��Χ", 0, 100));
		maleBodyMeasure.put("du_wei", new Cell(false, "��Χ", 0, 100));
		maleBodyMeasure.put("dibian", new Cell(true, "�ױ�", 0, 100));
		maleBodyMeasure.put("houshen_chang", new Cell(true, "����", 0, 100));
		maleBodyMeasure.put("jian_kuan", new Cell(true, "���", 0, 100));
		maleBodyMeasure.put("jian_kuan_qian", new Cell(false, "ǰ���", 0, 100));
		maleBodyMeasure.put("xiu_fei", new Cell(true, "���", 0, 100));
		maleBodyMeasure.put("qianshen_chang", new Cell(false, "ǰ��", 0, 100));
		maleBodyMeasure.put("qianxiong_kuan", new Cell(false, "ǰ�ؿ�", 0, 100));
		maleBodyMeasure.put("houbei_kuan", new Cell(false, "�󱳿�", 0, 100));
	}

	class Cell {

		boolean isNecessary;
		String name;
		int Min;
		int Max;

		Cell(boolean isNecessary, String name, int Min, int Max) {
			this.isNecessary = isNecessary;
			this.name = name;
			this.Min = Min;
			this.Max = Max;
		}

		boolean check(int input) {
			if (isNecessary) {
				if (input == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "��ǰ" + name + "Ϊ�������ݣ��Ҳ���Ϊ��" + "," + "��������д";
					return false;
				}
				if (input < Min) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "��ǰ" + name + "Ϊ" + input + "��" + "�Ѿ���������" + Min + "��" + "��������д";
					return false;
				}
				if (input > Max) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "��ǰ" + name + "Ϊ" + input + "��" + "�Ѿ���������" + Max + "��" + "��������д";
					return false;
				}
				return true;
			} else {
				return true;
			}
		}
	}

}
