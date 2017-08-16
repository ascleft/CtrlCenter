package com.zc.web.base.doman;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zc.web.base.service.DebugManager;
import com.zc.web.base.service.Log;

public class MainHttpFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

		Log.init(true, Log.STYLE_OPEN);

		DebugManager.init();

		Log.i(getClass() + " init Filter finish !");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		// String conString = request.getContentType();
		// int conTag = conString.indexOf("form-data");
		// int conTag2 = conString.indexOf("x-www-form-urlencoded");
		// System.out.println("conTag---->" + conTag);
		// System.out.println("conTag2---->" + conTag2);

		Log.i("filter start work");
		Log.i("filter----" + request.getContentType());
		Log.i("filter finish work");

		chain.doFilter((HttpServletRequest) request, (HttpServletResponse) response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

		Log.i("filter destroy");

	}

}
