package com.ltyx.tailor.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zc.web.support.doman.ZCBaseHttpFilter;
import com.zc.web.support.service.Log;
import com.zc.web.support.service.SystemInitHelper;

public class LTYXTailorFilter extends ZCBaseHttpFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

		SystemInitHelper.init();
		Log.Nano.tag("鲁泰优纤衬衫订单信息收集系统", "com.ltyx.tailor.filter", "LTYXTailorFilter", "初始化完成");

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		super.doFilter(servletRequest, servletResponse, filterChain);

		String isOnline = "" + session.getAttribute("isOnline");
		String ec_user_id = "" + session.getAttribute("ec_user_id");

		if ("null".equals(isOnline) || "0".equals(isOnline) || "".equals(isOnline) || "null".equals(ec_user_id) || "".equals(ec_user_id)) {
			response.sendRedirect("/CtrlCenter/LTYX/Tailor/LoginPage.action");
			Log.Nano.TagByLine("鲁泰优纤衬衫订单信息收集系统", "登录验证 过滤", "isOnline:" + isOnline, "ec_user_id:" + ec_user_id, "未登录 已重定向");
		} else {
			filterChain.doFilter((HttpServletRequest) request, (HttpServletResponse) response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Log.Nano.tag("鲁泰优纤衬衫订单信息收集系统", "com.ltyx.tailor.filter", "LTYXTailorFilter", "销毁");
	}
}
