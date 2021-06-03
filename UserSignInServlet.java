import java.io.IOException;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
/**
 * @author Atharv Tyagi <at477@njit.edu>
 * @project Social Media Project 
 * Servlet implementation to sign in existing users
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
     */
    protected boolean authenticate(String username, String password) {
    	DBConnector con = new DBConnector("jdbc:mysql://localhost:3306/HobbyHome", username, password);
    	
    	return con.signIn(username, password);
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
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	//Check username/password against database
    	if (authenticate(username, password)) {
    		//Redirect to user landing
    		response.sendRedirect("http://localhost:8080/BasicWebApplication/user-landing.html");
    	}
    	else {
    		//Redirect to login
    		response.sendRedirect("http://localhost:8080/BasicWebApplication/login.html");
    	}
    }  
  
} 
