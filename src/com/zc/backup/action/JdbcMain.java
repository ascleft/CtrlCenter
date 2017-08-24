package com.zc.backup.action;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.zc.support.service.Log;

public class JdbcMain {

	public static Connection conn = null;

	public static void init() {

		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/ctrlcenter";
			String username = "root";
			String password = "123456";

			conn = (Connection) DriverManager.getConnection(url, username, password);

			Log.i("jdbc init finish");

			test();
		} catch (ClassNotFoundException e) {
			Log.i("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		} catch (SQLException se) {
			Log.i("数据库连接失败！");
			se.printStackTrace();
		}
	}

	public static void test() {

		// String sql = "insert into user (name) values('jaskon')";
		// PreparedStatement pstmt = (PreparedStatement)
		// conn.prepareStatement(sql);
		// pstmt.execute(sql);

		// String sql = "select * from User where name=? and pwd=?";
		// try {
		// PreparedStatement pstmt = (PreparedStatement)
		// conn.prepareStatement(sql);
		// pstmt.setString(1, "Joel");
		// pstmt.setString(2, "123");
		// ResultSet rs = (ResultSet) pstmt.executeQuery();
		// if (rs.next()) {
		// Log.i(rs.getString("name"), rs.getString("pwd"),
		// rs.getString("permission"), rs.getString("time_last"),
		// rs.getString("time_creat"));
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } finally {
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }

		String sql = "select * from User ";
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			Log.Pro.start();
			while (rs.next()) {
				Log.Pro.whiteLine(rs.getString("name") + " " + rs.getString("pwd") + " " + rs.getString("permission") + " " + rs.getString("time_last") + " "
						+ rs.getString("time_creat"));
			}
			Log.Pro.finish();

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

}
