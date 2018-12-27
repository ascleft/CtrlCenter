package com.ltyx.open.prod.erp600.action;

import com.ltyx.open.prod.erp600.moudle.OrderStateActionMoudle;
import com.zc.support.doman.CCActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

public class OrderStateAction extends CCActionSupport {

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

		ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.ERP600_ORDER_STATE);

		doSubmit();

		writeResp(methodName, TextLogHelper.Type.ERP600_ORDER_STATE);

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
