package com.alec.custom_validation_messages.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.ToString;

@ToString
@Component
public class RegistrationDTO {

	@NotEmpty
	@Size(min = 1, max = 10)
	@Pattern(regexp = "^[A-Z][a-z]{0,9}")
	private String username;
	
	@NotEmpty
	// Minimum eight characters, at least one letter and one number
	// @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "{registration.password.policy}")
	@Pattern(regexp = "${password.regexp}", message = "{registration.password.policy}")
	private String password;
	
	@NotEmpty(message = "{registration.email.notempty}")
	@Email
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
