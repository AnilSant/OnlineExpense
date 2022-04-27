package com.utility;

public class UserRegistration {

	private String Name;
	private String UserName;
	private String Role;
	private String Password;
	
	public UserRegistration() {
		
	}
	
	public UserRegistration(String userName, String password,String role,String name) {
		super();
		this.Name = name;
		this.UserName = userName;
		this.Role = role;
		this.Password = password;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
