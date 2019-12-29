package com.snt.utils;

import java.util.Date;
import java.util.UUID;

import com.snt.constant.Constants;
import com.snt.entity.CredentialPool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {

	static private String secretKey = "VlVkb2FHSldVbmxrVnpsMVdqQk9iMkZWYUhCYVdGVTk="; 
	
	static private long accessTokenExpiredTime = 60*60;
	
	static private long refreshTokenExpiredTime = 60*60*24;
	
	static public String createAccessToken (CredentialPool credential) {
		String jti = UUID.randomUUID().toString();
		
		Date expried = new Date(System.currentTimeMillis() + JWTUtils.accessTokenExpiredTime);
		
		
		
		@SuppressWarnings("deprecation")
		String jws = Jwts.builder()
						.setId(jti)
						.setAudience(credential.getUsername())
						.setSubject(Constants.ACCESS_TOKEN)
						.setExpiration(expried)
						.claim("role", Constants.USER_ROLE)
						.signWith( SignatureAlgorithm.HS256, JWTUtils.secretKey.getBytes())
						.compact();
		
		return jws;
	}
	
	static public Claims tokenDecode (String token) {
		
		Claims tokenClaims = Jwts.parser().setSigningKey(JWTUtils.secretKey.getBytes()).parseClaimsJws(token)
							.getBody();
		return tokenClaims;
		
	}
	
	static public String createrefreshToken (String token) {
		String jti = UUID.randomUUID().toString();
		
		Date expried = new Date(System.currentTimeMillis() + JWTUtils.refreshTokenExpiredTime);
		
		
		
		@SuppressWarnings("deprecation")
		String jws = Jwts.builder()
						.setId(jti)
						.setAudience(JWTUtils.tokenDecode(token).getId())
						.setSubject(Constants.REFRESH_TOKEN)
						.setExpiration(expried)
						.signWith( SignatureAlgorithm.HS256, JWTUtils.secretKey.getBytes())
						.compact();
		
		return jws;
	}

}
