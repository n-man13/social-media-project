package com.njit.smp.model;

import java.util.List;

public class UserMessage {
	
	private Integer postId;
	private String firstName;
	private String lastName;
	private String postContent;
	private List<UserMessage> replies;
	
	public UserMessage() {}
	
	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
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
