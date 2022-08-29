package com.projeto.restapi.service;

import com.projeto.restapi.exception.NegocioException;
import com.projeto.restapi.exception.ResourceNotFoundException;
import com.projeto.restapi.model.Categoria;
import com.projeto.restapi.model.Produto;
import com.projeto.restapi.model.dto.CategoriaDTO;
import com.projeto.restapi.model.dto.ProdutoDTO;
import com.projeto.restapi.model.dto.request.ProdutoDTORequest;
import com.projeto.restapi.repository.CategoriaRepository;
import com.projeto.restapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public ProdutoDTO save(ProdutoDTORequest produtoDTO) {
        Produto produto = null;
        Optional<Categoria> categoria = null;
        if(produtoDTO != null){
            categoria = this.categoriaRepository.findById(produtoDTO.getCategoria());
            ProdutoDTO dto =new ProdutoDTO(produtoDTO.getDescricao(), produtoDTO.getPreco(), CategoriaDTO.converter(categoria.get()));
            dto.setCategoria(CategoriaDTO.converter(categoria.get()));
            produto = this.produtoRepository.save(Produto.converter(dto));
            return ProdutoDTO.convert(produto);
        }
        throw new NegocioException("Aconteceu algum problema ao salvar.");
    }

    @Override
    public void delete(ProdutoDTO produtoDTO) {
        if(produtoDTO != null){
            this.produtoRepository.delete(Produto.converter(produtoDTO));
        }
    }

    @Override
    public ProdutoDTO edit(ProdutoDTO produtoDTO) {
        Produto produto = null;
        if(produtoDTO != null){
            produto = Produto.converter(findById(produtoDTO.getIdProduto()));
            if(produto != null){
                produto = this.produtoRepository.save(Produto.converter(produtoDTO));
            }
            return ProdutoDTO.convert(produto);
        }
        throw new NegocioException("Aconteceu algum problema ao editar.");
    }

    @Override
    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = this.produtoRepository.findAll();
        return produtos.stream().map(ProdutoDTO::convert).collect(Collectors.toList());
    }

    @Override
    public List<ProdutoDTO> findAllByCategoriaId(Long id) {
        List<Produto> produtos = this.produtoRepository.getProdutoByCategoriaId(id);
        return produtos.stream().map(ProdutoDTO::convert).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO findById(Long id) {
        Optional<Produto> produto = this.produtoRepository.findById(id);
        if(produto.isPresent()){
            return ProdutoDTO.convert(produto.get());
        }
        throw new ResourceNotFoundException("Produto com ID informado não encontrado.");
    }

    @Override
    public ProdutoDTO findByDescricao(String descricao) {
        Produto produto = this.produtoRepository.getProdutofindByDescricao(descricao);
        if(produto != null){
            return ProdutoDTO.convert(produto);
        }
        throw new ResourceNotFoundException("Produto não encontrado.");
    }
}
