package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MoudleCSDParamUtil extends ZCBaseActionSupportPlugin {

	public MoudleCSDParamUtil(HttpServletRequest req) {
		this.name = "获取参数";
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub
		return false;
	}

	public ZCHttpReqParam getCSUOrderManPBYX() {

		ZCHttpReqParam param = new ZCHttpReqParam();

		// 订单信息
		param.addParam("LZX_01", getReqParamString("LZX_01"));
		param.addParam("LZX_02", getReqParamString("LZX_02"));
		param.addParam("LZX_03", getReqParamString("LZX_03"));
		param.addParam("LZX_04", getReqParamString("LZX_04"));
		param.addParam("LZX_06", getReqParamString("LZX_06"));

		// 文字刺绣
		// param.addParam("LZX_11_FOR_CHAR_SWITCH",
		// getReqParamString("LZX_11_FOR_CHAR_SWITCH"));
		// param.addParam("LZX_11_CHAR_TYPE",
		// getReqParamString("LZX_11_CHAR_TYPE"));
		// param.addParam("LZX_13_FOR_CHAR",
		// getReqParamString("LZX_13_FOR_CHAR"));
		// param.addParam("LZX_11_CHAR_SIZE",
		// getReqParamString("LZX_11_CHAR_SIZE"));
		// param.addParam("LZX_11_CHAR_COLOR",
		// getReqParamString("LZX_11_CHAR_COLOR"));
		// param.addParam("LZX_11_CHAR_WORD",
		// getReqParamString("LZX_11_CHAR_WORD"));
		// 图案刺绣
		// param.addParam("LZX_11_FOR_PIC_SWITCH",
		// getReqParamString("LZX_11_FOR_PIC_SWITCH"));
		// param.addParam("LZX_11_PIC_TYPE",
		// getReqParamString("LZX_11_PIC_TYPE"));
		// param.addParam("LZX_11_PIC_SIZE",
		// getReqParamString("LZX_11_PIC_SIZE"));
		// param.addParam("LZX_13_FOR_PIC",
		// getReqParamString("LZX_13_FOR_PIC"));
		// param.addParam("LZX_11_PIC_COLOR",
		// getReqParamString("LZX_11_PIC_COLOR"));
		// param.addParam("LZX_11_PIC_NUM",
		// getReqParamString("LZX_11_PIC_NUM"));

		// 新版刺绣参数
		param.addParam("embroidery", getEmbroideryPackage());

		param.addParam("LZX_120", getReqParamString("LZX_120"));
		param.addParam("LZX_17", getReqParamString("LZX_17"));
		param.addParam("LZX_26", getReqParamString("LZX_26"));
		param.addParam("YX_08", getReqParamString("YX_08", ""));
		param.addParam("YX_09", getReqParamString("YX_09"));
		param.addParam("cefeng", getReqParamString("cefeng"));
		param.addParam("chenbu", getReqParamString("chenbu"));
		param.addParam("customer_address", getReqParamString("customer_address"));
		param.addParam("customer_name", getReqParamString("customer_name"));
		param.addParam("customer_tel", getReqParamString("customer_tel"));
		// param.addParam("customer_tel_target",
		// getReqParamString("customer_tel_target"));
		param.addParam("customer_tips", getReqParamString("customer_tips"));
		// param.addParam("design_code", getReqParamString("design_code"));
		param.addParam("easy_type", getReqParamString("easy_type"));
		param.addParam("kouzi", getReqParamString("kouzi", ""));
		param.addParam("button_main_ft1", getReqParamString("button_main_ft1", "")); // 锁钉位置
		param.addParam("line_color_location_1", getReqParamString("line_color_location_1"));
		param.addParam("line_color_location_2", getReqParamString("line_color_location_2"));
		param.addParam("line_color_location_3", getReqParamString("line_color_location_3"));
		param.addParam("line_color_location_4", getReqParamString("line_color_location_4"));
		param.addParam("lingcheng", getReqParamString("lingcheng"));
		param.addParam("mingxian", getReqParamString("mingxian"));
		param.addParam("operator_id", getReqParamString("operator_id"));
		param.addParam("operator_name", getReqParamString("operator_name"));
		param.addParam("order_delivery_time", chooseNumber(getReqParamString("order_delivery_time")));// -----------
		param.addParam("order_mtm_type", getReqParamString("order_mtm_type"));// ---------------------
		param.addParam("order_processing_cost", getReqParamString("order_processing_cost"));// -------
		param.addParam("order_production_count", chooseNumber(getReqParamString("order_production_count")));
		param.addParam("order_production_count_real", getReqParamString("order_production_count_real"));// -----
		param.addParam("prices_desc", getReqParamString("prices_desc"));
		param.addParam("prices_now", getReqParamString("prices_now"));
		param.addParam("prices_system", getReqParamString("prices_system"));
		param.addParam("qiantiao", getReqParamString("qiantiao"));
		param.addParam("special_technics", getReqParamString("special_technics"));
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

		param.addParam("cixiu_kegong_num", getReqParamString("cixiu_kegong_num"));// 客供图案刺绣数量

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

		param.addParam("button_decorative_num", getReqParamString("button_decorative_num"));// 装饰扣数量
		param.addParam("button_decorative_code", getReqParamString("button_decorative_code"));// 装饰扣编号
		param.addParam("button_decorative_pos", getReqParamString("button_decorative_pos"));// 装饰扣位置

		param.addParam("ExpressNO", getReqParamString("ExpressNO"));// 客供物料单号

		param.addParam("adjunct_mark_size", getReqParamString("adjunct_mark_size"));// 客供物料单号

		return param;

	}

	public ZCHttpReqParam getCSUOrderManPBC() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		return param;
	}

	public ZCHttpReqParam getCSUOrderWomanPBYX() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		return param;
	}

	public ZCHttpReqParam getCSUOrderWomanPBC() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		return param;
	}

	public ZCHttpReqParam getCSUOrderDesign() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		param.addParam("design_code", getReqParamString("design_code"));
		return param;
	}

	public ZCHttpReqParam getCSAOrderManPBYX() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		param.addParam("customer_tel_target", getReqParamString("customer_tel_target"));
		return param;
	}

	public ZCHttpReqParam getCSAOrderManPBC() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		param.addParam("customer_tel_target", getReqParamString("customer_tel_target"));
		return param;
	}

	public ZCHttpReqParam getCSAOrderWomanPBYX() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		param.addParam("customer_tel_target", getReqParamString("customer_tel_target"));
		return param;
	}

	public ZCHttpReqParam getCSAOrderWomanPBC() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		param.addParam("customer_tel_target", getReqParamString("customer_tel_target"));
		return param;
	}

	public ZCHttpReqParam getCSAOrderSubcontract() {
		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("customer_address", getReqParamString("customer_address"));
		param.addParam("customer_name", getReqParamString("customer_name"));
		param.addParam("customer_tel", getReqParamString("customer_tel"));
		param.addParam("customer_tel_target", getReqParamString("customer_tel_target"));
		param.addParam("customer_tips", getReqParamString("customer_tips"));
		param.addParam("design_code", getReqParamString("design_code"));
		param.addParam("operator_id", getReqParamString("operator_id"));
		param.addParam("operator_name", getReqParamString("operator_name"));
		param.addParam("prices_desc", getReqParamString("prices_desc"));
		param.addParam("prices_now", getReqParamString("prices_now"));
		param.addParam("prices_system", getReqParamString("prices_system"));
		if ("计算价格".equals("subcontract_price_type")) {
			param.addParam("subcontract_fabric_unit_cost", getReqParamString("subcontract_fabric_unit_cost"));
		} else {
			param.addParam("subcontract_fabric_unit_cost", "0");
		}
		param.addParam("uskin_code", getReqParamString("uskin_code"));
		return param;
	}

	public ZCHttpReqParam getCSAOrderDesign() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		param.addParam("customer_tel_target", getReqParamString("customer_tel_target"));
		param.addParam("design_code", getReqParamString("design_code"));
		return param;
	}

	private String chooseNumber(String numberString) {
		String numberInt = "";
		switch (numberString) {
		case "11-30":
			numberInt = "11";
			break;
		case "31-100":
			numberInt = "31";
			break;
		case "101-500":
			numberInt = "101";
			break;
		case "501-1500":
			numberInt = "501";
			break;
		case "10-15":
			numberInt = "10";
			break;
		case "15-25":
			numberInt = "15";
			break;
		default:
			numberInt = numberString;
			break;
		}

		return numberInt;

	}

	private String getEmbroideryPackage() {

		JSONArray embroidery_list = new JSONArray();

		JSONObject embroidery_section_1 = null;
		String embroidery_section_1_type = getReqParamString("embroidery_section_1_type");
		if ("char".equals(embroidery_section_1_type)) {
			embroidery_section_1 = new JSONObject();
			embroidery_section_1.put("type", "char");
			embroidery_section_1.put("font", getReqParamString("embroidery_section_1_char_font"));
			embroidery_section_1.put("size", getReqParamString("embroidery_section_1_char_size"));
			embroidery_section_1.put("location", getReqParamString("embroidery_section_1_char_location"));
			embroidery_section_1.put("color", getReqParamString("embroidery_section_1_char_color"));
			embroidery_section_1.put("content", getReqParamString("embroidery_section_1_char_content"));
		}
		if ("pic".equals(embroidery_section_1_type)) {
			embroidery_section_1 = new JSONObject();
			embroidery_section_1.put("type", "pic");
			embroidery_section_1.put("font", getReqParamString("embroidery_section_1_pic_font"));
			embroidery_section_1.put("size", getReqParamString("embroidery_section_1_pic_size"));
			embroidery_section_1.put("location", getReqParamString("embroidery_section_1_pic_location"));
			embroidery_section_1.put("color", getReqParamString("embroidery_section_1_pic_color"));
			embroidery_section_1.put("content", getReqParamString("embroidery_section_1_pic_content"));
		}

		JSONObject embroidery_section_2 = null;
		String embroidery_section_2_type = getReqParamString("embroidery_section_2_type");
		if ("char".equals(embroidery_section_2_type)) {
			embroidery_section_2 = new JSONObject();
			embroidery_section_2.put("type", "char");
			embroidery_section_2.put("font", getReqParamString("embroidery_section_2_char_font"));
			embroidery_section_2.put("size", getReqParamString("embroidery_section_2_char_size"));
			embroidery_section_2.put("location", getReqParamString("embroidery_section_2_char_location"));
			embroidery_section_2.put("color", getReqParamString("embroidery_section_2_char_color"));
			embroidery_section_2.put("content", getReqParamString("embroidery_section_2_char_content"));
		}
		if ("pic".equals(embroidery_section_2_type)) {
			embroidery_section_2 = new JSONObject();
			embroidery_section_2.put("type", "pic");
			embroidery_section_2.put("font", getReqParamString("embroidery_section_2_pic_font"));
			embroidery_section_2.put("size", getReqParamString("embroidery_section_2_pic_size"));
			embroidery_section_2.put("location", getReqParamString("embroidery_section_2_pic_location"));
			embroidery_section_2.put("color", getReqParamString("embroidery_section_2_pic_color"));
			embroidery_section_2.put("content", getReqParamString("embroidery_section_2_pic_content"));
		}

		if (embroidery_section_1 != null) {
			embroidery_list.add(embroidery_section_1);
		}
		if (embroidery_section_2 != null) {
			embroidery_list.add(embroidery_section_2);
		}

		return embroidery_list.toString();

	}

	public ZCHttpReqParam getCSUPriceManPBYX() {

		String customer_tel_target = getReqParamString("customer_tel_target");
		String uskin_code = getReqParamString("uskin_code").toUpperCase();
		String kouzi = getReqParamString("kouzi");
		String tailor_type = getReqParamString("tailor_type");

		String order_delivery_time = getReqParamString("order_delivery_time");
		String order_processing_cost = getReqParamString("order_processing_cost");
		String order_mtm_type = getReqParamString("order_mtm_type");
		String order_production_count = getReqParamString("order_production_count");
		String LZX_11_FOR_CHAR_SWITCH = getReqParamString("LZX_11_FOR_CHAR_SWITCH");
		String LZX_11_FOR_PIC_SWITCH = getReqParamString("LZX_11_FOR_PIC_SWITCH");

		String special_technics = getReqParamString("special_technics");

		String LZX_01 = getReqParamString("LZX_01");// 领型
		String LZX_02 = getReqParamString("LZX_02");// 袖头,短袖头
		String LZX_03 = getReqParamString("LZX_03");// 门襟
		String LZX_04 = getReqParamString("LZX_04");// 口袋
		String weizhi_zhidai = getReqParamString("weizhi_zhidai");// 织带位置
		String LZX_26 = getReqParamString("LZX_26");// 底摆配布
		String cixiu_kegong_num = getReqParamString("cixiu_kegong_num");// 客供图案刺绣数量

		ZCHttpReqParam param = new ZCHttpReqParam();

		param.addParam("customer_tel_target", customer_tel_target);
		param.addParam("uskin_code", uskin_code);
		param.addParam("kouzi", kouzi);
		param.addParam("tailor_type", tailor_type);

		param.addParam("order_delivery_time", chooseNumber(order_delivery_time));
		param.addParam("order_processing_cost", order_processing_cost);
		param.addParam("order_mtm_type", order_mtm_type);
		param.addParam("order_production_count", chooseNumber(order_production_count));
		param.addParam("order_production_count_real", getReqParamString("order_production_count_real"));
		param.addParam("LZX_11_FOR_CHAR_SWITCH", LZX_11_FOR_CHAR_SWITCH);
		param.addParam("LZX_11_FOR_PIC_SWITCH", LZX_11_FOR_PIC_SWITCH);

		param.addParam("special_technics", special_technics);

		param.addParam("LZX_01", LZX_01);// 领型
		param.addParam("LZX_02", LZX_02);// 袖头,短袖头
		param.addParam("LZX_03", LZX_03);// 门襟
		param.addParam("LZX_04", LZX_04);// 口袋
		param.addParam("weizhi_zhidai", weizhi_zhidai);// 织带位置
		param.addParam("LZX_26", LZX_26);// 底摆配布
		param.addParam("cixiu_kegong_num", cixiu_kegong_num);// 客供图案刺绣数量

		param.addParam("adjunct_mark_size", getReqParamString("adjunct_mark_size"));// 客供物料单号

		return param;

	}

	public ZCHttpReqParam getCSUPriceManPBC() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		return param;
	}

	public ZCHttpReqParam getCSUPriceWomanPBYX() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		return param;
	}

	public ZCHttpReqParam getCSUPriceWomanPBC() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		return param;
	}

	public ZCHttpReqParam getCSUPriceDesign() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		param.addParam("design_code", getReqParamString("design_code"));
		return param;
	}

	public ZCHttpReqParam getCSAPriceManPBYX() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		return param;
	}

	public ZCHttpReqParam getCSAPriceManPBC() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		return param;
	}

	public ZCHttpReqParam getCSAPriceWomanPBYX() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		return param;
	}

	public ZCHttpReqParam getCSAPriceWomanPBC() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		return param;
	}

	public ZCHttpReqParam getCSAPriceDesign() {
		ZCHttpReqParam param = getCSUOrderManPBYX();
		param.addParam("design_code", getReqParamString("design_code"));
		return param;
	}

	// 查询工厂订单
	public ZCHttpReqParam getCSUGetOrder() {
		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("operator_id", getReqParamString("operator_id"));
		param.addParam("FactoryID", getReqParamString("FactoryID"));
		return param;
	}

	// 提交返修订单
	public ZCHttpReqParam getCSURepair() {
		ZCHttpReqParam param = new ZCHttpReqParam();

		String Types = "";
		String[] TypesArray = getReqParamStrings("Type");
		for (int i = 0; i < TypesArray.length; i++) {
			Types += TypesArray[i];
			if (i < TypesArray.length - 1) {
				Types += ",";
			}
		}
		param.addParam("Type", Types);
		param.addParam("FactoryID", getReqParamString("FactoryID"));
		param.addParam("ExpressNO", getReqParamString("ExpressNO"));
		param.addParam("Tips", getReqParamString("Tips"));
		param.addParam("Price", getReqParamString("prices_system"));
		param.addParam("operator_id", getReqParamString("operator_id"));
		return param;
	}

	// 提交样例订单
	public ZCHttpReqParam getCSUSample() {
		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addParam("operator_id", getReqParamString("operator_id"));
		param.addParam("customer_name", getReqParamString("customer_name"));// 客供物料单号
		param.addParam("ExpressNO", getReqParamString("ExpressNO"));// 客供物料单号
		param.addParam("customer_tips", getReqParamString("customer_tips"));// 客供物料单号
		param.addParam("tech_sample_type", getReqParamString("tech_sample_type"));
		param.addParam("tech_sample_code", getReqParamString("tech_sample_code"));
		param.addParam("uskin_code", getReqParamString("uskin_code"));
		param.addParam("measure", getReqParamString("measure"));
		param.addParam("prices_now", getReqParamString("prices_now"));
		param.addParam("fuck_eclipse", getReqParamString("mmp,打包出故障了"));
		return param;
	}

}