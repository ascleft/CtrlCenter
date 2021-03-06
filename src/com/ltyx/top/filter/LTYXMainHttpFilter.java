package com.ltyx.top.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zc.support.doman.ZCBaseHttpFilter;
import com.zc.support.service.Log;
import com.zc.support.service.VersionHelper;

public class LTYXMainHttpFilter extends ZCBaseHttpFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		super.doFilter(servletRequest, servletResponse, filterChain);

		session.setAttribute("SystemCore", VersionHelper.getCORE());
		session.setAttribute("SystemName", VersionHelper.getNAME());
		session.setAttribute("SystemVersion", VersionHelper.getVERSION());

		filterChain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

		Log.i(getClass() + " filter destroy");

	}

}
