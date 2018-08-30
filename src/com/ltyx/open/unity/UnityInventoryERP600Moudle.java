package com.ltyx.open.unity;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqProperty;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.LogType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UnityInventoryERP600Moudle extends ZCBaseActionSupportPlugin {

	public String name = null;
	public String desc = null;
	public ArrayList<UnityInventoryCell> cells = null;

	private String BPartnerCode = null; // 鲁泰分配给合作方的账号
	private String BPartnerKey_ = null; // 鲁泰分配给合作方的验证码
	private String ProCode_____ = null; // 产品编码
	private String ItemType____ = null; // 内容类型

	public UnityInventoryERP600Moudle(HttpServletRequest req) {
		this.request = req;

	}

	public void setParam(String code, String type) {

		this.name = "ERP600";
		this.desc = "正常";

		BPartnerCode = "BJYX001"; // 鲁泰分配给合作方的账号
		BPartnerKey_ = "123"; // 鲁泰分配给合作方的验证码
		ProCode_____ = code; // 产品编码
		if ("YX".equals(type)) {
			ItemType____ = "E"; // 内容类型 优纤编码
		} else if ("LT".equals(type)) {
			ItemType____ = "I"; // 内容类型 物料编码
		} else {
			ItemType____ = "E"; // 内容类型 优纤编码
		}

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

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("BPartnerCode", BPartnerCode);// 鲁泰分配给合作方的账号
		jsonObject.put("BPartnerKey", BPartnerKey_);// 鲁泰分配给合作方的验证码
		jsonObject.put("ProCode", ProCode_____);// 产品编码
		jsonObject.put("ItemType", ItemType____);// 内容类型

		ZCHttpReqProperty property = new ZCHttpReqProperty();
		property.importBase(ZCHttpReqProperty.BaseProperty.TYPE_1);
		property.importUserAgent(ZCHttpReqProperty.Terminal.PC);
		property.addProperty("Content-Type", "text/xml; charset=utf-8");
		property.addProperty("SOAPAction", "\"http://www.lttc.com.cn/ProductInventory\"");

		String req = "";
		req += "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		req += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		req += "<soap:Body>";
		req += "<ProductInventory xmlns=\"http://www.lttc.com.cn/\">";
		req += "<BProInfo>";
		req += jsonObject.toString();
		req += "</BProInfo>";
		req += "</ProductInventory>";
		req += "</soap:Body>";
		req += "</soap:Envelope>";

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addBody(req);
		// String resp =
		// ZCHttpReqSender.sendPost("http://eluthai.com/LTGlobalServices/BJYXInterface/LTGlobalServices.asmx",
		// param, property, LogType.SearchUnity);
		String resp = ZCHttpReqSender.sendPost("http://www.lttc.com.cn/LTGlobalServices/BJYXInterface/LTGlobalServices.asmx", param, property, LogType.SearchUnity);
		return resp;
	}

	private String getCore(String in) {

		String resp = "";

		if (in.length() > 300) {
			resp = in.substring(300, in.length() - 80);
		}

		return resp;
	}

	private boolean change(String in) {

		boolean isSucc = false;

		try {
			JSONObject jsonObject = JSONObject.fromObject(in);

			String code = jsonObject.get("ResultType").toString();
			String desc = jsonObject.get("ResultMsg").toString();

			if (code.equals("1") && desc.equals("成功")) {
				JSONArray array = jsonObject.getJSONArray("Content");

				for (int i = 0; i < array.size(); i++) {

					String uskinCode_ = ((JSONObject) array.get(i)).getString("ProCode");
					String luthaiCode = ((JSONObject) array.get(i)).getString("FabricCode");
					double all_______ = ((JSONObject) array.get(i)).getDouble("Quantity");
					double locked____ = ((JSONObject) array.get(i)).getDouble("LockingQuantity");
					String department = "现货科/零裁组";
					String warehouse_ = "未定义";

					UnityInventoryCell cell = new UnityInventoryCell(uskinCode_, luthaiCode, all_______, locked____, department, warehouse_);

					cells.add(cell);
				}

				if (cells.size() > 0) {
					this.desc = "库存信息正常";
				} else {
					this.desc = "库存信息正常，无库存";
				}
				isSucc = true;
			} else if (code.equals("0") && desc.equals("未处理！")) {
				// this.desc = "鲁泰ERP600返回异常: ";
				// this.desc += " ResultType " + code;
				// this.desc += " ResultMsg " + desc;
				// isSucc = false;
				this.desc = "库存信息正常，无库存";
				isSucc = true;
			} else {
				this.desc = "鲁泰ERP600返回异常,未知异常： " + " ResultType " + code + " ResultMsg " + desc;
				isSucc = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			this.desc = "鲁泰ERP600服务器异常 ,详情查看日志";
			isSucc = false;
		}

		return isSucc;
	}

}
