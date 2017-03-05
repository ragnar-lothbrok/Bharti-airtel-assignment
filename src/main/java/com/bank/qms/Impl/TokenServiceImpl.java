package com.bank.qms.Impl;

import java.util.Calendar;
import java.util.UUID;

import com.bank.qms.api.TokenService;
import com.bank.qms.model.Token;

public class TokenServiceImpl implements TokenService{
	
	private TokenServiceImpl(){
		
	}
	
	private static class SingletonHelper{
		private static final TokenService tokenService = new TokenServiceImpl();
	}
	
	public static TokenService getTokenServiceInstance(){
		return SingletonHelper.tokenService;
	}

	public Token genrateToken(String userId){
		Token token = new Token();
		token.setUserId(userId);
		token.setTokenId(UUID.randomUUID().toString());
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.MINUTE, 5);
		token.setExpirationTime(calender.getTimeInMillis());
		return token;
	}
	
	public Boolean isTokenExpired(Token token){
		if(token != null && token.getExpirationTime() != null){
			if(token.getExpirationTime() >= Calendar.getInstance().getTimeInMillis()){
				System.out.println("Token is still active : "+token.getTokenId());
				return true;
			}else{
				System.out.println("Token is expired : "+token.getTokenId());
				return false;
			}
		}
		return true;
	}
}
