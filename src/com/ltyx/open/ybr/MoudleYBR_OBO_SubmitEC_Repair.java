package com.ltyx.open.ybr;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;
import com.zc.support.service.TextLogHelper;

import net.sf.json.JSONObject;

public class MoudleYBR_OBO_SubmitEC_Repair extends ZCBaseActionSupportPlugin {

	public MoudleYBR_OBO_SubmitEC_Repair(HttpServletRequest req) {
		this.request = req;
	}

	public boolean doJobs() {

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

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshop_add_cart_repair.getUrl(), param, TextLogHelper.Type.USKIN_YBR_NSRC);
		Log.Nano.tag(ConfigHelperURL.Url_customshop_add_cart_repair.getDesc() + "Resp From EC", httpResp);

		JSONObject jsonHttpResp;
		String jsonERRCODE;
		String jsonERRDESC;
		String jsonData;
		try {
			jsonHttpResp = JSONObject.fromObject(httpResp);
			jsonERRCODE = jsonHttpResp.getString("ERRCODE");
			jsonERRDESC = jsonHttpResp.getString("ERRDESC");
			jsonData = jsonHttpResp.getString("data");;
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
