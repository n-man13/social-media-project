package com.njit.smp.model;

import java.util.List;

public class UserMessage {
	
	private Integer postid;
	private String firstName;
	private String lastName;
	private String postContent;
	private List<UserMessage> replies;
	
	public UserMessage() {}
	
	public Integer getPostid() {
		return postid;
	}

	public void setPostid(Integer postid) {
		this.postid = postid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPostContent() {
		return postContent;
	}
	
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	
	public List<UserMessage> getReplies() {
		return replies;
	}
	
	public void setReplies(List<UserMessage> replies) {
		this.replies = replies;
	}
}
