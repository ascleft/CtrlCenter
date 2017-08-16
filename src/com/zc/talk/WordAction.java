package com.zc.talk;

import java.util.Random;

import com.zc.web.base.doman.ZCActionSupport;

public class WordAction extends ZCActionSupport {

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
				dataTemp = "老子暗恋你很久了";
				break;
			case 1:
				dataTemp = "老子忍你很久了";
				break;
			case 2:
				dataTemp = "对不起你是个好人";
				break;
			case 3:
				dataTemp = "今天拉肚子我要请假";
				break;
			case 4:
				dataTemp = "今天发烧我要请假";
				break;
			case 5:
				dataTemp = "叔叔，我不约";
				break;
			case 6:
				dataTemp = "阿姨，我不约";
				break;
			default:
				dataTemp = "抬起屁股吧少年，让我们决一死战";
				break;
		}

		ERRCODE = "0";
		ERRDESC = "succ";
		data = dataTemp;

		writeResp();

		return null;

	}
}
