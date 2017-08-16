package com.ltyx.tailor.action;

import com.ltyx.tailor.actionplugin.MoudleCalCSV;
import com.ltyx.tailor.actionplugin.MoudleCheckMeasure;
import com.ltyx.tailor.actionplugin.MoudleCheckOther;
import com.ltyx.tailor.actionplugin.MoudleSubmitEC;
import com.zc.web.base.doman.ZCActionSupport;
import com.zc.web.base.service.CCReqManager;
import com.zc.web.base.service.Log;

public class SubmitTailorFormAction extends ZCActionSupport {

	/**
	 * ��Action���ڻ�ȡ��д��
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String submit() {
		Log.Nano.tag("�ύ������Ϣ����", "��ʼ");

		init(true);

		CCReqManager.showParams(request);

		doJobs();

		writeResp("�ύ������Ϣ����");

		Log.Nano.tag("�ύ������Ϣ����", "����");

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
		Log.Nano.tag("�����ύ��Ϣ����", "MoudleCheckOther", "�����");

		{
			MoudleCheckMeasure checkMeasure = new MoudleCheckMeasure(request);
			if (!checkMeasure.doJobs()) {
				ERRCODE = checkMeasure.getERRCODE();
				ERRDESC = checkMeasure.getERRDESC();
				data = checkMeasure.getData();
				return false;
			}
		}
		Log.Nano.tag("�����ύ��Ϣ����", "MoudleCheckMeasure", "�����");

		{
			MoudleSubmitEC submitEC = new MoudleSubmitEC(request);
			if (!submitEC.doJobs()) {
				ERRCODE = submitEC.getERRCODE();
				ERRDESC = submitEC.getERRDESC();
				data = submitEC.getData();
				return false;
			}
		}
		Log.Nano.tag("�����ύ��Ϣ����", "MoudleSubmitEC", "�����");

//		{
//			MoudleSubmitDB submitDB = new MoudleSubmitDB(request);
//			if (!submitDB.doJobs()) {
//				ERRCODE = submitDB.getERRCODE();
//				ERRDESC = submitDB.getERRDESC();
//				data = submitDB.getData();
//				return false;
//			}
//		}
//		Log.Nano.tag("�����ύ��Ϣ����", "MoudleSubmitDB", "�����");

		{
			MoudleCalCSV calCSV = new MoudleCalCSV(request);
			if (calCSV.doJobs()) {
				ERRCODE = calCSV.getERRCODE();
				ERRDESC = calCSV.getERRDESC();
				data = calCSV.getData();
				return true;
			}
		}
		Log.Nano.tag("�����ύ��Ϣ����", "MoudleCalCSV", "�����");

		return true;
	}

}
