package com.gtree.manage.model;

import lombok.Data;

/**
 * 
 * @author SrinivasuluP This class represents the main PasswordReset request
 */
@Data
public class PasswordResetRequest {

	String email;

	String newPassword;

	String confirmPassword;

}
