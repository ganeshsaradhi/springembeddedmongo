package com.capgemini.springbootembeddedmongodb.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler{

	 public EmployeeExceptionHandler() {
		 
	 }
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> userNotFoundException(ResourceNotFoundException e, WebRequest request)
	{
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(e.getMessage());
		errorMessage.setTimestamp(new Date());
		  
		errorMessage.setDescription(request.getDescription(false));
		return new ResponseEntity<Object>(errorMessage,HttpStatus.FORBIDDEN);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request)
	{
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setTimestamp(new Date());
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setDescription(request.getDescription(false));
		return new ResponseEntity<ErrorMessage>( errorMessage,HttpStatus.BAD_REQUEST);
	}
}