package com.ltyx.test;

import java.io.BufferedReader;
import java.io.IOException;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.service.LogType;
import com.zc.support.service.TextLogHelper;
import com.zc.support.service.TimeHelper;

public class JSJAction extends ZCBaseActionSupport {

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
			TextLogHelper.white(TimeHelper.getTimeHMSS(), LogType.JSJ_NOTICE);
			TextLogHelper.white(wholeStr, LogType.JSJ_NOTICE);
			TextLogHelper.white(" ", LogType.JSJ_NOTICE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TextLogHelper.white(request.toString());

		return null;

	}

}
