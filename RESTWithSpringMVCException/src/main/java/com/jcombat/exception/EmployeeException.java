package com.jcombat.exception;

public class EmployeeException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}
	public EmployeeException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public EmployeeException() {
		super();
	}
}