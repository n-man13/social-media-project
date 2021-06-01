import java.io.IOException;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
/**
 * @author Atharv Tyagi <at477@njit.edu>
 * @project Social Media Project 
 * Servlet implementation to sign up new users
 */
@WebServlet("/UserSignInServlet")  
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
     */
    protected boolean signUp(String username, String password, String firstName, String lastName, String email) {
    	DBConnector con = new DBConnector("jdbc:mysql://localhost:3306/HobbyHome", username, password);
    	
    	return con.addUser(username, password, firstName, lastName, email);
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
    	String firstName = request.getParameter("firstName");
    	String lastName  = request.getParameter("lastName");
    	String email 	 = request.getParameter("email");
    	
    	//Send new user information to database
    	if (signUp(username, password, firstName, lastName, email)) {
    		//Redirect to user landing
    		response.sendRedirect("http://localhost:8080/BasicWebApplication/login.html");
    	}
    	else {
    		//Redirect to signup
    		response.sendRedirect("http://localhost:8080/BasicWebApplication/signup.html");
    	}
    }  
  
} 