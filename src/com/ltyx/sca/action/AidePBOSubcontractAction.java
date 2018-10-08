package com.ltyx.sca.action;

import com.ltyx.sca.action.plugin.MoudleAideCheckSubcontract;
import com.ltyx.sca.action.plugin.MoudleAideCheckUserInfo;
import com.ltyx.sca.action.plugin.MoudleAideGetPriceSubcontract;
import com.ltyx.sca.action.plugin.MoudleAideSubmitECSubcontract;
import com.ltyx.sca.action.plugin.MoudleCheckPrice;
import com.ltyx.sca.action.plugin.MoudleCheckTechLZX11;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

public class AidePBOSubcontractAction extends ZCBaseActionSupport {

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
		String methodName = "定制顾问 其他商品 报价";

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
		String methodName = "定制顾问 其他商品 提交购物车";

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
		{
			MoudleAideGetPriceSubcontract moudle = new MoudleAideGetPriceSubcontract(request);
			if (!moudle.doJobs()) {
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			} else {
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return true;
			}
		}

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
			MoudleAideCheckSubcontract moudle = new MoudleAideCheckSubcontract(request);
			if (!moudle.doJobs()) {
				addProgressFail("委外商品信息检测");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("委外商品信息检测");
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
			MoudleAideSubmitECSubcontract moudle = new MoudleAideSubmitECSubcontract(request);
			if (!moudle.doJobs()) {
				addProgressFail("");
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
