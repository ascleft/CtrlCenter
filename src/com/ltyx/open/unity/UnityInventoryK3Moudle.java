package com.ltyx.open.unity;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UnityInventoryK3Moudle extends ZCBaseActionSupportPlugin {

	public String name = null;
	public String desc = null;
	public ArrayList<UnityInventoryCell> cells = null;

	private String filter = null; // 查询语句
	private String usertoken = null;// token
	private String businessObjectTypeId = null;// 企业标识码

	public UnityInventoryK3Moudle(HttpServletRequest req) {
		this.request = req;

	}

	public void setParam(String code, String warehouse, String fuzzy) {

		name = "K3";
		desc = "正常";

		if ("Y".equals(fuzzy)) {
			if ("LT".equals(warehouse)) {
				// Log.Nano.tag("即时库存接口 from K3", "鲁泰仓库");
				filter = "FMATERIALNUMBER LIKE  '%" + code + "%' OR FMATERIALNAME LIKE '%" + code + "%' OR FUSKIN LIKE '%" + code + "%'";
			} else if ("ZN".equals(warehouse)) {
				// Log.Nano.tag("即时库存接口 from K3", "智能制造");
				filter = "(FMATERIALNUMBER LIKE '%" + code + "%' OR FMATERIALNAME LIKE '%" + code + "%' OR FUSKIN LIKE '%" + code + "%') AND FSTOCKNUMBER='MLCK028'";
			} else if ("KG".equals(warehouse)) {
				// Log.Nano.tag("即时库存接口 from K3", "客供库存");
				filter = "(FMATERIALNUMBER LIKE '%" + code + "%' OR FMATERIALNAME LIKE '%" + code + "%' OR FUSKIN LIKE '%" + code + "%') AND FSTOCKNUMBER='MLCK040'";
			} else {
				// Log.Nano.tag("即时库存接口 from K3", "鲁泰仓库");
				filter = "FMATERIALNUMBER LIKE  '%" + code + "%' OR FMATERIALNAME LIKE '%" + code + "%' OR FUSKIN LIKE '%" + code + "%'";
			}
		} else {
			if ("LT".equals(warehouse)) {
				// Log.Nano.tag("即时库存接口 from K3", "鲁泰仓库");
				filter = "FMATERIALNUMBER LIKE  '" + code + "' OR FMATERIALNAME LIKE '" + code + "' OR FUSKIN LIKE '" + code + "'";
			} else if ("ZN".equals(warehouse)) {
				// Log.Nano.tag("即时库存接口 from K3", "智能制造");
				filter = "(FMATERIALNUMBER LIKE '" + code + "' OR FMATERIALNAME LIKE '" + code + "' OR FUSKIN LIKE '" + code + "') AND FSTOCKNUMBER='MLCK028'";
			} else if ("KG".equals(warehouse)) {
				// Log.Nano.tag("即时库存接口 from K3", "客供库存");
				filter = "(FMATERIALNUMBER LIKE '" + code + "' OR FMATERIALNAME LIKE '" + code + "' OR FUSKIN LIKE '" + code + "') AND FSTOCKNUMBER='MLCK040'";
			} else {
				// Log.Nano.tag("即时库存接口 from K3", "鲁泰仓库");
				filter = "FMATERIALNUMBER LIKE  '" + code + "' OR FMATERIALNAME LIKE '" + code + "' OR FUSKIN LIKE '" + code + "'";
			}
		}

		usertoken = "UtailorForK3Cloud201612";
		businessObjectTypeId = "STK_Inventory";

	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub
		cells = new ArrayList<UnityInventoryCell>();
		if (change(getCore(getFull()))) {
			return true;
		} else {
			return false;
		}
	}

	private String getFull() {

		ZCHttpReqParam param = new ZCHttpReqParam();

		param.addParam("usertoken", usertoken);
		param.addParam("businessObjectTypeId", businessObjectTypeId);
		param.addParam("filter", filter);

		String httpResp = ZCHttpReqSender.sendGet("http://www.lttcerp.com:88/k3cloud/Services/LUTAIWebService.asmx/K3CloudSendDataToUtailor", param);

		return httpResp;
	}

	private String getCore(String in) {

		String resp = "";

		resp = in.substring(103, in.length() - 9);

		return resp;
	}

	private boolean change(String in) {

		boolean isSucc = false;

		JSONObject jsonObject = JSONObject.fromObject(in);
		JSONArray array = jsonObject.getJSONArray("Data");

		for (int i = 0; i < array.size(); i++) {

			String uskinCode_ = ((JSONObject) array.get(i)).getString("FUSKIN");
			String luthaiCode = ((JSONObject) array.get(i)).getString("FMATERIALNUMBER");
			double all_______ = ((JSONObject) array.get(i)).getDouble("FBASEQTY");
			double available_ = ((JSONObject) array.get(i)).getDouble("FBASEAVBQTY");
			double locked____ = 0;
			String department = "制衣ERP";
			String warehouse_ = ((JSONObject) array.get(i)).getString("FSTOCKNUMBER");

			UnityInventoryCell cell = new UnityInventoryCell(uskinCode_, luthaiCode, all_______, available_, locked____, department, warehouse_);

			cells.add(cell);
		}

		if (cells.size() > 0) {
			this.desc = "库存信息正常";
		} else {
			this.desc = "库存信息正常，无库存";
		}
		isSucc = true;

		return isSucc;
	}

}
