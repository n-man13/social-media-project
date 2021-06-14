import java.io.*;
import java.net.*;

public class APIHook {

	private String url = "http://www.boredapi.com/api/activity";

	public String getHTML(String param) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		try (var reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			for (String line; (line = reader.readLine()) != null;) {
				result.append(line);
			}
		}
		return result.toString();
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
	}
}
