package com.ltyx.test.sql;

import java.sql.DriverManager;
import java.sql.SQLException;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.zc.web.base.service.Log;

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

	public static void makelocationRec() {

		JPushClient jpushClient = new JPushClient("8dd54c5cfd3fd812528c8aef", "e5e6c1087f2078fc69bbc72d", 3);

		// For push, all you need do is to build PushPayload object.
		PushPayload payload = buildPushObject_all_all_alert();

		try {
			PushResult result = jpushClient.sendPush(payload);
			System.out.println("Got result - " + result);

		} catch (APIConnectionException e) {
			// Connection error, should retry later
			System.out.println("Connection error, should retry later" + e);

		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			System.out.println("Should review the error, and fix the request" + e);
			System.out.println("HTTP Status: " + e.getStatus());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Error Message: " + e.getErrorMessage());
		}
	}

	public static PushPayload buildPushObject_all_all_alert() {
		return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag("10086")).setMessage(Message.content("推送消息正文")).build();
	}

}
