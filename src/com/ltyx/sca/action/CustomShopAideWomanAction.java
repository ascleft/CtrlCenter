package com.ltyx.sca.action;

import com.ltyx.sca.actionplugin.MoudleCSACheckSummaryClothes;
import com.ltyx.sca.actionplugin.MoudleCSACheckUserInfo;
import com.ltyx.sca.actionplugin.MoudleCSAGetPricePBYX;
import com.ltyx.sca.actionplugin.MoudleCSASubmitECWoman;
import com.ltyx.sca.actionplugin.MoudleCheckMeasure;
import com.ltyx.sca.actionplugin.MoudleCheckPrice;
import com.zc.support.doman.ZCBaseActionSupport;
import com.zc.support.link.ZCReqIntroGetter;
import com.zc.support.service.Log;

public class CustomShopAideWomanAction extends ZCBaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10087L;

	public String getPage() {

		init(true);

		session.setAttribute("QRurl", SCAPageConfigCommon.get_QR_url(request));

		{
			String rank = "" + session.getAttribute("ec_user_rank");
			
			if ("0".equals(rank)) {// 客户经理
				session.setAttribute("menulist", SCAPageConfigCommon.get_menu_list_jingli());
				Log.i("分配菜单1");
			} else if ("10".equals(rank)) {// 定制顾问
				session.setAttribute("menulist", SCAPageConfigCommon.get_menu_list_guwen());
				Log.i("分配菜单2");
			} else if ("20".equals(rank)) {// 定制店
				session.setAttribute("menulist", SCAPageConfigCommon.get_menu_list_dingzhidian());
				Log.i("分配菜单3");
			}
			if ("张弛".equals(session.getAttribute("ec_user_name")) || "zc".equals(session.getAttribute("ec_user_name"))) {
				session.setAttribute("menulist", SCAPageConfigCommon.get_menu_list_all());
				Log.i("分配菜单4");
			}
		}

		session.setAttribute("list_LZX_01", SCAPageConfigMan.get_list_LZX_01());
		session.setAttribute("list_LZX_02", SCAPageConfigMan.get_list_LZX_02());
		session.setAttribute("list_LZX_03", SCAPageConfigMan.get_list_LZX_03());
		session.setAttribute("list_LZX_04", SCAPageConfigMan.get_list_LZX_04());
		session.setAttribute("list_LZX_08", SCAPageConfigMan.get_list_LZX_08());
		session.setAttribute("list_LZX_120", SCAPageConfigMan.get_list_LZX_120());
		session.setAttribute("list_LZX_06", SCAPageConfigMan.get_list_LZX_06());
		session.setAttribute("list_LZX_17", SCAPageConfigMan.get_list_LZX_17());
		session.setAttribute("list_LZX_26", SCAPageConfigMan.get_list_LZX_26());
		session.setAttribute("list_LZX_13", SCAPageConfigMan.get_list_LZX_13());
		session.setAttribute("list_zhidai", SCAPageConfigMan.get_list_zhidai());
		session.setAttribute("list_color", SCAPageConfigMan.get_list_color());
		session.setAttribute("list_kouzi", SCAPageConfigMan.get_list_kouzi());
		session.setAttribute("list_shenxing", SCAPageConfigMan.get_list_shenxing());
		session.setAttribute("list_lingcheng", SCAPageConfigMan.get_list_lingcheng());
		session.setAttribute("list_mingxian", SCAPageConfigMan.get_list_mingxian());
		session.setAttribute("list_cefeng", SCAPageConfigMan.get_list_cefeng());
		session.setAttribute("list_qiantiao", SCAPageConfigMan.get_list_qiantiao());
		session.setAttribute("list_chenbu", SCAPageConfigMan.get_list_chenbu());

		session.setAttribute("list_weizhi_zhidai", SCAPageConfigMan.get_list_weizhi_zhidai());
		session.setAttribute("list_weizhi_peise", SCAPageConfigMan.get_list_weizhi_peise());

		return "succ";

	}

	public String getPrice() {

		init(true);
		String methodName = "客户经理 衬衫 优纤面料 报价";

		ZCReqIntroGetter.showParams(methodName, request);

		doGetPrice();
		writeResp(methodName);

		return null;

	}

	public String submit() {

		init(true);
		String methodName = "客户经理 衬衫 优纤面料 提交购物车";

		ZCReqIntroGetter.showParams(methodName, request);

		doSubmit();
		writeResp(methodName);

		return null;

	}

	public boolean doGetPrice() {

		MoudleCSAGetPricePBYX moudle = new MoudleCSAGetPricePBYX(request);
		moudle.doJobs();
		ERRCODE = moudle.getERRCODE();
		ERRDESC = moudle.getERRDESC();
		data = moudle.getData();
		return true;

	}

	public boolean doSubmit() {

		{
			MoudleCSACheckUserInfo moudle = new MoudleCSACheckUserInfo(request);
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
			MoudleCSACheckSummaryClothes moudle = new MoudleCSACheckSummaryClothes(request);
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
			MoudleCheckPrice moudle = new MoudleCheckPrice(request);
			if (!moudle.doJobs()) {
				addProgressFail("报价核对");
				ERRCODE = moudle.getERRCODE();
				ERRDESC = moudle.getERRDESC();
				data = moudle.getData();
				return false;
			}
			addProgressSucc("报价核对");
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
			MoudleCSASubmitECWoman moudle = new MoudleCSASubmitECWoman(request);
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
