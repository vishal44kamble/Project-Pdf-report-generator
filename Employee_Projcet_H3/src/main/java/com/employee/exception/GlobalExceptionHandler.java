package com.employee.exception;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException rnfe)
	{
		String message = rnfe.getMessage();
		String code="001";
		
		ApiError error=new ApiError();
		error.setMsg(message);
		error.setCode(code);
		
		return new ResponseEntity<ApiError>(error,HttpStatus.NOT_FOUND);
		
		
		
	}
	
	
}


