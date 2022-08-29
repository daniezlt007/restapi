package com.projeto.restapi.controller;

import com.projeto.restapi.model.Categoria;
import com.projeto.restapi.model.dto.CategoriaDTO;
import com.projeto.restapi.service.AuthenticateService;
import com.projeto.restapi.service.CategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @Autowired
    private AuthenticateService authenticateService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader String authorization) {
        Boolean usuarioLogado = this.authenticateService.validarTokenNaRequisicao(authorization);
        if (usuarioLogado) {
            List<CategoriaDTO> listAllCategoria = this.categoriaService.findAll();
            return !listAllCategoria.isEmpty() ? ResponseEntity.ok(listAllCategoria) : ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getFindByName(@PathVariable("name") String name, String authorization) {
        Boolean usuarioLogado = this.authenticateService.validarTokenNaRequisicao(authorization);
        if(usuarioLogado){
            CategoriaDTO categoriaDTO = this.categoriaService.findByDescricao(name);
            return ResponseEntity.ok(categoriaDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Categoria categoria, @RequestHeader String authorization) {
        Boolean usuarioLogado = this.authenticateService.validarTokenNaRequisicao(authorization);
        if(usuarioLogado){
            CategoriaDTO categoriaDTO = this.categoriaService.save(CategoriaDTO.converter(categoria));
            Map map = new HashMap<>();
            map.put("categoriaDTO", categoriaDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(categoriaDTO)
                    .toUri();
            return ResponseEntity.created(uri).body(map);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<?> getAllByCategoriaId(@PathVariable("categoriaId") Long categoriaId, @RequestHeader String authorization) {
        Boolean usuarioLogado = this.authenticateService.validarTokenNaRequisicao(authorization);
        if(usuarioLogado){
            CategoriaDTO categoriaDTO = this.categoriaService.findById(categoriaId);
            return categoriaDTO != null ? ResponseEntity.ok(categoriaDTO) : ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

}