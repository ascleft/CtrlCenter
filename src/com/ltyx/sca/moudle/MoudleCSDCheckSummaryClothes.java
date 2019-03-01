package com.ltyx.sca.moudle;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.doman.ZCBaseActionSupportPlugin;
import com.zc.support.service.NumberHelper;

public class MoudleCSDCheckSummaryClothes extends ZCBaseActionSupportPlugin {

	public MoudleCSDCheckSummaryClothes(HttpServletRequest req) {
		this.name = "校验 订单摘要信息";
		this.request = req;
	}

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		{
			String design_code = getReqParamString("design_code");
			String uskin_code = getReqParamString("uskin_code");
			if (uskin_code.length() < 1) {
				if (design_code.trim().length() == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "请填写正确的面料编号";
					return false;
				}
			}
		}

		{
			String YX_08 = getReqParamString("YX_08", "");
			if (YX_08.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "客供主唛必须填写说明";
				return false;
			}
		}

		{
			String kouzi = getReqParamString("kouzi", "");
			if (kouzi.length() < 1) {
				ERRCODE = "0";
				ERRDESC = "fail";
				data = "客供扣子必须填写说明";
				return false;
			}
		}

		{
			String order_mtm_type = getReqParamString("order_mtm_type", "");
			String order_production_count = getReqParamString("order_production_count", "");
			String order_production_count_real = getReqParamString("order_production_count_real", "");

			int[] minmax = chooseNumber(order_production_count);
			int real = getReqParamInt("order_production_count_real");

			if (order_mtm_type.equals("团单")) {
				if (order_production_count_real.length() == 0) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您没有填写团单的衬衫数量，请在【摘要】的【总数】处填写真实的衬衫数量";
					return false;
				} else {

					if (order_production_count_real.trim().length() == 0) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "请在【摘要】的【总数】处填写真实的衬衫数量(阿拉伯数字),只写空格没用";
						return false;
					}
					if (order_production_count_real.length() != order_production_count_real.trim().length()) {
						ERRCODE = "0";
						ERRDESC = "fail";
						data = "请在【摘要】的【总数】处填写真实的衬衫数量(阿拉伯数字),您当前填写的内容有空格";
						return false;
					}
				}
				if (!NumberHelper.isNumeric(order_production_count_real)) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "请在【摘要】的【总数】处填写真实的衬衫数量(阿拉伯数字)";
					return false;
				}
				if (real < minmax[0]) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前的衬衫数量小于起订量，请在【摘要】的【起订量】处选择相应梯度";
					return false;
				}
				if (minmax[1] < real) {
					ERRCODE = "0";
					ERRDESC = "fail";
					data = "您当前的衬衫数量可享受更大的优惠力度，请在【摘要】的【起订量】处选择相应梯度";
					return false;
				}
			}
		}

		return true;

	}

	private int[] chooseNumber(String numberString) {
		int numberInt[] = new int[2];
		switch (numberString) {

		case "10-15":
			numberInt[0] = 10;
			numberInt[1] = 15;
			break;
		case "15-25":
			numberInt[0] = 15;
			numberInt[1] = 25;
			break;
		case "11-30":
			numberInt[0] = 11;
			numberInt[1] = 30;
			break;
		case "31-100":
			numberInt[0] = 31;
			numberInt[1] = 100;
			break;
		case "101-500":
			numberInt[0] = 101;
			numberInt[1] = 500;
			break;
		case "501-1500":
			numberInt[0] = 501;
			numberInt[1] = 1500;
			break;
		default:
			numberInt[0] = 0;
			numberInt[1] = 0;
			break;
		}

		return numberInt;

	}

}
