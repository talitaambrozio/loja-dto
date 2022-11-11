package com.loja3.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.loja3.model.Produto;
import com.loja3.repository.ProdutoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService {
	
	private final ProdutoRepository repository;
	
	@Transactional
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}
	
	public Produto buscarProduto(Long id) {
		Optional<Produto> produto = repository.findById(id);
		return produto.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado!")) ;
	}
	
	public Page<Produto> buscarTodosProdutos(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	@Transactional
	public void excluir(Long id) {
		Produto produto = this.buscarProduto(id);
		repository.delete(produto);
	}
	
	@Transactional
	public Produto atualizar(Produto produto, Long id) {
		Produto produtoOriginal = this.buscarProduto(id);
		produto.setId(produtoOriginal.getId());
		return repository.save(produto);
	}
	

}
