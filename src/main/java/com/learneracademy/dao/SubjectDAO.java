package com.learneracademy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.learneracademy.models.Student;
import com.learneracademy.models.Subjects;

public class SubjectDAO {
	
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
	
	public void AddSubject(Subjects subject) {

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO subject" + "  (name, description,classnum) VALUES " + " (?, ?,?);")) {
			preparedStatement.setString(1, subject.getName());
			preparedStatement.setString(2, subject.getDescription());
			preparedStatement.setString(3, subject.getClassnum());

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
	
	public List<Subjects> getAllSubjects() {
		
		List<Subjects> subjects = new ArrayList<>();
		
		try (Connection connection = getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement("select * from subject");) {
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String classnum = rs.getString("classnum");
				subjects.add(new Subjects(id, name, description,classnum));
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return subjects;
	}
	
	public boolean removeSubject(int id) {
		boolean deleted = false;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("delete from subject where id = ?;");) {
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
