package com.learneracademy.models;

public class classes {
	private int id;
	private String num;
	private String description;
	public classes(int id, String num, String description) {
		super();
		this.id = id;
		this.num = num;
		this.description = description;
	}
	public classes(String num, String description) {
		super();
		this.num = num;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
