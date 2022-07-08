package com.learneracademy.models;

public class Subjects {
	private int id;
	private String name;
	private String description;
	private String classnum;
	
	public Subjects(int id, String name, String description,String classnum) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.classnum = classnum;
	}

	public Subjects(String name, String description,String classnum) {
		super();
		this.name = name;
		this.description = description;
		this.classnum = classnum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassnum() {
		return classnum;
	}

	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}
	
	
}
