package com.ltyx.sca.action;

import com.ltyx.sca.moudle.MoudleAideCheckSubcontract;
import com.ltyx.sca.moudle.MoudleAideCheckUserInfo;
import com.ltyx.sca.moudle.MoudleAideGetPriceSubcontract;
import com.ltyx.sca.moudle.MoudleAideSubmitECSubcontract;
import com.ltyx.sca.moudle.MoudleCheckPrice;
import com.zc.support.doman.CCActionSupport;

public class CustomShopAidePBYXSubcontractAction extends CCActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		AuthorizeAssistan.check(session, response, "0");

		session = SCAPageConfigCommon.manageMenu(session);
		session = SCAPageConfigCommon.manageTechnologyMan(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "客户经理 优纤男装 报价";

		{
			initDBLog(methodName, "2010");

			boolean isSucc = doGetPrice();

			if (isSucc) {
				addDBLogTags("成功");
			} else {
				addDBLogTags("失败");
			}

		}

		writeResp();

		return null;

	}

	public String check() {

		init(true);

		{
			boolean isSucc = doCheck();
			if (isSucc) {
			} else {
			}
		}

		writeResp();

		return null;

	}

	public String submit() {

		init(true);
		String methodName = "客户经理 优纤男装 提交购物车";

		{
			initDBLog(methodName, "2010");

			boolean isSucc = doSubmit();

			if (isSucc) {
				addDBLogTags("成功");
			} else {
				addDBLogTags("失败");
			}
		}

		writeResp();

		return null;

	}

	public boolean doGetPrice() {

		{// 报价
			MoudleAideGetPriceSubcontract moudle = new MoudleAideGetPriceSubcontract(request);
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

	public boolean doCheck() {

		{// 用户信息检测
			MoudleAideCheckUserInfo moudle = new MoudleAideCheckUserInfo(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 订单摘要信息
			MoudleAideCheckSubcontract moudle = new MoudleAideCheckSubcontract(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "校验成功";
			return true;
		}
	}

	public boolean doSubmit() {

		boolean checkSucc = doCheck();

		if (checkSucc == false) {
			return false;
		}

		{// 报价核对
			MoudleCheckPrice moudle = new MoudleCheckPrice(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 提交EC
			MoudleAideSubmitECSubcontract moudle = new MoudleAideSubmitECSubcontract(request);
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