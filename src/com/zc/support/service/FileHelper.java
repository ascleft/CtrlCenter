package com.zc.support.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

	public static void readFile(File file) {

		int ch = 0;
		FileReader fr = null;
		if (file.exists()) {
			try {
				fr = new FileReader(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while ((ch = fr.read()) != -1) {
					System.out.println((char) ch);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void writeFile(File file, String line) {
		try {
			if (file.exists()) {
				FileWriter fw = new FileWriter(file, true);
				fw.write(line);
				fw.write("\r");
				fw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void cleanFile(File file) {
		try {
			if (file.exists()) {
				FileWriter fw = new FileWriter(file, false);
				fw.write("");
				fw.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static File getDir(String... names) {

		String pathDir = ".";
		for (int i = 0; i < names.length; i++) {
			pathDir += "/" + names[i];
		}

		File dir = new File(pathDir);

		// 判断目录是否存在
		if (dir.exists()) {
			// System.out.println(pathDir + "已存在");
		} else {
			if (dir.mkdirs()) {
				// System.out.println(pathDir + "创建成功");
			} else {
				// System.out.println(pathDir + "无法创建");
			}
		}

		return dir;

	}

	public static File getFile(String... names) {

		String pathDir = ".";
		for (int i = 0; i < names.length - 1; i++) {
			pathDir += "/" + names[i];
		}

		String pathFile = ".";
		for (int i = 0; i < names.length; i++) {
			pathFile += "/" + names[i];
		}

		File dir = new File(pathDir);
		File file = new File(pathFile);

		// 判断目录是否存在
		if (dir.exists()) {
			// System.out.println(pathDir + "已存在");
		} else {
			if (dir.mkdirs()) {
				// System.out.println(pathDir + "创建成功");
			} else {
				// System.out.println(pathDir + "无法创建");
			}
		}

		// 判断文件是否存在
		if (file.exists()) {
			// System.out.println(pathFile + "已存在");
		} else {
			try {
				if (file.createNewFile()) {
					// System.out.println(pathFile + "创建成功");
				} else {
					// System.out.println(pathFile + "无法创建");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// System.out.println(pathFile + "无法创建" + "IO异常");
			}
		}

		return file;

	}

}
