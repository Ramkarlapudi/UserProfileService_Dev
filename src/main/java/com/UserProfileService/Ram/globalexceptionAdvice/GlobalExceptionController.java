package com.UserProfileService.Ram.globalexceptionAdvice;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.UserProfileService.Ram.exception.ExceptionResponse;
import com.UserProfileService.Ram.exception.UploadProfileException;
import com.UserProfileService.Ram.exception.UserExceptions;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserExceptions.class)
	public final ResponseEntity<Object> userException(UserExceptions userExceptions) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), userExceptions.getMessage(),
				"Unable to fetch User Details ");
		ResponseEntity<Object> res = new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
		return res;

	}

	@ExceptionHandler(UploadProfileException.class)
	public final ResponseEntity<Object> uploadUserException(UploadProfileException uploadProfileException) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), uploadProfileException.getMessage(),
				"Something Went Wrong plese check the mandatory items  ");
		ResponseEntity<Object> res = new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
		return res;

	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				"Something Went Wrong plese check the Method Request phrase  ");
		ResponseEntity<Object> res = new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
		// return super.handleHttpRequestMethodNotSupported(ex, headers, status,
		// request);
		return res;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<Object> uploadUserException(IllegalArgumentException illegalArgumentException) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), illegalArgumentException.getMessage(),
				"Something Went Wrong plese check the mandatory request items  ");
		ResponseEntity<Object> res = new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
		return res;

	}

}
