package com.loja2.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.loja2.dto.APIErrorDTO;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({LojaException.class})
	public ResponseEntity<APIErrorDTO> handleLojaException(LojaException ex, WebRequest request){
		
		String error = "Erro no sistema";
		
		APIErrorDTO apiError = new APIErrorDTO(ex.getMessage(), error, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<APIErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<APIErrorDTO> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){
		
		String error = "Recurso não encontrado!";
		
		APIErrorDTO apiError = new APIErrorDTO(ex.getMessage(), error, HttpStatus.NOT_FOUND);
		return new ResponseEntity<APIErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
