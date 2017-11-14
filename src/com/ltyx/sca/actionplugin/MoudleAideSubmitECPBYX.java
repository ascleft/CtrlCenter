package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;

public class MoudleAideSubmitECPBYX extends ZCBaseActionSupportPlugin {

	public MoudleAideSubmitECPBYX(HttpServletRequest req) {
		this.request = req;
	}

	public boolean doJobs() {

		ZCHttpReqParam param = new ZCHttpReqParam();

		param.addParam("LZX_01", getReqParamStringWithLog("LZX_01"));
		param.addParam("LZX_02", getReqParamStringWithLog("LZX_02"));
		param.addParam("LZX_03", getReqParamStringWithLog("LZX_03"));
		param.addParam("LZX_04", getReqParamStringWithLog("LZX_04"));
		param.addParam("LZX_06", getReqParamStringWithLog("LZX_06"));
		param.addParam("LZX_11_CHAR_COLOR", getReqParamStringWithLog("LZX_11_CHAR_COLOR"));
		param.addParam("LZX_11_CHAR_SIZE", getReqParamStringWithLog("LZX_11_CHAR_SIZE"));
		param.addParam("LZX_11_CHAR_TYPE", getReqParamStringWithLog("LZX_11_CHAR_TYPE"));
		param.addParam("LZX_11_CHAR_WORD", getReqParamStringWithLog("LZX_11_CHAR_WORD"));
		param.addParam("LZX_11_FOR_CHAR_SWITCH", getReqParamStringWithLog("LZX_11_FOR_CHAR_SWITCH"));
		param.addParam("LZX_11_FOR_PIC_SWITCH", getReqParamStringWithLog("LZX_11_FOR_PIC_SWITCH"));
		param.addParam("LZX_11_PIC_COLOR", getReqParamStringWithLog("LZX_11_PIC_COLOR"));
		param.addParam("LZX_11_PIC_NUM", getReqParamStringWithLog("LZX_11_PIC_NUM"));
		param.addParam("LZX_11_PIC_SIZE", getReqParamStringWithLog("LZX_11_PIC_SIZE"));
		param.addParam("LZX_11_PIC_TYPE", getReqParamStringWithLog("LZX_11_PIC_TYPE"));
		param.addParam("LZX_120", getReqParamStringWithLog("LZX_120"));
		param.addParam("LZX_13_FOR_CHAR", getReqParamStringWithLog("LZX_13_FOR_CHAR"));
		param.addParam("LZX_13_FOR_PIC", getReqParamStringWithLog("LZX_13_FOR_PIC"));
		param.addParam("LZX_17", getReqParamStringWithLog("LZX_17"));
		param.addParam("LZX_26", getReqParamStringWithLog("LZX_26"));
		param.addParam("YX_08", getReqParamStringWithLog("YX_08"));
		param.addParam("YX_09", getReqParamStringWithLog("YX_09"));
		param.addParam("cefeng", getReqParamStringWithLog("cefeng"));
		param.addParam("chenbu", getReqParamStringWithLog("chenbu"));
		param.addParam("customer_address", getReqParamStringWithLog("customer_address"));
		param.addParam("customer_name", getReqParamStringWithLog("customer_name"));
		param.addParam("customer_tel", getReqParamStringWithLog("customer_tel"));
		param.addParam("customer_tel_target", getReqParamStringWithLog("customer_tel_target"));
		param.addParam("customer_tips", getReqParamStringWithLog("customer_tips"));
		param.addParam("easy_type", getReqParamStringWithLog("easy_type"));
		param.addParam("kouzi", getReqParamStringWithLog("kouzi"));
		param.addParam("line_color_location_1", getReqParamStringWithLog("line_color_location_1"));
		param.addParam("line_color_location_2", getReqParamStringWithLog("line_color_location_2"));
		param.addParam("line_color_location_3", getReqParamStringWithLog("line_color_location_3"));
		param.addParam("line_color_location_4", getReqParamStringWithLog("line_color_location_4"));
		param.addParam("lingcheng", getReqParamStringWithLog("lingcheng"));
		param.addParam("mingxian", getReqParamStringWithLog("mingxian"));
		param.addParam("operator_id", getReqParamStringWithLog("operator_id"));
		param.addParam("operator_name", getReqParamStringWithLog("operator_name"));
		param.addParam("prices_desc", getReqParamStringWithLog("prices_desc"));
		param.addParam("prices_now", getReqParamStringWithLog("prices_now"));
		param.addParam("prices_system", getReqParamStringWithLog("prices_system"));
		param.addParam("qiantiao", getReqParamStringWithLog("qiantiao"));
		param.addParam("tailor_type", getReqParamStringWithLog("tailor_type"));
		param.addParam("uskin_code", getReqParamStringWithLog("uskin_code"));
		param.addParam("uskin_code_2", getReqParamStringWithLog("uskin_code_2"));
		param.addParam("zhidai", getReqParamStringWithLog("zhidai"));

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_aide_add_cart_pbyx, param);
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
