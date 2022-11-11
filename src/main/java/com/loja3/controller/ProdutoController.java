package com.loja3.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja3.dto.produto.ConsultaProdutoDTO;
import com.loja3.dto.produto.ProdutoMapper;
import com.loja3.dto.produto.RegistroProdutoDTO;
import com.loja3.model.Produto;
import com.loja3.service.ProdutoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("v1/produtos")
public class ProdutoController {
	
	private final ProdutoService service;
	
	@PostMapping
	public ResponseEntity<ConsultaProdutoDTO> salvarProduto(@RequestBody RegistroProdutoDTO dto){
		Produto produto = service.salvar(ProdutoMapper.fromDTO(dto));
		return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ConsultaProdutoDTO> buscarProduto(@PathVariable Long id){
		Produto produto = service.buscarProduto(id);
		return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
	}
	
	@GetMapping
	public ResponseEntity<Page<ConsultaProdutoDTO>> buscarTodosProdutos(Pageable pageable){
		
		return ResponseEntity.ok(service.buscarTodosProdutos(pageable).map(ProdutoMapper::fromEntity));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaProdutoDTO> excluirProduto(@PathVariable Long id){
		try {
			service.excluir(id);

			return ResponseEntity.ok().build();

		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ConsultaProdutoDTO> atualizarProduto(@PathVariable Long id,@RequestBody RegistroProdutoDTO dto){
		try {
			Produto produto = service.atualizar(ProdutoMapper.fromDTO(dto), id);

			return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));

		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
