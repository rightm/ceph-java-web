package com.web.exception;

public class CephWebException extends Exception{
	/**
	 * specify the exception 
	 */
	private static final long serialVersionUID = 1L;

	public CephWebException() {
		this("Unkonw operation") ;
	}
	
	public CephWebException(String message) {
		super(message)  ;
	}
	
}
