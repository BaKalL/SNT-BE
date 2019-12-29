package com.snt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

	private String accessToken;
	
	private String refreshToken;
	
	private boolean isAuthenticated;

}
