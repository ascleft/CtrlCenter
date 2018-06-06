package com.zc.support.doman;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zc.support.link.ZCReqParamGetter;
import com.zc.support.service.LogSyncSafe;
import com.zc.support.service.StringHelper;
import com.zc.support.service.TimeHelper;

import net.sf.json.JSONObject;

public class ZCBaseActionSupport extends ActionSupport implements ZCImplReqParamGetter {

	private static final long serialVersionUID = 10086L;

	public HttpServletResponse response;
	public HttpServletRequest request;

	public HttpSession session;

	private JSONObject result;
	public String ERRCODE;
	public String ERRDESC;
	public String data;

	public TimeHelper.Timer timer = null;

	/**
	 * 初始化ActionSupport，同时提供跨域支持（CORS）
	 * 
	 */

	public void init(boolean allowCORS) {

		initProgress();

		timer = new TimeHelper.Timer();

		response = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		if (allowCORS) {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST,GET");
			response.setContentType("text/html;charset=utf-8");
		}

		session = request.getSession();
		// session.setMaxInactiveInterval(6 * 60 * 60);

	}

	private ArrayList<String> progressLog = null;

	public void initProgress() {
		progressLog = new ArrayList<String>();
	}

	public void addProgress(String log) {
		progressLog.add(log);
	}

	public void addProgressSucc(String log) {
		log = StringHelper.fillRight(log, 18, "-");
		progressLog.add(log + "--->" + "成功");
	}

	public void addProgressFail(String log) {
		log = StringHelper.fillRight(log, 18, "-");
		progressLog.add(log + "--->" + "失败");
	}

	public void logProgress(String title, String logType) {
		if (progressLog.size() > 0) {
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
	}

	public void logActionResponse(String title, String logType) {
		// System.out.println(TimeHelper.getTimeHMSS());
		// Log.Pro.start();
		// Log.Pro.whiteLine(title);
		// Log.Pro.whiteCut();
		// Log.Pro.whiteLine("结束");
		// Log.Pro.whiteCut();
		// Log.Pro.whiteLine(result.toString());
		// Log.Pro.whiteCut();
		// timer.showTimerPartable(title);
		// Log.Pro.finish();

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

	/**
	 * 向链接中写入返回数据
	 * 
	 * 支持打印log
	 * 
	 */
	public void writeResp(String tab, String logType) {
		logProgress(tab, logType);
		writeResp();
		logActionResponse(tab, logType);
	}

	/**
	 * 向链接中写入返回数据
	 * 
	 */
	public void writeResp() {
		result = new JSONObject();
		result.put("ERRCODE", ERRCODE);
		result.put("ERRDESC", ERRDESC);
		result.put("data", data);
		try {
			PrintWriter out;
			out = response.getWriter();
			out.print(result.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 向链接中写入返回数据
	 * 
	 * 支持打印log
	 * 
	 */
	public void writeResp(String raw, String tab, String logType) {
		logProgress(tab, logType);
		writeResp();
		logActionResponse(tab, logType);
	}

	/**
	 * 向链接中写入返回数据
	 * 
	 */
	public void writeRaw(String raw) {
		try {
			PrintWriter out;
			out = response.getWriter();
			out.print(raw);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeRaw(String raw, String tab, String logType) {
		logProgress(tab, logType);
		writeRaw(raw);
		logActionResponse(tab, logType);
	}

	@Override
	public int getReqParamInt(String key) {
		int return_value = ZCReqParamGetter.getParamInt(request, key, false);
		return return_value;
	}

	@Override
	public int[] getReqParamInts(String key) {
		int[] return_value = ZCReqParamGetter.getParamInts(request, key, false);
		return return_value;
	}

	@Override
	public double getReqParamDouble(String key) {
		double return_value = ZCReqParamGetter.getParamDouble(request, key, false);
		return return_value;
	}

	@Override
	public double[] getReqParamDoubles(String key) {
		double[] return_value = ZCReqParamGetter.getParamDoubles(request, key, false);
		return return_value;
	}

	@Override
	public String getReqParamString(String key) {
		String return_value = ZCReqParamGetter.getParamString(request, key, false);
		return return_value;
	}

	@Override
	public String getReqParamString(String key, String symbol) {
		String return_value = ZCReqParamGetter.getParamStringWithSymbol(request, key, symbol, true);
		return return_value;
	}

	@Override
	public String[] getReqParamStrings(String key) {
		String[] return_value = ZCReqParamGetter.getParamStrings(request, key, false);
		return return_value;
	}

	@Override
	public int getReqParamIntWithLog(String key) {
		int return_value = ZCReqParamGetter.getParamInt(request, key, true);
		return return_value;
	}

	@Override
	public int[] getReqParamIntsWithLog(String key) {
		int[] return_value = ZCReqParamGetter.getParamInts(request, key, true);
		return return_value;
	}

	@Override
	public double getReqParamDoubleWithLog(String key) {
		double return_value = ZCReqParamGetter.getParamDouble(request, key, true);
		return return_value;
	}

	@Override
	public double[] getReqParamDoublesWithLog(String key) {
		double[] return_value = ZCReqParamGetter.getParamDoubles(request, key, true);
		return return_value;
	}

	@Override
	public String getReqParamStringWithLog(String key) {
		String return_value = ZCReqParamGetter.getParamString(request, key, true);
		return return_value;
	}

	@Override
	public String[] getReqParamStringsWithLog(String key) {
		String[] return_value = ZCReqParamGetter.getParamStrings(request, key, true);
		return return_value;
	}

}
