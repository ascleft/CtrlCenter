package com.ltyx.open.unity;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.TextLogHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UnityInventoryK3Moudle extends ZCBaseActionSupportPlugin {

	public String name = null;
	public String desc = null;
	public ArrayList<UnityInventoryCell> cells = null;

	private String filter = null; // 查询语句
	private String usertoken = null;// token
	private String businessObjectTypeId = null;// 企业标识码

	private String attribute = "";

	public UnityInventoryK3Moudle(HttpServletRequest req) {
		this.request = req;

	}

	public void setParam(String code, String warehouse, String fuzzy, String attribute) {

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

		this.attribute = attribute;

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

		String httpResp = ZCHttpReqSender.sendGet("http://www.lttcerp.com:88/k3cloud/Services/LUTAIWebService.asmx/K3CloudSendDataToUtailor", param,
				TextLogHelper.Type.UNITY_SEARCH_NSRC);

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

			if ("Y".equals(attribute)) {
				cell.addAttribute("FSTOCKNUMBER", ((JSONObject) array.get(i)).getString("FSTOCKNUMBER"));
				cell.addAttribute("FAUXPROP", ((JSONObject) array.get(i)).getString("FAUXPROP"));
				cell.addAttribute("FAVBQTY", ((JSONObject) array.get(i)).getString("FAVBQTY"));
				cell.addAttribute("FBASEAVBQTY", ((JSONObject) array.get(i)).getString("FBASEAVBQTY"));
				cell.addAttribute("FBASEQTY", ((JSONObject) array.get(i)).getString("FBASEQTY"));
				cell.addAttribute("FBASEUNIT", ((JSONObject) array.get(i)).getString("FBASEUNIT"));
				cell.addAttribute("FCATEGORYNAME", ((JSONObject) array.get(i)).getString("FCATEGORYNAME"));
				cell.addAttribute("FCREATEDATE", ((JSONObject) array.get(i)).getString("FCREATEDATE"));
				cell.addAttribute("FCUTFABRICFLOWER", ((JSONObject) array.get(i)).getString("FCUTFABRICFLOWER"));
				cell.addAttribute("FENDPRDSPECIFICATE", ((JSONObject) array.get(i)).getString("FENDPRDSPECIFICATE"));
				cell.addAttribute("FFABRIC", ((JSONObject) array.get(i)).getString("FFABRIC"));
				cell.addAttribute("FFABRICORG", ((JSONObject) array.get(i)).getString("FFABRICORG"));
				cell.addAttribute("FFABRICSALLOGISTICNO", ((JSONObject) array.get(i)).getString("FFABRICSALLOGISTICNO"));
				cell.addAttribute("FFABRICSERIES", ((JSONObject) array.get(i)).getString("FFABRICSERIES"));
				cell.addAttribute("FFABRICSPECIFICATE_LT", ((JSONObject) array.get(i)).getString("FFABRICSPECIFICATE_LT"));
				cell.addAttribute("FFABRICTYPE", ((JSONObject) array.get(i)).getString("FFABRICTYPE"));
				cell.addAttribute("FFINISH", ((JSONObject) array.get(i)).getString("FFINISH"));
				cell.addAttribute("FFINISHENG", ((JSONObject) array.get(i)).getString("FFINISHENG"));
				cell.addAttribute("FFLOWERSIZE", ((JSONObject) array.get(i)).getString("FFLOWERSIZE"));
				cell.addAttribute("FLOTNUMBER", ((JSONObject) array.get(i)).getString("FLOTNUMBER"));
				cell.addAttribute("FMATERIALNAME", ((JSONObject) array.get(i)).getString("FMATERIALNAME"));
				cell.addAttribute("FMATERIALNUMBER", ((JSONObject) array.get(i)).getString("FMATERIALNUMBER"));
				cell.addAttribute("FMIXRATE", ((JSONObject) array.get(i)).getString("FMIXRATE"));
				cell.addAttribute("FMIXRATE_ENG", ((JSONObject) array.get(i)).getString("FMIXRATE_ENG"));
				cell.addAttribute("FMODIFYDATE", ((JSONObject) array.get(i)).getString("FMODIFYDATE"));
				cell.addAttribute("FMTONO", ((JSONObject) array.get(i)).getString("FMTONO"));
				cell.addAttribute("FORDERFABRICSPECIFICATE", ((JSONObject) array.get(i)).getString("FORDERFABRICSPECIFICATE"));
				cell.addAttribute("FOWNER", ((JSONObject) array.get(i)).getString("FOWNER"));
				cell.addAttribute("FOWNERTYPE", ((JSONObject) array.get(i)).getString("FOWNERTYPE"));
				cell.addAttribute("FPATTERN", ((JSONObject) array.get(i)).getString("FPATTERN"));
				cell.addAttribute("FPRDSPECIFICATE", ((JSONObject) array.get(i)).getString("FPRDSPECIFICATE"));
				cell.addAttribute("FPRODUCTWIDTH", ((JSONObject) array.get(i)).getString("FPRODUCTWIDTH"));
				cell.addAttribute("FQTY", ((JSONObject) array.get(i)).getString("FQTY"));
				cell.addAttribute("FREMARK", ((JSONObject) array.get(i)).getString("FREMARK"));
				cell.addAttribute("FSEWFABRICFLOWER", ((JSONObject) array.get(i)).getString("FSEWFABRICFLOWER"));
				cell.addAttribute("FSOURCEFROM", ((JSONObject) array.get(i)).getString("FSOURCEFROM"));
				cell.addAttribute("FSPECIFICATION", ((JSONObject) array.get(i)).getString("FSPECIFICATION"));
				cell.addAttribute("FSTOCKLOC", ((JSONObject) array.get(i)).getString("FSTOCKLOC"));
				cell.addAttribute("FSTOCKNAME", ((JSONObject) array.get(i)).getString("FSTOCKNAME"));
				cell.addAttribute("FSTOCKSTATUS", ((JSONObject) array.get(i)).getString("FSTOCKSTATUS"));
				cell.addAttribute("FSTOCKUNIT", ((JSONObject) array.get(i)).getString("FSTOCKUNIT"));
				cell.addAttribute("FSUIT", ((JSONObject) array.get(i)).getString("FSUIT"));
				cell.addAttribute("FUSKIN", ((JSONObject) array.get(i)).getString("FUSKIN"));
				cell.addAttribute("FWARP", ((JSONObject) array.get(i)).getString("FWARP"));
				cell.addAttribute("FWARPFLOWERSIZE", ((JSONObject) array.get(i)).getString("FWARPFLOWERSIZE"));
				cell.addAttribute("FWARPTHREAD", ((JSONObject) array.get(i)).getString("FWARPTHREAD"));
				cell.addAttribute("FWARPTHREADCOUNT", ((JSONObject) array.get(i)).getString("FWARPTHREADCOUNT"));
				cell.addAttribute("FWEFT", ((JSONObject) array.get(i)).getString("FWEFT"));
				cell.addAttribute("FWEFTFLOWERSIZE", ((JSONObject) array.get(i)).getString("FWEFTFLOWERSIZE"));
				cell.addAttribute("FWEFTTHREAD", ((JSONObject) array.get(i)).getString("FWEFTTHREAD"));
				cell.addAttribute("FWEFTTHREADCOUNT", ((JSONObject) array.get(i)).getString("FWEFTTHREADCOUNT"));
			}

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
