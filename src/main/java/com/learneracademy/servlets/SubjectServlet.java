package com.learneracademy.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.learneracademy.dao.ClassesDAO;
import com.learneracademy.dao.StudentDAO;
import com.learneracademy.dao.SubjectDAO;
import com.learneracademy.models.Student;
import com.learneracademy.models.Subjects;
import com.learneracademy.models.classes;

/**
 * Servlet implementation class SubjectServlet
 */
@WebServlet("/subjects")
public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDAO subject_dao;
	private ClassesDAO classes_dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public SubjectServlet() {
		this.subject_dao = new SubjectDAO();
		this.classes_dao = new ClassesDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ListOfSubjects(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.AddNewSubject(request, response);
	}
	
	private void ListOfSubjects(HttpServletRequest request, HttpServletResponse response) {

		try {
			List<Subjects> list_of_Subjects = subject_dao.getAllSubjects();
			request.setAttribute("ListOfSubjects", list_of_Subjects);
			
			List<classes> list_of_classes = classes_dao.getAllClasses();
			request.setAttribute("ListOfClasses", list_of_classes);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/subjects.jsp");
			dispatcher.forward(request, response);

		} catch (ServletException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

	private void AddNewSubject(HttpServletRequest request, HttpServletResponse response) {

		try {
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String classnum = request.getParameter("classnum");
			
			Subjects new_subject = new Subjects(name, description,classnum);
			subject_dao.AddSubject(new_subject);
			
			request.setAttribute("status", "success");

			response.sendRedirect("subjects");

		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}
	
	private void DeleteSubject(HttpServletRequest request, HttpServletResponse response) {

		try {
			subject_dao.removeSubject(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("status", "success");
			response.sendRedirect("subjects");
		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

}
