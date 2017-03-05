package com.bank.qms.api;

import com.bank.qms.model.Token;

public interface TokenService {

	public Token genrateToken(String userId);

	Boolean isTokenExpired(Token token);
}
