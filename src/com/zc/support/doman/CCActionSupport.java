package com.zc.support.doman;

import java.util.ArrayList;

import com.ltyx.sca.log.ActionLogBeanBase;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.LogSyncSafe;
import com.zc.support.service.StringHelper;
import com.zc.support.service.TimeHelper;

import net.sf.json.JSONObject;

public class CCActionSupport extends ZCBaseActionSupport {

	private static final long serialVersionUID = 10086L;

	private JSONObject result;

	public String ERRCODE = null;// 响应码
	public String ERRDESC = null;// 响应描述
	public String data = null;// 主数据存放区
	public String TIP1 = null;// 贴士1
	public String TIP2 = null;// 贴士2
	public String TimeMgr = null;// 时间管理者

	public TimeHelper.Timer timer = null;

	public ActionLogBeanBase dbLog = null;

	/**
	 * 初始化ActionSupport，同时提供跨域支持（CORS）
	 * 
	 */
	@Override
	public void init(boolean allowCORS) {
		super.init(allowCORS);

		timer = new TimeHelper.Timer();
		initProgress();

	}

	private ArrayList<String> progressLog = null;

	private void initProgress() {
		progressLog = new ArrayList<String>();
	}

	public void addProgress(String log) {
		progressLog.add(log);
	}

	@Deprecated
	public void addProgressSucc(String log) {
		log = StringHelper.fillRightMIX(log, 18, "-");
		progressLog.add(log + "--->" + "成功");
	}

	@Deprecated
	public void addProgressFail(String log) {
		log = StringHelper.fillRightMIX(log, 18, "-");
		progressLog.add(log + "--->" + "失败");
	}

	public boolean runMoudle(ZCBaseActionSupportPlugin moudle) {
		boolean isSucc = moudle.doJobs();
		String moudleName = StringHelper.fillRightMIX(moudle.name, 15, "-");
		if (isSucc) {
			progressLog.add(moudleName + "-->" + "成功");
			ERRCODE = moudle.getERRCODE();
			ERRDESC = moudle.getERRDESC();
			data = moudle.getData();
		} else {
			progressLog.add(moudleName + "-->" + "失败");
			ERRCODE = moudle.getERRCODE();
			ERRDESC = moudle.getERRDESC();
			data = moudle.getData();
		}
		return isSucc;
	}

	public void logProgress(String title, String[][] logType) {
		if (progressLog.size() > 0) {
			{// 文本日志
				LogSyncSafe.Pro log = new LogSyncSafe.Pro();
				log.addStart(true);
				log.addMsgLine(title);
				log.addCut();
				for (String logNow : progressLog) {
					log.addMsgLine(logNow);
				}
				log.addfinish();
				log.flush(logType);
			}
			{// 数据库日志
				for (String logNow : progressLog) {
					if (dbLog != null) {
						dbLog.main.addTags(logNow);
					}
				}
			}
		}
	}

	public void logProgress() {
		if (progressLog.size() > 0) {
			{// 数据库日志
				for (String logNow : progressLog) {
					if (dbLog != null) {
						dbLog.main.addTags(logNow);
					}
				}
			}
		}
	}

	public void logActionResponse(String title, String[][] logType) {
		LogSyncSafe.Pro log = new LogSyncSafe.Pro();
		log.addStart(true);
		log.addMsgLine(title);
		log.addCut();
		log.addMsgLine("结束");
		log.addCut();
		log.addMsgLine(result.toString());
		log.addCut();
		log.addMsgLines(timer.getTimerPartableList());
		log.addfinish();
		log.flush(logType);
	}

	public void writeResp(String tab, String[][] logType) {
		logProgress(tab, logType);
		writeResp();
		logActionResponse(tab, logType);
	}

	public void writeResp() {
		logProgress();

		ERRCODE = (ERRCODE != null) ? ERRCODE : "1";
		ERRDESC = (ERRDESC != null) ? ERRDESC : "响应描述未初始化";
		data = (data != null) ? data : "主数据未添加";
		TIP1 = (TIP1 != null) ? TIP1 : "";
		TIP2 = (TIP2 != null) ? TIP2 : "";
		if (TimeMgr == null) {
			JSONObject TimeListJson = new JSONObject();
			ArrayList<String> timeList = timer.getTimerPartableList();
			for (int i = 0; i < timeList.size(); i++) {
				TimeListJson.put(i, timeList.get(i));
			}
			TimeMgr = TimeListJson.toString();
		}

		result = new JSONObject();
		result.put("ERRCODE", ERRCODE);
		result.put("ERRDESC", ERRDESC);
		result.put("data", data);
		result.put("TIP1", TIP1);
		result.put("TIP2", TIP2);
		result.put("TimeMgr", TimeMgr);

		writeRaw(result.toString());

		{
			makeDBLog();
		}

	}

	/**
	 * 向链接中写入返回数据
	 * 
	 * 支持打印log
	 * 
	 */
	public void writeResp(String raw, String tab, String[][] logType) {
		logProgress(tab, logType);
		writeResp();
		logActionResponse(tab, logType);
	}

	public void initDBLog(String name, String categary) {
		String ECid = (String) session.getAttribute("ec_user_id");
		if (ECid == null) {
			ECid = getReqParamString("operator_id");
		}
		dbLog = new ActionLogBeanBase(name, categary, ECid);
		addDBLogSrcReq();
		addDBLogKeys();
	}

	public void addDBLogTags(String... tags) {
		if (dbLog != null) {
			for (int i = 0; i < tags.length; i++) {
				dbLog.ec.addKeys(tags[i]);
			}
		}
	}

	private void addDBLogSrcReq() {
		if (dbLog != null) {
			dbLog.main.addSrcReq(ZCReqIntroGetter.getParams(request));
		}
	}

	private void addDBLogKeys() {
		if (dbLog != null) {
			dbLog.main.addKeys(getReqParamStrings("uskin_code"));
			dbLog.main.addKeys(getReqParamStrings("customer_name"));
			dbLog.main.addKeys(getReqParamStrings("LZX_11_CHAR_WORD"));
		}
	}

	public void makeDBLog() {
		if (dbLog != null) {
			dbLog.main.addTimer(timer);
			dbLog.main.addSrcResp(result.toString());
			dbLog.doLog();
		}
	}

}
