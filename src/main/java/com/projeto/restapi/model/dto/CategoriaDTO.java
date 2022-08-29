package com.projeto.restapi.model.dto;

import com.projeto.restapi.model.Categoria;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoriaDTO implements Serializable {

    private Long idCategoria;
    @NotBlank
    private String descricao;

    public static CategoriaDTO converter(Categoria categoria){
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setIdCategoria(categoria.getIdCategoria());
        categoriaDTO.setDescricao(categoria.getDescricao());
        return categoriaDTO;
    }

}
