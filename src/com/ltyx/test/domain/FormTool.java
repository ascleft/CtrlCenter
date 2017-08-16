package com.ltyx.test.domain;

import java.util.ArrayList;
import java.util.List;

public class FormTool {

	public List<Item> dojob() {
		List<Item> list = new ArrayList<Item>();
		list.add(new Item("jack", "11"));
		list.add(new Item("amy", "11"));
		list.add(new Item("jodan", "51"));

		return list;
	}
}
