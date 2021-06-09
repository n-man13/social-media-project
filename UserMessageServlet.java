package com.njit.smp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njit.smp.model.DirectMessage;
import com.njit.smp.model.UserMessage;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/UserSearchServlet")
public class UserMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /*protected List<DirectMessage> getUserMessages(String firstName, String lastName) {
    	System.out.println("connecting to db");    	
    	DBConnector connector = DBConnector.getInstance();
    	System.out.println("connected to db");
    	
    	return connector.getUserMessages(firstName, lastName);
    }*/
    
    protected String userExists(String firstName, String lastName) {
    	System.out.println("connecting to db");    	
    	DBConnector connector = DBConnector.getInstance();
    	System.out.println("connected to db");
    	
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
		String userSearch = request.getParameter("searchbox2");
		String userMessage = request.getParameter("usermessage");
		String username = request.getParameter("username");
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
		
			System.out.println("inside servlet userSearch = " + userSearch + " " + "firstname = " + firstName + " " + "lastname = " + lastName);
			//Send to database to log.
			List<DirectMessage> messages = new ArrayList();//getUserMessages(username, firstName, lastName);
			String userFullName = userExists(firstName, lastName);
			
			request.setAttribute("messages", messages);
			
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
