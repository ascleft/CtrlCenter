package com.ltyx.sca.action;

import com.ltyx.sca.actionplugin.MoudleCSACheckSummaryClothes;
import com.ltyx.sca.actionplugin.MoudleCSACheckUserInfo;
import com.ltyx.sca.actionplugin.MoudleCSAGetPricePBYX;
import com.ltyx.sca.actionplugin.MoudleCSASubmitECPBYX;
import com.ltyx.sca.actionplugin.MoudleCheckMeasure;
import com.ltyx.sca.actionplugin.MoudleCheckPrice;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;

public class CustomShopAidePBYXAction extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		session = SCAPageConfigCommon.manageMenu(session);
		session = SCAPageConfigCommon.manageTechnology(session);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "客户经理 衬衫 优纤面料 报价";

		ZCReqIntroGetter.showParams(methodName, request);

		doGetPrice();
		writeResp(methodName);

		return null;

	}

	public String submit() {

		init(true);
		String methodName = "客户经理 衬衫 优纤面料 提交购物车";

		ZCReqIntroGetter.showParams(methodName, request);

		doSubmit();
		writeResp(methodName);

		return null;

	}

	public boolean doGetPrice() {

		MoudleCSAGetPricePBYX moudle = new MoudleCSAGetPricePBYX(request);
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
			MoudleCSASubmitECPBYX moudle = new MoudleCSASubmitECPBYX(request);
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
