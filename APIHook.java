package com.njit.smp.servlets;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;

import com.njit.smp.model.BoredItem;


public class APIHook {

	private String urlToRead = "https://www.boredapi.com/api/activity";

	public BoredItem getHTML(String param, String price, String accessibility) throws Exception {
		getThis(param, price, accessibility);
		
		StringBuilder result = new StringBuilder();
		
		try {
			URL url = new URL(urlToRead);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			
			if (conn != null) {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String input = "";
					
					while ((input = br.readLine()) != null) {
						result.append(input);
					}
				}
				catch (IOException e) {
					System.err.println(e.toString());
				}
			}
		}
		catch (MalformedURLException e) {
			System.err.println(e.toString());
		}
		catch (IOException e) {
			System.err.println(e.toString());
		}
		
		return parseJSON(result.toString());
	}
	
	public static BoredItem parseJSON(String js) {
		BoredItem item = new BoredItem();
		
		String[] act = js.split("\"");
		
		if (act.length == 5) {
			item.setActivity("error");
		}
		else {
			item.setActivity(act[3]);
			item.setPrice(Double.valueOf(act[12].substring(1, act[12].length()-1)));
			item.setAccessibility(Double.valueOf(act[22].substring(1, act[22].length()-1)));
		}
		
		return item;
	}

	public void getThis(String category, String price, String accessibility) {
		switch (category.toLowerCase()) {
			case "random":
				urlToRead += "/";
				return;
			case "education":
				urlToRead += "?type=education";
				break;
			case "recreation":
				urlToRead += "?type=recreation";
				break;
			case "social":
				urlToRead += "?type=recreation";
				break;
			case "charity":
				urlToRead += "?type=charity";
				break;
			case "cooking":
				urlToRead += "?type=cooking";
				break;
			case "relaxation":
				urlToRead += "?type=relaxation";
				break;
			case "music":
				urlToRead += "?type=music";
				break;
			case "busywork":
				urlToRead += "?type=busywork";
				break;
		}
		
		urlToRead += price + accessibility;
	}
}