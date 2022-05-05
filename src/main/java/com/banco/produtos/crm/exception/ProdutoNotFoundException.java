package com.banco.produtos.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ProdutoNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProdutoNotFoundException(String message) {
		super(message);
	}
	
	public ProdutoNotFoundException(Exception exception) {
		super(exception);
	}

}
