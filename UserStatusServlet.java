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
 * Servlet implementation to store and display posts made by users
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
    
    protected boolean logPost(String username, String userPost) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.pushPost(username, userPost);
    }
    
    protected boolean logReply(String username, String userPost, int postId) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.pushReply(username, userPost, postId);
    }
    
    protected List<UserMessage> getAllPosts() {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.getAllPosts();
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
		String userReply = request.getParameter("replytextbox");
		String pageLoad = request.getParameter("initload");
		String username = request.getParameter("username");
		
		boolean success = false;
		RequestDispatcher dispatcher = null;
		
		//Send to database to log.
		
		if (userPost != null) {
			success = logPost(username, userPost);
		}
		if (userReply != null) {
			int postId = Integer.parseInt(request.getParameter("postId"));
			success = logReply(username, userReply, postId);
		}
		if (pageLoad != null) {
			success = true;
		}
		
		if (success) {
			List<UserMessage> posts = getAllPosts();
			request.setAttribute("posts", posts);
			
			dispatcher = getServletContext().getRequestDispatcher("/videogames.jsp");
			dispatcher.forward(request, response);
		}
		else {
			dispatcher = getServletContext().getRequestDispatcher("/user-landing.jsp");
			dispatcher.forward(request, response);
		}
	}

}
