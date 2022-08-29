package com.projeto.restapi.service;

import com.projeto.restapi.exception.NegocioException;
import com.projeto.restapi.exception.ResourceNotFoundException;
import com.projeto.restapi.model.Categoria;
import com.projeto.restapi.model.dto.CategoriaDTO;
import com.projeto.restapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = null;
        if(categoriaDTO != null){
            CategoriaDTO dto = this.findByDescricao(categoriaDTO.getDescricao());
            if(dto != null){
                throw new NegocioException("Categoria já existe.");
            }
            categoria = this.categoriaRepository.save(Categoria.converter(categoriaDTO));
        }
        return CategoriaDTO.converter(categoria);
    }

    @Override
    public void delete(CategoriaDTO categoriaDTO) {
        if(categoriaDTO != null){
            this.categoriaRepository.delete(Categoria.converter(categoriaDTO));
        }
        throw new NegocioException("Categoria não existe.");
    }

    @Override
    public CategoriaDTO edit(CategoriaDTO categoriaDTO) {
        Categoria categoria = null;
        if(categoriaDTO != null){
            categoria = this.categoriaRepository.save(Categoria.converter(categoriaDTO));
        }
        return CategoriaDTO.converter(categoria);
    }

    @Override
    public List<CategoriaDTO> findAll() {
        List<Categoria> categorias = this.categoriaRepository.findAll();
        return categorias.stream().map(CategoriaDTO::converter).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO findById(Long id) {
        Optional<Categoria> categoria = this.categoriaRepository.findById(id);
        if(categoria.isPresent()){
            return CategoriaDTO.converter(categoria.get());
        }
        throw new ResourceNotFoundException("Categoria não encontrada.");
    }

    @Override
    public CategoriaDTO findByDescricao(String descricao) {
        Categoria categoria = this.categoriaRepository.findByDescricao(descricao);
        if(categoria != null){
            return CategoriaDTO.converter(categoria);
        }
        throw new ResourceNotFoundException("Categoria não encontrada.");
    }
}
