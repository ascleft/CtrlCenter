package com.ltyx.tailor.actionplugin;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.zc.web.base.doman.ZCActionPluginBase;
import com.zc.web.base.doman.ZCHttpParam;
import com.zc.web.base.service.CCHttpReq;
import com.zc.web.base.service.Log;
import com.zc.web.base.service.URLConfigHelper;

public class MoudleSubmitEC extends ZCActionPluginBase {

	public MoudleSubmitEC(HttpServletRequest req) {
		this.req = req;
	}

	public boolean doJobs() {

		// uskin_code_type:USKIN/PBC���ֿ͹�����

		ZCHttpParam param = new ZCHttpParam();

		// ��Ʒ����
		param.addParam("quantity", "1");
		// �ܽ��
		param.addParam("total_amount", getString("prices"));
		// ����ID
		param.addParam("shipping_dlytype", "");
		// �Ƿ񿪷�Ʊ��trueΪ����Ʊ��falseΪ������
		param.addParam("is_tax", "");
		// ��Ʊ̧ͷ
		param.addParam("tax_title", "");
		// ��Ʊ���ݣ�1Ϊ��װ��2��Ʒ��ϸ
		param.addParam("tax_content", "");
		// ��Ʊ���ͣ� 1���ˣ�2��ҵ
		param.addParam("tax_type", "");
		// �ջ�������
		param.addParam("consignee_name", getString("customer_name"));
		// �ջ�����ϸ��ַ
		param.addParam("consignee_addr", getString("customer_address"));
		// �ջ����ֻ�
		param.addParam("consignee_mobile", getString("customer_tel"));
		// ��Ա�ֻ���/��Ա����/��Ա��
		param.addParam("member_mobile", getString("customer_tel"));
		// ����������
		param.addParam("customer_name", getString("customer_name") + "4j0u0n8y9i00726" + getString("customer_tel_target"));
		// �������ֻ�
		param.addParam("customer_mobile", getString("customer_tel"));
		// ������id
		param.addParam("operator_id", getString("operator_id"));
		// ��װ
		param.addParam("gift_box_ids", getString("YX_09"));
		// ���屸ע
		param.addParam("measurement_remark", "");
		// ���屸ע
		param.addParam("special_remark", getString("customer_tips"));
		// ��������
		if ("PBC".equals(getString("uskin_code_type"))) {
			param.addParam("memo", getString("customer_tips") + " ����������" + getString("uskin_code_length") + // ��������
					" ������ɫ��" + getString("uskin_code_color") + // ������ɫ
					" ���ϻ��ͣ�" + getString("uskin_code_pattern")// ��������
			);
		} else {
			param.addParam("memo", getString("customer_tips"));// ��������
		}
		// USKIN����
		param.addParam("bn", getString("uskin_code"));
		// �Ƽ���ʽID
		param.addParam("design_style_id", getString("design_style_id"));
		// ��������������
		param.addParam("data_type", getString("measure_type"));
		// ��� ����ʱ���ã����ÿգ�
		param.addParam("height", getString("height"));
		// ���� ����ʱ���ã����ÿգ�
		param.addParam("weight", getString("weight"));
		// ��Χ
		param.addParam("neck_around", getString("ling_wei"));
		// ��Χ
		param.addParam("chest_around", getString("xiong_wei"));
		// ����Χ
		param.addParam("mid_waist_around", getString("yao_wei"));
		// ��Χ
		param.addParam("waist_around", getString("yao_wei"));
		// ��Χ
		param.addParam("hip_around", getString("dibian"));
		// ���³�
		param.addParam("back_length", getString("houshen_chang"));
		// ���³�|�⴩
		param.addParam("outside_back_length", getString("houshen_chang"));
		// ���
		param.addParam("shoulder_width", getString("jian_kuan"));
		// ���䳤
		param.addParam("long_sleeve_length", getString("xiu_chang_zuo"));
		// ����Χ
		param.addParam("left_wrist_around", getString("xiutouchang_zuo"));
		// ����Χ
		param.addParam("right_wrist_around", getString("xiutouchang_you"));
		// ��Χ
		param.addParam("bicep_around", getString("xiu_fei"));
		// С��Χ
		param.addParam("forearm_around", getString("xiuzhou_fei"));
		// ǰ��
		param.addParam("front_length", getString("qianshen_chang"));
		// �ؿ�
		param.addParam("chest_width", getString("qianxiong_kuan"));
		// �󱳿�
		param.addParam("back_width", getString("houbei_kuan"));
		// ���䳤
		param.addParam("short_sleeve_length", getString("duanxiu_chang"));
		// �����Χ
		param.addParam("short_sleeve_opening_around", getString("duanxiu_kouwei_zuo"));
		// �ظߣ���ʱ���ã����ÿգ�
		param.addParam("chest_height", getString("chest_height"));
		// �ؾࣨ��ʱ���ã����ÿգ�
		param.addParam("chest_distance", getString("chest_distance"));
		// ����������2015SS-001
		param.addParam("style_name", getString("style_name"));
		// ��ʽ����0�����1�Ƽ��2���ɴ���
		param.addParam("style_type", getString("style_type"));
		// ���ɶȣ�24����25���� ���ɶ�
		param.addParam("easy_ids", "25");
		// �����䣺26���䣬27����
		param.addParam("sleeve_ids", getString("tailor_type"));
		// ���塢��װ����
		param.addParam("garment_type", getString("garment_type"));
		// ���Ϳ�ʽ
		param.addParam("collocation_ids_collar", getString("LZX_01"));
		// ��ڿ�ʽ
		param.addParam("collocation_ids_cuff", getString("LZX_02"));
		// �Ž��ʽ
		param.addParam("collocation_ids_frontfly", getString("LZX_03"));
		// �ڴ���ʽ
		param.addParam("position_ids_10", getString("LZX_04"));
		// �󱳿�ʽ----------------------------------------------------------------------------------------
		param.addParam("back_style", getString("LZX_17"));
		// Ŧ�ۿ�ʽ
		param.addParam("position_ids_11", getString("kouzi"));
		// ��ſ�ʽ
		param.addParam("position_ids_12", getString("lingcheng"));
		// ����ʽ
		param.addParam("position_ids_9", getString("YX_08"));
		// ����----------------------------------------------------------------------------------------
		param.addParam("open_wiring", getString("mingxian"));
		// ��칤��----------------------------------------------------------------------------------------
		param.addParam("side_sewing_process", getString("cefeng"));
		// Ƕ��----------------------------------------------------------------------------------------
		param.addParam("piping", getString("qiantiao"));
		// �Ĳ�----------------------------------------------------------------------------------------
		param.addParam("interlining", getString("chenbu"));
		// ��ɫ----------------------------------------------------------------------------------------
		{
			String peise = "";
			for (String temp_peise_weizhi : getStrings("peise_weizhi")) {
				peise += temp_peise_weizhi + "=" + getString("peise_bn") + ",";
			}
			param.addParam("other_fabric", peise);
		}
		// ����λ��
		param.addParam("embroidery_ids_3", getString("LZX_13_FOR_CHAR"));
		// ��������
		param.addParam("embroidery_ids_2", getString("LZX_11_CHAR_TYPE"));
		// ��������
		param.addParam("embroidery_ids_words", getString("LZX_11_CHAR_WORD"));
		// �����С----------------------------------------------------------------------------------------
		param.addParam("embroidery_size", getString("LZX_11_CHAR_SIZE"));
		// ������ɫ----------------------------------------------------------------------------------------
		param.addParam("embroidery_color", getString("LZX_11_CHAR_COLOR"));

		// ģ���ַ"http://61.50.122.58:8029/CtrlCenter/LTYX/Tailor/SubTailorEC.action"

		String httpResp = CCHttpReq.sendGet(URLConfigHelper.Url_SubTailor, param);
		Log.Nano.tag("Save Resp From EC", httpResp);

		JSONObject jsonHttpResp;
		String jsonERRCODE;
		String jsonERRDESC;
		String jsonData;
		try {
			jsonHttpResp = JSONObject.fromObject(httpResp);
			jsonERRCODE = jsonHttpResp.getString("ERRCODE");
			jsonERRDESC = jsonHttpResp.getString("ERRDESC");
			jsonData = "EC�����룺" + jsonERRCODE + " EC����������" + jsonERRDESC;
		} catch (Exception e) {
			// TODO: handle exception
			Log.Nano.tag("EC��������Ӧ����", httpResp);
			jsonERRCODE = "0";
			jsonERRDESC = "fail";
			jsonData = "EC��������Ӧ����";
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
