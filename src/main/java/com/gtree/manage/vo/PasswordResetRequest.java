package com.gtree.manage.vo;

/**
 * 
 * @author SrinivasuluP
 * This class represents the main PasswordReset request 
 */
public class PasswordResetRequest {

	String email;
	
	String newPassword;
	
	String confirmPassword;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String password) {
		this.newPassword = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}