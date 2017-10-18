package com.ltyx.tailor.actionplugin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.service.Log;

public class MoudleCheckMeasure extends ZCBaseActionSupportPlugin {

	private Map<String, Cell> maleBodyMeasure = null;

	public MoudleCheckMeasure(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub
		if (maleBodyMeasure == null) {
			init();
		}
		// 当尺寸类型指定为“needless”时直接跳过校验插件
		if ("needless".equals(getReqParamString("measure_type"))) {
			return true;
		}

		// 男性必填内容校验
		if (!check_maleMeasure()) {
			return false;
		}

		// 特殊关系
		if (!check_special()) {
			return false;
		}

		// 顺利执行返回
		return true;
	}

	private boolean check_special() {

		{
			String tailor_type = getReqParamString("tailor_type");
			String duanxiu_chang = getReqParamString("duanxiu_chang");
			String duanxiu_kouwei_zuo = getReqParamString("duanxiu_kouwei_zuo");
			String duanxiu_kouwei_you = getReqParamString("duanxiu_kouwei_you");
			if ("27".equals(tailor_type)
					&& (duanxiu_chang.length() == 0
							|| duanxiu_kouwei_zuo.length() == 0 || duanxiu_kouwei_you
							.length() == 0)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请补全短袖长及左右短袖口围信息";
				return false;
			}
		}

		{
			String tailor_type = getReqParamString("tailor_type");
			String xiu_chang_zuo = getReqParamString("xiu_chang_zuo");
			String xiu_chang_you = getReqParamString("xiu_chang_you");
			String xiutouchang_zuo = getReqParamString("xiutouchang_zuo");
			String xiutouchang_you = getReqParamString("xiutouchang_you");
			String xiuzhou_fei = getReqParamString("xiuzhou_fei");
			if ("26".equals(tailor_type)
					&& (xiu_chang_zuo.length() == 0
							|| xiu_chang_you.length() == 0
							|| xiutouchang_zuo.length() == 0
							|| xiutouchang_you.length() == 0 || xiuzhou_fei
							.length() == 0)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请补全左右袖长、袖头长及袖肘肥信息";
				return false;
			}
		}

		return true;
	}

	private boolean check_maleMeasure() {
		for (String key : maleBodyMeasure.keySet()) {
			if (!maleBodyMeasure.get(key).check(getReqParamInt(key))) {
				Log.Nano.TagByLine(maleBodyMeasure.get(key) + "未通过", "web-key:"
						+ key, "web-value:" + getReqParamString(key), "本地转型值:"
						+ getReqParamInt(key), "高精度转型:"
						+ getReqParamDouble(key));
				return false;
			}
			// Log.Nano.TagByLine(maleBodyMeasure.get(key) + "通过", "web-key:" +
			// key, "web-value:" + getReqParamString(key), "本地转型值:" +
			// getInt(key), "高精度转型:" + getDouble(key));

		}
		return true;
	}

	private void init() {
		maleBodyMeasure = new HashMap<String, Cell>();
		maleBodyMeasure.put("ling_wei",       new Cell(true,  "领围", 0, 1000));
		maleBodyMeasure.put("xiong_wei",      new Cell(true,  "胸围", 0, 1000));
		maleBodyMeasure.put("yao_wei",        new Cell(true,  "腰围", 0, 1000));
		maleBodyMeasure.put("du_wei",         new Cell(false, "肚围", 0, 1000));
		maleBodyMeasure.put("dibian",         new Cell(true,  "底边", 0, 1000));
		maleBodyMeasure.put("houshen_chang",  new Cell(true,  "后身长", 0, 1000));
		maleBodyMeasure.put("jian_kuan",      new Cell(true,  "肩宽", 0, 1000));
		maleBodyMeasure.put("jian_kuan_qian", new Cell(false, "前肩宽", 0, 1000));
		maleBodyMeasure.put("xiu_fei",        new Cell(true,  "袖肥", 0, 1000));
		maleBodyMeasure.put("qianshen_chang", new Cell(false, "前身长", 0, 1000));
		maleBodyMeasure.put("qianxiong_kuan", new Cell(false, "前胸宽", 0, 1000));
		maleBodyMeasure.put("houbei_kuan",    new Cell(false, "后背宽", 0, 1000));
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
					data = "当前" + name + "为" + input + "，" + "已经低于下限" + Min
							+ "，" + "请重新填写";
					return false;
				}
				if (input > Max) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "当前" + name + "为" + input + "，" + "已经超过上限" + Max
							+ "，" + "请重新填写";
					return false;
				}
				return true;
			} else {
				return true;
			}
		}
	}

}
