package com.njit.smp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njit.smp.model.User;

/**
 * @author Atharv Tyagi <at477@njit.edu>
 * @project Social Media Project 
 * Servlet implementation for administrative tools
 */
@WebServlet("/AdminToolsServlet")
public class AdminToolsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminToolsServlet() {
        super();
    }
    
    protected User searchResult(String firstName, String lastName) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.getUser(firstName, lastName);
    }
    
    protected int banUser(String username) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.banUser(username);
    }
    
    protected int unBanUser(String username) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.unBanUser(username);
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
		String search = request.getParameter("search");
		String action = request.getParameter("action");
		
		String fullName = request.getParameter("searchbox");
		String username = request.getParameter("username");
		
		String firstName = null;
		String lastName  = null;
		
		RequestDispatcher dispatcher = null;
		
		//Split first and last name
		if (fullName != null) {
			String[] str = fullName.split(" ");
			if(str.length > 1) {
				firstName = str[0];
				lastName  = str[1];
			}
			else if(str.length==1) {
				firstName = str[0];
			}
		}
		
		//Handle actions
		if (search != null) {
			User us = searchResult(firstName, lastName);
			
			request.setAttribute("result", us);
			
			dispatcher = getServletContext().getRequestDispatcher("/admin-landing.jsp");
			dispatcher.forward(request, response);
		}
		else if (action != null) {
			int success = -1;
			
			if (action.equals("DE-ACTIVATE")) {
				success = banUser(username);
			}
			else {
				success = unBanUser(username);
			}
			
			request.setAttribute("success", success);
			
			dispatcher = getServletContext().getRequestDispatcher("/admin-landing.jsp");
			dispatcher.forward(request, response);
		}
	}

}
