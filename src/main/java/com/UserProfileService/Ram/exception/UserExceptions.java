package com.UserProfileService.Ram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User Not Found")
public class UserExceptions extends RuntimeException {

	public UserExceptions(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	  

	

}
