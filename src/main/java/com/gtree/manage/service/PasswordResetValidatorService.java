package com.gtree.manage.service;

import com.gtree.manage.model.PasswordResetRequest;

/**
 * 
 * @author SrinivasuluP
 * 
 *         Interface representing the operations used for Password Reset
 *         input/request validation
 */
public interface PasswordResetValidatorService {

	public boolean passwordResetRequestValidator(PasswordResetRequest request);
}
