package com.zc.talk;

import java.util.Random;

import com.zc.web.base.doman.ZCActionSupport;

public class WordAction extends ZCActionSupport {

	private static final long serialVersionUID = 10086L;

	/**
	 * ��ȡҳ��
	 * 
	 */
	public String getPage() {
		return "succ";
	}

	/**
	 * ��ȡ�������ӿ�
	 * 
	 */
	public String randomWord() {

		init(true);

		String dataTemp = null;

		Random random = new Random();
		int i = random.nextInt(8);
		switch (i) {
		case 0:
			dataTemp = "���Ӱ�����ܾ���";
			break;
		case 1:
			dataTemp = "��������ܾ���";
			break;
		case 2:
			dataTemp = "�Բ������Ǹ�����";
			break;
		case 3:
			dataTemp = "������������Ҫ���";
			break;
		case 4:
			dataTemp = "���췢����Ҫ���";
			break;
		case 5:
			dataTemp = "���壬�Ҳ�Լ";
			break;
		case 6:
			dataTemp = "���̣��Ҳ�Լ";
			break;
		default:
			dataTemp = "̧��ƨ�ɰ����꣬�����Ǿ�һ��ս";
			break;
		}

		ERRCODE = "0";
		ERRDESC = "succ";
		data = dataTemp;

		writeResp();

		return null;

	}
}
