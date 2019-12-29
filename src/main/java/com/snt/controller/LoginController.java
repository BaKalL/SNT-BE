package com.snt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snt.constant.APIResponse;
import com.snt.constant.Constants;
import com.snt.dto.LoginDTO;
import com.snt.entity.CredentialPool;
import com.snt.service.CredentialPoolService;
import com.snt.utils.JWTUtils;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	CredentialPoolService credentialPoolService;
	
	@PostMapping("/login")
	public ResponseEntity<APIResponse> login (@RequestBody CredentialPool credential) {
		long took = System.currentTimeMillis();
		try {
			
			took = System.currentTimeMillis() - took;
			CredentialPool record = this.credentialPoolService.getUser(credential);
			if (record != null)
			{
				LoginDTO loginDTO = new LoginDTO();
				
				loginDTO.setAccessToken(JWTUtils.createAccessToken(credential));
				
				loginDTO.setRefreshToken(JWTUtils.createrefreshToken(loginDTO.getAccessToken()));
				
				loginDTO.setAuthenticated(true);
				
				return new ResponseEntity<>(new APIResponse(Constants.HTTP_SUCCESS, Constants.HTTP_SUCCESS_MESSAGE, Constants.HTTP_SUCCESS_MESSAGE, took ,loginDTO), HttpStatus.OK);
			}
			return new ResponseEntity<>(new APIResponse(Constants.HTTP_SUCCESS, Constants.UNAUTHORIZED_DESCRIPTION, Constants.UNAUTHORIZED_DESCRIPTION, took ,null), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(new APIResponse(Constants.HTTP_FAILED, Constants.HTTP_FAILED_MESSAGE, Constants.UNAUTHORIZED_DESCRIPTION, 0 ,null), HttpStatus.BAD_REQUEST);
		}
	}

}
