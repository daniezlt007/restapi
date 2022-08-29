package com.projeto.restapi.model;

import com.projeto.restapi.model.dto.ProdutoDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduto")
    private Long idProduto;

    private String descricao;

    private Double preco;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Produto() {

    }

    public Produto(Long idProduto, String descricao, Double preco, Categoria categoria) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public static Produto converter(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setIdProduto(produtoDTO.getIdProduto());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        if(produtoDTO.getCategoria() != null){
            produto.setCategoria(Categoria.converter(produtoDTO.getCategoria()));
        }
        return produto;
    }

}
