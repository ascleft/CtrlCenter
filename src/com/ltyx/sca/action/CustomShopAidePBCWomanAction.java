package com.ltyx.sca.action;

import com.ltyx.sca.actionplugin.MoudleCSACheckSummaryClothes;
import com.ltyx.sca.actionplugin.MoudleCSACheckUserInfo;
import com.ltyx.sca.actionplugin.MoudleCSAGetPriceWomanPBC;
import com.ltyx.sca.actionplugin.MoudleCSASubmitECWomanPBC;
import com.ltyx.sca.actionplugin.MoudleCheckMeasure;
import com.ltyx.sca.actionplugin.MoudleCheckPrice;
import com.ltyx.sca.actionplugin.MoudleCheckTechLZX11;
import com.ltyx.sca.actionplugin.MoudleCheckTechLZX120;
import com.ltyx.sca.actionplugin.MoudleCheckTechLZXNecessary;
import com.ltyx.sca.actionplugin.MoudleCheckTechYXST2PBC;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.LogType;

public class CustomShopAidePBCWomanAction extends ZCBaseActionSupport {

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
		session = SCAPageConfigCommon.manageTechnologyWoman(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		String code = getReqParamString("code");
		session.setAttribute("code", code);

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "客户经理 客供面料 女装 报价";

		doGetPrice();

		if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
			ZCReqIntroGetter.showParams(methodName, request, LogType.LTYX_USKIN_AIDE_SUCC);
			writeResp(methodName, LogType.LTYX_USKIN_AIDE_SUCC);
		} else {
			ZCReqIntroGetter.showParams(methodName, request, LogType.LTYX_USKIN_AIDE_FAIL);
			writeResp(methodName, LogType.LTYX_USKIN_AIDE_FAIL);
		}

		return null;

	}

	public String submit() {

		init(true);
		String methodName = "客户经理 客供面料 女装 提交购物车";

		doSubmit();

		if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
			ZCReqIntroGetter.showParams(methodName, request, LogType.LTYX_USKIN_AIDE_SUCC_ORDER);
			writeResp(methodName, LogType.LTYX_USKIN_AIDE_SUCC_ORDER);
		} else {
			ZCReqIntroGetter.showParams(methodName, request, LogType.LTYX_USKIN_AIDE_FAIL);
			writeResp(methodName, LogType.LTYX_USKIN_AIDE_FAIL);
		}

		return null;

	}

	public boolean doGetPrice() {

		MoudleCSAGetPriceWomanPBC moudle = new MoudleCSAGetPriceWomanPBC(request);
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
			MoudleCheckTechYXST2PBC moudle = new MoudleCheckTechYXST2PBC(request);
			if (!moudle.doJobs()) {
				addProgressFail("面料及特殊工艺校验(客供)");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("面料及特殊工艺校验(客供)");
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
			MoudleCSASubmitECWomanPBC moudle = new MoudleCSASubmitECWomanPBC(request);
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
