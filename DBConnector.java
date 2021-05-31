import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class DBConnector{
	
	private String url;
	private String username;
	private String password;
	
	public DBConnector(String url, String username, String password){
		this.url = url;
		this.username = username;
		this.password = password;
	}
	public testConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
		} catch (SQLException e){
			System.err.println(e.toString());
		}
	}
	public addUser(String newUser, String newPass, String firstName, String lastName, String email){
		String passHash = getSHA(newPass);
		
	}
	private String getSHA(String input){
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			byte[] messageDigest = md.digest(input.getBytes());
			
			BigInteger num = new BigInteger(1, messageDigest);
			
			String hashtext = num.toString(16);
			
			while (hashtext.length() < 32){
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch(NoSuchAlgorithmException e){
			System.err.println("Exception thrown" + e.toString());
		}
	}
}