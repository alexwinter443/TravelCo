package com.gcu.model;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * Kacey Morris and Alex Vergara
 * Milestone
 * 11/29/2021
 */
public class UserModel {
	private Long id;
	
	@NotNull(message="Username is a required field")
	@Size(min=3, max=32, message="Username must be between 3 and 32 characters")
	private String username;
	
	@NotNull(message="Password is a required field")
	@Size(min=3, max=32, message="Password must be between 3 and 32 characters")
	private String password;
	
	@Size(min=2, max=50, message="First name must be between 2 and 50 characters")
	private String firstname;
	
	@Size(min=2, max=50, message="Last name must be between 2 and 50 characters")
	private String lastname;
	
	@Size(min=7, max=50, message="Email address must be between 7 and 32 characters")
	private String email;
	
	@Size(min=12, max=12, message="Phone number must be 12 characters (000-000-0000)")
	private String phone;
	
	// NOTE ADDED ROLES
	private String roles;
	
	public UserModel()
	{}
	
	public UserModel(String username2, String password2) {
		username = username2;
		password = password2;
	}
	public UserModel(Long id2, String username2, String password2, String role2) {
		id = id2;
		username = username2;
		password = password2;
		roles = role2;
	}
	public UserModel(Long id, String username, String password, String firstName, String lastName, String email, String phone, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
		this.phone = phone;
		this.roles = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String role) {
		this.roles = role;
	}
	public String toString() {
		return "UserModel [username = " + this.username + " password = " + this.password + "]";
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}