package com.projeto.restapi.repository;


import com.projeto.restapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u where u.email=?1")
	Usuario findByEmailAddress(String email);
	
}
