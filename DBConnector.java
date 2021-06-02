import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author		Nikhil Shah <ns555@njit.edu>
 * @project Social Media Project
 * Connect Java to SQL Database
**/

class DBConnector{
	
	private String url; // sample url "jdbc:mysql://localhost:3306/HobbyHome"
	private String username;
	private String password;
	private Connection conn;
	
	/**
	 * Constructor
	 * @param 	url			url path to connect to mysql server. Sample: "jdbc:mysql://localhost:3306/HobbyHome"
	 * @param 	username 	username to connect to server
	 * @param 	password	password to connect to server
	**/
	public DBConnector(String url, String username, String password){
		this.url = url;
		this.username = username;
		this.password = password;
		testConnect();
	}
	
	// helper method to test if connection works
	private void testConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
		} catch (SQLException e){
			System.err.println(e.toString());
		} catch (ClassNotFoundException f){
			System.err.println("class not found: " + f.toString());
		}
	}
	
	/**
	 * to add a user to the database
	 *
	 * Checks if username is in use first, then adds all information to the database
	 *
	 * @param newUser		username to be added
	 * @param newPass		password to be hashed and added
	 * @param firstName 	name to be associated
	 * @param lastName 		name to be associated
	 * @param email			email to be associated
	 * @return 				true if user was successfully added to database, false if username is already in use
	**/
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
			ps.setBoolean(6, false);
			ps.executeUpdate();
			return isUser(newUser);
		}
		else{ 
			return false;
		}
	}
	
	// helper method to test if username is in database
	private boolean isUser(String user){
		try {
			String sqlSelectUsernames = "SELECT username FROM login";
			
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
	
	// helper method to hash with SHA-256 protocol
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
	
	/**
	 * Method to try to sign in a user
	 * 
	 * @param user		username to be signed in
	 * @param pass 		password to be checked if matching hashed
	 * @return 			true if user can be signed in, otherwise false
	**/
	public boolean signIn(String user, String pass){
		if (isUser(user)){
			try {
			String sqlSelectUsernames = "SELECT username,passhash FROM login";
			
			ResultSet rs = conn.prepareStatement(sqlSelectUsernames).executeQuery();
			while (rs.next()){
				if (user.equals(rs.getString("username")) && getSHA(pass).equals(rs.getString("passhash"))){
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
		else return false;
	}
}