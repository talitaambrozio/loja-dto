package com.loja3.dto.produto;

import com.loja3.model.Produto;

public class ProdutoMapper {
	
	public static Produto fromDTO(RegistroProdutoDTO dto) {
		return new Produto(null, dto.getNome(), dto.getDescricao(), dto.getUnidade());
	}

	public static ConsultaProdutoDTO fromEntity(Produto produto) {
		return new ConsultaProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getUnidade());
	}
}
