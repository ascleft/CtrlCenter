package com.ltyx.sca.action.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCheckTechYXST extends ZCBaseActionSupportPlugin {

	public MoudleCheckTechYXST(HttpServletRequest req) {
		this.request = req;
		this.name = "面料及特殊工艺校验";
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		// 特殊关系
		if (!check_special_1()) {
			return false;
		}
		// 特殊关系
		if (!check_special_2()) {
			return false;
		}
		// 特殊关系
		if (!check_special_3()) {
			return false;
		}
		// 特殊关系
		if (!check_special_4()) {
			return false;
		}

		// 顺利执行返回
		return true;
	}

	private boolean check_special_1() {

		{
			// YX-ST-00 无特殊工艺;YX-ST-01 DP成衣免烫;YX-ST-02 水洗
			String special_technics = getReqParamString("special_technics");
			String uskin_code = getReqParamString("uskin_code");
			if (isDP(uskin_code)) {
				if ("YX-ST-00".equals(special_technics)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "系统检测到您使用的面料是【优纤DP面料】，请在【工艺】【特殊工艺】中选择【DP成衣免烫】";
					return false;
				}
				if ("YX-ST-01".equals(special_technics)) {
					return true;
				}
				if ("YX-ST-02".equals(special_technics)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "系统检测到您使用的面料是【优纤DP面料】，DP面料是不支持水洗处理，请在【工艺】【特殊工艺】中选择【DP成衣免烫】";
					return false;
				}
			}
		}

		{
			// YX-ST-00 无特殊工艺;YX-ST-01 DP成衣免烫;YX-ST-02 水洗
			String special_technics = getReqParamString("special_technics");
			String uskin_code = getReqParamString("uskin_code");
			if (!isDP(uskin_code)) {
				if ("YX-ST-01".equals(special_technics)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "系统检测到您使用的面料是【优纤非DP面料】，【优纤非DP面料】不支持【DP成衣免烫】，请在【工艺】【特殊工艺】中进行调整";
					return false;
				}
			}
		}

		return true;
	}

	private boolean check_special_2() {

		{
			// YX-ST-00 无特殊工艺; YX-ST-01 DP成衣免烫; YX-ST-02 水洗
			// YX-19-01 牛腿合身; YX-19-02 0.3手工包缝
			String special_technics = getReqParamString("special_technics");
			String cefeng = getReqParamString("cefeng");
			if ("YX-ST-01".equals(special_technics)) {
				if ("YX-19-02".equals(cefeng)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "系统检测到您使用的【特殊工艺】是【DP成衣免烫】，【DP成衣免烫】仅支持【牛腿合身】，请在【工艺】【侧缝工艺】中选择【牛腿合身】";
					return false;
				}
				if ("YX-19-01".equals(cefeng)) {
					return true;
				}
			}
		}

		return true;
	}

	private boolean check_special_3() {

		{
			// YX-ST-00 无特殊工艺; YX-ST-01 DP成衣免烫; YX-ST-02 水洗
			// YX-16-07 DP专用衬布
			String special_technics = getReqParamString("special_technics");
			String chenbu = getReqParamString("chenbu");
			if ("YX-ST-01".equals(special_technics)) {
				if (!"YX-16-07".equals(chenbu)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "系统检测到您使用的【特殊工艺】是【DP成衣免烫】，【DP成衣免烫】仅支持【DP专用衬布】，请在【辅料】【衬布】中选择【DP专用衬布】";
					return false;
				}
			}
		}

		{
			// YX-16-03 300支专用
			String chenbu = getReqParamString("chenbu");
			String uskin_code = getReqParamString("uskin_code");
			if (is300(uskin_code)) {
				if (!"YX-16-03".equals(chenbu)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "系统检测到您使用的面料属于【300支面料】，【300支面料】仅支持【300支专用】，请在【辅料】【衬布】中选择【300支专用】";
					return false;
				}
			}
		}

		return true;
	}

	private boolean check_special_4() {

		{
			// YX-ST-00 无特殊工艺; YX-ST-01 DP成衣免烫; YX-ST-02 水洗
			// YX-07-51 内树脂, YX-07-54 外树脂
			String special_technics = getReqParamString("special_technics");
			String lingcheng = getReqParamString("lingcheng");
			if ("YX-ST-01".equals(special_technics)) {
				if ("YX-07-51".equals(lingcheng)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "系统检测到您使用的【特殊工艺】是【DP成衣免烫】，【DP成衣免烫】不支持【内树脂】，请在【工艺】【领撑】中调整";
					return false;
				}
			}
		}

		return true;
	}

	public boolean isDP(String name) {
		boolean isDP = false;
		int nameHead = name.indexOf("DP");
		int nameLine = name.indexOf("-");
		int nameLength = name.length();
		if (nameHead == 0 && nameLine == 4 && nameLength == 7) {
			isDP = true;
		}
		return isDP;
	}

	public boolean is300(String name) {
		boolean isDP = false;
		int nameHead = name.indexOf("UL");
		int nameLine = name.indexOf("-");
		int nameLength = name.length();
		if (nameHead == 0 && nameLine == 4 && nameLength == 7) {
			isDP = true;
		}
		return isDP;
	}

}
