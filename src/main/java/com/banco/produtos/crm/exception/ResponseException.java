package com.banco.produtos.crm.exception;

import lombok.Getter;

@Getter
public class ResponseException {
	private String timestamp;
	private String message;
	private String details;
	
	public ResponseException(String timestamp, String message, String details) {

		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	

}
