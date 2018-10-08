package com.ltyx.sca.action.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCheckTechClash extends ZCBaseActionSupportPlugin {

	public MoudleCheckTechClash(HttpServletRequest req) {
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

		return true;

	}

}
