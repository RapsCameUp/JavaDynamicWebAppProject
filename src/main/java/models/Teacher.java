package models;

public class Teacher {
	
	private int id;
	private String name;
	private String surname;
	private String cell;
	
	public Teacher(int id, String name, String surname, String cell) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.cell = cell;
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

	public void setPhoneNumbers(String cell) {
		this.cell = cell;
	}

}
