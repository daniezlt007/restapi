package com.projeto.restapi.controller;

import com.projeto.restapi.exception.LoginException;
import com.projeto.restapi.model.Usuario;
import com.projeto.restapi.model.dto.UsuarioAuthenticationDTO;
import com.projeto.restapi.model.dto.UsuarioDTO;
import com.projeto.restapi.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

	@Autowired
	private AuthenticateService authenticateService;

	public AuthenticationController(AuthenticateService authenticateService) {
		super();
		this.authenticateService = authenticateService;
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> authenticationUser(@RequestBody UsuarioDTO usuario) {
		Usuario usuarioConvertido = this.authenticateService.converter(usuario.getEmail());
		String token = this.authenticateService.gerarNovoToken(usuarioConvertido);
		usuarioConvertido.setToken(token);
		return new ResponseEntity<UsuarioAuthenticationDTO>(UsuarioAuthenticationDTO.toDto(usuarioConvertido, "Bearer "), HttpStatus.ACCEPTED);
	}

}
