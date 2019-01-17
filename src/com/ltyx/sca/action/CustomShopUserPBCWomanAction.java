package com.ltyx.sca.action;

import com.ltyx.sca.moudle.MoudleCSDCheckSummaryClothes;
import com.ltyx.sca.moudle.MoudleCSDCheckUserInfo;
import com.ltyx.sca.moudle.MoudleCSUGetPriceWomanPBC;
import com.ltyx.sca.moudle.MoudleCSUOrderWomanPBC;
import com.ltyx.sca.moudle.MoudleCheckMeasureWoman;
import com.ltyx.sca.moudle.MoudleCheckPrice;
import com.ltyx.sca.moudle.MoudleCheckTechClash;
import com.ltyx.sca.moudle.MoudleCheckTechLZX11;
import com.ltyx.sca.moudle.MoudleCheckTechLZX120;
import com.ltyx.sca.moudle.MoudleCheckTechLZXNecessary;
import com.ltyx.sca.moudle.MoudleCheckTechYXST2PBC;
import com.zc.support.doman.CCActionSupport;

public class CustomShopUserPBCWomanAction extends CCActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		AuthorizeAssistan.check(session, response, "20", "21");

		session = SCAPageConfigCommon.manageMenu(session);
		session = SCAPageConfigCommon.manageTechnologyWoman(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		String code = getReqParamString("code");
		session.setAttribute("code", code);

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "定制店 客供女装 报价";

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
		String methodName = "定制店 客供女装 提交购物车";

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
			MoudleCSUGetPriceWomanPBC moudle = new MoudleCSUGetPriceWomanPBC(request);
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

		{// 订单摘要信息
			MoudleCSDCheckSummaryClothes moudle = new MoudleCSDCheckSummaryClothes(request);
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

		{// 面料及特殊工艺校验(客供)
			MoudleCheckTechYXST2PBC moudle = new MoudleCheckTechYXST2PBC(request);
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
			MoudleCSUOrderWomanPBC moudle = new MoudleCSUOrderWomanPBC(request);
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
