package com.njit.smp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserStatusServlet
 */
@WebServlet("/UserStatusServlet")
public class UserStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserStatusServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPost = request.getParameter("userpagetextbox");
		String username = "";
		System.out.println("inside servlet userPost = "+ userPost);
		//Send to database to log.
		
		RequestDispatcher dispatcher = null;
		
		dispatcher = getServletContext().getRequestDispatcher("/user-profile.jsp");
		
		request.setAttribute("user", username);
		request.setAttribute("userpost", userPost);
		
		dispatcher.forward(request, response);
	}

}
