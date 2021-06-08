package com.njit.smp.servlets;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
/**
 * @author Atharv Tyagi <at477@njit.edu>
 * @project Social Media Project 
 * Servlet implementation to sign up new users to website
 */
@WebServlet("/UserSignUpServlet")  
public class UserSignUpServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
         
    /** 
     * @see HttpServlet#HttpServlet() 
     */  
    public UserSignUpServlet() {  
        super();
    }
    
    
    /** 
     * Signup
     * @param username 		username to pass to database
     * @param password 		password to pass to database 
     * @param lastName 		last name of user to pass to database
     * @param firstName 	first name of user to pass to database
     * @param email 		email of user to pass to database
     * @throws SQLException 
     */
    protected boolean signUp(String username, String password, String firstName, String lastName, String email) throws SQLException {
    	System.out.println("connecting to db");    	
    	DBConnector connector = DBConnector.getInstance();
    	System.out.println("connected to db");    	
    	return connector.addUser(username, password, firstName, lastName, email);
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
    	String username  = request.getParameter("username");
    	String password  = request.getParameter("password1");
    	String firstName = request.getParameter("firstname");
    	String lastName  = request.getParameter("lastname");
    	String email 	 = request.getParameter("email");
    	
    	//Send new user information to database
    	try {
			if (signUp(username, password, firstName, lastName, email)) {
				//Redirect to user landing
				response.sendRedirect("/");
			}
			else {
				//Redirect to signup
				//request.setAttribute("GEN_ERROR", e.getMessage());
				response.sendRedirect("signup.html");
			}
		} catch (SQLException | IOException e) {
			e.getMessage();
		}
    }  
  
} 