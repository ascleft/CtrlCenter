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
		// 当尺寸类型指定为“needless”时直接跳过校验插件
		if ("needless".equals(getString("measure_type"))) {
			return true;
		}
		// 男性必填内容校验
		if (!check_maleMeasure()) {
			return false;
		}
		// 顺利执行返回
		return true;
	}

	private boolean check_maleMeasure() {
		for (String key : maleBodyMeasure.keySet()) {
			if (!maleBodyMeasure.get(key).check(getInt(key))) {
				Log.Nano.TagByLine(maleBodyMeasure.get(key) + "未通过", "web-key:" + key, "web-value:" + getString(key), "本地转型值:" + getInt(key), "高精度转型:" + getDouble(key));
				return false;
			}
//			Log.Nano.TagByLine(maleBodyMeasure.get(key) + "通过", "web-key:" + key, "web-value:" + getString(key), "本地转型值:" + getInt(key), "高精度转型:" + getDouble(key));

		}
		return true;
	}

	private void init() {
		maleBodyMeasure = new HashMap<String, Cell>();
		maleBodyMeasure.put("ling_wei", new Cell(true, "领围", 0, 100));
		maleBodyMeasure.put("xiong_wei", new Cell(true, "胸围", 0, 100));
		maleBodyMeasure.put("yao_wei", new Cell(true, "腰围", 0, 100));
		maleBodyMeasure.put("du_wei", new Cell(false, "肚围", 0, 100));
		maleBodyMeasure.put("dibian", new Cell(true, "底边", 0, 100));
		maleBodyMeasure.put("houshen_chang", new Cell(true, "后身长", 0, 100));
		maleBodyMeasure.put("jian_kuan", new Cell(true, "肩宽", 0, 100));
		maleBodyMeasure.put("jian_kuan_qian", new Cell(false, "前肩宽", 0, 100));
		maleBodyMeasure.put("xiu_fei", new Cell(true, "袖肥", 0, 100));
		maleBodyMeasure.put("qianshen_chang", new Cell(false, "前身长", 0, 100));
		maleBodyMeasure.put("qianxiong_kuan", new Cell(false, "前胸宽", 0, 100));
		maleBodyMeasure.put("houbei_kuan", new Cell(false, "后背宽", 0, 100));
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
					data = "当前" + name + "为必填内容，且不得为零" + "," + "请重新填写";
					return false;
				}
				if (input < Min) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "当前" + name + "为" + input + "，" + "已经低于下限" + Min + "，" + "请重新填写";
					return false;
				}
				if (input > Max) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "当前" + name + "为" + input + "，" + "已经超过上限" + Max + "，" + "请重新填写";
					return false;
				}
				return true;
			} else {
				return true;
			}
		}
	}

}
