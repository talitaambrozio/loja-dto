package com.loja3.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.loja3.exception.EntityNotFoundException;
import com.loja3.model.Fornecedor;
import com.loja3.repository.FornecedorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FornecedorService {
	
	private final FornecedorRepository repository;

	public Fornecedor salvar(Fornecedor fornecedor) {
		return repository.save(fornecedor);
	}
	
	public Page<Fornecedor> buscarTodosFornecedores(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Fornecedor buscarFornecedor(Long id) {
		Optional<Fornecedor> optional = repository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrada"));
	}
	
	public void excluir(Long id) {
		Fornecedor fornecedor = this.buscarFornecedor(id);
		repository.delete(fornecedor);
	}
	
	public Fornecedor atualizar(Fornecedor fornecedor, Long id) {
		Fornecedor fornecedorOriginal = this.buscarFornecedor(id);
		fornecedor.setId(fornecedorOriginal.getId());
		return repository.save(fornecedor);
		
	}
}
