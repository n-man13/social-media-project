package com.njit.smp.servlets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.njit.smp.model.UserMessage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author		Nikhil Shah <ns555@njit.edu>
 * @project 		Social Media Project
 * Connect Java to PostgreSQL Database
**/

public class DBConnector {
	
	private final String url = "jdbc:postgresql://ec2-18-214-195-34.compute-1.amazonaws.com:5432/d31vf441kgsriv"; // sample url "jdbc:postgresql://localhost:5432/HobbyHome"
	private final String username = "cytrvwhcgxsesg";;
	private final String password = "4f7f4474d027fc97cd0a626d85608136869f6a51ecef3a69802ff612c60779a2";
	private Connection conn;
	private static DBConnector instance;
	
	private DBConnector() {	
		this.conn = getConnection();
	}
	
	public static DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
		}
		return instance;
	}
	
	// helper method to test if connection works
	private Connection getConnection() {
		System.err.println("Testing the connection.");
		if(this.conn != null) {
			return this.conn;
		}
		try{
			Class.forName("org.postgresql.Driver");
			this.conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
		} catch (SQLException e){
			System.err.println(e.toString());
		} catch (ClassNotFoundException f){
			System.err.println("class not found: " + f.toString());
		}
		return this.conn;
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
	 * @throws SQLException 
	**/
	public boolean addUser(String newUser, String newPass, String firstName, String lastName, String email) throws SQLException{
		System.out.println("user is: " + newUser + " pass is: " + newPass);
		boolean retVal = false;
		PreparedStatement ps = null;
		if (!isUserExists(newUser)){
			String passHash = newPass; //getSHA(newPass); skipping hashing for alpha
			String sqlAddEntry = "INSERT INTO HobbyHome.login(username, firstname, lastname, email, password, isadmin) VALUES (?, ?, ?, ?, ?, ?)";
			try {
				ps = this.conn.prepareStatement(sqlAddEntry);
				ps.setString(1, newUser);
				ps.setString(2, firstName);
				ps.setString(3, lastName);
				ps.setString(4, email);
				ps.setString(5, passHash);
				ps.setBoolean(6, false);
				ps.executeUpdate();
				retVal = true;
			}
			catch (SQLException e){
				System.err.println(e.toString());
			}
			finally {
				closeStatement(ps);
			}
		}		
		return retVal;
	}
	
	// helper method to test if username is in database
	private boolean isUserExists(String user){
		boolean retValue = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlSelectUsernames = "SELECT username FROM HobbyHome.login WHERE username=?";
		try {			
			ps = this.conn.prepareStatement(sqlSelectUsernames);
			ps.setString(1, user);
			rs = ps.executeQuery();
			if (rs.next()) {
				retValue = true;
			}
		} 
		catch (SQLException e){
			System.err.println(e.toString());
		}
		finally {
			closeResultSetStatement(rs, ps);
		}
		return retValue;
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
		return null;
	}
	
	/**
	 * Method to try to sign in a user
	 * 
	 * @param user		username to be signed in
	 * @param pass 		password to be checked if matching hashed
	 * @return 			1 if user can be signed in, 2 if admin, otherwise 0
	**/
	public int signIn(String user, String pass){
		System.out.println("inside connector user = "+user + "  and pass = " + pass);
		int retVal = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlSelectUsernames = "SELECT username, password, isadmin FROM HobbyHome.login WHERE username=? AND password=?";
		
			try {
				ps = this.conn.prepareStatement(sqlSelectUsernames);
				ps.setString(1, user);
				ps.setString(2, pass);
				rs = ps.executeQuery();
				System.out.println(ps);
				while(rs.next()) {
					System.out.print("Column 1 returned ");
				    System.out.println(rs.getString(1));
					retVal = 1;
					if (rs.getBoolean("isadmin")) {
						retVal = 2;
					}
				}
			}
			catch (SQLException e){
				System.err.println(e.toString());
			}
			finally {
				closeResultSetStatement(rs, ps);
			}
		
		return retVal;
	}
	
	public boolean pushPost(String username, String userPost) {
		boolean retVal = false;
		String sqlAddNewPost = "INSERT INTO HobbyHome.posts(username, postContent) VALUES (?, ?)";
		PreparedStatement ps = null;
		try {
			ps = this.conn.prepareStatement(sqlAddNewPost);
			ps.setString(1, username);
			ps.setString(2, userPost);
			ps.executeUpdate();
			retVal = true;
		} catch (SQLException e){
			System.err.println(e.toString());
		} finally {
			closeStatement(ps);
		}
		return retVal;
	}
	
	public boolean pushReply(String username, String userPost, int postId) {
		boolean retVal = false;
		String sqlAddReply = "INSERT INTO HobbyHome.posts(username, postContent, parentPost) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = this.conn.prepareStatement(sqlAddReply);
			ps.setString(1, username);
			ps.setString(2, userPost);
			ps.setInt(3, postId);
			ps.executeUpdate();
			retVal = true;
		} catch (SQLException e){
			System.err.println(e.toString());
		} finally {
			closeStatement(ps);
		}
		return retVal;
	}
	
	public List<UserMessage> getUserPosts(String firstname, String lastname){
		String sqlSelectUserPosts = "SELECT u.firstname, u.lastname, p.postcontent, p.postid FROM HobbyHome.login u, HobbyHome.posts p "
				+ "WHERE u.username=p.username AND p.parentpost is null AND u.firstname=? AND u.lastname=? ORDER BY postid desc";
		String sqlSelectReplies = "SELECT u.firstname, u.lastname, p.postcontent FROM HobbyHome.login u, HobbyHome.posts p WHERE u.username=p.username AND p.parentpost=?";
		UserMessage post;
		List<UserMessage> posts = new ArrayList<UserMessage>();
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			ps = this.conn.prepareStatement(sqlSelectUserPosts);
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				post = new UserMessage();
				post.setPostid(rs.getInt("postid"));
				post.setFirstName(rs.getString("firstname"));
				post.setLastName(rs.getString("lastname"));
				post.setPostContent(rs.getString("postcontent"));
				
				ps2 = this.conn.prepareStatement(sqlSelectReplies);
				ps2.setInt(1, post.getPostId());
				rs2 = ps2.executeQuery();
				
				List<UserMessage> replies = new ArrayList<UserMessage>();
				UserMessage reply;
				
				while (rs2.next()) {
					reply = new UserMessage();
					reply.setFirstName(rs2.getString("firstname"));
					reply.setLastName(rs2.getString("lastname"));
					reply.setPostContent(rs2.getString("postcontent"));
					replies.add(reply);
				}
				post.setReplies(replies);
				posts.add(post);
			}
		}catch (SQLException e){
			System.err.println(e.toString());
		}finally {
			closeResultSetStatement(rs, ps);
		}
	}
	
	public List<UserMessage> getAllPosts() {
		
		List<UserMessage> primaryPosts = new ArrayList<UserMessage>();
		
		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sqlSelectPrimaryPosts = "SELECT u.firstname, u.lastname, p.postcontent, p.postid FROM HobbyHome.login u, HobbyHome.posts p WHERE u.username=p.username AND p.parentpost is null order by postid desc";
		String sqlSelectReplies = "SELECT u.firstname, u.lastname, p.postcontent FROM HobbyHome.login u, HobbyHome.posts p WHERE u.username=p.username AND p.parentpost=?";
		
		try {
			ps = this.conn.prepareStatement(sqlSelectPrimaryPosts);
			ps2 = this.conn.prepareStatement(sqlSelectReplies);
			rs = ps.executeQuery();			
			UserMessage poster = null;
			while (rs.next()) {
				poster = new UserMessage();
				poster.setPostId(rs.getInt("postid"));
				poster.setFirstName(rs.getString("firstname"));
				poster.setLastName(rs.getString("lastname"));
				poster.setPostContent(rs.getString("postcontent"));
				
				//loop to get replies for this post				
				ps2.setInt(1, poster.getPostId());
				rs2 = ps2.executeQuery();
				List<UserMessage> replies = new ArrayList<UserMessage>();
				UserMessage replier = null;
				
				while (rs2.next()) {
					replier = new UserMessage();
					replier.setFirstName(rs2.getString("firstname"));
					replier.setLastName(rs2.getString("lastname"));
					replier.setPostContent(rs2.getString("postcontent"));
					replies.add(replier);					
				}				
				poster.setReplies(replies);
				primaryPosts.add(poster);
			}			
		}
		catch (SQLException e){
			System.err.println(e.toString());
		}
		finally {
			closeResultSetStatement(rs, ps);
		}
		
		return primaryPosts;
	}
	
	public String getFName(String user, String pass) {
		String retVal = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlSelectUsernames = "SELECT username, password, firstname, lastname FROM HobbyHome.login WHERE username=? AND password=?";
		
			try {
				ps = this.conn.prepareStatement(sqlSelectUsernames);
				ps.setString(1, user);
				ps.setString(2, pass);
				rs = ps.executeQuery();
				System.out.println(ps);
				while(rs.next()) {
					System.out.print("Column 1 returned ");
				    System.out.println(rs.getString("firstname"));
					retVal = rs.getString("firstname");
				}
			}
			catch (SQLException e){
				System.err.println(e.toString());
			}
			finally {
				closeResultSetStatement(rs, ps);
			}
		
		return retVal;
	}
	
	public String getLName(String user, String pass) {
		String retVal = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlSelectUsernames = "SELECT username, password, firstname, lastname FROM HobbyHome.login WHERE username=? AND password=?";
		
			try {
				ps = this.conn.prepareStatement(sqlSelectUsernames);
				ps.setString(1, user);
				ps.setString(2, pass);
				rs = ps.executeQuery();
				System.out.println(ps);
				while(rs.next()) {
					System.out.print("Column 1 returned ");
				    System.out.println(rs.getString("lastname"));
					retVal = rs.getString("lastname");
				}
			}
			catch (SQLException e){
				System.err.println(e.toString());
			}
			finally {
				closeResultSetStatement(rs, ps);
			}
		
		return retVal;
	}

	/**
	 * Run to close the outgoing database connection
	 * @throws SQLException 
	 */
	public void closeConnection() throws SQLException {
		conn.close();
	}
	
	public void closeResultSetStatement(ResultSet rs, Statement ps) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e1) {
        	System.err.println(e1.toString());
        }

        closeStatement(ps);
    }
	public void closeStatement(Statement ps) {
        if (ps != null) {
            try {
                ps.close();
            }
            catch (SQLException e1) {
            	System.err.println(e1.toString());
            }
        }
    }
}