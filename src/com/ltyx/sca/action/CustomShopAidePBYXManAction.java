package com.ltyx.sca.action;

import com.ltyx.sca.moudle.MoudleCSACheckSummaryClothes;
import com.ltyx.sca.moudle.MoudleCSACheckUserInfo;
import com.ltyx.sca.moudle.MoudleCSAGetPriceManPBYX;
import com.ltyx.sca.moudle.MoudleCSAOrderManPBYX;
import com.ltyx.sca.moudle.MoudleCheckMeasureMan;
import com.ltyx.sca.moudle.MoudleCheckPrice;
import com.ltyx.sca.moudle.MoudleCheckTechClash;
import com.ltyx.sca.moudle.MoudleCheckTechLZX01;
import com.ltyx.sca.moudle.MoudleCheckTechLZX11;
import com.ltyx.sca.moudle.MoudleCheckTechLZX120;
import com.ltyx.sca.moudle.MoudleCheckTechLZXNecessary;
import com.ltyx.sca.moudle.MoudleCheckTechYXST;
import com.zc.support.doman.CCActionSupport;

public class CustomShopAidePBYXManAction extends CCActionSupport {

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
			MoudleCSAGetPriceManPBYX moudle = new MoudleCSAGetPriceManPBYX(request);
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
			MoudleCSACheckUserInfo moudle = new MoudleCSACheckUserInfo(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 订单摘要信息
			MoudleCSACheckSummaryClothes moudle = new MoudleCSACheckSummaryClothes(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 尺寸校验
			MoudleCheckMeasureMan moudle = new MoudleCheckMeasureMan(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 面料及特殊工艺校验
			MoudleCheckTechYXST moudle = new MoudleCheckTechYXST(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 领型领插片冲突校验
			MoudleCheckTechLZX01 moudle = new MoudleCheckTechLZX01(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 袖褶冲突校验
			MoudleCheckTechLZX120 moudle = new MoudleCheckTechLZX120(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 刺绣校验
			MoudleCheckTechLZX11 moudle = new MoudleCheckTechLZX11(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 必要工艺信息校验
			MoudleCheckTechLZXNecessary moudle = new MoudleCheckTechLZXNecessary(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 冲突工艺校验
			MoudleCheckTechClash moudle = new MoudleCheckTechClash(request);
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
			MoudleCSAOrderManPBYX moudle = new MoudleCSAOrderManPBYX(request);
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
