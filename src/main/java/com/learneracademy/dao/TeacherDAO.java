package com.learneracademy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.learneracademy.models.Teacher;

public class TeacherDAO {

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

	public void AddNewTeacher(Teacher teacher) {

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO teacher" + "  (name, surname, cell) VALUES " + " (?, ?, ?);")) {
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getSurname());
			preparedStatement.setString(3, teacher.getCell());

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
	
	public List<Teacher> getAllTeachers() {
		
		List<Teacher> teachers = new ArrayList<>();
		
		try (Connection connection = getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement("select * from teacher");) {
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String cell = rs.getString("cell");
				teachers.add(new Teacher(id, name, surname, cell));
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return teachers;
	}
	
	public Teacher getATeacherByID(int id) {
		Teacher teacher = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select * from teacher where id =?");) {
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String cell = rs.getString("cell");
				
				teacher = new Teacher(id, name, surname, cell);
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return teacher;
	}
	
	public boolean updateTeacherDetails(Teacher teacher) {
		
		boolean updated = false;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("update teacher set name = ?,surname= ?, cell =? where id = ?;");) {
			
			
			statement.setString(1, teacher.getName());
			statement.setString(2, teacher.getSurname());
			statement.setString(3, teacher.getCell());
			statement.setInt(4, teacher.getId());

			if(statement.executeUpdate() > 0) {
				updated = true;
			}
			
		}
		 catch (Exception ex) {
				ex.printStackTrace();
			}
		return updated;
		
	}
	
	public boolean removeTeacher(int id) {
		boolean deleted = false;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("delete from teacher where id = ?;");) {
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
