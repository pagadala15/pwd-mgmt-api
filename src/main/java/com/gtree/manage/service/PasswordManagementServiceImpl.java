package com.gtree.manage.service;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtree.manage.events.MessagePublisher;
import com.gtree.manage.events.PasswordResetEvent;
import com.gtree.manage.model.PasswordResetRequest;
import com.gtree.manage.model.PasswordResetResponse;
import com.gtree.manage.repo.UserPwdRepository;

/**
 * 
 * @author SrinivasuluP
 * 
 *         Implementation of PasswordManagementService, this class is
 *         responsible for - Calling validator to confirm the inputs are
 *         appropriate - saving/updating the new password by encoding - creating
 *         and publishing an event to indicate PasswordReset Event
 */
@Service
public class PasswordManagementServiceImpl implements PasswordManagementService {

	private PasswordResetValidatorService validator;
	private UserPwdRepository userPwdRepo;
	private MessagePublisher publisher;

	@Autowired
	PasswordManagementServiceImpl(PasswordResetValidatorService validator, UserPwdRepository userPwdRepo,
			MessagePublisher publisher) {
		this.validator = validator;
		this.userPwdRepo = userPwdRepo;
		this.publisher = publisher;
	}

	/**
	 * implementation of password reset service by calling validator to validate
	 * inputs and calling publisher for publishing event on successful reset of
	 * password
	 */
	@Override
	public PasswordResetResponse resetPassword(PasswordResetRequest resetRequest) {
		if (validator.passwordResetRequestValidator(resetRequest)) {
			com.gtree.manage.entity.UserPwd user = userPwdRepo.findByEmail(resetRequest.getEmail());
			user.setPwd(Base64.getEncoder().encodeToString(resetRequest.getNewPassword().getBytes()));
			userPwdRepo.save(user);

			// on successful validation and update, its time to create an event
			publisher.publishMessage(createPasswordResetEvent(user.getId()));

		}

		return new PasswordResetResponse("Password reset Successful");
	}

	/**
	 * Handy method to create a Password reset event
	 * 
	 * @param id
	 * @return
	 */
	private PasswordResetEvent createPasswordResetEvent(Long id) {
		return new PasswordResetEvent("Password Reset has been triggered by user id " + id, new Date());
	}
}
