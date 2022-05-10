package com.core;

import java.io.Serializable;

public class Core implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6811439511286089299L;
	private boolean successful;
	private String message;
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
