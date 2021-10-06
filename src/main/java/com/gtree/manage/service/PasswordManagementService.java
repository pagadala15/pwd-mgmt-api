package com.gtree.manage.service;

import com.gtree.manage.vo.PasswordResetRequest;
import com.gtree.manage.vo.PasswordResetResponse;
/**
 * 
 * @author SrinivasuluP
 *  Interface representing the operations used for Password Reset
 */
public interface PasswordManagementService {

	PasswordResetResponse resetPassword(PasswordResetRequest resetRequest);
}
