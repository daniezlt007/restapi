package com.projeto.restapi.model;

import com.projeto.restapi.model.dto.CategoriaDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria")
    private Long idCategoria;

    private String descricao;

    public Categoria() {

    }

    public Categoria(Long idCategoria, String descricao) {
        this.idCategoria = idCategoria;
        this.descricao = descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Categoria converter(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoriaDTO.getIdCategoria());
        categoria.setDescricao(categoriaDTO.getDescricao());
        return categoria;
    }
}
