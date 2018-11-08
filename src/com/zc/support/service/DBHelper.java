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

	private synchronized static Connection createConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ctrlcenter" + "?useUnicode=true&characterEncoding=utf8";
			String username = "root";
			String password = ConfigHelperDB.PWD;
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			Log.i("找不到驱动程序类 ，加载驱动失败！");
			conn = null;
			e.printStackTrace();
		} catch (SQLException se) {
			Log.i("数据库连接失败！");
			se.printStackTrace();
			conn = null;
		}
		return conn;
	}

	public static selectAction select(String... names) {
		selectAction bean = new selectAction(names);
		bean.colums = names;
		return bean;
	}

	public static insertAction insert(String... names) {
		insertAction bean = new insertAction(names);
		return bean;

	}

	public static class insertAction {

		private String table = null;
		private String[] values = null;
		private String[] columns = null;

		private insertAction(String[] values) {
			values(values);
		}

		private insertAction values(String[] values) {
			this.values = values;
			for (int i = 0; i < values.length; i++) {
				this.values[i] = this.values[i].replace("'", "\\'");
				this.values[i] = this.values[i].replace("\"", "\\\"");
			}
			return this;
		}

		public insertAction columns(String... columns) {
			this.columns = columns;
			return this;
		}

		public insertAction into(String table) {
			this.table = table;
			return this;
		}

		public String exe() {
			String id = exeSQL(prepSQL());
			return id;
		}

		private String prepSQL() {
			String sql_full = "";
			String sql_table = "";
			String sql_columns = "";
			String sql_values = "";
			if (values.length == 0) {
				return "值总数不得为0";
			}
			if ((values.length != columns.length) && (columns.length != 0)) {
				return "值列总数不一致";
			}
			if (table == null || (table.length() == 0)) {
				return "表名未定义";
			}
			{// 定义表
				sql_table = table;
			}
			{// 定义列
				if (columns.length > 0) {
					sql_columns += " (";
					for (int i = 0; i < columns.length; i++) {
						sql_columns += '`';
						sql_columns += columns[i];
						sql_columns += '`';
						if (i < columns.length - 1) {
							sql_columns += ", ";
						}
					}
					sql_columns += ")";
				}
			}
			{// 定义值
				sql_values += " (";
				for (int i = 0; i < values.length; i++) {
					sql_values += '"';
					sql_values += values[i];
					sql_values += '"';
					if (i < values.length - 1) {
						sql_values += ", ";
					}
				}
				sql_values += ")";
			}
			sql_full = "INSERT INTO" + " " + sql_table + sql_columns + " VALUES" + sql_values;
			return sql_full;
		}

		private String exeSQL(String sql) {
			String id = "";
			Connection conn = createConn();
			try {
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
				System.out.println(sql);
				pstmt.execute();
				ResultSet rs = (ResultSet) pstmt.getGeneratedKeys();
				while (rs.next()) {
					id = rs.getString(1);
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
			return id;
		}

	}

	public static class selectAction {

		Connection conn = createConn();

		String table = null;
		String[] colums = null;
		String[] descriptions = null;
		String condition = null;

		private selectAction(String[] colums) {
			colums(colums);
		}

		private selectAction colums(String[] colums) {
			this.colums = colums;
			return this;
		}

		public selectAction from(String table) {
			this.table = table;
			return this;
		}

		public selectAction where(String... descriptions) {
			this.descriptions = descriptions;
			return this;
		}

		public selectAction condition(String condition) {
			this.condition = condition;
			return this;
		}

		public ArrayList<SelectBean> exe() {
			ArrayList<SelectBean> list = exeSQL(prepSQL());
			return list;
		}

		private String prepSQL() {
			String sql_full = "";
			String sql_table = "";
			String sql_descriptions = "";
			String sql_condition = "";
			if (condition != null && condition.length() > 0) {
				sql_condition = this.condition;
				if (colums == null || colums.length == 0) {
					return "列未定义";
				}
				sql_full = sql_condition;
			} else {
				if (table == null || (table.length() == 0)) {
					return "表名未定义";
				}
				if (colums == null || colums.length == 0) {
					return "列未定义";
				}
				{// 定义表
					sql_table = " " + table;
				}
				{// 定义条件
					if (descriptions != null && descriptions.length > 0) {
						sql_descriptions = " WHERE";
						for (int i = 0; i < descriptions.length; i++) {
							sql_descriptions += " ";
							sql_descriptions += descriptions[i];
							if (i < descriptions.length - 1) {
								sql_descriptions += " AND";
							}
						}
					}
				}
				sql_full = "SELECT * FROM" + sql_table + sql_descriptions;
			}
			return sql_full;
		}

		private ArrayList<SelectBean> exeSQL(String sql) {
			ArrayList<SelectBean> list = new ArrayList<SelectBean>();
			Connection conn = createConn();
			try {
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs = (ResultSet) pstmt.executeQuery();
				while (rs.next()) {
					SelectBean bean = new SelectBean();
					for (int i = 0; i < colums.length; i++) {
						bean.add(colums[i], rs.getString(colums[i]));
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
