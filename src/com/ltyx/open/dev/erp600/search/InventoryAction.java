package com.ltyx.open.dev.erp600.search;

import com.zc.support.doman.CCActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

public class InventoryAction extends CCActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {
		return "succ";
	}

	public String submit() {

		init(true);

		String methodName = "ERP600即时库存查询";

		ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.UNDEFINED);

		if (doGetInventory()) {
		}

		writeResp(methodName, TextLogHelper.Type.UNDEFINED);

		return null;

	}

	public boolean doGetInventory() {

		{
			InventoryActionMoudle moudle = new InventoryActionMoudle(request);
			if (!moudle.doJobs()) {
				addProgressFail("即时库存");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("即时库存");
			ERRCODE = moudle.getERRCODE();
			ERRDESC = moudle.getERRDESC();
			data = moudle.getData();
		}

		return true;

	}

}
