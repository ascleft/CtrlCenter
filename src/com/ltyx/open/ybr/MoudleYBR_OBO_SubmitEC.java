package com.ltyx.open.ybr;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;

public class MoudleYBR_OBO_SubmitEC extends ZCBaseActionSupportPlugin {

	public MoudleYBR_OBO_SubmitEC(HttpServletRequest req) {
		this.request = req;
	}

	double price = 0;

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean doJobs() {

		ZCHttpReqParam param = new ZCHttpReqParam();

		// 订单信息
		param.addParam("LZX_01", getReqParamString("LZX_01"));
		param.addParam("LZX_02", getReqParamString("LZX_02"));
		param.addParam("LZX_03", getReqParamString("LZX_03"));
		param.addParam("LZX_04", getReqParamString("LZX_04"));
		param.addParam("LZX_06", getReqParamString("LZX_06"));
		param.addParam("LZX_11_CHAR_COLOR", getReqParamString("LZX_11_CHAR_COLOR"));
		param.addParam("LZX_11_CHAR_SIZE", getReqParamString("LZX_11_CHAR_SIZE"));
		param.addParam("LZX_11_CHAR_TYPE", getReqParamString("LZX_11_CHAR_TYPE"));
		param.addParam("LZX_11_CHAR_WORD", getReqParamString("LZX_11_CHAR_WORD"));
		param.addParam("LZX_11_FOR_CHAR_SWITCH", getReqParamString("LZX_11_FOR_CHAR_SWITCH"));
		param.addParam("LZX_11_FOR_PIC_SWITCH", getReqParamString("LZX_11_FOR_PIC_SWITCH"));
		param.addParam("LZX_11_PIC_COLOR", getReqParamString("LZX_11_PIC_COLOR"));
		param.addParam("LZX_11_PIC_NUM", getReqParamString("LZX_11_PIC_NUM"));
		param.addParam("LZX_11_PIC_SIZE", getReqParamString("LZX_11_PIC_SIZE"));
		param.addParam("LZX_11_PIC_TYPE", getReqParamString("LZX_11_PIC_TYPE"));
		param.addParam("LZX_120", getReqParamString("LZX_120"));
		param.addParam("LZX_13_FOR_CHAR", getReqParamString("LZX_13_FOR_CHAR"));
		param.addParam("LZX_13_FOR_PIC", getReqParamString("LZX_13_FOR_PIC"));
		param.addParam("LZX_17", getReqParamString("LZX_17"));
		param.addParam("LZX_26", getReqParamString("LZX_26"));
		param.addParam("YX_08", getReqParamString("YX_08", ""));
		param.addParam("YX_09", getReqParamString("YX_09"));
		param.addParam("cefeng", getReqParamString("cefeng"));
		param.addParam("chenbu", getReqParamString("chenbu"));
		param.addParam("customer_address", getReqParamString("customer_address"));
		param.addParam("customer_name", getReqParamString("customer_name"));
		param.addParam("customer_tel", getReqParamString("customer_tel"));
		param.addParam("customer_tel_target", getReqParamString("customer_tel_target"));
		param.addParam("customer_tips", getReqParamString("customer_tips"));
		param.addParam("easy_type", getReqParamString("easy_type"));
		param.addParam("kouzi", getReqParamString("kouzi", ""));
		param.addParam("line_color_location_1", getReqParamString("line_color_location_1"));
		param.addParam("line_color_location_2", getReqParamString("line_color_location_2"));
		param.addParam("line_color_location_3", getReqParamString("line_color_location_3"));
		param.addParam("line_color_location_4", getReqParamString("line_color_location_4"));
		param.addParam("lingcheng", getReqParamString("lingcheng"));
		param.addParam("mingxian", getReqParamString("mingxian"));
		param.addParam("operator_id", getReqParamString("operator_id"));
		param.addParam("operator_name", getReqParamString("operator_name"));
		param.addParam("order_delivery_time", getReqParamString("order_delivery_time"));
		param.addParam("order_mtm_type", getReqParamString("order_mtm_type"));
		param.addParam("order_processing_cost", getReqParamString("order_processing_cost"));
		param.addParam("order_production_count", getReqParamString("order_production_count"));
		param.addParam("order_production_count_real", getReqParamString("order_production_count_real"));
		param.addParam("prices_desc", "衣帮人订单（下单工具接入）：" + getReqParamString("prices_desc"));
		param.addParam("prices_now", "" + this.price);
		param.addParam("prices_system", "" + this.price);
		param.addParam("qiantiao", getReqParamString("qiantiao"));
		param.addParam("sex", getReqParamString("sex"));
		param.addParam("tailor_type", getReqParamString("tailor_type"));
		param.addParam("uskin_code", getReqParamString("uskin_code").toUpperCase());
		param.addParam("uskin_code_2", getReqParamString("uskin_code_2").toUpperCase());
		param.addParam("weizhi_peise", getReqParamString("weizhi_peise", ""));
		param.addParam("zhidai", getReqParamString("zhidai"));

		// 成衣尺寸、量体尺寸信息
		param.addParam("measure_type", getReqParamString("measure_type"));
		param.addParam("size", getReqParamString("size"));
		param.addParam("height", getReqParamString("height"));
		param.addParam("weight", getReqParamString("weight"));
		param.addParam("xiong_wei", getReqParamString("xiong_wei"));
		param.addParam("yao_wei", getReqParamString("yao_wei"));
		param.addParam("du_wei", getReqParamString("du_wei"));
		param.addParam("dibian", getReqParamString("dibian"));
		param.addParam("ling_wei", getReqParamString("ling_wei"));
		param.addParam("neiwaichuan", getReqParamString("neiwaichuan"));
		param.addParam("houshen_chang_nei", getReqParamString("houshen_chang_nei"));
		param.addParam("houshen_chang_wai", getReqParamString("houshen_chang_wai"));
		param.addParam("jian_kuan", getReqParamString("jian_kuan"));
		param.addParam("xiu_chang", getReqParamString("xiu_chang"));
		param.addParam("duanxiu_chang", getReqParamString("duanxiu_chang"));
		param.addParam("xiu_fei", getReqParamString("xiu_fei"));
		param.addParam("xiuzhou_fei", getReqParamString("xiuzhou_fei"));
		param.addParam("duanxiu_kouwei", getReqParamString("duanxiu_kouwei"));
		param.addParam("xiutouchang_zuo", getReqParamString("xiutouchang_zuo"));
		param.addParam("xiutouchang_you", getReqParamString("xiutouchang_you"));
		param.addParam("qianshen_chang", getReqParamString("qianshen_chang"));
		param.addParam("qianxiong_kuan", getReqParamString("qianxiong_kuan"));
		param.addParam("houbei_kuan", getReqParamString("houbei_kuan"));
		param.addParam("chest_height", getReqParamString("chest_height"));
		param.addParam("chest_distance", getReqParamString("chest_distance"));

		param.addParam("ybr_ono_cust", getReqParamString("ybr_ono_cust"));
		param.addParam("ybr_ono_api", getReqParamString("ybr_ono_api"));
		param.addParam("ybr_fabric_using", getReqParamString("ybr_fabric_using"));
		param.addParam("ybr_addr_detail", getReqParamString("ybr_addr_detail"));
		param.addParam("ybr_addr_name", getReqParamString("ybr_addr_name"));
		param.addParam("ybr_addr_mobile", getReqParamString("ybr_addr_mobile"));
		param.addParam("ybr_addr_province", getReqParamString("ybr_addr_province"));
		param.addParam("ybr_addr_prefecture", getReqParamString("ybr_addr_prefecture"));
		param.addParam("ybr_addr_county", getReqParamString("ybr_addr_county"));

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshopaide_add_cart_ybr.getUrl(), param);
		Log.Nano.tag("衣帮人 提交 Resp From EC", httpResp);

		JSONObject jsonHttpResp;
		String jsonERRCODE;
		String jsonERRDESC;
		String jsonData;
		try {
			jsonHttpResp = JSONObject.fromObject(httpResp);
			jsonERRCODE = jsonHttpResp.getString("ERRCODE");
			jsonERRDESC = jsonHttpResp.getString("ERRDESC");
			jsonData = jsonERRDESC;
		} catch (Exception e) {
			// TODO: handle exception
			Log.Nano.tag("EC服务器响应错误", httpResp);
			jsonERRCODE = "0";
			jsonERRDESC = "fail";
			jsonData = "EC服务器响应错误";
		}

		if ("0".equals(jsonERRCODE) && "succ".equals(jsonERRDESC)) {
			return true;
		} else {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = jsonData;
			return false;
		}

	}

}
