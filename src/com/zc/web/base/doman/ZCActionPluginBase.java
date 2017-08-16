package com.zc.web.base.doman;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.Connection;
import com.zc.web.base.service.DBConfigHelper;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.ReqParamGatter;

public abstract class ZCActionPluginBase {

	// 返回响应基本内容
	protected String ERRCODE = null;
	protected String ERRDESC = null;
	protected String data = null;

	// 网络连接
	protected HttpServletRequest req = null;

	// 抽象动作，用于实现插件的业务逻辑
	abstract public boolean doJobs();

	public String getERRCODE() {
		return ERRCODE;
	}

	public String getERRDESC() {
		return ERRDESC;
	}

	public String getData() {
		return data;
	}

	// 受保护方法，用于获取连接中存在的键值对
	protected int getInt(String key) {
		return ReqParamGatter.getParamInt(req, key);
	}

	// 受保护方法，用于获取连接中存在的键值对
	protected double getDouble(String key) {
		return ReqParamGatter.getParamDouble(req, key);
	}

	// 受保护方法，用于获取连接中存在的键值对
	protected String getString(String key) {
		return ReqParamGatter.getParamString(req, key);
	}

	// 受保护方法，用于获取连接中存在的键值对
	protected String[] getStrings(String key) {
		return ReqParamGatter.getParamStrings(req, key);
	}

	// 数据库连接
	protected Connection DBconn = null;

	// 创建数据库连接
	protected void creatDBConn() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = DBConfigHelper.URL;
			String username = DBConfigHelper.NAME;
			String password = DBConfigHelper.PWD;

			DBconn = (Connection) DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			Log.i("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		} catch (SQLException se) {
			Log.i("数据库连接失败！");
			se.printStackTrace();
		}
	}

}
