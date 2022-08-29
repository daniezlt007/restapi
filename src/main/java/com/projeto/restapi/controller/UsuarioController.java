package com.projeto.restapi.controller;

import com.projeto.restapi.model.Usuario;
import com.projeto.restapi.model.dto.UsuarioAuthenticationDTO;
import com.projeto.restapi.service.TokenService;
import com.projeto.restapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;
	private TokenService tokenService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService, TokenService tokenService) {
		super();
		this.usuarioService = usuarioService;
		this.tokenService = tokenService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findAll() {
		List<Usuario> list = this.usuarioService.findAll();
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}

	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		usuario.setToken(tokenService.generateToken(usuario));
		Usuario usuarioCreated = this.usuarioService.create(usuario);
		return new ResponseEntity<UsuarioAuthenticationDTO>(UsuarioAuthenticationDTO.toDto(usuarioCreated, "Bearer "), HttpStatus.CREATED);
	}

}
