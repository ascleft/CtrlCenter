package com.zc.support.service;

public class StringHelper {
	public static String fillFooter(String input, int length, String texture) {
		String output = "";
		if (length > input.length()) {
			output += input;
			for (int i = 0; i < length - input.length(); i++) {
				if (null == texture) {
					output += " ";
				} else {
					output += texture;
				}
			}
		} else {
			output += input;
		}
		return output;
	}

}
