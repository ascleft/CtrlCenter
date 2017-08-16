package com.zc.web.base.service;

import javax.servlet.http.HttpServletRequest;

public class ReqParamGatter {

	public static int getParamInt(HttpServletRequest req, String key) {

		String[] input_values = null;
		int return_value;

		input_values = req.getParameterValues(key);

		if (null != input_values) {
			if (input_values.length == 1) {
				return_value = string2int(input_values[0]);
			} else {
				return_value = 0;
				for (String temp_input_value : input_values) {
					return_value += string2int(temp_input_value);
				}
				Log.Nano.tag("请求动作参数" + key, "出现" + input_values.length + "个选项", input_values.toString());
			}
		} else {
			return_value = 0;
			Log.Nano.tag("请求动作参数" + key, "请求动作中未包含该参数");
		}

		return return_value;
	}

	public static double getParamDouble(HttpServletRequest req, String key) {
		String[] input_values = null;
		double return_value;

		input_values = req.getParameterValues(key);

		if (null != input_values) {
			if (input_values.length == 1) {
				return_value = string2int(input_values[0]);
			} else {
				return_value = 0;
				for (String temp_input_value : input_values) {
					return_value += string2double(temp_input_value);
				}
				Log.Nano.tag("请求动作参数" + key, "出现" + input_values.length + "个选项", input_values.toString());
			}
		} else {
			return_value = 0;
			Log.Nano.tag("请求动作参数" + key, "请求动作中未包含该参数");
		}

		return return_value;
	}

	public static String getParamString(HttpServletRequest req, String key) {

		String[] input_values = null;
		String return_value = null;

		input_values = req.getParameterValues(key);

		if (null != input_values) {
			if (input_values.length == 1) {
				return_value = input_values[0].toString().trim();
			} else {
				return_value = "";
				for (String temp_input_value : input_values) {
					return_value += temp_input_value.trim() + ",";
				}
				Log.Nano.tag("请求动作参数" + key, "出现" + input_values.length + "个选项", return_value);
			}
		} else {
			return_value = "";
			Log.Nano.tag("请求动作参数" + key, "请求动作中未包含该参数");
		}

		return return_value;
	}

	public static String[] getParamStrings(HttpServletRequest req, String key) {

		String[] input_values = null;
		String[] return_value = null;

		input_values = req.getParameterValues(key);

		if (null != input_values) {
			return_value = new String[input_values.length];
			for (int i = 0; i < input_values.length; i++) {
				return_value[i] = input_values[i].toString().trim();
			}
			Log.Nano.tag("请求动作参数" + key, "出现" + input_values.length + "个选项", return_value.toString());
		} else {
			return_value = new String[] { "" };
			Log.Nano.tag("请求动作参数" + key, "请求动作中未包含该参数");
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
