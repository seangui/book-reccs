package com.bookreccs.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bookreccs.validation.ValidEmail;


public class UserDto {
	
	@NotNull(message="required")
	@Size(min = 1, message = "required")
	private String username; 
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
