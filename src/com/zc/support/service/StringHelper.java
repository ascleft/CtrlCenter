package com.zc.support.service;

public class StringHelper {
	
	public static String fillRight(String input, int length, String texture) {
		String output = "";
		if (length > input.length()) {
			int time = (length - input.length()) / (texture.length()) + 1;
			String right = "";
			for (int i = 0; i < time; i++) {
				if (null == texture) {
					right += " ";
				} else {
					right += texture;
				}
			}
			right = right.substring(0, length - input.length());
			output = input + right;
		} else {
			output = input;
		}
		return output;
		
	}

	public static String fillLeft(String input, int length, String texture) {
		String output = "";
		if (length > input.length()) {
			int time = (length - input.length()) / (texture.length()) + 1;
			String left = "";
			for (int i = 0; i < time; i++) {
				if (null == texture) {
					left += " ";
				} else {
					left += texture;
				}
			}
			left = left.substring(0, length - input.length());
			output = left + input;
		} else {
			output = input;
		}
		return output;
	}

}
