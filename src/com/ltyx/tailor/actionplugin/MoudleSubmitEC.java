package com.ltyx.tailor.actionplugin;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.zc.web.base.doman.ZCActionPluginBase;
import com.zc.web.base.doman.ZCHttpParam;
import com.zc.web.base.service.ZCHttpReq;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.URLConfigHelper;

public class MoudleSubmitEC extends ZCActionPluginBase {

	public MoudleSubmitEC(HttpServletRequest req) {
		this.req = req;
	}

	public boolean doJobs() {

		// uskin_code_type:USKIN/PBC区分客供面料

		ZCHttpParam param = new ZCHttpParam();

		// 商品数量
		param.addParam("quantity", "1");
		// 总金额
		param.addParam("total_amount", getString("prices"));
		// 配送ID
		param.addParam("shipping_dlytype", "");
		// 是否开发票：true为开发票，false为不开发
		param.addParam("is_tax", "");
		// 发票抬头
		param.addParam("tax_title", "");
		// 发票内容：1为服装，2商品明细
		param.addParam("tax_content", "");
		// 发票类型： 1个人，2企业
		param.addParam("tax_type", "");
		// 收货人姓名
		param.addParam("consignee_name", getString("customer_name"));
		// 收货人详细地址
		param.addParam("consignee_addr", getString("customer_address"));
		// 收货人手机
		param.addParam("consignee_mobile", getString("customer_tel"));
		// 会员手机号/会员邮箱/会员名
		param.addParam("member_mobile", getString("customer_tel"));
		// 被量人姓名
		param.addParam("customer_name", getString("customer_name") + "4j0u0n8y9i00726" + getString("customer_tel_target"));
		// 被量人手机
		param.addParam("customer_mobile", getString("customer_tel"));
		// 操作人id
		param.addParam("operator_id", getString("operator_id"));
		// 包装
		param.addParam("gift_box_ids", getString("YX_09"));
		// 量体备注
		param.addParam("measurement_remark", "");
		// 特体备注
		param.addParam("special_remark", getString("customer_tips"));
		// 订单附言
		if ("PBC".equals(getString("uskin_code_type"))) {
			param.addParam("memo", getString("customer_tips") + " 面料用量：" + getString("uskin_code_length") + // 面料用量
					" 面料颜色：" + getString("uskin_code_color") + // 面料颜色
					" 面料花型：" + getString("uskin_code_pattern")// 订单花型
			);
		} else {
			param.addParam("memo", getString("customer_tips"));// 订单附言
		}
		// USKIN编码
		param.addParam("bn", getString("uskin_code"));
		// 推荐款式ID
		param.addParam("design_style_id", getString("design_style_id"));
		// 量体体数据类型
		param.addParam("data_type", getString("measure_type"));
		// 身高 （暂时不用，可置空）
		param.addParam("height", getString("height"));
		// 体重 （暂时不用，可置空）
		param.addParam("weight", getString("weight"));
		// 领围
		param.addParam("neck_around", getString("ling_wei"));
		// 胸围
		param.addParam("chest_around", getString("xiong_wei"));
		// 中腰围
		param.addParam("mid_waist_around", getString("yao_wei"));
		// 腰围
		param.addParam("waist_around", getString("yao_wei"));
		// 臀围
		param.addParam("hip_around", getString("dibian"));
		// 后衣长
		param.addParam("back_length", getString("houshen_chang"));
		// 后衣长|外穿
		param.addParam("outside_back_length", getString("houshen_chang"));
		// 肩宽
		param.addParam("shoulder_width", getString("jian_kuan"));
		// 长袖长
		param.addParam("long_sleeve_length", getString("xiu_chang_zuo"));
		// 左腕围
		param.addParam("left_wrist_around", getString("xiutouchang_zuo"));
		// 右腕围
		param.addParam("right_wrist_around", getString("xiutouchang_you"));
		// 臂围
		param.addParam("bicep_around", getString("xiu_fei"));
		// 小臂围
		param.addParam("forearm_around", getString("xiuzhou_fei"));
		// 前身长
		param.addParam("front_length", getString("qianshen_chang"));
		// 胸宽
		param.addParam("chest_width", getString("qianxiong_kuan"));
		// 后背宽
		param.addParam("back_width", getString("houbei_kuan"));
		// 短袖长
		param.addParam("short_sleeve_length", getString("duanxiu_chang"));
		// 短袖口围
		param.addParam("short_sleeve_opening_around", getString("duanxiu_kouwei_zuo"));
		// 胸高（暂时不用，可置空）
		param.addParam("chest_height", getString("chest_height"));
		// 胸距（暂时不用，可置空）
		param.addParam("chest_distance", getString("chest_distance"));
		// 搭配名称例2015SS-001
		param.addParam("style_name", getString("style_name"));
		// 款式类型0基本款，1推荐款，2自由搭配
		param.addParam("style_type", getString("style_type"));
		// 宽松度：24修身，25舒适 宽松度
		param.addParam("easy_ids", "25");
		// 长短袖：26长袖，27短袖
		param.addParam("sleeve_ids", getString("tailor_type"));
		// 量体、服装类型
		param.addParam("garment_type", getString("garment_type"));
		// 领型款式
		param.addParam("collocation_ids_collar", getString("LZX_01"));
		// 袖口款式
		param.addParam("collocation_ids_cuff", getString("LZX_02"));
		// 门襟款式
		param.addParam("collocation_ids_frontfly", getString("LZX_03"));
		// 口袋款式
		param.addParam("position_ids_10", getString("LZX_04"));
		// 后背款式----------------------------------------------------------------------------------------
		param.addParam("back_style", getString("LZX_17"));
		// 纽扣款式
		param.addParam("position_ids_11", getString("kouzi"));
		// 领撑款式
		param.addParam("position_ids_12", getString("lingcheng"));
		// 领标款式
		param.addParam("position_ids_9", getString("YX_08"));
		// 明线----------------------------------------------------------------------------------------
		param.addParam("open_wiring", getString("mingxian"));
		// 侧缝工艺----------------------------------------------------------------------------------------
		param.addParam("side_sewing_process", getString("cefeng"));
		// 嵌条----------------------------------------------------------------------------------------
		param.addParam("piping", getString("qiantiao"));
		// 衬布----------------------------------------------------------------------------------------
		param.addParam("interlining", getString("chenbu"));
		// 配色----------------------------------------------------------------------------------------
		{
			String peise = "";
			for (String temp_peise_weizhi : getStrings("peise_weizhi")) {
				peise += temp_peise_weizhi + "=" + getString("peise_bn") + ",";
			}
			param.addParam("other_fabric", peise);
		}
		// 绣字位置
		param.addParam("embroidery_ids_3", getString("LZX_13_FOR_CHAR"));
		// 绣字字体
		param.addParam("embroidery_ids_2", getString("LZX_11_CHAR_TYPE"));
		// 绣字文字
		param.addParam("embroidery_ids_words", getString("LZX_11_CHAR_WORD"));
		// 刺绣大小----------------------------------------------------------------------------------------
		param.addParam("embroidery_size", getString("LZX_11_CHAR_SIZE"));
		// 刺绣颜色----------------------------------------------------------------------------------------
		param.addParam("embroidery_color", getString("LZX_11_CHAR_COLOR"));

		// 模拟地址"http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/SubTailorEC.action"

		String httpResp = ZCHttpReq.sendGet(URLConfigHelper.Url_SubTailor, param);
		Log.Nano.tag("Save Resp From EC", httpResp);

		JSONObject jsonHttpResp;
		String jsonERRCODE;
		String jsonERRDESC;
		String jsonData;
		try {
			jsonHttpResp = JSONObject.fromObject(httpResp);
			jsonERRCODE = jsonHttpResp.getString("ERRCODE");
			jsonERRDESC = jsonHttpResp.getString("ERRDESC");
			jsonData = "EC错误码：" + jsonERRCODE + " EC错误描述：" + jsonERRDESC;
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
