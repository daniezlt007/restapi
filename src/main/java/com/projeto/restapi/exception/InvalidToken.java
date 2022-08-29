package com.projeto.restapi.exception;

public class InvalidToken extends RuntimeException {

	public InvalidToken() {
		super("Token Inválido...");
	}

	public InvalidToken(String msg){
		super(msg);
	}

}
