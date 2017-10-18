package com.ltyx.tailor.actionplugin;

import java.util.ArrayList;

import com.zc.support.doman.ZCBaseActionSupportPlugin;

public class MoudleUlrtaCSV extends ZCBaseActionSupportPlugin {
	ArrayList<TableCell> list;

	@Override
	public boolean doJobs() {
		// TODO Auto-generated method stub

		init();

		return false;

	}

	void init() {
		list = new ArrayList<TableCell>();

		list.add(new TableCell("A1", "", "客户姓名"));
		list.add(new TableCell("B1", "", "定制店名称"));
		list.add(new TableCell("C1", "", "定制店编号"));
		list.add(new TableCell("D1", "", ""));
		list.add(new TableCell("E1", "", ""));
		list.add(new TableCell("F1", "", ""));
		list.add(new TableCell("G1", "", "项目"));
		list.add(new TableCell("H1", "", "身高厘米"));
		list.add(new TableCell("I1", "", "体重公斤"));
		list.add(new TableCell("J1", "", "领围"));
		list.add(new TableCell("K1", "", "胸围"));
		list.add(new TableCell("L1", "", "腰围"));
		list.add(new TableCell("M1", "", "肚围"));
		list.add(new TableCell("N1", "", "臀围"));
		list.add(new TableCell("O1", "", "后衣长"));
		list.add(new TableCell("P1", "", "肩宽"));
		list.add(new TableCell("Q1", "", "前肩宽"));
		list.add(new TableCell("R1", "", "长袖长"));
		list.add(new TableCell("S1", "", "左腕围"));
		list.add(new TableCell("T1", "", "右腕围"));
		list.add(new TableCell("U1", "", "大臂围"));
		list.add(new TableCell("V1", "", "10短袖长"));
		list.add(new TableCell("W1", "", "13短袖口"));
		list.add(new TableCell("X1", "", ""));
		list.add(new TableCell("Y1", "", ""));
		list.add(new TableCell("Z1", "", "基准板"));

		list.add(new TableCell("A2", "", ""));
		list.add(new TableCell("B2", "", ""));
		list.add(new TableCell("C2", "", ""));
		list.add(new TableCell("D2", "", ""));
		list.add(new TableCell("E2", "", ""));
		list.add(new TableCell("F2", "", ""));
		list.add(new TableCell("G2", "", "净体尺寸"));
		list.add(new TableCell("H2"));
		list.add(new TableCell("I2"));
		list.add(new TableCell("J2"));
		list.add(new TableCell("K2"));
		list.add(new TableCell("L2"));
		list.add(new TableCell("M2"));
		list.add(new TableCell("N2"));
		list.add(new TableCell("O2"));
		list.add(new TableCell("P2"));
		list.add(new TableCell("Q2"));
		list.add(new TableCell("R2"));
		list.add(new TableCell("S2"));
		list.add(new TableCell("T2"));
		list.add(new TableCell("U2"));
		list.add(new TableCell("V2"));
		list.add(new TableCell("W2"));
		list.add(new TableCell("X2"));
		list.add(new TableCell("Y2"));
		list.add(new TableCell("Z2"));

		list.add(new TableCell("A3", "", ""));
		list.add(new TableCell("B3", "", ""));
		list.add(new TableCell("C3", "", ""));
		list.add(new TableCell("D3", "", ""));
		list.add(new TableCell("E3", "", ""));
		list.add(new TableCell("F3", "", ""));
		list.add(new TableCell("G3", "", ""));
		list.add(new TableCell("H3", "", ""));
		list.add(new TableCell("I3", "", "DP后成衣尺寸"));
		list.add(new TableCell("J3"));
		list.add(new TableCell("K3"));
		list.add(new TableCell("L3"));
		list.add(new TableCell("M3"));
		list.add(new TableCell("N3"));
		list.add(new TableCell("O3"));
		list.add(new TableCell("P3"));
		list.add(new TableCell("Q3"));
		list.add(new TableCell("R3"));
		list.add(new TableCell("S3"));
		list.add(new TableCell("T3"));
		list.add(new TableCell("U3"));
		list.add(new TableCell("V3"));
		list.add(new TableCell("W3"));
		list.add(new TableCell("X3"));
		list.add(new TableCell("Y3"));
		list.add(new TableCell("Z3"));

		list.add(new TableCell("A4", "", ""));
		list.add(new TableCell("B4", "", ""));
		list.add(new TableCell("C4", "", ""));
		list.add(new TableCell("D4", "", ""));
		list.add(new TableCell("E4", "", ""));
		list.add(new TableCell("F4", "", ""));
		list.add(new TableCell("G4", "", ""));
		list.add(new TableCell("H4", "", ""));
		list.add(new TableCell("I4", "", "面料缩率"));
		list.add(new TableCell("J4"));
		list.add(new TableCell("K4"));
		list.add(new TableCell("L4"));
		list.add(new TableCell("M4"));
		list.add(new TableCell("N4"));
		list.add(new TableCell("O4"));
		list.add(new TableCell("P4"));
		list.add(new TableCell("Q4"));
		list.add(new TableCell("R4"));
		list.add(new TableCell("S4"));
		list.add(new TableCell("T4"));
		list.add(new TableCell("U4"));
		list.add(new TableCell("V4"));
		list.add(new TableCell("W4"));
		list.add(new TableCell("X4"));
		list.add(new TableCell("Y4"));
		list.add(new TableCell("Z4"));

		list.add(new TableCell("A5", "", ""));
		list.add(new TableCell("B5", "", ""));
		list.add(new TableCell("C5", "", ""));
		list.add(new TableCell("D5", "", ""));
		list.add(new TableCell("E5", "", ""));
		list.add(new TableCell("F5", "", ""));
		list.add(new TableCell("G5", "", ""));
		list.add(new TableCell("H5", "", ""));
		list.add(new TableCell("I5", "", "DP前尺寸"));
		list.add(new TableCell("J5"));
		list.add(new TableCell("K5"));
		list.add(new TableCell("L5"));
		list.add(new TableCell("M5"));
		list.add(new TableCell("N5"));
		list.add(new TableCell("O5"));
		list.add(new TableCell("P5"));
		list.add(new TableCell("Q5"));
		list.add(new TableCell("R5"));
		list.add(new TableCell("S5"));
		list.add(new TableCell("T5"));
		list.add(new TableCell("U5"));
		list.add(new TableCell("V5"));
		list.add(new TableCell("W5"));
		list.add(new TableCell("X5"));
		list.add(new TableCell("Y5"));
		list.add(new TableCell("Z5"));

		list.add(new TableCell("A6", "", ""));
		list.add(new TableCell("B6", "", ""));
		list.add(new TableCell("C6", "", ""));
		list.add(new TableCell("D6", "", ""));
		list.add(new TableCell("E6", "", ""));
		list.add(new TableCell("F6", "", ""));
		list.add(new TableCell("G6", "", ""));
		list.add(new TableCell("H6", "", ""));
		list.add(new TableCell("I6", "", "参考尺寸"));
		list.add(new TableCell("J6"));
		list.add(new TableCell("K6"));
		list.add(new TableCell("L6"));
		list.add(new TableCell("M6"));
		list.add(new TableCell("N6"));
		list.add(new TableCell("O6"));
		list.add(new TableCell("P6"));
		list.add(new TableCell("Q6"));
		list.add(new TableCell("R6"));
		list.add(new TableCell("S6"));
		list.add(new TableCell("T6"));
		list.add(new TableCell("U6"));
		list.add(new TableCell("V6"));
		list.add(new TableCell("W6"));
		list.add(new TableCell("X6"));
		list.add(new TableCell("Y6"));
		list.add(new TableCell("Z6"));

		list.add(new TableCell("A7", "", ""));
		list.add(new TableCell("B7", "", ""));
		list.add(new TableCell("C7", "", ""));
		list.add(new TableCell("D7", "", ""));
		list.add(new TableCell("E7", "", ""));
		list.add(new TableCell("F7", "", ""));
		list.add(new TableCell("G7", "", ""));
		list.add(new TableCell("H7", "", ""));
		list.add(new TableCell("I7", "", "调整数据"));
		list.add(new TableCell("J7"));
		list.add(new TableCell("K7"));
		list.add(new TableCell("L7"));
		list.add(new TableCell("M7"));
		list.add(new TableCell("N7"));
		list.add(new TableCell("O7"));
		list.add(new TableCell("P7"));
		list.add(new TableCell("Q7"));
		list.add(new TableCell("R7"));
		list.add(new TableCell("S7"));
		list.add(new TableCell("T7"));
		list.add(new TableCell("U7"));
		list.add(new TableCell("V7"));
		list.add(new TableCell("W7"));
		list.add(new TableCell("X7"));
		list.add(new TableCell("Y7"));
		list.add(new TableCell("Z7"));

		list.add(new TableCell("A8", "", "生产订单编号"));
		list.add(new TableCell("B8", "", "衣邦人客户订单编号"));
		list.add(new TableCell("C8", "", "面料编号"));
		list.add(new TableCell("D8", "", "鲁泰面料编号"));
		list.add(new TableCell("E8", "", ""));
		list.add(new TableCell("F8", "", ""));
		list.add(new TableCell("G8", "", "宽松度"));
		list.add(new TableCell("H8", "", "后背款式"));
		list.add(new TableCell("I8", "", "长短袖"));
		list.add(new TableCell("J8", "", "领型/距扣"));
		list.add(new TableCell("K8", "", "领插片"));
		list.add(new TableCell("L8", "", "袖头/褶/祺"));
		list.add(new TableCell("M8", "", "门襟"));
		list.add(new TableCell("N8", "", "口袋"));
		list.add(new TableCell("O8", "", "纽扣/钉扣"));
		list.add(new TableCell("P8", "", "主唛"));
		list.add(new TableCell("Q8", "", "领袖明线宽"));
		list.add(new TableCell("R8", "", "侧缝工艺"));
		list.add(new TableCell("S8", "", "嵌条/压折痕"));
		list.add(new TableCell("T8", "", "刺绣字体"));
		list.add(new TableCell("U8", "", "刺绣大小"));
		list.add(new TableCell("V8", "", "刺绣位置"));
		list.add(new TableCell("W8", "", "刺绣内容"));
		list.add(new TableCell("X8", "", "刺绣颜色"));
		list.add(new TableCell("Y8", "", "洗唛成分"));
		list.add(new TableCell("Z8", "", "包装"));

		list.add(new TableCell("A9", "", ""));
		list.add(new TableCell("B9", "", ""));
		list.add(new TableCell("C9", "", ""));
		list.add(new TableCell("D9", "", ""));
		list.add(new TableCell("E9", "", ""));
		list.add(new TableCell("F9", "", ""));
		list.add(new TableCell("G9", "", ""));
		list.add(new TableCell("H9", "", ""));
		list.add(new TableCell("I9", "", ""));
		list.add(new TableCell("J9", "", ""));
		list.add(new TableCell("K9", "", ""));
		list.add(new TableCell("L9", "", ""));
		list.add(new TableCell("M9", "", ""));
		list.add(new TableCell("N9", "", ""));
		list.add(new TableCell("O9", "", ""));
		list.add(new TableCell("P9", "", ""));
		list.add(new TableCell("Q9", "", ""));
		list.add(new TableCell("R9", "", ""));
		list.add(new TableCell("S9", "", ""));
		list.add(new TableCell("T9", "", ""));
		list.add(new TableCell("U9", "", ""));
		list.add(new TableCell("V9", "", ""));
		list.add(new TableCell("W9", "", ""));
		list.add(new TableCell("X9", "", ""));
		list.add(new TableCell("Y9", "", ""));
		list.add(new TableCell("Z9", "", ""));

		list.add(new TableCell("A10", "", "生产备注："));
		list.add(new TableCell("B10", "", ""));
		list.add(new TableCell("C10", "", ""));
		list.add(new TableCell("D10", "", ""));
		list.add(new TableCell("E10", "", ""));
		list.add(new TableCell("F10", "", ""));
		list.add(new TableCell("G10", "", ""));
		list.add(new TableCell("H10", "", ""));
		list.add(new TableCell("I10", "", ""));
		list.add(new TableCell("J10", "", ""));
		list.add(new TableCell("K10", "", ""));
		list.add(new TableCell("L10", "", ""));
		list.add(new TableCell("M10", "", ""));
		list.add(new TableCell("N10", "", ""));
		list.add(new TableCell("O10", "", ""));
		list.add(new TableCell("P10", "", ""));
		list.add(new TableCell("Q10", "", ""));
		list.add(new TableCell("R10", "", ""));
		list.add(new TableCell("S10", "", ""));
		list.add(new TableCell("T10", "", ""));
		list.add(new TableCell("U10", "", ""));
		list.add(new TableCell("V10", "", ""));
		list.add(new TableCell("W10", "", ""));
		list.add(new TableCell("X10", "", ""));
		list.add(new TableCell("Y10", "", ""));
		list.add(new TableCell("Z10", "", ""));

	}

	class TableCell {
		String type;
		String nameEXCEL;
		String nameEN;
		String nameCH;
		float value;

		public TableCell(String nameEXCEL, String nameEN, String nameCH) {

			this.type = "word";
			this.nameEXCEL = nameEXCEL;
			this.nameEN = nameEN;
			this.nameCH = nameCH;
			this.value = 0;

		}

		public TableCell(String nameEXCEL) {

			this.type = "number";
			this.nameEXCEL = "";
			this.nameEN = "";
			this.nameCH = "";
			this.value = 0;

		}

	}

	TableCell getCell(String name) {
		for (int i = 0; i < list.size(); i++) {
			String nameEXCEL = list.get(i).nameEXCEL;
			String nameEN = list.get(i).nameEN;
			String nameCH = list.get(i).nameCH;
			if ("name".equals(nameEXCEL)) {
				return list.get(i);
			}
			if ("name".equals(nameEN)) {
				return list.get(i);
			}
			if ("name".equals(nameCH)) {
				return list.get(i);
			}
		}
		return null;
	}

}
