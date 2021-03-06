package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleCheckTechLZX11 extends ZCBaseActionSupportPlugin {

	public MoudleCheckTechLZX11(HttpServletRequest req) {
		this.name = "校验 刺绣";
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

//		{
//			String LZX_11_CHAR_WORD = getReqParamString("LZX_11_CHAR_WORD").trim();
//			String LZX_11_FOR_CHAR_SWITCH = getReqParamString("LZX_11_FOR_CHAR_SWITCH").trim();
//			if ((LZX_11_CHAR_WORD.length() == 0) && ("1".equals(LZX_11_FOR_CHAR_SWITCH))) {
//				ERRCODE = "0";
//				ERRDESC = "fail";
//				data = "您选择了使用文字刺绣，但刺绣内容没有填写";
//				return false;
//			}
//		}
//
//		{
//			String LZX_11_CHAR_WORD = getReqParamString("LZX_11_CHAR_WORD").trim();
//			String LZX_11_FOR_CHAR_SWITCH = getReqParamString("LZX_11_FOR_CHAR_SWITCH").trim();
//			if ((LZX_11_CHAR_WORD.length() > 0) && ("0".equals(LZX_11_FOR_CHAR_SWITCH))) {
//				ERRCODE = "0";
//				ERRDESC = "fail";
//				data = "您选择了不使用文字刺绣，但填写了刺绣内容";
//				return false;
//			}
//		}

		{
			String type = getReqParamString("embroidery_section_1_type").trim();
			String content = getReqParamString("embroidery_section_1_char_content").trim();
			if (("char".equals(type)) && (content.length() == 0)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "您在【刺绣位置1】处选择了使用【文字刺绣】，但【刺绣内容】没有填写";
				return false;
			}
		}
		{
			String type = getReqParamString("embroidery_section_2_type").trim();
			String content = getReqParamString("embroidery_section_2_char_content").trim();
			if (("char".equals(type)) && (content.length() == 0)) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "您在【刺绣位置2】处选择了使用【文字刺绣】，但【刺绣内容】没有填写";
				return false;
			}
		}

		return true;

	}

}
