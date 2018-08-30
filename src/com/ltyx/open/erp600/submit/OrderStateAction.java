package com.ltyx.open.erp600.submit;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.LogType;

public class OrderStateAction extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {
		return "succ";
	}

	public String submit() {

		init(true);

		String methodName = "ERP600订单状态反查";

		ZCReqIntroGetter.showParams(methodName, request, LogType.ERP600_ORDER_SEARCH);

		doSubmit();

		writeResp(methodName, LogType.ERP600_ORDER_SEARCH);

		return null;

	}

	public boolean doSubmit() {

		{
			OrderStateActionMoudle moudle = new OrderStateActionMoudle(request);
			if (!moudle.doJobs()) {
				addProgressFail("ERP600订单状态反查");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("ERP600订单状态反查");
			ERRCODE = moudle.getERRCODE();
			ERRDESC = moudle.getERRDESC();
			data = moudle.getData();
		}

		return true;

	}

}
