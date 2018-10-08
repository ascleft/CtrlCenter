package com.ltyx.sca.action;

import com.ltyx.sca.action.plugin.MoudleCSACheckSummaryClothes;
import com.ltyx.sca.action.plugin.MoudleCSACheckUserInfo;
import com.ltyx.sca.action.plugin.MoudleCSAGetPriceManPBYX;
import com.ltyx.sca.action.plugin.MoudleCSAOrderManPBYX;
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

public class CustomShopAidePBYXManAction extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		if (!"3071".equals(session.getAttribute("ec_user_id").toString()) && !"129".equals(session.getAttribute("ec_user_id").toString())) {
			AuthorizeAssistan.check(session.getAttribute("ec_user_rank").toString(), response, "0");
		}

		session = SCAPageConfigCommon.manageMenu(session);
		session = SCAPageConfigCommon.manageTechnologyMan(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "客户经理 优纤面料 男装 报价";

		doGetPrice();

		if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.USKIN_AIDE_PRICE_SUCC);
			writeResp(methodName, TextLogHelper.Type.USKIN_AIDE_PRICE_SUCC);
		} else {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.USKIN_AIDE_PRICE_FAIL);
			writeResp(methodName, TextLogHelper.Type.USKIN_AIDE_PRICE_FAIL);
		}

		return null;

	}

	public String submit() {

		init(true);
		String methodName = "客户经理 优纤面料 男装 提交购物车";

		doSubmit();

		if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.USKIN_AIDE_ORDER_SUCC);
			writeResp(methodName, TextLogHelper.Type.USKIN_AIDE_ORDER_SUCC);
		} else {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.USKIN_AIDE_ORDER_FAIL);
			writeResp(methodName, TextLogHelper.Type.USKIN_AIDE_ORDER_FAIL);
		}

		return null;

	}

	public boolean doGetPrice() {

		MoudleCSAGetPriceManPBYX moudle = new MoudleCSAGetPriceManPBYX(request);
		moudle.doJobs();
		ERRCODE = moudle.getERRCODE();
		ERRDESC = moudle.getERRDESC();
		data = moudle.getData();
		return true;

	}

	public boolean doSubmit() {

		{
			MoudleCSACheckUserInfo moudle = new MoudleCSACheckUserInfo(request);
			if (!moudle.doJobs()) {
				addProgressFail("用户信息检测");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("用户信息检测");
		}

		{
			MoudleCSACheckSummaryClothes moudle = new MoudleCSACheckSummaryClothes(request);
			if (!moudle.doJobs()) {
				addProgressFail("订单摘要信息");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("订单摘要信息");
		}

		{
			MoudleCheckMeasure moudle = new MoudleCheckMeasure(request);
			if (!moudle.doJobs()) {
				addProgressFail("尺寸校验");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("尺寸校验");
		}

		{
			MoudleCheckTechYXST moudle = new MoudleCheckTechYXST(request);
			if (!moudle.doJobs()) {
				addProgressFail("面料及特殊工艺校验");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("面料及特殊工艺校验");
		}

		{
			MoudleCheckTechLZX01 moudle = new MoudleCheckTechLZX01(request);
			if (!moudle.doJobs()) {
				addProgressFail("领型领插片冲突校验");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("领型领插片冲突校验");
		}

		{
			MoudleCheckTechLZX120 moudle = new MoudleCheckTechLZX120(request);
			if (!moudle.doJobs()) {
				addProgressFail("袖褶冲突校验");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("袖褶冲突校验");
		}

		{
			MoudleCheckTechLZX11 moudle = new MoudleCheckTechLZX11(request);
			if (!moudle.doJobs()) {
				addProgressFail("刺绣校验");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("刺绣校验");
		}

		{
			MoudleCheckTechLZXNecessary moudle = new MoudleCheckTechLZXNecessary(request);
			if (!moudle.doJobs()) {
				addProgressFail("必要工艺信息校验");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("必要工艺信息校验");
		}

		{
			MoudleCheckTechClash moudle = new MoudleCheckTechClash(request);
			if (!moudle.doJobs()) {
				addProgressFail("冲突工艺校验");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("冲突工艺校验");
		}

		{
			MoudleCheckPrice moudle = new MoudleCheckPrice(request);
			if (!moudle.doJobs()) {
				addProgressFail("报价核对");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("报价核对");
		}

		{
			MoudleCSAOrderManPBYX moudle = new MoudleCSAOrderManPBYX(request);
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
