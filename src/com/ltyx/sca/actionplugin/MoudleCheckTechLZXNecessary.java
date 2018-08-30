package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCheckTechLZXNecessary extends ZCBaseActionSupportPlugin {

	public MoudleCheckTechLZXNecessary(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			String design_code = getReqParamString("design_code");
			if ("".equals(design_code)) {
				{
					String LZX_01 = getReqParamString("LZX_01");
					if ("".equals(LZX_01)) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "您当前选择的【领型】是无效的，我猜你是在【工艺类型】选择了【复杂款】，之后选择了复杂款工艺的【领型】，然后又切换回了【基础款】，这样貌似能用低价买高价衬衫，但这显然是不对的，我们不允许。";
						return false;
					}
				}

				{
					String LZX_02 = getReqParamString("LZX_02");
					if ("".equals(LZX_02)) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "您当前选择的【袖头】是无效的，我猜你是在【工艺类型】选择了【复杂款】，之后选择了复杂款工艺的【袖头】，然后又切换回了【基础款】，这样貌似能用低价买高价衬衫，但这显然是不对的，我们不允许。";
						return false;
					}
				}

				{
					String LZX_03 = getReqParamString("LZX_03");
					if ("".equals(LZX_03)) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "您当前选择的【门襟】是无效的，我猜你是在【工艺类型】选择了【复杂款】，之后选择了复杂款工艺的【门襟】，然后又切换回了【基础款】，这样貌似能用低价买高价衬衫，但这显然是不对的，我们不允许。";
						return false;
					}
				}

				{
					String LZX_04 = getReqParamString("LZX_04");
					if ("".equals(LZX_04)) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "您当前选择的【口袋】是无效的，我猜你是在【工艺类型】选择了【复杂款】，之后选择了复杂款工艺的【口袋】，然后又切换回了【基础款】，这样貌似能用低价买高价衬衫，但这显然是不对的，我们不允许。";
						return false;
					}
				}

				{
					String LZX_120 = getReqParamString("LZX_120");
					if ("".equals(LZX_120)) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "您当前选择的【袖褶】是无效的，我猜你是在【工艺类型】选择了【复杂款】，之后选择了复杂款工艺的【袖褶】，然后又切换回了【基础款】，这样貌似能用低价买高价衬衫，但这显然是不对的，我们不允许。";
						return false;
					}
				}

				{
					String LZX_17 = getReqParamString("LZX_17");
					if ("".equals(LZX_17)) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "您当前选择的【后片款式】是无效的，我猜你是在【工艺类型】选择了【复杂款】，之后选择了复杂款工艺的【后片款式】，然后又切换回了【基础款】，这样貌似能用低价买高价衬衫，但这显然是不对的，我们不允许。";
						return false;
					}
				}

				{
					String LZX_06 = getReqParamString("LZX_06");
					if ("".equals(LZX_06)) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "您当前选择的【下摆弧度】是无效的，我猜你是在【工艺类型】选择了【复杂款】，之后选择了复杂款工艺的【下摆弧度】，然后又切换回了【基础款】，这样貌似能用低价买高价衬衫，但这显然是不对的，我们不允许。";
						return false;
					}
				}

				{
					String LZX_26 = getReqParamString("LZX_26");
					if ("".equals(LZX_26)) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "您当前选择的【侧缝底摆贴布】是无效的，我猜你是在【工艺类型】选择了【复杂款】，之后选择了复杂款工艺的【侧缝底摆贴布】，然后又切换回了【基础款】，这样貌似能用低价买高价衬衫，但这显然是不对的，我们不允许。";
						return false;
					}
				}

				{
					String mingxian = getReqParamString("mingxian");
					if ("".equals(mingxian)) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "您当前选择的【翻领袖头明线】是无效的，我猜你是在【工艺类型】选择了【复杂款】，之后选择了复杂款工艺的【翻领袖头明线】，然后又切换回了【基础款】，这样貌似能用低价买高价衬衫，但这显然是不对的，我们不允许。";
						return false;
					}
				}

				{
					String cefeng = getReqParamString("cefeng");
					if ("".equals(cefeng)) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "您当前选择的【侧缝工艺】是无效的，我猜你是在【工艺类型】选择了【复杂款】，之后选择了复杂款工艺的【侧缝工艺】，然后又切换回了【基础款】，这样貌似能用低价买高价衬衫，但这显然是不对的，我们不允许。";
						return false;
					}
				}
			}
		}

		return true;

	}

}
