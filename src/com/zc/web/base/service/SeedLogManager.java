package com.zc.web.base.service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class SeedLogManager {

	public static Connection conn = null;
	public static final String TABLE = "seedlog";

	public static void creatConn() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/ctrlcenter";
			String username = "root";
			String password = "123456";

			conn = (Connection) DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			Log.i("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		} catch (SQLException se) {
			Log.i("数据库连接失败！");
			se.printStackTrace();
		}
	}

	public static ArrayList<SeedLog> getAllLog() {
		String sql = "select * from " + TABLE;
		return getLogBySql(sql);
	}

	public static ArrayList<SeedLog> getLogByName(String applicationName) {
		String sql = "select * from " + TABLE + " where belong " + "= '" + applicationName + "' ";
		return getLogBySql(sql);
	}

	public static ArrayList<SeedLog> getLogByNameAndState(String applicationName, String state) {
		String sql = "select * from " + TABLE + " where belong " + "= '" + applicationName + "' " + "and state " + "= '" + state + "' ";
		return getLogBySql(sql);
	}

	public static ArrayList<SeedLog> getLogByNameAndTitle(String applicationName, String title) {
		String sql = "select * from " + TABLE + " where belong " + "= '" + applicationName + "' " + "and title " + "= '" + title + "' ";
		return getLogBySql(sql);
	}

	public static ArrayList<SeedLog> getLogByNameAndTitleAndState(String applicationName, String title, String state) {
		String sql = "select * from " + TABLE + " where belong " + "= '" + applicationName + "' " + "and title " + "= '" + title + "' " + "and state " + "= '" + state + "' ";
		return getLogBySql(sql);
	}

	public static ArrayList<SeedLog> getLogBySql(String sql) {
		ArrayList<SeedLog> slal = new ArrayList<SeedLogManager.SeedLog>();

		creatConn();
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) pstmt.executeQuery();

			while (rs.next()) {
				SeedLog log = new SeedLog();
				log.id = rs.getString("id");
				log.belong = rs.getString("belong");
				log.state = rs.getString("state");
				log.title = rs.getString("title");
				log.description = rs.getString("description");
				log.tip = rs.getString("tip");
				log.time = rs.getString("time");
				slal.add(log);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			slal = new ArrayList<SeedLogManager.SeedLog>();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return slal;
	}

	public static void add(String applicationName, String title) {
		add(applicationName, "0", title, "", "", TimeHelper.getTime());
	}

	public static void add(String applicationName, String title, String description) {
		add(applicationName, "0", title, description, "", TimeHelper.getTime());
	}

	public static void add(String applicationName, String state, String title, String description) {
		add(applicationName, state, title, description, "", TimeHelper.getTime());
	}

	public static void add(String applicationName, String state, String title, String description, String tip) {
		add(applicationName, state, title, description, tip, TimeHelper.getTime());
	}

	private static void add(String applicationName, String state, String title, String description, String tip, String time) {

		String sql = "INSERT INTO " + //
				TABLE + //
				" (belong, state, title, description, tip, time )" + //
				" VALUES" + //
				" ('" + applicationName + "', '" + state + "', '" + title + "', '" + description + "', '" + tip + "', '" + time + "')";//
		Log.Nano.tag("sql", sql);

		creatConn();

		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static class SeedLog {

		public String id;
		public String belong;
		public String state;
		public String title;
		public String description;
		public String tip;
		public String time;

		public String toString() {
			String string = "";
			string += "id:" + id + " ";
			string += "belong:" + belong + " ";
			string += "state:" + state + " ";
			string += "title:" + title + " ";
			string += "description:" + description + " ";
			string += "tip:" + tip + " ";
			string += "time:" + time + " ";
			return string;
		}

	}

}
