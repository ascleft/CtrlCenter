package com.zc.support.service;

public class StringHelper {

	public static String fillLeftMIX(String input, int length, String texture) {
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

	public static String fillRightMIX(String input, int length, String entexture) {
		String output = "";
		if (input.length() < length) {
			int time = (int) Math.ceil((length - input.length()) / (entexture.length()));
			String right = "";
			for (int i = 0; i < time; i++) {
				if (null == entexture) {
					right += " ";
				} else {
					right += entexture;
				}
			}
			output = input + right;
		} else {
			output = input;
		}
		return output;

	}

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

	public static int countChineseNums(String str) {
		byte b[] = str.getBytes();
		int byteLength = b.length;
		int strLength = str.length();
		return (byteLength - strLength) / 2;
	}

}
