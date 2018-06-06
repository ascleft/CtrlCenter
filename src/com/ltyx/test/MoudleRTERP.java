package com.ltyx.test;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleRTERP extends ZCBaseActionSupportPlugin {

	static ArrayList<MoudleRTERP.Store> storeListRAM = null;

	public MoudleRTERP(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		return true;
	}

	public String getAll() {
		// TODO Auto-generated method stub

		String listString = null;

		listString = "{\"data\":[{\"name\":\"异想天开\",\"list\":[{\"name\":\"立领亚麻衬衫-白色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1A-YM38B\"},{\"name\":\"39\",\"code\":\"P01C17-NS1A-YM39B\"},{\"name\":\"40\",\"code\":\"P01C17-NS1A-YM40B\"},{\"name\":\"41\",\"code\":\"P01C17-NS1A-YM41B\"},{\"name\":\"42\",\"code\":\"P01C17-NS1A-YM42B\"},{\"name\":\"43\",\"code\":\"P01C17-NS1A-YM43B\"},{\"name\":\"44\",\"code\":\"P01C17-NS1A-YM44B\"}]},{\"name\":\"立领亚麻衬衫-米白\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1B-YM38M\"},{\"name\":\"39\",\"code\":\"P01C17-NS1B-YM39M\"},{\"name\":\"40\",\"code\":\"P01C17-NS1B-YM40M\"},{\"name\":\"41\",\"code\":\"P01C17-NS1B-YM41M\"},{\"name\":\"42\",\"code\":\"P01C17-NS1B-YM42M\"},{\"name\":\"43\",\"code\":\"P01C17-NS1B-YM43M\"},{\"name\":\"44\",\"code\":\"P01C17-NS1B-YM44M\"}]},{\"name\":\"立领亚麻衬衫-浅蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1C-YM38T\"},{\"name\":\"39\",\"code\":\"P01C17-NS1C-YM39T\"},{\"name\":\"40\",\"code\":\"P01C17-NS1C-YM40T\"},{\"name\":\"41\",\"code\":\"P01C17-NS1C-YM41T\"},{\"name\":\"42\",\"code\":\"P01C17-NS1C-YM42T\"},{\"name\":\"43\",\"code\":\"P01C17-NS1C-YM43T\"},{\"name\":\"44\",\"code\":\"P01C17-NS1C-YM44T\"}]},{\"name\":\"立领亚麻衬衫-湖蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1D-YM38H\"},{\"name\":\"39\",\"code\":\"P01C17-NS1D-YM39H\"},{\"name\":\"40\",\"code\":\"P01C17-NS1D-YM40H\"},{\"name\":\"41\",\"code\":\"P01C17-NS1D-YM41H\"},{\"name\":\"42\",\"code\":\"P01C17-NS1D-YM42H\"},{\"name\":\"43\",\"code\":\"P01C17-NS1D-YM43H\"},{\"name\":\"44\",\"code\":\"P01C17-NS1D-YM44H\"}]},{\"name\":\"DP成衣免烫衬衫-白色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-YS2A-DP38BS\"},{\"name\":\"39\",\"code\":\"P01C17-YS2A-DP39BS\"},{\"name\":\"40\",\"code\":\"P01C17-YS2A-DP40BS\"},{\"name\":\"41\",\"code\":\"P01C17-YS2A-DP41BS\"},{\"name\":\"42\",\"code\":\"P01C17-YS2A-DP42BS\"},{\"name\":\"43\",\"code\":\"P01C17-YS2A-DP43BS\"},{\"name\":\"44\",\"code\":\"P01C17-YS2A-DP44BS\"}]},{\"name\":\"DP成衣免烫衬衫-淡紫\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-YS2B-DP38ZS\"},{\"name\":\"39\",\"code\":\"P01C17-YS2B-DP39ZS\"},{\"name\":\"40\",\"code\":\"P01C17-YS2B-DP40ZS\"},{\"name\":\"41\",\"code\":\"P01C17-YS2B-DP41ZS\"},{\"name\":\"42\",\"code\":\"P01C17-YS2B-DP42ZS\"},{\"name\":\"43\",\"code\":\"P01C17-YS2B-DP43ZS\"},{\"name\":\"44\",\"code\":\"P01C17-YS2B-DP44ZS\"}]},{\"name\":\"DP成衣免烫衬衫-浅蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-YS2C-DP38QL\"},{\"name\":\"39\",\"code\":\"P01C17-YS2C-DP39QL\"},{\"name\":\"40\",\"code\":\"P01C17-YS2C-DP40QL\"},{\"name\":\"41\",\"code\":\"P01C17-YS2C-DP41QL\"},{\"name\":\"42\",\"code\":\"P01C17-YS2C-DP42QL\"},{\"name\":\"43\",\"code\":\"P01C17-YS2C-DP43QL\"},{\"name\":\"44\",\"code\":\"P01C17-YS2C-DP44QL\"}]},{\"name\":\"DP成衣免烫衬衫-中蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-YS2D-DP38LS\"},{\"name\":\"39\",\"code\":\"P01C17-YS2D-DP39LS\"},{\"name\":\"40\",\"code\":\"P01C17-YS2D-DP40LS\"},{\"name\":\"41\",\"code\":\"P01C17-YS2D-DP41LS\"},{\"name\":\"42\",\"code\":\"P01C17-YS2D-DP42LS\"},{\"name\":\"43\",\"code\":\"P01C17-YS2D-DP43LS\"},{\"name\":\"44\",\"code\":\"P01C17-YS2D-DP44LS\"}]},{\"name\":\"DP短袖衬衫-白色\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-YS1A-DP38BS\"},{\"name\":\"39\",\"code\":\"P01A17-YS1A-DP39BS\"},{\"name\":\"40\",\"code\":\"P01A17-YS1A-DP40BS\"},{\"name\":\"41\",\"code\":\"P01A17-YS1A-DP41BS\"},{\"name\":\"42\",\"code\":\"P01A17-YS1A-DP42BS\"},{\"name\":\"43\",\"code\":\"P01A17-YS1A-DP43BS\"},{\"name\":\"44\",\"code\":\"P01A17-YS1A-DP44BS\"}]},{\"name\":\"DP短袖衬衫-蓝色\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-YS1C-DP38TL\"},{\"name\":\"39\",\"code\":\"P01A17-YS1C-DP39TL\"},{\"name\":\"40\",\"code\":\"P01A17-YS1C-DP40TL\"},{\"name\":\"41\",\"code\":\"P01A17-YS1C-DP41TL\"},{\"name\":\"42\",\"code\":\"P01A17-YS1C-DP42TL\"},{\"name\":\"43\",\"code\":\"P01A17-YS1C-DP43TL\"},{\"name\":\"44\",\"code\":\"P01A17-YS1C-DP44TL\"}]}]},{\"name\":\"有练\",\"list\":[{\"name\":\"立领亚麻衬衫-白色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1A-YM38B\"},{\"name\":\"39\",\"code\":\"P01C17-NS1A-YM39B\"},{\"name\":\"40\",\"code\":\"P01C17-NS1A-YM40B\"},{\"name\":\"41\",\"code\":\"P01C17-NS1A-YM41B\"},{\"name\":\"42\",\"code\":\"P01C17-NS1A-YM42B\"},{\"name\":\"43\",\"code\":\"P01C17-NS1A-YM43B\"},{\"name\":\"44\",\"code\":\"P01C17-NS1A-YM44B\"}]},{\"name\":\"立领亚麻衬衫-米白\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1B-YM38M\"},{\"name\":\"39\",\"code\":\"P01C17-NS1B-YM39M\"},{\"name\":\"40\",\"code\":\"P01C17-NS1B-YM40M\"},{\"name\":\"41\",\"code\":\"P01C17-NS1B-YM41M\"},{\"name\":\"42\",\"code\":\"P01C17-NS1B-YM42M\"},{\"name\":\"43\",\"code\":\"P01C17-NS1B-YM43M\"},{\"name\":\"44\",\"code\":\"P01C17-NS1B-YM44M\"}]},{\"name\":\"立领亚麻衬衫-浅蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1C-YM38T\"},{\"name\":\"39\",\"code\":\"P01C17-NS1C-YM39T\"},{\"name\":\"40\",\"code\":\"P01C17-NS1C-YM40T\"},{\"name\":\"41\",\"code\":\"P01C17-NS1C-YM41T\"},{\"name\":\"42\",\"code\":\"P01C17-NS1C-YM42T\"},{\"name\":\"43\",\"code\":\"P01C17-NS1C-YM43T\"},{\"name\":\"44\",\"code\":\"P01C17-NS1C-YM44T\"}]},{\"name\":\"立领亚麻衬衫-湖蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1D-YM38H\"},{\"name\":\"39\",\"code\":\"P01C17-NS1D-YM39H\"},{\"name\":\"40\",\"code\":\"P01C17-NS1D-YM40H\"},{\"name\":\"41\",\"code\":\"P01C17-NS1D-YM41H\"},{\"name\":\"42\",\"code\":\"P01C17-NS1D-YM42H\"},{\"name\":\"43\",\"code\":\"P01C17-NS1D-YM43H\"},{\"name\":\"44\",\"code\":\"P01C17-NS1D-YM44H\"}]},{\"name\":\"DP成衣免烫衬衫-白色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-YS2A-DP38BS\"},{\"name\":\"39\",\"code\":\"P01C17-YS2A-DP39BS\"},{\"name\":\"40\",\"code\":\"P01C17-YS2A-DP40BS\"},{\"name\":\"41\",\"code\":\"P01C17-YS2A-DP41BS\"},{\"name\":\"42\",\"code\":\"P01C17-YS2A-DP42BS\"},{\"name\":\"43\",\"code\":\"P01C17-YS2A-DP43BS\"},{\"name\":\"44\",\"code\":\"P01C17-YS2A-DP44BS\"}]},{\"name\":\"DP成衣免烫衬衫-淡紫\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-YS2B-DP38ZS\"},{\"name\":\"39\",\"code\":\"P01C17-YS2B-DP39ZS\"},{\"name\":\"40\",\"code\":\"P01C17-YS2B-DP40ZS\"},{\"name\":\"41\",\"code\":\"P01C17-YS2B-DP41ZS\"},{\"name\":\"42\",\"code\":\"P01C17-YS2B-DP42ZS\"},{\"name\":\"43\",\"code\":\"P01C17-YS2B-DP43ZS\"},{\"name\":\"44\",\"code\":\"P01C17-YS2B-DP44ZS\"}]},{\"name\":\"DP成衣免烫衬衫-浅蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-YS2C-DP38QL\"},{\"name\":\"39\",\"code\":\"P01C17-YS2C-DP39QL\"},{\"name\":\"40\",\"code\":\"P01C17-YS2C-DP40QL\"},{\"name\":\"41\",\"code\":\"P01C17-YS2C-DP41QL\"},{\"name\":\"42\",\"code\":\"P01C17-YS2C-DP42QL\"},{\"name\":\"43\",\"code\":\"P01C17-YS2C-DP43QL\"},{\"name\":\"44\",\"code\":\"P01C17-YS2C-DP44QL\"}]},{\"name\":\"DP成衣免烫衬衫-中蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-YS2D-DP38LS\"},{\"name\":\"39\",\"code\":\"P01C17-YS2D-DP39LS\"},{\"name\":\"40\",\"code\":\"P01C17-YS2D-DP40LS\"},{\"name\":\"41\",\"code\":\"P01C17-YS2D-DP41LS\"},{\"name\":\"42\",\"code\":\"P01C17-YS2D-DP42LS\"},{\"name\":\"43\",\"code\":\"P01C17-YS2D-DP43LS\"},{\"name\":\"44\",\"code\":\"P01C17-YS2D-DP44LS\"}]},{\"name\":\"DP短袖衬衫-白色\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-YS1A-DP38BS\"},{\"name\":\"39\",\"code\":\"P01A17-YS1A-DP39BS\"},{\"name\":\"40\",\"code\":\"P01A17-YS1A-DP40BS\"},{\"name\":\"41\",\"code\":\"P01A17-YS1A-DP41BS\"},{\"name\":\"42\",\"code\":\"P01A17-YS1A-DP42BS\"},{\"name\":\"43\",\"code\":\"P01A17-YS1A-DP43BS\"},{\"name\":\"44\",\"code\":\"P01A17-YS1A-DP44BS\"}]},{\"name\":\"DP短袖衬衫-蓝色\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-YS1C-DP38TL\"},{\"name\":\"39\",\"code\":\"P01A17-YS1C-DP39TL\"},{\"name\":\"40\",\"code\":\"P01A17-YS1C-DP40TL\"},{\"name\":\"41\",\"code\":\"P01A17-YS1C-DP41TL\"},{\"name\":\"42\",\"code\":\"P01A17-YS1C-DP42TL\"},{\"name\":\"43\",\"code\":\"P01A17-YS1C-DP43TL\"},{\"name\":\"44\",\"code\":\"P01A17-YS1C-DP44TL\"}]}]},{\"name\":\"必要商城\",\"list\":[{\"name\":\"时尚商务印花衬衫-藏青\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS2A-YH38Q\"},{\"name\":\"39\",\"code\":\"P01C17-NS2A-YH39Q\"},{\"name\":\"40\",\"code\":\"P01C17-NS2A-YH40Q\"},{\"name\":\"41\",\"code\":\"P01C17-NS2A-YH41Q\"},{\"name\":\"42\",\"code\":\"P01C17-NS2A-YH42Q\"},{\"name\":\"43\",\"code\":\"P01C17-NS2A-YH43Q\"},{\"name\":\"44\",\"code\":\"P01C17-NS2A-YH44Q\"}]},{\"name\":\"时尚商务印花衬衫-蓝绿\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-SS1-YH38LL\"},{\"name\":\"39\",\"code\":\"P01C17-SS1-YH39LL\"},{\"name\":\"40\",\"code\":\"P01C17-SS1-YH40LL\"},{\"name\":\"41\",\"code\":\"P01C17-SS1-YH41LL\"},{\"name\":\"42\",\"code\":\"P01C17-SS1-YH42LL\"},{\"name\":\"43\",\"code\":\"P01C17-SS1-YH43LL\"},{\"name\":\"44\",\"code\":\"P01C17-SS1-YH44LL\"}]},{\"name\":\"时尚商务印花衬衫-藏蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-SS2-YH38ZL\"},{\"name\":\"39\",\"code\":\"P01A17-SS2-YH39ZL\"},{\"name\":\"40\",\"code\":\"P01A17-SS2-YH40ZL\"},{\"name\":\"41\",\"code\":\"P01A17-SS2-YH41ZL\"},{\"name\":\"42\",\"code\":\"P01A17-SS2-YH42ZL\"},{\"name\":\"43\",\"code\":\"P01A17-SS2-YH43ZL\"},{\"name\":\"44\",\"code\":\"P01A17-SS2-YH44ZL\"}]},{\"name\":\"天然纯亚麻时尚四色衬衫-白色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1A-YM38B\"},{\"name\":\"39\",\"code\":\"P01C17-NS1A-YM39B\"},{\"name\":\"40\",\"code\":\"P01C17-NS1A-YM40B\"},{\"name\":\"41\",\"code\":\"P01C17-NS1A-YM41B\"},{\"name\":\"42\",\"code\":\"P01C17-NS1A-YM42B\"},{\"name\":\"43\",\"code\":\"P01C17-NS1A-YM43B\"},{\"name\":\"44\",\"code\":\"P01C17-NS1A-YM44B\"}]},{\"name\":\"天然纯亚麻时尚四色衬衫-米白\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1B-YM38M\"},{\"name\":\"39\",\"code\":\"P01C17-NS1B-YM39M\"},{\"name\":\"40\",\"code\":\"P01C17-NS1B-YM40M\"},{\"name\":\"41\",\"code\":\"P01C17-NS1B-YM41M\"},{\"name\":\"42\",\"code\":\"P01C17-NS1B-YM42M\"},{\"name\":\"43\",\"code\":\"P01C17-NS1B-YM43M\"},{\"name\":\"44\",\"code\":\"P01C17-NS1B-YM44M\"}]},{\"name\":\"天然纯亚麻时尚四色衬衫-天蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1C-YM38T\"},{\"name\":\"39\",\"code\":\"P01C17-NS1C-YM39T\"},{\"name\":\"40\",\"code\":\"P01C17-NS1C-YM40T\"},{\"name\":\"41\",\"code\":\"P01C17-NS1C-YM41T\"},{\"name\":\"42\",\"code\":\"P01C17-NS1C-YM42T\"},{\"name\":\"43\",\"code\":\"P01C17-NS1C-YM43T\"},{\"name\":\"44\",\"code\":\"P01C17-NS1C-YM44T\"}]},{\"name\":\"天然纯亚麻时尚四色衬衫-湖蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS1D-YM38H\"},{\"name\":\"39\",\"code\":\"P01C17-NS1D-YM39H\"},{\"name\":\"40\",\"code\":\"P01C17-NS1D-YM40H\"},{\"name\":\"41\",\"code\":\"P01C17-NS1D-YM41H\"},{\"name\":\"42\",\"code\":\"P01C17-NS1D-YM42H\"},{\"name\":\"43\",\"code\":\"P01C17-NS1D-YM43H\"},{\"name\":\"44\",\"code\":\"P01C17-NS1D-YM44H\"}]},{\"name\":\"DP免烫商务四色衬衫-白色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS3A-DP38B\"},{\"name\":\"39\",\"code\":\"P01C17-NS3A-DP39B\"},{\"name\":\"40\",\"code\":\"P01C17-NS3A-DP40B\"},{\"name\":\"41\",\"code\":\"P01C17-NS3A-DP41B\"},{\"name\":\"42\",\"code\":\"P01C17-NS3A-DP42B\"},{\"name\":\"43\",\"code\":\"P01C17-NS3A-DP43B\"},{\"name\":\"44\",\"code\":\"P01C17-NS3A-DP44B\"}]},{\"name\":\"DP免烫商务四色衬衫-紫色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS3C-DP38Z\"},{\"name\":\"39\",\"code\":\"P01C17-NS3C-DP39Z\"},{\"name\":\"40\",\"code\":\"P01C17-NS3C-DP40Z\"},{\"name\":\"41\",\"code\":\"P01C17-NS3C-DP41Z\"},{\"name\":\"42\",\"code\":\"P01C17-NS3C-DP42Z\"},{\"name\":\"43\",\"code\":\"P01C17-NS3C-DP43Z\"},{\"name\":\"44\",\"code\":\"P01C17-NS3C-DP44Z\"}]},{\"name\":\"DP免烫商务四色衬衫-蓝色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS3B-DP38L\"},{\"name\":\"39\",\"code\":\"P01C17-NS3B-DP39L\"},{\"name\":\"40\",\"code\":\"P01C17-NS3B-DP40L\"},{\"name\":\"41\",\"code\":\"P01C17-NS3B-DP41L\"},{\"name\":\"42\",\"code\":\"P01C17-NS3B-DP42L\"},{\"name\":\"43\",\"code\":\"P01C17-NS3B-DP43L\"},{\"name\":\"44\",\"code\":\"P01C17-NS3B-DP44L\"}]},{\"name\":\"DP免烫商务四色衬衫-深蓝色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-NS3D-DP38ZL\"},{\"name\":\"39\",\"code\":\"P01C17-NS3D-DP38ZL\"},{\"name\":\"40\",\"code\":\"P01C17-NS3D-DP38ZL\"},{\"name\":\"41\",\"code\":\"P01C17-NS3D-DP38ZL\"},{\"name\":\"42\",\"code\":\"P01C17-NS3D-DP38ZL\"},{\"name\":\"43\",\"code\":\"P01C17-NS3D-DP38ZL\"},{\"name\":\"44\",\"code\":\"P01C17-NS3D-DP38ZL\"}]},{\"name\":\"网状长绒棉休闲短袖衬衫-天蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-NS4B-WZ38QL\"},{\"name\":\"39\",\"code\":\"P01A17-NS4B-WZ39QL\"},{\"name\":\"40\",\"code\":\"P01A17-NS4B-WZ40QL\"},{\"name\":\"41\",\"code\":\"P01A17-NS4B-WZ41QL\"},{\"name\":\"42\",\"code\":\"P01A17-NS4B-WZ42QL\"},{\"name\":\"43\",\"code\":\"P01A17-NS4B-WZ43QL\"},{\"name\":\"44\",\"code\":\"P01A17-NS4B-WZ44QL\"}]},{\"name\":\"网状长绒棉休闲短袖衬衫-藏青\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-SS13A-WZ38ZQ\"},{\"name\":\"39\",\"code\":\"P01A17-SS13A-WZ38ZQ\"},{\"name\":\"40\",\"code\":\"P01A17-SS13A-WZ38ZQ\"},{\"name\":\"41\",\"code\":\"P01A17-SS13A-WZ38ZQ\"},{\"name\":\"42\",\"code\":\"P01A17-SS13A-WZ38ZQ\"},{\"name\":\"43\",\"code\":\"P01A17-SS13A-WZ38ZQ\"},{\"name\":\"44\",\"code\":\"P01A17-SS13A-WZ38ZQ\"}]},{\"name\":\"网状长绒棉休闲短袖衬衫-黑\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-SS13B-WZ38HS\"},{\"name\":\"39\",\"code\":\"P01A17-SS13B-WZ39HS\"},{\"name\":\"40\",\"code\":\"P01A17-SS13B-WZ40HS\"},{\"name\":\"41\",\"code\":\"P01A17-SS13B-WZ41HS\"},{\"name\":\"42\",\"code\":\"P01A17-SS13B-WZ42HS\"},{\"name\":\"43\",\"code\":\"P01A17-SS13B-WZ43HS\"},{\"name\":\"44\",\"code\":\"P01A17-SS13B-WZ44HS\"}]},{\"name\":\"假日休闲棉麻短袖衬衫-草绿\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-SS45-GZ38CL\"},{\"name\":\"39\",\"code\":\"P01A17-SS45-GZ39CL\"},{\"name\":\"40\",\"code\":\"P01A17-SS45-GZ40CL\"},{\"name\":\"41\",\"code\":\"P01A17-SS45-GZ41CL\"},{\"name\":\"42\",\"code\":\"P01A17-SS45-GZ42CL\"},{\"name\":\"43\",\"code\":\"P01A17-SS45-GZ43CL\"},{\"name\":\"44\",\"code\":\"P01A17-SS45-GZ44CL\"}]},{\"name\":\"假日休闲棉麻短袖衬衫-蓝咖\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-SS43-GZ38LK\"},{\"name\":\"39\",\"code\":\"P01A17-SS43-GZ39LK\"},{\"name\":\"40\",\"code\":\"P01A17-SS43-GZ40LK\"},{\"name\":\"41\",\"code\":\"P01A17-SS43-GZ41LK\"},{\"name\":\"42\",\"code\":\"P01A17-SS43-GZ42LK\"},{\"name\":\"43\",\"code\":\"P01A17-SS43-GZ43LK\"},{\"name\":\"44\",\"code\":\"P01A17-SS43-GZ44LK\"}]},{\"name\":\"假日休闲棉麻短袖衬衫-青绿\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-SS44-GZ38LL\"},{\"name\":\"39\",\"code\":\"P01A17-SS44-GZ39LL\"},{\"name\":\"40\",\"code\":\"P01A17-SS44-GZ40LL\"},{\"name\":\"41\",\"code\":\"P01A17-SS44-GZ41LL\"},{\"name\":\"42\",\"code\":\"P01A17-SS44-GZ42LL\"},{\"name\":\"43\",\"code\":\"P01A17-SS44-GZ43LL\"},{\"name\":\"44\",\"code\":\"P01A17-SS44-GZ44LL\"}]},{\"name\":\"轻薄格纹长绒棉休闲衬衫-蓝色\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-SS23B-GW38QL\"},{\"name\":\"39\",\"code\":\"P01A17-SS23B-GW39QL\"},{\"name\":\"40\",\"code\":\"P01A17-SS23B-GW40QL\"},{\"name\":\"41\",\"code\":\"P01A17-SS23B-GW41QL\"},{\"name\":\"42\",\"code\":\"P01A17-SS23B-GW42QL\"},{\"name\":\"43\",\"code\":\"P01A17-SS23B-GW43QL\"},{\"name\":\"44\",\"code\":\"P01A17-SS23B-GW44QL\"}]},{\"name\":\"轻薄格纹长绒棉休闲衬衫-浅蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-SS23-GW38LS\"},{\"name\":\"39\",\"code\":\"P01A17-SS23-GW38LS\"},{\"name\":\"40\",\"code\":\"P01A17-SS23-GW38LS\"},{\"name\":\"41\",\"code\":\"P01A17-SS23-GW38LS\"},{\"name\":\"42\",\"code\":\"P01A17-SS23-GW38LS\"},{\"name\":\"43\",\"code\":\"P01A17-SS23-GW38LS\"},{\"name\":\"44\",\"code\":\"P01A17-SS23-GW38LS\"}]},{\"name\":\"轻薄格纹长绒棉休闲衬衫-深蓝\",\"list\":[{\"name\":\"38\",\"code\":\"P01A17-SS21-GW38SL\"},{\"name\":\"39\",\"code\":\"P01A17-SS21-GW39SL\"},{\"name\":\"40\",\"code\":\"P01A17-SS21-GW40SL\"},{\"name\":\"41\",\"code\":\"P01A17-SS21-GW41SL\"},{\"name\":\"42\",\"code\":\"P01A17-SS21-GW42SL\"},{\"name\":\"43\",\"code\":\"P01A17-SS21-GW43SL\"},{\"name\":\"44\",\"code\":\"P01A17-SS21-GW44SL\"}]},{\"name\":\"网状长绒棉休闲衬衫-蓝色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-SS11-WZ38TL\"},{\"name\":\"39\",\"code\":\"P01C17-SS11-WZ39TL\"},{\"name\":\"40\",\"code\":\"P01C17-SS11-WZ40TL\"},{\"name\":\"41\",\"code\":\"P01C17-SS11-WZ41TL\"},{\"name\":\"42\",\"code\":\"P01C17-SS11-WZ42TL\"},{\"name\":\"43\",\"code\":\"P01C17-SS11-WZ43TL\"},{\"name\":\"44\",\"code\":\"P01C17-SS11-WZ44TL\"}]},{\"name\":\"网状长绒棉休闲衬衫-灰蓝色\",\"list\":[{\"name\":\"38\",\"code\":\"P01C17-SS12-WZ38ZL\"},{\"name\":\"39\",\"code\":\"P01C17-SS12-WZ39ZL\"},{\"name\":\"40\",\"code\":\"P01C17-SS12-WZ40ZL\"},{\"name\":\"41\",\"code\":\"P01C17-SS12-WZ41ZL\"},{\"name\":\"42\",\"code\":\"P01C17-SS12-WZ42ZL\"},{\"name\":\"43\",\"code\":\"P01C17-SS12-WZ43ZL\"},{\"name\":\"44\",\"code\":\"P01C17-SS12-WZ44ZL\"}]}]}]}";

		JSONObject resp = JSONObject.fromObject(listString);

		JSONArray jsonStoreList = resp.getJSONArray("data");

		storeListRAM = new ArrayList<MoudleRTERP.Store>();

		for (int i = 0; i < jsonStoreList.size(); i++) {

			JSONObject jsonStore = jsonStoreList.getJSONObject(i);

			String jsonStoreName = jsonStore.getString("name");
			JSONArray jsonDesignlist = jsonStore.getJSONArray("list");

			Store store = new Store();
			store.nameCH = jsonStoreName;
			store.list = new ArrayList<MoudleRTERP.Design>();

			for (int j = 0; j < jsonDesignlist.size(); j++) {

				JSONObject jsonDesign = jsonDesignlist.getJSONObject(j);

				String jsonDesignName = jsonDesign.getString("name");
				JSONArray jsonSizelist = jsonDesign.getJSONArray("list");

				Design design = new Design();
				design.nameCH = jsonDesignName;
				design.list = new ArrayList<MoudleRTERP.Size>();

				for (int k = 0; k < jsonSizelist.size(); k++) {

					JSONObject jsonSize = jsonSizelist.getJSONObject(k);

					String jsonSizeName = jsonSize.getString("name");
					String jsonSizeCode = jsonSize.getString("code");

					Size size = new Size();
					size.nameCH = jsonSizeName;
					size.code = jsonSizeCode;

					design.list.add(size);
				}
				store.list.add(design);
			}
			storeListRAM.add(store);
		}
		return "";
	}

	public String search(String key) {
		// TODO Auto-generated method stub

		if (key.length() == 0) {
			return list2json(storeListRAM);

		} else {
			ArrayList<Store> storeListClone = new ArrayList<MoudleRTERP.Store>();

			for (int i = 0; i < storeListRAM.size(); i++) {

				Store storeClone = storeListRAM.get(i);

				if (storeClone.nameCH.indexOf(key) > -1) {
					storeListClone.add(storeClone);
				} else {
					Store storeCloneTemp = new Store();
					storeCloneTemp.nameCH = storeClone.nameCH;
					storeCloneTemp.list = new ArrayList<MoudleRTERP.Design>();

					int designCount = 0;

					for (int j = 0; j < storeClone.list.size(); j++) {

						Design designClone = storeClone.list.get(j);

						if (designClone.nameCH.indexOf(key) > -1) {
							designCount++;
							storeCloneTemp.list.add(designClone);
						}
					}
					if (designCount > 0) {
						storeListClone.add(storeCloneTemp);
					}
				}
			}
			return list2json(storeListClone);
		}
	}

	public String list2json(ArrayList<Store> stroeListIn) {

		JSONArray jsonStoreList = new JSONArray();
		// 循环商店列表
		for (int i = 0; i < stroeListIn.size(); i++) {

			JSONObject jsonStore = new JSONObject();

			JSONArray jsonDesignList = new JSONArray();
			String jsonStoreName = stroeListIn.get(i).nameCH;
			// 循环设计款式列表
			for (int j = 0; j < stroeListIn.get(i).list.size(); j++) {

				JSONObject jsonDesign = new JSONObject();

				JSONArray jsonSizeList = new JSONArray();
				String jsonDesignName = stroeListIn.get(i).list.get(j).nameCH;

				// 循环尺码列表
				for (int k = 0; k < stroeListIn.get(i).list.get(j).list.size(); k++) {

					JSONObject jsonSize = new JSONObject();

					jsonSize.put("code", stroeListIn.get(i).list.get(j).list.get(k).code);
					jsonSize.put("name", stroeListIn.get(i).list.get(j).list.get(k).nameCH);

					jsonSizeList.add(jsonSize);
				}

				jsonDesign.put("list", jsonSizeList);
				jsonDesign.put("name", jsonDesignName);

				jsonDesignList.add(jsonDesign);
			}

			jsonStore.put("list", jsonDesignList);
			jsonStore.put("name", jsonStoreName);

			jsonStoreList.add(jsonStore);

		}

		return jsonStoreList.toString();
	}

	class Store {
		String nameCH;
		String nameEN;
		ArrayList<Design> list;
	}

	class Design {
		String nameCH;
		String nameEN;
		ArrayList<Size> list;
	}

	class Size {
		String nameCH;
		String nameEN;
		String code;
	}

}
