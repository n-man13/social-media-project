package com.njit.smp.model;

public class DirectMessage{
	
	private Integer messageID;
	private String user;
	private String otherUser;
	private String message;
	private boolean sentByUser;
	
	public DirectMessage() {
		
	}
	public DirectMessage(Integer id, String username, String other, String content, boolean sentByUs) {
		messageID = id;
		user = username;
		message = content;
		otherUser = other;
		sentByUser = sentByUs;
	}
	public Integer getMessageID() {
		return messageID;
	}
	public String getUsername() {
		return user;
	}
	public String getOtherUser() {
		return otherUser;
	}
	public String getMessage() {
		return message;
	}
	public boolean isSentByUser() {
		return sentByUser;
	}
	public void setMessageID(Integer id) {
		messageID = id;
	}
	public void setUsername(String username) {
		user = username;
	}
	public void setOtherUsername(String otherUsername) {
		otherUser = otherUsername;
	}
	public void setMessage(String content) {
		message = content;
	}
	public void setSent(boolean sentByUs) {
		sentByUser = sentByUs;
	}
}