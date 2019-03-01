package com.ltyx.sca.action;

import com.ltyx.sca.moudle.MoudleCSDCheckUserInfo;
import com.ltyx.sca.moudle.MoudleCSUGetPriceSample;
import com.ltyx.sca.moudle.MoudleCSUOrderSample;
import com.zc.support.doman.CCActionSupport;

public class CustomShopUserSampleAction extends CCActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		AuthorizeAssistan.check(session, response, "20", "21");

		session = SCAPageConfigCommon.manageMenu(session);
		session = SCAPageConfigCommon.manageTechnologyMan(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "定制店  工艺部件 报价";

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
		String methodName = "定制店  工艺部件 提交购物车";

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
			MoudleCSUGetPriceSample moudle = new MoudleCSUGetPriceSample(request);
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
			MoudleCSDCheckUserInfo moudle = new MoudleCSDCheckUserInfo(request);
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

		{// 提交EC
			MoudleCSUOrderSample moudle = new MoudleCSUOrderSample(request);
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
