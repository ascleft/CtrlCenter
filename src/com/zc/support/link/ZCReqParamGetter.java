package com.zc.support.link;

import javax.servlet.http.HttpServletRequest;

import com.zc.support.service.Log;

public class ZCReqParamGetter {

	public static int getParamInt(HttpServletRequest req, String key, boolean allwLog) {

		int[] input_values = null;
		int return_value = 0;

		input_values = getParamInts(req, key, allwLog);

		for (int i = 0; i < input_values.length; i++) {
			return_value += input_values[i];
		}

		return return_value;
	}

	public static int[] getParamInts(HttpServletRequest req, String key, boolean allwLog) {

		String[] input_values = null;
		int[] return_value;

		input_values = req.getParameterValues(key);

		if (null != input_values) {

			return_value = new int[input_values.length];

			for (int i = 0; i < input_values.length; i++) {
				return_value[i] = string2int(input_values[i]);
			}

			if (allwLog) {
				if (input_values.length > 1) {
					Log.Nano.tag(//
							"请求动作参数" + key + "出现多选",//
							"共" + input_values.length + "个选项", //
							"转型前:", input_values.toString(),//
							"转型后:", return_value.toString()//
					);
				}
			}

		} else {
			return_value = new int[] { 0 };
			if (allwLog) {
				Log.Nano.tag("请求动作参数" + key + "数据异常", "请求动作中未包含该参数");
			}
		}

		return return_value;
	}

	public static double getParamDouble(HttpServletRequest req, String key, boolean allwLog) {

		double[] input_values = null;
		double return_value = 0;

		input_values = getParamDoubles(req, key, allwLog);

		for (int i = 0; i < input_values.length; i++) {
			return_value += input_values[i];
		}

		return return_value;
	}

	public static double[] getParamDoubles(HttpServletRequest req, String key, boolean allwLog) {

		String[] input_values = null;
		double[] return_value = null;

		input_values = req.getParameterValues(key);

		if (null != input_values) {

			return_value = new double[input_values.length];

			for (int i = 0; i < input_values.length; i++) {
				return_value[i] = string2double(input_values[i]);
			}
			
			if (allwLog) {
				if (input_values.length > 1) {
					Log.Nano.tag(//
							"请求动作参数" + key + "出现多选",//
							"共" + input_values.length + "个选项", //
							"转型前:", input_values.toString(),//
							"转型后:", return_value.toString()//
					);
				}
			}

		} else {
			return_value = new double[] { 0d };
			if (allwLog) {
				Log.Nano.tag("请求动作参数" + key + "数据异常", "请求动作中未包含该参数");
			}
		}

		return return_value;
	}

	public static String getParamString(HttpServletRequest req, String key, boolean allwLog) {

		String[] input_values = null;
		String return_value = "";

		input_values = getParamStrings(req, key, allwLog);

		for (int i = 0; i < input_values.length; i++) {

			return_value += input_values[i];

			if (i < input_values.length - 1) {
				return_value += ",";
			}
		}

		return return_value;
	}

	public static String[] getParamStrings(HttpServletRequest req, String key, boolean allwLog) {

		String[] input_values = null;
		String[] return_value = null;

		input_values = req.getParameterValues(key);

		if (null != input_values) {
			
			return_value = new String[input_values.length];
			
			for (int i = 0; i < input_values.length; i++) {
				return_value[i] = input_values[i].toString().trim();
			}
			
			if (allwLog) {
				if (input_values.length > 1) {
					Log.Nano.tag(//
							"请求动作参数" + key + "出现多选",//
							"共" + input_values.length + "个选项", //
							"转型前:", input_values.toString(),//
							"转型后:", return_value.toString()//
					);
				}
			}
			
		} else {
			return_value = new String[] { "" };
			if (allwLog) {
				Log.Nano.tag("请求动作参数" + key + "数据异常", "请求动作中未包含该参数");
			}
		}

		return return_value;
	}

	private static int string2int(String numString) {
		int numInt = (int) string2double(numString);
		return numInt;
	}

	private static double string2double(String numString) {
		double numDouble = 0d;
		try {
			java.text.DecimalFormat myformat = new java.text.DecimalFormat("#0.00");
			numDouble = Double.parseDouble(numString.trim());// 装换为double类型
			numDouble = Double.parseDouble(myformat.format(numDouble));// 保留2为小数
		} catch (Exception e) {
			// TODO: handle exception
			numDouble = 0;
		}
		return numDouble;
	}

}
