package com.zc.support.service;

import java.io.File;

public class TextLogHelper {
	public static void white(String line) {
		File day = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), TimeHelper.getDateYMD() + ".txt");
		File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), TimeHelper.getTimeH() + ".txt");
		FileHelper.writeFile(day, line);
		FileHelper.writeFile(hour, line);
	}

	public static void white(String line, String logType) {
		switch (logType) {

		case LogType.NORMAL: {
			NORMAL(line);
		}
			break;
		case LogType.SearchUnity: {
			SearchUnity(line);
		}
			break;
		case LogType.YBR_ORDER_ALL: {
			YBR_ORDER_ALL(line);
			break;
		}
		case LogType.YBR_ORDER_SUCC: {
			YBR_ORDER_SUCC(line);
		}
			break;
		case LogType.YBR_ORDER_FAIL: {
			YBR_ORDER_FAIL(line);
		}
			break;
		case LogType.LTYX_UTAILOR_ALL: {
			LTYX_UTAILOR_ALL(line);
		}
			break;
		case LogType.LTYX_UTAILOR_SUCC: {
			LTYX_UTAILOR_SUCC(line);
		}
			break;
		case LogType.LTYX_UTAILOR_SUCC_ORDER: {
			LTYX_UTAILOR_SUCC_ORDER(line);
		}
			break;
		case LogType.LTYX_UTAILOR_FAIL: {
			LTYX_UTAILOR_FAIL(line);
		}
			break;
		case LogType.LTYX_USKIN_AIDE_ALL: {
			LTYX_USKIN_AIDE_ALL(line);
		}
			break;
		case LogType.LTYX_USKIN_AIDE_SUCC: {
			LTYX_USKIN_AIDE_SUCC(line);
		}
			break;
		case LogType.LTYX_USKIN_AIDE_SUCC_ORDER: {
			LTYX_USKIN_AIDE_SUCC_ORDER(line);
		}
			break;
		case LogType.LTYX_USKIN_AIDE_FAIL: {
			LTYX_USKIN_AIDE_FAIL(line);
		}
			break;
		case LogType.LTYX_USKIN_USER_ALL: {
			LTYX_USKIN_USER_ALL(line);
		}
			break;
		case LogType.LTYX_USKIN_USER_SUCC: {
			LTYX_USKIN_USER_SUCC(line);
		}
			break;
		case LogType.LTYX_USKIN_USER_FAIL: {
			LTYX_USKIN_USER_FAIL(line);
		}
			break;
		case LogType.ERP600_ORDER_SUBMIT: {
			ERP600_ORDER_SUBMIT(line);
		}
			break;
		case LogType.ERP600_ORDER_SEARCH: {
			ERP600_ORDER_SEARCH(line);
		}
			break;
		case LogType.JSJ_NOTICE: {
			JSJ_NOTICE(line);
		}
			break;
		default: {
			Default(line);
		}
			break;
		}
	}

	private static void NORMAL(String line) {

		File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), TimeHelper.getDateYMD() + ".txt");
		File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), TimeHelper.getTimeH() + ".txt");
		FileHelper.writeFile(day_, line);
		FileHelper.writeFile(hour, line);

	}

	private static void SearchUnity(String line) {
		NORMAL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "SearchUnity", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "SearchUnity", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void YBR_ORDER_ALL(String line) {
		NORMAL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "YBR", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "YBR", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void YBR_ORDER_SUCC(String line) {
		YBR_ORDER_ALL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "YBR", "SUCC", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "YBR", "SUCC", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void YBR_ORDER_FAIL(String line) {
		YBR_ORDER_ALL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "YBR", "FAIL", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "YBR", "FAIL", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_UTAILOR_ALL(String line) {
		NORMAL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "UTAILOR", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "UTAILOR", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_UTAILOR_SUCC(String line) {
		LTYX_UTAILOR_ALL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "UTAILOR", "SUCC", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "UTAILOR", "SUCC", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_UTAILOR_SUCC_ORDER(String line) {
		LTYX_UTAILOR_SUCC(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "UTAILOR", "SUCC", "OREDR", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "UTAILOR", "SUCC", "OREDR", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_UTAILOR_FAIL(String line) {
		LTYX_UTAILOR_ALL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "UTAILOR", "FAIL", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "UTAILOR", "FAIL", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_USKIN_AIDE_ALL(String line) {
		NORMAL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "AIDE", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "AIDE", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_USKIN_AIDE_SUCC(String line) {
		LTYX_USKIN_AIDE_ALL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "AIDE", "SUCC", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "AIDE", "SUCC", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_USKIN_AIDE_SUCC_ORDER(String line) {
		LTYX_USKIN_AIDE_SUCC(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "AIDE", "SUCC", "ORDER", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "AIDE", "SUCC", "ORDER", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_USKIN_AIDE_FAIL(String line) {
		LTYX_USKIN_AIDE_ALL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "AIDE", "FAIL", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "AIDE", "FAIL", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_USKIN_USER_ALL(String line) {
		NORMAL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "USER", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "USER", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_USKIN_USER_SUCC(String line) {
		LTYX_USKIN_USER_ALL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "USER", "SUCC", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "USER", "SUCC", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void LTYX_USKIN_USER_FAIL(String line) {
		LTYX_USKIN_USER_ALL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "USER", "FAIL", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "USKIN", "USER", "FAIL", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void ERP600_ORDER_SUBMIT(String line) {
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "ERP600", "SUBMIT", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "ERP600", "SUBMIT", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void ERP600_ORDER_SEARCH(String line) {
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "ERP600", "SEARCH", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "ERP600", "SEARCH", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

	private static void JSJ_NOTICE(String line) {
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "JSJ", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "JSJ", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}
	
	private static void Default(String line) {
		NORMAL(line);
		{
			File day_ = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "Defult", TimeHelper.getDateYMD() + ".txt");
			File hour = FileHelper.getFile("CtrlCenterLog", TimeHelper.getDateYMD(), "Defult", TimeHelper.getTimeH() + ".txt");
			FileHelper.writeFile(day_, line);
			FileHelper.writeFile(hour, line);
		}
	}

}
