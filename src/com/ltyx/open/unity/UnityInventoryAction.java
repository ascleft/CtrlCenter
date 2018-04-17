package com.ltyx.open.unity;

import java.util.ArrayList;

import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UnityInventoryAction extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {
		return "succ";
	}

	private String Code;
	private String CodeType;// LT YX
	private String Department;// ERP600 K3
	private String Warehouse;// LT ZN KG

	public String submit() {

		init(true);

		String methodName = "ERP600即时库存查询";

		ZCReqIntroGetter.showParams(methodName, request);

		doGetInventory();

		writeResp(methodName);

		return null;

	}

	public void doGetInventory() {

		JSONArray jsonArray = new JSONArray();

		Code = getReqParamString("Code");
		CodeType = getReqParamString("CodeType");// LT YX
		Department = getReqParamString("Department");// ERP600 K3
		Warehouse = getReqParamString("Warehouse");// LT ZN KG

		if (Code.length() > 3) {
			switch (Department) {
			case "ERP600":
				switch (CodeType) {
				case "LT":
					getERP600(jsonArray, Code, "LT");
					break;
				case "YX":
					getERP600(jsonArray, Code, "YX");
					break;
				default:
					getERP600(jsonArray, Code, "YX");
					break;
				}
				break;
			case "K3":
				switch (Warehouse) {
				case "LT":
					getK3(jsonArray, Code, "LT");
					break;
				case "ZN":
					getK3(jsonArray, Code, "ZN");
					break;
				case "KG":
					getK3(jsonArray, Code, "KG");
					break;
				default:
					getK3(jsonArray, Code, "LT");
					break;
				}
				break;
			default:
				switch (CodeType) {
				case "LT":
					getERP600(jsonArray, Code, "LT");
					break;
				case "YX":
					getERP600(jsonArray, Code, "YX");
					break;
				default:
					getERP600(jsonArray, Code, "YX");
					break;
				}
				switch (Warehouse) {
				case "LT":
					getK3(jsonArray, Code, "LT");
					break;
				case "ZN":
					getK3(jsonArray, Code, "ZN");
					break;
				case "KG":
					getK3(jsonArray, Code, "KG");
					break;
				default:
					getK3(jsonArray, Code, "LT");
					break;
				}
				break;
			}
			ERRCODE = "0";
			ERRDESC = "succ";
			data = jsonArray.toString();
		} else {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "请填写正确的面料编码";
		}

	}

	private JSONArray getERP600(JSONArray jsonArray, String code, String codeType) {
		UnityInventoryERP600Moudle moudle = new UnityInventoryERP600Moudle(request);
		moudle.setParam(code, codeType);
		if (moudle.doJobs()) {
			addProgressSucc("联合即时库存 ERP600");
		} else {
			addProgressFail("联合即时库存 ERP600");
		}
		jsonArray.add(packageJson(moudle.name, moudle.desc, moudle.cells));
		return jsonArray;
	}

	private JSONArray getK3(JSONArray jsonArray, String code, String warehouse) {
		UnityInventoryK3Moudle moudle = new UnityInventoryK3Moudle(request);
		moudle.setParam(code, warehouse);
		if (moudle.doJobs()) {
			addProgressSucc("联合即时库存 K3");
		} else {
			addProgressFail("联合即时库存 K3");
		}
		jsonArray.add(packageJson(moudle.name, moudle.desc, moudle.cells));
		return jsonArray;
	}

	public JSONObject packageJson(String name, String desc, ArrayList<UnityInventoryCell> list) {

		JSONArray array = new JSONArray();

		for (UnityInventoryCell cell : list) {
			JSONObject jsonCell = new JSONObject();
			jsonCell.put("UskinCode", cell.UskinCode);
			jsonCell.put("LuthaiCode", cell.LuthaiCode);
			jsonCell.put("All", cell.All);
			jsonCell.put("Available", cell.Available);
			jsonCell.put("Locked", cell.Locked);
			jsonCell.put("Department", cell.Department);
			jsonCell.put("Warehouse", cell.Warehouse);
			array.add(jsonCell);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("desc", desc);
		jsonObject.put("list", array);

		System.out.println(jsonObject.toString());

		return jsonObject;

	}

}
