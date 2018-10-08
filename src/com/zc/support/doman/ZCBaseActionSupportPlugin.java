package com.zc.support.doman;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.Connection;
import com.zc.support.config.ConfigHelperDB;
import com.zc.support.link.ZCReqParamGetter;
import com.zc.support.service.Log;

public abstract class ZCBaseActionSupportPlugin implements ZCImplReqParamGetter {

	// 返回响应基本内容
	protected String ERRCODE = null;// 响应码
	protected String ERRDESC = null;// 响应描述
	protected String data = null;// 主数据存放区
	protected String TIP1 = null;// 贴士1
	protected String TIP2 = null;// 贴士2
	protected String TimeMgr = null;// 时间管理者

	// 网络连接
	protected HttpServletRequest request = null;

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

	public String getTIP1() {
		return TIP1;
	}

	public String getTIP2() {
		return TIP2;
	}

	// 数据库连接
	protected Connection DBconn = null;

	// 创建数据库连接
	protected void creatDBConn() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = ConfigHelperDB.URL;
			String username = ConfigHelperDB.NAME;
			String password = ConfigHelperDB.PWD;

			DBconn = (Connection) DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			Log.i("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		} catch (SQLException se) {
			Log.i("数据库连接失败！");
			se.printStackTrace();
		}
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
		String return_value = ZCReqParamGetter.getParamStringWithSymbol(request, key, symbol, false);
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
