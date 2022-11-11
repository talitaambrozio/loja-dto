package com.loja3.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ConsultaProdutoDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private String unidade;


}
