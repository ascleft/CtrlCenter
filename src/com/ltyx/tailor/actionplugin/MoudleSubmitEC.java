package com.ltyx.tailor.actionplugin;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.zc.web.support.config.ConfigHelperURL;
import com.zc.web.support.doman.ZCBaseActionSupportPlugin;
import com.zc.web.support.link.ZCHttpReqParam;
import com.zc.web.support.link.ZCHttpReq;
import com.zc.web.support.service.Log;

public class MoudleSubmitEC extends ZCBaseActionSupportPlugin {

	public MoudleSubmitEC(HttpServletRequest req) {
		this.request = req;
	}

	public boolean doJobs() {

		// uskin_code_type:USKIN/PBC区分客供面料

		ZCHttpReqParam param = new ZCHttpReqParam();

		// 商品数量
		param.addParam("quantity", "1");
		// 总金额
		param.addParam("total_amount", getReqParamString("prices"));
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
		param.addParam("consignee_name", getReqParamString("customer_name"));
		// 收货人详细地址
		param.addParam("consignee_addr", getReqParamString("customer_address"));
		// 收货人手机
		param.addParam("consignee_mobile", getReqParamString("customer_tel"));
		// 会员手机号/会员邮箱/会员名
		param.addParam("member_mobile", getReqParamString("customer_tel"));
		// 被量人姓名
		param.addParam("customer_name", getReqParamString("customer_name") + "4j0u0n8y9i00726" + getReqParamString("customer_tel_target"));
		// 被量人手机
		param.addParam("customer_mobile", getReqParamString("customer_tel"));
		// 操作人id
		param.addParam("operator_id", getReqParamString("operator_id"));
		// 包装
		param.addParam("gift_box_ids", getReqParamString("YX_09"));
		// 量体备注
		param.addParam("measurement_remark", "");
		// 特体备注
		param.addParam("special_remark", getReqParamString("customer_tips"));
		// 订单附言
		if ("PBC".equals(getReqParamString("uskin_code_type"))) {
			param.addParam("memo", getReqParamString("customer_tips") + " 面料用量：" + getReqParamString("uskin_code_length") + // 面料用量
					" 面料颜色：" + getReqParamString("uskin_code_color") + // 面料颜色
					" 面料花型：" + getReqParamString("uskin_code_pattern")// 订单花型
			);
		} else {
			param.addParam("memo", getReqParamString("customer_tips"));// 订单附言
		}
		// USKIN编码
		param.addParam("bn", getReqParamString("uskin_code"));
		// 推荐款式ID
		param.addParam("design_style_id", getReqParamString("design_style_id"));
		// 量体体数据类型
		param.addParam("data_type", getReqParamString("measure_type"));
		// 身高 （暂时不用，可置空）
		param.addParam("height", getReqParamString("height"));
		// 体重 （暂时不用，可置空）
		param.addParam("weight", getReqParamString("weight"));
		// 领围
		param.addParam("neck_around", getReqParamString("ling_wei"));
		// 胸围
		param.addParam("chest_around", getReqParamString("xiong_wei"));
		// 中腰围
		param.addParam("mid_waist_around", getReqParamString("yao_wei"));
		// 腰围
		param.addParam("waist_around", getReqParamString("yao_wei"));
		// 臀围
		param.addParam("hip_around", getReqParamString("dibian"));
		// 后衣长
		param.addParam("back_length", getReqParamString("houshen_chang"));
		// 后衣长|外穿
		param.addParam("outside_back_length", getReqParamString("houshen_chang"));
		// 肩宽
		param.addParam("shoulder_width", getReqParamString("jian_kuan"));
		// 长袖长
		param.addParam("long_sleeve_length", getReqParamString("xiu_chang_zuo"));
		// 左腕围
		param.addParam("left_wrist_around", getReqParamString("xiutouchang_zuo"));
		// 右腕围
		param.addParam("right_wrist_around", getReqParamString("xiutouchang_you"));
		// 臂围
		param.addParam("bicep_around", getReqParamString("xiu_fei"));
		// 小臂围
		param.addParam("forearm_around", getReqParamString("xiuzhou_fei"));
		// 前身长
		param.addParam("front_length", getReqParamString("qianshen_chang"));
		// 胸宽
		param.addParam("chest_width", getReqParamString("qianxiong_kuan"));
		// 后背宽
		param.addParam("back_width", getReqParamString("houbei_kuan"));
		// 短袖长
		param.addParam("short_sleeve_length", getReqParamString("duanxiu_chang"));
		// 短袖口围
		param.addParam("short_sleeve_opening_around", getReqParamString("duanxiu_kouwei_zuo"));
		// 胸高（暂时不用，可置空）
		param.addParam("chest_height", getReqParamString("chest_height"));
		// 胸距（暂时不用，可置空）
		param.addParam("chest_distance", getReqParamString("chest_distance"));
		// 搭配名称例2015SS-001
		param.addParam("style_name", getReqParamString("style_name"));
		// 款式类型0基本款，1推荐款，2自由搭配
		param.addParam("style_type", getReqParamString("style_type"));
		// 宽松度：24修身，25舒适 宽松度
		param.addParam("easy_ids", "25");
		// 长短袖：26长袖，27短袖
		param.addParam("sleeve_ids", getReqParamString("tailor_type"));
		// 量体、服装类型
		param.addParam("garment_type", getReqParamString("garment_type"));
		// 领型款式
		param.addParam("collocation_ids_collar", getReqParamString("LZX_01"));
		// 袖口款式
		param.addParam("collocation_ids_cuff", getReqParamString("LZX_02"));
		// 门襟款式
		param.addParam("collocation_ids_frontfly", getReqParamString("LZX_03"));
		// 口袋款式
		param.addParam("position_ids_10", getReqParamString("LZX_04"));
		// 后背款式----------------------------------------------------------------------------------------
		param.addParam("back_style", getReqParamString("LZX_17"));
		// 纽扣款式
		param.addParam("position_ids_11", getReqParamString("kouzi"));
		// 领撑款式
		param.addParam("position_ids_12", getReqParamString("lingcheng"));
		// 领标款式
		param.addParam("position_ids_9", getReqParamString("YX_08"));
		// 明线----------------------------------------------------------------------------------------
		param.addParam("open_wiring", getReqParamString("mingxian"));
		// 侧缝工艺----------------------------------------------------------------------------------------
		param.addParam("side_sewing_process", getReqParamString("cefeng"));
		// 嵌条----------------------------------------------------------------------------------------
		param.addParam("piping", getReqParamString("qiantiao"));
		// 衬布----------------------------------------------------------------------------------------
		param.addParam("interlining", getReqParamString("chenbu"));
		// 配色----------------------------------------------------------------------------------------
		{
			String peise = "";
			for (String temp_peise_weizhi : getReqParamStrings("peise_weizhi")) {
				peise += temp_peise_weizhi + "=" + getReqParamString("peise_bn") + ",";
			}
			param.addParam("other_fabric", peise);
		}
		// 绣字位置
		param.addParam("embroidery_ids_3", getReqParamString("LZX_13_FOR_CHAR"));
		// 绣字字体
		param.addParam("embroidery_ids_2", getReqParamString("LZX_11_CHAR_TYPE"));
		// 绣字文字
		param.addParam("embroidery_ids_words", getReqParamString("LZX_11_CHAR_WORD"));
		// 刺绣大小----------------------------------------------------------------------------------------
		param.addParam("embroidery_size", getReqParamString("LZX_11_CHAR_SIZE"));
		// 刺绣颜色----------------------------------------------------------------------------------------
		param.addParam("embroidery_color", getReqParamString("LZX_11_CHAR_COLOR"));

		// 模拟地址"http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/SubTailorEC.action"

		String httpResp = ZCHttpReq.sendGet(ConfigHelperURL.Url_SubTailor, param);
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
