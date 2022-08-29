package com.projeto.restapi.service;

import com.projeto.restapi.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
	
	public List<Usuario> findAll();
	public Optional<Usuario> find(Long id);
	public Usuario create(Usuario usuario);

}
