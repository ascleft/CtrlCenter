package com.ltyx.open.factory.k3;

import com.zc.support.doman.CCActionSupport;

public class SampleAcceptAction extends CCActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String check() {

		init(true);

		{
			boolean isSucc = doCheck();
			if (isSucc) {
			} else {
			}
		}

		writeResp();

		return null;

	}

	public String submit() {

		init(true);
		String methodName = "样品接收单 测试";

		{
			initDBLog(methodName, "2010");

			boolean isSucc = doSubmit();

			if (isSucc) {
				addDBLogTags("成功");
			} else {
				addDBLogTags("失败");
			}
		}

		writeResp();

		return null;

	}

	public boolean doCheck() {

		// {// 校验
		// MoudleSampleAcceptCheck moudle = new
		// MoudleSampleAcceptCheck(request);
		// boolean isSucc = runMoudle(moudle);
		// if (isSucc == false) {
		// return false;
		// }
		// }

		{
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "校验成功";
			return true;
		}

	}

	public boolean doSubmit() {

		boolean checkSucc = doCheck();

		if (checkSucc == false) {
			return false;
		}

		{// 提交K3
			MoudleSampleAcceptSubmit moudle = new MoudleSampleAcceptSubmit(request);
			moudle.prepDBLog(dbLog);
			boolean isSucc = runMoudle(moudle);
			dbLog = moudle.syncDBLog();
			if (isSucc == false) {
				return false;
			}
		}

		dbLog = null;

		{
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "提交成功";
			return true;
		}

	}

}
