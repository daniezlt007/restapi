package com.projeto.restapi.controller;

import com.projeto.restapi.model.dto.ProdutoDTO;
import com.projeto.restapi.model.dto.request.ProdutoDTORequest;
import com.projeto.restapi.service.AuthenticateService;
import com.projeto.restapi.service.ProdutoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@Api(value = "Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoServiceImpl produtoService;

    @Autowired
    private AuthenticateService authenticateService;

    @GetMapping
    @ApiOperation(value = "Mostra lista de produtos")
    public ResponseEntity<?> getAll(@RequestHeader String authorization){
        Boolean usuarioLogado = this.authenticateService.validarTokenNaRequisicao(authorization);
        if (usuarioLogado) {
            List<ProdutoDTO> listAllProduto = this.produtoService.findAll();
            return !listAllProduto.isEmpty() ? ResponseEntity.ok(listAllProduto) : ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/id/{idproduto}")
    @ApiOperation(value = "Busca produto por id")
    public ResponseEntity<?> getProdutoById(@PathVariable("idproduto") Long idproduto, @RequestHeader String authorization){
        Boolean usuarioLogado = this.authenticateService.validarTokenNaRequisicao(authorization);
        if (usuarioLogado) {
            ProdutoDTO produtoDTO = this.produtoService.findById(idproduto);
            return produtoDTO != null ? ResponseEntity.ok(produtoDTO) : ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/descricao/{descricao}")
    @ApiOperation(value = "Busca produtos por descrição")
    public ResponseEntity<?> getProdutoByDescricao(@PathVariable("descricao") String descricao, @RequestHeader String authorization){
        Boolean usuarioLogado = this.authenticateService.validarTokenNaRequisicao(authorization);
        if (usuarioLogado) {
            ProdutoDTO produtoDTO = this.produtoService.findByDescricao(descricao);
            return produtoDTO != null ? ResponseEntity.ok(produtoDTO) : ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/categoria/{categoriaId}")
    @ApiOperation(value = "Mostra lista de produtos por categorias")
    public ResponseEntity<?> getAllByCategoriaId(@PathVariable("categoriaId") Long categoriaId, @RequestHeader String authorization){
        Boolean usuarioLogado = this.authenticateService.validarTokenNaRequisicao(authorization);
        if (usuarioLogado) {
            List<ProdutoDTO> listAllProduto = this.produtoService.findAllByCategoriaId(categoriaId);
            return !listAllProduto.isEmpty() ? ResponseEntity.ok(listAllProduto) : ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    @ApiOperation(value = "Salva produto")
    public ResponseEntity<?> save(@RequestBody ProdutoDTORequest produtoDTORequest, @RequestHeader String authorization){
        Boolean usuarioLogado = this.authenticateService.validarTokenNaRequisicao(authorization);
        if (usuarioLogado) {
            ProdutoDTO produtoDTO = this.produtoService.save(produtoDTORequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    @ApiOperation(value = "Edita produto")
    public ResponseEntity<?> edit(@RequestBody ProdutoDTO produtoDTO, @RequestHeader String authorization){
        Boolean usuarioLogado = this.authenticateService.validarTokenNaRequisicao(authorization);
        if (usuarioLogado) {
            ProdutoDTO produtoDTOAtualizado = this.produtoService.edit(produtoDTO);
            return ResponseEntity.status(HttpStatus.OK).body(produtoDTOAtualizado);
        }
        return ResponseEntity.badRequest().build();
    }

}
