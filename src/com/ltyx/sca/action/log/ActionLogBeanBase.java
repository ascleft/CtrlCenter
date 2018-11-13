package com.ltyx.sca.action.log;

import java.util.ArrayList;
import java.util.Map;

import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.service.DBHelper;
import com.zc.support.service.TimeHelper;

import net.sf.json.JSONObject;

public class ActionLogBeanBase {

	public ECLog ec;
	public MainLog main;

	public ActionLogBeanBase(String name, String categary, String ECid) {
		main = new MainLog(name, categary, ECid);
		ec = new ECLog();
	}

	public void doLog() {
		main.formatLog();
		ec.formatLog();
		if (main._log_tags.size() > 0 && main._log_tags.size() > 0) {
			// 主日志
			String id = DBHelper//
					.insert(main.sql_category, main.sql_class, main.sql_method, main.sql_user, main.sql_tags, main.sql_keys, main.sql_snapshot, main.sql_date, main.sql_time)//
					.columns("category", "class", "method", "user", "tags", "keys", "snapshot", "date", "time")//
					.into("log_main")//
					.exe();
			// EC流转
			DBHelper//
					.insert(id, ec.sql_category, ec.sql_class, ec.sql_method, ec.sql_tags, ec.sql_keys, ec.sql_details, ec.sql_date, ec.sql_time)//
					.columns("fid", "category", "class", "method", "tags", "keys", "details", "date", "time")//
					.into("log_details")//
					.exe();
		}
	}

	public class MainLog {

		private String _action_category;
		private String _action_class;
		private String _action_method;
		private String _action_user;

		private ArrayList<String> _log_tags;
		private ArrayList<String> _log_keys;

		private String _src_Req;
		private String _src_Resp;

		private String _time_Begin;
		private String _time_End;
		private String _time_Total;

		private String sql_category;
		private String sql_class;
		private String sql_method;
		private String sql_user;
		private String sql_tags;
		private String sql_keys;
		private String sql_snapshot;
		private String sql_date;
		private String sql_time;

		public MainLog(String name, String categary, String ECid) {

			_action_category = categary;
			_action_class = "";
			_action_method = "";
			_action_user = DBHelper.select("id").from("log_user").where("ecid = '" + ECid + "'").exe().get(0).get("id");

			_log_tags = new ArrayList<String>();
			_log_keys = new ArrayList<String>();

			_src_Req = "";
			_src_Resp = "";

			_time_Begin = "";
			_time_End = "";
			_time_Total = "";

			addTags(name);

		}

		public void addTags(String... tag) {
			for (int i = 0; i < tag.length; i++) {
				_log_tags.add(tag[i]);
			}
		}

		public void addKeys(String... key) {
			for (int i = 0; i < key.length; i++) {
				_log_keys.add(key[i]);
			}
		}

		public void addSrcReq(Map<String, String> srcIn) {
			JSONObject jsonSrcIn = new JSONObject();
			for (String key : srcIn.keySet()) {
				jsonSrcIn.put(key, srcIn.get(key));

			}
			_src_Req = jsonSrcIn.toString();
		}

		public void addSrcResp(String src) {
			this._src_Resp = src;
		}

		@Deprecated
		public void addTimeBegin(String time) {
			_time_Begin = time;
		}

		@Deprecated
		public void addTimeEnd(String time) {
			_time_End = time;
		}

		@Deprecated
		public void addTimeTotal(String time) {
			_time_Total = time;
		}

		public void addTimer(TimeHelper.Timer timer) {
			ArrayList<String> timeList = timer.getTimerPartableList();
			if (timeList.size() >= 3) {
				_time_Begin = timeList.get(0).replace("开始时间: ", "");
				_time_End = timeList.get(timeList.size() - 2).replace("结束时间: ", "");
				_time_Total = timeList.get(timeList.size() - 1);
			}
		}

		private String getSnapshot() {
			JSONObject shot = new JSONObject();
			shot.put("src_req", _src_Req);
			shot.put("src_resp", _src_Resp);
			shot.put("time_begin", _time_Begin);
			shot.put("time_end", _time_End);
			shot.put("time_total", _time_Total);
			return shot.toString();
		}

		private void formatLog() {
			sql_category = _action_category;
			sql_class = _action_class;
			sql_method = _action_method;
			sql_user = _action_user;
			sql_tags = "";
			for (int i = 0; i < _log_tags.size(); i++) {
				sql_tags += _log_tags.get(i);
				sql_tags += (i < _log_tags.size() - 1) ? ", " : ". ";
			}
			sql_keys = "";
			for (int i = 0; i < _log_keys.size(); i++) {
				sql_keys += _log_keys.get(i);
				sql_keys += (i < _log_keys.size() - 1) ? ", " : ". ";
			}
			sql_snapshot = getSnapshot();
			sql_date = TimeHelper.getDateYMD();
			sql_time = _time_End;
		}
	}

	public class ECLog {

		private String _action_category;
		private String _action_class;
		private String _action_method;

		private String _log_tags;
		private String _log_keys;

		private String _src_Req;
		private String _src_Resp;

		private String _time_Begin;
		private String _time_End;
		private String _time_Total;

		private String sql_category;
		private String sql_class;
		private String sql_method;
		private String sql_tags;
		private String sql_keys;
		private String sql_details;
		private String sql_date;
		private String sql_time;

		public ECLog() {
			_action_category = "1041";
			_action_class = "";
			_action_method = "";

			_log_tags = "";
			_log_keys = "";

			_src_Req = "";
			_src_Resp = "";

			_time_Begin = "";
			_time_End = "";
			_time_Total = "";
		}

		public void addTags(String... tag) {
			for (int i = 0; i < tag.length; i++) {
				_log_tags += tag + ", ";
			}
		}

		public void addKeys(String... key) {
			for (int i = 0; i < key.length; i++) {
				_log_keys += key[i] + ", ";
			}
		}

		public void addSrcReq(String url, ZCHttpReqParam param) {
			_src_Req = url + "?" + param.getParam();
		}

		public void addSrcResp(String src) {
			this._src_Resp = src;
		}

		public void addTimer(TimeHelper.Timer timer) {
			timer.getTimerPartableList();
			_time_Begin = timer.getTimerPartableList().get(0).replace("开始时间: ", "");
			_time_End = timer.getTimerPartableList().get(1).replace("结束时间: ", "");
			_time_Total = timer.getTimerPartableList().get(2);
		}

		private String getSnapshot() {
			JSONObject shot = new JSONObject();
			shot.put("src_req", _src_Req);
			shot.put("src_resp", _src_Resp);
			shot.put("time_begin", _time_Begin);
			shot.put("time_end", _time_End);
			shot.put("time_total", _time_Total);
			return shot.toString();
		}

		public void formatLog() {
			sql_category = _action_category;
			sql_class = _action_class;
			sql_method = _action_method;
			sql_tags = _log_tags;
			sql_keys = _log_keys;
			sql_details = getSnapshot();
			sql_date = TimeHelper.getDateYMD();
			sql_time = _time_End;
		}
	}

}
