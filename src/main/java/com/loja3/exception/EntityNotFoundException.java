package com.loja3.exception;

public class EntityNotFoundException extends LojaException{

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
