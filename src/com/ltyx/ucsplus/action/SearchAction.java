package com.ltyx.ucsplus.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zc.web.base.doman.ZCActionSupport;
import com.zc.web.base.doman.ZCHttpParam;
import com.zc.web.base.service.CCHttpReq;
import com.zc.web.base.service.Log;

public class SearchAction extends ZCActionSupport {

	/**
	 * ��¼Action
	 * 
	 * session�е�userΪ�û���Ϣ��role��0Ϊ���Ȩ��
	 * 
	 * session�е�isOnline��1Ϊ�Ѿ���¼��0Ϊδ��¼��
	 */
	private static final long serialVersionUID = 10086L;

	/**
	 * ��ȡ����ҳ��
	 * 
	 */
	public String getPage() {
		return "succ";
	}

	/**
	 * ����
	 * 
	 */
	public String search() {

		init(true);

		String usertoken = "UtailorForK3Cloud201612";
		String businessObjectTypeId = "STK_Inventory";
		String filter = "filter";

		String name = request.getParameter("name");
		String storeroom = request.getParameter("storeroom");
		String rank = request.getParameter("rank");

		if (null == storeroom || "".equals(storeroom)) {

			Log.Nano.TagByLine("��ʱ���ӿ� from Web", "name:", name, "storeroom:", storeroom);

			Log.i(storeroom);

			ERRCODE = "1";
			ERRDESC = "fail";
			data = "����������ô��Ϸ�ӿ�";

			writeResp("��ʱ���ӿ� resp to Web");

			return null;

		} else {

			if (null == name || "".equals(name) || name.replaceAll(" ", "").length() == 0) {

				ERRCODE = "0";
				ERRDESC = "fail";
				data = "���������ϱ��";

				writeResp("��ʱ���ӿ� resp to Web");

				return null;

			}

			name = name.replaceAll(" ", "").toUpperCase();

			if (null == rank || "".equals(rank) || Integer.parseInt(rank) > 30) {

				if (name.length() > 15) {

					ERRCODE = "0";
					ERRDESC = "fail";
					data = "��ǰ�����Ȩ��ѯ������";

					writeResp("��ʱ���ӿ� resp to Web");

					return null;
				}
			}

			if ("0".equals(storeroom)) {
				// Log.Nano.tag("��ʱ���ӿ� from K3", "³̩�ֿ�");
				filter = "FMATERIALNUMBER LIKE '%" + name + "%' OR FMATERIALNAME LIKE '%" + name + "%' OR FUSKIN LIKE '%" + name + "%'";
			} else if ("1".equals(storeroom)) {
				// Log.Nano.tag("��ʱ���ӿ� from K3", "��������");
				filter = "(FMATERIALNUMBER LIKE '%" + name + "%' OR FMATERIALNAME LIKE '%" + name + "%' OR FUSKIN LIKE '%" + name + "%') AND FSTOCKNUMBER='MLCK028'";
			} else {
				// Log.Nano.tag("��ʱ���ӿ� from K3", "�͹����");
				filter = "(FMATERIALNUMBER LIKE '%" + name + "%' OR FMATERIALNAME LIKE '%" + name + "%' OR FUSKIN LIKE '%" + name + "%') AND FSTOCKNUMBER='MLCK040'";
			}

			ZCHttpParam param = new ZCHttpParam();

			param.addParam("usertoken", usertoken);
			param.addParam("businessObjectTypeId", businessObjectTypeId);
			param.addParam("filter", filter);

			String httpResp = CCHttpReq.sendGet("http://www.lttcerp.com:88/k3cloud/Services/LUTAIWebService.asmx/K3CloudSendDataToUtailor", param);

			httpResp = httpResp.substring(103, httpResp.length() - 9);

			JSONObject jsonHttpResp = JSONObject.fromObject(httpResp);
			String jsonERRCODE = jsonHttpResp.getString("Successful");
			String jsonERRDESC = jsonHttpResp.getString("Message");

			if ("true".equals(jsonERRCODE) && "null".equals(jsonERRDESC)) {

				JSONArray jsonData = (JSONArray) jsonHttpResp.get("Data");

				if (jsonData.toString().length() < 3) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "�ֿ���û�и����Ͽ��";
				} else {
					ERRCODE = "0";
					ERRDESC = "succ";
					data = jsonData.toString();
				}

			} else {

				ERRCODE = "1";
				ERRDESC = "fail";
				data = "K3�������쳣 ��K3�����룺" + jsonERRCODE + " ��K3��������" + jsonERRDESC;

			}

			writeResp();

			return null;
		}

	}
}
