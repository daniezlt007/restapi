package com.projeto.restapi.repository;

import com.projeto.restapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select p from Produto p join Categoria c on p.categoria.idCategoria = c.idCategoria" +
            " where c.idCategoria =:categoriaId")
    List<Produto> getProdutoByCategoriaId(@Param("categoriaId") Long categoriaId);

    @Query("select p from Produto p where p.descricao =:descricao")
    Produto getProdutofindByDescricao(@Param("descricao") String descricao);

}
