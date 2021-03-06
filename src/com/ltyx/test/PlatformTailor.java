package com.ltyx.test;

import java.util.Random;

import com.zc.support.doman.CCActionSupport;
import com.zc.support.service.TextLogHelper;

public class PlatformTailor extends CCActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 100894456L;

	public PlatformTailor() {

	}

	public String getPage() {

		return "succ";
	}

	public String updateList() {
		init(true);

		String key = getReqParamString("key");

		key = key.trim();

		MoudleRTERP moudleRTERP = new MoudleRTERP(request);

		moudleRTERP.getAll();

		String list = moudleRTERP.search(key);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = list.toString();

		writeResp("更新平台商品列表", TextLogHelper.Type.UNDEFINED);

		return null;

	}

	public String RTInventory() {
		init(true);

		Random random = new Random();
		int inventory = random.nextInt(100);

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "" + inventory;

		writeResp("更新即时库存", TextLogHelper.Type.UNDEFINED);

		return null;
	}

	public String submitTailor() {
		init(true);

		String code = getReqParamString("code");
		String num = getReqParamString("num");

		ERRCODE = "0";
		ERRDESC = "succ";
		data = "下单成功：" + code + "共" + num + "件";

		writeResp("平台商品 下单结束", TextLogHelper.Type.UNDEFINED);

		return null;
	}

}
