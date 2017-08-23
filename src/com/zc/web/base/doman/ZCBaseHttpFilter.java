package com.zc.web.base.doman;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.zc.web.base.service.Log;

public class ZCBaseHttpFilter implements Filter {

	public HttpServletResponse response;
	public HttpServletRequest request;

	public HttpSession session;

	public JSONObject result;
	public String ERRCODE;
	public String ERRDESC;
	public String data;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request = (HttpServletRequest) servletRequest;
		response = (HttpServletResponse) servletResponse;
		session = request.getSession();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

		Log.i("filter destroy");

	}

	public void writeResp(String tab) {
		writeResp();
		Log.Nano.tag(tab, result.toString());
	}

	public void writeResp() {
		result = new JSONObject();
		result.put("ERRCODE", ERRCODE);
		result.put("ERRDESC", ERRDESC);
		result.put("data", data);
		Log.Nano.tag("data", data);
		try {
			PrintWriter out;
			out = response.getWriter();
			out.print(result.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
