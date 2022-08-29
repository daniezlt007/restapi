package com.projeto.restapi.service;

import com.projeto.restapi.model.Usuario;
import com.projeto.restapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioImplementUsuario implements UsuarioService{
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioImplementUsuario(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) this.usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> find(Long id) {
		return this.usuarioRepository.findById(id);
	}

	@Override
	public Usuario create(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	

}
