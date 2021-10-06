package com.gtree.manage.service;

import com.gtree.manage.model.PasswordResetRequest;
import com.gtree.manage.model.PasswordResetResponse;

/**
 * 
 * @author SrinivasuluP
 * 
 *         Interface representing the operations used for Password Reset
 */
public interface PasswordManagementService {

	PasswordResetResponse resetPassword(PasswordResetRequest resetRequest);
}
