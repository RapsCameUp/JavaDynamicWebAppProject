package com.learneracademy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.learneracademy.models.Subjects;
import com.learneracademy.models.classes;

public class ClassesDAO {

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
	
	public void AddNewClass(classes new_class) {

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO class" + "  (num, description) VALUES " + " (?, ?);")) {
			preparedStatement.setString(1, new_class.getNum());
			preparedStatement.setString(2, new_class.getDescription());

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
	
	public List<classes> getAllClasses() {
		
		List<classes> classes = new ArrayList<>();
		
		try (Connection connection = getConnection();
				
			PreparedStatement preparedStatement = connection.prepareStatement("select * from class");) {
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String num = rs.getString("num");
				String description = rs.getString("description");
				classes.add(new classes(id, num, description));
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return classes;
	}
	
	public boolean removeClass(int id) {
		boolean deleted = false;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("delete from class where id = ?;");) {
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
