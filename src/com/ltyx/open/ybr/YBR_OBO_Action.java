package com.ltyx.open.ybr;

import com.ltyx.sca.actionplugin.MoudleCSCheckSummaryClothes;
import com.ltyx.sca.actionplugin.MoudleCSCheckUserInfo;
import com.ltyx.sca.actionplugin.MoudleCheckMeasure;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.LogType;

public class YBR_OBO_Action extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	double price = 0;

	public String submitPBYX() {

		init(true);

		String methodName = "衣帮人 对外接口 优纤面料 提交";

		price = 0;

		if (doGetPrice()) {
			doSubmitPBYX();
		}

		if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
			ZCReqIntroGetter.showParams(methodName, request, LogType.YBR_ORDER_SUCC);
			writeResp(methodName, LogType.YBR_ORDER_SUCC);
		} else {
			ZCReqIntroGetter.showParams(methodName, request, LogType.YBR_ORDER_FAIL);
			writeResp(methodName, LogType.YBR_ORDER_FAIL);
		}

		return null;

	}

	public String submitPBC() {

		init(true);

		String methodName = "衣帮人 对外接口 客供面料 提交";

		price = 0;

		if (doGetPrice()) {
			doSubmitPBC();
		}

		if ("succ".equals(ERRDESC) && "0".equals(ERRCODE)) {
			ZCReqIntroGetter.showParams(methodName, request, LogType.YBR_ORDER_SUCC);
			writeResp(methodName, LogType.YBR_ORDER_SUCC);
		} else {
			ZCReqIntroGetter.showParams(methodName, request, LogType.YBR_ORDER_FAIL);
			writeResp(methodName, LogType.YBR_ORDER_FAIL);
		}

		return null;

	}

	public boolean doSubmitPBYX() {

		{
			MoudleCSCheckUserInfo moudle = new MoudleCSCheckUserInfo(request);
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
			MoudleCSCheckSummaryClothes moudle = new MoudleCSCheckSummaryClothes(request);
			if (!moudle.doJobs()) {
				addProgressFail("订单摘要信息");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("订单摘要信息");
		}

		// {
		// MoudleCheckPrice moudle = new MoudleCheckPrice(request);
		// if (!moudle.doJobs()) {
		// addProgressFail("报价核对");
		// ERRCODE = moudle.getERRCODE();
		// ERRDESC = moudle.getERRDESC();
		// data = moudle.getData();
		// return false;
		// }
		// addProgressSucc("报价核对");
		// }

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
			MoudleYBR_OBO_SubmitEC_PBYX moudle = new MoudleYBR_OBO_SubmitEC_PBYX(request);
			moudle.setPrice(price);
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

	public boolean doSubmitPBC() {

		{
			MoudleCSCheckUserInfo moudle = new MoudleCSCheckUserInfo(request);
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
			MoudleCSCheckSummaryClothes moudle = new MoudleCSCheckSummaryClothes(request);
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
			MoudleYBR_OBO_SubmitEC_PBC moudle = new MoudleYBR_OBO_SubmitEC_PBC(request);
			moudle.setPrice(price);
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

	public boolean doGetPrice() {

		{
			MoudleYBR_OBO_GetPrice moudle = new MoudleYBR_OBO_GetPrice(request);
			if (!moudle.doJobs()) {
				addProgressFail("计算报价");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			} else {
				price = moudle.getPrice();
			}
			addProgressSucc("计算报价");
		}

		return true;

	}

}
