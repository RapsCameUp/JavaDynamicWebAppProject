package com.learneracademy.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.learneracademy.dao.StudentDAO;
import com.learneracademy.dao.StudentDAO;
import com.learneracademy.models.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/students")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDAO student_dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		this.student_dao = new StudentDAO();
	}
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String request_from = request.getServletPath(); //.toString().trim();
		
		String id = request.getParameter("id");
		if(id != null) {
			
			String action = request.getParameter("action");
			if(action == null ){
				this.ListOfStudents(request, response);
			}
			else if(action.equals("view")) {
				this.GetStudentByID(request, response);
			}
			else if(action.equals("delete")) {
				this.DeleteStudent(request, response);
			}
		}
		else {
			this.ListOfStudents(request, response);
		}
	}
	
	private void ListOfStudents(HttpServletRequest request, HttpServletResponse response) {

		try {
			List<Student> list_of_Students = student_dao.getAllStudent();
			request.setAttribute("ListOfStudents", list_of_Students);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/students.jsp");
			dispatcher.forward(request, response);

		} catch (ServletException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

	private void AddNewStudent(HttpServletRequest request, HttpServletResponse response) {

		try {
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String cell = request.getParameter("cell");
			String email = request.getParameter("email");
			String studentclass = request.getParameter("studentclass");
			
			Student new_Student = new Student(name, surname, cell,email,studentclass);
			student_dao.AddStudent(new_Student);
			
			request.setAttribute("status", "success");

			response.sendRedirect("students");

		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

	private void DeleteStudent(HttpServletRequest request, HttpServletResponse response) {

		try {
			student_dao.removeStudent(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("status", "success");
			response.sendRedirect("students");
		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

	private void UpdateStudentDetails(HttpServletRequest request, HttpServletResponse response) {

		try {
			int id = Integer.parseInt(request.getParameter("id"));

			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String cell = request.getParameter("cell");
			String email = request.getParameter("email");
			String studentclass = request.getParameter("studentclass");

			Student Student = new Student(id, name, surname, cell,email,studentclass);
			student_dao.updateStudentDetails(Student);
			request.setAttribute("status", "success");
			response.sendRedirect("students");
		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

	private void GetStudentByID(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Student Student = student_dao.getAStudentByID(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Student-view.jsp");
			request.setAttribute("student", Student);

			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action").trim();
		
		if(action.equals("doPut")) {
			this.doPut(request, response);
		}
		else if(action.equals("doAdd")){
			this.AddNewStudent(request, response);
		}
	}

}
