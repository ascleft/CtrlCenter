package com.zc.support.service;

import java.util.regex.Pattern;

public class NumberHelper {

	public static int string2int(String numString) {
		int numInt = (int) string2double(numString);
		return numInt;
	}

	public static double string2double(String numString) {
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

	/**
	 * 判断字符串是否为整数
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNumeric(String string) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(string).matches();
	}

}
