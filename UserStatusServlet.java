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
    
    protected boolean logPost(String username, String userPost, String pageName) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.pushPost(username, userPost, pageName);
    }
    
    protected boolean logReply(String username, String userPost, int postId, String pageName) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.pushReply(username, userPost, postId, pageName);
    }
    
    protected List<UserMessage> getAllPosts(String pageName) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	return connector.getAllPosts(pageName);
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
		String userPost 	= request.getParameter("userpagetextbox");
		String userReply 	= request.getParameter("replytextbox");
		String pageLoad 	= request.getParameter("initload");
		String postRedirect = request.getParameter("redirect");
		String pageName		= request.getParameter("pagename");
		
		String username 	  = request.getParameter("username");
		String postRedirectId = request.getParameter("postRedirectId");
		
		boolean success 			 = false;
		RequestDispatcher dispatcher = null;
		
		//Send to database to log.
		
		if (userPost != null) {
			success = logPost(username, userPost, pageName);
		}
		if (userReply != null) {
			int postId = Integer.parseInt(request.getParameter("postId"));
			success    = logReply(username, userReply, postId, pageName);
		}
		if (pageLoad != null) {
			success = true;
		}
		if (postRedirect != null) {
			success = true;
		}
		
		if (success) {
			String redirectUrl = "/user-landing.jsp";
			
			List<UserMessage> posts = getAllPosts(pageName);
			request.setAttribute("posts", posts);
			
			if (postRedirectId != null) { request.setAttribute("postRedirectId", postRedirectId); }
			
			switch (pageName) {
				case "videogames":
					redirectUrl = "/videogames.jsp";
					break;
				case "music":
					redirectUrl = "/music.jsp";
					break;
				case "artsncrafts":
					redirectUrl = "/artsncrafts.jsp";
					break;
				case "technology":
					redirectUrl = "/technology.jsp";
					break;
				case "sports":
					redirectUrl = "/sports.jsp";
					break;
			}
			
			dispatcher = getServletContext().getRequestDispatcher(redirectUrl);
			dispatcher.forward(request, response);
		}
		else {
			dispatcher = getServletContext().getRequestDispatcher("/user-landing.jsp");
			dispatcher.forward(request, response);
		}
	}

}
