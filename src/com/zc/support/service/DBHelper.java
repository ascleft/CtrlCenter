package com.zc.support.service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.zc.support.config.ConfigHelperDB;

public class DBHelper {

	private static Connection conn = null;

	private static void createConn() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/ctrlcenter";
			String username = "root";
			String password = ConfigHelperDB.PWD;

			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			Log.i("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		} catch (SQLException se) {
			Log.i("数据库连接失败！");
			se.printStackTrace();
		}

	}

	public static ArrayList<SelectBean> exeSelect(String sql, String[] names) {
		ArrayList<SelectBean> list = new ArrayList<SelectBean>();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				SelectBean bean = new SelectBean();
				for (int i = 0; i < names.length; i++) {
					bean.add(names[i], rs.getString(names[i]));
				}
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static SQLSelect select(String... names) {
		SQLSelect bean = new SQLSelect(names);
		bean.names = names;
		return bean;
	}

	public static class SQLSelect {
		String sqlString = "";
		String sqlTable = "";
		String sqlDescription = "";
		String[] names = null;

		private SQLSelect(String[] names) {
			this.sqlString = "";
			this.sqlTable = "";
			this.sqlDescription = "";
			this.names = names;
		}

		public SQLSelect from(String table) {
			this.sqlTable = table;
			return this;
		}

		public SQLSelect where(String... description) {

			this.sqlDescription = " where ";

			for (int i = 0; i < description.length; i++) {
				this.sqlDescription += description[i];
				if (i < description.length - 1) {
					this.sqlDescription += " and ";
				}
			}

			return this;
		}

		public ArrayList<SelectBean> exe() {
			createConn();
			sqlString = "select * from " + sqlTable + sqlDescription;
			return exeSelect(sqlString, names);
		}

	}

	public static class SelectBean {

		public HashMap<String, String> resultSet = null;

		public SelectBean() {
			this.resultSet = new HashMap<String, String>();
		}

		public void add(String key, String value) {
			resultSet.put(key, value);
		}

		public String get(String key) {
			return resultSet.get(key);
		}

		public String toString() {
			String info = "";
			for (String key : resultSet.keySet()) {
				info += key + ":" + resultSet.get(key) + " ";
			}
			return info;
		}

	}

}
