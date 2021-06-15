package com.njit.smp.servlets;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njit.smp.model.BoredItem;

/**
 * Servlet implementation class UserActivityServlet
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
    
    protected BoredItem getActivity(String activity, double price, double accessibility) {
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
			Random generator = new Random();
			
			if (cost == "random" && difficulty == "random") {
				activity = getActivity(choice, 0.0, 0.0);
			}
			else {
				double price = 0;
				int accessibility = 0;
				
				switch (cost.toLowerCase()) {
					case "free":
						price = (generator.nextInt(25)) / 100.0;
					case "low":
						price = (generator.nextInt((int)((0.49-0.25)*10+1))+0.25*10) / 10.0;
					case "moderate":
						price = (generator.nextInt((int)((0.75-0.50)*10+1))+0.50*10) / 10.0;
					case "high":
						price = (generator.nextInt((int)((0.99-0.75)*10+1))+0.75*10) / 10.0;
					case "expensive":
						price = 1.0;
				}
				
				switch (difficulty.toLowerCase()) {
					case "easy":
						price = (generator.nextInt(49)) / 100.0;
					case "medium":
						price = (generator.nextInt((int)((0.99-0.50)*10+1))+0.50*10) / 10.0;
					case "hard":
						price = 1.0;
				}
				
				activity = getActivity(choice, price, accessibility);
			}
			
			request.setAttribute("activity", activity);
			
			dispatcher = getServletContext().getRequestDispatcher("/explore.jsp");
			dispatcher.forward(request, response);
		}
	}
}
