package com.njit.smp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njit.smp.model.BoredItem;

/**
 * @author Atharv Tyagi <at477@njit.edu>
 * @project Social Media Project 
 * Servlet implementation to retrieve activities from Bored API
 */
@WebServlet("/UserActivityServlet")
public class UserActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserActivityServlet() {
        super();
    }
    
    /**
	 * getActivity
	 * Method to retrieve an activity within the specified search parameters from Bored API
	 * @param  activity name of the activity requested
	 * @param  price price range in USD of the activity requested
	 * @param  accessibility accessibility range (0-1) of the activity requested
	 * @return BoredItem object containing the activity, price, and accessibility
	**/
    
    protected BoredItem getActivity(String activity, String price, String accessibility) {
    	APIHook api = new APIHook();
    	BoredItem act = null;
    	
    	//Make call to api to get activity
    	try {
    		act = api.getHTML(activity, price, accessibility);
			
		} catch (Exception e) {
			System.err.println(e.toString());
		}
    	
    	return act;
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
		String choice = request.getParameter("choice");
		String cost = request.getParameter("cost");
		String difficulty = request.getParameter("difficulty");
		
		RequestDispatcher dispatcher = null;
		
		if (choice != null) {
			BoredItem activity = null;
			
			if (choice == "random") {
				activity = getActivity(choice, "", "");
			}
			else {
				String price = "";
				String accessibility = "";
				
				switch (cost.toLowerCase()) {
					case "random":
						price = "&minprice=0&maxprice=1";
						break;
					case "free":
						price = "&minprice=0&maxprice=0";
						break;
					case "low":
						price = "&minprice=0&maxprice=0.24";
						break;
					case "moderate":
						price = "&minprice=0.25&maxprice=0.49";
						break;
					case "high":
						price = "&minprice=0.50&maxprice=0.74";
						break;
					case "expensive":
						price = "&minprice=0.75&maxprice=1";
						break;
				}
				
				switch (difficulty.toLowerCase()) {
					case "random":
						accessibility = "&minaccessibility=0&maxaccessibility=1";
						break;
					case "easy":
						accessibility = "&minaccessibility=0&maxaccessibility=0.32";
						break;
					case "medium":
						accessibility = "&minaccessibility=0.33&maxaccessibility=0.66";
						break;
					case "hard":
						accessibility = "&minaccessibility=0.67&maxaccessibility=1";
						break;
				}
				
				activity = getActivity(choice, price, accessibility);
			}
			
			request.setAttribute("activity", activity);
			
			dispatcher = getServletContext().getRequestDispatcher("/explore.jsp");
			dispatcher.forward(request, response);
		}
	}
}
