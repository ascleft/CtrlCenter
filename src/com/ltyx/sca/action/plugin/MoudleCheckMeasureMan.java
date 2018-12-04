package com.ltyx.sca.action.plugin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.service.Log;
import com.zc.support.service.StringHelper;

public class MoudleCheckMeasureMan extends ZCBaseActionSupportPlugin {

	private Map<String, Cell> maleBodyMeasure = null;

	public MoudleCheckMeasureMan(HttpServletRequest req) {
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

		// 特殊关系
		if (!check_special_2()) {
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
			// YX-10-01 内穿,YX-10-02 外穿
			String neiwaichuan = getReqParamString("neiwaichuan");

			int houshen_chang_nei = getReqParamInt("houshen_chang_nei");
			int houshen_chang_wai = getReqParamInt("houshen_chang_wai");

			// YX-10-01 内穿
			if ("YX-10-01".equals(neiwaichuan)) {
				if (houshen_chang_nei == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【内穿】,请补全【后身长（内穿）】尺寸";
					return false;
				}
			}

			// YX-10-02 外穿
			if ("YX-10-02".equals(neiwaichuan)) {
				if (houshen_chang_wai == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【外穿】,请补全【后身长（外穿）】尺寸";
					return false;
				}
			}

		}

		{

			// YX-00-01 短袖,YX-00-02 长袖
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
					data = "您当前选择的是【短袖】,请补全【短袖长】尺寸";
					return false;
				}
				if (duanxiu_kouwei == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【短袖】,请补全【短袖口围】尺寸";
					return false;
				}
			}

			// YX-00-02 长袖
			if ("YX-00-02".equals(tailor_type)) {
				if (xiu_chang == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【长袖】,请补全【长袖长】尺寸";
					return false;
				}
				if (xiutouchang_zuo == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【长袖】,请补全【左袖头长（左腕围）】尺寸";
					return false;
				}
				if (xiutouchang_you == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【长袖】,请补全【右袖头长（右腕围）】尺寸";
					return false;
				}
			}
		}

		return true;
	}

	private boolean check_special_2() {

		{
			// 腰围 胸围-腰围差A LZX-17-02 A>21,其余款<14.5
			String LZX_17 = getReqParamString("LZX_17");
			double xiong_wei = getReqParamDouble("xiong_wei");
			double yao_wei = getReqParamDouble("yao_wei");
			double A = xiong_wei - yao_wei;
			if ("LZX-17-02".equals(LZX_17) || "LZX-17-05".equals(LZX_17)) {
				if (A > 21) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "胸围腰围差不合理," + //
							"若确定【胸围】的尺寸为" + StringHelper.numWithLength(xiong_wei, 0, 1) + "无误," + //
							"则其对应的【腰围】范围应" + //
							"大于" + //
							StringHelper.numWithLength(xiong_wei - 21, 0, 1);//
//					data = "胸围腰围差不合理,建议【胸围-腰围≤21】";
					return false;
				}
			}
			if ("LZX-17-01".equals(LZX_17) || "LZX-17-03".equals(LZX_17) || "LZX-17-04".equals(LZX_17)) {
				if (A > 14.5) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "胸围腰围差不合理," + //
							"若确定【胸围】的尺寸为" + StringHelper.numWithLength(xiong_wei, 0, 1) + "无误," + //
							"则其对应的【腰围】范围应" + //
							"大于" + //
							StringHelper.numWithLength(xiong_wei - 14.5, 0, 1);
//					data = "胸围腰围差不合理,建议【胸围-腰围≤14.5】";
					return false;
				}
			}
		}

		{
			// 腹围 腹围-腰围差B 5>B>3
			double du_wei = getReqParamDouble("du_wei");
			double yao_wei = getReqParamDouble("yao_wei");
			if (du_wei > 0 && yao_wei > 0) {
				double B = du_wei - yao_wei;
				if (B > 5 || B < 3) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "腹围腰围差不合理," + //
							"若确定【腰围】的尺寸为" + StringHelper.numWithLength(yao_wei, 0, 1) + "无误," + //
							"则其对应的【腹围】范围应" + //
							"介于" + //
							StringHelper.numWithLength(yao_wei + 3, 0, 1) + //
							"与" + //
							StringHelper.numWithLength(yao_wei + 5, 0, 1) + //
							"之间";//
//					data = "腹围腰围差不合理,建议【5≥腹围-腰围≥3】";
					return false;
				}
			}
		}

		{
			// 底摆 底摆-腹围差C 8>C>-2
			double dibian = getReqParamDouble("dibian");
			double du_wei = getReqParamDouble("du_wei");
			if (dibian > 0 && du_wei > 0) {
				double C = dibian - du_wei;
				if (C > 8 || C < -2) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "底摆腹围差不合理," + //
							"若确定【腹围】的尺寸为" + StringHelper.numWithLength(du_wei, 0, 1) + "无误," + //
							"则其对应的【底摆】范围应" + //
							"介于" + //
							StringHelper.numWithLength(du_wei - 2, 0, 1) + //
							"与" + //
							StringHelper.numWithLength(du_wei + 8, 0, 1) + //
							"之间";//
//					data = "底摆腹围差不合理,建议【8≥底摆-腹围≥-2】";
					return false;
				}
			}
		}

		{
			// 袖肥 袖肥D 胸围/2.5+2>D>胸围/2.5-5.5
			double xiong_wei = getReqParamDouble("xiong_wei");
			double xiu_fei = getReqParamDouble("xiu_fei");
			if (xiong_wei > 0 && xiu_fei > 0) {
				if ((xiu_fei > xiong_wei / 2.5 + 2) || (xiu_fei < xiong_wei / 2.5 - 5.5)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "袖肥胸围尺寸关系不合理," + //
							"若确定【胸围】的尺寸为" + StringHelper.numWithLength(xiong_wei, 0, 1) + "无误," + //
							"则其对应的【袖肥】范围应" + //
							"介于" + //
							StringHelper.numWithLength(xiong_wei / 2.5 - 5.5, 0, 1) + //
							"与" + //
							StringHelper.numWithLength(xiong_wei / 2.5 + 2, 0, 1) + //
							"之间";//
//					data = "袖肥胸围尺寸关系不合理,建议【胸围/2.5+2≥袖肥≥胸围/2.5-5.5】";
					return false;
				}
			}
		}

		{
			// 袖肘肥 袖肘肥-袖头长E 12>F>5

			String LZX_120 = getReqParamString("LZX_120");// 袖褶
			double xiuzhou_fei = getReqParamDouble("xiuzhou_fei");
			double xiutouchang_zuo = getReqParamDouble("xiutouchang_zuo");
			if ("LZX-120-00".equals(LZX_120)) {
				if (xiuzhou_fei > 0 && xiutouchang_zuo > 0) {
					double E = xiuzhou_fei - xiutouchang_zuo;
					if (E > 12 || E < 5) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "袖肘肥袖头长尺寸关系不合理," + //
								"若确定【袖头长】的尺寸为" + StringHelper.numWithLength(xiutouchang_zuo, 0, 1) + "无误," + //
								"则其对应的【袖肘肥】范围应" + //
								"介于" + //
								StringHelper.numWithLength(xiutouchang_zuo + 5, 0, 1) + //
								"与" + //
								StringHelper.numWithLength(xiutouchang_zuo + 12, 0, 1) + //
								"之间";//
//						data = "袖肘肥袖头长尺寸关系不合理,建议【12≥袖肘肥-袖头长≥5】";
						return false;
					}
				}
			}
			if ("LZX-120-01".equals(LZX_120)) {
				if (xiuzhou_fei > 0 && xiutouchang_zuo > 0) {
					double E = xiuzhou_fei - xiutouchang_zuo;
					if (E > 14 || E < 7) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "袖肘肥袖头长尺寸关系不合理," + //
								"若确定【袖头长】的尺寸为" + StringHelper.numWithLength(xiutouchang_zuo, 0, 1) + "无误," + //
								"则其对应的【袖肘肥】范围应" + //
								"介于" + //
								StringHelper.numWithLength(xiutouchang_zuo + 7, 0, 1) + //
								"与" + //
								StringHelper.numWithLength(xiutouchang_zuo + 14, 0, 1) + //
								"之间";//
//						data = "袖肘肥袖头长尺寸关系不合理,建议【14≥袖肘肥-袖头长≥7】";
						return false;
					}
				}
			}
			if ("LZX-120-02".equals(LZX_120)) {
				if (xiuzhou_fei > 0 && xiutouchang_zuo > 0) {
					double E = xiuzhou_fei - xiutouchang_zuo;
					if (E > 18 || E < 11) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "袖肘肥袖头长尺寸关系不合理," + //
								"若确定【袖头长】的尺寸为" + StringHelper.numWithLength(xiutouchang_zuo, 0, 1) + "无误," + //
								"则其对应的【袖肘肥】范围应" + //
								"介于" + //
								StringHelper.numWithLength(xiutouchang_zuo + 11, 0, 1) + //
								"与" + //
								StringHelper.numWithLength(xiutouchang_zuo + 18, 0, 1) + //
								"之间";//
//						data = "袖肘肥袖头长尺寸关系不合理,建议【18≥袖肘肥-袖头长≥11】";
						return false;
					}
				}
			}
		}

		{
			// 短袖口 袖肥-短袖口F 8>F>2
			double xiu_fei = getReqParamDouble("xiu_fei");
			double duanxiu_kouwei = getReqParamDouble("duanxiu_kouwei");
			if (xiu_fei > 0 && duanxiu_kouwei > 0) {
				double F = xiu_fei - duanxiu_kouwei;
				if (F > 8 || F < 2) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "袖肥短袖口尺寸关系不合理," + //
							"若确定【袖肥】的尺寸为" + StringHelper.numWithLength(xiu_fei, 0, 1) + "无误," + //
							"则其对应的【短袖口】范围应" + //
							"介于" + //
							StringHelper.numWithLength(xiu_fei - 8, 0, 1) + //
							"与" + //
							StringHelper.numWithLength(xiu_fei - 2, 0, 1) + //
							"之间";//
//					data = "袖肥短袖口尺寸关系不合理,建议【8≥袖肥-短袖口≥2】";
					return false;
				}
			}
		}

		{
			// 前衣长 前衣长-后衣长G 8>G>-2
			String neiwaichuan = getReqParamString("neiwaichuan");
			double houshen_chang_nei = getReqParamDouble("houshen_chang_nei");
			double houshen_chang_wai = getReqParamDouble("houshen_chang_wai");
			double qianshen_chang = getReqParamDouble("qianshen_chang");
			double G_1 = qianshen_chang - houshen_chang_nei;
			double G_2 = qianshen_chang - houshen_chang_wai;
			// YX-10-01 内穿
			if ("YX-10-01".equals(neiwaichuan)) {
				if (G_1 > 8 || G_1 < -2) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "前衣长后衣长尺寸关系不合理," + //
							"若确定【后衣长】的尺寸为" + StringHelper.numWithLength(houshen_chang_nei, 0, 1) + "无误," + //
							"则其对应的【前衣长】范围应" + //
							"介于" + //
							StringHelper.numWithLength(houshen_chang_nei - 2, 0, 1) + //
							"与" + //
							StringHelper.numWithLength(houshen_chang_nei + 8, 0, 1) + //
							"之间";//
//					data = "前衣长后衣长尺寸关系不合理,建议【8≥前衣长-后衣长≥-2】";
					return false;
				}
			}
			// YX-10-02 外穿
			if ("YX-10-02".equals(neiwaichuan)) {
				if (G_2 > 8 || G_2 < -2) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "前衣长后衣长尺寸关系不合理," + //
							"若确定【后衣长】的尺寸为" + StringHelper.numWithLength(houshen_chang_wai, 0, 1) + "无误," + //
							"则其对应的【前衣长】范围应" + //
							"介于" + //
							StringHelper.numWithLength(houshen_chang_wai - 2, 0, 1) + //
							"与" + //
							StringHelper.numWithLength(houshen_chang_wai + 8, 0, 1) + //
							"之间";//
//					data = "前衣长后衣长尺寸关系不合理,建议【8≥前衣长-后衣长≥-2】";
					return false;
				}
			}
		}

		{
			// 前胸宽 肩宽-前胸宽H 10>H>4.5
			double jian_kuan = getReqParamDouble("jian_kuan");
			double qianxiong_kuan = getReqParamDouble("qianxiong_kuan");
			if (jian_kuan > 0 && qianxiong_kuan > 0) {
				double H = jian_kuan - qianxiong_kuan;
				if (H > 10 || H < 4.5) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "肩宽前胸宽尺寸关系不合理," + //
							"若确定【肩宽】的尺寸为" + StringHelper.numWithLength(jian_kuan, 0, 1) + "无误," + //
							"则其对应的【前胸宽】范围应" + //
							"介于" + //
							StringHelper.numWithLength(jian_kuan - 10, 0, 1) + //
							"与" + //
							StringHelper.numWithLength(jian_kuan + 4.5, 0, 1) + //
							"之间";//
//					data = "肩宽前胸宽尺寸关系不合理,建议【10≥肩宽-前胸宽≥4.5】";
					return false;
				}
			}
		}

		{
			// 后背宽 肩宽-后背宽I 5>I≥0
			double jian_kuan = getReqParamDouble("jian_kuan");
			double houbei_kuan = getReqParamDouble("houbei_kuan");
			if (jian_kuan > 0 && houbei_kuan > 0) {
				double I = jian_kuan - houbei_kuan;
				if (I > 5 || I < 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "肩宽前胸宽尺寸关系不合理," + //
							"若确定【肩宽】的尺寸为" + StringHelper.numWithLength(jian_kuan, 0, 1) + "无误," + //
							"则其对应的【后背宽】范围应" + //
							"介于" + //
							StringHelper.numWithLength(jian_kuan - 5, 0, 1) + //
							"与" + //
							StringHelper.numWithLength(jian_kuan - 0, 0, 1) + //
							"之间";//
//					data = "肩宽后背宽尺寸关系不合理,建议【5≥肩宽-后背宽≥0】";
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
					data = "当前" + name + "为必填内容,且不得为零" + "," + "请重新填写";
					return false;
				}
				if (input < Min) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "当前" + name + "为" + input + "," + "已经低于下限" + Min + "," + "请重新填写";
					return false;
				}
				if (input > Max) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "当前" + name + "为" + input + "," + "已经超过上限" + Max + "," + "请重新填写";
					return false;
				}
				return true;
			} else {
				return true;
			}
		}
	}

}
