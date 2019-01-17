package com.ltyx.open.ybr;

import com.ltyx.open.ybr.moudle.MoudleYBR_OBO_Check;
import com.ltyx.open.ybr.moudle.MoudleYBR_OBO_CheckMeasure;
import com.ltyx.open.ybr.moudle.MoudleYBR_OBO_SubmitEC_PBC;
import com.ltyx.open.ybr.moudle.MoudleYBR_OBO_SubmitEC_PBYX;
import com.ltyx.open.ybr.moudle.MoudleYBR_OBO_SubmitEC_Repair;
import com.ltyx.sca.moudle.MoudleCSDCheckSummaryClothes;
import com.ltyx.sca.moudle.MoudleCSDCheckUserInfo;
import com.zc.support.doman.CCActionSupport;

public class YBR_OBO_Action extends CCActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	double price = 0;

	public String submitPBYX() {

		init(true);
		String methodName = "对外接口 衣邦人 优纤面料 提交";

		System.out.println("1");

		{
			initDBLog(methodName, "2010");

			System.out.println("1.5");

			boolean isSucc = doSubmitPBYX();

			System.out.println("8");

			if (isSucc) {
				addDBLogTags("成功");
			} else {
				addDBLogTags("失败");
			}

		}

		System.out.println("9");

		writeResp();

		System.out.println("0");

		return null;

	}

	public String submitPBC() {

		init(true);
		String methodName = "对外接口 衣邦人 客供面料 提交";

		{
			initDBLog(methodName, "2010");

			boolean isSucc = doSubmitPBC();

			if (isSucc) {
				addDBLogTags("成功");
			} else {
				addDBLogTags("失败");
			}

		}

		writeResp();

		return null;

	}

	public String repair() {

		init(true);
		String methodName = "对外接口 衣邦人 返修订单 提交";

		{
			initDBLog(methodName, "2010");

			boolean isSucc = doRepair();

			if (isSucc) {
				addDBLogTags("成功");
			} else {
				addDBLogTags("失败");
			}

		}

		writeResp();

		return null;

	}

	public boolean doSubmitPBYX() {

		{
			MoudleCSDCheckUserInfo moudle = new MoudleCSDCheckUserInfo(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{
			MoudleCSDCheckSummaryClothes moudle = new MoudleCSDCheckSummaryClothes(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{
			MoudleYBR_OBO_CheckMeasure moudle = new MoudleYBR_OBO_CheckMeasure(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{
			MoudleYBR_OBO_Check moudle = new MoudleYBR_OBO_Check(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{
			MoudleYBR_OBO_SubmitEC_PBYX moudle = new MoudleYBR_OBO_SubmitEC_PBYX(request);
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

	public boolean doSubmitPBC() {

		{
			MoudleCSDCheckUserInfo moudle = new MoudleCSDCheckUserInfo(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}
		{
			MoudleCSDCheckSummaryClothes moudle = new MoudleCSDCheckSummaryClothes(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}
		{
			MoudleYBR_OBO_CheckMeasure moudle = new MoudleYBR_OBO_CheckMeasure(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}
		{
			MoudleYBR_OBO_Check moudle = new MoudleYBR_OBO_Check(request);
			boolean isSucc = runMoudle(moudle);
			if (isSucc == false) {
				return false;
			}
		}

		{
			MoudleYBR_OBO_SubmitEC_PBC moudle = new MoudleYBR_OBO_SubmitEC_PBC(request);
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

	public boolean doRepair() {

		{
			MoudleYBR_OBO_SubmitEC_Repair moudle = new MoudleYBR_OBO_SubmitEC_Repair(request);
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
