package com.gtree.manage.service;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gtree.manage.model.PasswordResetRequest;
import com.gtree.manage.repo.UserPwdRepository;

/**
 * 
 * @author SrinivasuluP
 * 
 *         This validator implementation does the necessary validations on the
 *         input request - Checks if the inputs are provided - Checks if the
 *         inputs are in standard acceptable format - Checks if the user exists
 *         based on the inputs
 */
@Service
public class PasswordResetValidatorServiceImpl implements PasswordResetValidatorService {

	final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	private UserPwdRepository userPwdRepo;

	@Autowired
	public PasswordResetValidatorServiceImpl(UserPwdRepository userPwdRepo) {
		this.userPwdRepo = userPwdRepo;
	}

	/**
	 * This method does the crux of input validations. REGEX is used to validate the
	 * format of email Checks if the inputs are correct Checks if the user exists
	 * based on the inputs provided
	 */
	@Override
	public boolean passwordResetRequestValidator(PasswordResetRequest request) {

		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		if (StringUtils.isEmpty(request.getNewPassword()) || StringUtils.isEmpty(request.getConfirmPassword())
				|| StringUtils.isEmpty(request.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input");
		} else if (!request.getNewPassword().equalsIgnoreCase(request.getConfirmPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"New password and confirmation password should match");
		} else if (!pattern.matcher(request.getEmail()).matches()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
		} else if (userPwdRepo.findByEmail(request.getEmail()) == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user found with the email");
		}

		return true;
	}

}
