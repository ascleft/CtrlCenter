package com.zc.support.service;

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
}
