package com.ltyx.open.erp600.submit;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

public class SubmitAction extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {
		return "succ";
	}

	public String submit() {

		init(true);

		String methodName = "ERP600面料订单提交";

		ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.ERP600_SUBMIT_NSRC);

		if (doSubmit()) {
			writeResp(methodName, TextLogHelper.Type.ERP600_SUBMIT_SUCC);
		} else {
			writeResp(methodName, TextLogHelper.Type.ERP600_SUBMIT_FAIL);
		}

		return null;

	}

	public boolean doSubmit() {

		{
			SubmitActionMoudle moudle = new SubmitActionMoudle(request);
			if (!moudle.doJobs()) {
				addProgressFail("提交ERP600面料订单");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("提交ERP600面料订单");
			ERRCODE = moudle.getERRCODE();
			ERRDESC = moudle.getERRDESC();
			data = moudle.getData();
		}

		return true;

	}

}
