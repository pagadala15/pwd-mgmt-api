package com.gtree.manage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author SrinivasuluP 
 * 
 * This class represents the output of the password reset
 *         request
 */
@Data
@AllArgsConstructor
public class PasswordResetResponse {

	String statusMessage;

}
