package com.projeto.restapi.exception;

public class LoginException extends MyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginException() {
		super("E-mail e/ou senha inválidos.");
	}

}
