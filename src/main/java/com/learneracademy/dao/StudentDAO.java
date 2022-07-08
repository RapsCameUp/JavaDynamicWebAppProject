package com.learneracademy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.learneracademy.models.Student;

public class StudentDAO {
	
	Connection connection = null;
	
	protected Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb", "root", "12345678mySQL");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return connection;
	}

	public void AddStudent(Student student) {

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO student" + "  (name, surname, cell,email,studentclass) VALUES " + " (?, ?, ?,?,?);")) {
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getSurname());
			preparedStatement.setString(3, student.getCell());
			preparedStatement.setString(4, student.getEmail());
			preparedStatement.setString(5, student.getStudentClass());

			preparedStatement.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Student> getAllStudent() {
		
		List<Student> students = new ArrayList<>();
		
		try (Connection connection = getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement("select * from student");) {
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String cell = rs.getString("cell");
				String email = rs.getString("email");
				String studentclass = rs.getString("studentclass");
				students.add(new Student(id, name, surname, cell,email,studentclass));
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return students;
	}
	
	public Student getAStudentByID(int id) {
		Student student = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select * from student where id =?");) {
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String cell = rs.getString("cell");
				String email = rs.getString("email");
				String studentclass = rs.getString("studentclass");
				
				student = new Student(id, name, surname, cell,email,studentclass);
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return student;
	}
	
	public boolean updateStudentDetails(Student student) {
		
		boolean updated = false;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("update student set name = ?,surname= ?, cell =?, email =?, studentclass = ?, where id = ?;");) {
			
			
			statement.setString(1, student.getName());
			statement.setString(2, student.getSurname());
			statement.setString(3, student.getCell());
			statement.setString(4, student.getEmail());
			statement.setString(5, student.getStudentClass());
			statement.setInt(6, student.getId());

			if(statement.executeUpdate() > 0) {
				updated = true;
			}
			
		}
		 catch (Exception ex) {
				ex.printStackTrace();
			}
		return updated;
		
	}
	
	public boolean removeStudent(int id) {
		boolean deleted = false;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("delete from student where id = ?;");) {
			statement.setInt(1, id);
			
			if(statement.executeUpdate() > 0) {
				deleted = true;
			}
		}
		 catch (Exception ex) {
				ex.printStackTrace();
			}
		return deleted;
	}


}
