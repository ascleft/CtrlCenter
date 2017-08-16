package com.ltyx.test.domain;

public class Item {
	public Item(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String name;
	public String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
