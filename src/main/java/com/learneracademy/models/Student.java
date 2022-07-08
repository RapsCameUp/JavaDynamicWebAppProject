package com.learneracademy.models;

public class Student {
	private int id;
	private String name;
	private String surname;
	private String cell;
	private String email;
	private String studentclass;
	
	public Student(int id, String name, String surname, String cell, String email,String studentclass) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.cell = cell;
		this.email = email;
		this.studentclass = studentclass;
	}
	
	public Student(String name, String surname, String cell, String email,String studentclass) {
		super();
		this.name = name;
		this.surname = surname;
		this.cell = cell;
		this.email = email;
		this.studentclass = studentclass;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getStudentClass() {
		return studentclass;
	}

	public void setStudentClass(String studentclass) {
		this.studentclass = studentclass;
	}
	
}
