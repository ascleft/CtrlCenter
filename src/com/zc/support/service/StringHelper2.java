package com.zc.support.service;

public class StringHelper2 {

	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		java.util.Random random = new java.util.Random();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());

			sb.append(base.charAt(number));

		}
		return sb.toString();
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
