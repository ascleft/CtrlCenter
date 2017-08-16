package com.zc.talk;

import net.sf.json.JSONObject;

import com.zc.web.base.doman.ZCActionSupport;
import com.zc.web.base.doman.ZCHttpParam;
import com.zc.web.base.service.CCHttpReq;
import com.zc.web.base.service.Log;

public class TalkAction extends ZCActionSupport {

	private static final long serialVersionUID = 10086L;

	/**
	 * ��ȡҳ��
	 * 
	 */
	public String getPage() {
		return "succ";

	}

	/**
	 * ����ӿ�
	 * 
	 */
	public String talk() {

		Log.Nano.tag("Talk", "in");

		init(true);

		String tell = "" + request.getParameter("tell");

		if ("".equals(tell)) {

			Log.Nano.tag("����ӿ�  from Web", "tell:" + tell);

			ERRCODE = "1";
			ERRDESC = "fail";
			data = "����������ô��Ϸ�ӿ�";

			writeResp();

		} else {

			ZCHttpParam param = new ZCHttpParam();
			param.addParam("key", "free");
			param.addParam("appid", "0");
			param.addParam("msg", tell);

			String httpResp = CCHttpReq.sendGet("http://api.qingyunke.com/api.php", param);

			JSONObject jsonHttpResp = JSONObject.fromObject(httpResp);
			String result = jsonHttpResp.getString("result");
			String content = jsonHttpResp.getString("content");

			if ("0".equals(result)) {

				ERRCODE = "0";
				ERRDESC = "succ";
				data = content;

			} else {

				ERRCODE = "1";
				ERRDESC = "fail";
				data = "���η������쳣";

				Log.Nano.TagByLine("Talk", "�ӿ������쳣", content);

			}

			writeResp();

		}
		return null;

	}

}
