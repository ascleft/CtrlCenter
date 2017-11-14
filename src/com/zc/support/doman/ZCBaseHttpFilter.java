package com.zc.support.doman;

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

import com.zc.support.link.ZCReqParamGetter;
import com.zc.support.service.Log;

public class ZCBaseHttpFilter implements Filter, ZCImplReqParamGetter {

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

	@Override
	public int getReqParamInt(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getReqParamInts(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getReqParamDouble(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getReqParamDoubles(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReqParamString(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReqParamString(String key, String symbol) {
		String return_value = ZCReqParamGetter.getParamStringWithSymbol(request, key, symbol, true);
		return return_value;
	}

	@Override
	public String[] getReqParamStrings(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReqParamIntWithLog(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getReqParamIntsWithLog(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getReqParamDoubleWithLog(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getReqParamDoublesWithLog(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReqParamStringWithLog(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getReqParamStringsWithLog(String key) {
		// TODO Auto-generated method stub
		return null;
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
