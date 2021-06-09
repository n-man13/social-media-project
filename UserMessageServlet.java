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
import com.njit.smp.model.UserMessage;

/**
 * Servlet implementation class UserMessageServlet
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
    
    protected String userExists(String firstName, String lastName) {
    	System.out.println("connecting to db");    	
    	DBConnector connector = DBConnector.getInstance();
    	System.out.println("connected to db");
    	
    	return connector.doesUserExistByName(firstName, lastName);
    }
    
    protected List<DirectMessage> getMessages(String username, String firstName, String lastName) {
    	System.out.println("connecting to db from getmessage");
    	DBConnector connector = DBConnector.getInstance();
    	System.out.println("connected to db from getmessage");
    	
    	return connector.getMessages(username, firstName, lastName);
    }
    
    protected boolean pushMessage(String sendingUser, String firstName, String lastName, String message) {
    	System.out.println("connecting to db from pushmessage");    	
    	DBConnector connector = DBConnector.getInstance();
    	System.out.println("connected to db from pushmessage");
    	
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
		String username = request.getParameter("username");
		String otherPerson = request.getParameter("messageto");
		String messageContent = request.getParameter("messagetextbox");
		System.out.println("otherPerson = " + otherPerson);
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
			System.out.println("inside servlet userMessage = " + userSearch + " " + "firstname = " + firstName + " " + "lastname = " + lastName);
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
			System.out.println("inside servlet userMessage = " + otherPerson + " " + "firstname = " + firstName + " " + "lastname = " + lastName);
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
