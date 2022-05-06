package com.banco.produtos.crm.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.banco.produtos.crm.utils.FormatLocalDate;

@ControllerAdvice
public class ProdutoExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseException> handlerException(Exception e, WebRequest request){
		
		String texto = "Erro interno do servidor";
		
		FormatLocalDate flDate = new FormatLocalDate(LocalDateTime.now());
		ResponseException responseException = new ResponseException(flDate.formatDateTime(), texto, request.getDescription(false));
		
		return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ProdutoNotFoundException.class)
	public ResponseEntity<ResponseException> handlerNotFoundException(ProdutoNotFoundException e, WebRequest request){
		
		String texto = "Nao encontrado no Banco de Dados";
		
		FormatLocalDate flDate = new FormatLocalDate(LocalDateTime.now());
		ResponseException responseException = new ResponseException(flDate.formatDateTime(), texto, request.getDescription(false));
		
		return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
	}
}
