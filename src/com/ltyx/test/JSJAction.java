package com.ltyx.test;

import java.io.BufferedReader;
import java.io.IOException;

import com.zc.support.doman.CCActionSupport;
import com.zc.support.service.TextLogHelper;
import com.zc.support.service.TimeHelper;

public class JSJAction extends CCActionSupport {

	/**
	 * 该Action用于获取填写表单
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String Test() {

		init(true);

		try {
			BufferedReader br;
			br = request.getReader();
			String str, wholeStr = "";
			while ((str = br.readLine()) != null) {
				wholeStr += str;
			}
			TextLogHelper.white(TimeHelper.getTimeHMSS(), TextLogHelper.Type.TEST_JSJ_NSRC);
			TextLogHelper.white(wholeStr, TextLogHelper.Type.TEST_JSJ_NSRC);
			TextLogHelper.white(" ", TextLogHelper.Type.TEST_JSJ_NSRC);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TextLogHelper.white(request.toString());

		return null;

	}

}
