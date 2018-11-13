package com.ltyx.sca.action.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCheckTechLZX120 extends ZCBaseActionSupportPlugin {

	public MoudleCheckTechLZX120(HttpServletRequest req) {
		this.name = "校验 袖褶冲突";
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{

			// YX-00-01 短袖，YX-00-02 长袖
			String tailor_type = getReqParamString("tailor_type");
			// 袖褶
			String LZX_120 = getReqParamString("LZX_120");

			// YX-00-01 短袖
			if ("YX-00-01".equals(tailor_type)) {
				if (!(LZX_120.equals("LZX-120-00")||LZX_120.equals("YXL-120-00"))) {// 袖口无褶
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前选择的是【短袖】，请在【袖褶】中选择【袖口无褶】";
					return false;
				}
			}

		}

		return true;

	}

}
