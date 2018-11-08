package com.ltyx.sca.action;

import com.ltyx.sca.action.plugin.MoudleCSCheckSummaryClothes;
import com.ltyx.sca.action.plugin.MoudleCSCheckUserInfo;
import com.ltyx.sca.action.plugin.MoudleCSUGetPriceManPBYX;
import com.ltyx.sca.action.plugin.MoudleCSUOrderManPBYX;
import com.ltyx.sca.action.plugin.MoudleCheckMeasure;
import com.ltyx.sca.action.plugin.MoudleCheckPrice;
import com.ltyx.sca.action.plugin.MoudleCheckTechClash;
import com.ltyx.sca.action.plugin.MoudleCheckTechLZX01;
import com.ltyx.sca.action.plugin.MoudleCheckTechLZX11;
import com.ltyx.sca.action.plugin.MoudleCheckTechLZX120;
import com.ltyx.sca.action.plugin.MoudleCheckTechLZXNecessary;
import com.ltyx.sca.action.plugin.MoudleCheckTechYXST;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

public class CustomShopUserPBYXManAction extends ZCBaseActionSupport {

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
		String methodName = "定制店 优纤面料 男装 报价";

		{
			initDBLog(methodName, "2010", session.getAttribute("ec_user_id").toString());
			dbLog.main.addSrcReq(ZCReqIntroGetter.getParams(request));
		}

		boolean isSucc = doGetPrice();
		
		{
			if (isSucc) {
				dbLog.main.addTags("成功");
			} else {
				dbLog.main.addTags("失败");
			}
			dbLog.main.addKeys(getReqParamStrings("uskin_code"));
			dbLog.main.addKeys(getReqParamStrings("customer_name"));
			dbLog.main.addKeys(getReqParamStrings("LZX_13_FOR_CHAR"));

		}
		
		{

			if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
				ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.USKIN_USER_PRICE_SUCC);
				writeResp(methodName, TextLogHelper.Type.USKIN_USER_PRICE_SUCC);
			} else {
				ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.USKIN_USER_PRICE_FAIL);
				writeResp(methodName, TextLogHelper.Type.USKIN_USER_PRICE_FAIL);
			}
		}

		return null;

	}

	public String submit() {

		init(true);
		String methodName = "定制店 优纤面料 男装 提交购物车";

		{
			initDBLog(methodName, "2010", session.getAttribute("ec_user_id").toString());
			dbLog.main.addSrcReq(ZCReqIntroGetter.getParams(request));
		}

		boolean isSucc = doSubmit();

		{
			if (isSucc) {
				dbLog.main.addTags("成功");
			} else {
				dbLog.main.addTags("失败");
			}
			dbLog.main.addKeys(getReqParamStrings("uskin_code"));
			dbLog.main.addKeys(getReqParamStrings("customer_name"));
			dbLog.main.addKeys(getReqParamStrings("LZX_13_FOR_CHAR"));
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
			MoudleCSUGetPriceManPBYX moudle = new MoudleCSUGetPriceManPBYX(request);
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
			MoudleCheckMeasure moudle = new MoudleCheckMeasure(request);
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

		{// 报价核对
			MoudleCheckPrice moudle = new MoudleCheckPrice(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{// 提交EC
			MoudleCSUOrderManPBYX moudle = new MoudleCSUOrderManPBYX(request);
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
