package com.ltyx.sca.action.plugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;
import com.zc.support.service.TextLogHelper;

import net.sf.json.JSONObject;

public class MoudleCSUGetOrder extends ZCBaseActionSupportPlugin {

	public MoudleCSUGetOrder(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		if (getReqParamString("FactoryID").trim().length() == 0) {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "请在【原订单号】处填写正确的【生产单号】";
			return false;
		}
		if (getReqParamString("name_c").trim().length() == 0) {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "请在【修改项目】处选择所需要的服务";
			return false;
		}
		if (getReqParamString("Tips").trim().length() == 0) {
			ERRCODE = "0";
			ERRDESC = "fail";
			data = "请在【生产备注（工艺修改说明）】处填写详细的修改描述";
			return false;
		}
		
		MoudleCSParamUtil paramUtil = new MoudleCSParamUtil(request);
		ZCHttpReqParam param = paramUtil.getCSUGetOrder();

		String httpResp = ZCHttpReqSender.sendGet(ConfigHelperURL.Url_customshop_get_factory_order.getUrl(), param, TextLogHelper.Type.USKIN_USER_PRICE_NSRC);

		Log.Nano.tag(ConfigHelperURL.Url_customshop_get_factory_order.getDesc() + "Resp From EC", httpResp);

		JSONObject jsonHttpResp;
		String jsonERRCODE;
		String jsonERRDESC;
		String jsonData;

		try {
			jsonHttpResp = JSONObject.fromObject(httpResp);
			jsonERRCODE = jsonHttpResp.getString("ERRCODE");
			jsonERRDESC = jsonHttpResp.getString("ERRDESC");
			jsonData = jsonHttpResp.getString("data");
		} catch (Exception e) {
			// TODO: handle exception
			Log.Nano.tag("EC服务器响应错误，结构异常", httpResp);
			jsonHttpResp = null;
			jsonERRCODE = "0";
			jsonERRDESC = "fail";
			jsonData = "EC服务器响应错误，结构异常";
		}

		if ("0".equals(jsonERRCODE) && "succ".equals(jsonERRDESC)) {

			try {

				ERRCODE = "0";
				ERRDESC = "succ";
				data = getReqParamString("price_c");
				TIP1 = "";
				TIP1 += "原订单号：" + getReqParamString("FactoryID") + "<br/>";
				TIP1 += "原订单穿衣人：" + jsonHttpResp.getJSONObject("data").getString("CustomName") + "<br/>";
				TIP1 += "原订单主面料：" + jsonHttpResp.getJSONObject("data").getString("UskinCode") + "<br/>";
				TIP1 += "修改项目：" + getReqParamString("name_c") + "<br/>";
				TIP1 += "生产备注（工艺修改说明）：" + getReqParamString("Tips") + "<br/>";
				TIP1 += "工期：" + getReqParamString("time_c") + "个工作日" + "<br/>";

			} catch (Exception e) {
				// TODO: handle exception
				Log.Nano.tag("EC服务器响应错误，第二级格式异常", httpResp);

				e.printStackTrace();

				ERRCODE = "0";
				ERRDESC = "fail";
				data = "EC服务器响应错误，第二级格式异常";
			}

			return true;

		} else {

			ERRCODE = "0";
			ERRDESC = "fail";
			data = jsonData;

			return false;
		}
	}

}
