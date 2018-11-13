package com.ltyx.sca.action;

import com.ltyx.sca.action.plugin.MoudleAideCheckSummaryClothes;
import com.ltyx.sca.action.plugin.MoudleAideCheckUserInfo;
import com.ltyx.sca.action.plugin.MoudleAideGetPricePBYX;
import com.ltyx.sca.action.plugin.MoudleAideSubmitECPBYX;
import com.ltyx.sca.action.plugin.MoudleCheckPrice;
import com.ltyx.sca.action.plugin.MoudleCheckTechLZX01;
import com.ltyx.sca.action.plugin.MoudleCheckTechLZX11;
import com.zc.support.doman.CCActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

public class AidePBYXManAction extends CCActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		session = SCAPageConfigCommon.manageMenu(session);
		session = SCAPageConfigCommon.manageTechnologyMan(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "定制顾问 男装 报价";

		doGetPrice();

		if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.UNDEFINED);
			writeResp(methodName, TextLogHelper.Type.UNDEFINED);
		} else {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.UNDEFINED);
			writeResp(methodName, TextLogHelper.Type.UNDEFINED);
		}

		return null;

	}

	public String submit() {

		init(true);
		String methodName = "定制顾问 男装 提交购物车";

		doSubmit();

		if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.UNDEFINED);
			writeResp(methodName, TextLogHelper.Type.UNDEFINED);
		} else {
			ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.UNDEFINED);
			writeResp(methodName, TextLogHelper.Type.UNDEFINED);
		}

		return null;

	}

	public boolean doGetPrice() {

		MoudleAideGetPricePBYX moudle = new MoudleAideGetPricePBYX(request);
		moudle.doJobs();
		ERRCODE = moudle.getERRCODE();
		ERRDESC = moudle.getERRDESC();
		data = moudle.getData();
		return true;

	}

	public boolean doSubmit() {

		{
			MoudleAideCheckUserInfo moudle = new MoudleAideCheckUserInfo(request);
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
			MoudleAideCheckSummaryClothes moudle = new MoudleAideCheckSummaryClothes(request);
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
			MoudleCheckTechLZX01 moudle = new MoudleCheckTechLZX01(request);
			if (!moudle.doJobs()) {
				addProgressFail("领型插片冲突校验");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("领型插片冲突校验");
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
			MoudleAideSubmitECPBYX moudle = new MoudleAideSubmitECPBYX(request);
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
