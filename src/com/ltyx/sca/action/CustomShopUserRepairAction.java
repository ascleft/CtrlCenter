package com.ltyx.sca.action;

import com.ltyx.sca.action.plugin.MoudleCSUGetOrder;
import com.ltyx.sca.action.plugin.MoudleCSUOrderRepair;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

public class CustomShopUserRepairAction extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		if (!"3071".equals(session.getAttribute("ec_user_id").toString()) && !"129".equals(session.getAttribute("ec_user_id").toString())) {
			AuthorizeAssistan.check(session.getAttribute("ec_user_rank").toString(), response, "20", "21");
		}

		session = SCAPageConfigCommon.manageMenu(session);
		session = SCAPageConfigCommon.manageTechnologyMan(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "定制店  返修订单 报价";

		doGetPrice();

		if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.USKIN_USER_PRICE_SUCC);
			writeResp(methodName, TextLogHelper.Type.USKIN_USER_PRICE_SUCC);
		} else {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.USKIN_USER_PRICE_FAIL);
			writeResp(methodName, TextLogHelper.Type.USKIN_USER_PRICE_FAIL);
		}

		return null;

	}

	public String submit() {

		init(true);
		String methodName = "定制店 优纤面料 男装 提交购物车";

		doSubmit();

		if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.USKIN_USER_ORDER_SUCC);
			writeResp(methodName, TextLogHelper.Type.USKIN_USER_ORDER_SUCC);
		} else {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.USKIN_USER_ORDER_FAIL);
			writeResp(methodName, TextLogHelper.Type.USKIN_USER_ORDER_FAIL);
		}

		return null;

	}

	public boolean doGetPrice() {

		{
			MoudleCSUGetOrder moudle = new MoudleCSUGetOrder(request);
			if (!moudle.doJobs()) {
				addProgressFail("返修订单合法性检测");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				TIP1 = moudle.getTIP1();
				TIP2 = moudle.getTIP2();
				return false;
			}
			addProgressSucc("返修订单合法性检测");
			ERRCODE = moudle.getERRCODE();
			ERRDESC = moudle.getERRDESC();
			data = moudle.getData();
			TIP1 = moudle.getTIP1();
			TIP2 = moudle.getTIP2();
		}

		return true;
	}

	public boolean doSubmit() {

		{
			MoudleCSUOrderRepair moudle = new MoudleCSUOrderRepair(request);
			if (!moudle.doJobs()) {
				addProgressFail("提交EC");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("提交EC");
		}

		{
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "提交成功";
			return true;
		}

	}

}
