package com.ltyx.open.ybr;

import com.ltyx.sca.actionplugin.MoudleCSCheckSummaryClothes;
import com.ltyx.sca.actionplugin.MoudleCSCheckUserInfo;
import com.ltyx.sca.actionplugin.MoudleCheckMeasure;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;

public class YBR_OBO_Action extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	double price = 0;

	public String submit() {

		init(true);

		String methodName = "衣帮人 对外接口 提交";

		ZCReqIntroGetter.showParams(methodName, request);

		price = 0;

		if (doGetPrice()) {
			doSubmit();
		}

		writeResp(methodName);

		return null;

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

	public boolean doSubmit() {

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
			MoudleYBR_OBO_SubmitEC moudle = new MoudleYBR_OBO_SubmitEC(request);
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

}
