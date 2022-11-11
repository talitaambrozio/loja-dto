package com.loja3.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LojaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String message;

	public LojaException(String message) {
		super(message);
		this.message = message;
	}
	
	
	
}
