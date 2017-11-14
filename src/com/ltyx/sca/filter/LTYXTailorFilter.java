package com.ltyx.sca.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zc.support.doman.ZCBaseHttpFilter;
import com.zc.support.service.Log;
import com.zc.support.service.SystemInitHelper;

public class LTYXTailorFilter extends ZCBaseHttpFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

		SystemInitHelper.init();
		Log.Nano.tag("优纤下单工具SCA", this.getClass(), "初始化");

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		super.doFilter(servletRequest, servletResponse, filterChain);

		String isOnline = "" + session.getAttribute("isOnline");
		String ec_user_id = "" + session.getAttribute("ec_user_id");

		if ("null".equals(isOnline) || "0".equals(isOnline) || "".equals(isOnline) || "null".equals(ec_user_id) || "".equals(ec_user_id)) {
			response.sendRedirect("/CtrlCenter/LTYX/SCA/LoginPage.action");
			Log.Nano.TagByLine("优纤下单工具SCA", "登录验证 过滤", "isOnline:" + isOnline, "ec_user_id:" + ec_user_id, "未登录 已重定向");
		} else {
			filterChain.doFilter((HttpServletRequest) request, (HttpServletResponse) response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Log.Nano.tag("优纤下单工具SCA", this.getClass(), "销毁");
	}
}
