package com.gtree.manage.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.gtree.manage.entity.UserPwd;
import com.gtree.manage.events.MessagePublisher;
import com.gtree.manage.model.PasswordResetRequest;
import com.gtree.manage.repo.UserPwdRepository;

/**
 * 
 * @author SrinivasuluP
 *
 *This test class covers scenarios of publishing events and updating database operations
 */
@SpringBootTest
public class PasswordManagementServiceImplTest {
	
	@Mock
	private UserPwdRepository userRepository;
	
	@Mock
	private MessagePublisher publisher;
	
	@Mock
	private PasswordResetValidatorService validator;
	
	@InjectMocks
	private PasswordManagementServiceImpl target;
	
	/**
	 * This test covers the scenario of
	 *  a valid input request, after validation will call message publisher exactly one time for publishing event
	 *  calls database update to update db record exactly one times
	 */
	@Test
	public void test_resetPassword_successful_publishing() {
		
		PasswordResetRequest request = new PasswordResetRequest();
		 request.setConfirmPassword("1234");
		 request.setNewPassword("1234");
		 request.setEmail("abc@gtree.com");
		 when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new UserPwd());
		 when(validator.passwordResetRequestValidator(Mockito.any())).thenReturn(true);
		 target.resetPassword(request);
		 verify(publisher, times(1)).publishMessage(Mockito.any());
		 verify(userRepository, times(1)).save(Mockito.any());
		 verify(validator, times(1)).passwordResetRequestValidator(Mockito.any());
	}

	/**
	 * This test covers the scenario of
	 *  a valid input request, after validation will call message publisher exactly zero times for publishing event
	 *  calls database update to update db record exactly zero times
	 */
	@Test
	public void test_resetPassword_unsuccessful_publishing() {
		
		PasswordResetRequest request = new PasswordResetRequest();
		 request.setConfirmPassword("1234");
		 request.setNewPassword("1234");
		 request.setEmail("abc@gtree.com");
		 when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new UserPwd());
		 when(validator.passwordResetRequestValidator(Mockito.any())).thenReturn(false);
		 target.resetPassword(request);
		 verify(publisher, times(0)).publishMessage(Mockito.any());
		 verify(userRepository, times(0)).save(Mockito.any());
		 verify(validator, times(1)).passwordResetRequestValidator(Mockito.any());
	}
}
