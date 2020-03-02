package com.extramarks.journeybuilder.exception;

public class CustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String errorMessage;
	
	public CustomException(String errorMessage) {
		this.errorMessage=errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public static RuntimeException throwException(String message) {
		return new CustomException(message);
	}
}