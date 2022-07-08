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

import com.learneracademy.dao.ClassesDAO;
import com.learneracademy.dao.SubjectDAO;
import com.learneracademy.dao.TeacherDAO;
import com.learneracademy.models.*;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/teachers")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherDAO teacher_dao;
	private ClassesDAO classes_dao;
	private SubjectDAO subject_dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherServlet() {
		this.teacher_dao = new TeacherDAO();
		this.classes_dao = new ClassesDAO();
		this.subject_dao = new SubjectDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String request_from = request.getServletPath(); //.toString().trim();
		
		String id = request.getParameter("id");
		if(id != null) {
			
			String action = request.getParameter("action");
			if(action == null ){
				this.ListOfTeachers(request, response);
			}
			else if(action.equals("view")) {
				this.GetTeacherByID(request, response);
			}
			else if(action.equals("delete")) {
				this.DeleteTeacher(request, response);
			}
		}
		else {
			this.ListOfTeachers(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action").trim();
		
		if(action.equals("doPut")) {
			this.doPut(request, response);
		}
		else if(action.equals("doAdd")){
			this.AddNewTeacher(request, response);
		}
		else if(action.equals("doAssignTeacher")){
			this.AddNewTeacher(request, response);
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.UpdateTeacherDetails(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.DeleteTeacher(request, response);
	}

	private void ListOfTeachers(HttpServletRequest request, HttpServletResponse response) {

		try {
			List<Teacher> list_of_teachers = teacher_dao.getAllTeachers();
			request.setAttribute("ListOfTeachers", list_of_teachers);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/teachers.jsp");
			dispatcher.forward(request, response);

		} catch (ServletException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

	private void AddNewTeacher(HttpServletRequest request, HttpServletResponse response) {

		try {
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String cell = request.getParameter("cell");
			Teacher new_teacher = new Teacher(name, surname, cell);
			teacher_dao.AddNewTeacher(new_teacher);
			
			request.setAttribute("status", "success");

			response.sendRedirect("teachers");

		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

	private void DeleteTeacher(HttpServletRequest request, HttpServletResponse response) {

		try {
			teacher_dao.removeTeacher(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("status", "success");
			response.sendRedirect("teachers");
		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

	private void UpdateTeacherDetails(HttpServletRequest request, HttpServletResponse response) {

		try {
			int id = Integer.parseInt(request.getParameter("id"));

			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String cell = request.getParameter("cell");

			Teacher teacher = new Teacher(id, name, surname, cell);
			teacher_dao.updateTeacherDetails(teacher);
			request.setAttribute("status", "success");
			response.sendRedirect("teachers");
		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

	private void GetTeacherByID(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Teacher teacher = teacher_dao.getATeacherByID(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-view.jsp");
			request.setAttribute("teacher", teacher);
			
			List<classes> list_of_classes = classes_dao.getAllClasses();
			request.setAttribute("ListOfClasses", list_of_classes);
			
			List<Subjects> list_of_Subjects = subject_dao.getAllSubjects();
			request.setAttribute("ListOfSubjects", list_of_Subjects);

			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
