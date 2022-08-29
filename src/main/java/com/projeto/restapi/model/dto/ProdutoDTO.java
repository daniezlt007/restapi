package com.projeto.restapi.model.dto;

import com.projeto.restapi.model.Produto;
import com.projeto.restapi.model.dto.request.ProdutoDTORequest;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProdutoDTO implements Serializable {

    private Long idProduto;
    @NotBlank
    private String descricao;
    @NotBlank
    private Double preco;
    @NonNull
    private CategoriaDTO categoria;

    public ProdutoDTO(String descricao, Double preco, @NonNull CategoriaDTO categoria) {
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public static ProdutoDTO convert(Produto produto){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setIdProduto(produto.getIdProduto());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setPreco(produto.getPreco());
        if(produto.getCategoria() != null){
            produtoDTO.setCategoria(CategoriaDTO.converter(produto.getCategoria()));
        }
        return produtoDTO;
    }

}
