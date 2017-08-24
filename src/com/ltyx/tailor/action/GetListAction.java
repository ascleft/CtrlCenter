package com.ltyx.tailor.action;

import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.zc.support.doman.ZCBaseActionSupport;

public class GetListAction extends ZCBaseActionSupport {

	/**
	 * 该Action用于获取填写表单
	 *
	 */
	private static final long serialVersionUID = 10087L;

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------

	public String getPage() {

		return "succ";

	}

	public String getPageCount() {

		init(true);

		int pageSize = 2;
		int pageCount = 0;
		int itemCount = 0;

		creatDBConn();

		try {
			String sql_get_count = "select count(*) from order4user";

			PreparedStatement pstmt = (PreparedStatement) DBconn.prepareStatement(sql_get_count);
			pstmt.execute();
			ResultSet rs = (ResultSet) pstmt.getResultSet();
			if (rs.next()) {
				itemCount = rs.getInt(1);
			}

			if (itemCount % pageSize > 0) {
				pageCount = itemCount / pageSize + 1;
			} else {
				pageCount = itemCount / pageSize;
			}

		} catch (SQLException e) {
			e.printStackTrace();

			pageCount = 1;

		} finally {
			try {
				DBconn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "" + pageCount;

		writeResp();

		return null;

	}

	public String getList() {

		init(true);

		// String page_target = getReqParamValue("page_target");
		String page_target = "1";
		page_target = "" + (Integer.parseInt(page_target) - 1);

		JSONArray list = new JSONArray();

		creatDBConn();

		try {
			String sql_get_count = "SELECT * FROM order4user LIMIT " + page_target + ", 100;";

			PreparedStatement pstmt = (PreparedStatement) DBconn.prepareStatement(sql_get_count);
			pstmt.executeQuery();
			ResultSet rs = (ResultSet) pstmt.getResultSet(); // 获取结果
			while (rs.next()) {

				JSONObject item = new JSONObject();

				item.put("customer_name", rs.getString("customer_name"));
				item.put("customer_tel", rs.getString("customer_tel"));
				item.put("customer_email", rs.getString("customer_email"));
				item.put("customer_address", rs.getString("customer_address"));
				item.put("customer_tips", rs.getString("customer_tips"));
				item.put("operator_id", rs.getString("operator_id"));
				item.put("operator_name", rs.getString("operator_name"));
				item.put("operator_time", rs.getString("operator_time"));
				item.put("uskin_code", rs.getString("uskin_code"));
				item.put("quickphoto", rs.getString("quickphoto"));

				list.add(item);

			}

			if (list.size() > 0) {

				ERRCODE = "0";
				ERRDESC = "succ";
				data = list.toString();
			} else {

				ERRCODE = "0";
				ERRDESC = "fail";
				data = "数据库无相关记录。";
			}

		} catch (SQLException e) {
			e.printStackTrace();

			ERRCODE = "1";
			ERRDESC = "fail";
			data = "数据库异常，请稍候重试。";

		} finally {
			try {
				DBconn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		writeResp();

		return null;

	}

}
