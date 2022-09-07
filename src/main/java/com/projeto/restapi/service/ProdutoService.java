package com.projeto.restapi.service;

import com.projeto.restapi.model.dto.ProdutoDTO;
import com.projeto.restapi.model.dto.request.ProdutoDTORequest;

import java.util.List;

public interface ProdutoService {

    public ProdutoDTO save(ProdutoDTORequest produtoDTO);
    public void delete(Long id);
    public ProdutoDTO edit(ProdutoDTO produtoDTO);
    public List<ProdutoDTO> findAll();
    public List<ProdutoDTO> findAllByCategoriaId(Long id);
    public ProdutoDTO findById(Long id);
    public ProdutoDTO findByDescricao(String descricao);

}
