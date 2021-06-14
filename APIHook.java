package com.njit.smp.servlets;

import java.io.*;
import java.net.*;

import com.njit.smp.model.BoredItem;


public class APIHook {

	private String url = "http://www.boredapi.com/api/activity";

	public BoredItem getHTML(String param) throws Exception {
		String activity = getThis(param);
		StringBuilder result = new StringBuilder();
		String urlToRead = url + activity;
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		for (String line; (line = reader.readLine()) != null;) {
			result.append(line);
		}
		
		return parseJSON(result.toString());
	}
	
	public static BoredItem parseJSON(String js) {
		BoredItem item = new BoredItem();
		
		String[] act = js.split("\"");
		
		item.setActivity(act[3]);
		item.setPrice(Double.valueOf(act[12].substring(1, act[12].length()-1)));
		item.setAccessibility(Double.valueOf(act[22].substring(1, act[22].length()-1)));
		
		return item;
	}

	public String getThis(String category) {
		switch (category.toLowerCase()) {
			case "random":
				url += "/";
				break;
			case "education":
				url += "?type=education";
				break;
			case "recreation":
				url += "?type=recreation";
				break;
			case "social":
				url += "?type=recreation";
				break;
			case "charity":
				url += "?type=charity";
				break;
			case "cooking":
				url += "?type=cooking";
				break;
			case "relaxation":
				url += "?type=relaxation";
				break;
			case "music":
				url += "?type=music";
				break;
			case "busywork":
				url += "?type=busywork";
				break;
		}
		return category;
	}
}