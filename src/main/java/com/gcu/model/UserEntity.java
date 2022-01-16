package com.gcu.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/*
 * Kacey Morris and Alex Vergara
 * Milestone
 * 11/29/2021
 */

// associate properties with database table and columns
@Table("users")
public class UserEntity {
	@Id
	@Column("ID")
	Long id;
	@Column("USERNAME")
	String username;
	
	@Column("PASSWORD")
	String password;
	
	@Column("ROLE")
	String role;
	
	// default constructor
	public UserEntity() {
		
	}
	
	// data constructor
	public UserEntity(Long id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserEntity [id = " + this.id + ", username = " + this.username + ", password = " + this.password +  ",  role = " + this.role + "]";
	}

	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
