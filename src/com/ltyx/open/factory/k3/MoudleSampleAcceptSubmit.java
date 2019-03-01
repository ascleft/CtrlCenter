package com.ltyx.open.factory.k3;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.config.ConfigHelperURL;
import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.link.ZCHttpReqParam;
import com.zc.support.link.ZCHttpReqProperty;
import com.zc.support.link.ZCHttpReqSender;
import com.zc.support.service.Log;
import com.zc.support.service.TimeHelper;

import net.sf.json.JSONObject;

public class MoudleSampleAcceptSubmit extends ZCBaseActionSupportPlugin {

	private static String URL_EC2K3 = "http://222.134.77.23:8091/k3cloud/services/LUTAIWebService.asmx";
	private static String BODY_EC2K3 = "{\"FOrderDate\": \"2019-02-13\",\"FCustomName\": \"李帅定制\",\"FReceAddress\": \"山东省淄博市淄川区松龄东路81号\",\"FExpress\": \"顺丰快递\",\"FShopIDNum\": \"8888\",\"FShopName\": \"李帅定制\",\"FCustManager\": \"张孟霞\",\"FISGroup\": \"1\",\"FExpressCompany\": \"顺丰快递\",\"FPayMethod\": \"月结\",\"FBusineseType\": \"顺丰特惠\",\"FSettlMonAccounts\": \"10001\",\"FConsignee\": \"李帅淄博\",\"FReceCompany\": \"制衣ERP\",\"FReceNumber\": \"18688886666\",\"FRecePhone\": \"18688886666\",\"FDetailedAddress\": \"松龄东路81号\",\"FReceivePro\": \"山东省\",\"FReceiveCity\": \"淄博市\",\"FReceiveDis\": \"淄川区\",\"FDepositContent\": \"10001\",\"FDepositQty\": \"2\",\"FDepositNum\": \"\",\"FDepositWeight\": \"\",\"FSender\": \"李帅北京\",\"FCustOrderNo\": \"\",\"FSendCompany\": \"\",\"FSendPhone\": \"18688886666\",\"FSendDetaildAddr\": \"荣华南路10号院5号楼2003室\",\"FSendPro\": \"北京\",\"FSendCity\": \"北京\",\"FSendDis\": \"亦庄经济开发区\",\"FSendNote\": \"测试订单，请勿发货\",\"fentity\": [{\"FSampleDeliveryDate\": \"2019-02-13\",\"FUrgentDays\": \"0\",\"FStyleSelect\": \"\",\"FUskinFbric\": \"MC01-01\",\"FLTFabricNo\": \"\",\"FCustFabricExpressNo\": \"10001\",\"FGuestProDes\": \"\",\"FColorFabricNum\": \"MC01-02\",\"FColorPosNum\": \"LZX-80-00\",\"FNote\": \"\",\"FOrderNum\": \"20190213\",\"FOrderQty\": \"2\",\"Fpackage\": \"LZX-05-29T\",\"FFabricSettleCost\": \"100\",\"FProcessFee\": \"50\",\"FInAccountPrice\": \"150\",\"FTotalPrice\": \"200\",\"FTaxPrice\": \"400\",\"FNeck\": \"44\",\"FBust\": \"126\",\"FWaist\": \"115\",\"FBelly\": \"0\",\"FBase\": \"126\",\"FBackLength\": \"74\",\"FLeftWrist\": \"26\",\"FRightWrist\": \"26\",\"FLargeHip\": \"48.5\",\"FSmallHip\": \"0\",\"FShoulderWidth\": \"49\",\"FLongSleeve\": \"58.5\",\"FShortSleeve\": \"0\",\"FShortSleeveMouth\": \"0\",\"FFrontLength\": \"77.5\",\"FAcrossFront\": \"0\",\"FAcrossBack\": \"0\",\"FChestHeight\": \"0\",\"FChestDistance\": \"0\",\"FQchbj\": \"LZX-TT-55-11\",\"FLShoulderCharact\": \"LZX-TT-56-16\",\"FRShoulderCharact\": \"LZX-TT-57-23\",\"FQghyt\": \"LZX-TT-58-30\",\"FBackCharact\": \"LZX-TT-59-37\",\"FChestCharact\": \"LZX-TT-60-41\",\"FAbdominalFeatures\": \"LZX-TT-61-45\",\"FXzbx\": \"LZX-TT-62-49\",\"FXlsxw\": \"LZX-TT-63-54\",\"FSlsst\": \"LZX-TT-64-58\",\"FFrontCollarDig\": \"LZX-TT-73-62\",\"FCollarType\": \"LZX-01-01Z\",\"FCollarInsert\": \"YX-07-00\",\"FUnevenSleeveLen\": \"长袖\",\"FCuff\": \"LZX-02-01\",\"FMenJin\": \"LZX-03-02\",\"FPlacket\": \"LZX-04-00\",\"FBackPieceStyle\": \"LZX-17-02\",\"FBottomPend\": \"LZX-06-01\",\"FPlaster\": \"LZX-26-00\",\"FOpenTopCollar\": \"YX-18-01\",\"FSideSeam\": \"YX-19-01\",\"FGuestStyle\": \"\",\"FSpecialProNotes\": \"\",\"FStrSpecialArt\": \"无特殊工艺\",\"FCuffPleat\": \"LZX-120-01\",\"FTape\": \"4.2\",\"FLiningCloth\": \"YX-16-06\",\"FEmbroideryOpt\": \"1\",\"FChromaticLine\": \"U++++\",\"FFont\": \"LZX-11-01\",\"FContent\": \"LISHUAI\",\"FPattern\": \"\",\"FEmbroideryHeight\": \"YX-12-11\",\"FEmbroideryPos\": \"LZX-13-01\",\"FEmbroideryOpt_2\": \"0\",\"FChromaticLine_2\": \"\",\"FFont_2\": \"\",\"FContent_2\": \"\",\"FPattern_2\": \"\",\"FEmbroideryHeight_2\": \"\",\"FEmbroideryPos_2\": \"\",\"FGuestEmbroideryPat\": \"\",\"FBigButton\": \"KS021\",\"FButtonPos\": \"LZX-90-02\",\"FButtonDecorate\": \"KS002\",\"FButtonNum\": \"1\",\"FButtonDes\": \"纽扣色线说明\",\"FLeaderDecLine\": \"U++++\",\"FNailFastener\": \"U++++\",\"FLockColorLine\": \"U++++\",\"FSuture\": \"U++++\",\"FDecorativeLine\": \"U++++\",\"FRibbon\": \"ZDFFF\",\"FRibbonPos\": \"LZX-81-00\",\"Finstructions\": \"\",\"FRefsize\": \"\",\"FSizeName\": \"\",\"FSizeLabel\": \"1\",\"fsubentity\": [{\"FGuestName\": \"李帅\",\"FDqty\": \"1\",\"FReAddress\": \"山东省淄博市淄川区松龄东路81号\",\"FRephone\": \"18688886666\",\"FShirtNumber\": \"20190213-01\",\"FExpressCompany_Sub\": \"顺丰快递\",\"FPayMethod_Sub\": \"月结\",\"FBusineseType_Sub\": \"顺丰特惠\",\"FSettlMonAccounts_Sub\": \"10001\",\"FConsignee_Sub\": \"李帅淄博\",\"FReceCompany_Sub\": \"制衣ERP\",\"FReceNumber_Sub\": \"18688886666\",\"FRecePhone_Sub\": \"18688886666\",\"FDetailedAddress_Sub\": \"松龄东路81号\",\"FReceivePro_Sub\": \"山东省\",\"FReceiveCity_Sub\": \"淄博市\",\"FReceiveDis_Sub\": \"淄川区\",\"FDepositContent_Sub\": \"10001\",\"FDepositQty_Sub\": \"2\",\"FDepositNum_Sub\": \"\",\"FDepositWeight_Sub\": \"\",\"FSender_Sub\": \"李帅北京\",\"FCustOrderNo_Sub\": \"\",\"FSendCompany_Sub\": \"\",\"FSendPhone_Sub\": \"18688886666\",\"FSendDetaildAddr_Sub\": \"荣华南路10号院5号楼2003室\",\"FSendPro_Sub\": \"北京\",\"FSendCity_Sub\": \"北京\",\"FSendDis_Sub\": \"亦庄经济开发区\",\"FSendNote_Sub\": \"测试订单，请勿发货\"}, {\"FGuestName\": \"帅李\",\"FDqty\": \"1\",\"FReAddress\": \"山东省淄博市淄川区松龄东路18号\",\"FRephone\": \"18688886666\",\"FShirtNumber\": \"20190213-01\",\"FExpressCompany_Sub\": \"顺丰快递\",\"FPayMethod_Sub\": \"月结\",\"FBusineseType_Sub\": \"顺丰特惠\",\"FSettlMonAccounts_Sub\": \"10001\",\"FConsignee_Sub\": \"李帅淄博\",\"FReceCompany_Sub\": \"制衣ERP\",\"FReceNumber_Sub\": \"18688886666\",\"FRecePhone_Sub\": \"18688886666\",\"FDetailedAddress_Sub\": \"松龄东路81号\",\"FReceivePro_Sub\": \"山东省\",\"FReceiveCity_Sub\": \"淄博市\",\"FReceiveDis_Sub\": \"淄川区\",\"FDepositContent_Sub\": \"10001\",\"FDepositQty_Sub\": \"2\",\"FDepositNum_Sub\": \"\",\"FDepositWeight_Sub\": \"\",\"FSender_Sub\": \"李帅北京\",\"FCustOrderNo_Sub\": \"\",\"FSendCompany_Sub\": \"\",\"FSendPhone_Sub\": \"18688886666\",\"FSendDetaildAddr_Sub\": \"荣华南路10号院5号楼2003室\",\"FSendPro_Sub\": \"北京\",\"FSendCity_Sub\": \"北京\",\"FSendDis_Sub\": \"亦庄经济开发区\",\"FSendNote_Sub\": \"测试订单，请勿发货\"}]}]}";

	public MoudleSampleAcceptSubmit(HttpServletRequest req) {
		this.name = "样品通知单 提交K3";
		this.request = req;
	}

	public boolean doJobs() {
		// TODO Auto-generated method stub

		TimeHelper.Timer timer = new TimeHelper.Timer();

		ZCHttpReqParam param = new ZCHttpReqParam();
		param.addBody(BODY_EC2K3);

		ZCHttpReqProperty property = new ZCHttpReqProperty();
		property.addProperty("SOAPAction", "http://schema.kingdee.com/LUTAIWebServiceExtend/ReceiveECOrder");
		property.addProperty("Content-Type", "text/xml; charset=utf-8");

		BODY_EC2K3 = addShell(BODY_EC2K3);

		String httpResp = ZCHttpReqSender.sendPost(URL_EC2K3, param, property);

		Log.Nano.tag(ConfigHelperURL.Url_customshopaide_add_cart_man_pbyx.getDesc() + "Resp From EC", httpResp);

		timer.stop(null);
		log.ec.addSrcReq(ConfigHelperURL.Url_customshop_get_price_man_pbyx.getUrl(), param);
		log.ec.addSrcResp(httpResp);
		log.ec.addTimer(timer);

		JSONObject jsonHttpResp;
		String jsonERRCODE;
		String jsonERRDESC;
		String jsonData;
		try {
			jsonHttpResp = JSONObject.fromObject(httpResp);
			jsonERRCODE = jsonHttpResp.getString("ERRCODE");
			jsonERRDESC = jsonHttpResp.getString("ERRDESC");
			jsonData = jsonERRDESC;
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

	private String addShell(String json) {
		String newShell = "";
		newShell += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		newShell += "<soap:Body>";
		newShell += "<ReceiveECOrder xmlns=\"http://schema.kingdee.com/LUTAIWebServiceExtend/\">";
		newShell += "<jsonData>";
		newShell += json;
		newShell += "</jsonData>";
		newShell += "</ReceiveECOrder>";
		newShell += "</soap:Body>";
		newShell += "</soap:Envelope>";
		return newShell;

	}

}
