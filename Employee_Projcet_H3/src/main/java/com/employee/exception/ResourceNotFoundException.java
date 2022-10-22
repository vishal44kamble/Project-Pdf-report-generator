package com.employee.exception;

public class ResourceNotFoundException extends Exception {
	
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String messege)
	{
		super(messege);
	}
	
	public ResourceNotFoundException(String messege,Throwable e)
	{
		
	}

}
