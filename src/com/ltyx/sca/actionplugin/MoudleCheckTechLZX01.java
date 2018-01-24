package com.ltyx.sca.actionplugin;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCheckTechLZX01 extends ZCBaseActionSupportPlugin {

	public MoudleCheckTechLZX01(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub
		{
			String lingcheng = getReqParamString("lingcheng");
			String LZX_01 = getReqParamString("LZX_01");
			if (!Check(lingcheng, LZX_01)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "领型（" + LZX_01 + "）与领撑（" + lingcheng + "）冲突，下单时请认真检查！";
				return false;
			}
		}
		return true;
	}

	private boolean Check(String lingcheng, String LZX01) {
		boolean isSupported = false;
		if ("YX-07-54".equals(lingcheng)) {// 外树脂
			isSupported = isInTable(LZX01, 2);
		}
		if ("YX-07-00".equals(lingcheng)) {// 无
			isSupported = isInTable(LZX01, 0);
		}
		if ("YX-07-51".equals(lingcheng)) {// 内树脂
			isSupported = isInTable(LZX01, 1);
		}
		if ("YX-07-02".equals(lingcheng)) {// 外钢
			isSupported = isInTable(LZX01, 2);
		}
		if ("YX-07-01".equals(lingcheng)) {// 外铜
			isSupported = isInTable(LZX01, 2);
		}
		return isSupported;
	}

	private boolean isInTable(String name, int tableIndex) {
		boolean isIn = false;
		for (String name_temp : supportTable[tableIndex]) {
			if (name_temp.equals(name)) {
				isIn = true;
				break;
			}
		}
		return isIn;
	}

	final private static String[][] supportTable = {

	{
			// 不支持插片0
			"LZX-01-01G", // 内/外
			"LZX-01-01Z", // 内/外
			"LZX-01-01A", // 内/外
			"LZX-01-02G", // 内/外
			"LZX-01-02Z", // 内/外
			"LZX-01-02A", // 内/外
			"LZX-01-02S", // 内/外
			"LZX-01-03G", // 内/外
			"LZX-01-03Z", // 内/外
			"LZX-01-03A", // 内/外
			"LZX-01-03S", // 内/外
			"LZX-01-04G", // 内/外
			"LZX-01-04Z", // 内/外
			"LZX-01-04A", // 内/外
			"LZX-01-04S", // 内/外
			"LZX-01-05G", // 内/外
			"LZX-01-05Z", // 内/外
			"LZX-01-05A", // 内/外
			"LZX-01-06G", // 内/外
			"LZX-01-06Z", // 内/外
			"LZX-01-06A", // 内/外
			"LZX-01-06S", // 内/外
			"LZX-01-07G", // 不支持
			"LZX-01-07Z", // 不支持
			"LZX-01-07A", // 不支持
			"LZX-01-08G", // 不支持
			"LZX-01-08Z", // 不支持
			"LZX-01-09G", // 不支持
			"LZX-01-09Z", // 不支持
			"LZX-01-09A", // 不支持
			"LZX-01-10G", // 不支持
			"LZX-01-10Z", // 不支持
			"LZX-01-10A", // 不支持
			"LZX-01-11G", // 不支持
			"LZX-01-11Z", // 不支持
			"LZX-01-11A", // 不支持
			"LZX-01-13G", // 不支持
			"LZX-01-13Z", // 不支持
			"LZX-01-13A", // 不支持
			"LZX-01-14S", // 不支持
			"LZX-01-15S", // 不支持
			"LZX-01-16G", // 不支持
			"LZX-01-16Z", // 不支持
			"LZX-01-16A", // 不支持
			"LZX-01-17G", // 内/外
			"LZX-01-17Z", // 内/外
			"LZX-01-17A", // 内/外
			"LZX-01-18G", // 内/外
			"LZX-01-18Z", // 内/外
			"LZX-01-18A", // 内/外
			"LZX-01-19G", // 内/外
			"LZX-01-19Z", // 内/外
			"LZX-01-19A", // 内/外
			"LZX-01-20G", // 内/外
			"LZX-01-20Z", // 内/外
			"LZX-01-20A", // 内/外
			"LZX-01-21G", // 不支持
			"LZX-01-21Z", // 不支持
			"LZX-01-21A", // 不支持
			"LZX-01-22G", // 不支持
			"LZX-01-22Z", // 不支持
			"LZX-01-22A", // 不支持
			"LZX-01-23Z", // 内

	},

	{
			// 支持内插片 1
			"LZX-01-01G", // 内/外
			"LZX-01-01Z", // 内/外
			"LZX-01-01A", // 内/外
			"LZX-01-02G", // 内/外
			"LZX-01-02Z", // 内/外
			"LZX-01-02A", // 内/外
			"LZX-01-02S", // 内/外
			"LZX-01-03G", // 内/外
			"LZX-01-03Z", // 内/外
			"LZX-01-03A", // 内/外
			"LZX-01-03S", // 内/外
			"LZX-01-04G", // 内/外
			"LZX-01-04Z", // 内/外
			"LZX-01-04A", // 内/外
			"LZX-01-04S", // 内/外
			"LZX-01-05G", // 内/外
			"LZX-01-05Z", // 内/外
			"LZX-01-05A", // 内/外
			"LZX-01-06G", // 内/外
			"LZX-01-06Z", // 内/外
			"LZX-01-06A", // 内/外
			"LZX-01-06S", // 内/外
			"LZX-01-17G", // 内/外
			"LZX-01-17Z", // 内/外
			"LZX-01-17A", // 内/外
			"LZX-01-18G", // 内/外
			"LZX-01-18Z", // 内/外
			"LZX-01-18A", // 内/外
			"LZX-01-19G", // 内/外
			"LZX-01-19Z", // 内/外
			"LZX-01-19A", // 内/外
			"LZX-01-20G", // 内/外
			"LZX-01-20Z", // 内/外
			"LZX-01-20A", // 内/外
			"LZX-01-23Z", // 内

	},

	{
			// 支持外插 2
			"LZX-01-01G", // 内/外
			"LZX-01-01Z", // 内/外
			"LZX-01-01A", // 内/外
			"LZX-01-02G", // 内/外
			"LZX-01-02Z", // 内/外
			"LZX-01-02A", // 内/外
			"LZX-01-02S", // 内/外
			"LZX-01-03G", // 内/外
			"LZX-01-03Z", // 内/外
			"LZX-01-03A", // 内/外
			"LZX-01-03S", // 内/外
			"LZX-01-04G", // 内/外
			"LZX-01-04Z", // 内/外
			"LZX-01-04A", // 内/外
			"LZX-01-04S", // 内/外
			"LZX-01-05G", // 内/外
			"LZX-01-05Z", // 内/外
			"LZX-01-05A", // 内/外
			"LZX-01-06G", // 内/外
			"LZX-01-06Z", // 内/外
			"LZX-01-06A", // 内/外
			"LZX-01-06S", // 内/外
			"LZX-01-17G", // 内/外
			"LZX-01-17Z", // 内/外
			"LZX-01-17A", // 内/外
			"LZX-01-18G", // 内/外
			"LZX-01-18Z", // 内/外
			"LZX-01-18A", // 内/外
			"LZX-01-19G", // 内/外
			"LZX-01-19Z", // 内/外
			"LZX-01-19A", // 内/外
			"LZX-01-20G", // 内/外
			"LZX-01-20Z", // 内/外
			"LZX-01-20A", // 内/外

	}

	};

}
