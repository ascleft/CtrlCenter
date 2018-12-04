package com.ltyx.sca.action.plugin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.service.Log;

public class MoudleCheckMeasureWoman extends ZCBaseActionSupportPlugin {

	private Map<String, Cell> maleBodyMeasure = null;

	public MoudleCheckMeasureWoman(HttpServletRequest req) {
		this.name = "校验 尺寸";
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

	private boolean check_special_2() {

		{
			// 腰围 胸围-腰围差A LZX-17-02 A>21，其余款<14.5
			String LZX_17 = getReqParamString("LZX_17");
			int xiong_wei = getReqParamInt("xiong_wei");
			int yao_wei = getReqParamInt("yao_wei");
			int A = xiong_wei - yao_wei;
			if ("LZX-17-02".equals(LZX_17) || "LZX-17-05".equals(LZX_17)) {
				if (A >= 21) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "胸围腰围差不合理";
					return false;
				}
			}
			if ("LZX-17-01".equals(LZX_17) || "LZX-17-03".equals(LZX_17) || "LZX-17-04".equals(LZX_17)) {
				if (A >= 14.5) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "胸围腰围差不合理";
					return false;
				}
			}
		}

		{
			// 腹围 腹围-腰围差B 5>B>3
			int du_wei = getReqParamInt("du_wei");
			int yao_wei = getReqParamInt("yao_wei");
			if (du_wei > 0 && yao_wei > 0) {
				int B = du_wei - yao_wei;
				if (B >= 5 || B <= 3) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "腹围腰围差不合理";
					return false;
				}
			}
		}

		{
			// 底摆 底摆-腹围差C 8>C>-2
			int dibian = getReqParamInt("dibian");
			int du_wei = getReqParamInt("du_wei");
			if (dibian > 0 && du_wei > 0) {
				int C = dibian - du_wei;
				if (C >= 8 || C <= -2) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "底摆腹围差不合理";
					return false;
				}
			}
		}

		{
			// 袖肥 袖肥D 胸围/2.5+2>D>胸围/2.5-5.5
			int xiong_wei = getReqParamInt("xiong_wei");
			int xiu_fei = getReqParamInt("xiu_fei");
			if (xiong_wei > 0 && xiu_fei > 0) {
				if ((xiu_fei >= xiong_wei / 2.5 + 2) || (xiu_fei <= xiong_wei / 2.5 - 5.5)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "袖肥胸围尺寸关系不合理";
					return false;
				}
			}
		}

		{
			// 袖肘肥 袖肘肥-袖头长E 12>F>5
			int xiuzhou_fei = getReqParamInt("xiuzhou_fei");
			int xiutouchang_zuo = getReqParamInt("xiutouchang_zuo");
			if (xiuzhou_fei > 0 && xiutouchang_zuo > 0) {
				int E = xiuzhou_fei - xiutouchang_zuo;
				if (E >= 12 || E <= 5) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "袖肘肥袖头长尺寸关系不合理";
					return false;
				}
			}
		}

		{
			// 短袖口 袖肥-短袖口F 8>F>2
			int xiu_fei = getReqParamInt("xiu_fei");
			int duanxiu_kouwei = getReqParamInt("duanxiu_kouwei");
			if (xiu_fei > 0 && duanxiu_kouwei > 0) {
				int F = xiu_fei - duanxiu_kouwei;
				if (F >= 8 || F <= 2) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "袖肥短袖口尺寸关系不合理";
					return false;
				}
			}
		}

		{
			// 前衣长 前衣长-后衣长G 8>G>-2
			String neiwaichuan = getReqParamString("neiwaichuan");
			int houshen_chang_nei = getReqParamInt("houshen_chang_nei");
			int houshen_chang_wai = getReqParamInt("houshen_chang_wai");
			int qianshen_chang = getReqParamInt("qianshen_chang");
			int G_1 = qianshen_chang - houshen_chang_nei;
			int G_2 = qianshen_chang - houshen_chang_wai;
			// YX-10-01 内穿
			if ("YX-10-01".equals(neiwaichuan)) {
				if (G_1 >= 8 || G_1 <= -2) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "前衣长后衣长尺寸关系不合理";
					return false;
				}
			}
			// YX-10-02 外穿
			if ("YX-10-02".equals(neiwaichuan)) {
				if (G_2 >= 8 || G_2 <= -2) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "前衣长后衣长尺寸关系不合理";
					return false;
				}
			}
		}

		{
			// 肩宽-前胸宽H 10>H>4.5
			int jian_kuan = getReqParamInt("jian_kuan");
			int qianxiong_kuan = getReqParamInt("qianxiong_kuan");
			if (jian_kuan > 0 && qianxiong_kuan > 0) {
				int H = jian_kuan - qianxiong_kuan;
				if (H >= 10 || H <= 4.5) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "肩宽前胸宽尺寸关系不合理";
					return false;
				}
			}
		}

		{
			// 肩宽-后背宽I 5>I≥0
			int jian_kuan = getReqParamInt("xiu_fei");
			int houbei_kuan = getReqParamInt("houbei_kuan");
			if (jian_kuan > 0 && houbei_kuan > 0) {
				int I = jian_kuan - houbei_kuan;
				if (I >= 5 || I < 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "肩宽后背宽尺寸关系不合理";
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

		maleBodyMeasure.put("ling_wei", new Cell(true, "领围", 1, 200));// 20180614耍赖改的
		maleBodyMeasure.put("xiong_wei", new Cell(true, "胸围", 1, 200));
		maleBodyMeasure.put("yao_wei", new Cell(true, "腰围(中腰围)", 1, 200));
		maleBodyMeasure.put("dibian", new Cell(true, "底边(臀围)", 1, 200));
		maleBodyMeasure.put("xiu_fei", new Cell(true, "袖肥(大臂围)", 1, 200));// 20180614耍赖改的
		maleBodyMeasure.put("jian_kuan", new Cell(true, "肩宽", 1, 200));
		maleBodyMeasure.put("qianshen_chang", new Cell(true, "前身长", 1, 200));// 20180614耍赖改的

		maleBodyMeasure.put("xiuzhou_fei", new Cell(false, "袖肘肥（小臂围）", 0, 1000));
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
