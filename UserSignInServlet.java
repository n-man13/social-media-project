package com.njit.smp.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
  
/**
 * @author Atharv Tyagi <at477@njit.edu>
 * @project Social Media Project 
 * Servlet implementation to sign in existing users to website
 */
@WebServlet("/UserSignInServlet")  
public class UserSignInServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;
         
    /** 
     * @see HttpServlet#HttpServlet() 
     */  
    public UserSignInServlet() {  
        super();
    }
    
    
    /** 
     * Authenticate
     * @param username username to pass to database
     * @param password password to pass to database 
     * @throws SQLException 
     */
    protected int authenticate(String username, String password) throws SQLException {
    	System.out.println("connecting to db");    	
    	DBConnector connector = DBConnector.getInstance();
    	System.out.println("connected to db");
    	int isAuth = connector.signIn(username, password);
    	System.out.println("isAuth = "+isAuth);
    	return isAuth;
	}
    
    protected String getFirstName(String username, String password) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	String fName = connector.getFName(username, password);
    	
    	return fName;
    }
    
    protected String getLastName(String username, String password) {
    	DBConnector connector = DBConnector.getInstance();
    	
    	String lName = connector.getLName(username, password);
    	
    	return lName;
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
    	String password  = request.getParameter("password");
    	String firstName = "";
    	String lastName  = "";
    	System.out.println("inside servlet user = "+ username + "  and pass = " + password);
    	int authVal = 0;
    	HttpSession hs = request.getSession();
    	
    	RequestDispatcher dispatcher = null;
    	
    	//Check username/password against database
    	try {
    		authVal = authenticate(username, password);
    		firstName = getFirstName(username, password);
    		lastName = getLastName(username, password);
    		
			if (authVal == 1) {
				System.out.println("redirecting to user-landing");
				//Redirect to user landing
				dispatcher = getServletContext().getRequestDispatcher("/user-landing.jsp");
				
				hs.setAttribute("uname", username);
				hs.setAttribute("fname", firstName);
				hs.setAttribute("lname", lastName);
				
				dispatcher.forward(request, response);
			}
			else if (authVal == 2) {
				System.out.println("redirecting to admin-landing");
				//Redirect to admin landing
				dispatcher = getServletContext().getRequestDispatcher("/admin-landing.jsp");
				
				hs.setAttribute("uname", username);
				hs.setAttribute("fname", firstName);
				hs.setAttribute("lname", lastName);
				
				dispatcher.forward(request, response);
			}
			else {
				System.out.println("redirecting to login");
				//Redirect to login
				dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				
				request.setAttribute("error", "10");
				
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println(e.toString());
		}
    }  
  
} 