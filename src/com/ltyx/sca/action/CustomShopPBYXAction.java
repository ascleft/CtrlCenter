package com.ltyx.sca.action;

import com.ltyx.sca.actionplugin.MoudleCSCheckSummaryClothes;
import com.ltyx.sca.actionplugin.MoudleCSCheckUserInfo;
import com.ltyx.sca.actionplugin.MoudleCSGetPricePBYX;
import com.ltyx.sca.actionplugin.MoudleCSSubmitECPBYX;
import com.ltyx.sca.actionplugin.MoudleCheckMeasure;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;

public class CustomShopPBYXAction extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		session.setAttribute("QRurl", PageConfig.get_QR_url(request));

		session.setAttribute("menulist", PageConfig.get_menu_list());

		session.setAttribute("list_LZX_01", PageConfig.get_list_LZX_01());
		session.setAttribute("list_LZX_02", PageConfig.get_list_LZX_02());
		session.setAttribute("list_LZX_03", PageConfig.get_list_LZX_03());
		session.setAttribute("list_LZX_04", PageConfig.get_list_LZX_04());
		session.setAttribute("list_LZX_08", PageConfig.get_list_LZX_08());
		session.setAttribute("list_LZX_120", PageConfig.get_list_LZX_120());
		session.setAttribute("list_LZX_06", PageConfig.get_list_LZX_06());
		session.setAttribute("list_LZX_17", PageConfig.get_list_LZX_17());
		session.setAttribute("list_LZX_26", PageConfig.get_list_LZX_26());
		session.setAttribute("list_LZX_13", PageConfig.get_list_LZX_13());
		session.setAttribute("list_zhidai", PageConfig.get_list_zhidai());
		session.setAttribute("list_color", PageConfig.get_list_color());
		session.setAttribute("list_kouzi", PageConfig.get_list_kouzi());
		session.setAttribute("list_shenxing", PageConfig.get_list_shenxing());
		session.setAttribute("list_lingcheng", PageConfig.get_list_lingcheng());
		session.setAttribute("list_mingxian", PageConfig.get_list_mingxian());
		session.setAttribute("list_cefeng", PageConfig.get_list_cefeng());
		session.setAttribute("list_qiantiao", PageConfig.get_list_qiantiao());
		session.setAttribute("list_chenbu", PageConfig.get_list_chenbu());

		session.setAttribute("list_weizhi_zhidai", PageConfig.get_list_weizhi_zhidai());
		session.setAttribute("list_weizhi_peise", PageConfig.get_list_weizhi_peise());

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "定制店 衬衫 优纤面料 报价";

		ZCReqIntroGetter.showParams(methodName, request);

		doGetPrice();
		writeResp(methodName);

		return null;

	}

	public String submit() {

		init(true);
		String methodName = "定制店 衬衫 优纤面料 提交购物车";

		ZCReqIntroGetter.showParams(methodName, request);

		doSubmit();
		writeResp(methodName);

		return null;

	}

	public boolean doGetPrice() {

		MoudleCSGetPricePBYX moudle = new MoudleCSGetPricePBYX(request);
		moudle.doJobs();
		ERRCODE = moudle.getERRCODE();
		ERRDESC = moudle.getERRDESC();
		data = moudle.getData();
		return true;

	}

	public boolean doSubmit() {

		{
			MoudleCSCheckUserInfo moudle = new MoudleCSCheckUserInfo(request);
			if (!moudle.doJobs()) {
				addProgressFail("用户信息检测");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("用户信息检测");
		}

		{
			MoudleCSCheckSummaryClothes moudle = new MoudleCSCheckSummaryClothes(request);
			if (!moudle.doJobs()) {
				addProgressFail("订单摘要信息");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("订单摘要信息");
		}

		{
			MoudleCheckMeasure moudle = new MoudleCheckMeasure(request);
			if (!moudle.doJobs()) {
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("尺寸校验");
		}

		{
			MoudleCSSubmitECPBYX moudle = new MoudleCSSubmitECPBYX(request);
			if (!moudle.doJobs()) {
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("提交EC");
		}

		{
			ERRCODE = "0";
			ERRDESC = "succ";
			data = "提交成功";
			return true;
		}

	}

}
