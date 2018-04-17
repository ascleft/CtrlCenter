package com.ltyx.open.unity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;

public class SearchAction extends ZCBaseActionSupport {

	/**
	 * 搜索Action
	 * 
	 * session中的user为用户信息，role中0为最高权限
	 * 
	 * session中的isOnline：1为已经登录，0为未登录。
	 */

	private static final long serialVersionUID = 10086L;

	/**
	 * 搜索
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

			Log.Nano.TagByLine("即时库存接口 from Web", "name:", name, "storeroom:", storeroom);

			Log.i(storeroom);

			ERRCODE = "1";
			ERRDESC = "fail";
			data = "请不要攻击接口";

			writeResp("即时库存接口 resp to Web");

			return null;

		} else {

			if (null == name || "".equals(name) || name.replaceAll(" ", "").length() == 0) {

				ERRCODE = "0";
				ERRDESC = "fail";
				data = "请输入面料编号";

				writeResp("即时库存接口 resp to Web");

				return null;

			}

			name = name.replaceAll(" ", "").toUpperCase();

			if (null == rank || "".equals(rank) || Integer.parseInt(rank) > 30) {

				if (name.length() > 50) {

					ERRCODE = "0";
					ERRDESC = "fail";
					data = "当前身份无权查询该面料";

					writeResp("即时库存接口 resp to Web");

					return null;
				}
			}

			if ("0".equals(storeroom)) {
				// Log.Nano.tag("即时库存接口 from K3", "鲁泰仓库");
				filter = "FMATERIALNUMBER LIKE '%" + name + "%' OR FMATERIALNAME LIKE '%" + name + "%' OR FUSKIN LIKE '%" + name + "%'";
			} else if ("1".equals(storeroom)) {
				// Log.Nano.tag("即时库存接口 from K3", "智能制造");
				filter = "(FMATERIALNUMBER LIKE '%" + name + "%' OR FMATERIALNAME LIKE '%" + name + "%' OR FUSKIN LIKE '%" + name + "%') AND FSTOCKNUMBER='MLCK028'";
			} else {
				// Log.Nano.tag("即时库存接口 from K3", "客供库存");
				filter = "(FMATERIALNUMBER LIKE '%" + name + "%' OR FMATERIALNAME LIKE '%" + name + "%' OR FUSKIN LIKE '%" + name + "%') AND FSTOCKNUMBER='MLCK040'";
			}

			ZCHttpReqParam param = new ZCHttpReqParam();

			param.addParam("usertoken", usertoken);
			param.addParam("businessObjectTypeId", businessObjectTypeId);
			param.addParam("filter", filter);

			String httpResp = ZCHttpReqSender.sendGet("http://www.lttcerp.com:88/k3cloud/Services/LUTAIWebService.asmx/K3CloudSendDataToUtailor", param);

			httpResp = httpResp.substring(103, httpResp.length() - 9);

			JSONObject jsonHttpResp = JSONObject.fromObject(httpResp);
			String jsonERRCODE = jsonHttpResp.getString("Successful");
			String jsonERRDESC = jsonHttpResp.getString("Message");

			if ("true".equals(jsonERRCODE) && "null".equals(jsonERRDESC)) {

				JSONArray jsonData = (JSONArray) jsonHttpResp.get("Data");

				if (jsonData.toString().length() < 3) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "仓库中没有该面料库存";
				} else {
					ERRCODE = "0";
					ERRDESC = "succ";
					data = jsonData.toString();
				}

			} else {

				ERRCODE = "1";
				ERRDESC = "fail";
				data = "K3服务器异常 ，K3错误码：" + jsonERRCODE + " ，K3错误描述" + jsonERRDESC;

			}

			writeResp();

			return null;
		}

	}
}
