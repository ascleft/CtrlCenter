package com.ltyx.open.ybr.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;

public class MoudleYBRParamUtil extends ZCBaseActionSupportPlugin {

	public MoudleYBRParamUtil(HttpServletRequest req) {
		this.name = "获取参数";
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub
		return false;
	}

	public ZCHttpReqParam getYBROrderManPBYX() {

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
		param.addParam("button_main_ft1", getReqParamString("kouzi", ""));	//锁钉位置
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
		param.addParam("prices_desc", "衣邦人订单（下单工具接入）：" + getReqParamString("prices_desc"));
		param.addParam("prices_now", "" + 0);
		param.addParam("prices_system", "" + 0);
		param.addParam("qiantiao", getReqParamString("qiantiao"));
		param.addParam("sex", getReqParamString("sex"));
		param.addParam("tailor_type", getReqParamString("tailor_type"));
		param.addParam("uskin_code", getReqParamString("uskin_code").toUpperCase());
		param.addParam("uskin_code_2", getReqParamString("uskin_code_2").toUpperCase());
		param.addParam("weizhi_peise", getReqParamString("weizhi_peise"));
		param.addParam("weizhi_zhidai", getReqParamString("weizhi_zhidai"));
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

		// 20180601新增
		param.addParam("custom_id", getReqParamString("custom_id")); // 客户编号
		param.addParam("tag_price", getReqParamString("tag_price")); // 吊牌价
		param.addParam("document_pull_date", getReqParamString("document_pull_date")); // 合同发货日期
		param.addParam("financial_auth_time", getReqParamString("financial_auth_time")); // 财审通过时间

		// 特体选项
		param.addParam("spc_b_sho_f", getReqParamString("spc_b_sho_f"));// 前冲后掰肩
		param.addParam("spc_b_sho_l", getReqParamString("spc_b_sho_l"));// 左肩型
		param.addParam("spc_b_sho_r", getReqParamString("spc_b_sho_r"));// 右肩型
		param.addParam("spc_b_spi_s", getReqParamString("spc_b_spi_s"));// 前弓后仰体
		param.addParam("spc_b_spi_h", getReqParamString("spc_b_spi_h"));// 驼背
		param.addParam("spc_b_che_n", getReqParamString("spc_b_che_n"));// 胸型
		param.addParam("spc_b_abd_n", getReqParamString("spc_b_abd_n"));// 肚型
		param.addParam("spc_b_sle_n", getReqParamString("spc_b_sle_n"));// 袖子臂型
		param.addParam("spc_b_muf_d", getReqParamString("spc_b_muf_d"));// 袖窿深下挖
		param.addParam("spc_b_muf_u", getReqParamString("spc_b_muf_u"));// 袖窿深上调
		param.addParam("spc_c_nec_f", getReqParamString("spc_c_nec_f"));// 前领窝下挖

		return param;

	}

	public ZCHttpReqParam getYBROrderManPBC() {
		ZCHttpReqParam param = getYBROrderManPBYX();
		return param;
	}

	// 返修订单
	public ZCHttpReqParam getYBROrderRepair() {
		ZCHttpReqParam param = new ZCHttpReqParam();

		param.addParam("operator_id", getReqParamString("operator_id"));
		param.addParam("ybr_addr_name", getReqParamString("ybr_addr_name"));
		param.addParam("ybr_addr_mobile", getReqParamString("ybr_addr_mobile"));
		param.addParam("ybr_addr_detail", getReqParamString("ybr_addr_detail"));
		param.addParam("ybr_addr_province", getReqParamString("ybr_addr_province"));
		param.addParam("ybr_addr_prefecture", getReqParamString("ybr_addr_prefecture"));
		param.addParam("ybr_addr_county", getReqParamString("ybr_addr_county"));
		param.addParam("FactoryID", getReqParamString("FactoryID"));
		param.addParam("Price", getReqParamString("Price"));
		param.addParam("Type", getReqParamString("Type"));
		param.addParam("ExpressNO", getReqParamString("ExpressNO"));
		param.addParam("Tips", getReqParamString("Tips"));
		param.addParam("ybr_ono_cust", getReqParamString("ybr_ono_cust"));
		param.addParam("ybr_ono_api", getReqParamString("ybr_ono_api"));
		param.addParam("document_pull_date", getReqParamString("document_pull_date"));
		param.addParam("financial_auth_time", getReqParamString("financial_auth_time"));

		return param;
	}

}