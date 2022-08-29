package com.projeto.restapi.repository;

import com.projeto.restapi.model.Categoria;
import com.projeto.restapi.model.dto.CategoriaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByDescricao(String descricao);

}
