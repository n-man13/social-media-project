import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
* Author: Nikhil Shah
* Project: Social Media Project
* Connect Java to SQL Database
*/

class DBConnector{
	
	private String url;
	private String username;
	private String password;
	private Connection conn;
	
	public DBConnector(String url, String username, String password){
		this.url = url;
		this.username = username;
		this.password = password;
		testConnect();
	}
	public void testConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
		} catch (SQLException e){
			System.err.println(e.toString());
		}
	}
	public boolean addUser(String newUser, String newPass, String firstName, String lastName, String email){
		if (!isUser(newUser)){
			String passHash = getSHA(newPass);
			String sqlAddEntry = "INSERT INTO login VALUES (default, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sqlAddEntry);
			ps.setString(1, newUser);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, email);
			ps.setString(5, passHash);
			ps.setString(6, false);
			ps.executeUpdate();
			return isUser(newUser);
		}
		else{ 
			return false;
		}
	}
	private boolean isUser(String user){
		try {
			String sqlSelectUsernames = "SELECT username FROM login"
			
			ResultSet rs = conn.prepareStatement(sqlSelectUsernames).executeQuery();
			while (rs.next()){
				if (user.equals(rs.getString("username"))){
					rs.close();
					return true;
				}
			}
			rs.close();
			return false;
			
		} catch (SQLException e){
			System.err.println(e.toString());
		}
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