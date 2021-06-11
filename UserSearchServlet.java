package com.njit.smp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njit.smp.model.UserMessage;

/**
 * @author Atharv Tyagi <at477@njit.edu>
 * @project Social Media Project 
 * Servlet implementation to search for and display posts made by users
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected List<UserMessage> getUserPosts(String firstName, String lastName) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.getUserPosts(firstName, lastName);
    }
    
    protected String userExists(String firstName, String lastName) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.doesUserExistByName(firstName, lastName);
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
		String userSearch = request.getParameter("searchbox");
		String firstName = null;
		String lastName = null;
		RequestDispatcher dispatcher = null;
		
		if (userSearch != null) {
			String[] str = userSearch.split(" ");
			if(str.length > 1) {
				firstName = str[0];
				lastName  = str[1];
			}
			else if(str.length==1) {
				firstName = str[0];
			}
			
			//Send to database to log.
			List<UserMessage> posts = getUserPosts(firstName, lastName);
			String userFullName = userExists(firstName, lastName);
			
			request.setAttribute("searchposts", posts);
			
			if(userFullName != null) {
				request.setAttribute("fullname", userFullName);
			}
			
			dispatcher = getServletContext().getRequestDispatcher("/search-results.jsp");
			dispatcher.forward(request, response);

		}
		else {
			dispatcher = getServletContext().getRequestDispatcher("/user-landing.jsp");
			dispatcher.forward(request, response);
		}
	}
}
