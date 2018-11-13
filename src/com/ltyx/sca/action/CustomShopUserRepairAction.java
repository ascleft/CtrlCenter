package com.ltyx.sca.action;

import com.ltyx.sca.action.plugin.MoudleCSUGetOrder;
import com.ltyx.sca.action.plugin.MoudleCSUOrderRepair;
import com.zc.support.doman.CCActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

public class CustomShopUserRepairAction extends CCActionSupport {

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

		{
			initDBLog(methodName, "2010");

			boolean isSucc = doGetPrice();

			if (isSucc) {
				addDBLogTags("成功");
			} else {
				addDBLogTags("失败");
			}
		}

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
		String methodName = "定制店  返修订单 提交购物车";

		{
			initDBLog(methodName, "2010");

			boolean isSucc = doSubmit();

			if (isSucc) {
				addDBLogTags("成功");
			} else {
				addDBLogTags("失败");
			}
		}

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

		{// 报价
			MoudleCSUGetOrder moudle = new MoudleCSUGetOrder(request);
			moudle.prepDBLog(dbLog);
			boolean isSucc = runMoudle(moudle);
			dbLog = moudle.syncDBLog();
			if (isSucc == false) {
				return false;
			}
		}

		{
			return true;
		}

	}

	public boolean doSubmit() {

		{// 提交EC
			MoudleCSUOrderRepair moudle = new MoudleCSUOrderRepair(request);
			moudle.prepDBLog(dbLog);
			boolean isSucc = runMoudle(moudle);
			dbLog = moudle.syncDBLog();
			if (isSucc == false) {
				return false;
			}
		}

		{
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "提交成功";
			return true;
		}

	}

}
