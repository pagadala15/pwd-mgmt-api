package com.gtree.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtree.manage.service.PasswordManagementService;
import com.gtree.manage.vo.PasswordResetRequest;
import com.gtree.manage.vo.PasswordResetResponse;
/**
 * 
 * @author SrinivasuluP
 *  This controller is called (POST) to reset passwords of the users
 */
@RestController
@RequestMapping(path="/pwd-mgmt/v1/")
@Validated
public class PasswordManagementController {

	private PasswordManagementService pwdService;
	
	@Autowired
	PasswordManagementController(PasswordManagementService pwdService) {
		this.pwdService = pwdService;
	}

    /**
     * This method is called to reset the password by passing email , new password and confirm password.
     * User is determined/found by email and necessary validations are done before the new password is stored/updated into database
     * The new password is persisted into the db table and an Event 'PassworResetEvent' is created.
     * @param resetRequest
     * @return
     */
    @PostMapping(path= "/reset")
    public PasswordResetResponse selfServicePasswordReset(@RequestBody PasswordResetRequest resetRequest) {    	   		
    	return pwdService.resetPassword(resetRequest);
    }
}
