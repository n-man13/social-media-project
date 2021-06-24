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
     * authenticate
     * @param  username username to pass to database
     * @param  password password to pass to database 
     * @return 1 if user can be signed in, 2 if admin, otherwise 0
     * @throws SQLException 
     */
    protected int authenticate(String username, String password) throws SQLException {
    	DBConnector connector = DBConnector.getInstance();
    	int isAuth = connector.signIn(username, password);
    	
    	return isAuth;
	}
    
    /** 
     * getFirstName
     * @param  username username to pass to database
     * @param  password password to pass to database 
     * @return first name of the user searched
     * @throws SQLException 
     */
    
    protected String getFirstName(String username, String password) {
    	DBConnector connector = DBConnector.getInstance();
    	String fName = connector.getFName(username, password);
    	
    	return fName;
    }
    
    /** 
     * getLastName
     * @param  username username to pass to database
     * @param  password password to pass to database 
     * @return last name of the user searched
     * @throws SQLException 
     */
    
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
    	String resetFullName = request.getParameter("resetfullname");
    	String firstName = "";
    	String lastName  = "";
    	int authVal = 0;
    	HttpSession hs = request.getSession();
    	
    	RequestDispatcher dispatcher = null;
    	
    	//Check username/password against database
    	try {
    		authVal = authenticate(username, password);
    		firstName = getFirstName(username, password);
    		lastName = getLastName(username, password);
    		System.out.println(authVal);
			if (authVal == 1) {
				//Redirect to user landing
				dispatcher = getServletContext().getRequestDispatcher("/user-landing.jsp");
				
				hs.setAttribute("uname", username);
				hs.setAttribute("fname", firstName);
				hs.setAttribute("lname", lastName);
				
				dispatcher.forward(request, response);
			}
			else if (authVal == 2) {
				//Redirect to admin landing
				dispatcher = getServletContext().getRequestDispatcher("/admin-landing.jsp");
				
				hs.setAttribute("uname", username);
				hs.setAttribute("fname", firstName);
				hs.setAttribute("lname", lastName);
				
				dispatcher.forward(request, response);
			}
			else if (authVal == 3) {
				System.out.println("from servlet: banned");
				//Redirect login
				dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				
				request.setAttribute("error", "11");
				
				dispatcher.forward(request, response);
			}
			else {
				if (resetFullName != null) {
					hs.removeAttribute("messaging");
					
					dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
					dispatcher.forward(request, response);
					
					return;
				}
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