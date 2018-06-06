package com.zc.fun.talk;

import java.util.Random;

import com.zc.support.doman.ZCBaseActionSupport;

public class WordAction extends ZCBaseActionSupport {

	private static final long serialVersionUID = 10086L;

	/**
	 * 获取页面
	 *
	 */
	public String getPage() {
		return "succ";
	}

	/**
	 * 获取随机短语接口
	 *
	 */
	public String randomWord() {

		init(true);

		String dataTemp = null;

		Random random = new Random();
		int i = random.nextInt(8);
		switch (i) {
			case 0:
				dataTemp = "全时";
				break;
			case 1:
				dataTemp = "外卖";
				break;
			case 2:
				dataTemp = "泡泡米";
				break;
			case 3:
				dataTemp = "田老师";
				break;
			case 4:
				dataTemp = "煲仔饭";
				break;
			case 5:
				dataTemp = "嘉和一品";
				break;
			case 6:
				dataTemp = "麻辣烫";
				break;
			default:
				dataTemp = "全时";
				break;
		}

		ERRCODE = "0";
		ERRDESC = "succ";
		data = dataTemp;

		writeResp();

		return null;

	}
}
