package com.njit.smp.servlets;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.njit.smp.model.DirectMessage;
import com.njit.smp.model.UserMessage;
import com.njit.smp.model.User;

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
	
	public String doesUserExistByName(String firstName, String lastName) {
		String retVal = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlSelectName = "SELECT firstname, lastname FROM HobbyHome.login WHERE firstname=? AND lastname=?";
		
		try {
			ps = this.conn.prepareStatement(sqlSelectName);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			rs = ps.executeQuery();
			if (rs.next()) {
				retVal = rs.getString("firstname") + " " + rs.getString("lastname");
			}
		} catch (SQLException e) {
			System.err.println(e.toString());
		} finally {
			closeResultSetStatement(rs, ps);
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
	
	public User getUser(String firstName, String lastName){
		System.out.println("in connector first name = " + firstName + " last name = " + lastName);
		User us = new User();
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder query = new StringBuilder();
		query.append("SELECT username FROM HobbyHome.login WHERE firstname=?");
		if (lastName != null) {
			query.append(" AND lastname=?");
		}
		String sqlSelectUsernames = query.toString();
		
		try {
			ps = this.conn.prepareStatement(sqlSelectUsernames);
			ps.setString(1, firstName);
			if (lastName != null) { ps.setString(2, lastName); }
			
			rs = ps.executeQuery();
			if (rs.next()) {
				us.setUsername(rs.getString("username"));
			}
		}
		catch (SQLException e){
			System.err.println(e.toString());
		}
		finally {
			closeResultSetStatement(rs, ps);
		}
		return us;
	}
	
	public boolean banUser(String username) {
		boolean retVal = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlSelectUsernames = "UPDATE HobbyHome.login SET password='kZxUFkASl' WHERE username=?";
		try {
			ps = this.conn.prepareStatement(sqlSelectUsernames);
			ps.setString(1, username);
			ps.executeUpdate();
			retVal = true;
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
	 * Method to try to sign in a user
	 * 
	 * @param user		username to be signed in
	 * @param pass 		password to be checked if matching hashed
	 * @return 			1 if user can be signed in, 2 if admin, otherwise 0
	**/
	public int signIn(String user, String pass){
		int retVal = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlSelectUsernames = "SELECT username, password, isadmin FROM HobbyHome.login WHERE username=? AND password=?";
		
			try {
				ps = this.conn.prepareStatement(sqlSelectUsernames);
				ps.setString(1, user);
				ps.setString(2, pass);
				rs = ps.executeQuery();
				while(rs.next()) {
					retVal = 1;
					if (rs.getBoolean("isadmin")) {
						retVal = 2;
					}
					if (rs.getString("password") == "kZxUFkASl") {
						retVal = 3;
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
	
	public boolean pushPost(String username, String userPost, String pageName) {
		boolean retVal = false;
		String sqlAddNewPost = "INSERT INTO HobbyHome.posts(username, postContent, postpage) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = this.conn.prepareStatement(sqlAddNewPost);
			ps.setString(1, username);
			ps.setString(2, userPost);
			ps.setString(3, pageName);
			ps.executeUpdate();
			retVal = true;
		} catch (SQLException e){
			System.err.println(e.toString());
		} finally {
			closeStatement(ps);
		}
		return retVal;
	}
	
	public boolean pushReply(String username, String userPost, int postId, String pageName) {
		boolean retVal = false;
		String sqlAddReply = "INSERT INTO HobbyHome.posts(username, postContent, parentPost, postpage) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = this.conn.prepareStatement(sqlAddReply);
			ps.setString(1, username);
			ps.setString(2, userPost);
			ps.setInt(3, postId);
			ps.setString(4, pageName);
			ps.executeUpdate();
			retVal = true;
		} catch (SQLException e){
			System.err.println(e.toString());
		} finally {
			closeStatement(ps);
		}
		return retVal;
	}
	
	/**
	 * Returns a list of Posts by the person specified in the search parameters
	 * @param firstName			first name of the poster
	 * @param lastName			last name of the poster
	 * @return 					List of Posts ordered by postID
	 */
	
	public List<UserMessage> getUserPosts(String firstName, String lastName, String pageName){
		List<UserMessage> userPosts = new ArrayList<UserMessage>();
		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		StringBuilder sbr = new StringBuilder();
		sbr.append("SELECT u.firstname, u.lastname, p.postcontent, p.postid, p.postpage FROM HobbyHome.login u, HobbyHome.posts p");
		sbr.append(" WHERE u.username=p.username AND p.parentpost is null");
		sbr.append(" AND u.firstname=?");
		if(lastName!=null) {
			sbr.append(" AND u.lastname=?");	
		}
		sbr.append(" ORDER BY postid desc");
		
		String sqlSelectUserPosts = sbr.toString();
		String sqlSelectReplies = "SELECT u.firstname, u.lastname, p.postcontent, p.postpage FROM HobbyHome.login u, HobbyHome.posts p WHERE u.username=p.username AND p.parentpost=?";
		
		try {
			UserMessage post = null;
			ps = this.conn.prepareStatement(sqlSelectUserPosts);
			ps.setString(1, firstName);
			if(lastName!=null) {
				ps.setString(2, lastName);
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				post = new UserMessage();
				post.setPostId(rs.getInt("postid"));
				post.setFirstName(rs.getString("firstname"));
				post.setLastName(rs.getString("lastname"));
				post.setPostContent(rs.getString("postcontent"));
				post.setPageName(rs.getString("postpage"));
				
				ps2 = this.conn.prepareStatement(sqlSelectReplies);
				ps2.setInt(1, post.getPostId());
				rs2 = ps2.executeQuery();
				
				List<UserMessage> replies = new ArrayList<UserMessage>();
				UserMessage reply = null;
				
				while (rs2.next()) {
					reply = new UserMessage();
					reply.setFirstName(rs2.getString("firstname"));
					reply.setLastName(rs2.getString("lastname"));
					reply.setPostContent(rs2.getString("postcontent"));
					reply.setPageName(rs2.getString("postpage"));
					replies.add(reply);
				}
				post.setReplies(replies);
				userPosts.add(post);
			}
		} catch (SQLException e){
			System.err.println(e.toString());
		}
		finally {
			closeResultSetStatement(rs, ps);
		}
		
		return userPosts;
	}
	
	/**
	 * Returns a list of Posts on a page
	 * @param 		pageName	name of page the posts are on
	 * @return 					List of Posts ordered by postID
	 */
	
	public List<UserMessage> getAllPosts(String pageName) {
		List<UserMessage> primaryPosts = new ArrayList<UserMessage>();
		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sqlSelectPrimaryPosts = "SELECT u.firstname, u.lastname, p.postcontent, p.postid, p.postpage FROM HobbyHome.login u, HobbyHome.posts p WHERE u.username=p.username AND p.postPage=? AND p.parentpost is null order by postid desc";
		String sqlSelectReplies = "SELECT u.firstname, u.lastname, p.postcontent, p.postpage FROM HobbyHome.login u, HobbyHome.posts p WHERE u.username=p.username AND p.parentpost=?";
		
		try {
			ps = this.conn.prepareStatement(sqlSelectPrimaryPosts);
			ps.setString(1, pageName);
			ps2 = this.conn.prepareStatement(sqlSelectReplies);
			rs = ps.executeQuery();			
			UserMessage poster = null;
			while (rs.next()) {
				poster = new UserMessage();
				poster.setPostId(rs.getInt("postid"));
				poster.setFirstName(rs.getString("firstname"));
				poster.setLastName(rs.getString("lastname"));
				poster.setPostContent(rs.getString("postcontent"));
				poster.setPageName(rs.getString("postpage"));
				
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
					replier.setPageName(rs2.getString("postpage"));
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
	
	/**
	 * Returns username of user with specified first and last name
	 * 
	 * @param firstname			first name of user that is in contact with main user
	 * @param lastname			last name of user that is in contact with main user
	 * @return					username associated with this account
	 */
	public String getUsername(String firstname, String lastname) {
		String sqlSelectUsername = "Select u.username FROM HobbyHome.login u WHERE u.firstname=? AND u.lastname=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String username = null;
		
		try {
			ps = conn.prepareStatement(sqlSelectUsername);
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			rs = ps.executeQuery();
			rs.next();
			username = rs.getString("username");
			if (username == null || username.isEmpty()) {
				username = null;
			}
		} catch (SQLException e){
			System.err.println(e.toString());
		} finally {
			closeResultSetStatement(rs, ps);
		}
		
		return username;
	}
	
	/**
	 * Returns a list of Messages between two users
	 * @param username 			username logged in and asking for message history
	 * @param firstname			first name of user that is in contact with main user
	 * @param lastname			last name of user that is in contact with main user
	 * @return 					List of messages between the two specified users ordered by messageID
	 */
	public List<DirectMessage> getMessages(String username, String firstname, String lastname){
		List<DirectMessage> messages = new ArrayList<DirectMessage>();
		String otherUsername = getUsername(firstname, lastname);
		
		String sqlSelectUserMessages = "SELECT m.sendingUsername, m.receivingUsername, m.messageContent, m.messageID FROM HobbyHome.messaging m "
				+ "WHERE m.sendingUsername=? AND m.receivingUsername=? ORDER BY messageID";
		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			ps = this.conn.prepareStatement(sqlSelectUserMessages);
			ps.setString(1, username);
			ps.setString(2, otherUsername);
			rs = ps.executeQuery();
			DirectMessage message = null;
			while(rs.next()) {
				message = new DirectMessage();
				message.setUsername(rs.getString("sendingUsername"));
				message.setOtherUsername(rs.getString("receivingUsername"));
				message.setMessage(rs.getString("messageContent"));
				message.setSent(true);
				message.setMessageID(rs.getInt("messageID"));
				messages.add(message);
			}
			ps2 = this.conn.prepareStatement(sqlSelectUserMessages);
			ps2.setString(1, otherUsername);
			ps2.setString(2, username);
			rs2 = ps2.executeQuery();
			while(rs2.next()) {
				message = new DirectMessage();
				message.setUsername(rs2.getString("sendingUsername"));
				message.setOtherUsername(rs2.getString("receivingUsername"));
				message.setMessage(rs2.getString("messageContent"));
				message.setSent(false);
				message.setMessageID(rs2.getInt("messageID"));
				messages.add(message);
			}
			Collections.sort(messages, new Comparator<DirectMessage>() {
				@Override
		        public int compare(DirectMessage one, DirectMessage two) {
					return one.getMessageID() - two.getMessageID();
				}
			});
		} catch (SQLException e){
			System.err.println(e.toString());
		} finally {
			closeResultSetStatement(rs, ps);
			closeResultSetStatement(rs2, ps2);
		}
		
		return messages;
		
	}
	
	/**
	 * Adds a new Message to the Database
	 * 
	 * @param sendingUser		username associated to post
	 * @param receivingUser		post content to be written
	 * @param message			content of message
	 * @return 					true if message was sent, otherwise false
	 */
	public boolean pushMessage(String sendingUser, String firstname, String lastname, String message) {
		boolean retVal = false;
		String receivingUser = getUsername(firstname, lastname);
		String sqlAddMessage = "INSERT INTO HobbyHome.messaging (sendingUsername, receivingUsername, messageContent) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = this.conn.prepareStatement(sqlAddMessage);
			ps.setString(1, sendingUser);
			ps.setString(2, receivingUser);
			ps.setString(3, message);
			ps.executeUpdate();
			retVal = true;
		} catch (SQLException e){
			System.err.println(e.toString());
		} finally {
			closeStatement(ps);
		}
		return retVal;
	}
	
	/**
	 * Returns first name of the username given
	 * @param username			username of the person being searched
	 * @param password			password of the person being searched
	 * @return 					first name of the person being searched
	 */
	
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
				while(rs.next()) {
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
	
	/**
	 * Returns last name of the username given
	 * @param username			username of the person being searched
	 * @param password			password of the person being searched
	 * @return 					last name of the person being searched
	 */
	
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
				while(rs.next()) {
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