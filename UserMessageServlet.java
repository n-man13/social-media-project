package com.njit.smp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.njit.smp.model.DirectMessage;

/**
 * @author Atharv Tyagi <at477@njit.edu>
 * @project Social Media Project 
 * Servlet implementation to message other users
 */
@WebServlet("/UserMessageServlet")
public class UserMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMessageServlet() {
        super();
    }
    
    /**
	 * Method to check if a user exists given their first and last name
	 * @param  firstName	first name of requested user
	 * @param  lastName  last name of requested user
	 * @return true if user exists, false if user does not exist
	**/
    
    protected String userExists(String firstName, String lastName) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.doesUserExistByName(firstName, lastName);
    }
    
    /**
	 * Returns a list of Messages between two users
	 * @param username 			username logged in and asking for message history
	 * @param firstname			first name of user that is in contact with main user
	 * @param lastname			last name of user that is in contact with main user
	 * @return 					List of messages between the two specified users ordered by messageID
	 */
    
    protected List<DirectMessage> getMessages(String username, String firstName, String lastName) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.getMessages(username, firstName, lastName);
    }
    
    /**
	 * Adds a new Message to the Database
	 * 
	 * @param sendingUser		username associated to post
	 * @param receivingUser		post content to be written
	 * @param message			content of message
	 * @return 					true if message was sent, otherwise false
	 */
    
    protected boolean pushMessage(String sendingUser, String firstName, String lastName, String message) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.pushMessage(sendingUser, firstName, lastName, message);
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
		String messageUser = request.getParameter("messageuser");
		String sendMessage = request.getParameter("sendmessage");
		String resetFullName = request.getParameter("resetfullname");
		
		String username = request.getParameter("username");
		String otherPerson = request.getParameter("messageto");
		String messageContent = request.getParameter("messagetextbox");
		
		String firstName = null;
		String lastName = null;
		RequestDispatcher dispatcher = null;
		
		HttpSession hs = request.getSession();
		
		if (userSearch != null) {
			String[] str = userSearch.split(" ");
			if(str.length > 1) {
				firstName = str[0];
				lastName  = str[1];
			}
			else if(str.length==1) {
				firstName = str[0];
			}
		}
		
		if (otherPerson != null) {
			String[] str = otherPerson.split(" ");
			if(str.length > 1) {
				firstName = str[0];
				lastName  = str[1];
			}
			else if(str.length==1) {
				firstName = str[0];
			}
		}
		
		String userFullName = userExists(firstName, lastName);
		
		if (messageUser != null) {
			//Pull messages from database
			List<DirectMessage> messages = getMessages(username, firstName, lastName);
			
			hs.setAttribute("messaging", userFullName);
			
			request.setAttribute("messages", messages);
			request.setAttribute("fullname", userFullName);
			
			dispatcher = getServletContext().getRequestDispatcher("/user-inbox.jsp");
			dispatcher.forward(request, response);
		}
		else if (resetFullName != null) {
			hs.removeAttribute("messaging");
			
			dispatcher = getServletContext().getRequestDispatcher("/user-landing.jsp");
			dispatcher.forward(request, response);
		}
		else if (sendMessage != null) {
			//Send message to database
			if (pushMessage(username, firstName, lastName, messageContent)) {
				//Successfully pushed message
				
				//Pull messages from database
				List<DirectMessage> messages = getMessages(username, firstName, lastName);
				
				request.setAttribute("messages", messages);
				request.setAttribute("fullname", userFullName);
				
				dispatcher = getServletContext().getRequestDispatcher("/user-inbox.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if(userFullName != null) {
			request.setAttribute("fullname", userFullName);
			
			dispatcher = getServletContext().getRequestDispatcher("/user-inbox.jsp");
			dispatcher.forward(request, response);
		}
		else {
			dispatcher = getServletContext().getRequestDispatcher("/user-inbox.jsp");
			dispatcher.forward(request, response);
		}
	}
}
