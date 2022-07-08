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
import com.learneracademy.models.Subjects;
import com.learneracademy.models.classes;

/**
 * Servlet implementation class ClassServlet
 */
@WebServlet("/classes")
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private ClassesDAO classes_dao;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
	public ClassServlet() {
		this.classes_dao = new ClassesDAO();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ListOfClasses(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.AddNewClass(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void ListOfClasses(HttpServletRequest request, HttpServletResponse response) {

		try {
			List<classes> list_of_classes = classes_dao.getAllClasses();
			request.setAttribute("ListOfClasses", list_of_classes);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/classes.jsp");
			dispatcher.forward(request, response);

		} catch (ServletException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

	private void AddNewClass(HttpServletRequest request, HttpServletResponse response) {

		try {
			String num = request.getParameter("num");
			String description = request.getParameter("description");
			
			classes new_class = new classes(num, description);
			classes_dao.AddNewClass(new_class);
			
			request.setAttribute("status", "success");

			response.sendRedirect("classes");

		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}
	
	private void DeleteClass(HttpServletRequest request, HttpServletResponse response) {

		try {
			classes_dao.removeClass(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("status", "success");
			response.sendRedirect("classes");
		} catch (IOException e) {
			request.setAttribute("status", "failed");
			e.printStackTrace();
		}
	}

}
