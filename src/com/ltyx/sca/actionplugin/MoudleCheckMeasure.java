package com.ltyx.sca.actionplugin;

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

		init();

		// 当尺寸类型指定为“号衣尺码”时直接跳过校验插件
		if ("号衣尺码".equals(getReqParamString("measure_type"))) {
			return true;
		}
		// 当尺寸类型指定为“needless”时直接跳过校验插件
		if ("needless".equals(getReqParamString("measure_type"))) {
			return true;
		}

		// 特殊关系
		if (!check_special()) {
			return false;
		}

		// 男性必填内容校验
		if (!check_maleMeasure()) {
			return false;
		}

		// 顺利执行返回
		return true;
	}

	private boolean check_special() {

		// {
		// String tailor_type = getReqParamString("tailor_type");
		// String duanxiu_chang = getReqParamString("duanxiu_chang");
		// String duanxiu_kouwei = getReqParamString("duanxiu_kouwei");
		// if ("YX-00-01".equals(tailor_type) && (duanxiu_chang.length() == 0 ||
		// duanxiu_kouwei.length() == 0)) {
		// ERRCODE = "0";
		// ERRDESC = "fail";
		// data = "请补全短袖长及短袖口围信息";
		// return false;
		// }
		// }

		// {
		// String tailor_type = getReqParamString("tailor_type");
		// String xiu_chang = getReqParamString("xiu_chang");
		// String xiutouchang_zuo = getReqParamString("xiutouchang_zuo");
		// String xiutouchang_you = getReqParamString("xiutouchang_you");
		// if ("YX-00-02".equals(tailor_type) && (xiutouchang_zuo.length() == 0
		// || xiutouchang_you.length() == 0 || xiu_chang.length() == 0)) {
		// ERRCODE = "0";
		// ERRDESC = "fail";
		// data = "请补全袖长及左右袖头长信息";
		// return false;
		// }
		// }

		{
			// YX-10-01 内穿，YX-10-02 外穿
			String neiwaichuan = getReqParamString("neiwaichuan");

			int houshen_chang_nei = getReqParamInt("houshen_chang_nei");
			int houshen_chang_wai = getReqParamInt("houshen_chang_wai");

			// YX-10-01 内穿
			if ("YX-10-01".equals(neiwaichuan)) {
				if (houshen_chang_nei == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【内穿】，请补全【后身长（内穿）】尺寸";
					return false;
				}
			}

			// YX-10-02 外穿
			if ("YX-10-02".equals(neiwaichuan)) {
				if (houshen_chang_wai == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【外穿】，请补全【后身长（外穿）】尺寸";
					return false;
				}
			}

		}

		{

			// YX-00-01 短袖，YX-00-02 长袖
			String tailor_type = getReqParamString("tailor_type");

			int duanxiu_chang = getReqParamInt("duanxiu_chang");
			int xiu_chang = getReqParamInt("xiu_chang");

			int duanxiu_kouwei = getReqParamInt("duanxiu_kouwei");
			int xiutouchang_zuo = getReqParamInt("xiutouchang_zuo");
			int xiutouchang_you = getReqParamInt("xiutouchang_you");

			// YX-00-01 短袖
			if ("YX-00-01".equals(tailor_type)) {
				if (duanxiu_chang == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【短袖】，请补全【短袖长】尺寸";
					return false;
				}
				if (duanxiu_kouwei == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【短袖】，请补全【短袖口围】尺寸";
					return false;
				}
			}

			// YX-00-02 长袖
			if ("YX-00-02".equals(tailor_type)) {
				if (xiu_chang == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【长袖】，请补全【长袖长】尺寸";
					return false;
				}
				if (xiutouchang_zuo == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【长袖】，请补全【左袖头长（左腕围）】尺寸";
					return false;
				}
				if (xiutouchang_you == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【长袖】，请补全【右袖头长（右腕围）】尺寸";
					return false;
				}
			}
		}

		return true;
	}

	private boolean check_maleMeasure() {
		for (String key : maleBodyMeasure.keySet()) {
			if (!maleBodyMeasure.get(key).check(getReqParamInt(key))) {
				Log.Nano.TagByLine(maleBodyMeasure.get(key) + "未通过", "web-key:" + key, "web-value:" + getReqParamString(key), "本地转型值:" + getReqParamInt(key),
						"高精度转型:" + getReqParamDouble(key));
				return false;
			}
		}
		return true;
	}

	private void init() {
		maleBodyMeasure = new HashMap<String, Cell>();

		maleBodyMeasure.put("ling_wei",       new Cell(true, "领围",          1, 200));// 20180614耍赖改的
		maleBodyMeasure.put("xiong_wei",      new Cell(true, "胸围",          1, 200));
		maleBodyMeasure.put("yao_wei",        new Cell(true, "腰围(中腰围)",    1, 200));
		maleBodyMeasure.put("dibian",         new Cell(true, "底边(臀围)",     1, 200));
		maleBodyMeasure.put("xiu_fei",        new Cell(true, "袖肥(大臂围)",   1, 200));// 20180614耍赖改的
		maleBodyMeasure.put("jian_kuan",      new Cell(true, "肩宽",         1, 200));
		maleBodyMeasure.put("qianshen_chang", new Cell(true, "前身长",        1, 200));// 20180614耍赖改的

		maleBodyMeasure.put("xiuzhou_fei",       new Cell(false, "袖肘肥（小臂围）", 0, 1000));
		maleBodyMeasure.put("houshen_chang_nei", new Cell(false, "后身长（内穿）", 0, 1000));
		maleBodyMeasure.put("houshen_chang_wai", new Cell(false, "后身长（外穿）", 0, 1000));
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
