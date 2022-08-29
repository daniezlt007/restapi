package com.projeto.restapi.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTORequest {

    @NotBlank
    private String descricao;
    @NotBlank
    private Double preco;
    @NotBlank
    private Long categoria;

}
