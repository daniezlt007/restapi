package com.projeto.restapi.service;

import com.projeto.restapi.exception.*;
import com.projeto.restapi.model.Usuario;
import com.projeto.restapi.model.dto.UsuarioDTO;
import com.projeto.restapi.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticateService {

	private UsuarioRepository usuarioRepository;
	private TokenService tokenService;
	
	@Autowired
	public AuthenticateService(UsuarioRepository usuarioRepository, TokenService tokenService) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.tokenService = tokenService;
	}
	
	public Usuario authenticate(String email, String senha, String authorization) throws LoginException {
		Usuario usuario = this.usuarioRepository.findByEmailAddress(email);
		if(usuario != null){
			if(senha.equals(usuario.getSenha()) && !authorization.isEmpty() && validateToken(authorization)) {
				return usuario;
			}
		}
		throw new ResourceNotFoundException("Usuario não encontrado");
	}

	private boolean validateToken(String Authorization) {
		try {
			try {
				String tokenTratado = Authorization.replace("Bearer ", "");
				Claims claims = tokenService.decodeToken(tokenTratado);
				if(claims.getExpiration().before(new Date(System.currentTimeMillis()))) throw new ExpirationException();
				return true;
			}catch(ExpirationException ex) {
				ex.printStackTrace();
				throw ex;
			}catch(Exception e) {
				//e.printStackTrace();
				throw new InvalidToken();
			}
		} catch (ExpirationException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Usuario converter(String email){
		Usuario usuario = this.usuarioRepository.findByEmailAddress(email);
		if(usuario != null){
			return usuario;
		}
		throw new ResourceNotFoundException("Usuário não encontrado.");
	}

	public Boolean validarTokenNaRequisicao(String token){
		if(!token.isEmpty()){
			Boolean tokenValido = validateToken(token);
			if(tokenValido){
				return true;
			}
		}
		throw new InvalidToken("Erro ao gerar token.");
	}


	public String gerarNovoToken(Usuario usuario){
		String token = this.tokenService.generateToken(usuario);
		if(!token.isEmpty()){
			return token;
		}
		throw new NegocioException("Erro ao gerar token.");
	}
	
}
