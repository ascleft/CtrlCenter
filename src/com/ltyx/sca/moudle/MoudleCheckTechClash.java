package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCheckTechClash extends ZCBaseActionSupportPlugin {

	public MoudleCheckTechClash(HttpServletRequest req) {
		this.name = "校验 工艺冲突";
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			// LZX-01-13开头的领型，只能搭配LZX-03-04门襟
			String LZX_01 = getReqParamString("LZX_01");
			String LZX_03 = getReqParamString("LZX_03");
			if ("LZX-01-13G".equals(LZX_01)) {
				if (!"LZX-03-04".equals(LZX_03)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "领型门襟冲突，【领型】【LZX-01-13G】仅允许搭配【门襟】【LZX-03-04】";
					return false;
				}

			}
			if ("LZX-01-13Z".equals(LZX_01)) {
				if (!"LZX-03-04".equals(LZX_03)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "领型门襟冲突，【领型】【LZX-01-13Z】仅允许搭配【门襟】【LZX-03-04】";
					return false;
				}

			}
			if ("LZX-01-13A".equals(LZX_01)) {
				if (!"LZX-03-04".equals(LZX_03)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "领型门襟冲突，【领型】【LZX-01-13A】仅允许搭配【门襟】【LZX-03-04】";
					return false;
				}
			}
		}

		{

			String customer_tips = getReqParamString("customer_tips");
			String order_processing_cost = getReqParamString("order_processing_cost");

			if (customer_tips.length() > 0) {
				if (!"客供款".equals(order_processing_cost)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您填写了【生产备注】，请在【工艺类型】选项中选择【客供款】";
					return false;
				}

			}
		}

		{
			// 装饰纽扣使用
			String button_decorative_code = getReqParamString("button_decorative_code");
			String button_decorative_num = getReqParamString("button_decorative_num");
			String button_decorative_pos = getReqParamString("button_decorative_pos");
			if ("0".equals(button_decorative_num)) {
				if (button_decorative_code.length() > 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您选择的【装饰扣数量】为0，【装饰扣编号】处的选择无效";
					return false;
				}
				if (!"LZX-55-00".equals(button_decorative_pos)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您选择的【装饰扣数量】为0，【装饰扣位置】处的选择无效";
					return false;
				}
			} else {
				if (button_decorative_code.length() == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您选择的【装饰扣数量】不为0，请选择【装饰扣编号】";
					return false;
				}
				if ("LZX-55-00".equals(button_decorative_pos)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您选择的【装饰扣数量】不为0，请选择【装饰扣位置】";
					return false;
				}
			}
		}

		{
			String weizhi_peise = getReqParamString("weizhi_peise");
			String LZX_03 = getReqParamString("LZX_03");
			String uskin_code_2 = getReqParamString("uskin_code_2");

			if ((!weizhi_peise.equals("LZX-80-00")) && (uskin_code_2.trim().length() < 3)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "您已选择【配色位置】，请填写【配色面料】";
				return false;
			}

			// 礼服拼贴款做法一 LZX-20-01 门襟要求LZX-03-06/07 GZ开头面料
			// 礼服拼贴款做法二 LZX-20-02 门襟要求LZX-03-04 除GZ开头的以外
			// 礼服拼贴款做法三 LZX-20-03 门襟要求LZX-03-06/07 GZ开头面料
			// 礼服拼贴款做法四 LZX-20-04 门襟要求LZX-03-06/07 GZ开头面料

			if (weizhi_peise.equals("LZX-20-01")) {
				if (!(LZX_03.equals("LZX-03-06") || LZX_03.equals("LZX-03-07"))) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "仅当【门襟】为【LZX-03-06】或【LZX-03-07】时才可选用【礼服拼贴款做法一】";
					return false;
				}
				if (!isGZ(uskin_code_2)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "仅当【配色面料】为【GZ开头面料】才可选用【礼服拼贴款做法一】";
					return false;
				}
			}
			if (weizhi_peise.equals("LZX-20-02")) {
				if (!(LZX_03.equals("LZX-03-04"))) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "仅当【门襟】为【LZX-03-04】时才可选用【礼服拼贴款做法二】";
					return false;
				}
				if (isGZ(uskin_code_2)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "【配色面料】为【GZ开头面料】时，不支持【礼服拼贴款做法二】";
					return false;
				}
			}
			if (weizhi_peise.equals("LZX-20-03")) {
				if (!(LZX_03.equals("LZX-03-06") || LZX_03.equals("LZX-03-07"))) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "仅当【门襟】为【LZX-03-06】或【LZX-03-07】时才可选用【礼服拼贴款做法三】";
					return false;
				}
				if (!isGZ(uskin_code_2)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "仅当【配色面料】为【GZ开头面料】才可选用【礼服拼贴款做法三】";
					return false;
				}
			}
			if (weizhi_peise.equals("LZX-20-04")) {
				if (!(LZX_03.equals("LZX-03-06") || LZX_03.equals("LZX-03-07"))) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "仅当【门襟】为【LZX-03-06】或【LZX-03-07】时才可选用【礼服拼贴款做法四】";
					return false;
				}
				if (!isGZ(uskin_code_2)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "仅当【配色面料】为【GZ开头面料】才可选用【礼服拼贴款做法四】";
					return false;
				}
			}
		}

		return true;

	}

	private boolean isGZ(String uskin_code) {
		String[] dic = { "GZ01-01", "GZ01-02", "GZ01-03", "GZ01-04", "GZ01-05", "GZ01-06", "GZ01-07", "GZ01-08" };
		for (int i = 0; i < dic.length; i++) {
			if (uskin_code.equals(dic[i])) {
				return true;
			}
		}
		return false;
	}

}
