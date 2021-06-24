package com.njit.smp.model;

public class User {
	private String username;
	private String firstName;
	private String lastName;
	private boolean active;

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String user) {
		username = user;
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
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
