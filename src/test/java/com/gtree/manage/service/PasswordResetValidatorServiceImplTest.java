package com.gtree.manage.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.gtree.manage.entity.UserPwd;
import com.gtree.manage.model.PasswordResetRequest;
import com.gtree.manage.repo.UserPwdRepository;

/**
 * 
 * @author SrinivasuluP
 *
 *This test class covers different scenarios of PasswordRestRequest validations
 */
@SpringBootTest
public class PasswordResetValidatorServiceImplTest {

	@Mock
	private UserPwdRepository userRepository;
	
	@InjectMocks
	private PasswordResetValidatorServiceImpl target;
	
	/**
	 * This test covers the scenario:
	 *   - Wrong/null/blank inputs
	 */
	@Test
	public void test_inputs_not_provided() {
		PasswordResetRequest request = new PasswordResetRequest();
		Exception exception = assertThrows(ResponseStatusException.class, () -> {
			 target.passwordResetRequestValidator(request);
	        });
		 
		 assertTrue(exception.getMessage().contains("Invalid input"));
		 request.setConfirmPassword("123");
		 request.setNewPassword("1234");
		 request.setEmail("abc@gtree.com");
		 
		 exception = assertThrows(ResponseStatusException.class, () -> {
			 target.passwordResetRequestValidator(request);
	        });
		 
		 assertTrue(exception.getMessage().contains("New password and confirmation password should match"));
		 
		 request.setNewPassword("123");
		 request.setEmail("abc@gtre@net.com");
		 exception = assertThrows(ResponseStatusException.class, () -> {
			 target.passwordResetRequestValidator(request);
	        });
		 
		 assertTrue(exception.getMessage().contains("Invalid email"));
	}
	
	/**
	 * This test covers the scenario that deal with
	 *   - password and confirmation password are not equal
	 */
	@Test
	public void test_inputs_passwords_notmatchedd() {
		PasswordResetRequest request = new PasswordResetRequest();
		 request.setConfirmPassword("123");
		 request.setNewPassword("1234");
		 request.setEmail("abc@gtree.com");
		 
		 Exception exception = assertThrows(ResponseStatusException.class, () -> {
			 target.passwordResetRequestValidator(request);
	        });
		 
		 assertTrue(exception.getMessage().contains("New password and confirmation password should match"));

	}
	
	/**
	 * This test covers the scenario that deal with
	 *   - email is not a valid email/syntax
	 */
	@Test
	public void test_inputs_email_not_format_correctly() {
		PasswordResetRequest request = new PasswordResetRequest();
		 request.setConfirmPassword("123");
		 request.setNewPassword("123");
		 request.setEmail("abc@gtre@net.com");
		 Exception exception = assertThrows(ResponseStatusException.class, () -> {
			 target.passwordResetRequestValidator(request);
	        });
		 
		 assertTrue(exception.getMessage().contains("Invalid email"));
	}
	
	/**
	 * This test covers the scenario where user cannot be determined with the email provided
	 * 
	 */
	@Test
	public void test_user_cant_be_determined() {
		 PasswordResetRequest request = new PasswordResetRequest();
		 request.setConfirmPassword("1234");
		 request.setNewPassword("1234");
		 request.setEmail("abc@gtree.com");
		 
		 when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);
		 Exception  exception = assertThrows(ResponseStatusException.class, () -> {
			 target.passwordResetRequestValidator(request);
	        });
		 
		 assertTrue(exception.getMessage().contains("No user found with the email"));
	}
	
	/**
	 * This test covers all successful validations - happy scenario
	 * 
	 */
	@Test
	public void test_validation_successful() {
		 PasswordResetRequest request = new PasswordResetRequest();
		 request.setConfirmPassword("1234");
		 request.setNewPassword("1234");
		 request.setEmail("abc@gtree.com");
		 when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new UserPwd());		 
		 assertTrue(target.passwordResetRequestValidator(request));
	}
}
