package com.bank.qms.model;

import java.io.Serializable;

public class Token implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long expirationTime;
	private String tokenId;
	private String userId;

	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Token(Long expirationTime, String tokenId, String userId) {
		super();
		this.expirationTime = expirationTime;
		this.tokenId = tokenId;
		this.userId = userId;
	}

	public Token() {

	}

	@Override
	public String toString() {
		return "Token [expirationTime=" + expirationTime + ", tokenId=" + tokenId + ", userId=" + userId + "]";
	}

}
