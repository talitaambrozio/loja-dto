package com.loja3.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja3.dto.fornecedor.ConsultaFornecedorDTO;
import com.loja3.dto.fornecedor.FornecedorMapper;
import com.loja3.dto.fornecedor.RegistroFornecedorDTO;
import com.loja3.model.Fornecedor;
import com.loja3.service.FornecedorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/fornecedores")
@AllArgsConstructor
public class FornecedorController {
	
	private final FornecedorService service;
	
	@PostMapping
	public ResponseEntity<ConsultaFornecedorDTO> salvarFornecedor(@RequestBody RegistroFornecedorDTO dto){
		
		Fornecedor fornecedor = service.salvar(FornecedorMapper.fromDTO(dto));
		
		return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
	}
	
	@GetMapping
	public ResponseEntity<Page<ConsultaFornecedorDTO>> listarTodosFornecedores(@PageableDefault(size = 3) Pageable pageable){
		
		return ResponseEntity.ok(service.buscarTodosFornecedores(pageable).map(FornecedorMapper::fromEntity));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ConsultaFornecedorDTO> buscarUmFornecedor(@PathVariable Long id){
		Fornecedor fornecedor = service.buscarFornecedor(id);
		
		return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ConsultaFornecedorDTO> alterar(@RequestBody RegistroFornecedorDTO dto, @PathVariable Long id) {

		try {
			Fornecedor fornecedor = service.atualizar(FornecedorMapper.fromDTO(dto), id);

			return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));

		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaFornecedorDTO> excluirFornecedor(@PathVariable Long id) {

		try {
			service.excluir(id);

			return ResponseEntity.ok().build();

		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}

	}

}
