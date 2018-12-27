package com.ltyx.open.unity;

import java.util.ArrayList;

import com.ltyx.open.unity.bean.UnityInventoryCell;
import com.ltyx.open.unity.moudle.UnityInventoryERP600Moudle;
import com.ltyx.open.unity.moudle.UnityInventoryK3Moudle;
import com.zc.support.doman.CCActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.TextLogHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UnityInventoryAction extends CCActionSupport {

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
	private String Fuzzy;// Y N
	private String Attribute;// Y N

	private String[][] keyWords;

	public String submit() {

		init(true);

		String methodName = "即时库存 联合查询";

		doGetInventory();

		ZCReqIntroGetter.showParams(methodName, request, TextLogHelper.Type.UNITY_SEARCH_NSRC);
		writeResp(methodName, TextLogHelper.Type.UNITY_SEARCH_NSRC);

		return null;

	}

	public void doGetInventory() {

		JSONArray jsonArray = new JSONArray();

		Code = getReqParamString("Code");
		CodeType = getReqParamString("CodeType");// YX LT (YX)
		Department = getReqParamString("Department");// ERP600 K3 (&)
		Warehouse = getReqParamString("Warehouse");// LT ZN KG (LT)
		Fuzzy = getReqParamString("Fuzzy");// Y N (Y)
		Attribute = getReqParamString("Attribute");// Y N (N)

		packageKeyWords();

		if (Code.length() > 3) {
			switch (Department) {
			case "ERP600":
				getERP600(jsonArray, Code, keyWords[0][2]);
				break;
			case "K3":
				getK3(jsonArray, Code, keyWords[1][2], keyWords[1][3], keyWords[1][4]);
				break;
			default:
				getERP600(jsonArray, Code, keyWords[0][2]);
				getK3(jsonArray, Code, keyWords[1][2], keyWords[1][3], keyWords[1][4]);
				break;
			}
			ERRCODE = "0";
			ERRDESC = "succ";
			data = jsonArray.toString();
		} else {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "面料编号过短，请填写正确的面料编码";
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

	private JSONArray getK3(JSONArray jsonArray, String code, String warehouse, String fuzzy, String attribute) {
		UnityInventoryK3Moudle moudle = new UnityInventoryK3Moudle(request);
		moudle.setParam(code, warehouse, fuzzy, attribute);
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

			jsonCell.put("Attribute", cell.getAttributes());

			array.add(jsonCell);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("desc", desc);
		jsonObject.put("list", array);

		return jsonObject;

	}

	public void packageKeyWords() {

		keyWords = new String[2][5];

		// 编码类型
		// getERP600(jsonArray, Code, "LT");
		// keyWords[0][2]
		// YX LT (YX)
		switch (CodeType) {
		case "LT":
		case "YX":
			keyWords[0][2] = CodeType;
			break;
		default:
			keyWords[0][2] = "YX";
			break;
		}
		// 仓库指定
		// getK3(jsonArray, Code, "LT", "Y");
		// keyWords[1][2]
		// LT ZN KG (LT)
		switch (Warehouse) {
		case "LT":
		case "ZN":
		case "KG":
			keyWords[1][2] = Warehouse;
			break;
		default:
			keyWords[1][2] = "LT";
			break;
		}
		// 模糊查询
		// getK3(jsonArray, Code, "LT", "Y");
		// keyWords[1][3]
		// Y N (Y)
		switch (Fuzzy) {
		case "Y":
		case "N":
			keyWords[1][3] = Fuzzy;
			break;
		default:
			keyWords[1][3] = "Y";
			break;
		}
		// 面料属性
		// getK3(jsonArray, Code, "LT", "Y");
		// keyWords[1][4]
		// Y N (N)
		switch (Attribute) {
		case "N":
		case "Y":
			keyWords[1][4] = Attribute;
			break;
		default:
			keyWords[1][4] = "N";
			break;
		}

	}

}
