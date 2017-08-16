package com.ltyx.tailor.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zc.web.base.doman.ZCHttpFilter;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.SystemInitHelper;
import com.zc.web.base.service.VersionHelper;

public class LTYXTailorFilter extends ZCHttpFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

		SystemInitHelper.init();
		Log.Nano.tag("³̩���˳���������Ϣ�ռ�ϵͳ", "com.ltyx.tailor.filter", "LTYXTailorFilter", "��ʼ�����");

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		super.doFilter(servletRequest, servletResponse, filterChain);

		String isOnline = "" + session.getAttribute("isOnline");
		String ec_user_id = "" + session.getAttribute("ec_user_id");

		if ("null".equals(isOnline) || "0".equals(isOnline) || "".equals(isOnline) || "null".equals(ec_user_id) || "".equals(ec_user_id)) {
			response.sendRedirect("/CtrlCenter/LTYX/Tailor/LoginPage.action");
			Log.Nano.TagByLine("³̩���˳���������Ϣ�ռ�ϵͳ", "��¼��֤ ����", "isOnline:" + isOnline, "ec_user_id:" + ec_user_id, "δ��¼ ���ض���");
		} else {
			filterChain.doFilter((HttpServletRequest) request, (HttpServletResponse) response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Log.Nano.tag("³̩���˳���������Ϣ�ռ�ϵͳ", "com.ltyx.tailor.filter", "LTYXTailorFilter", "����");
	}
}
