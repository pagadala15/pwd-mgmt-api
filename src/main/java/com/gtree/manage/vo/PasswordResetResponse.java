package com.gtree.manage.vo;
/**
 * 
 * @author SrinivasuluP
 * This class represents the output of the password reset request
 */
public class PasswordResetResponse {

	String statusMessage;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public PasswordResetResponse(String statusMessage) {
		super();
		this.statusMessage = statusMessage;
	}
	
}
