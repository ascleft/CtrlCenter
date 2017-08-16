package com.zc.web.base.doman;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.Connection;
import com.zc.web.base.service.DBConfigHelper;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.ReqParamGatter;

public abstract class ZCActionPluginBase {

	// ������Ӧ��������
	protected String ERRCODE = null;
	protected String ERRDESC = null;
	protected String data = null;

	// ��������
	protected HttpServletRequest req = null;

	// ������������ʵ�ֲ����ҵ���߼�
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

	// �ܱ������������ڻ�ȡ�����д��ڵļ�ֵ��
	protected int getInt(String key) {
		return ReqParamGatter.getParamInt(req, key);
	}

	// �ܱ������������ڻ�ȡ�����д��ڵļ�ֵ��
	protected double getDouble(String key) {
		return ReqParamGatter.getParamDouble(req, key);
	}

	// �ܱ������������ڻ�ȡ�����д��ڵļ�ֵ��
	protected String getString(String key) {
		return ReqParamGatter.getParamString(req, key);
	}

	// �ܱ������������ڻ�ȡ�����д��ڵļ�ֵ��
	protected String[] getStrings(String key) {
		return ReqParamGatter.getParamStrings(req, key);
	}

	// ���ݿ�����
	protected Connection DBconn = null;

	// �������ݿ�����
	protected void creatDBConn() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = DBConfigHelper.URL;
			String username = DBConfigHelper.NAME;
			String password = DBConfigHelper.PWD;

			DBconn = (Connection) DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			Log.i("�Ҳ������������� ����������ʧ�ܣ�");
			e.printStackTrace();
		} catch (SQLException se) {
			Log.i("���ݿ�����ʧ�ܣ�");
			se.printStackTrace();
		}
	}

}
