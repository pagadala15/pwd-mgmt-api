package com.gtree.manage.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.gtree.manage.model.PasswordResetRequest;
import com.gtree.manage.model.PasswordResetResponse;
import com.gtree.manage.service.PasswordManagementService;

/**
 * 
 * @author SrinivasuluP
 *
 * This test class covers the rest controller POST mapping methods
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PasswordManagementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PasswordManagementService service;

	/**
	 * This test is to confirm the post request using the api - /pwd-mgmt/v1/reset
	 * is reaching the Rest controller
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_postRequest_successful() throws Exception {
		when(service.resetPassword(Mockito.any(PasswordResetRequest.class)))
				.thenReturn(new PasswordResetResponse("Password reset Successful"));

		mockMvc.perform(post("/pwd-mgmt/v1/reset").contentType(MediaType.APPLICATION_JSON).content(
				"{\"email\" : \"demo2@gtree.com\", 	 \"newPassword\" : \"1231\", 	 \"confirmPassword\": \"1231\" }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.statusMessage", is("Password reset Successful")));

	}

	/**
	 * This test to cover the scenario where if the input request is wrong, the service throws exception with message HttpStatus.BAD_REQUEST (400)
	 * @throws Exception
	 */
	@Test
	public void test_postRequest_unsuccessful() throws Exception {
		when(service.resetPassword(Mockito.any(PasswordResetRequest.class)))
				.thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input"));

		mockMvc.perform(post("/pwd-mgmt/v1/reset").contentType(MediaType.APPLICATION_JSON).content(
				"{\"email\" : \"demo2@gtree.com\", 	 \"newPassword\" : \"1231\", 	 \"confirmPassword\": \"\" }")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}
}
