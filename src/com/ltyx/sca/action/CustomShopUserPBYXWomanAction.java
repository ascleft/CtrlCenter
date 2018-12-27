package com.ltyx.sca.action;

import com.ltyx.sca.moudle.MoudleCSCheckSummaryClothes;
import com.ltyx.sca.moudle.MoudleCSCheckUserInfo;
import com.ltyx.sca.moudle.MoudleCSUGetPriceWomanPBYX;
import com.ltyx.sca.moudle.MoudleCSUOrderWomanPBYX;
import com.ltyx.sca.moudle.MoudleCheckMeasureWoman;
import com.ltyx.sca.moudle.MoudleCheckPrice;
import com.ltyx.sca.moudle.MoudleCheckTechClash;
import com.ltyx.sca.moudle.MoudleCheckTechLZX11;
import com.ltyx.sca.moudle.MoudleCheckTechLZX120;
import com.ltyx.sca.moudle.MoudleCheckTechLZXNecessary;
import com.ltyx.sca.moudle.MoudleCheckTechYXST;
import com.zc.support.doman.CCActionSupport;

public class CustomShopUserPBYXWomanAction extends CCActionSupport {

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
		session = SCAPageConfigCommon.manageTechnologyWoman(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "定制店 优纤女装 报价";

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
		String methodName = "定制店 优纤女装 提交购物车";

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
			MoudleCSUGetPriceWomanPBYX moudle = new MoudleCSUGetPriceWomanPBYX(request);
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
			MoudleCSCheckUserInfo moudle = new MoudleCSCheckUserInfo(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 订单摘要信息
			MoudleCSCheckSummaryClothes moudle = new MoudleCSCheckSummaryClothes(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 尺寸校验
			MoudleCheckMeasureWoman moudle = new MoudleCheckMeasureWoman(request);
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
			MoudleCSUOrderWomanPBYX moudle = new MoudleCSUOrderWomanPBYX(request);
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
