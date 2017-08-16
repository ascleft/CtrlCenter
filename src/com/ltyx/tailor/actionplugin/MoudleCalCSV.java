package com.ltyx.tailor.actionplugin;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.zc.web.base.doman.ZCActionPluginBase;
import com.zc.web.base.doman.ZCHttpParam;
import com.zc.web.base.service.ZCHttpReq;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.TimeHelper;
import com.zc.web.base.service.URLConfigHelper;

public class MoudleCalCSV extends ZCActionPluginBase {

	public MoudleCalCSV(HttpServletRequest req) {
		this.req = req;
	}

	public boolean doJobs() {

		ERRCODE = "0";
		ERRDESC = "succ";
		JSONObject datacontent = new JSONObject();
		datacontent.put("CONTENT", getCSV());
		datacontent.put("NAME", "鲁泰优纤智能下单系统" + TimeHelper.getTimeMS());
		data = datacontent.toString();
		return true;

	}

	public String getCSV() {
		String csvTitle_1 = "";
		String csvBody_1 = "";

		String csvBody_1_1 = "";
		String csvBody_1_2 = "";
		String csvBody_1_3 = "";
		String csvBody_1_4 = "";
		String csvBody_1_5 = "";

		String csvTitle_2 = "";
		String csvBody_2 = "";

		csvTitle_1 += "客户姓名" + ",";
		csvBody_1 += getString("customer_name") + ",";
		csvTitle_1 += "定制店名称" + ",";
		csvBody_1 += getString("") + ",";
		csvTitle_1 += "定制店编号" + ",";
		csvBody_1 += getString("") + ",";
		csvTitle_1 += "收货地址" + ",";
		csvBody_1 += getString("customer_address") + ",";
		csvTitle_1 += "收货人" + ",";
		csvBody_1 += getString("") + ",";
		csvTitle_1 += "收货联系方式" + ",";
		csvBody_1 += getString("") + ",";
		csvTitle_1 += "量体内容" + ",";
		csvBody_1 += getString("") + ",";
		csvTitle_1 += "身高" + ",";
		csvBody_1 += getString("shengao") + ",";
		csvTitle_1 += "体重" + ",";
		csvBody_1 += getString("tizhong") + ",";
		csvTitle_1 += "领围" + ",";
		csvBody_1 += getString("ling_wei") + ",";
		csvTitle_1 += "胸围" + ",";
		csvBody_1 += getString("xiong_wei") + ",";
		csvTitle_1 += "腰围" + ",";
		csvBody_1 += getString("yao_wei") + ",";
		csvTitle_1 += "肚围" + ",";
		csvBody_1 += getString("du_wei") + ",";
		csvTitle_1 += "底边" + ",";
		csvBody_1 += getString("dibian") + ",";
		csvTitle_1 += "后衣长" + ",";
		csvBody_1 += getString("houshen_chang") + ",";
		csvTitle_1 += "肩宽" + ",";
		csvBody_1 += getString("jian_kuan") + ",";
		csvTitle_1 += "长袖长" + ",";
		csvBody_1 += getString("xiu_chang_zuo") + ",";
		csvTitle_1 += "左腕围" + ",";
		csvBody_1 += getString("xiutouchang_zuo") + ",";
		csvTitle_1 += "右腕围" + ",";
		csvBody_1 += getString("xiutouchang_you") + ",";
		csvTitle_1 += "袖肥" + ",";
		csvBody_1 += getString("xiu_fei") + ",";
		csvTitle_1 += "小臂围" + ",";
		csvBody_1 += getString("xiuzhou_fei") + ",";
		csvTitle_1 += "前身长" + ",";
		csvBody_1 += getString("qianshen_chang") + ",";
		csvTitle_1 += "前胸宽" + ",";
		csvBody_1 += getString("qianxiong_kuan") + ",";
		csvTitle_1 += "后背宽" + ",";
		csvBody_1 += getString("houbei_kuan") + ",";
		csvTitle_1 += "短袖长" + ",";
		csvBody_1 += getString("duanxiu_chang") + ",";
		csvTitle_1 += "短袖口" + ",";
		csvBody_1 += getString("duanxiu_kouwei_zuo") + ",";
		csvTitle_1 += "号衣尺码" + ",";
		csvBody_1 += getString("") + ",";
		csvTitle_1 += "客供面料快递单号" + ",";

		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += "DP后成衣尺寸" + ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";
		csvBody_1_1 += ",";

		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += "面料缩率" + ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";
		csvBody_1_2 += ",";

		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += "DP前尺寸" + ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";
		csvBody_1_3 += ",";

		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += "参考尺寸" + ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";
		csvBody_1_4 += ",";

		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += "调整数据" + ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";
		csvBody_1_5 += ",";

		csvTitle_2 += "订单编号" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "快递方式" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "优纤面料" + ",";
		csvBody_2 += getString("uskin_code") + ",";
		csvTitle_2 += "客供面料编号" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "配色面料" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "配色部位" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "身型" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "后背款式" + ",";
		csvBody_2 += getString("LZX_17") + ",";
		csvTitle_2 += "长短袖" + ",";
		csvBody_2 += getString("tailor_type") + ",";
		csvTitle_2 += "领型" + ",";
		csvBody_2 += getString("LZX_01") + ",";
		csvTitle_2 += "领插片" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "袖头" + ",";
		csvBody_2 += getString("LZX_02") + ",";
		csvTitle_2 += "门襟" + ",";
		csvBody_2 += getString("LZX_03") + ",";
		csvTitle_2 += "口袋" + ",";
		csvBody_2 += getString("LZX_04") + ",";
		csvTitle_2 += "纽扣" + ",";
		csvBody_2 += getString("kouzi") + ",";
		csvTitle_2 += "主唛" + ",";
		csvBody_2 += getString("YX_08") + ",";
		csvTitle_2 += "明线宽" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "侧缝工艺" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "嵌条" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "衬布" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "刺绣字体" + ",";
		csvBody_2 += getString("LZX_11_CHAR_TYPE") + ",";
		csvTitle_2 += "刺绣大小" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "刺绣位置" + ",";
		csvBody_2 += getString("LZX_13_FOR_CHAR") + ",";
		csvTitle_2 += "刺绣内容" + ",";
		csvBody_2 += getString("LZX_11_CHAR_WORD") + ",";
		csvTitle_2 += "刺绣颜色" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "洗唛成分" + ",";
		csvBody_2 += getString("") + ",";
		csvTitle_2 += "包装" + ",";
		csvBody_2 += getString("YX_09") + ",";
		csvTitle_2 += "快递方式" + ",";
		csvBody_2 += getString("") + ",";

		return csvTitle_1 + "\n" + csvBody_1 + "\n" + csvBody_1_1 + "\n" + csvBody_1_2 + "\n" + csvBody_1_3 + "\n" + csvBody_1_4 + "\n" + csvBody_1_5 + "\n" + csvTitle_2 + "\n"
				+ csvBody_2;
	}

}
