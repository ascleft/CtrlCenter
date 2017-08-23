package com.ltyx.tailor.action;

import com.ltyx.tailor.actionplugin.MoudleCalCSV;
import com.ltyx.tailor.actionplugin.MoudleCheckMeasure;
import com.ltyx.tailor.actionplugin.MoudleCheckOther;
import com.ltyx.tailor.actionplugin.MoudleSubmitEC;
import com.zc.web.base.doman.ZCBaseActionSupport;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.ZCReqManager;

public class SubmitTailorFormAction extends ZCBaseActionSupport {

	/**
	 * 该Action用于获取填写表单
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String submit() {

		Log.Nano.tag("提交衬衫信息订单", "开始");

		init(true);

		ZCReqManager.showParams("提交衬衫信息订单", request);

		doJobs();

		writeResp("提交衬衫信息订单");

		Log.Nano.tag("提交衬衫信息订单", "结束");

		return null;

	}

	public boolean doJobs() {

		{
			MoudleCheckOther checkOther = new MoudleCheckOther(request);
			if (!checkOther.doJobs()) {
				ERRCODE = checkOther.getERRCODE();
				ERRDESC = checkOther.getERRDESC();
				data = checkOther.getData();
				return false;
			}
		}
		Log.Nano.tag("衬衫提交信息进度", "MoudleCheckOther", "已完成");

		{
			MoudleCheckMeasure checkMeasure = new MoudleCheckMeasure(request);
			if (!checkMeasure.doJobs()) {
				ERRCODE = checkMeasure.getERRCODE();
				ERRDESC = checkMeasure.getERRDESC();
				data = checkMeasure.getData();
				return false;
			}
		}
		Log.Nano.tag("衬衫提交信息进度", "MoudleCheckMeasure", "已完成");

		{
			MoudleSubmitEC submitEC = new MoudleSubmitEC(request);
			if (!submitEC.doJobs()) {
				ERRCODE = submitEC.getERRCODE();
				ERRDESC = submitEC.getERRDESC();
				data = submitEC.getData();
				return false;
			}
		}
		Log.Nano.tag("衬衫提交信息进度", "MoudleSubmitEC", "已完成");

		// {
		// MoudleSubmitDB submitDB = new MoudleSubmitDB(request);
		// if (!submitDB.doJobs()) {
		// ERRCODE = submitDB.getERRCODE();
		// ERRDESC = submitDB.getERRDESC();
		// data = submitDB.getData();
		// return false;
		// }
		// }
		// Log.Nano.tag("衬衫提交信息进度", "MoudleSubmitDB", "已完成");

		{
			MoudleCalCSV calCSV = new MoudleCalCSV(request);
			if (calCSV.doJobs()) {
				ERRCODE = calCSV.getERRCODE();
				ERRDESC = calCSV.getERRDESC();
				data = calCSV.getData();
				return true;
			}
		}
		Log.Nano.tag("衬衫提交信息进度", "MoudleCalCSV", "已完成");

		return true;
	}

}
